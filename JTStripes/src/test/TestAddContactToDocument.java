package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springsTeam.dao.jpa.JPAContactDao;
import org.springsTeam.dao.jpa.JPADocumentDao;
import org.springsTeam.dao.jpa.JTDao;
import org.springsTeam.model.Contact;
import org.springsTeam.model.Document;

public class TestAddContactToDocument
{
//   @Test
   public void testAddContact()
   {
      Document document = new Document();
      document.setFileType("PDF");
      document.setFileName("Blah 2.pdf");
      document.setTitle("Intro to Something");
      document.setContext("My Context");
      
      JPADocumentDao.getInstance().save(document);
      
      Contact contact = new Contact();
      contact.setFirstName("Joe");
      contact.setLastName("Giambi");
      
      JPAContactDao.getInstance().save(contact);
      document.addContact(contact);
      
      
      
      System.out.println(document.toString());
   }
   
   @Test
   public void testAddContact2()
   {
      try
      {
         Document document = JPADocumentDao.getInstance().read(1);
         Contact contact = JPAContactDao.getInstance().read(1);
         
         document.addContact(contact);
         contact.addDocument(document);
         
         JTDao.startTransaction();
         JPADocumentDao.getInstance().save(document);
         JPAContactDao.getInstance().save(contact);
         
         JTDao.commit();
         
         System.out.println(document.toString());
      }
      catch( Exception e )
      {
         System.err.println(e.getMessage());
         throw e;
      }
   }
}
