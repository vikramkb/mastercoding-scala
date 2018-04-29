package types.strategy

import money.Money

class NoStrategy() extends DiscountStrategy{
  override def apply(price: Money): Money = {
    price
  }
}

object NoStrategy {
  def apply(): NoStrategy = new NoStrategy()
}




