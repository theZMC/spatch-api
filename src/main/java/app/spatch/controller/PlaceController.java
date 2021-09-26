package app.spatch.controller;

import java.util.List;

import app.spatch.model.Place;
import app.spatch.service.PlaceService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlaceController {
  private PlaceService service = new PlaceService();

  @GET
  public List<Place> getPlaces(){
    return service.getPlaces();
  }

  @GET
  @Path("/{id}")
  public List<Place> getPlaceById(@PathParam("id") Integer id){
    return service.getPlaceById(id);
  }

  @GET
  @Path("/contact/{id}")
  public List<Place> getPlacesByPrimaryContactId(@PathParam("id") Integer id){
    return service.getPlacesByPrimaryContactId(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Place createPlace(Place place){
    return service.createPlace(place);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Place updatePlace(Place place){
    return service.updatePlace(place);
  }
}
