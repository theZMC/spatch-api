package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Technician;

public class TechnicianService {
  private DAO<Technician> dao = new DAO<Technician>(Technician.class);

  public Technician getTechnicianById(Integer id){
    return dao.selectById(id);
  }

  public List<Technician> getTechnicians(){
    return dao.selectAll();
  }

  public Technician createTechnician(Technician technician){
    return dao.insert(technician);
  }

  public Technician updateTechnician(Technician technician){
    return dao.update(technician);
  }

  public Technician deleteTechnician(Technician technician){
    return dao.delete(technician);
  }
}
