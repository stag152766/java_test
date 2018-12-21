package ru.testjava.sandBox;

public class Point {

  public static void main(String[] args) {

    Distance d = new Distance(2,1,5,7);
      System.out.println("Расстояние между точками (" + d.x1 + "; " + d.y1+ ") и (" + d.x2 + "; " + d.y2 +  ") равно " + d.distance());

  }

}

