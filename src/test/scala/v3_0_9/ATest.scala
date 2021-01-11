package v3_0_9

import org.scalatest.{DiagrammedAssertions, FunSuite}

class ATest extends FunSuite with DiagrammedAssertions {

  test("this is true") {
    assert(true)
  }

  test("this is false") {
    assert(false)
  }

  test("Hello should start with H") {
    assert("hello".startsWith("H"))
  }
}