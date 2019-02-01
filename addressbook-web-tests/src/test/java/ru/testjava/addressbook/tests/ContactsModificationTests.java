package ru.testjava.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactsModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Alex").withLastname("Jackson").withAddress("My street")
              .withMobile("+79135467841").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().all();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("test1")
            .withLastname("test2").withAddress("test3").withGroup(null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
