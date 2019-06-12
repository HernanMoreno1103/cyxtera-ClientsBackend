package co.com.cyxtera.clients.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import co.com.cyxtera.clients.entities.Client;
import co.com.cyxtera.clients.services.IClientService;

/**
 * Cliente Rest
 * 
 * @author Hernan
 *
 */
@RestController
@RequestMapping("/api/clients")
public class ClienteRestController {

	@Autowired
	private IClientService clientService;

	/**
	 * Servicio encargado de buscar todos los clientes
	 * 
	 * @return {@link List}
	 */
	@GetMapping("/findAll")
	public List<Client> findAll() {
		return clientService.findAll();
	}

	/**
	 * Servicio encargado de buscar el cliente por sharedKey
	 * 
	 * @param sharedKey
	 * @return {@link Client}
	 */
	@GetMapping("/find")
	public Client findBySharedKey(@RequestParam("q") String sharedKey) {
		return this.clientService.findBySharedKey(sharedKey);
	}

	/**
	 * Servicio encargado de consultar por id un cliente
	 * 
	 * @param id
	 * @return {@link Client}
	 */
	@GetMapping("/findById/{id}")
	public Client findById(@PathVariable Long id) {
		return this.clientService.findById(id);
	}

	/**
	 * Servicio encargado de almacenar un cliente nuevo
	 * 
	 * @param cliente
	 * @return
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public String save(@RequestBody Client cliente) {
		return this.clientService.save(cliente);
	}

	/**
	 * Servicio encargado de eliminar un cliente
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String delete(@PathVariable Long id) {
		return this.clientService.delete(id);
	}

	/**
	 * Servicio encargado de exportar listado de clientes
	 * 
	 * @return
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		response.setContentType("text/plain; charset=utf-8");

		// set file name and content type
		String filename = "clients.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

		// create a csv writer
		StatefulBeanToCsv<Client> writer = new StatefulBeanToCsvBuilder<Client>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(true).build();

		// write all users to csv file
		writer.write(clientService.findAll());
	}

}
