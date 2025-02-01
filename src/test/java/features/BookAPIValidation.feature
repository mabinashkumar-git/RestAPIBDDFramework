Feature: Validating addition, deletion of book

@AddBook @Regression
Scenario Outline: Verify if addition of book is successful

	Given Add "bookAPI" Payload "<Name>" "<ISBN>" "<AISLE>" "<Author>"	
	When user calls "addBookAPI" API with "POST" http request
	Then the API call is success with status code is "200"	
	And "Msg" in response body is "successfully added"
#	Then "bookAPI" created is validated using "getBookAPIByAuthorName" for "<Name>"
#	Then "bookAPI" created is validated using "getBookAPIByID"
	
	Examples:
	|    Name              |    ISBN   |    AISLE   |       Author        |
	| RestAPI-Automation   |    ABCD   |    EFGH    | Deloitte Innovation |


@AddBook @Regression
Scenario Outline: Verify if addition of book is successful

	Given Add "bookAPI" Payload for "<Name>" "<ISBN>" "<AISLE>" "<Author>"
	When user calls "addBookAPI" API with "POST" http request
	Then the API call is success with status code is "200"
	And "Msg" in response body is "successfully added"
#	Then "bookAPI" created is validated using "getBookAPIByAuthorName" for "<Name>"
#	Then "bookAPI" created is validated using "getBookAPIByID"

	Examples:
	|    Name   |    ISBN   |    AISLE   |    Author   |
	|    name   |    isbn   |    aisle   |    author   |