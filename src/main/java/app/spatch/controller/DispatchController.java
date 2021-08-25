package app.spatch.controller;

import java.util.List;

import app.spatch.model.Dispatch;
import app.spatch.model.Priority;
import app.spatch.service.DispatchService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/dispatches")
@Produces(MediaType.APPLICATION_JSON)
public class DispatchController {
  private DispatchService service = new DispatchService();

  @GET
  public List<Dispatch> getDispatches(){
    return service.getDispatches();
  }

  @GET
  @Path("/{id}")
  public Dispatch getDispatchById(@PathParam("id") Integer id){
    return service.getDispatchById(id);
  }

  @GET
  @Path("/trip/{id}")
  public List<Dispatch> getDispatchesByTripId(@PathParam("id") Integer dispatchId){
    return service.getDispatchesByTripId(dispatchId);
  }

  @GET
  @Path("/location/{id}")
  public List<Dispatch> getDispatchesByLocationId(@PathParam("id") Integer locationId){
    return service.getDispatchesByLocationId(locationId);
  }

  @GET
  @Path("/completed/{isComplete}")
  public List<Dispatch> getCompletedDispatches(@PathParam("isComplete") Boolean isComplete){
    if(isComplete != null){
      return service.getDispatchesByCompletion(isComplete);
    }
    return service.getDispatchesByCompletion(true);
  }

  @GET
  @Path("/incomplete")
  public List<Dispatch> getIncompleteDispatches(){
    return service.getDispatchesByCompletion(false);
  }

  @GET
  @Path("/priority/{priority}")
  public List<Dispatch> getDispatchesByPriority(@PathParam("priority") String priority){
    return service.getDispatchesByPriority(Priority.parse(priority));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Dispatch createDispatch(Dispatch dispatch){
    return service.createDispatch(dispatch);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Dispatch updateDispatch(Dispatch dispatch){
    return service.updateDispatch(dispatch);
  }
}
