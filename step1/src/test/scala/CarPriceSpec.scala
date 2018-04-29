import org.scalatest.FunSpec

class CarPriceSpec extends FunSpec {
  describe("Car") {
    describe("total price") {
      it("should apply hatchback discount for the diesel type lower price range car") {
        val price = CarPrice.totalPrice(0, 0, 450000)
        assert(price == 435500)
      }

      it("should apply hatchback discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(0, 0, 600000)
        assert(price == 564000)
      }

      it("should apply hatchback discount for the petrol type lower price range car") {
        val price = CarPrice.totalPrice(0, 1, 300000)
        assert(price == 292000)
      }

      it("should apply hatchback discount for the petrol type upper price range car") {
        val price = CarPrice.totalPrice(0, 1, 600000)
        assert(price == 584000)
      }

      it("should apply sedan discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(1, 0, 950000)
        assert(price == 905750)
      }

      it("should apply SUV discount for the diesel type upper price range car") {
        val price = CarPrice.totalPrice(2, 0, 1300000)
        assert(price == 1244000)
      }
    }
  }

}
