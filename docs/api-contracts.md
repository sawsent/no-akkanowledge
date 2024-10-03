# No-Akkanowledge API Contracts

This file outlines the endpoints, request formats, and response formats used in the **No-Akkanowledge** API.

---

## Transactions Endpoint

### **Request**

`POST /clientes/[id]/transacoes`
```json
{
    "valor": 1000,
    "tipo" : "c",
    "descricao" : "descricao"
}
```

Where:
- `[id]` (URL) is an integer representing the client ID.
- `valor` is a positive integer representing the transaction amount in cents (e.g., 1000 represents R$ 10).
- `tipo` can be `c` (credit) or `d` (debit).
- `descricao` is a string between 1 to 10 characters long.

All fields are required.

### **Response**

`HTTP 200 OK`
```json
{
    "limite" : 100000,
    "saldo" : -9098
}
```

Where:
- `limite` is the registered credit limit of the client.
- `saldo` is the new balance after the transaction is processed.

### **Rules**
- Debit transactions **cannot** reduce the client’s balance below the allowed credit limit. If the debit would result in an inconsistent balance, return `HTTP 422 Unprocessable Entity`.
- If the payload fails to meet the specifications (e.g., incorrect `tipo`, `valor`, or `descricao` length), return `HTTP 422`.
- If the client ID does not exist, return `HTTP 404 Not Found`.

---

## Balance Statement Endpoint

### **Request**

`GET /clientes/[id]/extrato`

Where:
- `[id]` (URL) is an integer representing the client ID.

### **Response**

`HTTP 200 OK`
```json
{
  "saldo": {
    "total": -9098,
    "data_extrato": "2024-01-17T02:34:41.217753Z",
    "limite": 100000
  },
  "ultimas_transacoes": [
    {
      "valor": 10,
      "tipo": "c",
      "descricao": "descricao",
      "realizada_em": "2024-01-17T02:34:38.543030Z"
    },
    {
      "valor": 90000,
      "tipo": "d",
      "descricao": "descricao",
      "realizada_em": "2024-01-17T02:34:38.543030Z"
    }
  ]
}
```

Where:
- `saldo` contains:
    - `total`: Current balance.
    - `data_extrato`: Timestamp when the statement was generated.
    - `limite`: The client’s credit limit.
- `ultimas_transacoes` is an array of up to 10 most recent transactions, ordered by `realizada_em`, containing:
    - `valor`: Transaction amount.
    - `tipo`: Transaction type (`c` for credit, `d` for debit).
    - `descricao`: Description from the transaction.
    - `realizada_em`: Timestamp of the transaction.

### **Rules**
- If the client ID does not exist, return `HTTP 404 Not Found`.
