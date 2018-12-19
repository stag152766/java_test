package ru.testjava.sandBox;

public class MyFirstProject {

	public static void main(String[] args) {

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area()); // обращаемся к методу, который ассоциирован с объектом

		Rectangle r = new Rectangle(4, 6); // чать когда, где функция вызывается
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
	}
}










