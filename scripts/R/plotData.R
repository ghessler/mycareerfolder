#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : Plot the munged message data
# Description     : 
#
# Revision History: 
#
#   01 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
warmUpTime <- 80
msgSizes <- commandArgs(TRUE)
numMessageSizes <- length(msgSizes)

pdf('PerfComparison.pdf')
par(mfrow=c(1, numMessageSizes))

for( msgSize in msgSizes )
{
   java6Data <- paste('java6_', msgSize, '_Data.out', sep='')
   df6 <- read.table(java6Data, header=T)
   df6 <- df6[df6$Seconds >= warmUpTime,]
   df6 <- df6$Recv.Sec
   
   java7Data <- paste('java7_', msgSize, '_Data.out', sep='')
   df7 <- read.table(java7Data, header=T)
   df7 <- df7[df7$Seconds >= warmUpTime,]
   df7 <- df7$Recv.Sec
   
   boxplot(list(J6=df6, J7=df7), main=paste(msgSize, "Byte Msgs"))
}
dev.off()

for( msgSize in msgSizes )
{
   pdf(paste('PerfDensities_', msgSize, '.pdf', sep=''))
   par(mfrow=c(2, 1))

   java6Data <- paste('java6_', msgSize, '_Data.out', sep='')
   df6 <- read.table(java6Data, header=T)
   df6 <- df6[df6$Seconds >= warmUpTime,]
   df6.Recv.Sec <- df6$Recv.Sec
   
   java7Data <- paste('java7_', msgSize, '_Data.out', sep='')
   df7 <- read.table(java7Data, header=T)
   df7 <- df7[df7$Seconds >= warmUpTime,]
   df7.Recv.Sec <- df7$Recv.Sec

   plot(density(df6.Recv.Sec), main=paste("J6", msgSize, "Byte Msgs"))
   plot(density(df7.Recv.Sec), main=paste("J7", msgSize, "Byte Msgs"))

   dev.off()
}
