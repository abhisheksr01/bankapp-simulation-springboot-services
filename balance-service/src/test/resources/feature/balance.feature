Feature: Balance Service updates the Balance when an update event is received and provides current balance through HTTP Get

  Scenario: When service receives an Balance update event
    Given Transaction service has calculated the balance
    When Balance service consumes the Balance Update event
    Then The current balance should get updated
#
#  Scenario: When the customer requests for current balance
#    Given The customer has provided the account number 1111
#    When Customer makes a call to get the current balance
#    Then Customer should see the current account balance
