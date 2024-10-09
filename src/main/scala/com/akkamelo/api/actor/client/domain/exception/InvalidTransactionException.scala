package com.akkamelo.api.actor.client.domain.exception

case class InvalidTransactionException(message: String) extends RuntimeException(message)
