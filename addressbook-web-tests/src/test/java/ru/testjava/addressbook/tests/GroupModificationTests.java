package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.GroupData;

public class GroupModificationTests extends TestBase{

  GroupModificationTests() {

  }

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification(); //создаем метод в классе GroupHelper
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();//создаем метод в классе GroupHelper
    app.getGroupHelper().returnToGroupPage();
  }
}
