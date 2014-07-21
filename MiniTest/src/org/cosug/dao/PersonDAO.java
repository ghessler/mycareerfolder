package org.cosug.dao;

import java.util.List;

import org.cosug.model.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This DAO will provide the CRUD operations for Person objects.
 * C = Create
 * R = Read
 * U = Update
 * D = Delete
 * @author SpringsTeam
 *
 */
public class PersonDAO
{
   private static SessionFactory sessionFactory = SessionFactoryInstance.sessionFactory();
   private static Session session = sessionFactory.openSession();
   /**
    * @return
    */
   public static List<Person> read()
   {
      List<Person> persons = null;
      
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         Criteria criteria = session.createCriteria(Person.class);
         persons = criteria.list();
         
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
      return persons;
   }
   
   public static Person read(int id)
   {
      Person contact = null;
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         contact = (Person) session.load(Person.class, id);
         
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
   
   public static void save(Person person) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         Object linkedContact = session.merge(person);
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
      Person personToDelete = read(id);
      remove(personToDelete);
   }
   
   public static void remove(Person person) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         session.delete(person);
         
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
