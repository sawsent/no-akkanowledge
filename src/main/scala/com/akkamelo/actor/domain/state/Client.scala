package com.akkamelo.actor.domain.state

import com.akkamelo.actor.domain.exception.InvalidTransactionException

import java.time.LocalDateTime


object Client {
  val initial: Client = Client(0, List.empty[Transaction], 0)
}

case class Client(id: Int, transactions: List[Transaction], limit: Int) {
  def add(transaction: Transaction): Client = {
    transaction match {
      case d: Debit => verifyDebit(d)
      case c: Credit =>
    }
    this.copy(id, transaction +: transactions, limit)
  }

  def getStatement: Statement = {
    val balanceInformation = BalanceInformation(money, limit, LocalDateTime.now())
    val lastTransactions = transactions.slice(0, 10)
    Statement(balanceInformation, lastTransactions)
  }

  private def money: Int = {
    transactions.foldRight(0)((t: Transaction, acc: Int) => t match {
      case Credit(value, _, _) => acc + value
      case Debit(value, _, _) => acc - value
    })
  }

  private def verifyDebit(debit: Debit): Unit = {
    if (money - debit.value < -1 * limit) throw InvalidTransactionException("Insufficient Funds")
  }

}
