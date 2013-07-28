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

function goodMsgSizes
{
   filterBadSizes $(getMsgSizes)
}
