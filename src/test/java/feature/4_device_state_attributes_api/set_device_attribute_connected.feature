Feature: Change the name, brightness and color of the connected device

  Scenario Outline: Change the brightness of connected device
    Given API end points
    And   you are connected to a device
    When  set brightness "<value>" using post operation
    Then  brightness should change for connected device
    And   verify "<attribute>" is updated to "<value>" for the state
    Examples:
      |attribute|| value |
      |brightness| |6.0|

  Scenario Outline: Change the name of connected device
    Given API end points
    And   you are connected to a device
    When  set name "<value>" using post operation
    Then  name should change for connected device
    And   verify "<attribute>" is updated to "<value>" for the state
    Examples:
      |attribute|| value |
      |name| |foobar|

