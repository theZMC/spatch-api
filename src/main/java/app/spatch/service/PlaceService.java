package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Place;

public class PlaceService {
  private DAO<Place> dao = new DAO<Place>(Place.class);

  public List<Place> getPlaceById(Integer id){
    return dao.selectById(id);
  }

  public List<Place> getPlacesByPrimaryContactId(Integer primaryContactId){
    return dao.select(Place.BY_CONTACT, primaryContactId);
  }

  public List<Place> getPlaces(){
    return dao.selectAll();
  }

  public Place createPlace(Place place){
    return dao.insertRecord(place);
  }

  public Place updatePlace(Place place){
    return dao.updateRecord(place);
  }

  public Place deletePlace(Integer id){
    return dao.deleteRecord(id);
  }
}
