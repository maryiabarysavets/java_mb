package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.createNewContact();
    app.fillContactForm(new ContactData("Maryia", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk"));
    app.submit();
    app.returnToHomePage();
  }

}

