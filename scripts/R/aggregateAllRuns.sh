#! /bin/bash
# @(#) 
#==========================================================================
# File            : aggregateAllRuns.sh
# Description     : This script will aggregate all runs in a directory created
#                   by the ~/bin/jmsperf/runAll.sh script.  This script creates
#                   a directory of the form 2011.12.06_12.33 which contains
#                   directories of the form R_<Checksummer>_<MsgSize> each
#                   with a number of run files (run1, run2, ...) and a vmstat.out
#                   file with vm statistics.
#
# Revision History: 
#
#   30 Nov 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Function: StartR 
# Usage   : $(StartR) <<- EndR
#              R Code 
#           EndR 
#==========================================================================
function StartR 
{
   echo 'R --quiet --no-save --vanilla --slave'
}

#==========================================================================
# Function   : lsd
# Description: Lists all directories
#==========================================================================
function lsd
{
   ls -l | grep ^d | awk '{print $NF}'
}

#==========================================================================
# Function   : checkRunDirectory 
# Description: Return "true" if 
#==========================================================================
function checkRunDirectory 
{
   local baseDir=${1:-.}
   #-----------------------------------------------------------------------
   # A run directory must containt files run1, run2, run3, ...
   #-----------------------------------------------------------------------
   [[ -e $baseDir/run1 ]]
}

#==========================================================================
# Function   : aggregateRuns
# Description: Aggregate all the run files (run1, run2 ...) into the file
#              allRuns_$baseDir.out
#==========================================================================
function aggregateRuns 
{
   local baseDir=${1:-.}
   
   #--------------------------------------------------------------------------
   # Bail if $baseDir is not a run directory
   #--------------------------------------------------------------------------
   if ! checkRunDirectory $baseDir
   then
      echo "$baseDir is not a run directory (there are no run files).  Skipping..."
      return
   fi
   #--------------------------------------------------------------------------
   # The header is the twelfth line of each run file.  So just choose run1 to
   # obtain the header info.
   #--------------------------------------------------------------------------
   header=$(sed -n '12p' $baseDir/run1)
   #--------------------------------------------------------------------------
   # Add "Run" to the start of the header 
   #--------------------------------------------------------------------------
   header="Run $header"
   
   for run in $(ls $baseDir/run*)
   do
      #-----------------------------------------------------------------------
      # Now output all lines except the 13th (a bunch of dashes) and the first
      # 12 (just metadata) and prepend the base-name of the run file name.
      # Here 13d means deleted the 13th line and subsequently, 1,12d means 
      # delete lines 1 through 12.
      #-----------------------------------------------------------------------
      sed -e '13d' -e '1,12d' $run | while read line
                                     do
                                        #-------------------------------------
                                        # Prepend the run file name
                                        #-------------------------------------
                                        echo "$(basename $run) $line" >> /tmp/junk.$$
                                     done
   
   done
   
   #--------------------------------------------------------------------------
   # Now prepend the header and write the data to allRuns_$baseDir.out
   #--------------------------------------------------------------------------
   echo $header | cat - /tmp/junk.$$ > allRuns_$baseDir.out
   rm /tmp/junk.$$
}

#==========================================================================
# Function    : outputMeansTable
# Description : 
#==========================================================================
function outputMeansTable
{
   #--------------------------------------------------------------------------
   # Create a header.  Here MPS = Messages Per Second and SD = Std Dev
   #--------------------------------------------------------------------------
   header="Checksummer MsgSize MeanMPS MeanSD"
   
   echo $header
   #--------------------------------------------------------------------------
   # For each allRuns_R_<JavaVersion>_<MsgSize> directory, aggregate the mean
   # numMsgs per second.
   #--------------------------------------------------------------------------
   for r_javaVersion_msgSize in allRuns_*.out
   do
      aggregateMeans.R $r_javaVersion_msgSize
   done
}

#==========================================================================
#                                     MAIN
#==========================================================================
PATH=$PATH:~/bin/R
#--------------------------------------------------------------------------
# Walk the list of all directories and aggregate the runs.  Each directory
# has the form R_<JavaVersion>_<MessageSize>.
# At the end of the aggregateRuns, there will be one file for each directory
# containing all data in run1 ... run9
#--------------------------------------------------------------------------
for r_jv_ms in $(lsd)
do
   echo aggregateRuns $r_jv_ms
   aggregateRuns $r_jv_ms
done

#--------------------------------------------------------------------------
# Now output the means table to stdout and tee it to file.
#--------------------------------------------------------------------------
outputMeansTable | tee aggregateMeans.out

# outputFinalResults.R | tee finalResults.out
#--------------------------------------------------------------------------
# Note the double quote around EndR.  Gotta have it since it keeps the 
# dollar signs from being interpreted in the R code.
#--------------------------------------------------------------------------
$(StartR) <<-"EndR" | tee finalResults.out
   #-----------------------------------------------------------------------
   # Read in the file as an R-DataFrame.  Here df is like an excel table ...
   #-----------------------------------------------------------------------
   cat("Aggregates\n")
   df <- read.table('aggregateMeans.out', header=TRUE)
   print(df)
   cat("--------------------------------------------------------------------\n")
   #-----------------------------------------------------------------------
   # ... group by message size ...
   #-----------------------------------------------------------------------
   # cat("Messages Received per Second by Message Size\n")
   # dg <- split(df, list(df$MsgSize))
   # print(dg)
   # cat("------------------------------------------------------------------\n")
   #-----------------------------------------------------------------------
   # print the mean of msgs recvd per second by checksummer and message size
   #-----------------------------------------------------------------------
   cat("Avg Messages Received per Second by Checksummer and Message Size\n")
   dh <- tapply(df$MeanMPS, list(df$Checksummer, df$MsgSize), mean)
   print(dh)
   #-----------------------------------------------------------------------
   # print the standard deviation of msgs recvd per second by checksummer and 
   # message size
   #-----------------------------------------------------------------------
   cat("Std Dev Messages Received per Second by Checksummer and Message Size\n")
   print(tapply(df$MeanSD, list(df$Checksummer, df$MsgSize), mean))
   cat("--------------------------------------------------------------------\n")
   #--------------------------------------------------------------------
   # Here Row 1 = Java6 
   #      Row 2 =  Java7
   #--------------------------------------------------------------------
   cat("Percentage Gain or Loss for Msgs per Sec for Java7 vs Java6\n")
   print((dh[2,] - dh[1,])/dh[1,] * 100)
   cat("--------------------------------------------------------------------\n")
EndR

createVMStats.sh >> finalResults.out
#--------------------------------------------------------------------------
# The following RScript is equivalent to the embedded code above 
#--------------------------------------------------------------------------
createFinalResults.R
#--------------------------------------------------------------------------
# Use Adobe Acrobat to render the plot, so make sure acroread is in PATH. 
#--------------------------------------------------------------------------
# acroread MsgsPerSecond_Plot.pdf &

~/bin/R/extractMPS.sh

# acroread PerfComparison.pdf &
