package ru.testjava.sandBox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
      String[] langs = {"Java", "C#", "Python", "PHP"};


      List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

      for (int i = 0; i < languages.size(); i++){ //атрибут size для списка
        System.out.println("Я хочу выучить " + languages.get(i)); // + обращается к элементу с помощью метода get (индекс)
      }

    }
}
