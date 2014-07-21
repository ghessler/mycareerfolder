package org.cosug.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BaseActionBean implements ActionBean
{
   private ActionBeanContext context;

   public BaseActionBean()
   {
      super();
   }

   @Override
   public ActionBeanContext getContext()
   {
      return context;
   }

   @Override
   public void setContext(ActionBeanContext context)
   {
      this.context = context;
   }

}