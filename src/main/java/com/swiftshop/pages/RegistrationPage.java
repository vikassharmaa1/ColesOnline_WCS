package com.swiftshop.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.swiftshop.main.FunLibrary;

public class RegistrationPage extends FunLibrary {

	public RegistrationPage enterFirstNameLastName() {
		Clear_Text(OR_OR.getProperty("Registration_First_Name_Text"), "Registration_First_Name_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_First_Name_Text"), first_name, "Registration_First_Name_Text");
		Clear_Text(OR_OR.getProperty("Registration_Last_Name_Text"), "Registration_Last_Name_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Last_Name_Text"), last_name, "Registration_Last_Name_Text");
		return this;
	}

	public RegistrationPage enterRandomEmailID() {
		username = emaild();
		System.setProperty("randomEmailAddress", username);
		testLog.info("Email ID is: " + username);
		Clear_Text(OR_OR.getProperty("Registration_Emailid_Text"), "Registration_Emailid_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Emailid_Text"), username, "Registration_Emailid_Text");
		return this;
	}

	public RegistrationPage enterEmailID(String username) {
		Sendkey_xpath(OR_OR.getProperty("Registration_Emailid_Text"), username, "Registration_Emailid_Text");
		return this;
	}

	public RegistrationPage enterPassword() {
		Clear_Text(OR_OR.getProperty("Registration_Password_Text"), "Registration_Password_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Password_Text"), password, "password");
		return this;
	}

	// Enter Mobile Number number
	public RegistrationPage enterMobileNumber(String mobNumber) {
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), mobNumber,
				"Registration_Mobile_Number_Field");
		return this;
	}

	// Suburb selection
	public RegistrationPage enterSuburb_Postcode(String Suburb) {
		Sendkey_xpath(OR_OR.getProperty("Suburb_Poscode_Input"), suburb, "Suburb_Poscode_Input");
		wait(3000);
		WebElement searchList = findElement("xpath", "Suburb_List");
		List<WebElement> options = searchList.findElements(By.tagName("li"));
		testLog.info(options.size());
		for (int i = 0; i < options.size(); i++) {
			if ((options.get(i).getText()).contains(suburbAddr)) {
				options.get(i).click();
				break;
			} else {
				testLog.info("Suburb is not found");
			}
		}

		return this;
	}

	public RegistrationPage enterDOB(String dd, String yyyy) { 
		Clear_Text(OR_OR.getProperty("Registration_DOB_Day_Text"), "Registration_DOB_Day_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_DOB_Day_Text"), dd, "Registration_DOB_Day_Text");
		Click_Button_Xpath(OR_OR.getProperty("Registration_DOB_Month_DropDown"), "Registration_DOB_Month_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("Registration_DOB_Month"), "Registration_DOB_Month");
		Clear_Text(OR_OR.getProperty("Registration_DOB_Year_Text"), "Registration_DOB_Year_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_DOB_Year_Text"), yyyy, "Registration_DOB_Year_Text");
		return this;
	}

	public RegistrationPage clickStartShoppingButton() {
		wait(2000); 
		Click_Button_Xpath(OR_OR.getProperty("Registration_Continue_Button"), "Registration_Continue_Button");
		wait(5000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 10);
		return this;
	}

	public RegistrationPage alertPopupHandler() {
		wait(2000);
		if (isElementPresent(OR_OR.getProperty("Session_Out_Alert_Popup"), "Session_Out_Alert_Popup")) {
			Click_Button_Xpath(OR_OR.getProperty("Session_Out_Alert_Popup_Login_Button"),
					"Session_Out_Alert_Popup_Login_Button");
			wait(2000);
			getPages.getLoginPage().clickNewToColesLink().enterFirstNameLastName().enterRandomEmailID().enterPassword()
					.clickStartShoppingButton();
		}
		return this;
	}

	public HomePage alertPopupHandler(String userType) {
		wait(2000);
		switch (userType) {
		case "newUser":

			if (isElementPresent(OR_OR.getProperty("Session_Out_Alert_Popup"), "Session_Out_Alert_Popup")) {
				Click_Button_Xpath(OR_OR.getProperty("Session_Out_Alert_Popup_Login_Button"),
						"Session_Out_Alert_Popup_Login_Button");
				wait(2000);
				getPages.getLoginPage().clickNewToColesLink().enterRandomEmailID().enterFirstNameLastName()
						.enterPassword().enterSuburb_Postcode("Suburb").enterMobileNumber(mobNumber)
						.clickStartShoppingButton();
			}
			break;
		case "businessUser":
			if (isElementPresent(OR_OR.getProperty("Session_Out_Alert_Popup"), "Session_Out_Alert_Popup")) {
				Click_Button_Xpath(OR_OR.getProperty("Session_Out_Alert_Popup_Login_Button"),
						"Session_Out_Alert_Popup_Login_Button");
				wait(2000);
				getPages.getLoginPage().clickNewToColesLink().enterRandomEmailID().enterFirstNameLastName()
						.clickBusinessUserCheckbox().enterBusinessName().enterABN().selectIndustry("Environmental")
						.enterPassword().enterSuburb_Postcode("Suburb").enterMobileNumber(mobNumber)
						.clickStartShoppingButton();
			}
			break;
		case "SubsFlybuysAndDOB":
			if (isElementPresent(OR_OR.getProperty("Session_Out_Alert_Popup"), "Session_Out_Alert_Popup")) {
				Click_Button_Xpath(OR_OR.getProperty("Session_Out_Alert_Popup_Login_Button"),
						"Session_Out_Alert_Popup_Login_Button");
				wait(2000);
				getPages.getLoginPage().clickNewToColesLink().enterFirstNameLastName().enterRandomEmailID()
						.enterDOB("10", "1990").enterPassword().clickStartShoppingButton();
			}
			break;
		case "businessDeliverySubscription":
			if (isElementPresent(OR_OR.getProperty("Session_Out_Alert_Popup"), "Session_Out_Alert_Popup")) {
				Click_Button_Xpath(OR_OR.getProperty("Session_Out_Alert_Popup_Login_Button"),
						"Session_Out_Alert_Popup_Login_Button");
				wait(2000);
				getPages.getLoginPage().clickNewToColesLink().enterFirstNameLastName().clickBusinessUserCheckbox()
						.enterBusinessName().enterABN().selectIndustry("Environmental").enterRandomEmailID()
						.enterPassword().clickStartShoppingButton();
			}
			break;
		}
		return new HomePage();
	}

	public RegistrationPage enterAddressDetails() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 2);
		Clear_Text(OR_OR.getProperty("Street_Address_Input"), "Street_Address_Input");
		Sendkey_xpath(OR_OR.getProperty("Street_Address_Input"), streetAddr, "Street_Address_Input");
		Clear_Text(OR_OR.getProperty("Suburb_Input"), "Suburb_Input");
		Sendkey_xpath(OR_OR.getProperty("Suburb_Input"), suburb, "Suburb_Input");
		Clear_Text(OR_OR.getProperty("Postcode_Input"), "Postcode_Input");
		Sendkey_xpath(OR_OR.getProperty("Postcode_Input"), postcode, "Postcode_Input");

		Select state_dropdown = new Select(driver.findElement(By.id("state-selection")));
		state_dropdown.selectByValue(state);

		Clear_Text(OR_OR.getProperty("Registration_Address_Nickname_Text"), "Registration_Address_Nickname_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Address_Nickname_Text"), nickname,
				"Registration_Address_Nickname_Text");
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), "0412345678",
				"Registration_Mobile_Number_Field");
		clickAddressContinueButton();
		wait(4000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 20);
		verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");
		Click_Button_Xpath(OR_OR.getProperty("Registration_Select_Address_HD"), "Registration_Select_Address_HD");
		return this;
	}

	public RegistrationPage validateAddressDetails() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Address_Name_Header"), "Address_Name_Header", 10, nickname);
		verify_xpath_contains_text(OR_OR.getProperty("Address_Name_Header"), nickname);
		verify_xpath_contains_text(OR_OR.getProperty("Street_Address_Text"), streetAddr.replace(",", " "));
		verify_xpath_contains_text(OR_OR.getProperty("Street_Address_Text"), suburb);
		verify_xpath_contains_text(OR_OR.getProperty("Street_Address_Text"), state);
		verify_xpath_contains_text(OR_OR.getProperty("Personal_Details_Text"), first_name);
		verify_xpath_contains_text(OR_OR.getProperty("Personal_Details_Text"), last_name);
		verify_xpath_contains_text(OR_OR.getProperty("Email_Id_Text"), System.getProperty("randomEmailAddress"));
		return this;
	}

	public RegistrationPage enterRegAddress(String Address, String StreetAddress) {

		String address_list = StreetAddress;
		String[] add_list = address_list.split(",");
		for (int i = 0; i <= add_list.length - 1; i++) {
			ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 2);
			Clear_Text(OR_OR.getProperty("Street_Address_Input"), "Street_Address_Input");
			Sendkey_xpath(OR_OR.getProperty("Street_Address_Input"), add_list[i], "Street_Address_Input");
			Clear_Text(OR_OR.getProperty("Suburb_Input"), "Suburb_Input");
			Sendkey_xpath(OR_OR.getProperty("Suburb_Input"), suburb, "Suburb_Input");
			Clear_Text(OR_OR.getProperty("Postcode_Input"), "Postcode_Input");
			Sendkey_xpath(OR_OR.getProperty("Postcode_Input"), postcode, "Postcode_Input");

			Select state_dropdown = new Select(driver.findElement(By.id("state-selection")));
			state_dropdown.selectByValue(state);

			Clear_Text(OR_OR.getProperty("Registration_Address_Nickname_Text"), "Registration_Address_Nickname_Text");
			Sendkey_xpath(OR_OR.getProperty("Registration_Address_Nickname_Text"), nickname,
					"Registration_Address_Nickname_Text");
			Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
			Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), "0412345678",
					"Registration_Mobile_Number_Field");
			Click_Button_Xpath(OR_OR.getProperty("Registration_Continue_Button_page2"),
					"Registration_Continue_Button_page2");
			wait(1000);
			ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 25);
			verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");

			int xpathCount = getXpathCount(OR_OR.getProperty("Registration_Valid_Address_List"),
					"Registration_Valid_Address_List");
			testLog.info("Total No. of  Valid address present in the list are:" + xpathCount);
			for (int invalue = 1; invalue <= xpathCount; invalue++) {
				String address_nval = OR_OR.getProperty("Registration_Valid_Address_N_List");
				String ivalue = Integer.toString(invalue);
				String add = address_nval;
				String add_1 = add.replace("nvalue", ivalue);
				String validAdd = get_xpath_text(add_1, "add_1");
				testLog.info("Valid Addresses " + invalue + " : " + validAdd);
			}
			if (Address.equals("ValidAdd")) {
				Click_Button_Xpath(OR_OR.getProperty("Registration_Enter_Different_Add"),
						"Registration_Enter_Different_Add");
			}
		}
		return this;
	}

	public RegistrationPage validateNoAddFoundText() {
		wait(3000);
		ExplicitWait(OR_OR.getProperty("Registration_No_Address_Found"), "Registration_No_Address_Found", 20);
		verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");
		verify_xpath_text(OR_OR.getProperty("Registration_No_Address_Found"), "No address found.");
		return this;
	}

	public RegistrationPage clickContinueShoppingBtn() {
		ExplicitWait(OR_OR.getProperty("Registration_shopping_button"), "Registration_shopping_button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Registration_shopping_button"), "Registration_shopping_button");

		boolean isflybuy = isElementPresent(OR_OR.getProperty("Link_Flybuys_Button"), "Link_Flybuys_Button");
		if (isflybuy) {
			Click_Button_Xpath(OR_OR.getProperty("Fly_buy_skip"), "Fly_buy_skip");
		}
		return this;
	}

	public RegistrationPage validateSuccessMsg() {
		ExplicitWait(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				"Registration_Account_Creation_Confirmation", 20);
		verify_xpath_text(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				OR_OR.getProperty("Registration_Account_Creation_Confirmation_Msg"));
		return this;
	}

	public RegistrationPage clickBusinessUserCheckbox() {
		ExplicitWait(OR_OR.getProperty("Name_Contact_Details_BusinessAcc_Checkbox"),
				"Name_Contact_Details_BusinessAcc_Checkbox", 5);
		Click_Button_Xpath(OR_OR.getProperty("Name_Contact_Details_BusinessAcc_Checkbox"),
				"Name_Contact_Details_BusinessAcc_Checkbox");
		return this;
	}

	public RegistrationPage enterBusinessName() {
		ExplicitWait(OR_OR.getProperty("Superbar_Busniess_Name"), "Superbar_Busniess_Name", 5);
		Sendkey_xpath(OR_OR.getProperty("Superbar_Busniess_Name"), OR_OR.getProperty("Business_Name"),
				"Superbar_Busniess_Name");
		return this;
	}

	public RegistrationPage enterABN() {
		ExplicitWait(OR_OR.getProperty("Superbar_ABN_Number"), "Superbar_ABN_Number", 5);
		Sendkey_xpath(OR_OR.getProperty("Superbar_ABN_Number"), abn, "Superbar_ABN_Number");
		return this;
	}

	public RegistrationPage selectIndustry(String industry) {
		Select industry_dropdown = new Select(driver.findElement(By.id("industry-type")));
		industry_dropdown.selectByValue(industry);
		return this;
	}

	public RegistrationPage enterInvalidAddress() {
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 2);
		Clear_Text(OR_OR.getProperty("Street_Address_Input"), "Street_Address_Input");
		Sendkey_xpath(OR_OR.getProperty("Street_Address_Input"), streetAddr, "Street_Address_Input");
		Clear_Text(OR_OR.getProperty("Suburb_Input"), "Suburb_Input");
		Sendkey_xpath(OR_OR.getProperty("Suburb_Input"), suburb1, "Suburb_Input");
		Clear_Text(OR_OR.getProperty("Postcode_Input"), "Postcode_Input");
		Sendkey_xpath(OR_OR.getProperty("Postcode_Input"), postcode, "Postcode_Input");
		Sendkey_xpath(OR_OR.getProperty("Registration_Address_Nickname_Text"), nickname,
				"Registration_Address_Nickname_Text");
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), mobNumber1,
				"Registration_Mobile_Number_Field");
		return this;
	}

	public RegistrationPage clickAddressContinueButton() {
		ExplicitWait(OR_OR.getProperty("Registration_Continue_Button_page2"), "Registration_Continue_Button_page2", 10);
		Click_Button_Xpath(OR_OR.getProperty("Registration_Continue_Button_page2"),
				"Registration_Continue_Button_page2");
		return this;
	}

	public RegistrationPage validateErrMsg() {
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
				"Please remove these characters from street address \"!\", \"$\"");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_2"),
				"Please remove these characters from suburb \"!\", \"%\"");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_3"),
				"Post code must not contain alphabets and special characters");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_4"),
				"Please remove these characters from the name for this address \"!\", \"@\", \"#\"");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_5"),
				"Please enter a mobile number beginning with \"02, 03, 04, 07\"");
		return this;
	}

	public RegistrationPage validatePasswordField() {
		// less than 8 characters
		Sendkey_xpath(OR_OR.getProperty("Registration_Password_Text"), "zxcvbnm", "password");
		clickStartShoppingButton();
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
				"Please ensure the password is at least 8 characters long");
		Clear_Text(OR_OR.getProperty("Registration_Password_Text"), "Registration_Password_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Password_Text"), "zxcvbnma", "password");
		clickStartShoppingButton();
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
				"Please ensure the password contains at least one number");
		Clear_Text(OR_OR.getProperty("Registration_Password_Text"), "Registration_Password_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Password_Text"), "zxcvbnma1", "password");
		clickStartShoppingButton();
		wait(2000);
		if (!isElementPresent(OR_OR.getProperty("Suburb_Poscode_Input"), "Suburb_Poscode_Input")) {
			testLog.error("Please check if the password entered is minimum 8 char and contains numeric");
			assertCheck("validatePasswordField",
					"Please check if the password entered is minimum 8 char and contains numeric",
					"Street_Address_Input");
		}

		/*
		 * if (!isElementPresent(OR_OR.getProperty("Street_Address_Input"),
		 * "Street_Address_Input")) { testLog.
		 * error("Please check if the password entered is minimum 8 char and contains numeric"
		 * ); assertCheck("validatePasswordField",
		 * "Please check if the password entered is minimum 8 char and contains numeric"
		 * , "Street_Address_Input"); }
		 */
		return this;
	}

	public RegistrationPage enterRDAddressDetails() {
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 2);
		Clear_Text(OR_OR.getProperty("Street_Address_Input"), "Street_Address_Input");
		Sendkey_xpath(OR_OR.getProperty("Street_Address_Input"), streetAddr, "Street_Address_Input");
		Clear_Text(OR_OR.getProperty("Suburb_Input"), "Suburb_Input");
		Sendkey_xpath(OR_OR.getProperty("Suburb_Input"), suburb, "Suburb_Input");
		Clear_Text(OR_OR.getProperty("Postcode_Input"), "Postcode_Input");
		Sendkey_xpath(OR_OR.getProperty("Postcode_Input"), postcode, "Postcode_Input");

		Select state_dropdown = new Select(driver.findElement(By.id("state-selection")));
		state_dropdown.selectByValue(state);

		Clear_Text(OR_OR.getProperty("Registration_Address_Nickname_Text"), "Registration_Address_Nickname_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Address_Nickname_Text"), nickname,
				"Registration_Address_Nickname_Text");
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), "0412345678",
				"Registration_Mobile_Number_Field");
		Click_Button_Xpath(OR_OR.getProperty("Registration_Continue_Button_page2"),
				"Registration_Continue_Button_page2");
		wait(1000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 20);
		verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");
		Click_Button_Xpath(OR_OR.getProperty("Registration_Select_Address_RD"), "Registration_Select_Address_RD");
		return this;
	}

	public RegistrationPage validateAccountCreationConfirmationPopup(String account_type) {
		ExplicitWait(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				"Registration_Account_Creation_Confirmation", 70);
		verify_xpath_text(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				"Your account has been created");
		switch (account_type) {
		case "HD":

			break;
		case "RD":
			verify_xpath_text(OR_OR.getProperty("Registration_Service_Type_Text"),
					"Remote delivery and Click & Collect are available in your area");
			break;
		default:
			testLog.error("Please select valid user type- HD or RD");
			assertCheck("validateAccountCreationConfirmationPopup", "Please select valid user type- HD or RD");
			break;
		}
		return this;
	}

	public RegistrationPage validateRDSuccessMsg() {
		ExplicitWait(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				"Registration_Account_Creation_Confirmation", 2);
		verify_xpath_text(OR_OR.getProperty("Registration_Account_Creation_Confirmation"),
				"Your account has been created");
		verify_xpath_text(OR_OR.getProperty("Registration_Service_Type_Text"),
				"Remote delivery and Click & Collect are available in your area");
		return this;
	}

	public RegistrationPage clickContinueShoppingWithOutSkipFlybuys() {
		ExplicitWait(OR_OR.getProperty("Registration_shopping_button"), "Registration_shopping_button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Registration_shopping_button"), "Registration_shopping_button");
		return this;
	}
}
