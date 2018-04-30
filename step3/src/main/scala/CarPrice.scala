import CarType.CarType
import FuelType.FuelType

import scala.annotation.tailrec

object CarPrice {

  val percentage: (Double, Double) => Double = (number, part) => {
    val whole = 100
    number * ( part / whole)
  }

  def carTypeDiscount(carType: CarType, price: Double, discountMap: Map[CarType, Double]): Double = {
    price - percentage(price, discountMap.getOrElse(carType, 0))
  }

  def fuelTypeDiscount(fuelType: FuelType, initialPrice: Double, price: Double, discountMap: Map[FuelType, List[(Double, Double)]]): Double = {
    val priceBreaks = discountMap.getOrElse(fuelType, List())
    price - priceBreaks.find(initialPrice <= _._1).map(x => x._2).getOrElse(0.0)
  }

  def totalPrice(carType : CarType, fuelType: FuelType, initialPrice: Double, carTypeDiscountMap: Map[CarType, Double] = Map(), fuelTypeDiscountMap: Map[FuelType, List[(Double, Double)]] = Map()): Double = {
    val discountedPrice = carTypeDiscount(carType, initialPrice, carTypeDiscountMap)
    fuelTypeDiscount(fuelType, initialPrice, discountedPrice, fuelTypeDiscountMap)
  }

}