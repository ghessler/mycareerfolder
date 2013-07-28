#! /usr/bin/perl
# @(#) Find duplicate Classes
#==========================================================================
# File            : findDups.pl
# Description     : 
#
# Revision History: 
#
#   13 Mar 2013 - U-CENTRIPITAL\bmarshall
#      Initial Version.
#==========================================================================
use strict;
use File::Find;
use FileHandle;

my %fileByName = {};

my $outFile = FileHandle->new('/tmp/duplicateClasses.dat', 'w') or die $!;

find(\&handleDups, '.');

for my $file (keys %fileByName)
{
  print "$file\n" if $fileByName{$file} > 1;
}

#==========================================================================
# Function: handleDups
#==========================================================================
sub handleDups()
{
  my $file = $_;
  
  if( $file =~ m/java$/ )
  {
     $fileByName{$file}++;
  }
}
