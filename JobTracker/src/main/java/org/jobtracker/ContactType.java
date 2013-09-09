package org.jobtracker;

/**
 * @author SpringTeam for questions and corrections
 *
 */
public enum ContactType
{
   Business
   {
      public String greet()
      {
         return "I'm a business contactType";
      }
   },
   Friend
   {
      public String greet()
      {
         return "I'm a friend contactType";
      }
   },
   Coach
   {
      public String greet()
      {
         return "I'm a coach contactType";
      }
   },
   Acquaintance
   {
      public String greet()
      {
         return "I'm a business contacttype";
      }
   };
   
   public abstract String greet();
}
