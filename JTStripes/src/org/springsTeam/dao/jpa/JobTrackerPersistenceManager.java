package org.springsTeam.dao.jpa;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author SpringTeam for questions and corrections
 *
 */
public class JobTrackerPersistenceManager
{
   private static SessionFactory sessionFactory;
   private Session session = sessionFactory.openSession();

   static
   {
      Configuration configuration = new AnnotationConfiguration().configure();
      sessionFactory = configuration.buildSessionFactory();
   }

   /**
    * @return the factory
    */
   public static SessionFactory getFactory()
   {
      return sessionFactory;
   }
}
