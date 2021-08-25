package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Note extends DBObject<Note>{
  private Integer id;
  private LocalDateTime time;
  private String message;
  private Integer dispatchId;

  public static final String BY_DISPATCH = "WHERE dispatchId = ?";

  @Override
  public Note fromResultSet(ResultSet resultSet) throws SQLException {
    return new Note(
      resultSet.getInt("id"),
      resultSet.getTimestamp("time").toLocalDateTime(),
      resultSet.getString("message"),
      resultSet.getInt("dispatchId")
    );
  }

  public Note(Integer id, LocalDateTime time, String message, Integer dispatchId){
    this.id = id;
    this.time = time;
    this.message = message;
    this.dispatchId = dispatchId;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public LocalDateTime getTime() {
    return time;
  }
  public void setTime(LocalDateTime time) {
    this.time = time;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Integer getDispatchId() {
    return dispatchId;
  }
  public void setDispatchId(Integer dispatchId) {
    this.dispatchId = dispatchId;
  }
}
