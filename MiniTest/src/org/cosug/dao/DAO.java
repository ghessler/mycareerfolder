package org.cosug.dao;

import java.util.ArrayList;
import java.util.List;

import org.cosug.model.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This DAO will provide the CRUD operations for objects of type T.
 * C = Create
 * R = Read
 * U = Update
 * D = Delete
 * @author SpringsTeam
 *
 */
public class DAO<T>
{
   private static SessionFactory sessionFactory = SessionFactoryInstance.sessionFactory();
   private static Session session = sessionFactory.openSession();
   private List<T> ts = new ArrayList<>();
   private Class<T> clazz;
   
   public DAO(String className)
   {
      try
      {
         clazz = (Class<T>) Class.forName(className);
      }
      catch( ClassNotFoundException e )
      {
         throw new DAOInitializationException(e);
      }
   }
   /**
    * @return
    */
   public List<T> read()
   {
      List<T> ts = null;
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         Criteria criteria = session.createCriteria(clazz);
         ts = criteria.list();
         
         transaction.commit();
      }
      catch( Exception e )
      {
         transaction.rollback();
      }
      finally
      {
//         session.close();
      }
      /*
       * End Transaction
       */
      return ts;
   }
   
   public T read(int id)
   {
      T contact = null;
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         contact = (T) session.load(clazz, id);
         
         transaction.commit();
      }
      catch( Exception e )
      {
         transaction.rollback();
      }
      finally
      {
//         session.close();
      }
      /*
       * End Transaction
       */
      return contact;
   }
   
   public void save(T entity) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         Object linkedContact = session.merge(entity);
         session.saveOrUpdate(linkedContact);
         
         transaction.commit();
      }
      catch( Exception e )
      {
         transaction.rollback();
         throw new DBError(e);
      }
      finally
      {
//         session.close();
      }
   }
   
   public void remove(T entity) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         session.delete(entity);
         
         transaction.commit();
      }
      catch( Exception e )
      {
         transaction.rollback();
         throw new DBError(e);
      }
      finally
      {
//         session.close();
      }
   }
}
