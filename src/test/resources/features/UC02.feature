@Regression
Feature: As the Clerk, I should be able to insert more than one working class hero into database via an API

  @APITest
  Scenario: AC1: Enhancement of (1), with the ability to insert a list
    Given  An API endpoint for Hero Record
    And User set request body with multiple hero records
    And User send POST HTTP request
    Then User receive response code "202"


#  Scenario: POST Payment Service Negative
#    Given  An API endpoint for Hero Record
#    And User set invalid request body
#    And User send POST HTTP request
#    Then User receive HTTP response code "400"


