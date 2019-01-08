package ru.testjava.sandBox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test(enabled = false)
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE)); //Integer.MAX_VALUE - простое число
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2)); //Integer.MAX_VALUE - простое число
  }


  @Test(enabled = false)
  public void testPrimesLong() {
    long n = Integer.MAX_VALUE; //значение переменной будет преобразовано в тип long
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void testPrimesFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }
}
