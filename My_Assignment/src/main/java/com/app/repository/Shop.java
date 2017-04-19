package com.app.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Domain Class representing a Shop
 * 
 * @author Somenath
 *
 */
@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long Id;
	@Column(unique = true)
	private String shopName;
	private String shopAddress;
	private String shopZipCode;
	private Double latitude;
	private Double longitude;

	/**
	 * Default Constructor
	 */
	public Shop() {
		this.Id = null;
	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param shopName Name of the shop
	 * @param shopAddress Address of the shop
	 * @param shopZipCode Zip Code of the shop
	 */
	public Shop(String shopName, String shopAddress, String shopZipCode) {
		this();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopZipCode = shopZipCode;
	}

	/*
	 * Getters and Setters
	 * 
	 */
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopZipCode() {
		return shopZipCode;
	}

	public void setShopZipCode(String shopZipCode) {
		this.shopZipCode = shopZipCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@JsonIgnore
	public Long getId() {
		return Id;
	}

}
