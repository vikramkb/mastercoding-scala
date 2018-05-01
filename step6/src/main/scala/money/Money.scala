package money

import util.MathExtra

class Money(val money: Double) {

  def + (otherMoney : Double): Money = {
    Money(this.money + otherMoney)
  }

  def + (otherMoney : Money): Money = {
    Money(this.money + otherMoney.money)
  }

  def -(m : Money): Money = {
    Money(this.money - m.money)
  }

  def %(per : Double): Money = {
    Money(MathExtra.percentage(money,per))
  }

  def <=(otherMoney : Money): Boolean = {
    money <= otherMoney.money
  }

  def max(otherMoney: Money): Money = {
    Money(math.max(money, otherMoney.money))
  }

  override def toString = s"Money($money)"
}

object Money {
  def apply(money: Double) : Money = new Money(money)
}
