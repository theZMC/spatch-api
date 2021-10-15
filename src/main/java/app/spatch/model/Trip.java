package app.spatch.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Trip extends DBObject<Trip>{
  @Selectable private Integer id;
  @Selectable @Insertable private Date date;
  @Selectable @Insertable private Long endTime;
  @Selectable @Insertable private Long startTime;
  @Selectable @Insertable private Integer technicianId;

  public static final String BY_DATE = "WHERE date = ?";
  public static final String BY_TECHNICIAN = "WHERE technicianId = ?";

  @Override
  public Trip fromResultSet(ResultSet resultSet) throws SQLException {
    return new Trip(
      resultSet.getInt("id"),
      resultSet.getDate("date"),
      resultSet.getLong("endTime"),
      resultSet.getLong("startTime"),
      resultSet.getInt("technicianId")
    );
  }

  public Trip(Integer id, Date date, Long endTime, Long startTime, Integer technicianId){
    this.id = id;
    this.date = date;
    this.endTime = endTime;
    this.startTime = startTime;
    this.technicianId = technicianId;
  }

  public Trip(){
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Long getEndTime() {
    return endTime;
  }
  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }
  public Long getStartTime() {
    return startTime;
  }
  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }
  public Integer getTechnicianId() {
    return technicianId;
  }
  public void setTechnicianId(Integer technicianId) {
    this.technicianId = technicianId;
  }
}
