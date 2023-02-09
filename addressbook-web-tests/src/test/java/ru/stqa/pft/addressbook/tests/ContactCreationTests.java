package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  String group = "test1";

  @BeforeMethod

  public void ensurePreconditions() {

    if (app.db().groups().size() ==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
  }


  @Test(dataProvider = "validContactsFromJson")

  public void testNewContact(ContactData contact) {
    app.goTo().home();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/IMG_9923.jpg");
    //  ContactData contact = new ContactData().withFirstName("Maryia").withLastName("Barysavets")
    //          .withAddress("Minsk").withMobilePhone("375336514233").withHomePhone("801745678657").withWorkPhone("8017786655").withPhone2("80172349067")
    //         .withEmail("maryiabarysavets@gmail.com").withEmail2("test@gmail.com").withEmail3("test+1@gmail.com").withGroup(group);
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}




