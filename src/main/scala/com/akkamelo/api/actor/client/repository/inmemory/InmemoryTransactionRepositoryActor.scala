package com.akkamelo.api.actor.client.repository.inmemory

import com.akkamelo.api.actor.client.domain.state.Transaction
import com.akkamelo.api.actor.client.repository.inmemory.InmemoryTransactionRepositoryActor.GetAllWithClientId
import com.akkamelo.core.actor.repository.BaseRepositoryActor.{PersistenceOperation, PersistenceOperationResponse}
import com.akkamelo.core.actor.repository.inmemory.BaseInmemoryRepositoryActor

object InmemoryTransactionRepositoryActor {
  case class GetAllWithClientId(clientId: Int) extends PersistenceOperation
  case class GetAllWithClientIdOperationSuccess(override val op: GetAllWithClientId, transactionList: List[Transaction]) extends PersistenceOperationResponse(op)
}

class InmemoryTransactionRepositoryActor extends BaseInmemoryRepositoryActor[Transaction] {

  def receive(mMap: Map[Int, Transaction]): Receive = super.behaviour(mMap).orElse(extraBehaviour(mMap))

  def extraBehaviour(mMap: Map[Int, Transaction]): Receive = {
    case op: GetAllWithClientId =>
  }
}
