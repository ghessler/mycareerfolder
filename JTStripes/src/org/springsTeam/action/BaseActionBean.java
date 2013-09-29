package org.springsTeam.action;


import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * @author SpringsTeam
 *
 */
public abstract class BaseActionBean implements ActionBean
{
   private ActionBeanContext actionBeanContext;

   public ActionBeanContext getContext()
   {
      return actionBeanContext;
   }
   public void setContext(ActionBeanContext actionBeanContext)
   {
      this.actionBeanContext = actionBeanContext;
   }
}