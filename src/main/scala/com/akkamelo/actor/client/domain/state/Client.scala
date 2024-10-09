package com.akkamelo.actor.client.domain.state

import com.akkamelo.actor.client.exception.InvalidTransactionException
import java.time.LocalDateTime


object Client {
  val initial: Client = Client(0, List.empty[Transaction], 0)
}

case class Client(id: Int, transactions: List[Transaction], limit: Int) extends Model {
  def add(transaction: Transaction): Client = {
    transaction match {
      case c: Credit =>
      case d: Debit => if (balance - d.value < -1 * limit) throw InvalidTransactionException("Can't debit to lower than limit")
    }
    this.copy(id, transaction +: transactions, limit)
  }

  def getStatement: Statement = {
    val balanceInformation = BalanceInformation(balance, limit, LocalDateTime.now())
    val lastTransactions = transactions.slice(0, 10)
    Statement(balanceInformation, lastTransactions)
  }

  private def balance: Int = {
    transactions.foldRight(0)((t: Transaction, acc: Int) => t match {
      case Credit(value, _, _) => acc + value
      case Debit(value, _, _) => acc - value
    })
  }

}
