package com.governance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing an admin in the system. This class is mapped to the
 * "admin_log" table in the database.
 */
@Entity
@Table(name = "admin_log")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private String password;

	/**
	 * Default constructor for Admin entity.
	 */
	public Admin() {
	}

	/**
	 * Constructor for creating an Admin entity with specified name, email, and
	 * password.
	 *
	 * @param name     The name of the admin.
	 * @param email    The email of the admin.
	 * @param password The password of the admin.
	 */
	public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Getter method for retrieving the ID of the admin.
	 *
	 * @return The ID of the admin.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter method for setting the ID of the admin.
	 *
	 * @param id The ID to set for the admin.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter method for retrieving the name of the admin.
	 *
	 * @return The name of the admin.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for setting the name of the admin.
	 *
	 * @param name The name to set for the admin.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for retrieving the email of the admin.
	 *
	 * @return The email of the admin.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter method for setting the email of the admin.
	 *
	 * @param email The email to set for the admin.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter method for retrieving the password of the admin.
	 *
	 * @return The password of the admin.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for setting the password of the admin.
	 *
	 * @param password The password to set for the admin.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Override of the toString() method to provide a string representation of the
	 * Admin object.
	 *
	 * @return A string representation of the Admin object.
	 */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
