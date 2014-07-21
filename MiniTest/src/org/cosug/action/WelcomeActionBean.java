package org.cosug.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;


/**
 * @author SpringsTeam
 *
 */
public class WelcomeActionBean extends BaseActionBean
{
   private static final String WELCOME_URL = "/WEB-INF/jsp/welcome.jsp";

   @DefaultHandler
   public Resolution welcome()
   {
      return new ForwardResolution(WELCOME_URL);
   }
}
