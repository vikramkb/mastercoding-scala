import types.strategy.{DiscountStrategy, PriceBreakStrategy, SimpleStrategy}
import types.discount.{FlatDiscount, PercentageDiscount}
import money.Money
import org.scalatest.FunSpec

class CarPriceSpec extends FunSpec {
  describe("Car") {
    describe("total price") {

      val dieselDiscountBreak = Seq((Money(500000.0), FlatDiscount(Money(10000.0))),(Money(Double.MaxValue), FlatDiscount(Money(30000))))

      val categoryDiscountMap : Map[String, Map[String, DiscountStrategy]] =
        Map(
          ("FUELTYPE", Map(("DIESEL", PriceBreakStrategy(dieselDiscountBreak)))),
          ("CARTYPE", Map(("HATCHBACK", SimpleStrategy(PercentageDiscount(10.0))
        ))))

      it("test should apply hatchback discount and diesel type upper range discounts") {
        val discountCategories = List(("CARTYPE","HATCHBACK"),("FUELTYPE", "DIESEL"))
        val price = CarPrice.totalPrice(Money(600000.0), discountCategories, categoryDiscountMap)
        assert(510000.0 == price.money)
      }
    }
  }
}
