package org.springsTeam.action;

import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import org.springsTeam.dao.ContactDao;
import org.springsTeam.dao.jpa.JPAContactDao;
import org.springsTeam.model.Contact;

/**
 * @author SpringsTeam
 *
 */
public abstract class ContactBaseActionBean extends BaseActionBean
{
   private Integer contactId;
//   @ValidateNestedProperties({
//      @Validate(field="id", required=true)
//      })
   private Contact contact;
   private ContactDao contactDao = JPAContactDao.getInstance();
   
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
   }
   
   /**
    * @param contactId the contactId to set
    */
   public void setId(Integer contactId)
   {
      this.contactId = contactId;
   }
   
   /**
    * @return the contact
    */
   public Contact getContact()
   {
      if( contactId != null )
      {
         return contactDao.read(contactId);
      }
      
      return contact;
   }
   
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
}
