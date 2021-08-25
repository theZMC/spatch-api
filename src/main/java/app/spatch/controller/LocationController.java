package app.spatch.controller;

import java.util.List;

import app.spatch.model.Location;
import app.spatch.service.LocationService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {
  private LocationService service = new LocationService();

  @GET
  public List<Location> getLocations(){
    return service.getLocations();
  }

  @GET
  @Path("/{id}")
  public Location getLocationById(@PathParam("id") Integer id){
    return service.getLocationById(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Location createLocation(Location location){
    return service.createLocation(location);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Location updateLocation(Location location){
    return service.updateLocation(location);
  }
}
