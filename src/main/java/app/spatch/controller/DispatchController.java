package app.spatch.controller;

import java.util.ArrayList;
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
import jakarta.ws.rs.core.Response;

@Path("/dispatches")
@Produces(MediaType.APPLICATION_JSON)
public class DispatchController {
  private DispatchService service = new DispatchService();

  private Response buildResponse(List<Dispatch> dispatches){
    Response response = Response
    .status(200)
    .entity(dispatches)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Dispatch dispatch){
    List<Dispatch> dispatches = new ArrayList<Dispatch>();
    dispatches.add(dispatch);
    return buildResponse(dispatches);
  }

  @GET
  public Response getDispatches(){
    return buildResponse(service.getDispatches());
  }

  @GET
  @Path("/{id}")
  public Response getDispatchById(@PathParam("id") Integer id){
    return buildResponse(service.getDispatchById(id));
  }

  @GET
  @Path("/trip/{id}")
  public Response getDispatchesByTripId(@PathParam("id") Integer dispatchId){
    return buildResponse(service.getDispatchesByTripId(dispatchId));
  }

  @GET
  @Path("/place/{id}")
  public Response getDispatchesByPlaceId(@PathParam("id") Integer placeId){
    return buildResponse(service.getDispatchesByPlaceId(placeId));
  }

  @GET
  @Path("/completed")
  public Response getCompletedDispatches(){
    return buildResponse(service.getDispatchesByCompletion(true));
  }

  @GET
  @Path("/completed/{isComplete}")
  public Response getCompletedDispatches(@PathParam("isComplete") Boolean isComplete){
    return buildResponse(service.getDispatchesByCompletion(isComplete));
  }

  @GET
  @Path("/incomplete")
  public Response getIncompleteDispatches(){
    return buildResponse(service.getDispatchesByCompletion(false));
  }

  @GET
  @Path("/priority/{priority}")
  public Response getDispatchesByPriority(@PathParam("priority") String priority){
    return buildResponse(service.getDispatchesByPriority(Priority.parse(priority)));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createDispatch(Dispatch dispatch){
    return buildResponse(service.createDispatch(dispatch));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateDispatch(Dispatch dispatch){
    return buildResponse(service.updateDispatch(dispatch));
  }
}
