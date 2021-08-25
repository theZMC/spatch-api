package app.spatch.controller;

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

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
public class TripController {
  private TripService service = new TripService();

  @GET
  public List<Trip> getTrips(){
    return service.getTrips();
  }

  @GET
  @Path("/{id}")
  public Trip getTrip(@PathParam("id") Integer id){
    return service.getTripById(id);
  }

  @GET
  @Path("/technician/{id}")
  public List<Trip> getTripsByTechnicianId(@PathParam("id") Integer technicianId){
    return service.getTripsByTechnicianId(technicianId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Trip createTrip(Trip trip){
    return service.createTrip(trip);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Trip updateTrip(Trip trip){
    return service.updateTrip(trip);
  }
}
