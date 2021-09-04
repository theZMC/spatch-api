package app.spatch.service;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

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

  public Contact deleteContact(Integer id){
    return dao.deleteRecord(id);
  }

  public Boolean isValidContact(Contact contact){
    Boolean isValid = true;
    if(!EmailValidator.getInstance().isValid(contact.getEmail())){
      isValid = false;
    }
    return isValid;
  }
}
