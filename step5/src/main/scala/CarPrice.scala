object CarPrice {

  type Money = Double
  type Percentage = Double
  type PriceBreak = (Double, DiscountType)
  type DiscountType = Money => Money
  type Category = String
  type SubCategory = String

  def percentage(number : Double, part : Double) : Double = {
    val whole = 100
    number * ( part / whole)
  }

  def flatDiscount(discountAmount: Money)(amount: Money): Money = {
    math.max(discountAmount, percentage(amount, 0.1))
  }

  def percentageDiscount(per: Percentage)(amount: Money): Money = {
    percentage(amount, per)
  }

  def simpleStrategy(discountFn: DiscountType)(price: Money): Money = {
    discountFn(price)
  }
  def priceBreakStrategy(priceBreak: Seq[PriceBreak])(price: Money): Money = {
    priceBreak.find(price <= _._1).map(_._2(price)).getOrElse(price)
  }

  def totalPrice(price: Double, discountCategories : Seq[(Category,SubCategory)], categoryDiscountMap : Map[Category, Map[SubCategory, DiscountType]]) : Money = {
    price - discountCategories
      .foldLeft(0.0)(
        (result, break) =>
          result + categoryDiscountMap.getOrElse(break._1, Map())
            .getOrElse(break._2, (x:Double)=>x)(price)
      )

  }
}