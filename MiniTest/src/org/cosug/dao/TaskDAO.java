package org.cosug.dao;

import java.util.List;

import org.cosug.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This DAO will provide the CRUD operations for Task objects.
 * C = Create
 * R = Read
 * U = Update
 * D = Delete
 * @author SpringsTeam
 *
 */
public class TaskDAO
{
   private static SessionFactory sessionFactory = SessionFactoryInstance.sessionFactory();
   private static Session session = sessionFactory.openSession();
   /**
    * @return
    */
   public static List<Task> read()
   {
      List<Task> tasks = null;
      
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         Criteria criteria = session.createCriteria(Task.class);
         tasks = criteria.list();
         
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
      return tasks;
   }
   
   public static Task read(int id)
   {
      Task task = null;
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         task = (Task) session.load(Task.class, id);
         
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
      return task;
   }
   
   public static void save(Task task) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         Object linkedContact = session.merge(task);
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
   
   public static void remove(int id) throws DBError
   {
      Task taskToDelete = read(id);
      remove(taskToDelete);
   }
   
   public static void remove(Task task) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         session.delete(task);
         
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
