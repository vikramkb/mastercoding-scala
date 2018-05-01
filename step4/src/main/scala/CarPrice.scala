object CarPrice {

  type DiscountType = Double => Double

  def percentage(number : Double, part : Double) : Double = {
    val whole = 100
    number * ( part / whole)
  }

  def flatDiscount(discountAmount: Double)(amount: Double): Double = {
    math.max(discountAmount, percentage(amount, 0.1))
  }

  def percentageDiscount(per: Double)(amount: Double): Double = {
    percentage(amount, per)
  }

  def simpleStrategy(discountFn: DiscountType)(price: Double): Double = {
    discountFn(price)
  }

  def priceBreakStrategy(priceBreak: Seq[(Double, DiscountType)])(price: Double): Double = {
    priceBreak.find(price <= _._1).map(_._2(price)).getOrElse(price)
  }

  def totalPrice(price: Double, discountCategories : Seq[(String,String)], categoryDiscountMap : Map[String, Map[String, DiscountType]]) : Double = {
    price - discountCategories
      .foldLeft(0.0)(
        (result, break) =>
          result + categoryDiscountMap.getOrElse(break._1, Map())
          .getOrElse(break._2, (x:Double)=>x)(price)
      )
  }

  def main(args: Array[String]): Unit = {
    def discount(discountFn : Double => Double, price: Double) : Double = {
      price - discountFn(price)
    }

    val flatDiscountFn = CarPrice.flatDiscount(10000.0)(_)
    //prints 10000
    println(flatDiscountFn(600000))
    //prints 590000
    println(discount(flatDiscountFn, 600000))

    val percentageDiscountFn = CarPrice.percentageDiscount(10.0)(_)
    //prints 60000
    println(percentageDiscountFn(600000))
    //prints 540000
    println(discount(percentageDiscountFn, 600000))

    val simpleStrategyFlatDiscountFn = simpleStrategy(flatDiscountFn)(_)
    //prints 10000
    println(simpleStrategyFlatDiscountFn(600000))
    val simpleStrategyPercentageDiscountFn = simpleStrategy(percentageDiscountFn)(_)
    //prints 60000
    println(simpleStrategyPercentageDiscountFn(600000))

    val dieselDiscountBreak = List((500000.0, flatDiscountFn),(Double.MaxValue, percentageDiscountFn))

    val priceBreakStrategyFn = priceBreakStrategy(dieselDiscountBreak)(_)
    //prints 10000
    println(priceBreakStrategyFn(400000))
    //prints 60000
    println(priceBreakStrategyFn(600000))

    val categoryDiscountMap : Map[String, Map[String, Double => Double]] =
      Map(
        ("FUELTYPE", Map(("DIESEL", priceBreakStrategyFn))),
        ("CARTYPE", Map(("HATCHBACK", simpleStrategyPercentageDiscountFn)))
      )
    val discountCategories = List(("CARTYPE","HATCHBACK"),("FUELTYPE", "DIESEL"))

    val discountedPrice1 = totalPrice(400000, discountCategories, categoryDiscountMap)
    //print 350000
    println(discountedPrice1)
    val discountedPrice2 =totalPrice(600000, discountCategories, categoryDiscountMap)
    //print 480000
    println(discountedPrice2)
  }
}