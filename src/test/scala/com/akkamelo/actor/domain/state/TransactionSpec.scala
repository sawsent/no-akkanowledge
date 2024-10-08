package com.akkamelo.actor.domain.state

import com.akkamelo.actor.domain.exception.InvalidTransactionException

import org.scalatest.flatspec.AnyFlatSpec

class TransactionSpec extends AnyFlatSpec {

  "A Transaction" should "be a Debit or a Credit in" {
    val debit = Debit(100,"desc")
    val debitIsATransaction = debit match {
      case t: Transaction => true
      case _ => false
    }
    assert(debitIsATransaction)

    val credit = Credit(100,"desc")
    val creditIsATransaction = credit match {
      case t: Transaction => true
      case _ => false
    }
    assert(creditIsATransaction)
    0
  }

  it should "must have a positive value" {
    assertThrows[InvalidTransactionException] {
      Debit(-100, "desc")
    }
    assertThrows[InvalidTransactionException] {
      Credit(-100, "desc")
    }
    val credit = Credit(200, "desc")
    assert(credit.value == 200)
    0
  }

  it should "must have a description with size between 1 and 10" {
    assertThrows[InvalidTransactionException] {
      Debit(100, "")
    }

    assertThrows[InvalidTransactionException] {
      Debit(100, "12345678901")
    }

    val credit = Credit(200, "1234567890")
    assert(credit.description == "1234567890")
    0
  }

}
