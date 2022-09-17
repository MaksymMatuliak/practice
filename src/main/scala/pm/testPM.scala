package pm

object testPM extends App {
  def show(e: Expression): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Production(e1, e2) => {
      def maybeShowParentheses(e: Expression) = e match {
        case Sum(_, _) => s"(${show(e)})"
        case _ => show(e)
      }

      s"${maybeShowParentheses(e1)} * ${maybeShowParentheses(e2)}"
    }
  }

  println(show(Sum(Number(1), Number(2))))
  println(show(Production(Sum(Number(1), Number(2)), Number(100))))
}
