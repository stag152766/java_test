package ru.testjava.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Alex").
              withLastname("Jackson").withAddress("My street").
              withMobile("111").withHome("222").withWork("333").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().HomePage();
    //загружаем список множества контактов, выбираем случайным образом какой то контакт
    ContactData contact = app.contact().all().iterator().next();
    //будем сравнивать информацию на главной странице с данными в форме редактирования контакта
    //сделаем вспомогательный метод
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);







  }





}
