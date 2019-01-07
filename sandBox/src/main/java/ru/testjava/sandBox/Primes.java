package ru.testjava.sandBox;

public class Primes {

  public static boolean isPrime(int n) { //true простое
    for (int i = 2; i < n ; i++) {
      if (n % i == 0) { //число делится без остатка
      return false;
      }
    }
    return true;
  }

  //чикл for может быть переписан в другой вид
  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n) {
      if (n % i == 0) {
        return false;
      }
      i++; //на каждой итерации цикла увеличиваем переменную на 1, выполнять эти действия будем пока i = n
    }
    return true;
  }
}