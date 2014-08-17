package org.cosug.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.cosug.dao.DAO;
import org.cosug.model.Document;

public class DocumentListActionBean extends BaseActionBean
{
   private static final String DOCUMENT_LIST_URL = "/WEB-INF/jsp/documentList.jsp";
   private DAO<Document> documentDAO = new DAO<>(Document.class.getName());
   /**
    * @return the persons
    */
   public List<Document> getDocuments()
   {
      return documentDAO.read();
   }

   public Resolution showDocuments()
   {
      return new ForwardResolution(DOCUMENT_LIST_URL);
   }
}
