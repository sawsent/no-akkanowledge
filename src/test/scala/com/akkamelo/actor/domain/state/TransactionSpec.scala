package com.akkamelo.actor.domain.state

import org.scalatest.flatspec.AnyFlatSpec

class TransactionSpec extends AnyFlatSpec {

  "A Transaction" should "must be a Debit or a Credit in" {
    val debit = Debit(100,"Description")
    val debitIsATransaction = debit match {
      case t:Transaction => true
      case _ => false
    }
    assert(debitIsATransaction)

    val credit = Credit(100,"Description")
    val creditIsATransaction = credit match {
      case t:Transaction => true
      case _ => false
    }
    assert(creditIsATransaction)
  }

  it should "must have a positive value" {
    assertThrows[InvalidTransactiontException] {
      Debit(100, "Description")
    }
    val credit = Credit(200, "Description")
    assert(credit.value == 200)
  }

  it should "must have a description with size between 1 and 10" {
    assertThrows[InvalidTransactiontException] {
      Debit(100, "")
    }

    assertThrows[InvalidTransactiontException] {
      Debit(100, "12345678901")
    }

    val credit = Credit(200, "1234567890")
    assert(credit.description == "1234567890")
  }

}
