//entity bean to store form data, contains mapping and joining of tables where necessary
package src;

import java.util.List;

import javax.persistence.*;

@Entity(name = "stusurvey")
public class StuSurvey implements java.io.Serializable{
	/**
	 *
	 */
	
	private static final long serialVersionUID = 1L;
	String fname;
	String lname;
	String street;
	String city;
	String state;
	String zip;
	String contact;
	@Id
	String email;
	String d1;
	String d2;
	String temp;
	String interest;
	String likelihood;
	String raffle;
	String eName;
	String eContactNumber;
	String eEmail;
	String comments;
	
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "student_emergency_info" , joinColumns = {
			@JoinColumn(name="email")} , inverseJoinColumns = {
			@JoinColumn(name="eId1")})
	private List<EmergencyInfo> emergencyinfo;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
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
		return raffle;
	}
	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteContactNumber() {
		return eContactNumber;
	}
	public void seteContactNumber(String eContactNumber) {
		this.eContactNumber = eContactNumber;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<EmergencyInfo> getEmergencyinfo() {
		return emergencyinfo;
	}
	public void setEmergencyinfo(List<EmergencyInfo> emergencyinfo) {
		this.emergencyinfo = emergencyinfo;
	}
	
	
	
}
