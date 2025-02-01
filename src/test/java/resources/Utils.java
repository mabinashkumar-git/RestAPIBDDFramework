package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification request;
	
	public RequestSpecification requestSpecification() throws IOException {
		
//		if(request == null) {
//			
//		}
//		return request;
		
		/* Here all the output will be logged in logging.txt */
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt")); 
		
		request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).
				addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)). //This statement is to print all the values or queries
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		return request;
		
	}
	
public RequestSpecification requestSpecifications(String payLoadType) throws IOException {
	PrintStream log = new PrintStream(new FileOutputStream("logging-" + payLoadType + ".txt"));
	
	if(payLoadType.equalsIgnoreCase("placeAPI")) {
		
		request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).
				addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)). //This statement is to print all the values or queries
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		
	}
	else if(payLoadType.equalsIgnoreCase("webAPI")) {
		
		request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL1")).
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		
	}
	else if(payLoadType.equalsIgnoreCase("bookAPI")) {
		
		request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL2")).
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		
	}
	
	return request;		
	}
	
	public static String getGlobalValue(String globalValue) throws IOException {
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties");
		property.load(fis);
		String GlobalValue = property.getProperty(globalValue);
		return GlobalValue;
		
	}
	
	public String getJsonpathValue(Response response, String key) {
		
		String responses = response.asString();
		JsonPath js = new JsonPath(responses);
		String Response = js.get(key).toString();
		return Response;
		
	}
	

}
