package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Note extends DBObject<Note>{
  @Selectable private Integer id;
  @Selectable @Insertable private Integer dispatchId;
  @Selectable @Insertable private Timestamp time;
  @Selectable @Insertable private String message;

  public static final String BY_DISPATCH = "WHERE dispatchId = ?";

  @Override
  public Note fromResultSet(ResultSet resultSet) throws SQLException {
    return new Note(
      resultSet.getInt("id"),
      resultSet.getTimestamp("time"),
      resultSet.getString("message"),
      resultSet.getInt("dispatchId")
    );
  }

  public Note(Integer id, Timestamp time, String message, Integer dispatchId){
    this.id = id;
    this.time = time;
    this.message = message;
    this.dispatchId = dispatchId;
  }

  public Note(){
  }

  // public Note(){
  //   this.id = null;
  //   this.time = null;
  //   this.message = null;
  //   this.dispatchId = null;
  // }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getDispatchId() {
    return dispatchId;
  }
  public void setDispatchId(Integer dispatchId) {
    this.dispatchId = dispatchId;
  }
  public Timestamp getTime() {
    return time;
  }
  public void setTime(Timestamp time) {
    this.time = time;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
}
