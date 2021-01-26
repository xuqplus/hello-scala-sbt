package v3_2_2

import org.scalatest.funsuite.AnyFunSuite

class CTest extends AnyFunSuite {

  test("exclusive or") {
    info("" + (1 ^ 2))
    assert((1 ^ 2) == 3)
  }
}