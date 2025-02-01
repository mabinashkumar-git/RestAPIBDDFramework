package resources;

import java.util.ArrayList;

import Pojo.AddBookAPI;
import Pojo.AddPlace;
import Pojo.AddWebAPI;
import Pojo.Location;
import Pojo.ProjectDetail;
import Pojo.Project;

public class TestDataBuild {
	
	public static AddPlace addPlacePayload(String name, String address, String language){
		
		AddPlace addplace = new AddPlace();
		
		addplace.setAccuracy(50);
		addplace.setName(name);
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setAddress(address);
		addplace.setWebsite("http://deloitte.com");
		addplace.setLanguage(language);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addplace.setLocation(location);
		
		ArrayList<String> typesList = new ArrayList<String>();
		typesList.add("QA Testing");
		typesList.add("API Testing");
		addplace.setTypes(typesList);
		
		return addplace;
		
	}
	
	/* JSON to POJO converter -> https://json2csharp.com/code-converters/json-to-pojo */	
	public static AddWebAPI addProjectWebAPI(String jobID, String jobTitle, String projectName) {
		AddWebAPI addwebapi = new AddWebAPI();
		Project project = new Project();
		
		addwebapi.setJobId(jobID);
		addwebapi.setJobTitle(jobTitle);
		addwebapi.setJobDescription("API Automation");
		
		ArrayList<String> experienceList = new ArrayList<String>();
		experienceList.add("RestAPI Automation");
		experienceList.add("Selenium Automation");
		experienceList.add("Mobile Automation");
		addwebapi.setExperience(experienceList);
		
		ProjectDetail projectDetail = new ProjectDetail();
		
		ArrayList<String> technologyList = new ArrayList<String>();
		technologyList.add("RestAPI");
		technologyList.add("Karate");
		technologyList.add("ReadyAPI");
		
		projectDetail.setProjectName(projectName);
		projectDetail.setTechnology(technologyList);
		
		ArrayList<ProjectDetail> projectDetailsList = new ArrayList<ProjectDetail>();
		projectDetailsList.add(projectDetail);
		addwebapi.setProject(projectDetailsList);
				
		return addwebapi;
		
	}
	
	public static AddBookAPI addBookAPI(String Name, String ISBN, String AISLE, String Author) {
		AddBookAPI bookAPI = new AddBookAPI();
		bookAPI.setName(Name);
		bookAPI.setIsbn(ISBN);
		bookAPI.setAisle(AISLE);
		bookAPI.setAuthor(Author);
		
		return bookAPI;
	}
	
	public static String deletePlacePayload(String  placeId ) {
		
		return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
	
	}

public static String addProjectAPI(String jobID, String jobTitle, String projectName) {
	return "{\r\n"
			+ "        \"jobId\": " + jobID + ",\r\n"
			+ "        \"jobTitle\": \"" + jobTitle + "\",\r\n"
			+ "        \"jobDescription\": \"To develop andriod application\",\r\n"
			+ "        \"experience\": [\r\n"
			+ "            \"Google\",\r\n"
			+ "            \"Apple\",\r\n"
			+ "            \"Mobile Iron\"\r\n"
			+ "        ],\r\n"
			+ "        \"project\": [\r\n"
			+ "            {\r\n"
			+ "                \"projectName\": \"" + projectName + "\",\r\n"
			+ "                \"technology\": [\r\n"
			+ "                    \"Kotlin\",\r\n"
			+ "                    \"SQL Lite\",\r\n"
			+ "                    \"Gradle\"\r\n"
			+ "                ]\r\n"
			+ "            }\r\n"
			+ "        ]\r\n"
			+ "    }";
}

}
