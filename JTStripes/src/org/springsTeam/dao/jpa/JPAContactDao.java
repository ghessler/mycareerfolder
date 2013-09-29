package org.springsTeam.dao.jpa;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springsTeam.dao.ContactDao;
import org.springsTeam.model.Contact;


/**
 * @author SpringTeam for questions and corrections
 *
 */
public class JPAContactDao implements ContactDao
{
   private Session session = JobTrackerPersistenceManager.getFactory().openSession();
   private static JPAContactDao instance = new JPAContactDao();
   
   /**
    * @return the instance
    */
   public static JPAContactDao getInstance()
   {
      return instance;
   }

   @Override
   public List<Contact> read()
   {
      Criteria criteria = session.createCriteria(Contact.class);
      criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

      return criteria.list();
   }

   @Override
   public Contact read(Integer id)
   {
      session.beginTransaction();
      Contact contact = (Contact) session.load(Contact.class, id);
      session.getTransaction().commit();
      
      return contact;
   }

   @Override
   public void save(Contact contact)
   {
      session.beginTransaction();
      session.save(contact);
      session.getTransaction().commit();
   }

   @Override
   public void delete(Integer id)
   {
      session.beginTransaction();
      Contact contact = (Contact) session.load(Contact.class, id);
      session.delete(contact);
      session.getTransaction().commit();
   }

}
