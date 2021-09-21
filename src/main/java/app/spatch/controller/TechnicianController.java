package app.spatch.controller;

import java.util.ArrayList;
import java.util.List;

import app.spatch.model.Technician;
import app.spatch.service.TechnicianService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/technicians")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicianController {
  private TechnicianService service = new TechnicianService();


  private Response buildResponse(List<Technician> technicians){
    Response response = Response
    .status(200)
    .entity(technicians)
    .header("Access-Control-Allow-Origin", "*")
    .build();

    return response;
  }
  private Response buildResponse(Technician technician){
    List<Technician> technicians = new ArrayList<Technician>();
    technicians.add(technician);
    return buildResponse(technicians);
  }

  @GET
  public Response getTechnicians(){
    return buildResponse(service.getTechnicians());
  }

  @GET
  @Path("/{id}")
  public Response getTechnicianById(@PathParam("id") Integer id){
    return buildResponse(service.getTechnicianById(id));
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTechnician(Technician technician){
    return buildResponse(service.createTechnician(technician));
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateTechnician(Technician technician){
    return buildResponse(service.updateTechnician(technician));
  }
}
