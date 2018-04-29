import CarType.CarType
import FuelType.FuelType

object Car {

  val carTypeDiscount: (CarType, Double) => Double = (carType, price) => carType match {
    case CarType.HATCHBACK => price - price * 1 / 100
    case CarType.SEDAN => price - price * 1.5 / 100
    case CarType.SUV => price - price * 2 / 100
  }

  val fuelTypeDiscount: (FuelType, Double, Double) => Double = (fuelType, initialPrice, price) => fuelType match {
    case FuelType.DIESEL =>
      if(initialPrice <= 500000) price - 10000 else price - 30000
    case FuelType.PETROL =>
      if(initialPrice <= 300000) price - 5000 else price - 10000
  }

  val totalPrice: (CarType, FuelType, Double) => Double = (carType, fuelType, initialPrice) => {
    val discountedPrice = carTypeDiscount(carType, initialPrice)
    fuelTypeDiscount(fuelType, initialPrice, discountedPrice)
  }
}