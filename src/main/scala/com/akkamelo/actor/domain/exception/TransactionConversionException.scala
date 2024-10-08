package com.akkamelo.actor.domain.exception

case class TransactionConversionException(message: String) extends RuntimeException(message)
