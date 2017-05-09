import java.util.List;

// Client properties
public class Client{
  private int id =0;
  private int stylistid = 0;
  private String firstname;
  private String lastname;
  private String phonenumber;
  private String email;
  private int age;
  private String notes;

// Client constructor
  public Client(String firstname, String lastname, String phone, String email, int age, String notes, int stylistid) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phone;
    this.email = email;
    this.age = age;
    this.notes = notes;
    this.stylistid = stylistid;
  }

    public int getId() {
      return id;
    }
    public String getFirstName() {
    return firstname;
    }

    public String getLastName() {
    return lastname;
    }
    public String getPhoneNumber() {
    return phonenumber;
    }
    public String getEmail() {
    return email;
    }
    public int getAge() {
    return age;
    }
    public String getNotes() {
    return notes;
  }
}
