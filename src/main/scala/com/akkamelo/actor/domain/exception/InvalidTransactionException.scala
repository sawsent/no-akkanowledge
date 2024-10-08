package com.akkamelo.actor.domain.exception

case class InvalidTransactionException(message: String) extends RuntimeException(message)
