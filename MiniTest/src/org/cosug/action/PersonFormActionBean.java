package org.cosug.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.cosug.dao.DAO;
import org.cosug.dao.DBError;
import org.cosug.model.Person;
import org.cosug.util.OperationCount;

/**
 * @author SpringsTeam
 *
 */
public class PersonFormActionBean extends BaseActionBean
{
   private static final String PERSON_FORM_URL = "/WEB-INF/jsp/personForm.jsp";
   private int id;
   private Person person;
   private DAO<Person> personDAO = new DAO<Person>(Person.class.getName());

   /**
    * @return the id
    */
   public int getId()
   {
      System.out.printf("Operation: %02d PersonFormActionBean.getID()\n", OperationCount.count());

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
      System.out.printf("Operation: %02d PersonFormActionBean.setID()\n", OperationCount.count());
      this.id = id;
      this.person = personDAO.read(id);
   }

   /**
    * @return the persons
    */
   public Person getPerson()
   {
      System.out.printf("Operation: %02d PersonFormActionBean.getPerson(): %s\n", OperationCount.count(), person == null ? "null":person.toString());
      return person;
   }

   /**
    * @param person
    *           the person to set
    */
   public void setPerson(Person person)
   {
      System.out.printf("Operation: %02d PersonFormActionBean.setPerson()\n", OperationCount.count());
      this.person = person;
   }

   public Resolution savePerson() throws DBError
   {
      System.out.printf("Operation: %02d PersonFormActionBean.savePerson()\n", OperationCount.count());
      try
      {
         personDAO.save(person);
         getContext().getMessages().add(new SimpleMessage("{0} has been saved.", person.getName()));
      }
      catch( DBError e )
      {
         e.printStackTrace();
      }
      return new RedirectResolution(PersonListActionBean.class);
   }

   public Resolution createPerson()
   {
      person = new Person();
      System.out.printf("Operation: %02d PersonFormActionBean.createPerson()\n", OperationCount.count());
      return new ForwardResolution(PERSON_FORM_URL);
   }

   public Resolution editPerson()
   {
      System.out.printf("Operation: %02d PersonFormActionBean.editPerson()\n", OperationCount.count());
      return new ForwardResolution(PERSON_FORM_URL);
   }

   public Resolution deletePerson() throws DBError
   {
      System.out.printf("Operation: %02d PersonFormActionBean.deletePerson()\n", OperationCount.count());
      personDAO.remove(person);
      return new RedirectResolution(PersonListActionBean.class);
   }

   public Resolution cancel() throws DBError
   {
      System.out.printf("Operation: %02d PersonFormActionBean.cancel()\n", OperationCount.count());
      return new RedirectResolution(PersonListActionBean.class);
   }
}
