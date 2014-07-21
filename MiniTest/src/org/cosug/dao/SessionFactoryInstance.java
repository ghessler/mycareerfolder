package org.cosug.dao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author SpringsTeam
 *
 */
public class SessionFactoryInstance
{
   private static SessionFactory sessionFactory;
   private static Lock lock = new ReentrantLock();

   public static SessionFactory sessionFactory() throws HibernateException
   {
      if( sessionFactory == null )
      {
         lock.lock();
         try
         {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                  .buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         }
         catch(Throwable throwable)
         {
            System.err.println("Error: " + throwable.getMessage());
         }
         finally
         {
            lock.unlock();
         }
      }

      return sessionFactory;
   }
}
