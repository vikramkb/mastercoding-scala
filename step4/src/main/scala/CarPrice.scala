object CarPrice {

  type discountType = Double => Double

  val percentage: (Double, Double) => Double = (number, part) => {
    val whole = 100
    number * ( part / whole)
  }

  def flatDiscount(discountAmount: Double)(amount: Double): Double = {
    amount - discountAmount
  }

  def percentageDiscount(per: Double)(amount: Double): Double = {
    amount - percentage(amount, per)
  }

  def simpleStrategy(discountFn: discountType)(price: Double): Double = {
    discountFn(price)
  }

  def priceBreakStrategy(priceBreak: Seq[(Double, Double => Double)])(price: Double): Double = {
    priceBreak.find(price <= _._1).map(_._2(price)).getOrElse(price)
  }

  def totalPrice(price: Double, discountCategories : Seq[(String,String)], categoryDiscountMap : Map[String, Map[String, Double => Double]]) : Double = {
    discountCategories
      .foldLeft(price)(
        (result, break) =>
        categoryDiscountMap.getOrElse(break._1, Map())
          .getOrElse(break._2, (x:Double)=>x)(result)
      )
  }
}