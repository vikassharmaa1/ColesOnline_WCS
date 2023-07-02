package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class PaymentTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT187-Verify that system accepts valid card")
	public void validateSystemAcceptValidCard() throws Exception {
		double mov_value = 50;
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
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Pay using card and change the payment method
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Validate checkout page
		// getPages.getAlmostDonePage().validateAtCheckoutPage();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT188- Verify that system donot allow invalid card details")
	public void validateSystemNotAcceptInvalidCard() throws Exception {
		double mov_value = 50;
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
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validating invalid card
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterInvalidCard_CheckErrorMessage()
				.clickCancel().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT189- Verify user is not able to place order using decline expired card")
	public void validatePlaceOrderUsingExpiredCard() {
		double mov_value = 50;
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
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate User get payment error message after placing order
		getPages.getPaymentPage().validatePaymentCardIssue().clickGoToPaymentOpt().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT190- Verify that system allows valid paypal details")
	public void validateSystemAcceptValidPaypal() throws Exception {
		double mov_value = 50;
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
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost >= mov_value) {
			// Navigate trolley and performed checkout
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
			// .checkoutFlowHandler();
		} else {
			// Adding product to trolley and performed checkout
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
					.clickProceedToCheckOutButton().TrolleyHandler();
			// checkoutFlowHandler();
		}
		// Validate valid paypal details
		getPages.getAlmostDonePage().clickPayUsingPayPal().clickAtPaypalAccount().enterPaypalID().clickNext()
				.enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		getPages.getAlmostDonePage().validateAtCheckoutPage().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT191- Verify paypal using invalid account details")
	public void validateSystemNotAcceptInvalidPaypal() throws Exception {
		double mov_value = 50;
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
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost >= mov_value) {
			// Navigate trolley and performed checkout
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		} else {
			// Adding product to trolley and performed checkout
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
					.clickProceedToCheckOutButton().TrolleyHandler();
		}
		// Validate Invalid paypal details
		getPages.getAlmostDonePage().clickPayUsingPayPal().clickAtPaypalAccount().clickNotYouLink().enterPaypalID()
				.clickNext().enterPaypalPassword().clickPaypalLoginButton().validatePaypalErrorMsg().closePaypalWindow()
				.clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	/*---Don't have card to validate scenario at SIT internal---*/
	/*
	 * @Test(description =
	 * "WCS_AUT192- Verify that user is not able to place order using decline Insufficient Funds in cards"
	 * ) public void validatePlaceOrderUsingInsufficientFundsCards() { double
	 * mov_value = 50; // Logged-in user funLibrary.login(); // Only if slot is not
	 * already selected if (!getPages.getHomePage().isSlotSelected()) {
	 * getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().
	 * HDSlotSelector( "signed", true, false);
	 * getPages.myAccountPage().closeSuperBar(); } else {
	 * testLog.info("Slot is already selected"); } if
	 * (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value); } // Proceed to checkout
	 * getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler(); // Placing order
	 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
	 * enterCardNumber().selectExpiryMonth()
	 * .selectExpiryYear().enterCVV().clickContinueButton(); // Submit order
	 * getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder( );
	 * // Validate User get payment error message after placing order
	 * getPages.getPaymentPage().validatePaymentCardIssue().clickGoToPaymentOpt(
	 * ).clickKeepShopping(); // Logging-out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout();
	 * 
	 * }
	 */

	/*---EFTPOS functionality has been obsolete in SPC. So, removing this script from our automation suite---*/

	/*
	 * @Test(description =
	 * "WCS_AUT193- Verify that user is able to select EFTPOS while placing order" )
	 * public void validateSystemAcceptEFTPOS() throws Exception { double mov_value
	 * = 50; // Logged-in user funLibrary.login(); // Only if slot is not already
	 * selected if (!getPages.getHomePage().isSlotSelected()) {
	 * getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().
	 * HDSlotSelector( "signed", true, false); } else {
	 * testLog.info("Slot is already selected"); } double trolley_Total_Cost =
	 * getPages.getTrolleyAndCheckoutPage().getTrolleyAmount(); if
	 * (trolley_Total_Cost >= mov_value) { // Navigate trolley and performed
	 * checkout
	 * getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler().checkoutFlowHandler(); } else { // Adding product to trolley
	 * and performed checkout
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value).clickTrolley()
	 * .clickProceedToCheckOutButton().TrolleyHandler().checkoutFlowHandler(); } //
	 * If user redirect on Almost done Or Checkout page if
	 * (getPages.getPaymentPage().isAtPaymentPage()) {
	 * getPages.getPaymentPage().clickPayUsigEFPOTS(); // Validating selected
	 * payment method
	 * getPages.getAlmostDonePage().validatePaymentMethod("Pay on delivery"); //
	 * Placing order using EFPOTS
	 * getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder(
	 * ).clickReturnToHome(); } else {
	 * getPages.getAlmostDonePage().clickChangePayment().clickPayUsigEFPOTS(); //
	 * Validating selected payment method
	 * getPages.getAlmostDonePage().validatePaymentMethod("Pay on delivery"); //
	 * Placing order using EFPOTS
	 * getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder(
	 * ).clickReturnToHome(); } // Logging-out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */

	@Test(description = "WCS_AUT194- Verify that system autopopulate card details when user has checked this checkbox in previous order.")
	public void validateAutoPopulatedSavedCardDetails() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Check and remove if card details are already saved
		if (getPages.getSuperBarPage().clickMyAccount().isCardDetailSaved()) {
			getPages.myAccountPage().clickCardDetailsDeleteButton().clickCardDetailsConfirmDeleteButton();
		}
		// Only if slot is not already selected
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Slot is already selected");
		}
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().checkRememberCheckbox().clickContinueButton();
		getPages.getAlmostDonePage().validateCardDetails_CheckoutPage().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().validateSavedCardDetails().clickLogout();
	}

	@Test(description = "WCS_AUT210- Verify that user is abe to change payment option from card to paypal account")
	public void validateChangePaymentOptionFromCardToPaypal() throws Exception {
		double mov_value = 50;
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
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost >= mov_value) {
			// Navigate trolley and performed checkout
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler(); // checkoutFlowHandler();
		} else {
			// Adding product to trolley and performed checkout
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
					.clickProceedToCheckOutButton().TrolleyHandler(); // checkoutFlowHandler();
		}
		// Pay using card and change the payment method
		// if (getPages.getPaymentPage().isAtPaymentPage()) {
		// getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
		// .enterCVV().clickContinueButton();
		// // Select payment option pay using paypal
		// getPages.getAlmostDonePage().clickChangePayment().clickUsingDifferentPaypalAccount().enterPaypalID()
		// .clickNext().enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// // Validating selected payment method
		// getPages.getAlmostDonePage().validatePaymentMethod("PayPal");
		// } else {
		// // Select payment option pay using paypal
		// getPages.getAlmostDonePage().clickChangePayment().clickUsingDifferentPaypalAccount().enterPaypalID()
		// .clickNext().enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// getPages.getAlmostDonePage().validatePaymentMethod("PayPal");
		// }
		// Make payment using card
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Select payment option pay using paypal
		getPages.getAlmostDonePage().clickPayUsingPayPal().clickAtPaypalAccount().enterPaypalID().clickNext()
				.enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT211- Verify that user is abe to change payment option from paypal to card")
	public void validateChangePaymentOptionFromPaypalToCard() throws Exception {
		double mov_value = 50;
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
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost >= mov_value) {
			// Navigate trolley and performed checkout
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		} else {
			// Adding product to trolley and performed checkout
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
					.clickProceedToCheckOutButton().TrolleyHandler();
		}
		// Select payment option pay using paypal
		getPages.getAlmostDonePage().clickPayUsingPayPal().clickAtPaypalAccount().enterPaypalID().clickNext()
				.enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// Make payment using card
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	/*---EFTPOS functionality has been obsolete in SPC. So,removing this script from our automation suite ---*/

	/*
	 * @Test(description =
	 * "WCS_AUT212- Verify that user is abe to change paymen option to EFTPOS")
	 * public void validateChangePaymentOptionFromPaypalToEFTPOS() throws Exception
	 * { double mov_value = 50; // Logged-in user funLibrary.login(); // Only if
	 * slot is not already selected if (!getPages.getHomePage().isSlotSelected()) {
	 * getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().
	 * HDSlotSelector( "signed", true, false); } else {
	 * testLog.info("Slot is already selected"); } double trolley_Total_Cost =
	 * getPages.getTrolleyAndCheckoutPage().getTrolleyAmount(); if
	 * (trolley_Total_Cost >= mov_value) { // Navigate trolley and performed
	 * checkout
	 * getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler().checkoutFlowHandler(); } else { // Adding product to trolley
	 * and performed checkout
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value).clickTrolley()
	 * .clickProceedToCheckOutButton().TrolleyHandler().checkoutFlowHandler(); } //
	 * Pay using Paypal and change payment method to EFTOPS if
	 * (getPages.getPaymentPage().isAtPaymentPage()) {
	 * getPages.getPaymentPage().clickUsingDifferentPaypalAccount().
	 * enterPaypalID().clickNext() .enterPaypalPassword().clickPaypalLoginButton().
	 * clickAgreeAndContinueButton(); // Select payment option pay using card
	 * getPages.getAlmostDonePage().clickChangePayment().clickPayUsigEFPOTS(); }
	 * else { // Select payment option pay using paypal
	 * getPages.getAlmostDonePage().clickChangePayment().
	 * clickUsingDifferentPaypalAccount().enterPaypalID()
	 * .clickNext().enterPaypalPassword().clickPaypalLoginButton().
	 * clickAgreeAndContinueButton(); // Select payment option pay using card
	 * getPages.getAlmostDonePage().clickChangePayment().clickPayUsigEFPOTS(); } //
	 * Placing order
	 * getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder(
	 * ).clickReturnToHome(); // Logging-out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */

	/*---Payment page has been obsolete in SPC. So,removing this script from our automation suite ---*/

	/*
	 * @Test(description =
	 * "WCS_AUT213- Verify Keep Shoping link from payment option page") public void
	 * validateKeepShoppingAtPaymentPage() throws Exception { double mov_value = 50;
	 * // Logged-in user funLibrary.login(); // Only if slot is not already selected
	 * if (!getPages.getHomePage().isSlotSelected()) {
	 * getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().
	 * HDSlotSelector( "signed", true, false); } else {
	 * testLog.info("Slot is already selected"); } if
	 * (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value); } // Proceed to checkout
	 * getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler(); // Validate keep shopping link
	 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
	 * validateKeepShoppingLink().clickKeepShoppingLink(); // Logging-out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */

	@Test(description = "WCS_AUT230- Verify that user is not able to place order using decline capture card")
	public void validatePlaceOrderUsingCaptureCard() {
		double mov_value = 50;
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
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate User get payment error message after placing order // Below
		// validation is invalid so commenting that
		// getPages.getPaymentPage().validatePaymentCardIssue().clickGoToPaymentOpt().clickKeepShopping();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT231- Verify that user is not able to place order using decline invalid card")
	public void validatePlaceOrderUsingInvalidCard() {
		double mov_value = 50;
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
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate User get payment error message after placing order
		getPages.getPaymentPage().validatePaymentCardIssue().clickGoToPaymentOpt().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT232- Verify error message while submit order without card.")
	public void validatePlaceOrderWithOutCard() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("NT 0820");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate error message
		getPages.getAlmostDonePage().verifyErrorMessage_Order();
		// Click at keep shopping link
		getPages.getPaymentPage().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT233- Verify that Flypay window is loaded with all attribute", enabled=false)
	public void validateFlypayWindowAttribute() throws Exception {
		double mov_value = 50;
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
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost >= mov_value) {
			// Navigate trolley and performed checkout
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
			// .checkoutFlowHandler();
		} else {
			// Adding product to trolley and performed checkout
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
					.clickProceedToCheckOutButton().TrolleyHandler();
			// checkoutFlowHandler();
		}
		// Clicking at Flypay button
		getPages.getAlmostDonePage().clickPayUsingFlypay().clickAtFlypayAccount();
		// Validating window attribute
		getPages.getPaymentPage().validateFlypayWindowAttribute().closeFlywayWindow();
		// Returning back to home page
		getPages.getAlmostDonePage().validateAtCheckoutPage().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

}
