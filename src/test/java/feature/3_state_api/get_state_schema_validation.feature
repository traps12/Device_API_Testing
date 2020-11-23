Feature: Schema validation for state API

  Scenario: Json schema validation for state API
    Given state API endpoint
    And   device is connected
    When  I perform get state of connected device
    Then  response structure should match with json schema of state API