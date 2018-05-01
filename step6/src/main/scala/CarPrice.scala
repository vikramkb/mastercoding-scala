import types.strategy.{DiscountStrategy, NoStrategy, SimpleStrategy}
import money.Money
import types.Types.{DiscountSubCategory, DiscountCategory}

object CarPrice {
  def totalPrice(price: Money, discountCategories: Seq[(DiscountCategory, DiscountSubCategory)], categoryDiscountStrategy: Map[DiscountCategory, Map[DiscountSubCategory, DiscountStrategy]]): Money = {
    price - discountCategories
      .foldLeft(Money(0))((result, categoryPair) => {
        val discountStrategy = categoryDiscountStrategy.getOrElse(categoryPair._1, Map())
          .getOrElse(categoryPair._2, NoStrategy())
        discountStrategy.apply(price) + result
      })
  }
}