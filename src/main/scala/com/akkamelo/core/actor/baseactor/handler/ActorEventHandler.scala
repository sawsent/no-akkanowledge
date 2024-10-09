package com.akkamelo.core.actor.baseactor.handler

trait ActorEventHandler[M, E] {
  type Handler = PartialFunction[(M, E), M]

  def handle(): Handler
}
