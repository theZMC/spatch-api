package app.spatch.controller;

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

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
public class NoteController {
  private NoteService service = new NoteService();

  @GET
  public List<Note> getNotes(){
    return service.getNotes();
  }

  @GET
  @Path("/{id}")
  public Note getNoteById(@PathParam("id") Integer id){
    return service.getNoteById(id);
  }

  @GET
  @Path("/dispatch/{id}")
  public List<Note> getNotesByDispatchId(@PathParam("id") Integer dispatchId){
    return service.getNotesByDispatchId(dispatchId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Note createNote(Note note){
    return service.createNote(note);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Note updateNote(Note note){
    return service.updateNote(note);
  }
}
