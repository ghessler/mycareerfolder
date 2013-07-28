#! /usr/bin/Rscript
# @(#) 
#==========================================================================
# File            : firstScript.R
# Description     : If data in a column contains character data, that columns'
#                   data is coerced to String.  In this case, to perform
#                   statistical computations, it must be converted to numeric
#                   by using as.numeric(as.vector(dataFrame$column)).  
#                   In this script, we give an example that removes the line
#                   containing dashes - right under the header - so that the
#                   resulting data is exclusively numeric.  Then no coercing
#                   occurs and summary stats can be performed straight away.
#
# Revision History: 
#
#   12 Oct 2012 - bmarshal
#      Initial Version.
#==========================================================================
#--------------------------------------------------------------------------
# Lists all files in the current directory; run1 should be there.
#--------------------------------------------------------------------------
dir()
#--------------------------------------------------------------------------
# create a connection - a File Handle - for reading in data ('r' param) 
#--------------------------------------------------------------------------
connection <- file('run1', 'r')
#--------------------------------------------------------------------------
# Read in the whole file into the lines vector (array)
#--------------------------------------------------------------------------
lines <- readLines(connection)
#--------------------------------------------------------------------------
# Release connection resource 
#--------------------------------------------------------------------------
close(connection)
#--------------------------------------------------------------------------
# Display the lines variable
#--------------------------------------------------------------------------
print(lines)
#--------------------------------------------------------------------------
# Show the class of the lines variable.  It is "character" since there are 
# characters in the file, so the whole is coerced to character 
#--------------------------------------------------------------------------
class(lines)
#--------------------------------------------------------------------------
# Now open a file for writing ...
#--------------------------------------------------------------------------
connection <- file('cleanedRun1', 'w')
#--------------------------------------------------------------------------
# ... and write a vector to it. Some clarifications:
#   1. num1:num2  is a vector of all numbers between num1 and num2, inclusive
#   2. thus -1:-11  is the vector (-1, -2, -3, ... , -11)
#   3. temp = lines[-1:-11] is thus the vector that excludes elements 1 
#      through 11 (the meta-data) from the lines vector
#   4. lines[-1:-11][-2] = temp[-2] is the vector obtained by additionally
#      stripping the dashes 
#--------------------------------------------------------------------------
writeLines(lines[-1:-11][-2], connection)
#--------------------------------------------------------------------------
# Release the connection resource 
#--------------------------------------------------------------------------
close(connection) # release resource
#--------------------------------------------------------------------------
# Display the contents of the newly created file and verify that it contains
# no dashes.
#--------------------------------------------------------------------------
file.show('cleanedRun1')
#--------------------------------------------------------------------------
# Now read the data into an R Dataframe and list the header names
# The cat() function (cat for catenate) prints to stdout, '\n' is the newline
# character.
#--------------------------------------------------------------------------
cat("Reading data ...\n")
data <- read.table('cleanedRun1', header=TRUE)
cat("Class of data$Recv.Sec: ")     # now it is numeric
print(class(data$Recv.Sec))
cat("Field Names\n")
names(data)
#--------------------------------------------------------------------------
# Set the display width to 300
#--------------------------------------------------------------------------
options(width=300)
cat("Summary Data\n")
summary(data)
#--------------------------------------------------------------------------
# Display a histogram with an appropriate title (data$Recv.Sec is numeric) 
#--------------------------------------------------------------------------
hist(data$Recv.Sec, main="Messages Per Second")

