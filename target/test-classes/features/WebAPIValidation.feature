Feature: Validating WebAPI

@AddWebAPI @Regression
Scenario Outline: Verify if details is being successfully added using Add Web API

	Given Add "webAPI" Payload "<jobID>" "<jobTitle>" "<projectName>"
	When user calls "addWebAPI" API with "POST" http request
	Then the API call is success with status code is "201"
	And "jobId" in response body is "<jobID>"
	And "webAPI" created for "jobTitle" is "<jobTitle>" using "getWebAPI" is validated
	Then "webAPI" is deleted by "deleteWebAPI" call for "<jobID>" with "200" status code
	Then validate deleted "webAPI" by "deleteWebAPI" call for "<jobID>" with "404" status code
	
	Examples:
	|    jobID   |      jobTitle       |    projectName         |
	| 	  5714   |     QA Automation   |    Deloitte USI        |
	| 	  5715   |     SDET            |    Deloitte US         |
