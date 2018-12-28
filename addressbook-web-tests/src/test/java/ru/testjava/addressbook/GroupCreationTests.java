package ru.testjava.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage("groups");
    initGroupCreation("new");
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation("submit");
    returnToGroupPage("group page");
  }

}
