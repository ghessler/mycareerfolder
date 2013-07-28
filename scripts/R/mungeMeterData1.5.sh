#! /bin/bash
# @(#) 
#==========================================================================
# File            : mungeMeterData.sh
# Description     : 
#
# Revision History: 
#
#   18 Oct 2012 - Brian Patrick Marshall,,,
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
for meterFile in meter.Meter?.log
do
   cat $meterFile | grep MsgDelta | awk '{print $2, $9, $13}'  > junk
   echo "Time MsgDelta MsgRate" | cat - junk > ${meterFile}.munged
   rm junk
done

$(StartR) <<- "EndR"
   source('~/bin/R/analyzeMeterData.R')
   
   for( file in list.files(pattern='meter.Meter..log.munged') )
   {
      print(sprintf("Analyzing file %s", file))
      analyzeMeterData(file)
   }
EndR
