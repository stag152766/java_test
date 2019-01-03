package ru.testjava.sandBox;

import java.sql.SQLOutput;

public class Equation {

  private double a;
  private double b;
  private double c;

  private int n; // колво корней уравнения

  public Equation(double a, double b, double c) { //принимает на вход 3 параметра

    this.a = a; //просим среду чтобы эти параметры были присвоены в одноименные поля
    this.b = b;
    this.c = c;


    double d = b * b - 4 * a * c; //анализ количества корней

    if (a != 0) { //алгоритм решения квадратного уравнения
      if (d > 0) { //используется конструкция типа свертка
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }

    } else if (b != 0) { //алгоритм решения линейного, вырожденного уравнения //используется конструкция типа свертка
      n = 1;

    } else if (c != 0) {
      n = 0;

    } else {
      n = -1;
    }


  }




  public int rootNumber() {
    return n;
  }
}
