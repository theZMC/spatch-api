package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Dispatch;
import app.spatch.model.Priority;

public class DispatchService {
  private DAO<Dispatch> dao = new DAO<Dispatch>(Dispatch.class);

  public List<Dispatch> getDispatchById(Integer id){
    return dao.selectById(id);
  }

  public List<Dispatch> getDispatchesByLocationId(Integer locationId){
    return dao.select(Dispatch.BY_LOCATION, locationId);
  }

  public List<Dispatch> getDispatchesByTripId(Integer tripId){
    return dao.select(Dispatch.BY_TRIP, tripId);
  }

  public List<Dispatch> getDispatchesByCompletion(Boolean completion){
    return dao.select(Dispatch.BY_COMPLETION, completion);
  }

  public List<Dispatch> getDispatchesByPriority(Priority priority){
    return dao.select(Dispatch.BY_PRIORITY, priority.toString());
  }

  public List<Dispatch> getDispatches(){
    return dao.selectAll();
  }

  public Dispatch createDispatch(Dispatch dispatch){
    return dao.insertRecord(dispatch);
  }

  public Dispatch updateDispatch(Dispatch dispatch){
    return dao.updateRecord(dispatch);
  }

  public Dispatch deleteDispatch(Integer id){
    return dao.deleteRecord(id);
  }
}
