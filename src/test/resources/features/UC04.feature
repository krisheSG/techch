@Regression
Feature: As the Bookkeeper, I should be able to query the amount of tax
  relief for each person in the database so that I can report the
  figures to my Bookkeeping Manager

  @APITest
  Scenario: AC1: a GET endpoint which returns a list consist of natid, tax relief amount and name
    Given An API endpoint for Calculator Tax Releif
    Then User verify valid HTTP response code "200" for Calculator Tax Releif

  @APITest
  Scenario:  AC2: natid field must be masked from the 5th character onwards with dollar sign ‘$’
  Given An API endpoint for Calculator Tax Releif
  When User verifies natid field must be masked from the fifth character onwards with dollar sign "$"

  @APITest
  Scenario Outline:  AC3: computation of the tax relief is using the formula as described
  Given An API endpoint for Calculator Tax Releif
  When User verifies tax relief computation values with uploaded data "<birthday>", "<gender>", "<name>", "<natid>", "<salary>", "<tax>"
# Then User verifies tax relief values for each "<name>"
  Examples:
  |birthday|gender|name     |natid    |salary|tax|
  |14032021|m     |HeroM0Yr |HeroM0Yr1|599   |59.9|
  |14032003|m     |HeroM18Yr|HeroM18Yr1|8989|898.9|
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




#    And Response body contains all the valid fields for all the products
#    And User set request body with "<birthday>","<gender>","<name>" and "<password>"
#    And User set request body with "<birthday>","<gender>","<name>", "<natid>", "<salary>" and "<tax>"
#    And User send POST HTTP request
#    Then User receive response code "200"

