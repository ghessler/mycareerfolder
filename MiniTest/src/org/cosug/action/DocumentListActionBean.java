package org.cosug.action;

import java.util.List;

import org.cosug.dao.DocumentDAO;
import org.cosug.model.Document;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class DocumentListActionBean extends BaseActionBean
{
   private static final String DOCUMENT_LIST_URL = "/WEB-INF/jsp/documentList.jsp";
   
   /**
    * @return the persons
    */
   public List<Document> getDocuments()
   {
      return DocumentDAO.read();
   }

   public Resolution showDocuments()
   {
      return new ForwardResolution(DOCUMENT_LIST_URL);
   }
}
