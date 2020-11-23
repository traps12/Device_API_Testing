Feature: User should not be able to change the name, brightness and color of the not connected device

  Scenario: Change the brightness of non connected device
    Given API end points
    And   disconnect device
    When  set brightness "6.0" using post operation
    Then   response should contains success false message

  Scenario: Change the name of non connected device
    Given API end points
    And   disconnect device
    When  set name "foobar" using post operation
    Then   response should contains success false message

  Scenario: Change the color of non connected device
    Given API end points
    And   disconnect device
    When   set color "#336699" using post operation
    Then response should contains success false message