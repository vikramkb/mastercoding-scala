import CarType.CarType
import FuelType.FuelType

object CarPrice {

  val percentage: (Double, Double) => Double = (number, part) => {
    val whole = 100
    number * (part / whole)
  }

  def carTypeDiscount(carType: CarType, price: Double, discountMap: Map[CarType, Double]): Double = {
    price - percentage(price, discountMap.getOrElse(carType, 0))
  }

  def fuelTypeDiscount(fuelType: FuelType, initialPrice: Double, price: Double, discountMap: Map[FuelType, List[(Double, Double)]]): Double = {
    val priceBreaks = discountMap.getOrElse(fuelType, List())
    price - priceBreaks.find(initialPrice <= _._1).map(x => x._2).getOrElse(0.0)
  }

  def totalPrice(carType: CarType, fuelType: FuelType, initialPrice: Double, carTypeDiscountMap: Map[CarType, Double] = Map(), fuelTypeDiscountMap: Map[FuelType, List[(Double, Double)]] = Map()): Double = {
    val discountedPrice = carTypeDiscount(carType, initialPrice, carTypeDiscountMap)
    fuelTypeDiscount(fuelType, initialPrice, discountedPrice, fuelTypeDiscountMap)
  }

  def main(args: Array[String]): Unit = {
    val dieselDiscountBreak: List[(Double, Double)] = List((500000.0, 10000.0), (Double.MaxValue, 30000))
    val petrolDiscountBreak: List[(Double, Double)] = List((300000.0, 5000.0), (Double.MaxValue, 10000))

    val fuelTypeDiscountMap: Map[FuelType, List[(Double, Double)]] = Map((FuelType.DIESEL, dieselDiscountBreak), (FuelType.PETROL, petrolDiscountBreak))
    val carTypeDiscountMap: Map[CarType, Double] = Map((CarType.HATCHBACK, 1.0), (CarType.SEDAN, 1.5))

    val price = CarPrice.totalPrice(CarType.HATCHBACK, FuelType.DIESEL, 600000, carTypeDiscountMap, fuelTypeDiscountMap)
    //prints 5,64,000
    println(price)
  }

}