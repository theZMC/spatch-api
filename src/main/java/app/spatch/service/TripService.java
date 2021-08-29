package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Trip;

public class TripService {
  private DAO<Trip> dao = new DAO<Trip>(Trip.class);

  public Trip getTripById(Integer id){
    return dao.selectById(id);
  }

  public List<Trip> getTripsByTechnicianId(Integer technicianId){
    return dao.select(Trip.BY_TECHNICIAN, technicianId);
  }

  public List<Trip> getTrips(){
    return dao.selectAll();
  }

  public Trip createTrip(Trip trip){
    return dao.insertRecord(trip);
  }

  public Trip updateTrip(Trip trip){
    return dao.updateRecord(trip);
  }

  public Trip deleteTrip(Trip trip){
    return dao.deleteRecord(trip);
  }

}
