Feature: Error Scenarios

  Scenario: When a non existing customer id is passed
    Given Customer provides a non existing customer id "2000"
    When The customer makes a call to create an account
    Then The API should return error "Customer not found" and status code 404

  Scenario: When a long number is passed
    Given Customer provides a invalid customer id "100000000000000"
    When The customer makes a call to create an account
    Then The API should return error "Bad Request, invalid input" and status code 400

  Scenario: When a long number is passed
    Given Customer provides a invalid account number "123321400000000"
    When The customer makes a call to get account details
    Then The API should return error "Bad Request, invalid input" and status code 400

  Scenario: When a non existing account number is passed
    Given Customer provides a account number "2222"
    When The customer makes a call to get account details
    Then The API should return error "Account Number not found" and status code 404
