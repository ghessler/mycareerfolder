#!/usr/bin/Rscript
#@(#) Create a final report from the aggreateMeans.out file
#==========================================================================
# File            : createFinalResults.R
# Description     : 
#
# Revision History: 
#
# 02 Dec 2011 - Brian Marshall,,,
# Initial Version.
#==========================================================================
#-----------------------------------------------------------------------
# Read in the file as an R-DataFrame.  Here df is like an excel table ...
#-----------------------------------------------------------------------
cat("Aggregates\n")
df <- read.table('aggregateMeans.out', header=TRUE)
print(df)
cat("--------------------------------------------------------------------\n")
#-----------------------------------------------------------------------
# ... group by message size ...
#-----------------------------------------------------------------------
# cat("Messages Received per Second by Message Size\n")
# dg <- split(df, list(df$MsgSize))
# print(dg)
# cat("------------------------------------------------------------------\n")
#-----------------------------------------------------------------------
# print the mean of msgs recvd per second by checksummer and message size
#-----------------------------------------------------------------------
cat("Avg Messages Received per Second by Checksummer and Message Size\n")
dh <- tapply(df$MeanMPS, list(df$Checksummer, df$MsgSize), mean)
print(dh)
#-----------------------------------------------------------------------
# print the standard deviation of msgs recvd per second by checksummer and 
# message size
#-----------------------------------------------------------------------
cat("Std Dev Messages Received per Second by Checksummer and Message Size\n")
print(tapply(df$MeanSD, list(df$Checksummer, df$MsgSize), mean))
cat("--------------------------------------------------------------------\n")
#--------------------------------------------------------------------
# Here Row 1 = Java6
#      Row 2 = Java7
#--------------------------------------------------------------------
cat("Percentage Gain or Loss for Msgs per Sec for Java 7 vs Java 6\n")
print((dh[2,] - dh[1,])/dh[1,] * 100)
cat("\n")

#-----------------------------------------------------------------------
# Now create a plot of the data.  Hmmm, this can be done better
#-----------------------------------------------------------------------
dir <- getwd()

pdf('MsgsPerSecond_Plot.pdf')
par(bg='Light Blue')

java6Data<- dh[1,]
java7Data <- dh[2,]

plotData <- data.frame(java6=java6Data, java7=java7Data)

colors <- c('red', 'black')
lineTypes <- c('dashed', 'solid')

matplot(plotData, type='l', xlab='Msg Size', ylab='Msgs Per Sec', col=colors, 
        lty=lineTypes)
legend('bottomleft', c('java6', 'java7'), col=colors, lty=lineTypes)

dev.off()
