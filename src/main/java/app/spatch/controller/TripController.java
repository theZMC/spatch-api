package app.spatch.controller;

import java.util.ArrayList;
import java.util.List;

import app.spatch.model.Trip;
import app.spatch.service.TripService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
public class TripController {
  private TripService service = new TripService();

  private Response buildResponse(List<Trip> trips){
    Response response = Response
    .status(200)
    .entity(trips)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Trip trip){
    List<Trip> trips = new ArrayList<Trip>();
    trips.add(trip);
    return buildResponse(trips);
  }

  @GET
  public Response getTrips(){
    return buildResponse(service.getTrips());
  }

  @GET
  @Path("/{id}")
  public Response getTrip(@PathParam("id") Integer id){
    return buildResponse(service.getTripById(id));
  }

  @GET
  @Path("/technician/{id}")
  public Response getTripsByTechnicianId(@PathParam("id") Integer technicianId){
    return buildResponse(service.getTripsByTechnicianId(technicianId));
  }

  @GET
  @Path("/date/{date}")
  public Response getTripsByDate(@PathParam("date") String date){
    return buildResponse(service.getTripsByDate(date));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTrip(Trip trip){
    return buildResponse(service.createTrip(trip));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateTrip(Trip trip){
    return buildResponse(service.updateTrip(trip));
  }
}
