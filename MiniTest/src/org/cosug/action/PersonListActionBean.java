package org.cosug.action;

import java.util.List;

import org.cosug.dao.PersonDAO;
import org.cosug.model.Person;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class PersonListActionBean extends BaseActionBean
{
   private static final String PERSON_LIST_URL = "/WEB-INF/jsp/personList.jsp";
   
   /**
    * @return the persons
    */
   public List<Person> getPersons()
   {
      return PersonDAO.read();
   }

   public Resolution showNames()
   {
      return new ForwardResolution(PERSON_LIST_URL);
   }
}
