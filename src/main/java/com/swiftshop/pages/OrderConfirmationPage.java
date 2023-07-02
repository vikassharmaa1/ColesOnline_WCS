package com.swiftshop.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.swiftshop.main.FunLibrary;

public class OrderConfirmationPage extends FunLibrary {

	public OrderConfirmationPage verifyOrderConfirmation() {
		wait(30000);
		ExplicitWait(OR_OR.getProperty("Your_Order_has_been_placed_Text"), "Your_Order_has_been_placed_Text", 20);
		verify_xpath_text(OR_OR.getProperty("Your_Order_has_been_placed_Text"), "Your order has been placed.");
		return new OrderConfirmationPage();
	}

	public HomePage clickReturnToHome() {
		ExplicitWait(OR_OR.getProperty("Order_ConfirmationPage_Back_To_HomePage_Link"),
				"Order_ConfirmationPage_Back_To_HomePage_Link", 20);
		Click_Button_Xpath(OR_OR.getProperty("Order_ConfirmationPage_Back_To_HomePage_Link"),
				"Order_ConfirmationPage_Back_To_HomePage_Link");
		wait(3000);
		ExplicitWait(OR_OR.getProperty("SearchField_InsideText_Path"), "SearchField_InsideText_Path", 5);
		return new HomePage();
	}

	public OrderConfirmationPage selectValueFromAuthenticationResultDropdown(String textValue) {
		// Selecting value from ACS emulator drop-down
		wait(20000);
		if ((isElementPresent(OR_OR.getProperty("ACS_Emulator_IFrame"), "ACS_Emulator_IFrame"))) {
			switch_Frame("ACS_Emulator_IFrame");
			selectValueFromDropDown(OR_OR.getProperty("ACS_Emulator_DropDown"), textValue);
			// selectAuthResultDropDownValue();
			// Click at submit button
			clickSubmitOrderButton_ACS();
			// Switch to default frame
			switch_To_DefaultFrame();
		} else {
			testLog.info("Either payment card is 3DS Enabled - frictionless or 3DS Disabled");
		}
		return this;
	}

	public OrderConfirmationPage clickSubmitOrderButton_ACS() {
		ExplicitWait(OR_OR.getProperty("ACS_Emulator_Submit_Button"), "ACS_Emulator_Submit_Button", 20);
		Click_Button_Xpath(OR_OR.getProperty("ACS_Emulator_Submit_Button"), "ACS_Emulator_Submit_Button");
		return this;
	}

	public OrderConfirmationPage selectAuthResultDropDownValue() {
		ExplicitWait(OR_OR.getProperty("ACS_Emulator_DropDown"), "ACS_Emulator_DropDown", 20);
		WebElement emulatorDropdwon = getWebElement("ACS_Emulator_DropDown");
		performActions().moveToElement(emulatorDropdwon).click().pause(1000).sendKeys(acsAuthResult).sendKeys(Keys.TAB)
				.build().perform();
		testLog.info("Element is found and selected: " + "ACS_Emulator_DropDown");
		return this;
	}

}
