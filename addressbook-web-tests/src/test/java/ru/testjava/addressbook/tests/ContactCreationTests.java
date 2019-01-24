package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testjava.addressbook.modul.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Alex").withLastname("Jackson")
            .withAddress("My street").withMobile("+79135467841").withGroup("test1");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}




