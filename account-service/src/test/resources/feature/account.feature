Feature: Create and Get a Account

  Scenario: When a customer passes an valid customer id and request to create account
    Given Customer provides a valid customer id "1000"
    When The customer makes a call to create an account
    Then The API should return the account and customer details and status 201

  Scenario: When a customer passes an valid account number
    Given Customer provides a account number "1111"
    When The customer makes a call to get account details
    Then The API should return the account details and status 200