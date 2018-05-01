package types.discount

import money.Money

class PercentageDiscount(val percentageDiscount: Double) extends DiscountType{
  override def calculate(price: Money): Money = {
    price % percentageDiscount
  }
}

object PercentageDiscount {
  def apply(percentageDiscount: Double): PercentageDiscount = new PercentageDiscount(percentageDiscount)
}


