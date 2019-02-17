package ru.testjava.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testjava.addressbook.model.GroupData;
import ru.testjava.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.csv")));
    String line = reader.readLine(); //чтение одной первой строки
    //неизвестно сколько строк в файле, поэтому используем цикл while
    while (line != null) {
      String[] split = line.split(";");//выполнение обработки прочитанных строк
      //строим из полученных кусочков объект, который помещаем в массив
      //массив состоит из одного этого объекта(элемента). Добавляем массив в список
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine(); //выполнение этого цикла, на каждой следующей итерации выполняется выражение - читать следующую строчку из тогоже самого файла
    }//когда все строки кончатся, вместо очередной строки вернется значение null и выполнение цикла прекратится
    return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().
            mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


  @Test(enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().сount(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
