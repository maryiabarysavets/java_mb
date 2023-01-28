package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test

  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Masha", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk"));
    app.getContactHelper().submitModificationContact();
    app.returnToHomePage();
  }
}
