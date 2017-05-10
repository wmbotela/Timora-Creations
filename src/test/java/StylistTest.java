import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
  private Stylist stylist;
  private Stylist stylist2;

  @Test
  public void Stylist_instantiates_true() {
    assertEquals(true, stylist instanceof Stylist);
  }

  @Test
  public void getName_returnsCorrectName_String() {
    assertEquals("kate", stylist.getName());
  }

  @Test
  public void setName_updatesName_String() {
    stylist.setName("Ken");
    assertEquals("Ken", Stylist.find(stylist.getId()).getName());
  }

  @Test
  public void find_returnCorrectStylist_true() {
    assertTrue(Stylist.find(stylist.getId()).getName().equals(stylist.getName()));
  }

  @Test
  public void Stylist_returnsAllInstances_true() {
    assertTrue(Stylist.all().size()>1);
  }

}
