package com.swiftshop.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.swiftshop.main.FunLibrary;

public class ChooseDeliveryTimePage extends FunLibrary {

	public DeliverySlotPage clickHDSlotLink1() {
		verify_xpath_text(OR_OR.getProperty("Click_Collect_Form_Header"),
				OR_OR.getProperty("Click_Collect_Form_Header_Text"));
		Click_Button_Xpath(OR_OR.getProperty("Click_Collect_HD_Address1"), "Click_Collect_HD_Address1");
        wait(2000);
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickHDSlotLink2() {
		verify_xpath_text(OR_OR.getProperty("Click_Collect_Form_Header"),
				OR_OR.getProperty("Click_Collect_Form_Header_Text"));
		Click_Button_Xpath(OR_OR.getProperty("Click_Collect_HD_Address2"), "Click_Collect_HD_Address1");

		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickHDAddress(String HD_address_name) {
		wait(8000);
		By getChanges = null;
		if (isElementPresent(OR_OR.getProperty("Choose_Delivery_Change_Address_Add_An_Address"),
				"Choose_Delivery_Change_Address_Add_An_Address")) {
			getChanges = By.xpath(OR_OR.getProperty("HD_All_Addresses1"));
		} else if(isElementPresent(OR_OR.getProperty("SB_Choose_Delivery_Time_Heading"),
				"SB_Choose_Delivery_Time_Heading")){
			getChanges = By.xpath(OR_OR.getProperty("HD_All_Addresses"));
		}else {
			testLog.error("Addresses not visible. Sidebar in not loaded in time");
			assertCheck("clickHDAddress()", "Addresses not visible. Sidebar in not loaded in time. Please check screenshot");
		}
		
		java.util.List<WebElement> slot_elements = driver.findElements(getChanges);
		int total_addresses_count = slot_elements.size();
		testLog.info("Total HD addresses in the list are: " + total_addresses_count);
        wait(2000);
		for (WebElement el : slot_elements) {

			if (el.getText().toLowerCase().contains(HD_address_name.toLowerCase())) {
				el.click();
				wait(5000);
				break;
			}
		}
		if (isElementPresent(OR_OR.getProperty("Click_Collect_Form_Header"), "Click_Collect_Form_Header")) {
			testLog.error(HD_address_name + " is not found");
			assertCheck("clickHDAddress", HD_address_name + " is not found", "");
		}
		wait(5000);

		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickCCSlotLink1() {

		verify_xpath_text(OR_OR.getProperty("Click_Collect_Form_Header"),
				OR_OR.getProperty("Click_Collect_Form_Header_Text"));
		verify_xpath_text(OR_OR.getProperty("Click_Collect_Form_Title"),
				OR_OR.getProperty("Click_Collect_Form_Title_Text"));
		Click_Button_Xpath(OR_OR.getProperty("Click_Collect_Form_slot1"), "Click_Collect_Form_slot1");
		wait(4000);
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickChooseDifferentTimeLink() {
		Click_Button_Xpath(OR_OR.getProperty("CC_Selected_Choose_A_Different_Time_Link"),
				"CC_Selected_Choose_A_Different_Time_Link");
		wait(4000);

		return new DeliverySlotPage();
	}

	public ChooseDeliveryTimePage clickChangeLink() {
		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Time_Change_Link"), "Choose_Delivery_Time_Change_Link", 5);
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Time_Change_Link"), "Choose_Delivery_Time_Change_Link");
		verify_xpath_text(OR_OR.getProperty("HD_Selected_Change_Address_List_Header"),
				"How would you like to get your shopping?");

		return this;
	}

	public ChooseDeliveryTimePage clickChangeDelivery() {
		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Time_Change_Link"), "Choose_Delivery_Time_Change_Link", 5);
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Time_Change_Link"), "Choose_Delivery_Time_Change_Link");
		return this;
	}

	public String selectOtherThanSelctedHDAddress() {

		String selectedAddress = "";
		WebElement el = null;
		String address = "";
		ExplicitWait(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"), "Geo_Location_Bar_Localised_Suburb", 20);
		selectedAddress = get_xpath_text(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"),
				"Geo_Location_Bar_Localised_Suburb");
		testLog.info("currently user account is localise to:" + selectedAddress);
		wait(5000);
		List<WebElement> HdAddresses = driver
				.findElements(By.xpath(OR_OR.getProperty("Click_collect_All_HD_Addresses")));
		Iterator<WebElement> it = HdAddresses.iterator();
		while (it.hasNext()) {
			el = it.next();
			address = el.getText();
			wait(5000);
			if (!(address.contains(selectedAddress))) {
				el.click();
				ExplicitWait(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link", 20);
				break;
			}
		}
		return address;
	}

	public ChooseDeliveryTimePage validateDeliveryInfoMsg() {
		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Info_Msg"), "Choose_Delivery_Info_Msg", 5);
		verify_xpath_text(OR_OR.getProperty("Choose_Delivery_Info_Msg"),
				OR_OR.getProperty("Choose_Delivery_Info_Msg_Text"));

		return this;

	}

	public ChooseDeliveryTimePage validateDeliveryLinkText() {
		verify_xpath_text(OR_OR.getProperty("View_Delivery_Collection_Times_Link"),
				OR_OR.getProperty("View_Delivery_Collection_Times_Link_Text"));

		return this;

	}

	public LoginPage clickViewDeliveryCollectionLink() {

		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Info_Msg"), "Choose_Delivery_Info_Msg", 2);
		Click_Button_Xpath(OR_OR.getProperty("View_Delivery_Collection_Times_Link"),
				"View_Delivery_Collection_Times_Link");

		return new LoginPage();

	}

	public ChooseDeliveryTimePage validateRDAddress() {
		wait(3000);
		String rdAddress = get_xpath_text(OR_OR.getProperty("RD_Address_Choose_Delivery"),
				"RD_Address_Choose_Delivery");
		if (rdAddress.contains(nickname)) {
			testLog.info("Registeed RD Address contains: " + nickname);
		} else {
			testLog.error("Registeed RD Address NOT contains: " + nickname);
			assertCheck("validateRDAddress", "RD address does not contains nickname");
		}
		if (rdAddress.contains(streetAddr)) {
			testLog.info("Registeed RD Address contains: " + streetAddr);
		} else {
			testLog.error("Registeed RD Address NOT contains: " + streetAddr);
			assertCheck("validateRDAddress", "RD address does not contains street address");
		}
		if (rdAddress.contains(suburb)) {
			testLog.info("Registeed RD Address contains: " + suburb);
		} else {
			testLog.error("Registeed RD Address NOT contains: " + suburb);
			assertCheck("validateRDAddress", "RD address does NOT contains suburb");
		}
		if (rdAddress.contains(postcode)) {
			testLog.info("Registeed RD Address contains: " + postcode);
		} else {
			testLog.error("Registeed RD Address NOT contains: " + postcode);
			assertCheck("validateRDAddress", "RD address does NOT contains postcode");
		}
		if (rdAddress.contains(state)) {
			testLog.info("Registeed RD Address contains: " + state);
		} else {
			testLog.error("Registeed RD Address NOT contains: " + state);
			assertCheck("validateRDAddress", "RD address does NOT contains state");
		}
		return this;
	}

	public String selectRDAddress() {

		String selectedAddress = "";
		String address = "";
		ExplicitWait(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"), "Geo_Location_Bar_Localised_Suburb", 12);
		selectedAddress = get_xpath_text(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"),
				"Geo_Location_Bar_Localised_Suburb");
		testLog.info("currently user account is localise to:" + selectedAddress);

		ExplicitWait(OR_OR.getProperty("Click_Collect_RD_Address1_Text"), "Click_Collect_RD_Address1_Text", 2);
		wait(5000);
		address = get_xpath_text(OR_OR.getProperty("Click_Collect_RD_Address1_Text"), "Click_Collect_RD_Address1_Text");
		testLog.info(address);
		Click_Button_Xpath(OR_OR.getProperty("Click_Collect_RD_Address1"), "Click_Collect_RD_Address1");
		ExplicitWait(OR_OR.getProperty("Toll_Perkins_Address"), "Toll_Perkins_Address", 2);
		Click_Button_Xpath(OR_OR.getProperty("Toll_Perkins_Address"), "Toll_Perkins_Address");
		ExplicitWait(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link", 20);

		return address;
	}

	public String selectCCAddress() {
		String selectedAddress = "";
		String address = "";
		ExplicitWait(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"), "Geo_Location_Bar_Localised_Suburb", 12);
		selectedAddress = get_xpath_text(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"),
				"Geo_Location_Bar_Localised_Suburb");
		testLog.info("currently user account is localise to:" + selectedAddress);

		ExplicitWait(OR_OR.getProperty("Click_Collect_Form_slot1"), "Click_Collect_Form_slot1", 2);
		wait(5000);
		address = get_xpath_text(OR_OR.getProperty("Click_Collect_Form_slot1"), "Click_Collect_Form_slot1");
		testLog.info(address);
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Click_Collect_Form_slot1"), "Click_Collect_Form_slot1");
		ExplicitWait(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link", 20);
		return address;
	}

	public ChooseDeliveryTimePage validateAddressTitle() {
		ExplicitWait(OR_OR.getProperty("Click_Collect_HD_Address1"), "Click_Collect_HD_Address1", 10);
		wait(1000);
		verify_xpath_text(OR_OR.getProperty("Click_Collect_Form_Header"),
				OR_OR.getProperty("Click_Collect_Form_Header_Text"));
		verify_xpath_contains_text(OR_OR.getProperty("Choose_Delivery_Content"),
				OR_OR.getProperty("Choose_Delivery_Click_Collect_Shopping_Title_Text"));
		verify_xpath_contains_text(OR_OR.getProperty("Choose_Delivery_Content"),
				OR_OR.getProperty("Choose_Delivery_HD_Address_Title_Text"));
		verify_xpath_contains_text(OR_OR.getProperty("Choose_Delivery_Content"),
				OR_OR.getProperty("Choose_Delivery_RD_Address_Title_Text"));
		return this;
	}

	public ChooseDeliveryTimePage validateAddress(String address) {

		switch (address) {
		case "HD1":
			if (isElementPresent(OR_OR.getProperty("Choose_Delivery_First_HD_Address_Details"),
					"Choose_Delivery_First_HD_Address_Details")) {
				String hdAddress1 = get_xpath_text(OR_OR.getProperty("Choose_Delivery_First_HD_Address_Details"),
						"Choose_Delivery_First_HD_Address_Details");
				testLog.info("First registered HD address is: => " + hdAddress1);
				String streetAddress = OR_OR.getProperty("First_HD_Street_Address");
				String suburb = OR_OR.getProperty("First_HD_Suburb");
				String state = OR_OR.getProperty("First_HD_State");
				String postCode = OR_OR.getProperty("First_HD_Postcode");

				if (hdAddress1.contains(streetAddress)) {
					testLog.info("Registered HD address contains: " + streetAddress);
				} else {
					testLog.error("Registered HD address NOT contains: " + streetAddress);
					assertCheck("validateAddress", "Registered HD address not contains street address");
				}
				if (hdAddress1.contains(suburb)) {
					testLog.info("Registered HD address contains: " + suburb);
				} else {
					testLog.error("Registered HD address NOT contains: " + suburb);
					assertCheck("validateAddress", "Registered HD address not contains suburb");
				}
				if (hdAddress1.contains(state)) {
					testLog.info("Registered HD address contains: " + state);
				} else {
					testLog.error("Registered HD address NOT contains: " + state);
					assertCheck("validateAddress", "Registered HD address not contains state");
				}
				if (hdAddress1.contains(postCode)) {
					testLog.info("Registered HD address contains: " + postCode);
				} else {
					testLog.error("Registered HD address NOT contains: " + postCode);
					assertCheck("validateAddress", "Registered HD address not contains postcode");
				}
			} else {
				testLog.error("Registered HD address NOT present");
				assertCheck("validateAddress", "Registered HD address not present");
			}
			break;

		case "RD1":
			if (isElementPresent(OR_OR.getProperty("Choose_Delivery_First_RD_Address_Details"),
					"Choose_Delivery_First_RD_Address_Details")) {
				String rdAddress1 = get_xpath_text(OR_OR.getProperty("Choose_Delivery_First_RD_Address_Details"),
						"Choose_Delivery_First_RD_Address_Details");
				testLog.info("First registered RD address is: => " + rdAddress1);
				String streetAddress = OR_OR.getProperty("First_RD_Street_Address");
				String suburb = OR_OR.getProperty("First_RD_Suburb");
				String state = OR_OR.getProperty("First_RD_State");
				String postCode = OR_OR.getProperty("First_RD_Postcode");

				if (rdAddress1.contains(streetAddress)) {
					testLog.info("Registered RD address contains: " + streetAddress);
				} else {
					testLog.error("Registered RD address NOT contains: " + streetAddress);
					assertCheck("validateAddress", "Registered RD address not contains street address");
				}
				if (rdAddress1.contains(suburb)) {
					testLog.info("Registered RD address contains: " + suburb);
				} else {
					testLog.error("Registered RD address NOT contains: " + suburb);
					assertCheck("validateAddress", "Registered RD address not contains suburb");
				}
				if (rdAddress1.contains(state)) {
					testLog.info("Registered RD address contains: " + state);
				} else {
					testLog.error("Registered RD address NOT contains: " + state);
					assertCheck("validateAddress", "Registered RD address not contains state");
				}
				if (rdAddress1.contains(postCode)) {
					testLog.info("Registered RD address contains: " + postCode);
				} else {
					testLog.error("Registered RD address NOT contains: " + postCode);
					assertCheck("validateAddress", "Registered RD address not contains postcode");
				}
			} else {
				testLog.error("Registered RD address NOT present");
				assertCheck("validateAddress", "Registered RD address not present");
			}
			break;
		}
		return this;
	}

	public DeliverySlotPage clickFirstHDAddressLink() {
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_First_HD_Address"), "Choose_Delivery_First_HD_Address");
		wait(2000);
		return new DeliverySlotPage();
	}

	public ChooseDeliveryTimePage clickFirstRDAddressLink() {
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_First_RD_Address"), "Choose_Delivery_First_RD_Address");
		wait(2000);
		return this;
	}

	public String getRDProviderAddress() {
		String rdAddress = " ";
		ExplicitWait(OR_OR.getProperty("RD_First_Remote_Provider_Address"), "RD_First_Remote_Provider_Address", 5);
		if (isElementPresent(OR_OR.getProperty("RD_First_Remote_Provider_Address"),
				"RD_First_Remote_Provider_Address")) {
			rdAddress = get_xpath_text(OR_OR.getProperty("RD_First_Remote_Provider_Address_Text"),
					"RD_First_Remote_Provider_Address_Text");
		}
		return rdAddress;
	}

	public DeliverySlotPage selectNextRDProviderAddress() {
		ExplicitWait(OR_OR.getProperty("Next_RD_Provider"), "Next_RD_Provider", 5);
		Click_Button_Xpath(OR_OR.getProperty("Next_RD_Provider"), "Next_RD_Provider");
		wait(4000);
		return new DeliverySlotPage();
	}

	public DeliverySlotPage selectFirstRDProviderAddress() {
		ExplicitWait(OR_OR.getProperty("RD_First_Remote_Provider_Address"), "RD_First_Remote_Provider_Address", 5);
		Click_Button_Xpath(OR_OR.getProperty("RD_First_Remote_Provider_Address"), "RD_First_Remote_Provider_Address");
		wait(4000);
		return new DeliverySlotPage();
	}

	public ChooseDeliveryTimePage clickAddAnAddress() {
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Add_An_Address"), "Choose_Delivery_Add_An_Address");
		return this;
	}

	public ChooseDeliveryTimePage enterNewDeliveryDetails(String nickname, String dIMessage) {
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
		Clear_Text(OR_OR.getProperty("ChooseDelivery_Delivery_Instructions_Textbox"),
				"ChooseDelivery_Delivery_Instructions_Textbox");
		Sendkey_xpath(OR_OR.getProperty("ChooseDelivery_Delivery_Instructions_Textbox"), dIMessage,
				"ChooseDelivery_Delivery_Instructions_Textbox");
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Add_An_Address_Continue"),
				"Choose_Delivery_Add_An_Address_Continue");
		wait(5000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 20);
		verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");
		Click_Button_Xpath(OR_OR.getProperty("Registration_Select_Address_HD"), "Registration_Select_Address_HD");
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), "0412345678",
				"Registration_Mobile_Number_Field");
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Add_An_Address_Continue"),
				"Choose_Delivery_Add_An_Address_Continue");
		wait(5000);
		return this;
	}

	public ChooseDeliveryTimePage clickAddress(String address) {
		if (address.contains("HD")) {
			ExplicitWait(OR_OR.getProperty("Choose_Delivery_HD_Address1"), "Choose_Delivery_HD_Address1", 5);
			Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_HD_Address1"), "Choose_Delivery_HD_Address1");
		} else {
			// TODO: implement in future if required
		}
		wait(4000);
		return this;
	}

	public DeliverySlotPage clickSecondHDAddressLink() {
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Second_HD_Address"), "Choose_Delivery_Second_HD_Address");
		wait(2000);
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickCCLocationLink() {
		Click_Button_Xpath(OR_OR.getProperty("SuperBarSelectCCLocationLink"),
				"SuperBarSelectCCLocationLink");
		wait(4000);

		return new DeliverySlotPage();
	}

}
