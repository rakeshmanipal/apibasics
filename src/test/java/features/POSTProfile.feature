Feature:
  Verify POST operation using Rest Assured

  Scenario: Verify Post Operation
    Given I perform Post Operation for "posts"

  Scenario: Verify Post Operation for Profile
    Given I perform Post Operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sams | 2       |
    Then I should see the response has name as "Sams"