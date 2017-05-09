import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
  private Client client;
  private Client client2;


  @Test
  public void Client_instantiates_true(){
    assertEquals(true, client instanceof Client);
  }
  @Test
  public void getFirstName_returnsCorrectName_String() {
    assertEquals("Kim", client.getFirstName());
  }
  @Test
  public void getLastName_returnsCorrectName_String() {
    assertEquals("West", client.getLastName());
  }
  @Test
  public void getPhoneNumber_returnsCorrectPhoneNumber_String() {
    assertEquals("0724556677", client.getPhoneNumber());
  }
  @Test
  public void getEmail_returnsCorrectEmail_String() {
    assertEquals("kimk@gmail.com", client.getEmail());
  }
  @Test
  public void getAge_returnsCorrectAge_String() {
    assertEquals(35, client.getAge());
  }
  @Test
  public void getNotes_returnsCorrectNotes_String() {
    assertEquals("preference", client.getNotes());
  }
}
