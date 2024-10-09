package com.akkamelo.core.actor.repository.inmemory

import com.akkamelo.core.actor.baseactor.domain.state.Model
import com.akkamelo.core.actor.repository.BaseRepositoryActor
import com.akkamelo.core.actor.repository.BaseRepositoryActor.{Get, ModelAlreadyExistsException, ModelDoesntExistException, OperationFailure, OperationSuccess, Save, Update}

object BaseInmemoryRepositoryActor {

}

class BaseInmemoryRepositoryActor[M <: Model] extends BaseRepositoryActor[M] {
  import context._

  override def receive: Receive = behaviour(Map.empty[Int, M])

  def behaviour(mMap: Map[Int, M]): Receive = {
    case op: Save[M] =>
      val m = op.model
      if (mMap.contains(m.id)) {
        sender() ! OperationFailure(op, s"Model ${m.getClass.getSimpleName} already exists in mMap, please use update.")
        throw ModelAlreadyExistsException(s"Model ${m.getClass.getSimpleName} already exists in mMap, please use update.")
      }
      become(behaviour(mMap + (m.id, m)))
      sender() ! OperationSuccess(op, m)

    case op: Get[M] =>
      val id = op.id
      if (!mMap.contains(id)) {
        sender() ! OperationFailure(op, s"Model with id $id doesn't exist in mMap.")
        throw ModelDoesntExistException(s"Model with id $id doesn't exist in mMap.")
      }
      sender() ! OperationSuccess(op, mMap(id))
    case op: Update[M] =>
      val m = op.model
      if (!mMap.contains(m.id)) {
        sender() ! OperationFailure(op, s"Model ${m.getClass.getSimpleName} doesnt exist in mMap, please use create.")
        throw ModelDoesntExistException(s"Model ${m.getClass.getSimpleName} doesnt exist in mMap, please use create.")
      }
      become(behaviour(mMap + (m.id, m)))
      sender() ! OperationSuccess(op, m)
  }
}
