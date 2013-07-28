#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : createBoxPlot
# Description     : 
#
# Revision History: 
#
#   31 Jul 2012 - Brian Patrick Marshall,,,
#      Initial Version.
#==========================================================================
getMessageSizes <- function()
{
   tokens <- c()
   
   for( file in list.files(pattern="allRuns_R_java.*.out") )
   {
     tokens <- c(tokens, unlist(strsplit(unlist(strsplit(file, "_"))[4], "\\."))[1])
   }
   
   unique(tokens)
}

for( size in getMessageSizes() )
{
#   par(mfrow=c(1, 3))
   size <- 100
   jv <- 6
   fileName <- paste('allRuns_R_java', jv, "_", size, ".out", sep='')
   df <- read.table(fileName, header=TRUE)
   dg <- df[df$Seconds > 100,]
   mps6 <- dg[, c('Recv.Sec')]

   jv <- 7
   fileName <- paste('allRuns_R_java', jv, "_", size, ".out", sep='')
   df <- read.table(fileName, header=TRUE)
   dg <- df[df$Seconds > 100,]
   mps7 <- dg[, c('Recv.Sec')]

   boxplot(mps6, mps7)
}

for( file in list.files(pattern = 'recvPerSec_.*.out') )
{
  df <- read.table(file, header=T)
#  df <- df[df$Seconds > 80,]
  print(file)
  print(head(df))
}


