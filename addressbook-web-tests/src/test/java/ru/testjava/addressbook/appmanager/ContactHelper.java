package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void submitContactCreation() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }

  public void fillContactForm(String fname, String mname, String lname, String mtnick, String strname, String mnumber) {
    type(By.name("firstname"),fname);
    type(By.name("middlename"),mname);
    type(By.name("nickname"),lname);
    type(By.name("address"),strname);
    type(By.name("mobile"),mnumber);
  }
}
