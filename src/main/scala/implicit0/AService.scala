package implicit0

object AService {

  def sum[A](xs: List[A])(implicit user: User[A]): A = {
    if (xs.isEmpty)
      user.sum
    else
      user.add(xs.head, sum(xs.tail))
  }
}
