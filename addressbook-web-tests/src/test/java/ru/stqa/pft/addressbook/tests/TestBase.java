package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

//  протолирование (лог-файлы)
//  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser",Browser.CHROME.browserName()));


  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown()  {
    app.stop();
  }

//  протолирование (лог-файлы)
//  @BeforeMethod
//  public void logTestStart(Method m, Object[] p){
//    logger.info("Start test"+ m.getName() + "with parameters" + Arrays.asList(p));
//  }

//  @AfterMethod
//  public void logTestStop(Method m, Object[] p){
//    logger.info("Stop test" + m.getName()+ "with parameters" + Arrays.asList(p));
//  }
}
