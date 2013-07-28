#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : perfComparison.R
# Description     : 
#
# Revision History: 
#
#   04 Apr 2012 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Here TRUE prints out all args after --args
#==========================================================================
for( arg in commandArgs(TRUE) )
{
   cat(paste("Processing ", arg, '\n'))
   df <- read.table(arg, header=TRUE, sep=',')
   cat(paste("Contents of ", arg))
   dg <- split(df, list(df$Operation, df$StorePolicy))

   print(dg)
}
