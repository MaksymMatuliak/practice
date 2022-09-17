package list

case class Cons[+A] (h: A, t: MyAbstractList[A]) extends MyAbstractList[A] {
  override def head: A = h

  override def tail: MyAbstractList[A] = t

  override def isEmpty: Boolean = false

  def map[B] (transformer: A => B): MyAbstractList[B] = {
    Cons(transformer(h), t.map(transformer))
  }

  override def add[B >: A] (item: B): MyAbstractList[B] = {
    val list = Cons(item, this)
    list
  }

  override def filter(predicate: A => Boolean): MyAbstractList[A] = {
    if (predicate(head)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def foreach(function: A => Unit): Unit = {
    function(head)
    t.foreach(function)
  }

  override def sort(comparison: (A, A) => Int): MyAbstractList[A] = {
    def insert(x: A, sortedList: MyAbstractList[A]): MyAbstractList[A] = {
      if (sortedList.isEmpty) Cons(x, EmptyList)
      else if (comparison(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(comparison)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyAbstractList[B], function: (A, B) => C): MyAbstractList[C] = {
    if (list.isEmpty) throw RuntimeException("Length probably is not the same")
    else Cons(function(head, list.head), tail.zipWith(list.tail, function))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start, h)
    t.fold(newStart)(operator)
  }

  override def toString = s"Cons($head, $tail)"
}
