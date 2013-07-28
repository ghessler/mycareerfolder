#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : outputFinalResults.R
# Description     : 
#
# Revision History: 
#
#   30 Nov 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#-----------------------------------------------------------------------
# Read in the file as an R-DataFrame ...
#-----------------------------------------------------------------------
df <- read.table('aggregateMeans.out', header=TRUE)
print(df)
cat("\n\n")
#-----------------------------------------------------------------------
# ... group by message size 
#-----------------------------------------------------------------------
dg <- split(df, list(df$MsgSize))
# write.table(dg, col.names=FALSE)
print(dg)
cat("\n\n")
#-----------------------------------------------------------------------
# ... and finally create a table of msgs recvd per sec by checksummer 
# and msgSize
#-----------------------------------------------------------------------
dh <- tapply(df$MeanMPS, list(df$Checksummer, df$MsgSize), mean)
print(dh)

