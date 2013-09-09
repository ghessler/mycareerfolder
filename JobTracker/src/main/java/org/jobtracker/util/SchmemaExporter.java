package org.jobtracker.util;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jobtracker.Contact;

/**
 * @author SpringTeam for questions and corrections
 *
 */
public class SchmemaExporter
{
   public static void main(String[] args)
   {
      Configuration config = new Configuration();
      
      Properties properties = new Properties();

      properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test"); 
      properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
      properties.put("hibernate.show_sql", "true");
      config.setProperties(properties);

      config.addAnnotatedClass(Contact.class);

      SchemaExport schemaExport = new SchemaExport(config);
      schemaExport.setDelimiter(";");
      /*
       * Write the DDL (Data Definition Language) file to the sql directory
       */
      schemaExport.setOutputFile("src/main/resources/sql/contactDDL.sql");

      /**Just dump the schema SQLs to the console , but not execute them ***/
      schemaExport.create(true, false);
   }
}
