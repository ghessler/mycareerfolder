#! /bin/bash
# @(#) 
#==========================================================================
# File            : moveReportDirs.sh
# Description     : 
#
# Revision History: 
#
#   05 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
for dir in $(lsd | grep '^2012')
do
  cd $dir
  acroread *.pdf & vi final*
  cd -

  read -n 1 -p "Move: ?" response
  if [[ $response == [yY] ]]
  then
     mv $dir examined   
  fi
done

