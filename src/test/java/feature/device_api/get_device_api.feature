Feature: Get list all available devices: GET `/devices`

  Scenario: Get list of available devices name and IP
    Given devices API endpoint
    When  I perform GET operation
    Then  I get name and IP of devices

  Scenario: It should not return valid response for invalid Url
    Given devices API endpoint
    When  I perform GET operation with invalid url
    Then   I should get 404 status
   