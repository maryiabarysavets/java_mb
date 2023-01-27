package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String mobile;
  private final String email;
  private final String address;

  public ContactData(String FirstName, String LastName, String Mobile, String Email, String Address) {
    firstName = FirstName;
    lastName = LastName;
    mobile = Mobile;
    email = Email;
    address = Address;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }
}
