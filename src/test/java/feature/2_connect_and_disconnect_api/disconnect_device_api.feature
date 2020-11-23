Feature: Disconnect from any connected device: POST `/disconnect`

Scenario: Disconnect to device for given ip
Given API endpoint
And   connect to device using post operation
When  disconnect device using post operation
Then  device should get disconnected

