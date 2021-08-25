package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Dispatch extends DBObject<Dispatch> {
  @Selectable private Integer id;
  @Selectable @Insertable private Integer locationId;
  @Selectable @Insertable private Integer tripId;
  @Selectable @Insertable private LocalDateTime scheduledTime;
  @Selectable @Insertable private LocalDateTime startTime;
  @Selectable @Insertable private LocalDateTime endTime;
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
      resultSet.getTimestamp("scheduledTime").toLocalDateTime(),
      resultSet.getTimestamp("startTime").toLocalDateTime(),
      resultSet.getTimestamp("endTime").toLocalDateTime(),
      resultSet.getInt("estTimeOnSite"),
      resultSet.getBoolean("isComplete"),
      Priority.parse(resultSet.getString("priority"))
    );
  }

  public Dispatch(Integer id, Integer locationId, Integer tripId, LocalDateTime scheduledTime, LocalDateTime startTime,
    LocalDateTime endTime, Integer estTimeOnSite, Boolean isComplete, Priority priority){
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
  public LocalDateTime getScheduledTime() {
    return scheduledTime;
  }
  public void setScheduledTime(LocalDateTime scheduledTime) {
    this.scheduledTime = scheduledTime;
  }
  public LocalDateTime getStartTime() {
    return startTime;
  }
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }
  public LocalDateTime getEndTime() {
    return endTime;
  }
  public void setEndTime(LocalDateTime endTime) {
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
