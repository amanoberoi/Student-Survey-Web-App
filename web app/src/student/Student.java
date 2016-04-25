//This file contains all the getters and setters for the form fields.
// Method to get city and state from ZIP
// Auto complete feature implementation for likelihood

package student;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student {

	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String contactNumber;
	private String email;
	private Date dateOfSurvey;
	private Date startDate;
	private String[] like;
	private String interest;
	private String likelihood;
	private String Raffle;
	private String comments;
	private String temp;
	private String eName1;
	private String eNo1;
	private String eId1;
	private String eName2;
	private String eNo2;
	private String eId2;
	
	public Student()
	{
		
	}
	
	public Student(
			String firstName,
			String lastName,
			String streetAddress,
			String city,
			String state,
			String zip,
			String contactNumber,
			String email,
			Date dateOfSurvey,
			Date startDate,
			String temp,
			String interest,
			String likelihood,
			String Raffle,
			String comments,
			String eName1,
			String eNo1,
			String eId1,
			String eName2,
			String eNo2,
			String eId2
			)
		{
			this.firstName=firstName;
			this.lastName=lastName;
			this.streetAddress=streetAddress;
			this.city=city;
			this.state=state;
			this.zip=zip;
			this.contactNumber=contactNumber;
			this.email=email;
			this.dateOfSurvey=dateOfSurvey;
			this.startDate=startDate;
			this.temp=temp;
			this.interest=interest;
			this.likelihood=likelihood;
			this.Raffle=Raffle;
			this.comments=comments;
			this.eName1=eName1;
			this.eNo1=eNo1;
			this.eId1=eId1;
			this.eName2=eName2;
			this.eNo2=eNo2;
			this.eId2=eId2;

		}
		
			
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfSurvey() {
		return dateOfSurvey;
	}
	public void setDateOfSurvey(Date dateOfSurvey) {
		this.dateOfSurvey = dateOfSurvey;
	}
	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public String getOptions() {
		return Arrays.toString(like);
	}
	
	
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String[] getLike() {
		return like;
	}
	public void setLike(String[] like) {
		this.like = like;
		temp=getOptions();
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getLikelihood() {
		return likelihood;
	}
	public void setLikelihood(String likelihood) {
		this.likelihood = likelihood;
	}
	public String getRaffle() {
		return Raffle;
	}
	public void setRaffle(String raffle) {
		Raffle = raffle;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

	


	public String geteName1() {
		return eName1;
	}

	public void seteName1(String eName1) {
		this.eName1 = eName1;
	}

	public String geteNo1() {
		return eNo1;
	}

	public void seteNo1(String eNo1) {
		this.eNo1 = eNo1;
	}

	public String geteId1() {
		return eId1;
	}

	public void seteId1(String eId1) {
		this.eId1 = eId1;
	}

	public String geteName2() {
		return eName2;
	}

	public void seteName2(String eName2) {
		this.eName2 = eName2;
	}

	public String geteNo2() {
		return eNo2;
	}

	public void seteNo2(String eNo2) {
		this.eNo2 = eNo2;
	}

	public String geteId2() {
		return eId2;
	}

	public void seteId2(String eId2) {
		this.eId2 = eId2;
	}






	private static final String likelihoodString = "Very Likely,Likely,Unlikely";
	private static final String[] likelihoodArray = likelihoodString.split(",");
	
	public List<String> completeLikelihood(String likelihoodPrefix) {
		List<String> matches = new ArrayList<String>();
		for(String possibleLikelihood: likelihoodArray) {
			if(possibleLikelihood.toUpperCase().startsWith(likelihoodPrefix.toUpperCase())) {
				matches.add(possibleLikelihood);
			}
		}
		
		return(matches);
	}
	
	public String getZip() {
		return zip;
	}
	
	
	public Map<String,String> getZipMap()
	{
		Map<String,String> cityState = new HashMap<String,String>();
		
		cityState.put("22312","Alexandria,VA");
		cityState.put("22030","Fairfax,VA");
		cityState.put("22301","Tysons Corner,MD");
		cityState.put("20148","Ashburn,VA");
		
		return cityState;
	}

	public void setZip(String zip) {
		if(zip.length()==5)
		{
			for(Map.Entry<String,String> zipentry: getZipMap().entrySet())
			{
				if(zipentry.getKey().equals(zip))
				{
					String[] zipCityState = zipentry.getValue().split(",");
					this.city = zipCityState[0];
					this.state = zipCityState[1];
					break;
				}
				else
				{
					this.city="";
					this.state="";
				}
			}
		}
		
		this.zip = zip;
	}
		
	
}
