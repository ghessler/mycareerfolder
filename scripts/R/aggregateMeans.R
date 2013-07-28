#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : aggregateMeans.R
# Description     : 
#
# Revision History: 
#
#   30 Nov 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Here TRUE prints out all args after --args
#==========================================================================
for( fileName in commandArgs(TRUE) )
{
   df <- read.table(fileName, header=TRUE)
   #-----------------------------------------------------------------------
   # Allow for warmup, hence include only rows for which Seconds > 80.  Here
   # we filter out all rows for which the Seconds column has a value > 80.
   # Note that the R expression df[expr,] filters out all columns (nothing
   # specified after the comma) of the df dataset for which expr is true.
   #-----------------------------------------------------------------------
   df <- df[df$Seconds > 80,]

   #-----------------------------------------------------------------------
   # fileName has the form allruns_R_Factor1_Factor2;  Extract both Factor1 
   # and Factor2.  R has '1' based arrays.
   # Note strsplit returns a list (= Map in other languages) and unlist
   # returns a vector
   #-----------------------------------------------------------------------
   tokens <- unlist(strsplit(fileName, "_"))
   factor1 <- tokens[3]
   factor2 <- (unlist(strsplit(tokens[4], '\\.')))[1]
   cat(factor1, " ", factor2, " ")

   #-----------------------------------------------------------------------
   # First get the mean num msgs recv'd per second grouped by run number
   # (run1, run2 ...) and then average those.  Same for standard deviation
   #-----------------------------------------------------------------------
   cat(mean(tapply(df$Recv.Sec, list(df$Run), mean)), " ")
   cat(mean(tapply(df$Recv.Sec, list(df$Run), sd)), "\n")
}
