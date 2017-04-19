package com.app.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for CRUD operation
 * 
 * @author Somenath
 *
 */
public interface RetailManagerRepository extends CrudRepository<Shop, Long> {

	/**
	 * Method to save shop date.
	 * 
	 * Optimistic Locking will make sure that during a transaction<br>
	 * no dirty read will occur. 
	 */
	@Lock(LockModeType.READ)
	Shop save(Shop shop);

	/**
	 * 
	 * @param name
	 *            Name of the shop to be searched for.
	 * @return Shop object
	 */
	Shop findByShopName(String name);

}
