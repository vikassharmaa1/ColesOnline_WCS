
package com.swiftshop.testdata;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class PlaceOrders extends Base_Class_Browser {
	
	@Test(description = "WCS_AUT238-Place HD order with perticular slot", invocationCount=2)
	public void validatePlaceOrderWithSlotDateTime_HD() {

	// Change SlotDate and SlotTime the one you want to select and place order.
	String SlotDate="15";
	String SlotTime="7:00 AM - 8:00 AM";

	double mov_value = 50;
	// Logged-in user
	funLibrary.login();
	// Localize to HD
	//funLibrary.LocalizeToHD("VIC 3166");
	// Select HD slot
	//getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("VIC 3166").HDSlotSelectorWithMachingSlotDateTime(SlotDate,SlotTime);
	double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
	if (trolley_Total_Cost >= mov_value) {
	// Navigate trolley and performed checkout
	getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler().checkoutFlowHandler();
	} else {
	// Adding product to trolley and performed checkout
	getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
	.clickProceedToCheckOutButton().TrolleyHandler().checkoutFlowHandler();
	}
	// Pay using card and change the payment method
	if (getPages.getPaymentPage().isAtPaymentPage()) {
	getPages.getPaymentPage().clickPayUsingCard().enterCardNumber().selectExpiryMonth().selectExpiryYear()
	.enterCVV().clickContinueButton();
	// Placing order
	getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
	// Validate order confirmation
	getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
	} else {
	// Placing order
	getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
	// Validate order confirmation
	getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
	}
	
	// Logging out user
			getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD1() throws Exception {
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD2() throws Exception {
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD3() throws Exception {
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD4() throws Exception {
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	// Placing order for Store 0645
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD5() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("VIC 3166");
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD6() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("VIC 3166");
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD7() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("VIC 3166");
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT218- Verify that user is able to place HD orders successfully", invocationCount=20)
	public void validatePlaceOrder_HD8() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("VIC 3166");
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
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Pay On Collection
		getPages.getAlmostDonePage().clickPayOnDelivery();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Validate order confirmation
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@BeforeTest
	public void beforeTest() {

	//	sheetname = "DeliverySlotTests";
	}
}
	
