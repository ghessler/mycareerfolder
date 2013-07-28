#--------------------------------------------------------------------------
# Function: timeSplit
#--------------------------------------------------------------------------
timeSplit <- function(x)
{
   y <- unlist(strsplit(x, ':'))
   z <- as.numeric(unlist(strsplit(y[3], ',')))
   y <- as.numeric(y[1:2])

   y[1]*24*60*60*1000 + y[2]*60*1000 + z[1]*1000 + z[2]
}

#--------------------------------------------------------------------------
# Function: analyzeMeterData
#--------------------------------------------------------------------------
analyzeMeterData <- function(fileName)
{
   baseName = unlist(strsplit(fileName, '\\.'))[2]
   df <- read.table(fileName, header=T)
   dg <- data.frame(list(time=as.numeric(sapply(as.vector(df$Time), timeSplit)),
                         deltaMsgs = as.numeric(sapply(as.vector(df$MsgDelta), 
                                                function(x) gsub(',', '', x))),
                         msgRate = as.numeric(df$MsgRate)))

   dh <- cbind(list(millis=c(1, diff(dg$time))), dg)
   dk <- cbind(dh, list(msgRate2 = dh$deltaMsgs/dh$millis*1000))

   dk <- dk[-1:-10,]

   options(width=300)
   summary(dk)

   connection <- file(sprintf("%s.summary", baseName), 'w')
   writeLines(summary(dk), connection)
   close(connection)
   
   pdfFile = sprintf("%s.pdf", baseName)
   pdf(pdfFile)
   plot(dk$time, dk$msgRate)
   abline(lm(dk$msgRate ~ dk$time))
   dev.off()

   write.table(dk, paste(fileName, '.analyzed', sep=''))
}
