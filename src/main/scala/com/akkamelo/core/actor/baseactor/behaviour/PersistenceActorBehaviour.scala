package com.akkamelo.core.actor.baseactor.behaviour

import akka.actor.Actor
import com.akkamelo.core.actor.baseactor.BaseActor.{ActorCommand, ActorEvent}
import com.akkamelo.core.actor.baseactor.domain.state.Model

trait PersistenceActorBehaviour[M <: Model, P <: Persistor[M]] {

}
