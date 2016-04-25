//this is an interface that contains the delete method declaration 
package src;

import javax.ejb.Remote;

@Remote
public interface DeleteRemote {
	public void delete(String delete);

}
