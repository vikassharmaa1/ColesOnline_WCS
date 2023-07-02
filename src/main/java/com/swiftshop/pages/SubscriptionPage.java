package com.swiftshop.pages;

import com.swiftshop.main.FunLibrary;

public class SubscriptionPage extends FunLibrary {

	public SubscriptionPage validateSubscriptionPage() {
		ExplicitWait(OR_OR.getProperty("Subscription_Delivery_plus"), "Subscription_Delivery_plus", 10);
		verify_xpath_text(OR_OR.getProperty("Subscription_Delivery_plus"),
				OR_OR.getProperty("Subscription_Delivery_plus_Text"));
		boolean cancelLink = isElementPresent(OR_OR.getProperty("Subscription_Cancel_Keep_Shopping_Link"),
				"Subscription_Cancel_Keep_Shopping_Link");
		boolean tcApplyLink = isElementPresent(OR_OR.getProperty("Subscription_TC_Apply_Link"),
				"Subscription_TC_Apply_Link");

		if (cancelLink == true) {
			testLog.info("Cancel & Keep shoping link is present at - Choose your delivery Subscriptions page");

		} else {
			testLog.error(
					"Opps!! Cancel & Keep shoping link is NOT present at - Choose your delivery Subscriptions page, Something went WRONG! ");
			assertCheck("validateSubscriptionPage",
					"Opps!! Cancel & Keep shoping link is NOT present at - Choose your delivery Subscriptions page, Something went WRONG! ");
		}
		if (tcApplyLink == true) {
			testLog.info("T&C Apply link is present  at - Choose your delivery Subscriptions page");

		} else {
			testLog.error(
					"Opps!! T&C Apply link is NOT present at - Choose your delivery Subscriptions page, Something went WRONG! ");
			assertCheck("validateSubscriptionPage",
					"Opps!! T&C Apply link is NOT present at - Choose your delivery Subscriptions page, Something went WRONG! ");
		}
		return this;
	}

	public SubscriptionPage clickStartTrialButton() {
		Click_Button_Xpath(OR_OR.getProperty("Start_Trial"), "Start_Trial");
		wait(5000);
		return this;
	}
	
	public SubscriptionPage clickConfirmSubscriptionCheckbox() {
		ExplicitWait(OR_OR.getProperty("Confirm_Subscription"), "Confirm_Subscription", 10);
		verify_xpath_text(OR_OR.getProperty("Confirm_Subscription"),
				OR_OR.getProperty("Confirm_Subscription_text"));
		Click_Button_Xpath(OR_OR.getProperty("I_agree_checkbox"), "I_agree_checkbox");
		return this;
	}


	public SubscriptionPage clickSubmitSubscriptionButton() throws InterruptedException {
		Click_Button_Xpath(OR_OR.getProperty("Submit_Subscription"), "Submit_Subscription");
		wait(6000);
		verify_xpath_text(OR_OR.getProperty("Subscription_Success"), OR_OR.getProperty("Subscription_Success_text"));
		return this;
	}

	public SubscriptionPage validateSubscription(String SubscriptionType) {
		if (SubscriptionType.equalsIgnoreCase("Mid week")) {
			verify_xpath_text(OR_OR.getProperty("Subscribed_to_Delivery"),
					OR_OR.getProperty("Subscribed_to_Delivery_Text2"));
		} else {
			verify_xpath_text(OR_OR.getProperty("Subscribed_to_Delivery"),
					OR_OR.getProperty("Subscribed_to_Delivery_Text"));
		}
		return this;
	}

	public SubscriptionPage validatePaymentDetails(String paymentType, String cardExpiryDate, String cardNo) {

		ExplicitWait(OR_OR.getProperty("Subscription_Payment_Details"), "Subscription_Payment_Details", 5);
		String paymentDetails = get_xpath_text(OR_OR.getProperty("Subscription_Payment_Details"),
				"Subscription_Payment_Details");
		System.out.println("Payment Details  ->>>" + paymentDetails);

		if (paymentDetails.contains(paymentType)) {
			testLog.info("Payment type text **" + paymentType
					+ "** is present in delivery subscription under payment details");
		} else {
			testLog.error("Opps!! Payment type text **" + paymentType
					+ "** is NOT present in delivery subscription under payment details, Something went WRONG! ");
			assertCheck("validatePaymentDetails", "Opps!! Payment type text **" + paymentType
					+ "** is NOT present in delivery subscription under payment details, Something went WRONG! ");
		}
		if (paymentDetails.contains(cardExpiryDate)) {
			testLog.info("Card validity **" + cardExpiryDate
					+ "** details is present in delivery subscription under payment details");
		} else {
			testLog.error("Opps!! Card validity details **" + cardExpiryDate
					+ "** is NOT present in delivery subscription under payment details, Something went WRONG! ");
			assertCheck("validatePaymentDetails", "Opps!! Card validity details **" + cardExpiryDate
					+ "** is NOT present in delivery subscription under payment details, Something went WRONG! ");
		}
		if (paymentDetails.contains(cardNo.substring(12))) {
			testLog.info("Card Number last four details is present in delivery subscription under payment details");
		} else {
			testLog.error(
					"Opps!! Card Number last four details is NOT present in delivery subscription under payment details, Something went WRONG! ");
			assertCheck("validatePaymentDetails",
					"Opps!! Card Number last four details is NOT present in delivery subscription under payment details, Something went WRONG! ");
		}
		return this;
	}

	public MyAccountPage backToMyAccountTab() {
		Click_Button_Xpath(OR_OR.getProperty("Back_Link"), "Back_Link");
		return new MyAccountPage();	
	}
	
	public SubscriptionPage clickBackLink() {
		Click_Button_Xpath(OR_OR.getProperty("SubscriptionHistory_BackLink"), "SubscriptionHistory_BackLink");
		return this;
	}

	public PaymentPage selectMidWeekSaverPlan() {
		Click_Button_Xpath(OR_OR.getProperty("Mid_Week_Saver_option"), "Mid_Week_Saver_option");
		verify_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "How would you like to pay?");
		return new PaymentPage();
	}

	public PaymentPage selectAllDaySaverPlan() {
		Click_Button_Xpath(OR_OR.getProperty("Any_Day_Saver_option"), "Any_Day_Saver_option");
		verify_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "How would you like to pay?");
		return new PaymentPage();
	}

	public HomePage clickBackToHomePageButton() {
		Click_Button_Xpath(OR_OR.getProperty("Back_to_Homepage"), "Back_to_Homepage");
		return new HomePage();
	}

	public SubscriptionPage cancelSubscription() {
		ExplicitWait(OR_OR.getProperty("Cancel_Subscription"), "Cancel_Subscription", 20);
		Click_Button_Xpath(OR_OR.getProperty("Cancel_Subscription"), "Cancel_Subscription");
		ExplicitWait(OR_OR.getProperty("Cancel_My_Subscription_Popup_Button"), "Cancel_My_Subscription_Popup_Button", 5);
		Click_Button_Xpath(OR_OR.getProperty("Cancel_My_Subscription_Popup_Button"), "Cancel_My_Subscription_Popup_Button");
		switch_Frame("Cancel_Subscription_Popup_Iframe");
		ExplicitWait(OR_OR.getProperty("Cancel_Subscription_Submit_Feedback_Button"), "Cancel_Subscription_Submit_Feedback_Button", 5);
		Click_Button_Xpath(OR_OR.getProperty("Cancel_Subscription_Submit_Feedback_Button"), "Cancel_Subscription_Submit_Feedback_Button");
		switch_To_DefaultFrame();
		Click_Button_Xpath(OR_OR.getProperty("Cancel_Subscription_Popup_Close_Button"), "Cancel_Subscription_Popup_Close_Button");
		return this;
	}

	public SubscriptionPage validateSubscriptionCancelled() {
		ExplicitWait(OR_OR.getProperty("Subscription_Status_Cancelled"), "Subscription_Status_Cancelled", 5);
		verify_xpath_text(OR_OR.getProperty("Subscription_Status_Cancelled"),"CANCELLED");		
		return this;
	}
	public SubscriptionPage validateSubscriptionStatusActive() {
		ExplicitWait(OR_OR.getProperty("Subscription_Status_Active"), "Subscription_Status_Active", 5);
		verify_xpath_text(OR_OR.getProperty("Subscription_Status_Active"),"ACTIVE");	
		return this;
	}
	
	public SubscriptionPage clickChooseThisOptionButton() {
		//verify_xpath_text(OR_OR.getProperty("Subscription_Choose_This_Option_Button"), "Subscription_Choose_This_Option_Button_Text");
		Click_Button_Xpath(OR_OR.getProperty("Subscription_Choose_This_Option_Button"), "Subscription_Choose_This_Option_Button");
		return this;
	}

public SubscriptionPage validateViewInvoiceLink() {
		
		//click View invoice link in Manage your delivery subscription tab
		Click_Button_Xpath(OR_OR.getProperty("View_Invoice_link"), "View_Invoice_link");
		verify_xpath_text(OR_OR.getProperty("Subscription_History_heading"), OR_OR.getProperty("Subscription_History_heading_text"));
		return new SubscriptionPage();
	}

	public SubscriptionPage validateSubscriptionFlybuysText() {
		verify_xpath_text(OR_OR.getProperty("Subscription_Flybuys_Heading"), OR_OR.getProperty("Subscription_Flybuys_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Subscription_Flybuys_SubHeading"), OR_OR.getProperty("Subscription_Flybuys_SubHeading_Text"));
		return this;
	}
	
	public SubscriptionPage clickLinkFlybuys() {
		Click_Button_Xpath(OR_OR.getProperty("Subscription_Link_Flybuys"), "Subscription_Link_Flybuys");
		return this;
	}
	
	public SubscriptionPage validateSubFlybuysSuccessMsg() {
		verify_xpath_text(OR_OR.getProperty("Subscription_Link_Flybuys_SuccessMsg"), OR_OR.getProperty("Subscription_Link_Flybuys_SuccessMsg_Text"));
		return this;
	}
	
	public SubscriptionPage validateSubscriptionDOBSection() {
		verify_xpath_text(OR_OR.getProperty("Subscription_DOB"), OR_OR.getProperty("Subscription_DOB_Text"));
		verify_xpath_text(OR_OR.getProperty("Subscription_DOB_Heading"), OR_OR.getProperty("Subscription_DOB_Heading_Text"));
		return this;
	}
	
	public SubscriptionPage enterDOBOnSubscriptionPage() {
		Clear_Text(OR_OR.getProperty("Subscription_DOB_Day"), "Subscription_DOB_Day");
		Sendkey_xpath(OR_OR.getProperty("Subscription_DOB_Day"), "10", "Subscription_DOB_Day");
		Click_Button_Xpath(OR_OR.getProperty("Subscription_DOB_Month"), "Subscription_DOB_Month");
		Click_Button_Xpath(OR_OR.getProperty("Subscription_DOB_Month_Feb"), "Subscription_DOB_Month_Feb");
		Clear_Text(OR_OR.getProperty("Subscription_DOB_Year"), "Subscription_DOB_Year");
		Sendkey_xpath(OR_OR.getProperty("Subscription_DOB_Year"), "1990", "Subscription_DOB_Year");
		return this;
	}
	
	public SubscriptionPage clickSaveDOB() {
		Click_Button_Xpath(OR_OR.getProperty("Subscription_DOB_Save_Button"), "Subscription_DOB_Save_Button");
		return this;
	}

	public SubscriptionPage validateFlybuysAndDOBNotPresentOnSubPage() {
		boolean flybuysHeading = isElementNotPresent(OR_OR.getProperty("Subscription_Flybuys_Heading"),
				"Subscription_Flybuys_Heading");
		boolean flybuysSubHeading = isElementNotPresent(OR_OR.getProperty("Subscription_Flybuys_SubHeading"),
				"Subscription_Flybuys_SubHeading");
		boolean dobHeading = isElementNotPresent(OR_OR.getProperty("Subscription_DOB_Heading"),
				"Subscription_DOB_Heading");
		if (flybuysHeading && flybuysSubHeading && dobHeading) {
			testLog.info("Flybuys and date of birth is already saved, so its not displaying on subscription page");

		} else {
			testLog.error("Flybuys and date of birth is already saved, so its should not displaying on subscription page");
			assertCheck("validateFlybuysAndDOBNotPresentOnSubPage",
					"Flybuys and date of birth is already saved, so its should not displaying on subscription page");
		}
		Click_Button_Xpath(OR_OR.getProperty("Back_to_Homepage"), "Back_to_Homepage");
		return this;
	}
}
