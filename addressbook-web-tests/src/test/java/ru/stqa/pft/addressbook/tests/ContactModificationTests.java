package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().fillContactForm(new ContactData("Maryia", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk", "test1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Masha", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk", null), false);
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().gotoHomePage();
  }
}
