package org.springsTeam.dao.jpa;


import java.util.List;

import org.hibernate.Criteria;
import org.springsTeam.dao.ContactDao;
import org.springsTeam.model.Contact;


/**
 * @author SpringTeam for questions and corrections
 *
 */
public class JPAContactDao extends JTDao implements ContactDao
{
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
      if( contact.getId() == null )
      {
         session.save(contact);
      }
      else
      {
         Object contactN = session.merge(contact);
         session.saveOrUpdate(contactN);
      }
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
