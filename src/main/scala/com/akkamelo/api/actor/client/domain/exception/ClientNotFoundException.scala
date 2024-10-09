package com.akkamelo.api.actor.client.domain.exception

case class ClientNotFoundException(message: String) extends RuntimeException(message)

