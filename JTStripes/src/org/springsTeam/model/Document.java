package org.springsTeam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author SpringsTeam
 *
 */
@Entity
public class Document
{
   private Integer id;
   private String title;
   private String fileType;
   private String fileName;
   private String contents;
   private Set<Contact> contacts = new HashSet<>();
   
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
    * @return the fileType
    */
   public String getFileType()
   {
      return fileType;
   }
   /**
    * @param fileType the fileType to set
    */
   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }
   /**
    * @return the fileName
    */
   public String getFileName()
   {
      return fileName;
   }
   /**
    * @param fileName the fileName to set
    */
   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }
   /**
    * @return the context
    */
   public String getContext()
   {
      return contents;
   }
   /**
    * @param contents the context to set
    */
   public void setContext(String contents)
   {
      this.contents = contents;
   }
   
   /**
    * @return the contacts
    */
   @ManyToMany(mappedBy = "documents")
   public Set<Contact> getContacts()
   {
      return contacts;
   }
   /**
    * @param contacts the contacts to set
    */
   public void setContacts(Set<Contact> contacts)
   {
      this.contacts = contacts;
   }
   @Override
   public String toString()
   {
      return "Document [id=" + id + ", title=" + title + ", fileType=" + fileType + ", fileName=" + fileName + ", contents="
            + contents + ", contacts=" + contacts + "]";
   }
   /**
    * @param contact
    */
   public void addContact(Contact contact)
   {
      contacts.add(contact);
   }
   /**
    * @param contact
    */
   public void removeContact(Contact contact)
   {
      contacts.remove(contact);
   }
   
   
}
