package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    String group = "test1";
    if (!app.getContactHelper().isThereAContact()) {
      if (!app.getContactHelper().isThereAGroupToSelect(group)){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData(group, null, null));
      }
      app.getContactHelper().fillContactForm(new ContactData("Maryia", "Barysavets", "Minsk", "375336514233", "mariaborisovets@gmail.com", group), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Masha", "Barysavets", "Moscow", "375336514233", "mariaborisovets@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
