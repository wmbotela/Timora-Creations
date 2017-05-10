import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;

  public Stylist(String name) {
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name = name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
   public boolean equals(Object otherStylist) {
     if(!(otherStylist instanceof Stylist)) {
       return false;
     } else {
       Stylist newStylist = (Stylist) otherStylist;
       return this.getName().equals(newStylist.getName()) && this.getId() == newStylist.getId();
     }
   }

  public void editStylist(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :newname WHERE id = :id";
      con.createQuery(sql)
      .addParameter("newname", this.name)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id;";
      return con.createQuery(sql)
      .addParameter("id", stylistId)
      .executeAndFetchFirst(Stylist.class);
    }
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public static void deleteStylist(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", _id)
      .executeUpdate();
    }
  }

}
