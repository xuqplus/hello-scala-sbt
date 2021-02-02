package implicit0

object ImplicitUser {

  implicit val stringUser: User[String] = new User[String] {
    override var sum: String = ""

    override def add(a: String, b: String): String = {
      sum += a + b
      sum
    }
  }
  implicit val integerUser: User[Integer] = new User[Integer] {
    override var sum: Integer = 0

    override def add(a: Integer, b: Integer): Integer = {
      sum += a + b
      sum
    }
  }
}
