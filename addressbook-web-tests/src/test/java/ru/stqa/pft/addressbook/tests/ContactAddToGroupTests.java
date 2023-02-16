package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (contactToAddToGroup(app.db().contacts()) == null) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().home();
      app.contact().createAndFill(new ContactData().withFirstName("Maryia").withLastName("Barysavets")
              .withAddress("Minsk").withMobilePhone("375336514233").withEmail("maryiabarysavets@gmail.com"));
    }
  }

  @Test
  public void addContactToGroup() {
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contactToAddToGroup(contacts);
    GroupData groupToAdd = groupWithoutContact();
    Groups contactGroupsBefore = selectedContact.getGroups();
    Contacts groupContactsBefore = groupToAdd.getContacts();
    app.goTo().home();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().addContactToGroup(String.valueOf(groupToAdd.getId()));
    ContactData selectedContactAfter = app.db().contactById(selectedContact.getId());
    GroupData groupToAddAfter = app.db().getGroupById(groupToAdd.getId());
    Groups contactGroupsAfter = selectedContactAfter.getGroups();
    Contacts groupContactsAfter = groupToAddAfter.getContacts();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupToAdd)));
    assertThat(groupContactsAfter, equalTo(groupContactsBefore.withAdded(selectedContactAfter)));

  }

  public ContactData contactToAddToGroup(Contacts contacts) {
    for (ContactData contact : contacts) {
      Set<GroupData> contactInGroup = contact.getGroups();
      if (contactInGroup.size() < app.db().groups().size()) {
        return contact;
      }
    }
    return null;
  }

  public GroupData groupWithoutContact() {
    Contacts contacts = app.db().contacts();
    Groups groupsInContact = contactToAddToGroup(contacts).getGroups();
    Groups groups = app.db().groups();
    groups.removeAll(groupsInContact);
    GroupData group = groups.iterator().next();
    return group;
  }
}