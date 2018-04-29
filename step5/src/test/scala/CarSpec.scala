import org.scalatest.FunSpec

class CarSpec extends FunSpec {
  describe("Car") {
    describe("total price") {

      val dieselDiscountBreak = List((500000.0, Car.flatDiscount(10000.0)(_)),(Double.MaxValue, Car.flatDiscount(30000)(_)))

      val categoryDiscountMap : Map[String, Map[String, Double => Double]] =
        Map(
          ("FUELTYPE", Map(("DIESEL", Car.priceBreakStrategy(dieselDiscountBreak)))),
          ("CARTYPE", Map(("HATCHBACK", Car.simpleStrategy(Car.percentageDiscount(1.0)))))
        )

      it("test should apply hatchback discount and diesel type upper range discounts") {
        val discountCategories = List(("CARTYPE","HATCHBACK"),("FUELTYPE", "DIESEL"))
        val price = Car.totalPrice(600000, discountCategories, categoryDiscountMap)
        assert(564000.0 == price)
      }
    }
  }
}
