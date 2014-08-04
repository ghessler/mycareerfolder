package org.cosug.dao;

import java.util.List;

import org.cosug.model.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * This DAO will provide the CRUD operations for Document objects.
 * C = Create
 * R = Read
 * U = Update
 * D = Delete
 * @author SpringsTeam
 *
 */
public class DocumentDAO
{
   private static SessionFactory sessionFactory = SessionFactoryInstance.sessionFactory();
   private static Session session = sessionFactory.openSession();
   /**
    * @return
    */
   public static List<Document> read()
   {
      List<Document> persons = null;
      
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         Criteria criteria = session.createCriteria(Document.class);
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
   
   public static Document read(int id)
   {
      Document contact = null;
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         contact = (Document) session.load(Document.class, id);
         
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
   
   public static void save(Document document) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         Object linkedContact = session.merge(document);
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
      Document documentToDelete = read(id);
      remove(documentToDelete);
   }
   
   public static void remove(Document document) throws DBError
   {
//      Session session = null;
      Transaction transaction = null;
      try
      {
//         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         
         session.delete(document);
         
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
