package com.akkamelo.actor.domain.dto

import com.akkamelo.actor.domain.state.TransactionType

object CommandTransactionDTO {
  val empty: CommandTransactionDTO = new CommandTransactionDTO(0, 0, TransactionType.NO_TYPE, "desc")
}

case class CommandTransactionDTO(clientId: Int, value: Int, transactionType: TransactionType, description: String)
