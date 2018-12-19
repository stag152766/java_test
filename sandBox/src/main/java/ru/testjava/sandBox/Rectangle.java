package ru.testjava.sandBox;

public class Rectangle {

    public double a;
    public double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return this.a * this.b; //ссылка на объект доступно через ключевое слово this. ссылка на тот объект, в котором этот метод вызван
    }
}