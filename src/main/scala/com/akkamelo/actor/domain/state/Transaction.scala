package com.akkamelo.actor.domain.state

import com.akkamelo.actor.domain.exception.{InvalidTransactionException, TransactionConversionException}

object Transaction {
  def validate(value: Int, description: String, transactionType: TransactionType): Transaction = {
    if (value < 0) throw InvalidTransactionException("Transaction with negative value cannot exist")
    if (description.isEmpty || description.length > 10) throw InvalidTransactionException("Description length must have length between 1 and 10 characters")
    if (transactionType == TransactionType.NO_TYPE) throw InvalidTransactionException("Transaction type must be specified.")
    new Transaction(value, description, transactionType)
  }

}

class Transaction(val value: Int, val description: String, transactionType: TransactionType) {
  implicit def toCredit: Credit = transactionType match {
    case TransactionType.CREDIT => new Credit(value, description)
    case _ => throw TransactionConversionException("Can't convert debit transaction to credit transaction.")
  }

  def toDebit: Debit = transactionType match {
    case TransactionType.DEBIT => new Debit(value, description)
    case _ => throw TransactionConversionException("Can't convert credit transaction to debit transaction.")
  }
}

object Credit {
  def apply(value: Int, description: String): Credit = Transaction.validate(value, description, TransactionType.CREDIT).toCredit

}
case class Credit(override val value: Int, override val description: String) extends Transaction(value, description, TransactionType.CREDIT)

object Debit {
  def apply(value: Int, description: String): Debit = Transaction.validate(value, description, TransactionType.DEBIT).toDebit
}
case class Debit(override val value: Int, override val description: String) extends Transaction(value, description, TransactionType.DEBIT)


