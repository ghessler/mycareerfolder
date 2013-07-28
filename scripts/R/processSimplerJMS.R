#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : processSimplerJMS.R
# Description     : 
#
# Revision History: 
#
#   17 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
fileName <- "receiver_6_50000_java7_Report.dat"
df <- read.table(fileName, header=T)

#--------------------------------------------------------------------------
# Remove the first row and append a row of zeros to perform deltas, but 
# remove the last row of the deltas
#--------------------------------------------------------------------------
dg <- rbind(df[-1,], c(0,0))
dh <- (dg - df)[-dim(df)[1],]

dk <- cbind(dh, recPerSec=dh[,2]/dh[,1]*1000)


fileName <- "sender_50000_java7_Report.dat"
df <- read.table(fileName, header=T)
#--------------------------------------------------------------------------
# Remove the first row and append a row of zeros to perform deltas, but 
# remove the last row of the deltas
#--------------------------------------------------------------------------
dg <- rbind(df[-1,], c(0,0))
#--------------------------------------------------------------------------
# dh now has the number of messages sent per second
#--------------------------------------------------------------------------
dh <- (dg - df)[-dim(df)[1],]

dk <- cbind(dh, sentPerSec=dh[,2]/dh[,1]*1000)
#--------------------------------------------------------------------------
# Now strip the first column (time) and add by column to obtain the total
# number of messages sent
#--------------------------------------------------------------------------
dl <- cbind(dh, sum=apply(dh[,-1], 1, sum))
dm <- cbind(dh, sentPerSec=dl[,dim(dl)[2]]/dl[,1]*1000)


