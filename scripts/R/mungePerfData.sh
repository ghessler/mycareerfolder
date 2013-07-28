#! /bin/bash
# @(#) 
#==========================================================================
# File            : mungePerfData.sh
# Description     : 
#
# Revision History: 
#
#   06 Apr 2012 - Brian Marshall,,,
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
# Function   : mergeData 
#==========================================================================
function mergeData
{
   header=$(sed -n '1p' $(lf))
   for file in *.dat
   do
      sed -i '1d' $file
   done

   echo $header | cat - *.dat > allData.dat
}

#==========================================================================
#                                     MAIN
#==========================================================================
cd /home/bmarshal/workspaces/WLS2/HeapTests
#--------------------------------------------------------------------------
# cd to the newest directory and merge the data
#--------------------------------------------------------------------------
cd $(lsd | tail -1)
mergeData

$(StartR) <<-"EndR"
   df <- read.table("allData.dat", header=T, sep=',')
   split(df, list(df$Operation,df$StorePolicy))
EndR

