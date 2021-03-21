@Regression
Feature: As the Clerk, I should be able to insert a single record of working class hero into database via an API
#AC1: Single record of a working class hero should consist of Natural Id (natid), Name, Gender, Birthday, Salary and Tax paid

  @APITest
  Scenario: Insert a single record of working class hero
    Given  An API endpoint for Hero Record
    And User set request body with single hero record
    And User send POST HTTP request
    Then User receives HTTP response code "202" for a single Hero record

  @APITest
    Scenario Outline: Insert a record and should consist of Natural Id (natid), Name, Gender, Birthday, Salary and Tax paid
    Given An API endpoint for Hero Record
    And User set request body with "<birthday>", "<gender>", "<name>", "<natid>", "<salary>", "<tax>"
    And User send POST HTTP request
    Then User receive response code "202"
    Examples:
      |birthday|gender|name|natid|salary|tax|
      |14032021|m|HeroM0Yr|HeroM0Yr1|599|59.9|
      |14032003|m|HeroM18Yr|HeroM18Yr1|8989|898.9|
      |14031985|m|HeroM36Yr|HeroM36Yr1|8989|898.9|
      |14031971|m|HeroM50Yr|HeroM50Yr1|8989|898.9|
      |14031946|m|HeroM75Yr|HeroM75Yr1|8989|898.9|
      |14031945|m|HeroM76Yr|HeroM76Yr1|599|59.9|
      |14032021|f|HeroF0Yr|HeroF0Yr1|599|59.9|
      |14032003|f|HeroF18Yr|HeroF18Yr1|8989|898.9|
      |14031985|f|HeroF36Yr|HeroF36Yr1|8989|898.9|
      |14031971|f|HeroF50Yr|HeroF50Yr1|8989|898.9|
      |14031946|f|HeroF75Yr|HeroF75Yr1|8989|898.9|
      |14031945|f|HeroF76Yr|HeroF76Yr1|599|59.9|





