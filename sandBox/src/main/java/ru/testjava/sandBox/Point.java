package ru.testjava.sandBox;

public class Point {
  public double x1; // Объекты этого класса содержат по атрибута
  public double x2;
  public double y1;
  public double y2;

  public static void main(String[] args) {

    Point p1 = new Point();
    Point p2 = new Point();
    p1.x1 = 0;
    p2.x2 = 10.0;
    p1.y1 = - 4.0;
    p2.y2 = 5.0;
    System.out.println("Расстояние между точками (" + p1.x1 + "; " + p1.y1+ ") и (" + p2.x2 + "; " + p2.y2 +  ") равно " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) { //функция принимает объекты класса в качестве параметра
    return Math.sqrt(Math.pow((p2.x2 - p1.x1), 2)+ Math.pow((p2.y2 - p1.y1), 2));
  }

}

