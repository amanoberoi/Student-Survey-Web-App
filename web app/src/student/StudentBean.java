//This is the managed bean containing the class studentbean which contains the Student object model.
//It also contains references to the Student service class that performs all the business logic.
// Additional logic to check for semester date to be after survey date.

package student;


import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;



@ManagedBean(name="studentbean")
@SessionScoped
public class StudentBean {
	
	private Student s=new Student();
	private WinningResult wr = new WinningResult();
	private ArrayList<Student> allSurveys = new ArrayList<Student>();
	private Search sfield = new Search();
	private String list;
	
	
	public String getList() {
		return list;
	}

	

	public void setList(String list) {
		this.list = list;
	}

	
	

	public ArrayList<Student> getAllSurveys() {
		return allSurveys;
	}



	public void setAllSurveys(ArrayList<Student> allSurveys) {
		this.allSurveys = allSurveys;
	}



	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}
	
	public WinningResult getWr() {
		return wr;
	}

	public void setWr(WinningResult wr) {
		this.wr = wr;
	}

	
	
			public Search getSfield() {
		return sfield;
	}



	public void setSfield(Search sfield) {
		this.sfield = sfield;
	}



			public String surveyData()
			{
				
				Date sDate = s.getDateOfSurvey();
				Date semDate = s.getStartDate();
				
				System.out.println(sDate);
				System.out.println(semDate);
				
				if (semDate.before(sDate))
				{
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage errorMessage= new FacesMessage("Semester start date must be after survey date.");
					errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage("form:startDate", errorMessage);
					return(null);
				}
				
				else
				{
				
				StudentService ss = new StudentService();
				String RaffleData = s.getRaffle();
				
				double mean,stddev;
				
				mean=ss.calculateMean(RaffleData);
				stddev=ss.calculateStddev(RaffleData,mean);
				
				wr.setMean(mean);
				wr.setStddev(stddev);
				
					
				try{
					try {
						ss.StoreStudentSurvey(s);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					}
				catch (SQLException e) {
					e.printStackTrace();
					}
					
				
				if(mean<90)
					return "SimpleAcknowledgement";
				else
					return "WinnerAcknowledgement";
				
				}			
		}
			
	
	
	
	public String AlphaOrder() throws IOException, SQLException{
	
		allSurveys = new ArrayList<Student>();
		
		 StudentService ss= new StudentService();
			String query = "asc";
			allSurveys =ss.retrieveStudentSurvey(query);
			
		return "ListSurvey";
		
	}
	
	
	public void Delete(String delete) throws IOException, SQLException, NamingException {
		
		
		System.out.println("Delete Reached!");
		
		allSurveys = new ArrayList<Student>();
		 StudentService ss= new StudentService();
		 ss.delete(delete);
		 CustomQuery();
			 
	}

	
	
	public String CustomQuery() throws IOException, SQLException{
		 StudentService ss= new StudentService();
		  allSurveys = new ArrayList<Student>();
			String lname= sfield.getLastName();
			String fname = sfield.getFirstName();
			String city = sfield.getCity();
			String state = sfield.getState();
			int flag=0;
			
			String query = "";
			if(!lname.equals("")){
				if(lname.endsWith("*")){
				query += " s.lname like '"+lname.substring(0, lname.length()-1)+"%'";
				}
				else{
					query += " s.lname = '"+lname+"'";
				}
				flag++;
				
			}
			if(!fname.equals("")){
				if (flag>0){
					query+=" and";
				}
				if(fname.endsWith("*")){
				query += " s.fname like '"+fname.substring(0, fname.length()-1)+"%'";
				
				}
				else{
					query += " s.fname = '"+fname+"'";
				}
				flag++;
			}
			if(!city.equals("")){
				if (flag>0){
					query+=" and";
				}
				if(city.endsWith("*")){
				query += " s.city like '"+city.substring(0, city.length()-1)+"%'";
				
				}
				else{
					query += " s.city = '"+city+"'";
				}
				flag++;
			}
			
			if(!state.equals("")){
				if (flag>0){
					query+=" and";
				}
				if(state.endsWith("*")){
				query += " s.state like '"+state.substring(0, state.length()-1)+"%'";
				
				}
				else{
					query += " s.state = '"+state+"'";
				}
			}
			System.out.println(query);
			allSurveys = ss.retrieveStudentSurvey(query);
		
		
		return "ListSurvey1";
		
	}
	
	public String sessDelete(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Survey.xhtml?faces-redirect=true";
	}
	
	public String sessSearchDelete(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/EJBSearch.xhtml?faces-redirect=true";
		
	}
}
	
