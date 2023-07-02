package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class OrderConfirmationTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully")
	public void validatePlaceOrder_HD() throws Exception {
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
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Select value from ACS emulator drop-down
//		getPages.getOrderConfirmationPage()
//				.selectValueFromAuthenticationResultDropdown("(Y) Authentication/Account Verification Successful")
//				.clickSubmitOrderButton_ACS().switch_To_DefaultFrame();
//		getPages.getOrderConfirmationPage()
//				.selectValueFromAuthenticationResultDropdown("(Y) Authentication/Account Verification Successful");
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT219- Verify that user is able to place CC orders successfully")
	public void validatePlaceOrder_CC() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("NT 0820");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnCollection(); //Not available
		// right now
		// Complete Order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT220 - validate that user is able to place RD orders successfully")
	public void validatePlaceOrder_RD() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Localize to Casuarina CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler().saveInstructions_RDOrder();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption()
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT227 -Validate that user redirects to home page after user click Home button on order confirmation page")
	public void validateHomeButtonOnOrderConfirmationPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// If trolley amount is less than mov then empty trolley and add items
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler();
		// If user is on almost done page click Change Payment and navigate back
		// to
		// Payment page
		// String checkout_header =
		// selLibrary.get_xpath_text(funLibrary.OR_OR.getProperty("Checkout_Flow_Header"),
		// "Checkout_Flow_Header");
		// if (checkout_header.equals("Please confirm your order details")) {
		// testLog.warn("User is on Almost Done page. clicking on change payment
		// to navigate to payment option page");
		// getPages.getAlmostDonePage().clickChangePayment();
		// }
		// Pay using any available payment option
		// getPages.getPaymentPage().selectAnyAvailablePaymentOption();
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();

		// getPages.getAlmostDonePage().clickPayOnDelivery();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "Validate that user is able to place order using Partner Delivery slot successfully")
	public void validatePlaceOrder_PD() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("VIC 3185");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().PDSlotSelector("UnattendedOnly", false,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order

		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();

		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT249- Verify that user is able to place HD orders successfully for WA address")
	public void validatePlaceOrder_HDForWA() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("WA 6153");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector_WA("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order

		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();

		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT249- Verify that user is able to place CC orders successfully for WA address")
	public void validatePlaceOrder_CCForWA() throws Exception {
		double mov_value = 50;
		String CCAddress = "Riverton, 6148";
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("WA 6153");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Riverton")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Placing order

		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
