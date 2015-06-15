/******************************************************************
 * Name           : Palaniappan Ramiah
 * ZID            : Z1726972
 * Assignment No. : 2
 * Program Name   : MileRedemptionApp.java
 * Description    : Contains the loop for user interaction thru GUI
 *                  Call methods of MileRedeemer class & prints a
 *                  list of redeemable tickets by the written logic
 *****************************************************************/
package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MileRedemptionApp extends JFrame {

	// Variables - declaration and initialization
	private static Scanner fileScanner = null;

	private static String[] destinationCity = null, listOfTickets = null;
	private static String tkt = "";

	// Object instance of MileRedeemer class created to call its methods
	private static MileRedeemer mileRedeemer = new MileRedeemer();
	Destination destination = null;

	// Fields Declaration
	final JLabel comment = new JLabel(
			"\nYour miles can be used to redeem the following tickets:");
	final JLabel remMilesLabel = new JLabel("Congrats! Your Remianing Miles is");
	JTextArea ticketArea = new JTextArea();
	JList listBox;
	JButton button;
	JTextField text1, text2, text3, text4, remMiles = new JTextField("", 10);

	@SuppressWarnings("unchecked")
	public MileRedemptionApp() {

		// Setting the container's properties .
		this.setSize(710, 380);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0, 2));
		this.setBackground(Color.red);
		this.setResizable(false);

		// Creating new panel and adding it.
		JPanel leftPane = new JPanel();
		JPanel rightPane = new JPanel();
		this.add(leftPane);
		this.add(rightPane);

		// Setting background colors and layout for the panes
		leftPane.setBackground(Color.getHSBColor(30, 100, 90));
		rightPane.setBackground(Color.getHSBColor(30, 0, 90));
		leftPane.setLayout(new GridLayout(2, 0, 20, 20));
		rightPane.setLayout(new GridLayout(2, 0, 20, 20));

		// Setting title borders for the panes
		leftPane.setBorder(BorderFactory
				.createTitledBorder("List of Destination Cities"));
		rightPane.setBorder(BorderFactory.createTitledBorder("Redeem Tickets"));

		// Creating 4 panels, setting the bkg color & layout and adding it
		final JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPane.add(leftTopPanel);
		leftTopPanel.setBackground(Color.white);

		final JPanel leftBottomPanel = new JPanel(new GridLayout(0, 2));
		leftPane.add(leftBottomPanel);
		leftBottomPanel.setBackground(Color.getHSBColor(30, 100, 80));

		final JPanel rightTopPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER));
		rightPane.add(rightTopPanel);
		rightTopPanel.setBackground(Color.getHSBColor(30, 0, 75));

		final JPanel rightBottomPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER));
		rightPane.add(rightBottomPanel);
		rightBottomPanel.setBackground(Color.white);

		// Create a new listbox control for displaying destination cities
		final JList listBox = new JList(destinationCity);
		listBox.setFixedCellWidth(330);
		leftTopPanel.add(listBox);

		// Creating 2 panels each for label and textfields respectively
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftBottomPanel.add(labelPanel);
		labelPanel.setBackground(Color.getHSBColor(30, 100, 80));

		JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		leftBottomPanel.add(textFieldPanel);
		textFieldPanel.setBackground(Color.getHSBColor(30, 100, 80));

		// Creating the required labels, textfields & setting its font & size
		JLabel label1 = new JLabel("Required Miles");
		label1.setFont(new Font("Serif", Font.PLAIN, 15));
		text1 = new JTextField("", 14);

		JLabel label2 = new JLabel("Miles for upgrading");
		label2.setFont(new Font("Serif", Font.PLAIN, 15));
		text2 = new JTextField("", 14);

		JLabel label3 = new JLabel("Miles for Supersaver");
		label3.setFont(new Font("Serif", Font.PLAIN, 15));
		text3 = new JTextField("", 14);

		JLabel label4 = new JLabel("Months for Supersaver");
		label4.setFont(new Font("Serif", Font.PLAIN, 15));
		text4 = new JTextField("", 14);

		// Setting the text fields to non editable forms
		text1.setEditable(false);
		text2.setEditable(false);
		text3.setEditable(false);
		text4.setEditable(false);

		// Adding labels and textfields to the panels
		labelPanel.add(label1);
		textFieldPanel.add(text1);
		labelPanel.add(label2);
		textFieldPanel.add(text2);
		labelPanel.add(label3);
		textFieldPanel.add(text3);
		labelPanel.add(label4);
		textFieldPanel.add(text4);

		listBox.addListSelectionListener(new ListSelectionListener() {
			// Method to get invoked when a city is chosen from the list
			public void valueChanged(ListSelectionEvent event) {
				// it will return if the value is changed
				if (event.getValueIsAdjusting())
					return;

				// Getting the miles details of a city
				destination = mileRedeemer.getMileDetails(listBox
						.getSelectedValue().toString());

				// Setting the text to details respective to the cities
				text1.setText(String.valueOf(destination.getNormalMiles()));
				text2.setText(String.valueOf(destination
						.getUpgradingAdditionalMiles()));
				text3.setText(String.valueOf(destination.getSuperSaverMiles()));
				text4.setText(mileRedeemer.getMonthDetails(destination
						.getStartMonth())
						+ " to "
						+ mileRedeemer.getMonthDetails(destination
								.getEndMonth()));
			}
		});

		// Setting the label and textfield for accumulated miles
		JLabel accumulatedMiles = new JLabel("Your Accumulated Miles");
		final JTextField accumulatedMilesText = new JTextField("", 10);

		// Setting the label and spinner for departure month
		JLabel departureMonth = new JLabel("Month of Departure");
		SpinnerListModel monthModel = new SpinnerListModel(
				mileRedeemer.getMonthStrings());
		final JSpinner spinner = new JSpinner(monthModel);
		spinner.setPreferredSize(new Dimension(130, 20));

		// Creating the button that invokes the required method
		JButton button = new JButton("Redeem Tickets >>>");

		// Adding the fields
		rightTopPanel.add(accumulatedMiles);
		rightTopPanel.add(accumulatedMilesText);
		rightTopPanel.add(departureMonth);
		rightTopPanel.add(spinner);
		rightTopPanel.add(button);

		button.addActionListener(new ActionListener() {
			// Method that is invoked when the buton is clicked
			public void actionPerformed(ActionEvent arg0) {

				// Adding the comment section to the panel
				rightBottomPanel.add(comment);

				// Setting the redeemable tickets to null
				tkt = "";

				// Setting the visibility of elements to false first
				comment.setVisible(false);
				ticketArea.setVisible(false);
				remMilesLabel.setVisible(false);
				remMiles.setVisible(false);

				// Getting the month value in number from the spinner
				int month = mileRedeemer.getMonthNumber((String) spinner
						.getValue());

				// Getting miles from the textfield & checking its validity
				String milesValue = accumulatedMilesText.getText();
				boolean match = milesValue.matches("[0-9]*");

				// Continued if its a number
				if (match) {

					// Converting the miles value to int
					int accumulatedMiles = Integer.parseInt(milesValue);

					// Getting the list of redeemed tickets
					listOfTickets = mileRedeemer.redeemMiles(accumulatedMiles,
							month);

					// Setting the comment section to true
					comment.setVisible(true);

					// Traversing through String array and printing the
					// redeemable tickets
					if (listOfTickets[0] != null) {
						for (String ticket : listOfTickets)
							if (ticket != null)
								tkt = tkt.concat(ticket + "\n");
					} else
						tkt = "You need more miles to redeem a ticket";

					// Setting the text area with the tickets
					ticketArea.setText(tkt);

					// Adding the tickets and setting it to visible
					rightBottomPanel.add(ticketArea);
					ticketArea.setVisible(true);

					// Adding the Rem miles label and setting it to visible
					rightBottomPanel.add(remMilesLabel);
					remMilesLabel.setVisible(true);

					// Adding the rem miles text to visible and non editable
					remMiles.setText("" + mileRedeemer.getRemainingMiles());
					remMiles.setEditable(false);
					remMiles.setVisible(true);
					rightBottomPanel.add(remMiles);
				} else
					System.out
							.println("Please enter a number in the accumulated miles field");
			}
		});

	}

	/**
	 * main() method which handles all the user interaction thru GUI and Call
	 * methods of MileRedeemer class by creating a object instance.
	 * 
	 * @param args
	 *            []
	 * @return null
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		// To check the number of arguments that is passed to the method
		if (!(args[0].equals(""))) // The file from the file's location passed
									// thru the args is stored
			fileScanner = new Scanner(new File(args[0]));
		else {
			System.err
					.println("Provide only one argument containing the file's location.");
			System.exit(0);
		}

		// Methods of MileRedeemer class are called by the object's instance
		mileRedeemer.readDestinations(fileScanner);
		destinationCity = mileRedeemer.getCityNames();

		// Swing Utility method to synchronize with the user's action
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Create an instance of the test application
				new MileRedemptionApp();
			}
		});
	}
}