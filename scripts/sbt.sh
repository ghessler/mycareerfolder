#! /bin/bash
# @(#) 
#==========================================================================
# File            : sbt.sh
# Description     : 
#
# Revision History: 
#
#   19 Sep 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
unset $(env | grep proxy | sed 's/=.*//')
export PATH=$PATH:/home/bmarshal/docs/coursera/scala/sbt/bin
sbt 
