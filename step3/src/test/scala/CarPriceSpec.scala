import CarType.CarType
import FuelType.FuelType
import org.scalatest.FunSpec

class CarPriceSpec extends FunSpec {
  describe("Car") {
    describe("total price") {

      val dieselDiscountBreak: List[(Double, Double)] = List((500000.0, 10000.0),(Double.MaxValue, 30000))
      val petrolDiscountBreak: List[(Double, Double)] = List((300000.0, 5000.0), (Double.MaxValue, 10000))

      val fuelTypeDiscountMap : Map[FuelType, List[(Double, Double)]] = Map((FuelType.DIESEL, dieselDiscountBreak), (FuelType.PETROL, petrolDiscountBreak))
      val carTypeDiscountMap : Map[CarType, Double] = Map((CarType.HATCHBACK, 1.0), (CarType.SEDAN, 1.5))


      it("test should apply hatchback discount for diesel type upper range cars") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.DIESEL, 600000, carTypeDiscountMap, fuelTypeDiscountMap)
        assert(price == 564000)
      }

      it("test should return same price in case no discounts applicable") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.DIESEL, 600000)
        assert(price == 600000)
      }
    }
  }

}
