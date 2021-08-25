package app.spatch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBObject<T> {
  private Integer id;

  public DBObject(){
  }

  public DBObject(Integer id){
    this.id = id;
  }

  public Integer getId(){
    return this.id;
  }

  public String getTableName(){
    return this.getClass().getName().toLowerCase();
  }

  public abstract T fromResultSet(ResultSet resultSet) throws SQLException;
}
