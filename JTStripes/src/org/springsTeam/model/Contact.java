package org.springsTeam.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Contact
{
   private Integer id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private Date birthDate;

   @Id
   @GeneratedValue
   public Integer getId()
   {
      return id;
   }
   public void setId(Integer id)
   {
      this.id = id;
   }
   public String getFirstName()
   {
      return firstName;
   }
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }
   public String getLastName()
   {
      return lastName;
   }
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }
   public String getEmail()
   {
      return email;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }
   public String getPhoneNumber()
   {
      return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }
   public Date getBirthDate()
   {
      return birthDate;
   }
   public void setBirthDate(Date birthDate)
   {
      this.birthDate = birthDate;
   }
   
   public Contact assign(Contact contact)
   {
      this.id = contact.id;
      this.firstName = contact.firstName;
      this.lastName = contact.lastName;
      this.birthDate = contact.birthDate;
      this.email = contact.email;
      this.phoneNumber = contact.phoneNumber;
      
      return this;
   }

   @Override
   public boolean equals(Object obj)
   {
      try
      {
         return id.equals(((Contact) obj).getId());
      }
      catch( Exception exc )
      {
         return false;
      }
   }
   @Override
   public int hashCode()
   {
      return 31 + ((id == null) ? 0 : id.hashCode());
   }
   @Override
   public String toString()
   {
      return String.format("%s %s", firstName, lastName);
   }
}
