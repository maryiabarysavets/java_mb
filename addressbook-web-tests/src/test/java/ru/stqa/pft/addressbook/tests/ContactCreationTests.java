package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.getContactHelper().createNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Maryia", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk"));
    app.getContactHelper().submit();
    app.returnToHomePage();
  }

}

