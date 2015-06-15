/******************************************************************
 * Name           : Palaniappan Ramiah
 * ZID            : Z1726972
 * Assignment No. : 2
 * Program Name   : MileRedeemer.java
 * Description    : Reads the file, gets the information from it &
 *                  encapsulates the logic for redeeming mileage.
 *****************************************************************/
package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MileRedeemer {

	// Constants - declaration and initialization
	private static final String ECONOMY = "economy";
	private static final String FIRST = "first";

	// Variables - declaration and initialization
	private int key = 0, index = 0, lastIndex = 0, redeemedTicketsLength = 0,
			destinationLength = 0, normalMiles = 0, superSaverMiles = 0,
			upgradedMiles = 0, remainingMiles = 0;

	private boolean superSaverEligible = false;

	private String destinationObject = null;

	private String[] destinationObjects = null, redeemedTickets = null,
			ticketClass = null;

	private List<Destination> destinationArrayList = new ArrayList<Destination>();

	private Destination[] destinationList;

	/**
	 * This method reads the file from the location provided in the args[],
	 * converts to String array & also to an object of a list of Destination
	 * 
	 * @param fileScanner
	 * @return void
	 */
	public void readDestinations(Scanner fileScanner) {

		// Checking of the file has more lines
		while (fileScanner.hasNext()) {

			// Storing a single line to the String
			destinationObject = fileScanner.nextLine();

			// Finding the lindex of last occuring '-'
			lastIndex = destinationObject.lastIndexOf("-");

			// Replacing '-' with ';', splitting at ';' & storing as arrays
			destinationObjects = (new StringBuilder(destinationObject).replace(
					lastIndex, lastIndex + 1, ";").toString()).split(";");

			// Adding the array to an instance object of a list of Destination
			destinationArrayList.add(new Destination(
					destinationObjects[index++], Integer
							.parseInt(destinationObjects[index++]), Integer
							.parseInt(destinationObjects[index++]), Integer
							.parseInt(destinationObjects[index++]), Integer
							.parseInt(destinationObjects[index++]), Integer
							.parseInt(destinationObjects[index])));

			// Resetting the index to zero for the next loop
			index = 0;
		}
	}

	/**
	 * This method reads destinationArrayList object and stores only the
	 * destination in the destinationObjects array and sorts it too.
	 * 
	 * @param null
	 * @return String[]
	 */
	public String[] getCityNames() {

		// Setting the size of the array to that of the arraylist object
		destinationObjects = new String[destinationArrayList.size()];

		// Storing the destination city to the destinationObjects array
		for (Destination dest : destinationArrayList) {
			destinationObjects[index++] = dest.getDestinationCity();
		}

		Arrays.sort(destinationObjects);

		return destinationObjects;
	}

	/**
	 * This method reads destinationArrayList object and returns the mile
	 * details of the city.
	 * 
	 * @param String
	 * @return Destination
	 */
	public Destination getMileDetails(String selectedCity) {

		Destination destination = null;

		// Storing the destination city to the destinationObjects array
		for (Destination destn : destinationArrayList)
			if (selectedCity.equals(destn.getDestinationCity()))
				destination = destn;

		return destination;
	}

	/**
	 * This method reads month(number) of supersaver of the destination city and
	 * returns the corresponding Month details in words.
	 * 
	 * @param int
	 * @return String
	 */
	public String getMonthDetails(int month) {

		String monthString;
		switch (month) {
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;
		default:
			monthString = "Invalid month";
			break;
		}

		return monthString;
	}

	/**
	 * This method reads month details in words as the input and returns its
	 * corresponding number.
	 * 
	 * @param String
	 * @return int
	 */
	public int getMonthNumber(String monthValue) {
		Date date = null;

		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH)
					.parse(monthValue);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;

		return month;
	}

	/**
	 * The month Strings can be obtained in this method.
	 * 
	 * @param null
	 * @return String[]
	 */
	public String[] getMonthStrings() {

		String[] months = new java.text.DateFormatSymbols().getMonths();
		int lastIndex = months.length - 1;

		if (months[lastIndex] == null || months[lastIndex].length() <= 0) {
			String[] monthStrings = new String[lastIndex];
			System.arraycopy(months, 0, monthStrings, 0, lastIndex);
			return monthStrings;
		} else { // if last item not empty
			return months;
		}
	}

	/**
	 * This method consturcts the destinationList object from arraylist & sorts
	 * it. It also sends back a list of redeemable ticket information
	 * 
	 * @param miles
	 * @param month
	 * @return String[]
	 */
	public String[] redeemMiles(int miles, int month) {

		// Converting destinationArrayList to an object instance of Destination
		destinationList = (Destination[]) destinationArrayList
				.toArray(new Destination[destinationArrayList.size()]);

		// Sorting the destinationList in the descending order of normal miles
		Arrays.sort(destinationList, new Comparator<Destination>() {
			public int compare(Destination destination1,
					Destination destination2) {
				return destination2.getNormalMiles()
						- destination1.getNormalMiles();
			}
		});

		// Calling the method which handles all the logic of redeeming tickets
		redeemLogic(miles, month);

		// Constructing the list of redeemable tickets information
		for (index = 0; index < redeemedTickets.length; index++) {
			if (redeemedTickets[index] != null)
				redeemedTickets[index] = "* A trip to "
						+ redeemedTickets[index] + ", " + ticketClass[index]
						+ " class.";
		}

		return redeemedTickets;
	}

	/**
	 * This method calculates whether user is eligible to use super saver miles,
	 * checks for upgradation of ticket & also calculates the remaining miles
	 * 
	 * @param miles
	 * @param month
	 */
	public void redeemLogic(int miles, int month) {

		// Assigning the destinationLength also to both the other arrays
		key = 0;
		destinationLength = destinationList.length;
		redeemedTickets = new String[destinationLength];
		ticketClass = new String[destinationLength];

		for (index = 0; index < destinationLength; index++) {

			// Supersaver logic for months, if its in the range of 1-12
			if ((destinationList[index].getEndMonth() - destinationList[index]
					.getStartMonth()) >= 0) {
				if ((month >= destinationList[index].getStartMonth())
						&& (month <= destinationList[index].getEndMonth()))
					superSaverEligible = true;
				else
					superSaverEligible = false;
			} else { // Supersaver logic for months, if its in the range of
						// 12-11
				if ((month < destinationList[index].getStartMonth())
						&& (month > destinationList[index].getEndMonth()))
					superSaverEligible = false;
				else
					superSaverEligible = true;
			}

			normalMiles = destinationList[index].getNormalMiles();
			superSaverMiles = destinationList[index].getSuperSaverMiles();

			// Checking for enough superSaverMiles, if travel is in super saver
			// month
			if ((superSaverEligible == true) && (miles >= superSaverMiles)) {
				redeemedTickets[key] = destinationList[index]
						.getDestinationCity();
				miles = miles - superSaverMiles;
				ticketClass[key++] = ECONOMY; // Economy class set for
												// redeemable tickets
			} // Checking for enough normalMiles, if travel isn't in super saver
				// month
			else if ((superSaverEligible == false) && (miles >= normalMiles)) {
				redeemedTickets[key] = destinationList[index]
						.getDestinationCity();
				miles = miles - normalMiles;
				ticketClass[key++] = ECONOMY;
			}
		}

		redeemedTicketsLength = redeemedTickets.length;

		for (index = 0; index < destinationLength; index++) {
			for (key = 0; key < redeemedTicketsLength; key++) {

				upgradedMiles = destinationList[index]
						.getUpgradingAdditionalMiles();

				// Checking for miles to upgrade the redeemable tickets to first
				// class
				if ((destinationList[index].getDestinationCity()
						.equals(redeemedTickets[key]))
						&& (miles >= upgradedMiles)) {
					ticketClass[key] = FIRST; // Overriding the value from
												// economy to first
					miles = miles - upgradedMiles;
				}
			}
		}
		remainingMiles = miles; // The miles subtracted above is the remaining
								// miles
	}

	/**
	 * This method returns the remaining miles back
	 * 
	 * @param null
	 * @return int
	 */
	public int getRemainingMiles() {
		return remainingMiles;
	}

}