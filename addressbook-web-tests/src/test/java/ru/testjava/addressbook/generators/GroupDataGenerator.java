package ru.testjava.addressbook.generators;

import ru.testjava.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator { //генерирует инфо о группах


  public static void main(String[] args) throws IOException { //запускаемый файл
    //при запуске программы передадим параметры через массив строк
    int count = Integer.parseInt(args[0]); //количество групп
    File file = new File(args[1]);//путь к файлу

  //1 часть генерация данных
    List<GroupData> groups = generatorGroups(count);
  //2 часть сохраненние данных в файл
    save(groups, file);

  }

  private static List<GroupData> generatorGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i))
              .withFooter(String.format("footer %s", i)));
    }
    return groups;
    }

  private static void save(List<GroupData> groups, File file) throws IOException { //выбирем формат coma Separeted Values
    //открываем файл на запись
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(),group.getHeader(),group.getFooter()));
    }
    //закрываем файл
    writer.close();
  }



}
