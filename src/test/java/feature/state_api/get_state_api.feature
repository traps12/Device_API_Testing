Feature: Get the state of a device: GET `/state`
  Scenario: Get State of connected device
    Given state API endpoint
    And  device is connected
    When  I perform get state of connected device
    Then  get state information about device

  Scenario: User should not be able to get state of disconnected device
    Given state API endpoint
    And   device is disconnected
    When  I perform get state of connected device
    Then  response from state api should contains success false message