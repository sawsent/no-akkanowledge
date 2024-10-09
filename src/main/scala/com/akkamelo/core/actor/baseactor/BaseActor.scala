package com.akkamelo.core.actor.baseactor

import akka.actor.Actor
import com.akkamelo.core.actor.baseactor.BaseActor.{ActorCommand, ActorEvent}
import com.akkamelo.core.actor.baseactor.behaviour.PersistenceActorBehaviour
import com.akkamelo.core.actor.baseactor.domain.state.Model

object BaseActor {
  trait ActorCommand
  trait ActorEvent
}

trait BaseActor[M <: Model, C <: ActorCommand, E <: ActorEvent] extends Actor with PersistenceActorBehaviour[M {

}
