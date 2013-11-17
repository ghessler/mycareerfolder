package org.springsTeam.dao.jpa;


import java.util.List;

import org.hibernate.Criteria;
import org.springsTeam.dao.DocumentDao;
import org.springsTeam.model.Contact;
import org.springsTeam.model.Document;


/**
 * @author SpringTeam for questions and corrections
 *
 */
public class JPADocumentDao extends JTDao implements DocumentDao
{
   private static JPADocumentDao instance = new JPADocumentDao();
   
   /**
    * @return the instance
    */
   public static JPADocumentDao getInstance()
   {
      return instance;
   }

   @Override
   public List<Document> read()
   {
      Criteria criteria = session.createCriteria(Document.class);
      criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

      return criteria.list();
   }

   @Override
   public Document read(Integer id)
   {
      session.beginTransaction();
      Document document = (Document) session.load(Document.class, id);
      session.getTransaction().commit();
      
      return document;
   }

   @Override
   public void save(Document document)
   {
//      session.beginTransaction();
      session.save(document);
//      session.getTransaction().commit();
   }

   @Override
   public void delete(Integer id)
   {
      session.beginTransaction();
      Document document = (Document) session.load(Document.class, id);
      session.delete(document);
//      session.getTransaction().commit();
   }

   @Override
   public void addContact(Document document, Contact contact)
   {
      document = read(document.getId());
      document.getContacts().add(contact);
      document.addContact(contact);
   }

   @Override
   public void removeContact(Document document, Contact contact)
   {
      document = read(document.getId());
      document.getContacts().add(contact);
      document.removeContact(contact);
   }
}
