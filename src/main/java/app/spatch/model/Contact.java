package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Contact extends DBObject<Contact>{
  @Selectable private Integer id;
  @Selectable @Insertable private String firstName;
  @Selectable @Insertable private String lastName;
  @Selectable @Insertable private Long phone;
  @Selectable @Insertable private String email;

  @Override
  public Contact fromResultSet(ResultSet resultSet) throws SQLException {
    return new Contact(
      resultSet.getInt("id"),
      resultSet.getString("firstName"),
      resultSet.getString("lastName"),
      resultSet.getLong("phone"),
      resultSet.getString("email")
    );
  }

  public Contact(Integer id, String firstName, String lastName, Long phone, String email){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
  }

  public Contact(){
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
  public Long getPhone() {
    return phone;
  }
  public void setPhone(Long phone) {
    this.phone = phone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
