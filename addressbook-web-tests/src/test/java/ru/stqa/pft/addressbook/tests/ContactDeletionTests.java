package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod

  public void ensurePreconditions() {
    String group = "test1";
    if (app.contact().all().size() == 0) {
      if (!app.contact().isThereAGroupToSelect(group)) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(group));
      }
      app.contact().createAndFill(new ContactData().withFirstName("Maryia").withLastName("Barysavets")
              .withAddress("Minsk").withMobilePhone("375336514233").withHomePhone("801745678657").withWorkPhone("8017786655")
              .withEmail("maryiabarysavets@gmail.com").withEmail2("test@gmail.com").withEmail3("test+1@gmail.com").withGroup(group));
    }
  }

  @Test

  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().home();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
