package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		
		StepDefination stepdefination = new StepDefination();
		
		if(StepDefination.place_Id == null) {
			
			stepdefination.addpPlacePayLoad("Deloitte USI", "Hyderabad", "English/Hindi");
			stepdefination.userCallsAPIwithHTTPReq("addPlaceAPI", "POST");
			stepdefination.validateAPICallStatusCode(200);
			stepdefination.verifyResponseBodyParameter("Deloitte USI", "getPlaceAPI");
			
		}
		
		
	}
	

}
