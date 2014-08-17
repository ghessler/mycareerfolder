package org.cosug.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.cosug.dao.DBError;
import org.cosug.dao.TaskDAO;
import org.cosug.model.Task;
import org.cosug.util.OperationCount;

/**
 * @author SpringsTeam
 *
 */
public class TaskFormActionBean extends BaseActionBean
{
   private static final String TASK_FORM_URL = "/WEB-INF/jsp/taskForm.jsp";
   private int id;
   private Task task;

   /**
    * @return the id
    */
   public int getId()
   {
      System.out.printf("Operation: %02d TaskFormActionBean.getID()\n", OperationCount.count());

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
      System.out.printf("Operation: %02d TaskFormActionBean.setID()\n", OperationCount.count());
      this.id = id;
      this.task = TaskDAO.read(id);
   }

   /**
    * @return the tasks
    */
   public Task getTask()
   {
      System.out.printf("Operation: %02d TaskFormActionBean.getTask(): %s\n", OperationCount.count(), task == null ? "null":task.toString());
      return task;
   }

   /**
    * @param task
    *           the task to set
    */
   public void setTask(Task task)
   {
      System.out.printf("Operation: %02d TaskFormActionBean.setTask()\n", OperationCount.count());
      this.task = task;
   }

   public Resolution saveTask() throws DBError
   {
      System.out.printf("Operation: %02d TaskFormActionBean.saveTask()\n", OperationCount.count());
      try
      {
         TaskDAO.save(task);
         getContext().getMessages().add(new SimpleMessage("{0} has been saved.", task.getTitle()));
      }
      catch( DBError e )
      {
         e.printStackTrace();
      }
      return new RedirectResolution(TaskListActionBean.class);
   }

   public Resolution createTask()
   {
      task = new Task();
      System.out.printf("Operation: %02d TaskFormActionBean.createTask()\n", OperationCount.count());
      return new ForwardResolution(TASK_FORM_URL);
   }

   public Resolution editTask()
   {
      System.out.printf("Operation: %02d TaskFormActionBean.editTask()\n", OperationCount.count());
      return new ForwardResolution(TASK_FORM_URL);
   }

   public Resolution deleteTask() throws DBError
   {
      System.out.printf("Operation: %02d TaskFormActionBean.deleteTask()\n", OperationCount.count());
      TaskDAO.remove(task);
      return new RedirectResolution(TaskListActionBean.class);
   }

   public Resolution cancel() throws DBError
   {
      System.out.printf("Operation: %02d TaskFormActionBean.cancel()\n", OperationCount.count());
      return new RedirectResolution(TaskListActionBean.class);
   }
}
