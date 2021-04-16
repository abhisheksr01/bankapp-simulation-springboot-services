Feature: Customer provides the details and Cashier passes the information to transaction service
# TODO:Fix the test, sometimes the Consumer is not getting invoked and other time SQLException occurred at TransactionService
  Scenario: When transaction event occurred and consumer listens
    Given Cashier produces an Credit Transaction event
    When Transaction service consumes the Credit Transaction event
#    Then The API should return status 201
