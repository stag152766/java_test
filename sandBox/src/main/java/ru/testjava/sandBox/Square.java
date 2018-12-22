package ru.testjava.sandBox;

public class Square {
    public double l;

    public Square(double l) {
        this.l = l;
    }

    public double area(){ // метод будет ассоциирован с объектом
        return this.l * this.l; /* обращаться к ассоциированному объету, можно через ключевое слово this -
        это ссылка на тот объект, с которым ассоциирован метод*/

    }
}
