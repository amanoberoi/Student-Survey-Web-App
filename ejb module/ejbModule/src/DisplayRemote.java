//this is an interface that contains method to retrieve/display the table.
package src;


import java.util.List;

import javax.ejb.Remote;

@Remote
public interface DisplayRemote {

	
	List<StuSurvey> retrieveAllProjects();
	List<StuSurvey> retrieveProjects(String query);
	
	
}
