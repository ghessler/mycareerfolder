package org.springsTeam.dao;

import java.util.List;

import org.springsTeam.model.Contact;
import org.springsTeam.model.Document;

public interface DocumentDao
{
   List<Document> read();
   Document read(Integer id);
   void save(Document document);
   void delete(Integer id);
   
   void addContact(Document document, Contact contact);
   void removeContact(Document document, Contact contact);
}
