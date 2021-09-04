package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class Place extends DBObject<Place>{
  @Selectable private Integer id;
  @Selectable @Insertable private Integer primaryContactId;
  @Selectable @Insertable private String address;
  @Selectable @Insertable private String gPlaceId;
  @Selectable @Insertable private String displayName;

  public static final String BY_CONTACT = "WHERE primaryContactId = ?";

  @Override
  public Place fromResultSet(ResultSet resultSet) throws SQLException {
    return new Place(
      resultSet.getInt("id"),
      resultSet.getInt("primaryContactId"),
      resultSet.getString("address"),
      resultSet.getString("gPlaceId"),
      resultSet.getString("displayName")
    );
  }

  public Place(Integer id, Integer primaryContactId, String address, String gPlaceId, String displayName){
    this.id = id;
    this.primaryContactId = primaryContactId;
    this.address = address;
    this.gPlaceId = gPlaceId;
    this.displayName = displayName;
  }

  public Place(){
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getPrimaryContactId() {
    return primaryContactId;
  }
  public void setPrimaryContactId(Integer primaryContactId) {
    this.primaryContactId = primaryContactId;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getgPlaceId() {
    return gPlaceId;
  }
  public void setgPlaceId(String gPlaceId) {
    this.gPlaceId = gPlaceId;
  }
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
