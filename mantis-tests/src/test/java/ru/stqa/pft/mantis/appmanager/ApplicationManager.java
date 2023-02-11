package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  private String browser;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(Browser.FIREFOX.browserName())) {
      wd = new FirefoxDriver();
    } else if (browser.equals(Browser.CHROME.browserName())) {
      wd = new ChromeDriver();
    } else if (browser.equals(Browser.SAFARI.browserName())) {
      wd = new SafariDriver();
    }

    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}