package ru.testjava.sandBox;

public class MyFirstProject {

	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Max");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s)); /* передаем объект, в качестве параметра функции
s.1 используем объект, у которого мы берем атрибут s.l
*/
		Rectangle r = new Rectangle(4, 6); // чать когда, где функция вызывается
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(Square s){ // функция принимает не сторону квадрата, а объект типа Квадрат
		return s.l * s.l; // для того чтобы получить доступ к атрибуту объекта, необходимо написать (объект).(значение атрибута)
	}

	public static double area(Rectangle r) {
		return r.a * r.b;
	}
}