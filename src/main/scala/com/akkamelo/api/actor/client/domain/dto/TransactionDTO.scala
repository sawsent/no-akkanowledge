package com.akkamelo.api.actor.client.domain.dto

import com.akkamelo.api.actor.client.domain.state.TransactionType

case class TransactionDTO(value: Int, transactionType: TransactionType, description: String)
