package app.spatch.controller;

import java.util.List;

import app.spatch.model.Contact;
import app.spatch.service.ContactService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactController {
  private ContactService service = new ContactService();

  @GET
  public List<Contact> getContacts(){
    return service.getContacts();
  }

  @GET
  @Path("/{id}")
  public List<Contact> getContactById(@PathParam("id") Integer id){
    return service.getContactById(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Contact createContact(Contact contact){
    return service.createContact(contact);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Contact updateContact(Contact contact){
    return service.updateContact(contact);
  }
}
