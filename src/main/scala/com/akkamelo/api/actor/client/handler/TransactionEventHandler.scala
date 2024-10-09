package com.akkamelo.api.actor.client.handler

import com.akkamelo.api.actor.client.ClientActor.TransactionEvent
import com.akkamelo.api.actor.client.domain.dto.TransactionDTO
import com.akkamelo.api.actor.client.domain.state.{Client, Credit, Debit, TransactionType}
import com.akkamelo.core.actor.baseactor.handler.ActorEventHandler

object TransactionEventHandler extends ActorEventHandler[Client, TransactionEvent] {

  override def handle(): Handler = {
    case (c1, TransactionEvent(c2, TransactionDTO(value, TransactionType.CREDIT, description))) =>  c1 add Credit(value, description)
    case (c1, TransactionEvent(c2, TransactionDTO(value, TransactionType.DEBIT, description))) => c1 add Debit(value, description)
  }
}
