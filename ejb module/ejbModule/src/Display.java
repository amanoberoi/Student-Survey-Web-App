//This file contains a class Display that displays the table data by implementing interface displayremote
//contains two retrieval methods, one for all records display and other for the search query
package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.persistence.*;



@Stateless
@Remote(DisplayRemote.class)
public class Display implements DisplayRemote {
	
	@PersistenceContext(unitName = "Assignment4_Aman")
	private EntityManager entityManager;
	
	
	

	@Override
	public List<StuSurvey> retrieveAllProjects() {
		   
    	
        String q1 = "SELECT s from "+ StuSurvey.class.getName() + " s";
      
        Query query1 = entityManager.createQuery(q1);
     
        List<StuSurvey> AllProjects = query1.getResultList();
       
        return AllProjects;
		
	}

	@Override
	public List<StuSurvey> retrieveProjects(String query) {
		if (query.equals("asc")){
        String q2 = "SELECT s from "+ StuSurvey.class.getName() + " s ORDER BY s.lname ASC" ;
     
        Query query2 = entityManager.createQuery(q2);
   
        List<StuSurvey> stusurvey = query2.getResultList();
       
        return stusurvey;
		}
		else{
			 String qq = "SELECT s from "+ StuSurvey.class.getName() + " s where " + query ;
			 
		        Query query3 = entityManager.createQuery(qq);
		     
		        List<StuSurvey> stusurvey = query3.getResultList();
		       return stusurvey;
			
		}
		
	}

}
