package types.strategy

import money.Money

trait DiscountStrategy {
  def apply(price: Money) : Money
}