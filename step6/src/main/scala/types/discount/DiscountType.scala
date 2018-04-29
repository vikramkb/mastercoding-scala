package types.discount

import money.Money

trait DiscountType {
  def calculate(price: Money) : Money
}
