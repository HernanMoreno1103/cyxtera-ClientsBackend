package co.com.cyxtera.clients.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opencsv.bean.CsvBindByPosition;

/**
 * Entity Client
 * 
 * @author Hernan
 *
 */
@Entity
@Table(name = "clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Attribute id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@CsvBindByPosition(position = 1)
	private Long id;

	/**
	 * Attribute sharedKey
	 */
	@CsvBindByPosition(position = 2)
	private String sharedKey;

	/**
	 * Attribute name
	 */
	@CsvBindByPosition(position = 3)
	private String name;

	/**
	 * Attribute phone
	 */
	@CsvBindByPosition(position = 4)
	private String phone;

	/**
	 * Attribute email
	 */
	@CsvBindByPosition(position = 5)
	private String email;

	/**
	 * Attribute startDate
	 */
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	@CsvBindByPosition(position = 6)
	private Date startDate;

	/**
	 * Attribute endDate
	 */
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	@CsvBindByPosition(position = 7)
	private Date endDate;

	/**
	 * Gets the id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the sharedKey
	 * 
	 * @return
	 */
	public String getSharedKey() {
		return sharedKey;
	}

	/**
	 * Sets the sharedKey
	 * 
	 * @param sharedKey
	 */
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	/**
	 * Gets the name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the phone
	 * 
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the startDate
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the startDate
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the endDate
	 * 
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the endDate
	 * 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", sharedKey=" + sharedKey + ", name=" + name + ", phone=" + phone + ", email="
				+ email + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
