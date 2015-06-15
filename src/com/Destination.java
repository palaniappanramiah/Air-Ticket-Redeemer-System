/******************************************************************
 * Name           : Palaniappan Ramiah
 * ZID            : Z1726972
 * Assignment No. : 2
 * Program Name   : Destination.java
 * Description    : Encapsulates destination, miles & month details 
 *                  with its getters and setters and a constructor.
 *****************************************************************/
package com;

public class Destination {

	// Variables - declaration
	private String destinationCity;
	private int normalMiles, superSaverMiles, upgradingAdditionalMiles,
			startMonth, endMonth;

	// Constructor
	public Destination(String city, int miles, int superMiles,
			int upgradingMiles, int startingMonth, int endingMonth) {
		destinationCity = city;
		normalMiles = miles;
		superSaverMiles = superMiles;
		upgradingAdditionalMiles = upgradingMiles;
		startMonth = startingMonth;
		endMonth = endingMonth;
	}

	// Getters and Setters
	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getNormalMiles() {
		return normalMiles;
	}

	public void setNormalMiles(int normalMiles) {
		this.normalMiles = normalMiles;
	}

	public int getUpgradingAdditionalMiles() {
		return upgradingAdditionalMiles;
	}

	public void setUpgradingAdditionalMiles(int upgradingAdditionalMiles) {
		this.upgradingAdditionalMiles = upgradingAdditionalMiles;
	}

	public int getSuperSaverMiles() {
		return superSaverMiles;
	}

	public void setSuperSaverMiles(int superSaverMiles) {
		this.superSaverMiles = superSaverMiles;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
}
