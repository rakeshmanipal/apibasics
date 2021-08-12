
 Feature:
   Verify get operation using Rest Assured

   Scenario: Verify one author of the post
     Given I perform GET Operation for "posts"
     Then I should see the author name as "Karthik KK"

   Scenario: Verify collection of authors in the post
     Given I perform GET Operation for "posts"
     Then I should see the authors name

   Scenario: Verify Parameter of Get
     Given I perform GET Operation for "posts"
     Then I should see verify Get Parameter

  @Smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform Authenticate operation for "/auth/login" with body
    |email|password|
    |nilson@email.com|temp123|
    Given I perform GET Operation for "/posts/1"
    Then I should see the author name as "Karthik KK"

   @Smoke
   Scenario: Verify GET operation with bearer authentication token for Json Schema validation
     Given I perform Authenticate operation for "/auth/login" with body
       |email|password|
       |nilson@email.com|temp123|
     Given I perform GET Operation for "/posts/1"
     Then I should see the author name as "Karthik KK" with json validation
