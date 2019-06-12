package co.com.cyxtera.clients.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.cyxtera.clients.entities.Client;

/**
 * Inteface ClientDAO
 * 
 * @author Hernan
 *
 */
public interface IClientDao extends CrudRepository<Client, Long> {

	/**
	 * Metodo que consulta el cliente por sharedKey
	 * 
	 * @param sharedKey
	 * @return
	 */
	Client findBySharedKey(String sharedKey);
	
}
