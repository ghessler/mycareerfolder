#! /bin/bash
# @(#) 
#==========================================================================
# File            : outputMeansTable.sh
# Description     : 
#
# Revision History: 
#
#   30 Nov 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#--------------------------------------------------------------------------
# Create a header
#--------------------------------------------------------------------------
header="Checksummer MsgSize MeanMPS MeanSD"

echo $header
#--------------------------------------------------------------------------
# For each allRuns_R_<Checksummer>_<MsgSize> directory, aggregate the mean
# numMsgs per second.
#--------------------------------------------------------------------------
for r_cksummer_msgSize in allRuns_*.out
do
   aggregateMeans.R $r_cksummer_msgSize
done
