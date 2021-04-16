Feature: Create and Get a Customer

  Scenario: When valid customer details are passed should store the details in Db
    Given Customer provides its first name "Abhishek" and surname "Rajput"
    When The customer makes a call to store the details
    Then The API should return the Customer Data with Id

  Scenario: When a valid customer id is passed should return the customer data
    Given Customer provides a valid customer id "1000"
    When The customer makes a call to get the customer details
    Then The API should return the associated Customer Data
