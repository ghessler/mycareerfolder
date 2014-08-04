package org.cosug.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.cosug.dao.DBError;
import org.cosug.dao.DocumentDAO;
import org.cosug.model.Document;
import org.cosug.util.OperationCount;

/**
 * @author SpringsTeam
 *
 */
public class DocumentFormActionBean extends BaseActionBean
{
   private static final String DOCUMENT_FORM_URL = "/WEB-INF/jsp/documentForm.jsp";
   private int id;
   private Document document;

   /**
    * @return the id
    */
   public int getId()
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.getID()\n", OperationCount.count());

      return id;
   }

   /**
    * @param id
    *           the id to set
    */
   public void setId(int id)
   {
      System.out.println("===========================================================================");
      OperationCount.reset();
      System.out.printf("Operation: %02d DocumentFormActionBean.setID()\n", OperationCount.count());
      this.id = id;
      this.document = DocumentDAO.read(id);
   }

   /**
    * @return the documents
    */
   public Document getDocument()
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.getDocument(): %s\n", OperationCount.count(), document == null ? "null":document.toString());
      return document;
   }

   /**
    * @param document
    *           the document to set
    */
   public void setDocument(Document document)
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.setDocument()\n", OperationCount.count());
      this.document = document;
   }

   public Resolution saveDocument() throws DBError
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.saveDocument()\n", OperationCount.count());
      try
      {
         DocumentDAO.save(document);
         getContext().getMessages().add(new SimpleMessage("{0} has been saved.", document.getTitle()));
      }
      catch( DBError e )
      {
         e.printStackTrace();
      }
      return new RedirectResolution(DocumentListActionBean.class);
   }

   public Resolution createDocument()
   {
      document = new Document();
      System.out.printf("Operation: %02d DocumentFormActionBean.createDocument()\n", OperationCount.count());
      return new ForwardResolution(DOCUMENT_FORM_URL);
   }

   public Resolution editDocument()
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.editDocument()\n", OperationCount.count());
      return new ForwardResolution(DOCUMENT_FORM_URL);
   }

   public Resolution deleteDocument() throws DBError
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.deleteDocument()\n", OperationCount.count());
      DocumentDAO.remove(document);
      return new RedirectResolution(DocumentListActionBean.class);
   }

   public Resolution cancel() throws DBError
   {
      System.out.printf("Operation: %02d DocumentFormActionBean.cancel()\n", OperationCount.count());
      return new RedirectResolution(DocumentListActionBean.class);
   }
}
