import org.scalatest.{FlatSpec, FunSpec, Matchers}

import scala.collection.mutable.Stack

class CarPriceSpec extends FunSpec {
  describe("Car") {
    describe("total price") {
      it("should apply hatchback discount for the diesel type lower price range car") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.DIESEL, 450000)
        assert(price == 435500)
      }

      it("should apply hatchback discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.DIESEL, 600000)
        assert(price == 564000)
      }

      it("should apply hatchback discount for the petrol type lower price range car") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.PETROL, 300000)
        assert(price == 292000)
      }

      it("should apply hatchback discount for the petrol type upper price range car") {
        val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.PETROL, 600000)
        assert(price == 584000)
      }

      it("should apply sedan discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(CarType.SEDAN, FuelType.DIESEL, 950000)
        assert(price == 905750)
      }

      it("should apply SUV discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(CarType.SUV, FuelType.DIESEL, 1300000)
        assert(price == 1244000)
      }
    }
  }

}
