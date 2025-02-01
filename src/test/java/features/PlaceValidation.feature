Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using Add Place API

	Given Add place Payload "<Name>" "<Address>" "<Language>"
	When user calls "addPlaceAPI" API with "POST" http request
	Then the API call is success with status code is "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<Name>" using "getPlaceAPI"
	
	Examples:
	|    Name        |      Address       |    Language      |
	| 	Deloitte USI |     Hyderabad      |    English       |
#	| 	Deloitte US  |      US            |    English       |

	
#@DeletePlace @Regression
#Scenario: Verify if Delete Place functionality is working
#
#	Given DeletePlace Payload
#	When user calls "deletePlaceAPI" API with "POST" http request
#	Then the API call is success with status code is "200"
#	And "status" in response body is "OK"