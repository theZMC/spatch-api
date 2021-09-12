package app.spatch.controller;

import java.util.ArrayList;
import java.util.List;

import app.spatch.model.Contact;
import app.spatch.service.ContactService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactController {
  private ContactService service = new ContactService();

  private Response buildResponse(List<Contact> contacts){
    Response response = Response
    .status(200)
    .entity(contacts)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Contact contact){
    List<Contact> contacts = new ArrayList<Contact>();
    contacts.add(contact);
    return buildResponse(contacts);
  }

  @GET
  public Response getContacts(){
    return buildResponse(service.getContacts());
  }

  @GET
  @Path("/{id}")
  public Response getContactById(@PathParam("id") Integer id){
    return buildResponse(service.getContactById(id));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createContact(Contact contact){
    return buildResponse(service.createContact(contact));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateContact(Contact contact){
    return buildResponse(service.updateContact(contact));
  }

  @DELETE
  @Path("/{id}")
  public Response deleteContact(@PathParam("id") Integer id){
    return buildResponse(service.deleteContact(id));
  }
}
