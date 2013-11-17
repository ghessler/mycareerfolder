package org.springsTeam.action;


import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.springsTeam.dao.ContactDao;
import org.springsTeam.dao.jpa.JPAContactDao;
import org.springsTeam.model.Contact;

/**
 * @author SpringsTeam
 *
 */
public class ContactListActionBean extends BaseActionBean
{
   private static final String LIST = "/WEB-INF/jsp/contact_list.jsp";
   private static final String VIEW = "/WEB-INF/jsp/contact_view.jsp";

   private Integer contactId;
   private Contact contact;
   private ContactDao contactDao = JPAContactDao.getInstance(); 

   /**
    * @return
    */
   @DefaultHandler
   public Resolution list()
   {
      return new ForwardResolution(LIST);
   }

   /**
    * @return
    */
   public Resolution view()
   {
      return new ForwardResolution(VIEW);
   }

   /**
    * @return
    */
   public Resolution delete()
   {
      Contact deleted = contactDao.read(contactId);
      contactDao.delete(contactId);
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deleted));
      
      return new RedirectResolution(getClass());
   }


   public void setContactId(Integer id)
   {
      contactId = id;
   }

   public Contact getContact()
   {
      return contactDao.read(contactId);
   }

   /**
    * @param contact the contact to set
    */
   public void setContact(Contact contact)
   {
      this.contact = contact;
   }

   public List<Contact> getContacts()
   {
      return contactDao.read();
   }
}