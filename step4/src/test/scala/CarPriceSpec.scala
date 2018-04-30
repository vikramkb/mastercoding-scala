import org.scalatest.FunSpec

class CarPriceSpec extends FunSpec {
  describe("Car") {
    describe("total price") {

      val dieselDiscountBreak = List((500000.0, CarPrice.flatDiscount(10000.0)(_)),(Double.MaxValue, CarPrice.flatDiscount(30000)(_)))

      val categoryDiscountMap : Map[String, Map[String, Double => Double]] =
        Map(
          ("FUELTYPE", Map(("DIESEL", CarPrice.priceBreakStrategy(dieselDiscountBreak)))),
            ("CARTYPE", Map(("HATCHBACK", CarPrice.simpleStrategy(CarPrice.percentageDiscount(1.0)))))
        )

      it("test should apply hatchback discount and diesel type upper range discounts") {
        val discountCategories = List(("CARTYPE","HATCHBACK"),("FUELTYPE", "DIESEL"))
        val price = CarPrice.totalPrice(600000, discountCategories, categoryDiscountMap)
        assert(564000.0 == price)
      }
    }
  }

}
