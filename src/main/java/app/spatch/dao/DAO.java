package app.spatch.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import app.spatch.model.DBObject;
import app.spatch.model.annotations.Insertable;
import app.spatch.model.annotations.Selectable;

public class DAO<T extends DBObject<T>> {
  private T dbObject;

  public DAO(T dbObject){
    this.setDBObject(dbObject);
  }

  public void setDBObject(T dbObject){
    this.dbObject = dbObject;
  }

  public DAO(Class<T> dbObjectClass){
    try{
      this.dbObject = dbObjectClass.newInstance();
    } catch (InstantiationException e){
      e.printStackTrace();
      System.out.println("Could not instantiate class: " + dbObjectClass.getName());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println("The class has no public constructor. Can't create: " + dbObjectClass.getName());
    }
  }

  public List<T> selectAll(){
    return selectAll(0, 0);
  }

  public List<T> selectAll(Integer limit){
    return selectAll(limit, 0);
  }

  public List<T> selectAll(Integer limit, Integer offset){
    String query = getSelectStatement();

    if(limit > 0 && offset > 0){
      query += " LIMIT ?";
      query += " OFFSET ?";
      return selectFromSQL(query, limit, offset);
    }
    if(limit > 0){
      query += " LIMIT ?";
      return selectFromSQL(query, limit);
    }
    return selectFromSQL(query);
  }
  public List<T> select(String constraints){
    String query = getSelectStatement();
    query += constraints;
    return selectFromSQL(query);
  }

  private List<T> selectFromSQL(String query, Object...params) {
    List<T> records = new ArrayList<T>();

    try {
      Connection conn = MariaDBUtil.getConnection();
      PreparedStatement stmt = prepareStatement(conn, query, false, params);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        records.add(dbObject.fromResultSet(resultSet));
      }
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return records;
  }


  public T selectById(Integer id){
    List<T> records = new ArrayList<T>();
    String query    = getSelectStatement();

    query += " WHERE id = ?";
    records = selectFromSQL(query, id);
    return records.get(0);
  }

  public List<T> select(String constraints, Object...params){
    String query = getSelectStatement();
    query += " " + constraints;
    return selectFromSQL(query, params);
  }

  public T insertRecord(T dbObject){
    this.setDBObject(dbObject);
    return insertRecord();
  }

  public T insertRecord(){
    T insertedRecord  = null;
    String query      = "INSERT INTO " + dbObject.getTableName() + " (";
    String values     = "";
    Map<String, Object> fieldsAndValues = getFieldsAndValues(Insertable.class);

    Object[] params = new Object[fieldsAndValues.size()];
    int i = 0;
    for(String key : fieldsAndValues.keySet()){
      query += key + ", ";
      values += "?, ";
      params[i] = fieldsAndValues.get(key);
      i++;
    }
    query = query.substring(0, query.length()-2)
      .concat(") VALUES (")
      .concat(values.substring(0, values.length()-2))
      .concat(")");

    try {
      Connection conn = MariaDBUtil.getConnection();
      PreparedStatement stmt = prepareStatement(conn, query, true, params);
      stmt.executeUpdate();
      ResultSet resultSet = stmt.getGeneratedKeys();
      Integer insertedRecordId = null;

      if(resultSet.next()){
        insertedRecordId = resultSet.getInt("id");
      }
      T record = selectById(insertedRecordId);
      if(record != null){
        insertedRecord = record;
      }
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return insertedRecord;
  }

  public T updateRecord(T dbObject){
    this.setDBObject(dbObject);
    return updateRecord();
  }

  public T updateRecord(){
    T record = selectById(dbObject.getId());

    if(record != null){
      String query = String.format("UPDATE %s SET ", dbObject.getTableName());
      Map<String, Object> updateMap = getFieldsAndValues(Insertable.class);
      Object[] params = new Object[1+updateMap.size()];

      int i = 0;
      for(String key : updateMap.keySet()){
        query += key + " = ?, ";
        params[i] = updateMap.get(key);
        i++;
      }
      params[i] = dbObject.getId();
      query = query.substring(0, query.length()-2);
      query += " WHERE id = ?";

      executeSQL(query, params);
    }
    return record;
  }

  public T deleteRecord(T dbObject){
    this.dbObject = dbObject;
    return deleteRecord();
  }

  public T deleteRecord(){
    T record = selectById(dbObject.getId());

    if(record != null){
      String query = String.format("DELETE FROM %s WHERE id = ?", dbObject.getTableName());
      executeSQL(query, record.getId());
    }
    return record;
  }

  public T executeSQL(String query, Object...params){
    T record = selectById(dbObject.getId());

    if(record != null){
      try {
        Connection conn = MariaDBUtil.getConnection();
        PreparedStatement stmt = prepareStatement(conn, query, false, params);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return record;
  }

  private String getSelectStatement(){
    String  query = "SELECT " +
      getFields(Selectable.class).toString().replace("[", "").replace("]", "") +
      " FROM " + dbObject.getTableName();
    return query;
  }

  private List<String> getFields(Class<? extends Annotation> annotation){
    List<String> fieldNames = new ArrayList<String>();
    List<Field> fields = Arrays.asList(dbObject.getClass().getDeclaredFields()).stream().filter(
      field -> field.isAnnotationPresent(annotation)
    ).collect(Collectors.toList());

    fields.forEach(field -> fieldNames.add(field.getName()));
    return fieldNames;
  }

  private Map<String, Object> getFieldsAndValues(Class<? extends Annotation> annotation){
    Map<String, Object> fieldsAndValues = new HashMap<String, Object>();
    List<Field> fields = Arrays.asList(dbObject.getClass().getDeclaredFields()).stream().filter(
      field -> field.isAnnotationPresent(annotation)
    ).collect(Collectors.toList());

    fields.forEach(field -> {
      String name = field.getName();
      Object obj = null;
      try{
        field.setAccessible(true);
        obj = field.get(dbObject);
        field.setAccessible(false);
      } catch (IllegalAccessException e){
        e.printStackTrace();
      }
      fieldsAndValues.put(name, obj);
    });
    return fieldsAndValues;
  }

  public static PreparedStatement prepareStatement (Connection conn, String query, Boolean returnKeys, Object... params) {
    PreparedStatement stmt = null;
    try{
      stmt = returnKeys ?
        conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) :
        conn.prepareStatement(query);
      for (int i = 0; i < params.length; i++) {
        stmt.setObject(i + 1, params[i]);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println(stmt);
    return stmt;
  }
}
