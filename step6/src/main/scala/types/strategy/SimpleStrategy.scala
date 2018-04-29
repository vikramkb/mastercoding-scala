package types.strategy

import types.discount.{DiscountType, FlatDiscount}
import money.Money

class SimpleStrategy(val discountType: DiscountType) extends DiscountStrategy{
  override def apply(price: Money): Money = {
    discountType.calculate(price)
  }
}

object SimpleStrategy {
  def apply(discountType: DiscountType) = new SimpleStrategy(discountType)
}

