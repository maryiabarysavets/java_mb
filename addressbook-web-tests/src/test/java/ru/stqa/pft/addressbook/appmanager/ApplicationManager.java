package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class ApplicationManager  {
  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
private final String browser;

 public ApplicationManager(String browser) {
 this.browser = browser;
}

  public void init() {
 if(browser == Browser.IE.browserName()){
   wd = new InternetExplorerDriver();
 } else if (browser == Browser.CHROME.browserName()){
   wd = new ChromeDriver();
  } else if (browser == Browser.SAFARI.browserName()) {
     wd = new SafariDriver();
    }

    System.setProperty("web-driver.chrome.driver", "");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    wd.get("http://localhost/addressbook/index.php");

    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }


  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
    wd.get("http://localhost/addressbook/index.php");
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ru.stqa.pft.addressbook.appmanager.ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
