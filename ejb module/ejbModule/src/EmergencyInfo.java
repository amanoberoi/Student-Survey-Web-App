//entity bean that contains emergency contact info
package src;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="emergencyinfo")
public class EmergencyInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String eName1;
	private String eName2;
	private String eNumber1;
	private String eNumber2;
	
	@Id
	private String eId1;
	private String eId2;
	public String geteName1() {
		return eName1;
	}
	public void seteName1(String eName1) {
		this.eName1 = eName1;
	}
	public String geteName2() {
		return eName2;
	}
	public void seteName2(String eName2) {
		this.eName2 = eName2;
	}
	public String geteNumber1() {
		return eNumber1;
	}
	public void seteNumber1(String eNumber1) {
		this.eNumber1 = eNumber1;
	}
	public String geteNumber2() {
		return eNumber2;
	}
	public void seteNumber2(String eNumber2) {
		this.eNumber2 = eNumber2;
	}
	public String geteId1() {
		return eId1;
	}
	public void seteId1(String eId1) {
		this.eId1 = eId1;
	}
	public String geteId2() {
		return eId2;
	}
	public void seteId2(String eId2) {
		this.eId2 = eId2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
