package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    GroupData groupToAdd = app.db().groups().iterator().next();
    Contacts contacts = app.db().contacts();
    if (app.db().contacts().size() == 0) {
      app.goTo().home();
      app.contact().create(new ContactData().withFirstName("Maryia").withLastName("Barysavets")
              .withAddress("Minsk").withMobilePhone("375336514233").withEmail("maryiabarysavets@gmail.com").inGroup(groupToAdd));
    }
    app.goTo().home();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        app.contact().selectContactById(contact.getId());
        app.contact().addContactToGroup(String.valueOf(groupToAdd.getId()));
      }
    }
  }

  @Test
  public void testContactRemoveGroup() throws InterruptedException {
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = app.contact().contactInGroup(contacts);
    GroupData selectedGroup = selectedGroup();
    Groups contactGroupsBefore = app.db().contactById(selectedContact.getId()).getGroups();
    Contacts groupContactsBefore = app.db().getGroupById(selectedGroup.getId()).getContacts();
    app.goTo().home();
    app.contact().selectGroup(selectedGroup.getName());
    app.contact().removeSelectedContactFromGroup(app.contact().contactInGroup(contacts));
    ContactData selectedContactAfter = app.db().contactById(selectedContact.getId());
    GroupData groupAfterRemove = app.db().getGroupById(selectedGroup.getId());
    Groups contactGroupsAfter = selectedContactAfter.getGroups();
    Contacts groupContactsAfter = groupAfterRemove.getContacts();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(selectedGroup)));
    assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(selectedContact)));
  }

  public GroupData selectedGroup() {
    Contacts contacts = app.db().contacts();
    return app.contact().contactInGroup(contacts).getGroups().iterator().next();
  }
}