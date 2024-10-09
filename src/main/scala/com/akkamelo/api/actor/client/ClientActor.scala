package com.akkamelo.api.actor.client

import com.akkamelo.api.actor.client.domain.dto
import com.akkamelo.api.actor.client.domain.dto.{CommandTransactionDTO, TransactionDTO}
import com.akkamelo.api.actor.client.domain.state.{Client, TransactionType}
import com.akkamelo.core.syntax.ActorEvent

object ClientActor {
  def props(client: Client): Props = Props(new ClientActor(client))

  case class TransactionCommand()
  case class TransactionEvent(client: Client, transactionDTO: TransactionDTO) extends ActorEvent
}

class ClientActor(val client: Client) extends Actor {

  override def receive: Receive = {
    case CommandTransactionDTO(clientId, value, transactionType: TransactionType, description) =>
      // get client
      // create transactionDTO
      val transactionDTO = dto.TransactionDTO(value, transactionType, description)



  }
}
