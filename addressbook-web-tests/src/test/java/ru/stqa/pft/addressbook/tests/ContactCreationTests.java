package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    String group = "test1";
    if(!app.getContactHelper().isThereGroupToSelect(group)){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData(group, null, null));
      app.getContactHelper().createNewContact();
    }
    app.getContactHelper().createNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Maryia", "Barysavets", "375336514233", "mariaborisovets@gmail.com", "Minsk", group), true);
    app.getContactHelper().submit();
  }
}

