package util

object MathExtra {
  def percentage(number: Double, part: Double): Double = {
    val whole = 100
    number * (part / whole)
  }
}
