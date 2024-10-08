package com.akkamelo.actor.domain.exception

case class ClientNotFoundException(message: String) extends RuntimeException(message)

