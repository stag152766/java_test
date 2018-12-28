package ru.testjava.addressbook;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase { //исчезла ссылка на расширение класса ApplicationManager

  protected final ApplicationManager app = new ApplicationManager(); // создается ссылка на новый объект типа ApplicationManager

  @BeforeClass(alwaysRun = true)
  public void setUp()  {
    app.init(); /* те вызовы , которые выполнялись в текущем классе
    (как бы this.init()) теперь вместо этого вызываются методы, которые находятся в объекте  типа ApplicationManager
    */
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

}
