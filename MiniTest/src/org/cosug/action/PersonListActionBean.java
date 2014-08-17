package org.cosug.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.cosug.dao.DAO;
import org.cosug.model.Person;

public class PersonListActionBean extends BaseActionBean
{
   private static final String PERSON_LIST_URL = "/WEB-INF/jsp/personList.jsp";
   private static final DAO<Person> personDAO = new DAO<Person>(Person.class.getName());
   /**
    * @return the persons
    */
   public List<Person> getPersons()
   {
      return personDAO.read();
   }

   public Resolution showNames()
   {
      return new ForwardResolution(PERSON_LIST_URL);
   }
}
