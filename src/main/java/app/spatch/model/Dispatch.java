package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Dispatch extends DBObject<Dispatch> {
  @Selectable private Integer id;
  @Selectable @Insertable private Integer placeId;
  @Selectable @Insertable private Integer tripId;
  @Selectable @Insertable private Long scheduledTime;
  @Selectable @Insertable private Long startTime;
  @Selectable @Insertable private Long endTime;
  @Selectable @Insertable private Integer estTimeOnSite;
  @Selectable @Insertable private Boolean isComplete;
  @Selectable @Insertable private Priority priority;

  public final static String BY_PLACE = "WHERE placeId = ?";
  public final static String BY_TRIP = "WHERE tripId = ?";
  public final static String BY_COMPLETION = "WHERE isComplete = ?";
  public final static String BY_PRIORITY = "WHERE priority = ?";
  public final static String UNASSIGNED = "WHERE tripId IS NULL";

  @Override
  public Dispatch fromResultSet(ResultSet resultSet) throws SQLException {
    return new Dispatch(
      resultSet.getInt("id"),
      resultSet.getInt("placeId"),
      resultSet.getInt("tripId"),
      resultSet.getLong("scheduledTime"),
      resultSet.getLong("startTime"),
      resultSet.getLong("endTime"),
      resultSet.getInt("estTimeOnSite"),
      resultSet.getBoolean("isComplete"),
      Priority.parse(resultSet.getString("priority"))
    );
  }

  public Dispatch(Integer id, Integer placeId, Integer tripId, Long scheduledTime, Long startTime,
    Long endTime, Integer estTimeOnSite, Boolean isComplete, Priority priority){
    this.id = id;
    this.placeId = placeId > 0 ? placeId : null;
    this.tripId = tripId > 0 ? tripId : null;
    this.scheduledTime = scheduledTime > 0 ? scheduledTime : null;
    this.startTime = startTime > 0 ? startTime : null;
    this.endTime = endTime > 0 ? endTime : null;
    this.estTimeOnSite = estTimeOnSite > 0 ? estTimeOnSite : null;
    this.isComplete = isComplete;
    this.priority = priority;
  }

  public Dispatch(){
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getPlaceId() {
    return placeId;
  }
  public void setPlaceId(Integer placeId) {
    if(placeId == null){
      this.placeId = null;
      return;
    }
    this.placeId = placeId > 0 ? placeId : null;
  }
  public Integer getTripId() {
    return tripId;
  }
  public void setTripId(Integer tripId) {
    if(tripId == null){
      this.tripId = null;
      return;
    }
    this.tripId = tripId > 0 ? tripId : null;
  }
  public Long getScheduledTime() {
    return scheduledTime;
  }
  public void setScheduledTime(Long scheduledTime) {
    if(scheduledTime == null){
      this.scheduledTime = null;
      return;
    }
    this.scheduledTime = scheduledTime > 0 ? scheduledTime : null;
  }
  public Long getStartTime() {
    return startTime;
  }
  public void setStartTime(Long startTime) {
    if(startTime == null){
      this.startTime = null;
      return;
    }
    this.startTime = startTime > 0 ? startTime : null;
  }
  public Long getEndTime() {
    return endTime;
  }
  public void setEndTime(Long endTime) {
    if(endTime == null){
      this.endTime = null;
      return;
    }
    this.endTime = endTime > 0 ? endTime : null;
  }
  public Integer getEstTimeOnSite() {
    return estTimeOnSite;
  }
  public void setEstTimeOnSite(Integer estTimeOnSite) {
    if(estTimeOnSite == null){
      this.estTimeOnSite = null;
      return;
    }
    this.estTimeOnSite = estTimeOnSite > 0 ? estTimeOnSite : null;
  }
  public Boolean getIsComplete() {
    return isComplete;
  }
  public void setIsComplete(Boolean isComplete) {
    if(isComplete == null){
      isComplete = false;
    }
    this.isComplete = isComplete;
  }
  public Priority getPriority() {
    return priority;
  }
  public void setPriority(Priority priority) {
    this.priority = priority;
  }
  public void setPriority(String priority) {
    this.priority = Priority.parse(priority);
  }
}
