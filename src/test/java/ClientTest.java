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
  public void getStylistId_returnsCorrectId_String() {
    assertEquals(1, client.getStylistId());
  }
  @Test
  public void setStylistId_updatesStylistId_int() {
    client2.setStylistId(2);
    assertEquals(2, Client.find(client2.getId()).getStylistId());
  }

  @Test
  public void getFirstName_returnsCorrectName_String() {
    assertEquals("Kim", client.getFirstName());
  }
  @Test
  public void setFristName_updatesName_String() {
    client2.setFirstName("Kanye");
    assertEquals("Kanye", Client.find(client2.getId()).getFirstName());
  }
  @Test
  public void getLastName_returnsCorrectName_String() {
    assertEquals("West", client.getLastName());
  }
  @Test
  public void setLastName_updatesName_String() {
    client2.setFirstName("West");
    assertEquals("West", Client.find(client2.getId()).getFirstName());
  }
  @Test
  public void getPhoneNumber_returnsCorrectPhoneNumber_String() {
    assertEquals("0724556677", client.getPhoneNumber());
  }
  @Test
  public void setPhoneNumber_updatesPhoneNumber_String() {
    client2.setPhoneNumber("0724445566");
    assertEquals("0724445566", Client.find(client2.getId()).getPhoneNumber());
  }
  @Test
  public void getEmail_returnsCorrectEmail_String() {
    assertEquals("kimk@gmail.com", client.getEmail());
  }
  @Test
  public void setEmail_updatesEmail_String() {
    client2.setEmail("Kanye@gmail.com");
    assertEquals("kanye@gmail.com", Client.find(client2.getId()).getEmail());
  }
  @Test
  public void getAge_returnsCorrectAge_String() {
    assertEquals(35, client.getAge());
  }
  @Test
  public void setFristAge_updatesAge_int() {
    client2.setAge(40);
    assertEquals(40, Client.find(client2.getId()).getAge());
  }
  @Test
  public void getNotes_returnsCorrectNotes_String() {
    assertEquals("preference", client.getNotes());
  }
  @Test
  public void setNotes_updatesNotes_String() {
    client2.setNotes("allergies");
    assertEquals("allergies", Client.find(client2.getId()).getNotes());
  }
}




































  
