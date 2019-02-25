package ru.testjava.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.testjava.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);


  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }


  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test" + m.getName() + "with parameter " + Arrays.asList(p));


  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop (Method m, Object[] p) {
    logger.info("Stop test" + m.getName()+ "with parameter " + Arrays.asList(p));

  }

}
