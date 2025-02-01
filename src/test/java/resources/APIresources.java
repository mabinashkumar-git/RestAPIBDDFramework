package resources;

/* enum is special class in java which has collections of constants and methods */
public enum APIresources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	addWebAPI("/normal/webapi/add"),
	getWebAPI("/normal/webapi/all"),
	deleteWebAPI("/normal/webapi/remove/"),
	addBookAPI("/Library/Addbook.php"),
	getBookAPIByAuthorName("/Library/GetBook.php"),
	getBookAPIByID("/Library/GetBook.php");
	
	private String resource;

	APIresources(String resource) {
		this.resource = resource;
		
	}
	
	public String getResource() {
		return resource;
		
	}

}
