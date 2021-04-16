Feature: Customer provides the details and Cashier passes the information to transaction service

  Scenario: When a customer provides a valid account number and transaction details
    Given Customer provides a valid account number and transaction details
    When The cashier makes a call to the service to process the transaction
    Then The API should return status 201
