package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Trip extends DBObject<Trip>{
  @Selectable private Integer id;
  @Selectable @Insertable private LocalDate date;
  @Selectable @Insertable private Integer technicianId;

  public static final String BY_TECHNICIAN = "WHERE technicianId = ?";

  @Override
  public Trip fromResultSet(ResultSet resultSet) throws SQLException {
    return new Trip(
      resultSet.getInt("id"),
      resultSet.getDate("date").toLocalDate(),
      resultSet.getInt("technicianId")
    );
  }

  public Trip(Integer id, LocalDate date, Integer technicianId){
    this.id = id;
    this.date = date;
    this.technicianId = technicianId;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }
  public Integer getTechnicianId() {
    return technicianId;
  }
  public void setTechnicianId(Integer technicianId) {
    this.technicianId = technicianId;
  }
}
