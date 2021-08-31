package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Contact;

public class ContactService {
  private DAO<Contact> dao = new DAO<Contact>(Contact.class);

  public List<Contact> getContactById(Integer id){
    return dao.selectById(id);
  }

  public List<Contact> getContacts(){
    return dao.selectAll();
  }

  public Contact createContact(Contact contact){
    return dao.insertRecord(contact);
  }

  public Contact updateContact(Contact contact){
    return dao.updateRecord(contact);
  }

  public Contact deleteContact(Contact contact){
    return dao.deleteRecord(contact);
  }
}
