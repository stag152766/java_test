package ru.testjava.sandBox;

public class Point {
  public double x1; // Объекты этого класса должны содержать два атрибута
  public double x2;


  public static double distance(Point p1, Point p2) { //функция принимает объекты класса в качестве параметра



    return Math.sqrt((p2.x2 - p1.x1) * (p2.x2 - p1.x1));
  }

  public static void main(String[] args) {

    Point p1 = new Point();
    Point p2 = new Point();
    p1.x1 = 2.54;
    p2.x2 = 10.0;
    System.out.println("Расстояние между точками " + p1.x1 + " и " + p2.x2 +" равно " + distance(p1, p2));


  }


}

