package ru.testjava.sandBox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test(enabled = false)
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2)); //Integer.MAX_VALUE - простое число
  }


  @Test(enabled = false)
  public void testPrimesLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test(enabled = false)
  public void testPrimesFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test(enabled = true)
  public void testPrime2Fast() {
    Assert.assertTrue(Primes.isPrime2Fast(Integer.MAX_VALUE));
  }
}
