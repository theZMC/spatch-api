package app.spatch.controller;

import java.util.ArrayList;
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
import jakarta.ws.rs.core.Response;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlaceController {
  private PlaceService service = new PlaceService();

  private Response buildResponse(List<Place> places){
    Response response = Response
    .status(200)
    .entity(places)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Place place){
    List<Place> places = new ArrayList<Place>();
    places.add(place);
    return buildResponse(places);
  }

  @GET
  public Response getPlaces(){
    return buildResponse(service.getPlaces());
  }

  @GET
  @Path("/{id}")
  public Response getPlaceById(@PathParam("id") Integer id){
    return buildResponse(service.getPlaceById(id));
  }

  @GET
  @Path("/contact/{id}")
  public Response getPlacesByPrimaryContactId(@PathParam("id") Integer id){
    return buildResponse(service.getPlacesByPrimaryContactId(id));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createPlace(Place place){
    return buildResponse(service.createPlace(place));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updatePlace(Place place){
    return buildResponse(service.updatePlace(place));
  }
}
