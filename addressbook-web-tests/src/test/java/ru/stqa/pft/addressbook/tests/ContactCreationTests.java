package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  String group = "test1";

  @BeforeMethod

  public void ensurePreconditions() {

    if (!app.contact().isThereAGroupToSelect(group)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
  }


  @Test

  public void testNewContact() {
     app.goTo().home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Maryia").withLastName("Barysavets")
            .withAddress("Minsk").withMobilePhone("375336514233").withHomePhone("801745678657").withWorkPhone("8017786655")
            .withEmail("maryiabarysavets@gmail.com").withEmail2("test@gmail.com").withEmail3("test+1@gmail.com").withGroup(group);
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}




