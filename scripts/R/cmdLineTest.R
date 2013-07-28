#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : cmdLineTest.R
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
x <- commandArgs(TRUE)
print(sprintf("There are %d command line arguments\n", length(commandArgs(TRUE))))
class(x)
print(paste("Got >> ", x[1]))

for( fileName in commandArgs(TRUE) )
{
   print(paste("Got a", fileName))
}
