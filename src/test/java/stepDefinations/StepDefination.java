package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.DataDriven;
import resources.TestDataBuild;
import resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;


public class StepDefination extends Utils {
	
	private RequestSpecification response;
	private ResponseSpecification responses;
	private Response Response;
	private Response Responsses;
	private int StatusCode;
	private String Responses;
	public static String place_Id;
	private static String id;
	
	JsonPath js = new JsonPath(Responses);

	DataDriven d = new DataDriven();
	
	@Given("^Add place Payload \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void addpPlacePayLoad(String name, String address, String language) throws Throwable {
        
		/* Command for request */
		response = given().log().all().spec(requestSpecification()).
				body(TestDataBuild.addPlacePayload(name, address, language));
		
		/* Command for response */
		String Response = response.when().post("/maps/api/place/add/json").
				then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println("Response :" + Response);
		
		responses = new ResponseSpecBuilder().
//				expectStatusCode(StatusCode).expectContentType(ContentType.JSON).build();
		expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
    }
	
	@Given("Add {string} Payload {string} {string} {string}")
    public void addWebAPIPayload(String payLoadType, String jobID, String jobTitle, String projectName) throws Throwable {
        
		/* Command for request */
		response = given().log().all().spec(requestSpecifications(payLoadType)).
				body(TestDataBuild.addProjectWebAPI(jobID, jobTitle, projectName));
		
		String Response = response.when().post("/normal/webapi/add").
				then().assertThat().log().all().statusCode(201).extract().response().asString();
		System.out.println("Response :" + Response);
		
		responses = new ResponseSpecBuilder().
		expectStatusCode(201).expectContentType(ContentType.JSON).build();	
    }
	
	
	@Given("Add {string} Payload {string} {string} {string} {string}")
	public void add_Payload(String payLoadType, String Name, String ISBN, String AISLE, String Author) throws IOException {
		int random_int = (int)Math.floor((100) * Math.random());
		String ISBN_new = ISBN+Integer.toString(random_int);
		String AISLE_new = AISLE+Integer.toString(random_int);
		id = ISBN_new + AISLE_new;
		System.out.println("ID :" + id);
		
		/* Command for request */
		response = given().log().all().spec(requestSpecifications(payLoadType)).
				body(TestDataBuild.addBookAPI(Name, ISBN+random_int, AISLE+random_int, Author));
		
		String Response = response.when().post("/Library/Addbook.php").
				then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println("Response :" + Response);
		
		responses = new ResponseSpecBuilder().
		expectStatusCode(200).expectContentType(ContentType.JSON).build();	
    }

	@Given("Add {string} Payload for {string} {string} {string} {string}")
	public void addPayloadFromExcel(String payLoadType, String Name, String ISBN, String AISLE, String Author) throws IOException {
		ArrayList data = d.getData(getGlobalValue("TestCaseName"),getGlobalValue("TestSheetName"));

		int random_int = (int)Math.floor((100) * Math.random());
		String ISBN_new = data.get(2) + Integer.toString(random_int);
		String AISLE_new = data.get(3) + Integer.toString(random_int);
		id = ISBN_new + AISLE_new;
		System.out.println("ID :" + id);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Name, data.get(1));
		map.put(ISBN, ISBN_new);
		map.put(AISLE, AISLE_new);
		map.put(Author, data.get(4));

		/* Command for request */
		response = given().log().all().spec(requestSpecifications(payLoadType)).
				body(map);

		String Response = response.when().post("/Library/Addbook.php").
				then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println("Response :" + Response);

		responses = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}
	

    @When("^user calls \"([^\"]*)\" API with \"([^\"]*)\" http request$")
    public void userCallsAPIwithHTTPReq(String resource, String methodType) throws Throwable {
    	
    	APIresources resourceAPI = APIresources.valueOf(resource);
    	String APIResource = resourceAPI.getResource();
    	System.out.println("API Resource :" + APIResource);
    	
    	if (methodType.equalsIgnoreCase("POST")) {
    		Response = response.when().post(APIResource);
    	}
    	
    	else if(methodType.equalsIgnoreCase("GET")) {
    		Response = response.when().get(APIResource);
    	}
    	
    }

    @Then("^the API call is success with status code is \"([^\"]*)\"$")
    public void validateAPICallStatusCode(int statusCode) throws Throwable {
        this.StatusCode = statusCode;
    	Responsses = Response.then().log().all().spec(responses).extract().response();
    	Responses = Responsses.asString();
    	System.out.println("*****************************************");
    	System.out.println("Responses :" + Responses);
    	System.out.println("*****************************************");
    	
    	assertEquals(Responsses.getStatusCode(), statusCode);
        
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void statusOfResponseBody(String Status, String ExpectedStatusValue) throws Throwable {
        
 //       System.out.println(js.get(Status));
 //       assertEquals(js.get(Status).toString(),ExpectedStatusValue);
    	assertEquals(getJsonpathValue(Responsses, Status),ExpectedStatusValue);
        
    }
    
    @And("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verifyResponseBodyParameter(String ExpectedName, String resource) throws Throwable {
        
    	place_Id = getJsonpathValue(Responsses, "place_id");
    	System.out.println("Place_Id :" + place_Id);
    	response = given().log().all().spec(requestSpecification()).queryParam("place_id", place_Id);
    	
    	userCallsAPIwithHTTPReq(resource, "GET");
    	validateAPICallStatusCode(200);
    	String ActualName = getJsonpathValue(Responsses, "name");            //video -> 107
    	assertEquals(ExpectedName, ActualName);
    	
    }
    
    @And("{string} created for {string} is {string} using {string} is validated")
    public void verifyResponseBodyParameters(String payLoadType, String responseKey, String ExpectedTitle, 
    		String resource) throws Throwable {
    	
    	String ActualTitle = getJsonpathValue(Responsses, responseKey);            //video -> 107
    	assertEquals(ExpectedTitle, ActualTitle);
    	
    	response = given().log().all().spec(requestSpecifications(payLoadType));
    	responses = new ResponseSpecBuilder().
    			expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	userCallsAPIwithHTTPReq(resource, "GET");
//    	Response.then().log().all().spec(responses).extract().response();
    	Responsses = Response.then().log().all().spec(responses).extract().response();
    	assertEquals(Responsses.getStatusCode(), 200);
  	
    }
    
    
    @Then("{string} created is validated using {string} for {string}")
    public void validateResponse(String payLoadType, String resource, String authorName) throws Throwable {
    	response = given().log().all().spec(requestSpecifications(payLoadType)).queryParam("AuthorName", authorName);
    	responses = new ResponseSpecBuilder().
    			expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	userCallsAPIwithHTTPReq(resource, "GET");
    	Responsses = Response.then().log().all().spec(responses).extract().response();
    	assertEquals(Responsses.getStatusCode(), 200);
    }
    
    @Then("{string} created is validated using {string}")
    public void validateResponseByParameter(String payLoadType, String resource) throws Throwable {
    	response = given().log().all().spec(requestSpecifications(payLoadType)).queryParam("ID", id);
    	responses = new ResponseSpecBuilder().
    			expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	userCallsAPIwithHTTPReq(resource, "GET");
    	Responsses = Response.then().log().all().spec(responses).extract().response();
    	assertEquals(Responsses.getStatusCode(), 200);
    }
    
    
    @Given("^DeletePlace Payload$")
    public void deleteplacepayload() throws Throwable {
    	
    	given().log().all().spec(requestSpecification()).
    	body(TestDataBuild.deletePlacePayload(place_Id));
    	
    }
    
    @Then("{string} is deleted by {string} call for {string} with {string} status code")
    public void deleteAPIPayLoad(String payLoadType, String resource, String jobID, String statusCode) throws IOException {
    	response = given().log().all().spec(requestSpecifications(payLoadType));
    	responses = new ResponseSpecBuilder().
    			expectStatusCode(Integer.parseInt(statusCode)).expectContentType(ContentType.JSON).build();
    	
    	APIresources resourceAPI = APIresources.valueOf(resource);
    	String APIResource = resourceAPI.getResource();
    	System.out.println("API Resource :" + APIResource);
    	Response = response.when().delete(APIResource + jobID);
    	Response.then().log().all().spec(responses).extract().response();
    	Response deleteResponsses = Response.then().log().all().spec(responses).extract().response();
    	assertEquals(deleteResponsses.getStatusCode(), Integer.parseInt(statusCode));
    	
    }
    
    @Then("validate deleted {string} by {string} call for {string} with {string} status code")
    public void validateDeletedWebAPI(String payLoadType, String resource, String jobID, String statusCode) throws IOException {
    	deleteAPIPayLoad(payLoadType, resource, jobID, statusCode);
    }

    

}
