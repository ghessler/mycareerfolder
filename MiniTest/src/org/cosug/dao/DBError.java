package org.cosug.dao;

/**
 * @author SpringsTeam
 *
 */
public class DBError extends Exception
{
   private static final long serialVersionUID = 2835100579731003466L;

   /**
    * 
    */
   public DBError()
   {
   }

   /**
    * @param message
    */
   public DBError(String message)
   {
      super(message);
   }

   /**
    * @param cause
    */
   public DBError(Throwable cause)
   {
      super(cause);
   }

   /**
    * @param message
    * @param cause
    */
   public DBError(String message, Throwable cause)
   {
      super(message, cause);
   }

   /**
    * @param message
    * @param cause
    * @param enableSuppression
    * @param writableStackTrace
    */
   public DBError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
