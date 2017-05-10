import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import org.sql2o.*;

public class ClientTest {

  @Before
  public void initialize() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String delete = "DELETE FROM clients *;";
      con.createQuery(delete).executeUpdate();
    }
  }

  @Test
  public void getId_returnsClientId_true() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    assertTrue(newClient.getId() > 0);
  }

  @Test
  public void getName_returnsCorrectName_true() {
    Client newClient = new Client("Client", 1);
    assertTrue(newClient.getName().equals("Client"));
  }

  @Test
  public void client_instantiatesCorrectly_true() {
    Client newClient = new Client("Client", 1);
    assertTrue(newClient instanceof Client);
  }

  @Test
  public void save_savesNewClient_true() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    assertTrue((Client.all().size() > 0));
  }

  @Test
  public void delete_deletesAllClients() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    Client.delete();
    assertTrue(Client.all().size() == 0);
  }

  @Test
  public void all_findsAllClients_true() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

  @Test
  public void find_findsClient_true() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    assertTrue(newClient.equals(Client.find(newClient.getId())));
  }

  @Test
  public void editClient_editsClients_true() {
    Client newClient = new Client("Client", 1);
    newClient.save();
    newClient.editClient("Customer");
    assertTrue(Client.all().get(0).equals(newClient));
  }

}
