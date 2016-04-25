// This student service class contains all the business logic operations such as calculating
// the mean and standard deviation.
// Business logic to store data and retrieve data from SQL database.
package student;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import src.DeleteRemote;
import src.DisplayRemote;
import src.EmergencyInfo;
import src.StoreRemote;
import src.StuSurvey;


public class StudentService {
	
	public double calculateMean(String raffle) {
		
		double mean=0;
		String RaffleData[]=raffle.split(",");
		
		for(int i=0;i<RaffleData.length;i++)
		{
			mean = mean + Integer.parseInt(RaffleData[i]);
		}
		
		mean = mean / RaffleData.length;
		return mean;
	}
	
	
	public double calculateStddev(String raffle, double mean) {
		
		double stddev=0,sum=0;
		String RaffleData[]=raffle.split(",");
		
		for(int i=0;i<RaffleData.length;i++) {
			sum = sum + Math.pow((Integer.parseInt(RaffleData[i]) - mean),2);
		}
		
		stddev=Math.sqrt(sum/RaffleData.length);
		
		return stddev;
	}

	
	
	public void StoreStudentSurvey (Student student) throws IOException, SQLException{
		 
		try {
		
				final Hashtable jndiProperties = new Hashtable();
				jndiProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
       
				final Context context = new InitialContext(jndiProperties);
				StoreRemote dd = (StoreRemote)context.lookup("ejb:Assignment4EAR/Assignment4_2//Store!src.StoreRemote");
       
				Date date=student.getDateOfSurvey();
				Date sdate = student.getStartDate(); 
				String d1 = null;
				String d2=null;
				SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		
				d1=sdf.format(date);
				d2=sdf.format(sdate);
		
					StuSurvey students = new StuSurvey();
					students.setFname(student.getFirstName());
					students.setLname(student.getLastName());
					students.setStreet(student.getStreetAddress());
					students.setCity(student.getCity());
					students.setState(student.getState());
					students.setZip(student.getZip());
					students.setContact(student.getContactNumber());
					students.setEmail(student.getEmail());
					students.setD1(d1);
					students.setD2(d2);
					students.setTemp(student.getTemp());
					students.setInterest(student.getInterest());
					students.setLikelihood(student.getLikelihood());
					students.setRaffle(student.getRaffle());
					students.setComments(student.getComments());
		
					List<EmergencyInfo> eminfo = new ArrayList<EmergencyInfo>();
					EmergencyInfo einfo = new EmergencyInfo();
					einfo.seteName1(student.geteName1());
					einfo.seteId1(student.geteId1());
					einfo.seteNumber1(student.geteNo1());
					
					if(!(student.geteId2().isEmpty()) && (student.geteId2() !=null))
					{
						einfo.seteName2(student.geteName2());
						einfo.seteId2(student.geteId2());
						einfo.seteNumber2(student.geteNo2());
					}
					
					eminfo.add(einfo);
					students.setEmergencyinfo(eminfo);
					dd.saveSurvey(students);
		
		}catch(Exception e){
		System.out.println("Exception thrown " +e); 
		}
	}
	

	
	public ArrayList<Student> retrieveStudentSurvey(String Query) throws IOException, SQLException{
		ArrayList<Student> AllSurveys = new ArrayList<Student>();
		
		
	
		Student survey = new Student();
		 
		try {
			final Hashtable jndiProperties = new Hashtable();
	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        
	        final Context context = new InitialContext(jndiProperties);
	        System.out.println("About to get connection");
	        
	       DisplayRemote dd = (DisplayRemote)context.lookup("ejb:Assignment4EAR/Assignment4_2//Display!src.DisplayRemote");
	        
	       List<StuSurvey> rs =	dd.retrieveProjects(Query);
	       DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	       
	       for(StuSurvey stu : rs)
	        {
	        	
	        	
	        	survey.setFirstName(stu.getFname());
				survey.setLastName(stu.getLname());
				survey.setStreetAddress(stu.getStreet());
				survey.setCity(stu.getCity());
				survey.setState(stu.getState());
				survey.setZip(stu.getZip());
				survey.setContactNumber(stu.getContact());
				survey.setEmail(stu.getEmail());
				
				Date dateofsurvey= format.parse(stu.getD1());
				survey.setDateOfSurvey(dateofsurvey);
				Date semdate = format.parse(stu.getD2());
				survey.setStartDate(semdate);
				
				survey.setTemp(stu.getTemp());
				survey.setInterest(stu.getInterest());
				survey.setLikelihood(stu.getLikelihood());
				survey.setComments(stu.getComments());
				survey.setRaffle(stu.getRaffle());
				
				
				List<EmergencyInfo> einfo = stu.getEmergencyinfo();
				for(EmergencyInfo em : einfo)
				{
					survey.seteName1(em.geteName1());
					survey.seteId1(em.geteId1());
					survey.seteNo1(em.geteNumber1());
					
					if(null!=em.geteId2() && !(em.geteId2().isEmpty())){
						
						survey.seteName2(em.geteName2());
						survey.seteId2(em.geteId2());
						survey.seteNo2(em.geteNumber2());
					}
				}
				
				AllSurveys.add(survey);
				survey = new Student();
				
			}
	       
	
		}catch(Exception e){
		System.out.println("Exception thrown " +e); 
		}
	
		return AllSurveys;
		
	}
       
	
	
	public void delete(String del) throws NamingException{
		final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        System.out.println("About to get connection");
        
      
       DeleteRemote dd = (DeleteRemote)context.lookup("ejb:Assignment4EAR/Assignment4_2//Delete!src.DeleteRemote");
       dd.delete(del);
	}
	
	
}


/*
public void storeData(String fname,
						String lname,
						String street,
						String city,
						String state,
						String zip,
						String contact,
						String email,
						String d1,
						String d2,
						String temp,
						String interest,
						String likelihood,
						String raffle,
						String comments) throws SQLException
	{
			
	 try
	   {
		   
		   Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		   Connection conn =  DriverManager.getConnection ("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g", "aoberoi2", "aman6192");
		   System.out.println("Connection established.");
		   
		   String query =  "INSERT INTO Student VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	  
	   PreparedStatement ps = conn.prepareStatement(query);
	   System.out.println("Preparing Query");
	   ps.setString(1,fname);
	   ps.setString(2,lname);
	   ps.setString(3,street);  
	   ps.setString(4,city);
	   ps.setString(5,state);
	   ps.setString(6,zip);
	   ps.setString(7,contact);
	   ps.setString(8,email);
	   ps.setString(9,d1);
	   ps.setString(10,d2);
	   ps.setString(11,temp);
	   ps.setString(12,interest);
	   ps.setString(13,likelihood);
	   ps.setString(14,raffle);
	   ps.setString(15,comments);
	  
	   
	   ps.executeUpdate();
	   System.out.println("Successfully updated database.");
	   ps.close();
	 
	
	   if(conn != null){
		   conn.close();
	   }
	   }
	   catch(ClassNotFoundException cnfe) {
		   System.out.println("Error: Unable to load driver class.");
		   System.exit(1);
		}
		catch(IllegalAccessException iae) {
		   System.out.println("Error: Access problem while loading.");
		   System.exit(2);
		}
		catch(InstantiationException ie) {
		   System.out.println("Error:Unable to instantiate driver.");
		   System.exit(3);
		}
		catch(SQLException sqle){
			System.out.println("Error:SQL EXCEPTION");
			System.exit(4);
		}
	}

public ArrayList<Student> displayData(){

 ArrayList<Student> s1 = null;
try {
	
	   s1 = new ArrayList<Student>();
	   
	   Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	   Connection conn =  DriverManager.getConnection ("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g", "aoberoi2", "aman6192");
	   System.out.println("Connection established.");
	   Statement stmt = conn.createStatement();
	 
	   ResultSet result = stmt.executeQuery("Select * from Student");
	   
	   while(result.next()){
		  
		   Student stud = new Student();
		   stud.setFirstName(result.getString(1));
		   stud.setLastName(result.getString(2));
		   stud.setStreetAddress(result.getString(3));
		   stud.setCity(result.getString(4));
		   stud.setState(result.getString(5));
		   stud.setZip(result.getString(6));
		   stud.setContactNumber(result.getString(7));
		   stud.setEmail(result.getString(8));
		   
		   
		   DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		   
		   try{
			   
		   System.out.println(result.getString(9));
		   Date dateofsurvey= format.parse(result.getString(9));
		  stud.setDateOfSurvey(dateofsurvey);
		
		   Date semdate = format.parse(result.getString(10));
			 stud.setStartDate(semdate);
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   stud.setTemp(result.getString(11));
		   stud.setInterest(result.getString(12));
		   stud.setLikelihood(result.getString(13));
		   stud.setRaffle(result.getString(14));
		   stud.setComments(result.getString(15));
		   s1.add(stud);

	   }
	   
	  
	   conn.close();
	   System.out.println("conn closed");
}
catch(ClassNotFoundException cnfe) {
	   System.out.println("Error: unable to load driver class!");
	   System.exit(1);
	}
	catch(IllegalAccessException iae) {
	   System.out.println("Error: access problem while loading!");
	   System.exit(2);
	}
	catch(InstantiationException ie) {
	   System.out.println("Error: unable to instantiate driver!");
	   System.exit(3);
	}
	catch(SQLException sqle){
		System.out.println("Error:SQLEXCEPTION");
		System.exit(4);
	}
return s1;
}
*/
