package org.cosug.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SpringsTeam
 *
 */
@Entity
public class Task
{
   private int id;
   private String title;
   private String contact;
   private String startDate;
   private String endDate;
   private String description;

   public String getStartDate() {
	   return startDate;
   }

   public void setStartDate(String startDate) {
	   this.startDate = startDate;
   }

   public String getEndDate() {
	   return endDate;
   }

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


   public String getContact() {
	   return contact;
   }

   public void setContact(String contact) {
	   this.contact = contact;
   }

   /**
    * @return the id
    */
   @Id
   @GeneratedValue
   public int getId()
   {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(int id)
   {
      this.id = id;
   }

   /**
    * @return the title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * @param title the title to set
    */
   public void setTitle(String title)
   {
      this.title = title;
   }

    /**
    * @return the description
    */
   public String getDescription()
   {
      return description;
   }

   /**
    * @param description the description to set
    */
   public void setDescription(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return "Document [id=" + id + ", title=" + title + ", contact=" + contact +", description=" + description +
    		  ", startDate=" + startDate + ", endDate="+ endDate + "]";
   }
   
}
