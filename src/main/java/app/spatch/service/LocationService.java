package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Location;

public class LocationService {
  private DAO<Location> dao = new DAO<Location>(Location.class);

  public List<Location> getLocationById(Integer id){
    return dao.selectById(id);
  }

  public List<Location> getLocationsByPrimaryContactId(Integer primaryContactId){
    return dao.select(Location.BY_CONTACT, primaryContactId);
  }

  public List<Location> getLocations(){
    return dao.selectAll();
  }

  public Location createLocation(Location location){
    return dao.insertRecord(location);
  }

  public Location updateLocation(Location location){
    return dao.updateRecord(location);
  }

  public Location deleteLocation(Integer id){
    return dao.deleteRecord(id);
  }
}
