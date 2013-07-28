#! /bin/bash
# @(#) 
#==========================================================================
# File            : mungeMeterData2.sh
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
# The meter.Meter1.log ... meter.Meter8.log (or whatever the last number is
# (one for each Publisher/Subscriber pair)) contains msg rates data in the
# last field.
#--------------------------------------------------------------------------
for file in meter.Meter?.log
do
   #-----------------------------------------------------------------------
   # Remove 20*5 seconds worth of data to allow for warmup
   #-----------------------------------------------------------------------
   cat $file | grep '^2012.*Msg Rate' | awk '{print $NF}' | grep -v [a-zA-Z] | tail -n +20 > $file.rate
done

#--------------------------------------------------------------------------
# Now execute R code from with Bash using a "Here Document" and output the
# results to the file 'msgRatesSummary.dat.   This is a lot better than
# writing code to perform stats, since R is specialized to do so and has
# an immense number of libraries to help.
#--------------------------------------------------------------------------
$(StartR) <<-"EndR" | tee msgRatesSummary.dat
   library(psych)
   options(width=300)

   #-----------------------------------------------------------------------
   # Now walk the files created above, the meter.Meter?.log.rate files 
   #-----------------------------------------------------------------------
   for( file in list.files(pattern = 'meter.Meter.*log.rate$'))
   {
      #--------------------------------------------------------------------
      # Read the data into a data frame
      #--------------------------------------------------------------------
      df <- read.table(file)
      #--------------------------------------------------------------------
      # Give the data a name
      #--------------------------------------------------------------------
      names(df) <- c('Msg Rate')
      #--------------------------------------------------------------------
      # Print summary statistics.  Note the describe() function is in the
      # "psych" library, hence the inclusion above
      #--------------------------------------------------------------------
      print(summary(df))
      print(describe(df))
   }
EndR
