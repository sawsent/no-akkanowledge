package com.akkamelo.actor.client.exception

case class ClientNotFoundException(message: String) extends RuntimeException(message)

