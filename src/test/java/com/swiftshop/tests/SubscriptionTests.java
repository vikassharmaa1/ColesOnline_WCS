package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class SubscriptionTests extends Base_Class_Browser {

	String midWeek = "Mid week";
	String anyDay = "Any day";

	@Test(description = "WCS_AUT088 - Verify View History link in Manage your delivery subscription tab")
	public void validateViewHistoryLinkINManageYourDeliverySubscriptionTab() throws Exception {
		// Log in as an existing user
		funLibrary.login();
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateViewInvoiceLink().clickBackLink().backToMyAccountTab().clickLogout();
	}

	@Test(description = "WCS_AUT089- Verify that EFPOTS is not available for subscription ", enabled = false)
	public void validateEfpostsPresenceInSubscriptions() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on confirmation window continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Navigating coles plus subscription page
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Clicking on coles logo
		getPages.getPaymentPage().validatedEftposPresence().clickColesLogo();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT090- Verify user is able to subscribe using credit card", enabled = false)
	public void validateDeliveryPlusSubscriptionUsingCreditCard() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on confirmation window continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Choosing subscription from coles plus subscription page
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Choosing card as payment mode and entering card details
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validate subscriptions and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateSubscriptionStatusActive().backToMyAccountTab().clickLogout();
	}

	@Test(description = "WCS_AUT091 - Verify that user is able to subscribe using PayPal", enabled = false)
	public void validateDeliveryPlusSubscriptionUsingPayPal() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
        // Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Selecting payment mode as paypal and entering details
		getPages.getPaymentPage().clickPayUsingPaypal().enterPaypalID().clickNext().enterPaypalPassword()
				.clickPaypalLoginButton().clickAgreeAndContinueButton();
		// Navigating back to homePage after subscribing for Delivery Plus
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validating subscription details in superbar under My Account section
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateSubscriptionStatusActive().backToMyAccountTab().clickLogout();
	}

	@Test(description = "WCS_AUT092- Verify manage subscription for a subscribed user with credit card", enabled = false)
	public void validateUserSubscribedSubscription() throws Exception {
		String paymentType = "Credit / debit card";
		String cardExpiryDate = funLibrary.expiry_month + " / " + funLibrary.expiry_year;
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validate subscriptions and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateSubscriptionStatusActive()
				.validatePaymentDetails(paymentType, cardExpiryDate, funLibrary.cardno).backToMyAccountTab()
				.clickLogout();
	}

	@Test(description = "WCS_AUT087 - Verify Change link is there in Manage your delivery subscription tab")
	public void validateChangeLinkInManageYourDeliverySubscriptionTab() throws Exception {
		// Log in as an existing user
		funLibrary.login();
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateSubscriptionStatusActive().backToMyAccountTab().clickLogout();
	}

	@Test(description = "WCS_AUT096- verify that user do not get free delivery if order value is less than 100")
	public void validateSubscriptionForLessThanHundreadDollar() throws Exception {
		double mov_value = 40;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Slot is already selected");
		}
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler(); // checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Validating subscription applied and placing order
		getPages.getAlmostDonePage().verifySubscriptionisNotApplied().clickAgreementCheckbox().clickCompleteOrder()
				.clickReturnToHome();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT097- Verify that user get free delivery for order for more than $100")
	public void validateFreeDeliveryMoreThanHundredDollar() throws Exception {
		double mov_value = 100;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Slot is already selected");
		}
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler(); // checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Verifying subscription
		getPages.getAlmostDonePage().verifySubscriptionisApplied().clickAgreementCheckbox().clickCompleteOrder()
				.clickReturnToHome();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT098- verify that user is able to cancel subscription successfully", enabled = false)
	public void validateSubscriptionCancelledSuccessfully() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Adding payment details
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validate subscriptions and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.cancelSubscription().validateSubscriptionCancelled();
		getPages.getSuperBarPage().clickMyAccount();
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT099- Verify B2B user is able to subscribe for Coles plus.", enabled = false)
	public void validateBusinessUserDeliveryPlusSubscription() throws Exception {
		// Register new business user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton().getPages.getPaymentPage().clickPayUsingCard()
						.enterCardNumber().selectExpiryMonth().selectExpiryYear().enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validate subscriptions and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.validateSubscriptionStatusActive().backToMyAccountTab().clickLogout();
	}

	@Test(description = "WCS_AUT100- Verify user gets free delivery if trial period is not over and subscrition is cancelled", enabled = false)
	public void validateFreeDeliveryForCancelledSubscription() throws Exception {
		double mov_value = 100;
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Adding address
		getPages.getSuperBarPage().clickMyAccount().clickMyAddressesLink().clickAddAddressButton()
				.enterAddressDetails();
		// Validating address
		getPages.getRegistrationPage().validateAddressDetails();
		// Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.clickBackToHomePageButton();
		// Validate subscriptions and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_AfterSubscription()
				.cancelSubscription();
		getPages.getSuperBarPage().clickMyAccount();
		// Only if slot is not already selected
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Slot is already selected");
		}
		// Adding product to trolley
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler(); // checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Make payment using card
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Validating subscription applied and placing order
		getPages.getAlmostDonePage().verifySubscriptionisApplied().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT093- verify user is able to link fybuys through delivery subscription", enabled = false)
	public void validateLinkFlybuysWhileDeliverySubscription() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();

		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Choosing card as payment mode and entering card details
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.validateSubscriptionFlybuysText().clickLinkFlybuys();
		// Linking Flybuys
		getPages.myAccountPage().enterFlybuysNumber("9420", "0109", "5217").ClickLinkFlybuysButton()
				.validateFlybuysSuccessMsg();
		// Validating success message
		getPages.getSubscriptionPage().validateSubFlybuysSuccessMsg();
		// Validating linked flybuys displaying on my account section
		getPages.getSuperBarPage().clickMyAccount().validateLinkedFlybuysNum("EndsWith_5217");
		// Logging out user
		getPages.myAccountPage().clickLogout();
	}

	@Test(description = "WCS_AUT094- verify user ia able to add DOB through delivery subscription", enabled = false)
	public void validateAddDOBWhileDeliverySubscription() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Clicking on delivery plus subscription link
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Choosing card as payment mode and entering card details
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.enterDOBOnSubscriptionPage().clickSaveDOB();
		// Validating saved DOB displaying on my account section
		getPages.getSuperBarPage().clickMyAccount().validateSavedDOB();
		// Logging out user
		getPages.myAccountPage().clickLogout();
	}

	@Test(description = "WCS_AUT095- verify user not able to get flybuys and DOB on delivey subscription page if already saved", enabled = false)
	public void validateSubsFlybuysAndDOBIfAlreadySaved() throws Exception {
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).enterDOB("10", "1990").clickStartShoppingButton()
				.alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getRegistrationPage().clickContinueShoppingWithOutSkipFlybuys();
		// Linking Flybuys
		getPages.myAccountPage().enterFlybuysNumber("9420", "0109", "5217").ClickLinkFlybuysButton()
				.validateFlybuysPasswordPageText().clickContinueWithoutImport().closeSuperBar();
		getPages.getSuperBarPage().clickMyAccount().clickDeliveryPlusSubscriptionLink_BeforeSubscription()
				.clickSignUpButton().clickChooseThisOptionButton();
		// Choosing card as payment mode and entering card details
		getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
				.enterCVV().clickContinueButton();
		// Confirming the subscription and redirecting at home page
		getPages.getSubscriptionPage().clickConfirmSubscriptionCheckbox().clickSubmitSubscriptionButton()
				.validateFlybuysAndDOBNotPresentOnSubPage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	/*
	 * Below code could be use later - Add the registered user to delivery plus
	 * segment DatabaseUtilities DbUtils = new DatabaseUtilities();
	 * DbUtils.InsertUserIntoMBRGRPMBR(FunLibrary.RandomGeneratedUsername);
	 * DbUtils.InsertUserIntoColesPlusInviteeFlybuysTrail(FunLibrary.
	 * RandomGeneratedUsername); Validate Payment options available for
	 * subscriptions
	 */

}
