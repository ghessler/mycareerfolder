#! /bin/bash
# @(#) 
#==========================================================================
# File            : summarize.sh
# Description     : 
#
# Revision History: 
#
#   16 Oct 2012 - Brian Patrick Marshall,,,
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

#==========================    MAIN    ====================================
file=${1:?"Usage $0 <meterFile>"}

echo Msgs.Sec > ${file}.data
grep 'Msg Rate' $file | sed 's/.*= //' >> ${file}.data

$(StartR) <<-EndR | tee ${file}.out
   pdf("PerfComparisons_$file.pdf") 
   print(df <- read.table("${file}.data", header=TRUE))
   summary(df)
   hist(df[[1]], probability=TRUE)
   lines(density(df[[1]]))
   dev.off()
EndR

