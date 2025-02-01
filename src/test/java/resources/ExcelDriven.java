package resources;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
//import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Utils;

public class ExcelDriven extends Utils  {
	
	@Test
	public void addBook() throws IOException
	{
		DataDriven d = new DataDriven();
		ArrayList data = d.getData("RestAddBook","RestAssured");

		System.out.println("Data 1 -:" + data.get(0));
		System.out.println("Data 2 -:" + data.get(1));
		System.out.println("Data 3 -:" + data.get(2));
		System.out.println("Data 4 -:" + data.get(3));
		
		HashMap<String, Object>  map = new HashMap<String, Object>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));


		RestAssured.baseURI="http://216.10.245.166";
		Response resp=given().log().all().header("Content-Type","application/json").body(map).
		when().post("/Library/Addbook.php").
		then().log().all().statusCode(200).extract().response();

		String response = resp.toString();
		System.out.println("Response :" + response);

		String id = getJsonpathValue(resp, "ID");
		System.out.println("ID :" + id);;
		

	}
	
	


}
