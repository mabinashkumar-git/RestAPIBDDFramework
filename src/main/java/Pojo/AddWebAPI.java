package Pojo;

import java.util.List;

public class AddWebAPI {
	
	private String jobId;
	private String jobTitle;
	private String jobDescription;
	private List<String> experience;
	private List<ProjectDetail> project;
	

	public List<ProjectDetail> getProject() {
		return project;
	}

	public void setProject(List<ProjectDetail> project) {
		this.project = project;
	}

	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getJobDescription() {
		return jobDescription;
	}
	
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	public List<String> getExperience() {
		return experience;
	}
	
	public void setExperience(List<String> experience) {
		this.experience = experience;
	}

}
