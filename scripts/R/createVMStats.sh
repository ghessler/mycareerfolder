#! /bin/bash
# @(#) 
#==========================================================================
# File            : createVMStats.sh
# Description     : 
#
# Revision History: 
#
#   01 Dec 2011 - Brian Marshall,,,
#      Initial Version.
#==========================================================================
#==========================================================================
# Function: StartR 
# Usage   : $(StartR) R code EndR 
#==========================================================================
function StartR 
{
   echo 'R --quiet --no-save --vanilla --slave'
}

#==========================================================================
# Function   : lsd
# Description: Lists all directories
#==========================================================================
function lsd
{
   ls -l | grep ^d | awk '{print $NF}'
}

#==========================================================================
#                                     MAIN
#==========================================================================
#--------------------------------------------------------------------------
# Walk the list of all directories and aggregate the runs.  Each directory
# has the form R_<Checksummer>_<MessageSize>.
#--------------------------------------------------------------------------
echo "Checksummer MsgSize Idle" > allVmstat.out

for r_ck_ms in $(lsd)
do
   #--------------------------------------------------------------------
   # Extract the Checksummer used and the Message Size from the directory.
   # A reportDirectory has the form R_Checksummer_MsgSize, hence we need
   # to extract the second and third tokens of the '_' separated list.
   # Here tokens is a list of tokens (because of the parens).
   #--------------------------------------------------------------------
   tokens=($(echo $r_ck_ms | sed 's/_/ /g'))
   checksummer=${tokens[1]}
   msgSize=${tokens[2]}
 
   #--------------------------------------------------------------------
   # Now pull out the CPU Idle column of vmstat; this is the third column
   # from the end (hence NF - 2), throw out all lines that don't consist
   # exclusively of digits and finally throw out the first 15 lines to
   # account for warm-up. 
   #--------------------------------------------------------------------
   for line in $(awk '{print $(NF -2)}' $r_ck_ms/vmstat.out | grep '^[0-9][0-9]*' | tail -n +15)
   do
      echo "$checksummer $msgSize $line" >> allVmstat.out
   done
done

$(StartR) <<-EndR
   df <- read.table('allVmstat.out', header=TRUE)
   #--------------------------------------------------------------------
   # Note, here we use df[,3] as example.  This means "all rows" (nothing
   # before the coomma) for the third column (1 based in R); essentially
   # df[,3] is df's third column.  We could have used df$Idle since the
   # third column's header is Idle, but the shell would have tried to
   # interpret $Idle as the value of a variable.
   #--------------------------------------------------------------------
   dg <- tapply(df[,3], list(df[,1], df[,2]), mean)
   cat("VMStats by Checksummer and Message Size\n")
   print(dg)
   cat("\n")

   cat("Percentage Savings in CPU for Adler32\n")
   #--------------------------------------------------------------------
   # Here Row 1 = Adler32
   #      Row 2 = Baseline
   #      Row 3 = NIOAdler32
   #--------------------------------------------------------------------
   print((dg[2,] - dg[1,])/dg[2,] * 100)
   cat("\n")
   cat("Percentage Savings in CPU for NIOAdler32\n")
   print((dg[2,] - dg[3,])/dg[2,] * 100)
   cat("--------------------------------------------------------------------\n")
EndR
