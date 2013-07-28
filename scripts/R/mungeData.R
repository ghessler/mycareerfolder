#==========================================================================
# Function: processAllDirectories
#==========================================================================
processAllDirectories <- function()
{
   #-----------------------------------------------------------------------
   # Walk the collection of directories, and process each ...
   #-----------------------------------------------------------------------
   for(dir in dir()[file.info(dir())$isdir])
   {
      cat('Processing directory ', dir)
      processRunDirectory(dir)
   }
}

#==========================================================================
# Function: getRecvPSData 
#==========================================================================
getRecvPSData <- function(dir)
{
   setwd(dir) 

   cat('Reading allSummary.out in ', dir)
   df <- read.table('allSummary.out', header=T)
   #-----------------------------------------------------------------------
   # Now calculate the mean and standard deviations for all records after
   # 80 seconds
   #-----------------------------------------------------------------------
   dg <- df[df$Seconds > 80,]
   dh <- tapply(dg$Recv.Sec, list(dg$Checksummer, dg$MsgSize), mean)

   setwd('..')

   dh
}

#==========================================================================
# Function: processRunDirectory  
#==========================================================================
processRunDirectory <- function(dir)
{
   setwd(dir) 

   print(paste('Reading allSummary.out in ', dir))
   df <- read.table('allSummary.out', header=T)
   recvPsM <- tapply(df$Recv.Sec, list(df$Checksummer, df$MsgSize), mean)
   recvPsSd <- tapply(df$Recv.Sec, list(df$Checksummer, df$MsgSize), sd)

   sendPsM <- tapply(df$Send.Sec, list(df$Checksummer, df$MsgSize), mean)
   sendPsSd <- tapply(df$Send.Sec, list(df$Checksummer, df$MsgSize), sd)
   
   #-----------------------------------------------------------------------
   # Now calculate the mean and standard deviations for all records after
   # 80 seconds
   #-----------------------------------------------------------------------
   dg <- df[df$Seconds > 80,]
   recvPsM80 <- tapply(dg$Recv.Sec, list(dg$Checksummer, dg$MsgSize), mean)
   recvPsSd80 <- tapply(dg$Recv.Sec, list(dg$Checksummer, dg$MsgSize), sd)

   sendPsM80 <- tapply(dg$Send.Sec, list(dg$Checksummer, dg$MsgSize), mean)
   sendPsSd80 <- tapply(dg$Send.Sec, list(dg$Checksummer, dg$MsgSize), sd)

   write.table(list(recvPsMean=recvPsM80, recvPsSd=recvPsSd80, sendPsM=sendPsM80, sendPsSd=sendPsSd80), file='stats.out')
   write.table(list(recvPsMean=recvPsM80, recvPsSd=recvPsSd80), file='recvs.out')
   write.table(list(sendPsM=sendPsM80, sendPsSd=sendPsSd80), file='sends.out')
   setwd('..')
}

#==========================================================================
# Function: showMeans  
#==========================================================================
showMeans <- function()
{
   dfVector <- c()
   for(dir in dir()[file.info(dir())$isdir])
   {
      print(paste('Means for directory ', dir))
      df <- read.table(paste(dir, '/stats.out', sep=''), header=T)
      dfVector <- c(dfVector, df)
   }

   dfVector
}

#==========================================================================
# Function: showMeansInDir
#==========================================================================
showMeansInDir <- function(dir)
{
   print(paste('Means for directory ', dir))
   df <- read.table(paste(dir, '/stats.out', sep=''), header=T)

   df 
}
