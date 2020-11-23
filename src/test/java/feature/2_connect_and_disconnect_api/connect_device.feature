Feature: Connect to a device: POST `/connect`

  Scenario: connect to device for given ip
    Given API endpoint
    And   disconnect device using post operation
    When  connect to device using post operation
    Then  device should get connected
    And   response should contains success true message



