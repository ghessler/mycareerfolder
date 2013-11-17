package org.springsTeam.action;


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
public class ContactFormActionBean extends BaseActionBean  // ContactBaseActionBean
{
   private static final String FORM = "/WEB-INF/jsp/contact_form.jsp";
   private Integer contactId;
   private Contact contact;
   private ContactDao contactDao = JPAContactDao.getInstance();

   /**
    * @param contact the contact to set
    */
   public void setContact(Contact contact)
   {
      this.contact = contact;
   }

   /**
    * @return the contactDao
    */
   public ContactDao getContactDao()
   {
      return contactDao;
   }

   /**
    * @return the contactId
    */
   public Integer getContactId()
   {
      return contactId;
   }

   /**
    * @param contactId the contactId to set
    */
   public void setContactId(Integer contactId)
   {
      this.contactId = contactId;
      contact = contactDao.read(contactId);
   }

   /**
    * @return the contact
    */
   public Contact getContact()
   {
      return contact;
   }

   /**
    * @return
    */
   @DefaultHandler
   public Resolution form()
   {
      return new ForwardResolution(FORM);
   }

   /**
    * @return
    */
   public Resolution save()
   {
      Contact contact = getContact();
      getContactDao().save(contact);
      getContext().getMessages().add(new SimpleMessage("{0} has been saved.", contact));

      return new RedirectResolution(ContactListActionBean.class);
   }

   /**
    * @return
    */
   public Resolution cancel()
   {
      getContext().getMessages().add(new SimpleMessage("Action cancelled."));

      return new RedirectResolution(ContactListActionBean.class);
   }
}
