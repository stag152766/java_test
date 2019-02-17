package ru.testjava.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.testjava.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator { //генерирует инфо о группах


  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file; //тип String потому что библиотека jc напрямую работу с файлами не поддерживает


  public static void main(String[] args) throws IOException { //запускаемый файл
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {//заворачиваем в метод try
      jCommander.parse(args);
    } catch (ParameterException ex) {//перехватываем исключение
      jCommander.usage(); //если исключение возникло, то выводим на консоль инфо о доступных опциях при помощи метода usage()
      return; //метод генератор запускать не надо
    }
    generator.run();
  }

  private void run() throws IOException {
//1 часть генерация данных
    List<GroupData> groups = generatorGroups(count);
    //2 часть сохраненние данных в файл
    save(groups, new File(file)); //преобразуем

  }


  private List<GroupData> generatorGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i))
              .withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

  private void save(List<GroupData> groups, File file) throws IOException {
    //открываем файл на запись
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    //закрываем файл
    writer.close();
  }


}
