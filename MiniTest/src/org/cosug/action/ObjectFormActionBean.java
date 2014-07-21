package org.cosug.action;

import javax.persistence.Entity;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.cosug.dao.DAO;
import org.cosug.dao.DBError;
import org.cosug.util.EntityWrapper;
import org.cosug.util.OperationCount;

/**
 * @author SpringsTeam
 *
 */
public class ObjectFormActionBean<T extends EntityWrapper> extends BaseActionBean
{
   private static final String ENTITY_FORM_URL = "/WEB-INF/jsp/objectForm.jsp";
   private static final String ENTITY_LIST_URL = "/WEB-INF/jsp/objectList.jsp";
   private int id;
   private T entity;
   private DAO<T> objectDAO = new DAO<T>();

   /**
    * @return the id
    */
   public int getId()
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.getID()\n", OperationCount.count());

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
      System.out.printf("Operation: %02d ObjectFormActionBean.setID()\n", OperationCount.count());
      this.id = id;
      this.entity = objectDAO.read(id);
   }

   /**
    * @return the persons
    */
   public T getEntity()
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.getEntity(): %s\n", OperationCount.count(), entity == null ? "null":entity.toString());
      return entity;
   }

   /**
    * @param person
    *           the person to set
    */
   public void setEntity(T entity)
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.setEntity()\n", OperationCount.count());
      this.entity = entity;
   }

   public Resolution saveEntity() throws DBError
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.saveEntity()\n", OperationCount.count());
      try
      {
         objectDAO.save(entity);
         getContext().getMessages().add(new SimpleMessage("{0} has been saved.", entity));
      }
      catch( DBError e )
      {
         e.printStackTrace();
      }
      return new RedirectResolution(ENTITY_LIST_URL);
   }

   public Resolution createEntity()
   {
      /*
       * Create entity somehow
       */
      System.out.printf("Operation: %02d ObjectFormActionBean.createEntity()\n", OperationCount.count());
      return new ForwardResolution(ENTITY_FORM_URL);
   }

   public Resolution editEntity()
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.editEntity()\n", OperationCount.count());
      return new ForwardResolution(ENTITY_FORM_URL);
   }

   public Resolution deleteEntity() throws DBError
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.deleteEntity()\n", OperationCount.count());
      objectDAO.remove(entity);
      return new RedirectResolution(ENTITY_LIST_URL);
   }

   public Resolution cancel() throws DBError
   {
      System.out.printf("Operation: %02d ObjectFormActionBean.cancel()\n", OperationCount.count());
      return new RedirectResolution(ENTITY_LIST_URL);
   }
}
