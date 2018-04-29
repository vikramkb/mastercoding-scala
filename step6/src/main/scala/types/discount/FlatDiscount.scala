package types.discount

import money.Money

class FlatDiscount(val discountAmount: Money) extends DiscountType{
  override def calculate(price: Money): Money = {
    price - discountAmount
  }
}

object FlatDiscount {
  def apply(discountAmount: Money): FlatDiscount = new FlatDiscount(discountAmount)
}


