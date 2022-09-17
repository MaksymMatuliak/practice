package errors

object PocketCalculator {
  def add(x: Int, y: Int): Int = {
    val result = x + y
    if (x > 0 && y > 0 && result < 0) {
      throw new OverflowException
    }  else if(x < 0 && y < 0 && result > 0) {
      throw new UnderflowException
    } else {
      x + y
    }
  }

  def subtract(x: Int, y: Int): Int = {
    val result = x - y
    if (x < 0 && y > 0 && result > 0) {
      throw new UnderflowException
    }  else if(x > 0 && y < 0 && result < 0) {
      throw new OverflowException
    } else {
      x - y
    }
  }

  def multiply(x: Int, y: Int): Int = {
    if (x > 0 && y > 0 && Int.MaxValue / x < y) {
      throw new OverflowException
    } else if(x > 0 && y < 0 && Int.MaxValue / x < -y) {
      throw new UnderflowException
    } else if (x < 0 && y > 0 && Int.MaxValue / y < -x) {
      throw new UnderflowException
    } else if(x < 0 && y > 0 && Int.MaxValue / y < -x) {
      throw new UnderflowException
    } else {
      x * y
    }
  }

  def divide(x: Int, y: Int): Int = {
    if (y != 0) {
      x / y
    } else {
      throw new MathCalculationException
    }
  }
}
