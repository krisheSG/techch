@Regression
Feature: As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI

  @UITest
  Scenario: AC1: First row of the csv file must be natid, name, gender, salary, birthday, tax
    Given User verify first row of the csv file must be with "birthday,gender,name,natid,salary,tax"

   @UITest
  Scenario: AC2: Subsequent rows of csv are the relevant details of each working class hero
    Given User verify subsequent rows of csv are the relevant details of each working class hero

  @UITest
    Scenario: AC3: A simple button that allows me to upload a file on my pc to the portal
    Given User launched OppenheimerProject login page in "Web"
    When User upload CSV file input of working class hero
