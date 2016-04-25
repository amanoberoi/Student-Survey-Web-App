//this file contains a class Store having methods to store survey data.
package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
@Remote(StoreRemote.class)
public class Store implements StoreRemote {
	@PersistenceContext(unitName = "Assignment4_Aman")
    private EntityManager entityManager;
     
 
 
	@Override
	public void StoreSurvey(String query) throws SQLException {
		DataSource ds = null;
		Connection conn = null; 
		PreparedStatement ps = null; 
		InitialContext ic; 
		try {
			ic = new InitialContext();
			ds = (DataSource)ic.lookup( "java:/hw4AmanDS" );
			conn = ds.getConnection(); 

        //System.out.println("inside store data");
	
		ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
	
		rs.close();
		ps.close();
		}catch(Exception e){
		System.out.println("Exception thrown " +e); 
		}finally{
		if(conn != null){
		conn.close();
		} 
		}
		
	}

	@Override
	public void saveSurvey(StuSurvey student) {
        entityManager.persist(student);

	}

	

}
