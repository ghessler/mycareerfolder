package org.jobtracker;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SpringTeam for questions and corrections
 * 
 */
@Entity
public class Contact
{
   @Id()
   @GeneratedValue
   private int contactId;
   
   private int userId;
   private Date dateCreated;
   private String name;
   private String organization;
   private ContactType contactType;
   private String address1;
   private String address2;
   private String city;
   private String state;
   private String zipCode;
   private String businessPhone;
   private String businessPhone2;
   private String cellPhone;
   private String faxPhone;
   private String businessEmailAddress;
   private String otherEmailAddress;
   /**
    * @return the contactId
    */
   public int getContactId()
   {
      return contactId;
   }
   /**
    * @param contactId the contactId to set
    */
   public void setContactId(int contactId)
   {
      this.contactId = contactId;
   }
   /**
    * @return the userId
    */
   public int getUserId()
   {
      return userId;
   }
   /**
    * @param userId the userId to set
    */
   public void setUserId(int userId)
   {
      this.userId = userId;
   }
   /**
    * @return the dateCreated
    */
   public Date getDateCreated()
   {
      return dateCreated;
   }
   /**
    * @param dateCreated the dateCreated to set
    */
   public void setDateCreated(Date dateCreated)
   {
      this.dateCreated = dateCreated;
   }
   /**
    * @return the name
    */
   public String getName()
   {
      return name;
   }
   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      this.name = name;
   }
   /**
    * @return the organization
    */
   public String getOrganization()
   {
      return organization;
   }
   /**
    * @param organization the organization to set
    */
   public void setOrganization(String organization)
   {
      this.organization = organization;
   }
   /**
    * @return the contactType
    */
   public ContactType getContactType()
   {
      return contactType;
   }
   /**
    * @param contactType the contactType to set
    */
   public void setContactType(ContactType contactType)
   {
      this.contactType = contactType;
   }
   /**
    * @return the address1
    */
   public String getAddress1()
   {
      return address1;
   }
   /**
    * @param address1 the address1 to set
    */
   public void setAddress1(String address1)
   {
      this.address1 = address1;
   }
   /**
    * @return the address2
    */
   public String getAddress2()
   {
      return address2;
   }
   /**
    * @param address2 the address2 to set
    */
   public void setAddress2(String address2)
   {
      this.address2 = address2;
   }
   /**
    * @return the city
    */
   public String getCity()
   {
      return city;
   }
   /**
    * @param city the city to set
    */
   public void setCity(String city)
   {
      this.city = city;
   }
   /**
    * @return the state
    */
   public String getState()
   {
      return state;
   }
   /**
    * @param state the state to set
    */
   public void setState(String state)
   {
      this.state = state;
   }
   /**
    * @return the zipCode
    */
   public String getZipCode()
   {
      return zipCode;
   }
   /**
    * @param zipCode the zipCode to set
    */
   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }
   /**
    * @return the businessPhone
    */
   public String getBusinessPhone()
   {
      return businessPhone;
   }
   /**
    * @param businessPhone the businessPhone to set
    */
   public void setBusinessPhone(String businessPhone)
   {
      this.businessPhone = businessPhone;
   }
   /**
    * @return the businessPhone2
    */
   public String getBusinessPhone2()
   {
      return businessPhone2;
   }
   /**
    * @param businessPhone2 the businessPhone2 to set
    */
   public void setBusinessPhone2(String businessPhone2)
   {
      this.businessPhone2 = businessPhone2;
   }
   /**
    * @return the cellPhone
    */
   public String getCellPhone()
   {
      return cellPhone;
   }
   /**
    * @param cellPhone the cellPhone to set
    */
   public void setCellPhone(String cellPhone)
   {
      this.cellPhone = cellPhone;
   }
   /**
    * @return the faxPhone
    */
   public String getFaxPhone()
   {
      return faxPhone;
   }
   /**
    * @param faxPhone the faxPhone to set
    */
   public void setFaxPhone(String faxPhone)
   {
      this.faxPhone = faxPhone;
   }
   /**
    * @return the businessEmailAddress
    */
   public String getBusinessEmailAddress()
   {
      return businessEmailAddress;
   }
   /**
    * @param businessEmailAddress the businessEmailAddress to set
    */
   public void setBusinessEmailAddress(String businessEmailAddress)
   {
      this.businessEmailAddress = businessEmailAddress;
   }
   /**
    * @return the otherEmailAddress
    */
   public String getOtherEmailAddress()
   {
      return otherEmailAddress;
   }
   /**
    * @param otherEmailAddress the otherEmailAddress to set
    */
   public void setOtherEmailAddress(String otherEmailAddress)
   {
      this.otherEmailAddress = otherEmailAddress;
   }
   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Contact [contactId=" + contactId + ", userId=" + userId + ", dateCreated=" + dateCreated + ", name=" + name
            + ", organization=" + organization + ", contactType=" + contactType + ", address1=" + address1 + ", address2="
            + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", businessPhone=" + businessPhone
            + ", businessPhone2=" + businessPhone2 + ", cellPhone=" + cellPhone + ", faxPhone=" + faxPhone
            + ", businessEmailAddress=" + businessEmailAddress + ", otherEmailAddress=" + otherEmailAddress + "]";
   }
   
   
}
