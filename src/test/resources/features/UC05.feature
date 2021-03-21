@Regression
Feature: As the Governor, I should be able to see a button on the screen so that I can dispense tax relief for my working class heroes

  Background:
    Given User launched OppenheimerProject login page in "Web"

  @UITest
  Scenario: AC1: The button on the screen must be red-colored
  When User verify the button on the screen must be red-colored "#dc3545"


  @UITest
  Scenario: AC2: The text on the button must be exactly “Dispense Now”
  When User verify the text on the button must be exactly "Dispense Now"

  @UITest
  Scenario: AC3: After clicking on the button, it should direct me to a page with a text that says “Cash dispensed”
  When User verify after clicking on the button, it should direct me to a page with a text that says "Cash dispensed"
