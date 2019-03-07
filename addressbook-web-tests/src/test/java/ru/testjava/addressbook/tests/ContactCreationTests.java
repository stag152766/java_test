package ru.testjava.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.ContactData;
import ru.testjava.addressbook.model.Contacts;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    String xml = "";
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.xml")));
    String line = reader.readLine();

    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.alias("contact",ContactData.class);
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().
            mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}




