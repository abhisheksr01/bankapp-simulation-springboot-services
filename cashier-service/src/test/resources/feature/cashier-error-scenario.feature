Feature: Error Scenarios

  Scenario: When a non existing customer id is passed
    Given Customer provides a non existing account number and transaction details
    When The cashier makes a call to the service to process the transaction
    Then The API should return error "Account Number not found, Unable to process the transaction" and status code 404
