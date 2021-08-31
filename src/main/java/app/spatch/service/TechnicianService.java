package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Technician;

public class TechnicianService {
  private DAO<Technician> dao = new DAO<Technician>(Technician.class);

  public List<Technician> getTechnicianById(Integer id){
    return dao.selectById(id);
  }

  public List<Technician> getTechnicians(){
    return dao.selectAll();
  }

  public Technician createTechnician(Technician technician){
    return dao.insertRecord(technician);
  }

  public Technician updateTechnician(Technician technician){
    return dao.updateRecord(technician);
  }

  public Technician deleteTechnician(Integer id){
    return dao.deleteRecord(id);
  }
}
