package org.cosug.action;

import java.util.List;

import org.cosug.dao.TaskDAO;
import org.cosug.model.Task;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class TaskListActionBean extends BaseActionBean
{
   private static final String TASK_LIST_URL = "/WEB-INF/jsp/taskList.jsp";
   
   /**
    * @return the tasks
    */
   public List<Task> getTasks()
   {
      return TaskDAO.read();
   }

   public Resolution showTasks()
   {
      return new ForwardResolution(TASK_LIST_URL);
   }
}
