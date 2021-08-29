package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Dispatch extends DBObject<Dispatch> {
  @Selectable private Integer id;
  @Selectable @Insertable private Integer locationId;
  @Selectable @Insertable private Integer tripId;
  @Selectable @Insertable private Timestamp scheduledTime;
  @Selectable @Insertable private Timestamp startTime;
  @Selectable @Insertable private Timestamp endTime;
  @Selectable @Insertable private Integer estTimeOnSite;
  @Selectable @Insertable private Boolean isComplete;
  @Selectable @Insertable private Priority priority;

  public final static String BY_LOCATION = "WHERE locationId = ?";
  public final static String BY_TRIP = "WHERE tripId = ?";
  public final static String BY_COMPLETION = "WHERE isComplete = ?";
  public final static String BY_PRIORITY = "WHERE priority = ?";

  @Override
  public Dispatch fromResultSet(ResultSet resultSet) throws SQLException {
    return new Dispatch(
      resultSet.getInt("id"),
      resultSet.getInt("locationId"),
      resultSet.getInt("tripId"),
      resultSet.getTimestamp("scheduledTime"),
      resultSet.getTimestamp("startTime"),
      resultSet.getTimestamp("endTime"),
      resultSet.getInt("estTimeOnSite"),
      resultSet.getBoolean("isComplete"),
      Priority.parse(resultSet.getString("priority"))
    );
  }

  public Dispatch(Integer id, Integer locationId, Integer tripId, Timestamp scheduledTime, Timestamp startTime,
    Timestamp endTime, Integer estTimeOnSite, Boolean isComplete, Priority priority){
    this.id = id;
    this.locationId = locationId;
    this.tripId = tripId;
    this.scheduledTime = scheduledTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.estTimeOnSite = estTimeOnSite;
    this.isComplete = isComplete;
    this.priority = priority;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getLocationId() {
    return locationId;
  }
  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }
  public Integer getTripId() {
    return tripId;
  }
  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }
  public Timestamp getScheduledTime() {
    return scheduledTime;
  }
  public void setScheduledTime(Timestamp scheduledTime) {
    this.scheduledTime = scheduledTime;
  }
  public Timestamp getStartTime() {
    return startTime;
  }
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }
  public Timestamp getEndTime() {
    return endTime;
  }
  public void setEndTime(Timestamp endTime) {
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
}
