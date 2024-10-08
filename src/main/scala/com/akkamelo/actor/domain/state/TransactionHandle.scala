package com.akkamelo.actor.domain.state

import com.akkamelo.actor.domain.dto.CommandTransactionDTO

object TransactionHandle {
  def handle(): (Client, CommandTransactionDTO) => Client = (c, t) => {
    c
  }
}

class TransactionHandle {


}
