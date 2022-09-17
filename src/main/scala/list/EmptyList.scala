package list

case object EmptyList extends MyAbstractList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyAbstractList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing] (num: B): MyAbstractList[B] = {
    Cons(num, EmptyList)
  }

  def map[B] (transformer: Nothing => B): MyAbstractList[B] = EmptyList

  override def filter(predicate: Nothing => Boolean): MyAbstractList[Nothing] = {
    EmptyList
  }

  override def foreach(function: Nothing => Unit): Unit = ()

  override def sort(function: (Nothing, Nothing) => Int): MyAbstractList[Nothing] = {
    EmptyList
  }

  override def zipWith[B, C](list: MyAbstractList[B],
                          function: (Nothing, B) => C): MyAbstractList[C] = {
    if (!list.isEmpty) throw RuntimeException("Length probably is not the same")
    else EmptyList
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = {
    start
  }

  override def toString: String = "empty"
}
