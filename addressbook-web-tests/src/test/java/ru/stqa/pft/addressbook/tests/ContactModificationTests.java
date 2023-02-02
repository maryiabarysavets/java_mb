package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    String group = "test1";
    if (!app.getContactHelper().isThereContact()) {
      if (!app.getContactHelper().isThereGroupToSelect(group)){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData(group, null, null));
      }
      app.getContactHelper().fillContactForm(new ContactData("Maryia", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk", group), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Masha", "Gavrilovets", "375336514233", "mariaborisovets@gmail.com", "Minsk", null), false);
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().gotoHomePage();
  }
}
