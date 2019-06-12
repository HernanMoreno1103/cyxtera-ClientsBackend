package co.com.cyxtera.clients.services;

import java.util.List;

import co.com.cyxtera.clients.entities.Client;

/**
 * Interface Client Service
 * 
 * @author Hernan
 *
 */
public interface IClientService {

	/**
	 * Metodo encargado de buscar todos los clientes
	 * 
	 * @return {@link List}
	 */
	public List<Client> findAll();

	/**
	 * Metodo encargado de buscar todos los clientes por sharedKey
	 * 
	 * @param sharedKey
	 * @return client
	 */
	public Client findBySharedKey(String sharedKey);

	/**
	 * Metodo encargado de consultar por id un cliente
	 * 
	 * @param id
	 * @return {@link Client}
	 */
	public Client findById(Long id);

	/**
	 * Metodo encargado de almacenar un cliente nuevo
	 * 
	 * @param client
	 * @return
	 */
	public String save(Client client);

	/**
	 * Metodo encargado de eliminar un cliente
	 * 
	 * @param id
	 * @return
	 */
	public String delete(Long id);

}
