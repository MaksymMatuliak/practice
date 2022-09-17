package list

abstract class MyAbstractList[+A] {
  def head: A

  def tail: MyAbstractList[A]

  def isEmpty: Boolean

  def add[B >: A] (item: B): MyAbstractList[B]

  def map[B] (transformer: A => B): MyAbstractList[B]
  
  def filter(predicate: A => Boolean): MyAbstractList[A]

  def foreach(function: A => Unit): Unit

  def sort(comparison: (A, A) => Int): MyAbstractList[A]

  def zipWith[B, C] (list: MyAbstractList[B], function:  (A, B) => C): MyAbstractList[C]

  def fold[B](start: B)(operator: (B, A) => B): B

  def toString: String
}
