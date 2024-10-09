package com.akkamelo.core.actor.repository

import akka.actor.{Actor, Props}
import com.akkamelo.core.actor.baseactor.domain.state.Model

object BaseRepositoryActor {
  def props[M <: Model]: Props = Props[BaseRepositoryActor[M]]

  class PersistenceOperation
  case class Save[M <: Model](model: M) extends PersistenceOperation
  case class Get[M <: Model](id: Int) extends PersistenceOperation
  case class Update[M <: Model](model: M) extends PersistenceOperation

  class PersistenceOperationResponse(val op: PersistenceOperation)
  case class OperationFailure[M <: Model](override val op: PersistenceOperation, reason: String) extends PersistenceOperationResponse(op)
  case class OperationSuccess[M <: Model](override val op: PersistenceOperation, model: M) extends PersistenceOperationResponse(op)

  case class ModelAlreadyExistsException[M <: Model](message: String) extends RuntimeException(message)
  case class ModelDoesntExistException[M <: Model](message: String) extends RuntimeException(message)

}

trait BaseRepositoryActor[M <: Model] extends Actor {
  import context._
}
