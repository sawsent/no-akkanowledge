package com.akkamelo.actor.client.handler

import com.akkamelo.actor.client.domain.dto.TransactionDTO
import com.akkamelo.actor.client.domain.state.{Client, Credit, Debit, TransactionType}
import com.akkamelo.actor.event.TransactionEvent
import com.akkamelo.syntax.ActorEventHandler

object TransactionEventHandler extends ActorEventHandler[Client, TransactionEvent] {

  override def handle(): Handler = {
    case (c1, TransactionEvent(c2, TransactionDTO(value, TransactionType.CREDIT, description))) =>  add Credit(value, description)
    case (c1, TransactionEvent(c2, TransactionDTO(value, TransactionType.DEBIT, description))) => client add Debit(value, description)
  }
}
