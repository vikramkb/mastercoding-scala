
object CarPrice {
  val totalPrice: (Int, Int, Double) => Double = (carType, fuelType, initialPrice) => {
    var totalPrice = initialPrice
    //discount based on the car type
    if (carType == 0) {
      totalPrice -= totalPrice * 1 / 100
    } else if (carType == 1) {
      totalPrice -= totalPrice * 1.5 / 100
    } else {
      totalPrice -= totalPrice * 2 / 100
    }

    //discount based on the fuel type
    if (fuelType == 0) {
      if (initialPrice <= 500000) {
        totalPrice -= 10000
      } else {
        totalPrice -= 30000
      }
    } else if (fuelType == 1) {
      if (initialPrice <= 300000) {
        totalPrice -= 5000
      } else {
        totalPrice -= 10000
      }
    }

    totalPrice
  }

  def main(args: Array[String]): Unit = {
    //0 - HATHBACK, 0 - DIESEL, 600000 - price of car before discount
    println(s"price before discount = 600000")
    val price = totalPrice(0, 0, 600000)
    //prints 564000.0
    println(s"price after discount = $price")
  }
}