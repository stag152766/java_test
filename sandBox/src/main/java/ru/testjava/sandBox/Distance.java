package ru.testjava.sandBox;

public class Distance {
  public double x1;
  public double x2;
  public double y1;
  public double y2;

  public Distance(double x1, double x2, double y1, double y2) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }

  public double distance() { // метод будет ассоциирован с объектом
    return Math.sqrt(Math.pow((this.x2 - this.x1), 2)+ Math.pow((this.y2 - this.y1), 2)); //обращаемся к ассоциированному объекту
  }
}
