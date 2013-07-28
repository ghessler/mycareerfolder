#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : mungeSimplePerfData.R
# Description     : 
#
# Revision History: 
#
#   08 Aug 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
createMPS_Data <- function(file)
{
   options(digits=4)

   deltaFileName = paste(unlist(strsplit(file, '\\.'))[1], '.delta', sep='') 
   df <- read.table(file, header=TRUE)
   #-----------------------------------------------------------------------
   # Remove the first row from the data set and add a row of (0,0) at its
   # end to keep the same dimension ...
   #-----------------------------------------------------------------------
   dg <- rbind(df[-1,], c(0, 0))

   #-----------------------------------------------------------------------
   # ... and now do the differences in order to get Messages Received per
   # reporting interval.  Remove the last row, which is bogus.
   #-----------------------------------------------------------------------
   dk <- dg - df
   dk <- dk[-dim(dk)[1],]
   dk <- cbind(dk, Recv.Sec=dk$Recvd/dk$Time*1000)

   write.table(dk, file=deltaFileName, row.names=FALSE)
}

#--------------------------------------------------------------------------
# Create a list of all "receiver*.dat" files and have the createMPS_Data
# function operate on each in order to create a delta file.
#--------------------------------------------------------------------------
receiverFiles <- list.files(pattern="receiver.*dat")
lapply(receiverFiles, createMPS_Data)
