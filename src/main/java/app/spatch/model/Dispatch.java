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
    this.placeId = placeId;
    this.tripId = tripId;
    this.scheduledTime = scheduledTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.estTimeOnSite = estTimeOnSite;
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
  public void setPlaceId(
      Integer placeId) {
    this.placeId = placeId;
  }
  public Integer getTripId() {
    return tripId;
  }
  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }
  public Long getScheduledTime() {
    return scheduledTime;
  }
  public void setScheduledTime(Long scheduledTime) {
    this.scheduledTime = scheduledTime;
  }
  public Long getStartTime() {
    return startTime;
  }
  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }
  public Long getEndTime() {
    return endTime;
  }
  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }
  public Integer getEstTimeOnSite() {
    return estTimeOnSite;
  }
  public void setEstTimeOnSite(Integer estTimeOnSite) {
    this.estTimeOnSite = estTimeOnSite;
  }
  public Boolean getIsComplete() {
    return isComplete;
  }
  public void setIsComplete(Boolean isComplete) {
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
