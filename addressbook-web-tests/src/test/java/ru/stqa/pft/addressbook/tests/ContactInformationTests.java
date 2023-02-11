package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String group = "test1";
    if (app.contact().all().size() == 0) {
      if (!app.contact().isThereAGroupToSelect(group)) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(group));
      }
      app.contact().createAndFill(new ContactData().withFirstName("Maryia").withLastName("Barysavets")
              .withAddress("Minsk").withMobilePhone("375336514233").withHomePhone("8 017 456 78 65").withWorkPhone("8-017-786-655").withPhone2("80172349067")
              .withEmail("maryiabarysavets@gmail.com").withEmail2("test@gmail.com").withEmail3("test+1@gmail.com"));//.withGroup(group));

    }
  }

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhone2())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
