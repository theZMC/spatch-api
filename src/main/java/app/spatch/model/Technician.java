package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Technician extends DBObject<Technician>{
  @Selectable private Integer id;
  @Selectable @Insertable private String firstName;
  @Selectable @Insertable private String lastName;
  @Selectable @Insertable private String avatar;
  @Selectable @Insertable private Integer phone;
  @Selectable @Insertable private String email;

  @Override
  public Technician fromResultSet(ResultSet resultSet) throws SQLException {
    return new Technician(
      resultSet.getInt("id"),
      resultSet.getString("firstName"),
      resultSet.getString("lastName"),
      resultSet.getString("avatar"),
      resultSet.getInt("phone"),
      resultSet.getString("email")
    );
  }

  public Technician(Integer id, String firstName, String lastName, String avatar, Integer phone, String email){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatar = avatar;
    this.phone = phone;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getAvatar() {
    return avatar;
  }
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
  public Integer getPhone() {
    return phone;
  }
  public void setPhone(Integer phone) {
    this.phone = phone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
