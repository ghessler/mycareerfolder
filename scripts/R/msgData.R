#! /usr/bin/R
# @(#) 
#==========================================================================
# File            : msgData.R
# Description     : 
#
# Revision History: 
#
#   07 Nov 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#--------------------------------------------------------------------------
# Constants 
#--------------------------------------------------------------------------
startLine <- 12
warmupThreshold <- 80

runType <- 0  # Baseline = 0
msgSize <- 8

df <- data.frame()

write(df, file="data.summary")

#==========================================================================
# Function: processTypeDirectory  
#==========================================================================
processTypeDirectory <- function(dir)
{
   for(dir in grep("^\\.$", list.dirs(), value=TRUE, invert=TRUE) )
   {
      processRunDirectory(dir)
   }
}

#==========================================================================
# Function: processRunDirectory  
#==========================================================================
processRunDirectory <- function(dir)
{
   print(sprintf("Operating on directory <%s>", dir))

   runNumber <- 1
   for( file in dir(path=dir, pattern="send.*out") )
   {
      print(sprintf("%s/%s", dir, file))
      data <- getDataFrame(sprintf("%s/%s", dir, file))
      record <- calcAvgSendMPS(data, runNumber) 
      df <<- rbind(df, record)

      runNumber <- runNumber + 1
   }

   runNumber <- 1
   for( file in dir(path=dir, pattern="recv.*out") )
   {
      print(sprintf("%s/%s", dir, file))
      data <- getDataFrame(sprintf("%s/%s", dir, file))
      record <- calcAvgReceiveMPS(data, runNumber) 
      df <<- rbind(df, record)

      runNumber <- runNumber + 1
   }
}

#==========================================================================
# Function:    getDataFrame
# Description: Return the data frame for the passed in file name consisting
#              of the data > warmupThreshold
#==========================================================================
getDataFrame <- function(fileName)
{
   #--------------------------------------------------------------------------
   # Remove the dashes
   #--------------------------------------------------------------------------
   rawData <- readLines(fileName)
   rawData <- grep("---", rawData, value=TRUE, invert=TRUE)
   
   newFileName <- sprintf("%s.NEW", fileName)
   conn <- file(newFileName, "w")
   writeLines(rawData, conn)
   close(conn)
   #--------------------------------------------------------------------------
   # Now re-read the data
   #--------------------------------------------------------------------------
   rawData <- readLines(newFileName)
   indices <- grep("D", rawData)
   endLine <- min(indices[indices >= startLine])
   #--------------------------------------------------------------------------
   # Now get the data frame and remove the new file
   #--------------------------------------------------------------------------
   data <- read.table(newFileName, header=TRUE, skip=10, nrows=endLine-startLine)
   unlink(newFileName)
   #--------------------------------------------------------------------------
   # Remove the first 80 seconds worth of data to account for System warm up
   #--------------------------------------------------------------------------
   data <- data[data$Seconds > warmupThreshold,]

   return(data)
}

#========================================================================== 
# Function: calcReceiveAvgMPS
#==========================================================================
calcAvgReceiveMPS <- function(data, runNumber)
{
   #--------------------------------------------------------------------------
   # Now calculate average (as opposed to instantaneous) msgs/sec 
   #--------------------------------------------------------------------------
   lastIndex <- length(data$Seconds)
   msgsPerSec <- (data$Recv[lastIndex] - data$Recv[1]) / (data$Seconds[lastIndex] - data$Seconds[1])

   print(sprintf("Messages Received per second for a %s run of %dK messages is %f", runType, msgSize, msgsPerSec))

   return(list(RunType=runType, RunNumber=runNumber, MsgSize=msgSize, Operation=1, AvgMPS=msgsPerSec))
}

#==========================================================================
# Function: calcAvgSendMPS
#==========================================================================
calcAvgSendMPS <- function(data, runNumber)
{
   #--------------------------------------------------------------------------
   # Now calculate average (as opposed to instantaneous) msgs/sec 
   #--------------------------------------------------------------------------
   lastIndex <- length(data$Seconds)
   msgsPerSec <- (data$Send[lastIndex] - data$Send[1]) / (data$Seconds[lastIndex] - data$Seconds[1])
   print(sprintf("Messages Sent per second for a %s run of %dK messages is %f", runType, msgSize, msgsPerSec))

   return(list(RunType=runType, RunNumber=runNumber, MsgSize=msgSize, Operation=0, AvgMPS=msgsPerSec))
}

