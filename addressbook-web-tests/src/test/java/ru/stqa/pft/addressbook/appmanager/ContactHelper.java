package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submit() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address"), contactData.getAddress());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void createNewContact() {
    click(By.linkText("add new"));
    wd.get("http://localhost/addressbook/edit.php");
  }

 // public void initContactModification(int index) {
 //   wd.findElements(By.name("selected[]")).get(index).click();
//    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
 // }
 public void initContactModification(int id) {
   WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
   WebElement row = checkbox.findElement(By.xpath("./../.."));
   List<WebElement> cells = row.findElements(By.tagName("td"));
   cells.get(7).findElement(By.tagName("a")).click();
 }

  public void submitModificationContact() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }


  public void createAndFillContact(ContactData contact) {
    createNewContact();
    fillContactForm(contact, true);
    submit();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereAGroupToSelect(String groupToFind) {
    createNewContact();
    try {
      Select group = new Select(wd.findElement(By.name("new_group")));
      group.selectByVisibleText(groupToFind);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
