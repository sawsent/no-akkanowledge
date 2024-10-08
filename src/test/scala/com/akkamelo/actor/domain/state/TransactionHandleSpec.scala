package com.akkamelo.actor.domain.state

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class TransactionHandleSpec  extends AnyFlatSpecLike with TableDrivenPropertyChecks with should.Matchers {


  "TransactionHandle" should "apply transactions to Client state" in {

    val victim = TransactionHandle.handle()
    val examples = Table(("description", "client", "transactionCommand", "expectation"),
        (
          "Initial Client 1",
          Client.initial,
          CommandTransactionDTO.empty.copy(id = 1, valor = 100, tipo = "c", descricao = "descricao"),
          Client.initial.copy(id = 1, transactions = transactions ++ Credit(100,"descricao"))
        )
      // Add more cases
      )
    forAll(examples) { (description, client, transactionCommand, expectation) =>
      victim(client,transactionCommand) should be expectation
    }

  }

  //Test if the ID is in the scope of this app. See: docs/api-contracts.md ## Initial Client Register
  "TransactionHandle" should "throws a exception if ID isn't in the app scope (1-5)" in {

    val victim = TransactionHandle.handle()
    val examples = Table(("description", "client", "transactionCommand"),
      (
        "Initial Client 1",
        Client.initial,
        CommandTransactionDTO.empty.copy(id = 11, valor = 100, tipo = "c", descricao = "descricao"),
        AnyRef
      )
      // Add more cases
    )
    forAll(examples) { (description, client, transactionCommand) =>
       assertThrows[ClientNotFound](victim(client,transactionCommand))
    }

  }

}
