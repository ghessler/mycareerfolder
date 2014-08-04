package org.cosug.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SpringsTeam
 *
 */
@Entity
public class Document
{
   private int id;
   private String title;
   private String fileType;
   private String fileName;
   private String description;

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
      return "Document [id=" + id + ", title=" + title + ", fileType=" + fileType + ", fileName=" + fileName + ", description="
            + description + "]";
   }
   
}
