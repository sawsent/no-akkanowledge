package com.akkamelo.actor.client.domain.dto

import com.akkamelo.actor.client.domain.state.TransactionType

case class TransactionDTO(value: Int, transactionType: TransactionType, description: String)
