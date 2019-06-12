package co.com.cyxtera.clients.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.cyxtera.clients.dao.IClientDao;
import co.com.cyxtera.clients.entities.Client;

/**
 * Class Client Service
 * 
 * @author Hernan
 *
 */
@Service
public class ClientServiceImpl implements IClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

	private static final String MESSAGE_CREATED = "Client is created successfully";
	private static final String MESSAGE_DELETED = "Client is deleted successfully";

	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		LOGGER.info("Find All Clients...");

		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findBySharedKey(String sharedKey) {
		LOGGER.info("Find Clients By SharedKey {} ...", sharedKey);

		return clientDao.findBySharedKey(sharedKey);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		LOGGER.info("Find Client By Id {} ...", id);

		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public String save(Client client) {
		LOGGER.info("Create Client {} ...", client.toString());

		// create shared key
		String[] names = client.getName().split("\\s+");
		client.setSharedKey(names[0].substring(0, 1) + names[1]);

		clientDao.save(client);

		return MESSAGE_CREATED;
	}

	@Override
	@Transactional
	public String delete(Long id) {
		LOGGER.info("Delete Client {} ...", id);

		Client client = this.findById(id);
		clientDao.delete(client);

		return MESSAGE_DELETED;
	}

}
