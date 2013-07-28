#! /bin/bash
# @(#) 
#==========================================================================
# File            : createReports.sh
# Description     : 
#
# Revision History: 
#
#   05 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Function: findBadMsgSizes
#==========================================================================
function findBadMsgSizes
{
   find | xargs grep -il err  | grep R_java | sed -e 's/.*_//'  -e 's/\/.*$//'
}

#==========================================================================
# Function: cleanDir 
#==========================================================================
function cleanDir
{
   echo "Bad Msg Sizes: $(findBadMsgSizes)"
   for badMsgSize in $(findBadMsgSizes)
   do
      box "rm -r R_java*_${badMsgSize}"
      rm -r R_java*_${badMsgSize}
      promptToContinue "Found bad file $badMsgSize in $dir"
   done
}

#==========================================================================
# Function: processDir
#==========================================================================
function processDir
{
  local dir=$1

  cd $dir > /dev/null
     cleanDir
     box ~/bin/R/aggregateAllRuns.sh
     ~/bin/R/aggregateAllRuns.sh
  cd -  > /dev/null
}


#==========================================================================
#                                     MAIN
#==========================================================================
for dir in $(lsd | grep ^2)
do
  # box "Processing $dir"
  processDir $dir
done
