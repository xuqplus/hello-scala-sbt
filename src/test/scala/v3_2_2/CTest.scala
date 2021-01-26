package v3_2_2

import org.scalatest.funsuite.AnyFunSuite

class CTest extends AnyFunSuite {

  test("exclusive or") {
    info("" + (1 ^ 2))
    assert((1 ^ 2) == 3)
  }

  val arr = Array(1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 7, 8, 8, 9, 9)

  test("two non-repeating numbers in an array") {
    var c: Integer = null
    for (a <- arr) {
      if (c == null)
        c = a
      else
        c ^= a
      //      for (b <- arr) {
      //        info(String.format("%s-%s", a, b))
      //      }
    }
    info("" + c)
    info("" + (5 ^ 6))
  }
}