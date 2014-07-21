package org.cosug.util;

/**
 * @author SpringsTeam
 *
 */
public class OperationCount
{
   private static int count;
   
   public static int count()
   {
      return ++count;
   }

   public static void reset()
   {
      count = 0;
   }
}
