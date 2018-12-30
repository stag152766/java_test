package ru.testjava.addressbook.tests;

import org.testng.annotations.Test;
import ru.testjava.addressbook.tests.TestBase;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion(){
    app.gotoGroupPage("groups");
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage("group page");
  }


}
