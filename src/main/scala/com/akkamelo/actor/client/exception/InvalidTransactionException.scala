package com.akkamelo.actor.client.exception

case class InvalidTransactionException(message: String) extends RuntimeException(message)
