package com.akkamelo.syntax

import com.akkamelo.actor.client.domain.state.Model

trait ActorEventHandler[M <: Model, E <: ActorEvent] {
  final type Handler = PartialFunction[(M, E), M]

  def handle(): Handler
}
