package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    String group = "test1";
    if (!app.getContactHelper().isThereAGroupToSelect(group)) {
      app.goTo().groupPage();
      app.group().create(new GroupData(group, null, null));
      app.getContactHelper().createNewContact();
    }
    app.goTo().homePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createNewContact();
    ContactData contact = new ContactData("Maryia", "Barysavets", "Minsk", "mariaborisovets@gmail.com", "375336514233", group);
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submit();
    app.goTo().homePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

