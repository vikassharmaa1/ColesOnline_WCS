package com.swiftshop.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.swiftshop.main.FunLibrary;

public class PaymentPage extends FunLibrary {
	String winHandle = "";

	public void payByEFTPOS() {
		ExplicitWait(OR_OR.getProperty("Pay_by_EFTPOS_on_collection_button"), "Pay_by_EFTPOS_on_collection_button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Pay_by_EFTPOS_on_collection_button"),
				"Pay_by_EFTPOS_on_collection_button");
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_PlaceOrder_Button"), "AlmostDonePage_PlaceOrder_Button", 10);
	}

	public PaymentPage clickPayUsingCard() {
		ExplicitWait(OR_OR.getProperty("Pay_by_Credit_Card_button"), "Pay_by_Credit_Card_button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Pay_by_Credit_Card_button"), "Pay_by_Credit_Card_button");
		wait(10000);
		ExplicitWait(OR_OR.getProperty("Checkout_Flow_IPG_Header"), "Checkout_Flow_IPG_Header", 20);
		verify_xpath_text(OR_OR.getProperty("Checkout_Flow_IPG_Header"), "Enter new credit / debit card details");
		switch_Frame("IPG_iFrame_id");
		return this;
	}

	public PaymentPage enterCardNumber() {
		switch_Frame("MPGS_Card_Number_IFrame");
		if (isElementPresent(OR_OR.getProperty("MPGS_Card_Number"), "MPGS_Card_Number")) {
			Sendkey_xpath(OR_OR.getProperty("MPGS_Card_Number"), cardno, "MPGS_Card_Number");
			switch_To_DefaultFrame();
		} else {
			Sendkey_xpath(OR_OR.getProperty("IPG_Card_Number"), cardno, "IPG_Card_Number");
		}
		return this;
	}

	public PaymentPage selectExpiryMonth() {
		switch_Frame("MPGS_Expiry_Date_Month_IFrame");
		if (isElementPresent(OR_OR.getProperty("MPGS_Expiry_Date_Month_Dropdown"), "MPGS_Expiry_Date_Month_Dropdown")) {
			Select dropdown1 = new Select(
					driver.findElement(By.xpath(OR_OR.getProperty("MPGS_Expiry_Date_Month_Dropdown"))));
			wait(1000);
			dropdown1.selectByValue(expiry_month);
			switch_To_DefaultFrame();
		} else {
			Select dropdown1 = new Select(
					driver.findElement(By.xpath(OR_OR.getProperty("IPG_Expiry_Date_Month_Dropdown"))));
			wait(1000);
			dropdown1.selectByValue(expiry_month);
		}
		return this;
	}

	public PaymentPage selectExpiryYear() {
		switch_Frame("MPGS_Expiry_Date_Year_IFrame");
		if (isElementPresent(OR_OR.getProperty("MPGS_Expiry_Date_Year_Dropdown"), "MPGS_Expiry_Date_Year_Dropdown")) {
			Select dropdown2 = new Select(
					driver.findElement(By.xpath(OR_OR.getProperty("MPGS_Expiry_Date_Year_Dropdown"))));
			wait(1000);
			dropdown2.selectByValue(expiry_year);
			switch_To_DefaultFrame();
		} else {
			Select dropdown2 = new Select(
					driver.findElement(By.xpath(OR_OR.getProperty("IPG_Expiry_Date_Year_Dropdown"))));
			wait(1000);
			dropdown2.selectByValue(expiry_year);
		}
		return this;
	}

	public PaymentPage enterCVV() {
		switch_Frame("MPGS_CVV_IFrame");
		if (isElementPresent(OR_OR.getProperty("MPGS_CVV"), "MPGS_CVV")) {
			Sendkey_xpath(OR_OR.getProperty("MPGS_CVV"), cvv, "MPGS_CVV");
			switch_To_DefaultFrame();
		} else {
			Sendkey_xpath(OR_OR.getProperty("IPG_CVV"), cvv, "IPG_CVV");
		}
		return this;
	}

	public PaymentPage checkRememberCheckbox() {
		Click_Button_Xpath(OR_OR.getProperty("IPG_Remember_Me_Checkbox"), "IPG_Remember_Me_Checkbox");
		return this;
	}

	public PaymentPage clickContinueButton() {
		Click_Button_Xpath(OR_OR.getProperty("IPG_Continue_Button"), "IPG_Continue_Button");
		switch_To_DefaultFrame();
		wait(10000);
		return this;
	}

	public PaymentPage clickPayUsingPaypal() {
		ExplicitWait(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button");

		// switch window
		wait(5000);
		winHandle = driver.getWindowHandle();
		for (String newWin : driver.getWindowHandles()) {
			driver.switchTo().window(newWin);
		}
		wait(5000);
		return this;
	}

	public PaymentPage enterPaypalID() {
		wait(5000);
		switch_Frame("Payment_Page_Paypal_Iframe");
		if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Email_Field"), "Paypal_Popup_Window_Email_Field")) {
			ExplicitWait(OR_OR.getProperty("Paypal_Popup_Window_Email_Field"), "Paypal_Popup_Window_Email_Field", 20);
			Sendkey_xpath(OR_OR.getProperty("Paypal_Popup_Window_Email_Field"), paypal_id,
					"Paypal_Popup_Window_Email_Field");
		}
		return this;
	}

	public PaymentPage clickNext() {
		if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Next_Button"), "Paypal_Popup_Window_Next_Button")) {
			Click_Button_Xpath(OR_OR.getProperty("Paypal_Popup_Window_Next_Button"), "Paypal_Popup_Window_Next_Button");
			wait(3000);
		}
		return this;
	}

	public PaymentPage enterPaypalPassword() {
		if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Password_Field"),
				"Paypal_Popup_Window_Password_Field")) {
			Sendkey_xpath(OR_OR.getProperty("Paypal_Popup_Window_Password_Field"), paypal_password,
					"Paypal_Popup_Window_Password_Field");
		}
		return this;
	}

	public PaymentPage clickPaypalLoginButton() {
		if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Login_Button"),
				"Paypal_Popup_Window_Login_Button")) {
			Click_Button_Xpath(OR_OR.getProperty("Paypal_Popup_Window_Login_Button"),
					"Paypal_Popup_Window_Login_Button");
			switch_To_DefaultFrame();
			wait(5000);
		}
		return this;
	}

	public PaymentPage clickAgreeAndContinueButton() {
		if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Agree_Continue_Button"),
				"Paypal_Popup_Window_Agree_Continue_Button")) {
			scrollTo_Xpath(OR_OR.getProperty("Paypal_Popup_Window_Agree_Continue_Button"),
					"Paypal_Popup_Window_Agree_Continue_Button", 1);
			Click_Button_Xpath(OR_OR.getProperty("Paypal_Popup_Window_Agree_Continue_Button"),
					"Paypal_Popup_Window_Agree_Continue_Button");
			wait(5000);
		}
		driver.switchTo().window(winHandle);
		return this;
	}
	
	public PaymentPage clickNotYouLink(){
		
		if(isElementPresent(OR_OR.getProperty("Paymant_Page_Paypal_Not_You_Link"),"Paypal_Popup_Window_Not_You_Link")){
			Click_Button_Xpath(OR_OR.getProperty("Paymant_Page_Paypal_Not_You_Link"),
					"Paymant_Page_Paypal_Not_You_Link");
			ExplicitWait(OR_OR.getProperty("Paypal_Popup_Window_Email_Field"), "Paypal_Popup_Window_Email_Field", 20);
			wait(3000);
		}
		return this;
	}

	public PaymentPage clickPayUsigEFPOTS() {
		Click_Button_Xpath(OR_OR.getProperty("Pay_by_EFTPOS_on_collection_button"),
				"Pay_by_EFTPOS_on_collection_button");
		wait(10000);
		return this;
	}

	public PaymentPage validatedEftposPresence() {
		ExplicitWait(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button", 10);
		boolean creditCardButton = isElementPresent(OR_OR.getProperty("Pay_by_Credit_Card_button"),
				"Pay_by_Credit_Card_button");
		boolean payPalButton = isElementPresent(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button");
		boolean eftposButton = isElementPresent(OR_OR.getProperty("Pay_by_EFTPOS_on_collection_button"),
				"Pay_by_EFTPOS_on_collection_button");

		if (creditCardButton == true) {
			testLog.info("Pay by Credit Card button is available for Subscriptions");

		} else {
			testLog.error(
					"Opps!! Pay by Credit Card button is NOT available for Subscriptions, Something went WRONG! ");
			assertCheck("validatedEftposPresence",
					"Opps!! Pay by Credit Card button is NOT available for Subscriptions, Something went WRONG! ");
			;
		}

		if (payPalButton == true) {
			testLog.info("Pay by Paypal button is available for Subscriptions");

		} else {
			testLog.error("Opps!! Pay by Paypal button is NOT available for Subscriptions, Something went WRONG! ");
			assertCheck("validatedEftposPresence",
					"Opps!! Pay by Paypal button is NOT available for Subscriptions, Something went WRONG! ");
			;
		}

		if (eftposButton == false) {
			testLog.info("Pay by EFTPOS button is NOT available for Subscriptions, **Expected behaviour**");

		} else {
			testLog.error("Opps!! Pay by EFTPOS button is available for Subscriptions, Something went WRONG! ");
			assertCheck("validatedEftposPresence",
					"Opps!! Pay by EFTPOS button is available for Subscriptions, Something went WRONG! ");
			;
		}
		return this;
	}

	public PaymentPage clickColesLogo() throws AWTException, InterruptedException {
		Click_Button_Xpath(OR_OR.getProperty("Coles_Logo_path"), "Coles_Logo_path");
		wait(3000);
		return this;

	}

	public AlmostDonePage selectAnyAvailablePaymentOption() {
		cardno = OR_OR.getProperty("IPG_Card_Number_Text4");
		expiry_month = "01";
		expiry_year = "2025";
		cvv = "123";
		paypal_id = "testdata001@mailinator.com";
		paypal_password = "passw0rd";

		ExplicitWait(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header", 20);
		String checkout_header = get_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header");

		if (checkout_header.equals("How would you like to pay?")) {
			if (isElementPresent(OR_OR.getProperty("Pay_by_EFTPOS_on_collection_button"),
					"Pay_by_EFTPOS_on_collection_button")) {
				clickPayUsigEFPOTS();
			} else if (isElementPresent(OR_OR.getProperty("Pay_by_Credit_Card_button"), "Pay_by_Credit_Card_button")) {
				clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear().enterCVV()
						.clickContinueButton();
			} else {
				clickPayUsingPaypal().enterPaypalID().clickNext().enterPaypalPassword().clickPaypalLoginButton()
						.clickAgreeAndContinueButton();
			}
		} else if (checkout_header.equals("Please confirm your order details")) {
			testLog.warn("User is on Almost Done page. Skipping Payment page!");
		} else {
			testLog.error("Opps!! Something went wrong, payment page is NOT loaded");
			assertCheck("selectAnyAvailablePaymentOption", "Opps!! Something went wrong, payment page is NOT loaded");
		}
		return new AlmostDonePage();
	}

	public boolean isAtPaymentPage() {
		// ExplicitWait((OR_OR.getProperty("Payment_Page_Credit_Card")),
		// "Payment_Page_Credit_Card", 5);
		wait(2000);
		boolean flag = isElementPresent(OR_OR.getProperty("Payment_Page_Credit_Card"), "Payment_Page_Credit_Card");
		// Pay_by_Credit_Card_button
		if (flag == true) {
			return true;
		} else {
			return false;
		}
	}

	public PaymentPage clickUsingDifferentPaypalAccount() {
		ExplicitWait(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button", 10);
		if (isElementPresent(OR_OR.getProperty("Payment_Page_Use_Different_Paypal_Account"),
				"Payment_Page_Use_Different_Paypal_Account")) {

			Click_Button_Xpath(OR_OR.getProperty("Payment_Page_Use_Different_Paypal_Account"),
					"Payment_Page_Use_Different_Paypal_Account");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
			}
			if (isElementPresent(OR_OR.getProperty("Paypal_Popup_Window_Not_You_Link"),
					"Paypal_Popup_Window_Not_You_Link")) {
				Click_Button_Xpath(OR_OR.getProperty("Paypal_Popup_Window_Not_You_Link"),
						"Paypal_Popup_Window_Not_You_Link");
				wait(5000);
			}
			wait(5000);
		} else {
			Click_Button_Xpath(OR_OR.getProperty("Pay_by_PayPal_button"), "Pay_by_PayPal_button");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
			}
			wait(5000);
		}
		return this;
	}

	public PaymentPage validateKeepShoppingLink() {
		ExplicitWait(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"), "Payment_Page_Keep_Shopping_Link", 5);
		verify_xpath_text(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"),
				OR_OR.getProperty("Payment_Page_Keep_Shopping_Link_Text"));
		return this;
	}

	public HomePage clickKeepShoppingLink() {
		if (isElementPresent(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"), "Payment_Page_Keep_Shopping_Link")) {
			Click_Button_Xpath(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"), "Payment_Page_Keep_Shopping_Link");
		} else {
			testLog.error("Opps!! Keep shopping link is NOT present at payment page");
			assertCheck("clickKeepShoppingLink", "Keep shopping link is NOT present at payment page");
		}
		ExplicitWait(OR_OR.getProperty("Everything_Tab"), "Everything_Tab", 20);
		return new HomePage();
	}

	public PaymentPage enterInvalidCard_CheckErrorMessage() {
		String[] sku_list = cardno.split(",");
		for (int i = 0; i < sku_list.length; i++) {
			switch_Frame("MPGS_Card_Number_IFrame");
			if (isElementPresent(OR_OR.getProperty("MPGS_Card_Number"), "MPGS_Card_Number")) {
				Clear_Text(OR_OR.getProperty("MPGS_Card_Number"), "MPGS_Card_Number");
				Sendkey_xpath(OR_OR.getProperty("MPGS_Card_Number"), sku_list[i], "MPGS_Card_Number");
				switch_To_DefaultFrame();
			} else {
				Sendkey_xpath(OR_OR.getProperty("IPG_Card_Number"), sku_list[i], "IPG_Card_Number");
			}
			selectExpiryMonth();
			selectExpiryYear();
			enterCVV();
			clickContinueButton();
			wait(5000);
			validateInvalidCardErrorMessage();
		}
		return this;
	}

	public PaymentPage validateInvalidCardErrorMessage() {
		ExplicitWait(OR_OR.getProperty("Payment_Page_Credit_Card_Error_Msg"), "Payment_Page_Credit_Card_Error_Msg", 10);
		verify_xpath_text(OR_OR.getProperty("Payment_Page_Credit_Card_Error_Msg"),
				OR_OR.getProperty("Payment_Page_Credit_Card_Error_Msg_Text"));
		wait(1000);
		return this;
	}

	public PaymentPage clickCancel() {
		ExplicitWait(OR_OR.getProperty("Payment_Page_Cancel_Link"), "Payment_Page_Cancel_Link", 5);
		Click_Button_Xpath(OR_OR.getProperty("Payment_Page_Cancel_Link"), "Payment_Page_Cancel_Link");
		wait(2000);
		return this;
	}

	public HomePage clickKeepShopping() {
		if (isElementPresent(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"), "Payment_Page_Keep_Shopping_Link")) {
			Click_Button_Xpath(OR_OR.getProperty("Payment_Page_Keep_Shopping_Link"), "Payment_Page_Keep_Shopping_Link");
		} else {
			testLog.error("Keep shopping link is NOT present at Payment page");
			assertCheck("clickKeepShopping", "Keep shopping link is NOT present at payment page");
		}
		ExplicitWait(OR_OR.getProperty("Everything_Tab"), "Everything_Tab", 20);
		return new HomePage();
	}

	public PaymentPage validatePaymentCardIssue() {
		wait(30000);
		// ExplicitWait(OR_OR.getProperty("ADP_Checkout_Payment_Card_Issue"),
		// "ADP_Checkout_Payment_Card_Issue", 30);
		if (isElementPresent(OR_OR.getProperty("ADP_Checkout_Payment_Card_Issue"), "ADP_Checkout_Payment_Card_Issue")) {
			verify_xpath_text(OR_OR.getProperty("ADP_Checkout_Payment_Card_Issue"),
					OR_OR.getProperty("ADP_Checkout_Payment_Card_Issue_Text"));
		} else {
			testLog.error("Order should not be placed because we have entered declined card number in payment page");
			assertCheck("validatePaymentCardIssue",
					"Order should not be placed because we have entered declined card number in payment page");
		}
		return this;
	}

	public PaymentPage clickGoToPaymentOpt() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_Checkout_Go_To_Payment_Options_Button"),
				"ADP_Checkout_Go_To_Payment_Options_Button");
		// ExplicitWait(OR_OR.getProperty("Pay_by_Credit_Card_button"),
		// "Pay_by_Credit_Card_button", 50);
		wait(10000);
		return this;
	}

	public PaymentPage validatePaypalErrorMsg() {
		ExplicitWait(OR_OR.getProperty("Payment_Page_Paypal_Error_Msg"), "Payment_Page_Paypal_Error_Msg", 5);
		verify_xpath_text(OR_OR.getProperty("Payment_Page_Paypal_Error_Msg"),
				OR_OR.getProperty("Payment_Page_Paypal_Error_Msg_Text"));
		return this;
	}

	public PaymentPage closePaypalWindow() {
		if (isElementPresent(OR_OR.getProperty("Paypal_Window_Close_Btn"), "Paypal_Window_Close_Btn")) {
			Click_Button_Xpath(OR_OR.getProperty("Paypal_Window_Close_Btn"), "Paypal_Window_Close_Btn");
		} else {
			driver.close();
			driver.switchTo().window(winHandle);
		}
		return this;
	}
	
	public PaymentPage validateFlypayWindowAttribute() {
		ExplicitWait(OR_OR.getProperty("Flypay_Window_Title"), "Flypay_Window_Title", 10);
		if (isElementPresent(OR_OR.getProperty("Flypay_Window_Title"), "Flypay_Window_Title")) {
			verify_xpath_text(OR_OR.getProperty("Flypay_Window_Title"), OR_OR.getProperty("Flypay_Window_Title_Text"));
		} else {
			testLog.error("Opps!! Flypay window title is not showing");
			assertCheck("validateFlypayWindowAttribute", "Opps!! Opps!! Flypay window title is not showing");
		}
		if (isElementPresent(OR_OR.getProperty("Flypay_Window_Email"), "Flypay_Window_Email")) {
			testLog.info("Great!! Flypay window is loaded and email field is present");
		} else {
			testLog.error("Opps!! Flypay window is not loading");
			assertCheck("validateFlypayWindowAttribute", "Opps!! It seems, there is an issue in loading Flypay window");
		}
		if (isElementPresent(OR_OR.getProperty("Flypay_Window_Password"), "Flypay_Window_Password")) {
			testLog.info("Great!! Flypay window is loaded and password field is present");
		} else {
			testLog.error("Opps!! Flypay window is not loading");
			assertCheck("validateFlypayWindowAttribute", "Opps!! It seems, there is an issue in loading Flypay window");
		}
		return this;
	}
	
	public PaymentPage closeFlywayWindow() {
		// Closing current window
		//driver.close();
		wait(2000);
		// driver.switchTo().defaultContent();
		// driver.switchTo().window(winHandle);
		return this;
	}
}