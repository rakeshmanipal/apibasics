Feature: PUT Posts
  Test the PUT Operation

  Scenario: Verify PUT operation after POST
    Given I ensure Perform POST operation for "/posts" with body as
      | id | title              | author |
      | 6  | API Testing Course | Rakesh |
    And I Perform PUT operation for "/posts/{postid}/"
      | id | title       | author |
      | 6  | API Testing | Rakesh |
    And I perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 6      |
    Then I "should" see the body with title as "API Testing"