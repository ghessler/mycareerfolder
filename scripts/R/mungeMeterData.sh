#! /bin/bash
# @(#) 
#==========================================================================
# File            : mungeMeterData.sh
# Description     : 
#
# Revision History: 
#
#   23 Oct 2012 - bmarshal
#      Initial Version.
#==========================================================================
#==========================================================================
# Function   : StartR 
# Usage      : $(StartR) R code EndR 
#==========================================================================
function StartR 
{
   echo 'R --quiet --no-save --vanilla --slave'
}

#==========================================================================
#                                    MAIN
#==========================================================================
#--------------------------------------------------------------------------
# Output the store type so that the user knows we are collecting data for
# that store
#--------------------------------------------------------------------------
echo "Collecting data for store type: $(ls *Namer.log | sed 's/_.*//' | figlet --gay)"
#--------------------------------------------------------------------------
# The meter.Meter1.log ... meter.Meter8.log (or whatever the last number is
# (one for each Publisher/Subscriber pair)) contains msg rates data in the
# last field.
#--------------------------------------------------------------------------
for file in meter.Meter?.log
do
   #-----------------------------------------------------------------------
   # Remove 20*5 seconds worth of data to allow for warmup.  This is quick
   # and dirty.
   # TODO:  Let R determine warmup time and have it remove pre-warmup data.
   #-----------------------------------------------------------------------
   cat $file | grep '^2012.*Msg Rate' | awk '{print $NF}' | grep -v [a-zA-Z] | tail -n +20 > $file.rate
done

#--------------------------------------------------------------------------
# Now execute R code from within Bash using a "Here Document" and output the
# results to the file 'msgRatesSummary.dat.   This is a lot better than
# writing code to perform stats, since R is specialized to do so and has
# an immense number of libraries to help.
#--------------------------------------------------------------------------
$(StartR) <<-"EndR" | tee msgRatesSummary.dat
   library(psych)
   options(width=300)

   #-----------------------------------------------------------------------
   # Now get the list of files created above upon which we can operate. 
   # This is the perferred approach; as opposed to looping, prefer using
   # an "apply" method to operate directly on a list.  So create a list of
   # file names ...
   #-----------------------------------------------------------------------
   files <- list.files(pattern = 'meter.Meter.*log.rate$')
   #-----------------------------------------------------------------------
   # ... and create a list of dataframes from it ...
   # Here's how it works: sapply operates on a "vector" by applying the 
   # passed in function to each of its elements.  Here we pass in an
   # anonymous function, which takes an element from the passed in list -
   # a file name - and creates a dataframe.  The returned variable will 
   # consist of dataframes.
   #-----------------------------------------------------------------------
   dataFrames <- sapply(files, function(file) 
                               {
                                  #----------------------------------------
                                  # Read the file contents into a dataframe
                                  #----------------------------------------
                                  df <- read.table(file)
                                  #----------------------------------------
                                  # Name the column
                                  #----------------------------------------
                                  names(df) <- c('Msg Rate')
                                  #----------------------------------------
                                  # Return the dataframe
                                  #----------------------------------------
                                  df
                               })
   #-----------------------------------------------------------------------
   # Rename the column names by stripping 'meter.' and ...  
   #-----------------------------------------------------------------------
   columnNames <- names(dataFrames)
   columnNames <- gsub('meter.', '', columnNames)
   columnNames <- gsub('.log.rate.', ' ', columnNames)
   names(dataFrames) <- columnNames
   #-----------------------------------------------------------------------
   # ... perform summary statistics
   #-----------------------------------------------------------------------
   sapply(dataFrames, function(df) summary(df))
   sapply(dataFrames, function(df) describe(df))
EndR

#--------------------------------------------------------------------------
# Notify that we're done 
#--------------------------------------------------------------------------
if which notify-send
then
   notify-send "Done processing data in $(pwd)"
fi
if which sl 
then
   sl
fi
