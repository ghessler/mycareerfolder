package test;

import java.util.List;

import org.junit.Test;
import org.springsTeam.dao.jpa.JPAContactDao;
import org.springsTeam.model.Contact;

/**
 * @author SpringTeam for questions and corrections
 *
 */
public class TestJPAContactDao
{

   @Test
   public void testConnect()
   {
      JPAContactDao dao = new JPAContactDao();

      List<Contact> contacts = dao.read();
      
      for(Contact contact : contacts)
      {
         System.out.println(contact);
      }
      
      dao.delete(3);
   }

}
