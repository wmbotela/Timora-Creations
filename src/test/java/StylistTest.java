import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import org.sql2o.*;

public class StylistTest {
//Instructs stylistTest to use db hair_salon_test
  @Before
  public void initialize() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "william", "password");
  }

// Deleltes db after test run
  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String delete = "DELETE FROM stylists *;";
      con.createQuery(delete)
      .executeUpdate();
    }
  }

  @Test
  public void getId_returnsStylistId_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    assertTrue(newStylist.getId() > 0);
  }

  @Test
  public void getName_FindsCorrectName_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    assertTrue(newStylist.getName().equals("Stylist"));
  }

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    assertTrue(newStylist instanceof Stylist);
  }

  @Test
  public void save_savesNewStyist_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    assertTrue((Stylist.all().size() > 0));
  }

  @Test
  public void find_findsStylist_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    assertTrue(newStylist.equals(Stylist.find(newStylist.getId())));
  }

  @Test
  public void delete_remvoesAllStylists() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    Stylist.delete();
    assertTrue(Stylist.all().size() == 0);
  }

  @Test
  public void all_findsAllStylists_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void editStylist_editsStylist_true() {
    Stylist newStylist = new Stylist("Stylist", "email", 1);
    newStylist.save();
    newStylist.editStylist("Stylize");
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }


}
