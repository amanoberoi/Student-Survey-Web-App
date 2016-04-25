//This file contains a class that deletes a row from the relation schema by implementing interface deleteremote
package src;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;

/**
 * Session Bean class Delete
 */
@Stateless
public class Delete implements DeleteRemote {
	@PersistenceContext(unitName = "Assignment4_Aman")
    private EntityManager entityManager;
    
	/*
     * Default constructor. 
     */
    public Delete() {
        
    }

	@Override
	public void delete(String delete) {
		StuSurvey stusurvey = entityManager.find(StuSurvey.class, delete);
		entityManager.remove(stusurvey);
		
	}

}