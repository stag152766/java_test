package ru.testjava.sandBox;

public class Cat {
  private String name;
  private int age;
  private int weight;

  public Cat(String name, int age, int weight) {
    this.name = name;
    this.age = age;
    this.weight = weight;
  }


  public static void main(String[] args){
    Cat barsik = new Cat("Барсик", 5, 4);
    String barsikName = barsik.getName();
    int barsikAge = barsik.getAge();
    int barsikWeight = barsik.getWeight();
    System.out.println("Имя кота " + barsikName);
    System.out.println("Возвраст кота " + barsikAge);
    System.out.println("Вес кота " + barsikWeight);


  }

  public int getAge() {
    return age;
  }

  public int getWeight() {
    return weight;
  }

  private String getName() {
    return name;
  }


}
