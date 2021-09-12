package app.spatch.controller;

import java.util.ArrayList;
import java.util.List;

import app.spatch.model.Note;
import app.spatch.service.NoteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
public class NoteController {
  private NoteService service = new NoteService();

  private Response buildResponse(List<Note> notes){
    Response response = Response
    .status(200)
    .entity(notes)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Note note){
    List<Note> notes = new ArrayList<Note>();
    notes.add(note);
    return buildResponse(notes);
  }

  @GET
  public Response getNotes(){
    return buildResponse(service.getNotes());
  }

  @GET
  @Path("/{id}")
  public Response getNoteById(@PathParam("id") Integer id){
    return buildResponse(service.getNoteById(id));
  }

  @GET
  @Path("/dispatch/{id}")
  public Response getNotesByDispatchId(@PathParam("id") Integer dispatchId){
    return buildResponse(service.getNotesByDispatchId(dispatchId));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createNote(Note note){
    return buildResponse(service.createNote(note));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateNote(Note note){
    return buildResponse(service.updateNote(note));
  }
}
