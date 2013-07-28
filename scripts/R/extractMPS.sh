#! /bin/bash
# @(#) Extract the Recv/Sec columns for
#==========================================================================
# File            : extracMPS.sh
# Description     : 
#
# Revision History: 
#
#   01 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Function   : getMsgSizes
# Description: Examine all directories of the form R_java_msgSize and extract
#              the last underscore separated tokens.
#==========================================================================
function getMsgSizes
{
   \ls -d R_java* | awk -F_ '{print $NF}' | sort -nu
}

#==========================================================================
# Function   : filterBadSizes 
# Description: Examine all directories of the form R_java_msgSize and extract
#              the last underscore separated tokens.
#==========================================================================
function  filterBadSizes
{
   local msgSizes=$*
   local acceptableMsgSizes
   
   for msgSize in $msgSizes
   do
      if ! grep -il error java*${msgSize}_Data.out > /dev/null
      then
        acceptableMsgSizes="$acceptableMsgSizes $msgSize" 
      fi
   done

   echo $acceptableMsgSizes
}

#==========================================================================
# Function   : getPerfHeader
# Description: All Performance Reports have the same header, on line 12.
#              So go to a random Performance directory and extract it.
#==========================================================================
function getPerfHeader
{
   sed -n '12p' run1
}

#==========================================================================
# Function   : extractRunData 
# Description: Write all run file's data, starting line 14, to stdout.  The
#              first 13 lines are just meta data and can be discarded.
#==========================================================================
function extractRunData
{
   getPerfHeader
   for runFile in run*
   do
      sed -n '14,$p' $runFile
   done
}

#==========================================================================
# Function   : extractMPSData
# Description: Extract the Messages Per Second data.  This consists of 
#              columns 1 and 5 of the run* files in $dir, whereby column 1
#              is the number of seconds elapsed since the start of the 
#              experiment and column 5 is the 'Recv/Sec' column
#==========================================================================
function extractMPSData
{
   local dir=$1

   cd $dir > /dev/null
      #--------------------------------------------------------------------
      # Only return columns 1 and 5 of extractRunData's data, which is the
      # seconds elapsed and the 'Recv/Sec' column
      #--------------------------------------------------------------------
      extractRunData | awk '{print $1, $5}' 
   cd - > /dev/null
}

#==========================================================================
#                                     MAIN
#==========================================================================
echo "Operating on Messages of size $(getMsgSizes | xargs)" | box
for msgSize in $(getMsgSizes)
do
   for javaVersion in 6 7
   do
      dir="R_java${javaVersion}_$msgSize"
      box extractMPSData $dir > java${javaVersion}_${msgSize}_Data.out
      extractMPSData $dir  > java${javaVersion}_${msgSize}_Data.out
   done
done

plotData.R $(filterBadSizes $(getMsgSizes))
