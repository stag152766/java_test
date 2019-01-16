package ru.testjava.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.testjava.addressbook.modul.GroupData;

import java.util.ArrayList;
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

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
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




  public void createGroup(GroupData group) { //параметру дали любое имя, значение задается в тесте
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() { //будет проверяться наличие какого то элемента, который мы пытаемся выбирать в методе SelectGroup (соответствует включенному чекбоксу)
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
     return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements) {
      String name = element.getText();
      String id = element.findElement(By.tagName("input")).getAttribute("value");  //откуда взять id ищем внутри одного элемента другой
      GroupData group = new GroupData(id, name, null,null); //GroupHelper должен откуда то идентификатор извлекать, и передавать его в качестве параметра в конструктор
      groups.add(group);
    }
      return groups;
  }
}
