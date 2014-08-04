package org.cosug.util;


import java.util.Properties;

import org.cosug.model.Document;
import org.cosug.model.Person;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author SpringTeam for questions and corrections
 *
 */
public class SchemaExporter
{
   public static void main(String[] args)
   {
      Configuration config = new Configuration();
      
      Properties properties = new Properties();

      properties.put("hibernate.connection.url", "jdbc:mysql://192.168.56.1:3306/osug"); 
      properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
      properties.put("hibernate.connection.username", "osug");
      properties.put("hibernate.connection.password", "feelfree");
      properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      properties.put("hibernate.show_sql", "true");
      config.setProperties(properties);

      config.addAnnotatedClass(Person.class);
      config.addAnnotatedClass(Document.class);

      SchemaExport schemaExport = new SchemaExport(config);
      schemaExport.setDelimiter(";");
      /*
       * Write the DDL (Data Definition Language) file to the sql directory
       */
      schemaExport.setOutputFile("resource/sql/personDDL.sql");

      /**Just dump the schema SQLs to the console , but not execute them ***/
      schemaExport.create(true, true);
   }
}