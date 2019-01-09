package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); //количество групп до добавления
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupHelper().getGroupCount(); //количество групп до добавления
    Assert.assertEquals(after, before + 1);
  }

}
