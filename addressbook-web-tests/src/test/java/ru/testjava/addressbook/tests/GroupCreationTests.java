package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage("groups"); //просим объект типа ApplicationManager чтобы он выполнил какое-то действие (обращаемся к нему)
    app.initGroupCreation("new");
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.submitGroupCreation("submit");
    app.returnToGroupPage("group page");
  }

}
