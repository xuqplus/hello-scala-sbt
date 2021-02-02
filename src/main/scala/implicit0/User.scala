package implicit0

abstract class User[A] {
  var sum: A

  def add(a: A, b: A): A
}

