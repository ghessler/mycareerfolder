package org.cosug.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.cosug.util.OperationCount;

/**
 * @author SpringsTeam
 *
 */
@Entity
public class Person
{
   private int id;
   private String name;
   private int age;

   /**
    * @return the id
    */
   @Id
   @GeneratedValue
   public int getId()
   {
      System.out.printf("Operation: %02d Person.getID()\n", OperationCount.count());
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(int id)
   {
      System.out.printf("Operation: %02d Person.setID()\n", OperationCount.count());
      this.id = id;
   }

   /**
    * @return the name
    */
   public String getName()
   {
      System.out.printf("Operation: %02d Person.getName()\n", OperationCount.count());
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      System.out.printf("Operation: %02d Person.setName()\n", OperationCount.count());
      this.name = name;
   }

   /**
    * @return the age
    */
   public int getAge()
   {
      return age;
   }

   /**
    * @param age the age to set
    */
   public void setAge(int age)
   {
      this.age = age;
   }

   @Override
   public String toString()
   {
      return "Person [id=" + id + ", name=" + name + "]";
   }
}
