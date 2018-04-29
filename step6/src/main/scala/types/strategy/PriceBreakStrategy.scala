package types.strategy

import types.discount.DiscountType
import money.Money

class PriceBreakStrategy(val priceBreaks: Seq[(Money, DiscountType)]) extends DiscountStrategy {
  override def apply(price: Money): Money = {
    val priceBreak = priceBreaks.find(price <= _._1)
    priceBreak.map(_._2.calculate(price)).getOrElse(price)
  }
}

object PriceBreakStrategy {
  def apply(priceBreaks: Seq[(Money, DiscountType)]) = new PriceBreakStrategy(priceBreaks)
}

