Feature:  Schema validation for device API response

  Scenario: Json schema validation for device API
    Given devices API endpoint
    When  I perform GET operation
    Then  response structure should match with json schema


