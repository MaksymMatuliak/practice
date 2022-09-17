import errors.{MathCalculationException, OverflowException, PocketCalculator, UnderflowException}
import list.{Cons, EmptyList}

object Main {
  def main(args: Array[String]): Unit = {
    val firstList = Cons(1, EmptyList)
    println(firstList.toString)
    val secondList = firstList.add(2)
    val thirdList = secondList.add(3)
    val fourthList = thirdList.add(2)
    val fifthList = fourthList.add(1)
    println(thirdList.toString)

    println(thirdList.filter((item: Int) => {
      item % 2 == 0
    }))
    println(thirdList.map((item: Int) => {
      item * 2
    }))

    val calculator = PocketCalculator
    try {
      println(calculator.add(100, Int.MaxValue))
    } catch {
      case e: OverflowException => println("Too much")
    }

    try {
      println(calculator.subtract(-11, Int.MaxValue))
    } catch {
      case e: UnderflowException => println("Too much")
    }

    try {
      println(calculator.divide(100, 0))
    } catch {
      case e: MathCalculationException => println("Don't use zero")
    }

    def getOutOfMemory(x: String, accumulator: String = ""): String = {
      getOutOfMemory(accumulator ++ "OutOfMemory", accumulator + x)
    }

    //getOutOfMemory("100")

    def getStackOverflow(x: Int = 0): Int = {
      getStackOverflow() + 0
    }

    //getStackOverflow()

    def concatenator: (String, String) => String = (v1: String, v2: String) => {
      v1 + v2
    }

    println(concatenator("Maks", "Hello"))

    val specialFunction: Int => (Int => Int) = (v1: Int) => {
      (v2: Int) => {
        v1 + v2
      }
    }

    println(specialFunction(1)(2))


    fifthList.foreach(x => println(x + " - This is from foreach!!!"))

    println(fifthList.sort((a, b) => a - b))


    val firstListPlus100 = Cons(100, EmptyList)
    val secondListPlus100 = firstListPlus100.add(200)
    val thirdListPlus100 = secondListPlus100.add(300)
    val fourthListPlus100 = thirdListPlus100.add(200)
    val fifthListPlus100 = fourthListPlus100.add(100)
    println(fifthList.zipWith(fifthListPlus100, _ + " - " + _))

    println(fifthList.fold(1)(_ + _))

    def toCurry(function: (Int, Int) => Int): (Int => (Int => Int)) = {
      x => y => (function(x, y))
    }

    def fromCurry(function: Int => Int => Int): (Int, Int) => Int = {
      (x, y) => function(x)(y)
    }

    def compose[A, B, T](f: A => B, g: T => A): T => B = {
      x => f(g(x))
    }

    def andThen[A, B, C](f: A => B, g: B => C): A => C = {
      x => g(f(x))
    }

    def superAdder: (Int => Int => Int) = toCurry((a, b) => a + b)
    def add4 = superAdder(4)
    println(add4(6))

    val simpleAdder = fromCurry(superAdder)
    println(simpleAdder(4, 18))

    val add2 = (x: Int) => x + 2
    val times2 = (x: Int) => x * 2
    val composedFunction = compose(add2, times2)
    println(composedFunction(100))

    // all combinations between 2 lists

    val list1 = List(1, 2, 3)
    val list2 = List("a", "b", "c")
    val list3 = List("black", "white")

    println(list1.flatMap(number => list2.flatMap(character =>
        list3.map(color => s"$number - $character - $color"))))

    val combinations = for {
      number <- list1
      character <- list2
      color <- list3
    } yield (s"$number - $character - $color")

    println(combinations)

    var counter = 0;
    val test = for {
      x <- fifthList
    } yield (counter = counter + 1)

    println(counter)
    
    val phoneBook = Map(
      "Jim" -> 34535,
      "Marry" -> 45336,
      "JIM" -> 4535
    )
    println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  }
}
