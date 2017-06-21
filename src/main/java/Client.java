import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String email;
  private int stylist_id;

  public Client(String name, String email,int stylistid) {
    this.name = name;
    this.email = email;
    this.stylist_id = stylistid;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail(){
    return this.email;
  }

  public int getStylist_id() {
    return this.stylist_id;
  }

  public void save() {
    String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :id);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
   public boolean equals(Object otherClient) {
     if(!(otherClient instanceof Client)) {
       return false;
     } else {
       Client newClient = (Client) otherClient;
       return this.getName().equals(newClient.getName()) && this.getId() == newClient.getId();
     }
   }

  public void editClient(String newName){
    this.name = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET (name) = (:newname) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("newname", newName)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id;";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    }
  }

  public static List<Client> findClients(int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id;";
      return con.createQuery(sql)
      .addParameter("id", stylistid)
      .executeAndFetch(Client.class);
    }
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public static void deleteClient(int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", stylistid)
      .executeUpdate();
    }
  }

}
