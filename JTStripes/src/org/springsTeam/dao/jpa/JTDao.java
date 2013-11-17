package org.springsTeam.dao.jpa;

import org.hibernate.Session;

/**
 * @author SpringsTeam
 *
 */
public class JTDao
{
   public static Session session = JobTrackerPersistenceManager.getFactory().openSession();

   /**
    * 
    */
   public static void commit()
   {
      session.getTransaction().commit();
   }

   public static void startTransaction()
   {
      session.beginTransaction();
   }

}
