package org.cosug.dao;

/**
 * @author SpringsTeam
 *
 */
public class DAOInitializationException extends RuntimeException
{
   private static final long serialVersionUID = 1901570476665560354L;

   /**
    * 
    */
   public DAOInitializationException()
   {
   }

   /**
    * @param message
    */
   public DAOInitializationException(String message)
   {
      super(message);
   }

   /**
    * @param cause
    */
   public DAOInitializationException(Throwable cause)
   {
      super(cause);
   }

   /**
    * @param message
    * @param cause
    */
   public DAOInitializationException(String message, Throwable cause)
   {
      super(message, cause);
   }

   /**
    * @param message
    * @param cause
    * @param enableSuppression
    * @param writableStackTrace
    */
   public DAOInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
