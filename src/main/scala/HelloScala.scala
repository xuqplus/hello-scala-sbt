import implicit0.AService.sum
import implicit0.ImplicitUser.{integerUser, stringUser}
import implicit0.User

object HelloScala {

  implicit val stringUser0: User[String] = stringUser
  implicit val intUser: User[Int] = new User[Int] {
    override var sum: Int = 0

    override def add(a: Int, b: Int): Int = {
      sum += a + b
      sum
    }
  }

  def main(args: Array[String]): Unit = {
    println(sum(List("a", "b"))(stringUser))
    println(sum(List("c", "d"))(stringUser0))
    println(sum(List(Integer.valueOf(1), Integer.valueOf(2))))
    println(sum(List(1, 2)))
    println("hello, world..")
  }
}
