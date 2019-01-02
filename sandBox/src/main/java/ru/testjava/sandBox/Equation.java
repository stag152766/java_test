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

    if (a == 0) {
      if (b == 0) {
        if (c == 0) {
          n = 1;
        } else {
          n = 0;
        }
      } else {
      if (d > 0) {
        n = 2;
      } else {
        if (d == 0) {
          n = 1;
        } else {
          n = 0;
        }
      }
      }



  }

  public int rootNumber() {
    return n;
  }
}
