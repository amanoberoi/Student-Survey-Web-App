//this file contains the interface store remote with method declarations savesurvey and storesurvey.
package src;

import java.sql.SQLException;
import javax.ejb.Remote;

@Remote
public interface StoreRemote {
	void saveSurvey(StuSurvey student);
	 
	public void StoreSurvey(String query) throws SQLException;
}
