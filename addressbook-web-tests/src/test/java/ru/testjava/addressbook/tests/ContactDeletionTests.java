package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Alex").
              withLastname("Jackson").withAddress("My street").
              withMobile("+79135467841").withGroup("test1"), true);
    }
  }


  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().selectContact(index);
    app.contact().deleteContact();
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
