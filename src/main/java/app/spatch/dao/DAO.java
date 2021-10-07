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
    query += " " + constraints;
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


  public List<T> selectById(Integer id){
    List<T> records = new ArrayList<T>();
    String query    = getSelectStatement();

    query += " WHERE id = ?";
    records = selectFromSQL(query, id);
    return records;
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
    String query      = "INSERT INTO " + dbObject.generateTableName() + " (";
    String values     = "";
    Map<String, Object> fieldsAndValues = getFieldNamesAndValues(Insertable.class);

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
      List<T> records = selectById(insertedRecordId);
      if(!records.isEmpty()){
        insertedRecord = records.get(0);
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
    List<T> records = selectById(dbObject.getId());
    T updatedRecord = null;

    if(!records.isEmpty()){
      updatedRecord = records.get(0);
      String query = String.format("UPDATE %s SET ", dbObject.generateTableName());
      Map<String, Object> updateMap = getFieldNamesAndValues(Insertable.class);
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
    return updatedRecord;
  }

  public T deleteRecord(Integer id){
    List<T> records = selectById(id);
    T deletedRecord = null;

    if(!records.isEmpty()){
      deletedRecord = records.get(0);
      String query = String.format("DELETE FROM %s WHERE id = ?", dbObject.generateTableName());
      executeSQL(query, deletedRecord.getId());
    }
    return deletedRecord;
  }

  public void executeSQL(String query, Object...params){
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

  private String getSelectStatement(){
    String  query = "SELECT " +
      getFieldNames(Selectable.class).toString().replace("[", "").replace("]", "") +
      " FROM " + dbObject.generateTableName();
    return query;
  }

  private List<Field> getFields(Class<? extends Annotation> annotation){
    List<Field> fields = Arrays.asList(dbObject.getClass().getDeclaredFields()).stream().filter(
      field -> field.isAnnotationPresent(annotation)
    ).collect(Collectors.toList());
    return fields;
  }

  private List<String> getFieldNames(Class<? extends Annotation> annotation){
    List<String> fieldNames = new ArrayList<String>();
    List<Field> fields = getFields(annotation);

    fields.forEach(field -> fieldNames.add(field.getName()));
    return fieldNames;
  }

  private Map<String, Object> getFieldNamesAndValues(Class<? extends Annotation> annotation){
    Map<String, Object> fieldsAndValues = new HashMap<String, Object>();
    List<Field> fields = getFields(annotation);

    fields.forEach(field -> {
      String name = field.getName();
      Object obj = null;
      try{
        if(field.isAccessible()){
          obj = field.get(dbObject);
          if(obj != null && obj.getClass().isEnum()){
            obj = obj.toString();
          }
        } else {
          field.setAccessible(true);
          obj = field.get(dbObject);
          if(obj != null && obj.getClass().isEnum()){
            obj = obj.toString();
          }
          field.setAccessible(false);
        }
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
