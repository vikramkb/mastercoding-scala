object Car {

  type Money = Double
  val percentage: (Double, Double) => Double = (number, part) => {
    val whole = 100
    number * ( part / whole)
  }

  def flatDiscount(discountAmount: Money)(amount: Money): Money = {
    amount - discountAmount
  }

  type Percentage = Double
  def percentageDiscount(per: Percentage)(amount: Money): Money = {
    amount - percentage(amount, per)
  }

  type DiscountType = Money => Money
  def simpleStrategy(discountFn: DiscountType)(price: Money): Money = {
    discountFn(price)
  }

  type PriceBreak = (Double, DiscountType)
  def priceBreakStrategy(priceBreak: Seq[PriceBreak])(price: Money): Money = {
    priceBreak.find(price <= _._1).map(_._2(price)).getOrElse(price)
  }

  type Category = String
  type SubCategory = String
  def totalPrice(price: Double, discountCategories : Seq[(Category,SubCategory)], categoryDiscountMap : Map[Category, Map[SubCategory, DiscountType]]) : Money = {
    discountCategories.foldLeft(price)((result, break) => categoryDiscountMap.getOrElse(break._1, Map()).getOrElse(break._2, (x:Double) => x)(result))
  }
}