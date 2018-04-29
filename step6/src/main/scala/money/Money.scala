package money

import util.MathExtra

class Money(val money: Double) {
  def -(m : Money): Money = {
    Money(this.money - m.money)
  }

  def %(per : Double): Money = {
    Money(MathExtra.percentage(money,per))
  }

  def <=(otherMoney : Money): Boolean = {
    money <= otherMoney.money
  }

  override def toString = s"Money($money)"
}

object Money {
  def apply(money: Double) : Money = new Money(money)
}
