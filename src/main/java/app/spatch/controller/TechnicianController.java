package app.spatch.controller;

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

@Path("/technicians")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicianController {
  private TechnicianService service = new TechnicianService();

  @GET
  public List<Technician> getTechnicians(){
    return service.getTechnicians();
  }

  @GET
  @Path("/{id}")
  public Technician getTechnicianById(@PathParam("id") Integer id){
    return service.getTechnicianById(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Technician createTechnician(Technician technician){
    return service.createTechnician(technician);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Technician updateTechnician(Technician technician){
    return service.updateTechnician(technician);
  }
}
