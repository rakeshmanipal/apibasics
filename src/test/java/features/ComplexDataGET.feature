Feature: Complex data GET
  Verify complex DATA GET operation using POJO

  @smoke
  Scenario: Verify GET operation for Complex data
    Given I perform Authenticate operation for "/auth/login" with body
      | email            | password |
      | nilson@email.com | temp123  |
    And I perform GET operation with path parameter for address "/location"
      | id |
      | 1  |
    Then I should see the street name as "1st street" for the "primary" address