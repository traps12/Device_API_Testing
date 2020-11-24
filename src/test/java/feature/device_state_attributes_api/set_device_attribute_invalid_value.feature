Feature: User should not able to change the name, brightness and color with invalid values

  Scenario Outline: Not able to change brightness of device with invaild value
    Given API end points
    And   you are connected to a device
    When  set brightness "<value>" using post operation
    Then  response should contains success false message
    And   verify "<attribute>" is not updated to "<value>" for the state
    Examples:
      |attribute|| value |
      |brightness| |12|
      |brightness| |-1|

  Scenario Outline: Not able to change color of device with invaild values
    Given API end points
    And you are connected to a device
    When  set color "<value>" using post operation
    Then  response should contains success false message
    And   verify "<attribute>" is not updated to "<value>" for the state
    Examples:
      |attribute|| value |
      |color| |#|