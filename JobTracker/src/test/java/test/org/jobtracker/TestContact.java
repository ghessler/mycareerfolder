package test.org.jobtracker;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.jobtracker.Contact;
import org.jobtracker.ContactType;
import org.junit.Test;

/**
 * Note: Since we are using MySQL, I added the MySQL JDBC dependency to pom.xml.
 * Add the JDBC Drivers for the Databases you'd like to use to the same file.
 * 
 * @author SpringTeam for questions and corrections
 *
 */
public class TestContact
{
   @Test
   public void test()
   {
      /*
       * Get the current Date.
       */
      Date currentDate = Calendar.getInstance().getTime();
      /*
       * Now create a new Contact and set its properties...
       */
      Contact contact = new Contact();
      contact.setUserId(1);
      contact.setContactType(ContactType.Acquaintance);
      contact.setDateCreated(currentDate);

      contact.setName("John Smith");
      contact.setAddress1("Fire Station");
      contact.setAddress2("Dublin Blvd");
      contact.setCity("Colorado Springs");
      contact.setState("CO");
      contact.setZipCode("80920");
      contact.setBusinessEmailAddress("john@jobtracker.com");
      contact.setBusinessPhone("(719) 123-3662");
      contact.setBusinessPhone2("(303) 331-5100");
      contact.setCellPhone("911");
      contact.setFaxPhone("(719) 325-6699");
      contact.setOrganization("Spring Team");
      /*
       * The entry point into Hibernate is the SessionFactory. Remember hibernate.cfg.xml containing
       * the JDBC properties and such must be in the CLASSPATH.
       */
      SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
      Session session = sessionFactory.openSession();
      
      session.beginTransaction();
      session.save(contact);
      session.getTransaction().commit();
      
      session.close();
   }

}
