package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.testjava.addressbook.model.GroupData;
import ru.testjava.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) { //параметру дали любое имя, значение задается в тесте
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() { //будет проверяться наличие какого то элемента, который мы пытаемся выбирать в методе SelectGroup (соответствует включенному чекбоксу)
    return isElementPresent(By.name("selected[]"));
  }

  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  public void delete(GroupData deletedGroup) {
    selectGroupById(deletedGroup.getId()); //выбор по идентификатору номеру
    deleteSelectedGroups();
    returnToGroupPage();
  }
}

