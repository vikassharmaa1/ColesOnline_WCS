package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class AlmostDoneTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT140-Verify that user is able to see past orders in orders section")
	public void validatePastOrders() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validate all HD, CC and RD past orders
		/*getPages.getSuperBarPage().clickOrders().validateOrdersHeadings().clickChange().selectAddress("HD")
				.clickChange().selectAddress("CC").clickChange().selectAddress("RD");*/
		
		getPages.getSuperBarPage().clickOrders().validateOrdersHeadings().verifyPastOrders();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT141-Verify that user can add product to cart from past order")
	public void validateAddProductsFromPastOrder() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getTrolleyAndCheckoutPage();
		// Add Products from past order
		getPages.getSuperBarPage().clickOrders().clickPastOrderDelivered().clickReorderItems().addAllPastOrderItems();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT142-Verify past order status of HD orders")
	public void validatePastOrdersStatusDelivered() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating HD past order status
		getPages.getSuperBarPage().clickOrders().validateOrderStatus();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT143-Verify past order status for CC orders")
	public void validatePastOrdersStatusCollected() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating CC past order status
		getPages.getSuperBarPage().clickOrders().validateOrderStatus();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT144-Verify past order status for orders")
	public void validatePastOrdersStatusYetToBeDelivered() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating CC past order status
		getPages.getSuperBarPage().clickOrders().validateOrderStatus();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT145-Modify order-Verify that user is able to place order, modify the order and again place it.")
	public void validateModifyOrderWithProducts() throws Exception {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Delivery
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Get Order ID
		String orderId = getPages.getAlmostDonePage().getOrderId();
		// Return to home
		getPages.getOrderConfirmationPage().clickReturnToHome();
		// Navigate to Orders and select the placed order and click Modify this
		// order
		getPages.getSuperBarPage().clickOrders().clickPlacedOrder(orderId).clickModifyOrder().closeSuperBar();
		// Adding new products to the cart. add_Skus_To_Trolley function will
		// first
		// empty the cart.
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_2, "Y", mov_value);
		// click proceed to checkout
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Delivery
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		// place order again with modified cart
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT146-Verify past order status of HD orders")
	public void validateOrderCancellation() throws Exception {
		double mov_value = 50;
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD Slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Delivery
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		funLibrary.LocalizeToHD("NT 0820");
		// Select CC Slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Collection
		// getPages.getAlmostDonePage().clickPayOnCollection(); Commented as it
		// is
		// unavailable right now
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Canceling order
		getPages.getSuperBarPage().clickOrders().cancelOrders();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT146-Verify past order status of HD orders")
	public void validateOrderCancellation_PD() throws Exception {
		double mov_value = 50;
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("VIC 3185");
		// Select HD Slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().PDSlotSelector("UnattendedOnly", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Pay On Delivery
		// getPages.getAlmostDonePage().clickPayOnDelivery();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();

		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Canceling order
		getPages.getSuperBarPage().clickOrders().cancelOrders();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Functional change- noBagOption functionality has been obsolete
	// @Test(description = "WCS_AUT174-Validate that bagging price is not
	// included if user has changed bagging option to bagless")
	// public void validateTotalAmountBagToBaglessForHD() throws Exception {
	// double mov_value = 50;
	// // Logged-in user
	// funLibrary.login();
	// // Localize to HD address
	// funLibrary.LocalizeToHD("NT 0820");
	// // Select HD slot
	// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector(
	// "signed", true,
	// false);
	// getPages.myAccountPage().closeSuperBar();
	// // Adding product to trolley, process to Almost done page
	// getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	// mov_value).clickTrolley()
	// .clickProceedToCheckOutButton().TrolleyHandler();//
	// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
	// // Validate total amount selecting bag option in almost done page
	// getPages.getAlmostDonePage().validateTotalAmountWithBag();
	// double bagPrice = getPages.getAlmostDonePage().getBagPrice();
	// double totalAmt = getPages.getAlmostDonePage().getTotalAmount();
	// // Validate total order cost after changing to bagless in almost done
	// page
	// getPages.getAlmostDonePage().changeToNoBag().validateTotalAmountWithBagless(bagPrice,
	// totalAmt)
	// .clickKeepShoppingLink();
	// // Logging out user
	// getPages.getSuperBarPage().clickMyAccount().clickLogout();
	// }

	@Test(description = "WCS_AUT176-Order Total Before and after Changing to Bagless")
	public void validateTotalAmountBagToBaglessForCC() throws Exception {
		double mov_value = 50;
		String CCAddress = "Casuarina, 0810";
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Localize to Casuarina CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Casuarina")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);

		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();

		// Validate total amount selecting bag option in almost done page
		getPages.getAlmostDonePage().validateTotalAmountWithBag();
		double bagPrice = getPages.getAlmostDonePage().getBagPrice();
		double totalAmt = getPages.getAlmostDonePage().getTotalAmount();
		// Validate total order cost after changing to bagless in almost done
		// page
		getPages.getAlmostDonePage().changeToNoBag().validateTotalAmountWithBagless(bagPrice, totalAmt)
				.clickKeepShoppingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT203- View trolley item link should navigate user to home page and show trolley items in superbar")
	public void validateViewTrolleyItemLinkInAlmostDonePage() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Selecting slot
		if (getPages.getHomePage().isSlotSelected()) {
			testLog.info("Slot is already selected.");
		} else {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		}
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page. Click on View Trolley button and check
		// superbar
		// is expanded on homepage
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		getPages.getAlmostDonePage().clickModifyTrolley().validateViewTrolleyButton();
		// .clickProceedToCheckOutButton().TrolleyHandler();//
		// .checkoutFlowHandler().selectAnyAvailablePaymentOption()
		// Click on View trolley items link
		// getPages.getAlmostDonePage().clickViewTrolleyLink().validateViewTrolleyButton();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT204- validate that user is able to change delivery details from almost done page,"
			+ "WCS_CE3882-Validate that user is able to click on Change link next to current date & time on Confirm your Order page for Order type delivery.")
	public void validateChangeDeliveryDetailsInAlmostDonePage() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot for NT
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Add product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page.
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Validate change deliverySlot link
		String current_date = getPages.getAlmostDonePage().getSlotDate();
		getPages.getAlmostDonePage().clickDeliverySlotChangeLink().selectNextDay().HDSlotSelector("signed", true, false,
				true);
		String updated_date = getPages.getAlmostDonePage().getSlotDate();
		if (current_date.equals(updated_date)) {
			testLog.error("Failed to update Delivery slot. Slot Date before change:" + current_date
					+ " Date after update:" + updated_date);
			selLibrary.assertCheck("",
					"Faield to upate Delivery slot. current_date:" + current_date + " updated_date" + updated_date);
		} else {
			testLog.info("selected Date was:" + current_date);
			testLog.info("updated Date is:" + updated_date);
			testLog.info("Slot date changed successfully");
		}
		// Validating Slot Date, Time and price after changing the another slot
		// in
		// Confirm your order page (Jira ticket num CE-3882)
		getPages.getAlmostDonePage().validateSlotDateTimePrice("HD");
		// Validate change Delivery Address and selecting next day slot
		getPages.getAlmostDonePage().validateDeliveryType("HD").clickDeliveryAddressChangeLink()

				.clickHDAddressDSS("VIC 3166").HDSlotSelector("signed", true, false, true);

		String updated_address = getPages.getAlmostDonePage().getDeliveryAddress();

		if (updated_address.contains("VIC 3166")) {
			testLog.info("Slot address changed successfully");
		} else {
			testLog.error("Failed to update Delivery address. updated address is" + updated_address);
			selLibrary.assertCheck("", "Faield to upate Delivery address. Updated address is" + updated_address);
		}
		// Validate change Delivery Type. Changing Delivery type from Signed to
		// Unattended
		getPages.getAlmostDonePage().clickDeliveryTypeChangeLink().selectUnattendedDelivery()
				.clickDSSSlotPopupContinueBtn().skipUnattendedDeliveryInstructions();
		// Getting delivery type
		String delivery_type = getPages.getAlmostDonePage().getDeliveryType();
		if (delivery_type.contains("Unattended")) {
			testLog.info("Delivery type changed successfully from signed to unattended");
		} else {
			testLog.error("Failed to update Delivery Type");
			selLibrary.assertCheck("", "Faield to upate Delivery Type");
		}
		// validate change Delivery Instruction
		// covered in TC 167
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Functional change- noBagOption functionality has been obsolete
	// @Test(description = "WCS_AUT205- Verify that user is able to change
	// bagging option on almost done page.")
	// public void validateChangeBaggingOptAlmostDonePage_SignedDelivery()
	// throws Exception {
	// double mov_value = 50;
	// // Logged-in user
	// funLibrary.login();
	// // Localize to HD address
	// funLibrary.LocalizeToHD("NT 0820");
	// // Select HD slot
	// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector(
	// "signed",
	// false, false);
	// getPages.myAccountPage().closeSuperBar();
	// // Adding product to trolley, process to Almost done page
	// getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	// mov_value).clickTrolley()
	// .clickProceedToCheckOutButton().TrolleyHandler();//
	// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
	// // Adding card number
	// getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
	// .selectExpiryYear().enterCVV().clickContinueButton();
	// // Validate user can change bagging option in ADP
	// getPages.getAlmostDonePage().clickChangeBaggingOptionLink().changeToBagMyGroceries().clickAgreementCheckbox()
	// .clickCompleteOrder().clickReturnToHome();
	// // Logging out user
	// getPages.getSuperBarPage().clickMyAccount().clickLogout();
	// }

	@Test(description = "WCS_AUT206-Verify that user is not able to change bagging option on almost done page.")
	public void validateNotChangeBaggingOptAlmostDonePage_UD() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("unattendedonly", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton();// .TrolleyHandler();//
												// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Below code could be use leter
		/*
		 * getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().
		 * enterCardNumber().selectExpiryMonth()
		 * .selectExpiryYear().enterCVV().clickContinueButton();
		 */
		// Validate user not able change bagging option in ADP
		getPages.getAlmostDonePage().validateUDNotAbleToChangeBagging().clickKeepShoppingLink();
		// Below code could be use later
		/*
		 * .clickChangeBaggingOptionLink().validateUDNotAbleToChangeBagging()
		 * .clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		 */
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT207-Verify that user is able to select substitue for the products in almost done page")
	public void validateChangeSubstitutionInAlmostDonePage() throws Exception {
		double mov_value = 100;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Add card
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Validate user able to change substitution in AMD
		getPages.getAlmostDonePage().validateSubstituteLabel().chooseWhatCanBeSubstituted()
				.chooseSubstituteItNoExtraCost().chooseDontSubstituteIt().clickAgreementCheckbox().clickCompleteOrder()
				.clickReturnToHome();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT208- Validate promo code is successfully applied.")
	public void validatePromocodeIsApplied() throws Exception {
		// double mov_value = 200;
		double mov_value = 50;
		String promocode = funLibrary.test_data;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			// Adding product to trolley
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Navigate to almost done page
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Getting trolley total amount
		double trolley_final_amount = getPages.getAlmostDonePage().getTotalAmount();
		// Apply promo code
		getPages.getAlmostDonePage().clickAddPromoCodeLink().enterPromoCode(promocode).clickApplyPromoCodeButton();
		// validate promo code
		double trolley_Final_amount_AfterPromocode = getPages.getAlmostDonePage().getTotalAmount();
		double promo_discount = getPages.getAlmostDonePage().getPromoCodeDiscountAmount(promocode);
		if (trolley_Final_amount_AfterPromocode == (trolley_final_amount - promo_discount)) {
			testLog.info("Promo code aplied succesfully");
		} else {
			testLog.error("Promo code is not applied");
			selLibrary.assertCheck("validateValidPromocodeIsApplied", "Promo code is not applied");
		}
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT209- Validate system does not allow invalid promo code.")
	public void validateInvalidPromocodeNotApplied() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			// Add products to trolley
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Checkout
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler();

		// Validate keep shopping link
		// if (getPages.getPaymentPage().isAtPaymentPage()) {
		// getPages.getPaymentPage().clickUsingDifferentPaypalAccount().enterPaypalID().clickNext()
		// .enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// getPages.getAlmostDonePage().applyInvalidPromoCode_CheckErrorMessage();
		// } else {
		// Navigate to payment page and validate keep shopping link
		getPages.getAlmostDonePage().applyInvalidPromoCode_CheckErrorMessage();
		// }
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT214- Verify user is able to change valid Flybuy from Almost done page")
	public void validateChangeValidFlybuysInAlmostDonePage() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// UnLink Flybuys number if present
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys();
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// If trolley amount is less than mov then empty trolley and add items
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		// Validate user able to change Flybuys
		getPages.getAlmostDonePage().validateFlybuysText().clickChangeFlybuysLink().validateADPFlybuysPage();
		getPages.myAccountPage().enterFlybuysNumber("9420", "0109", "5217");
		getPages.getAlmostDonePage().clickSaveFlybuys().validateSavedFlybuysADP();
		// Placing order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT215- Verify user is not able to save Invalid Flybuy from Almost done page")
	public void validateChangeInValidFlybuysInAlmostDonePage() throws Exception {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// UnLink Flybuys number if present
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys();
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// If trolley amount is less than mov then empty trolley and add items
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Validate user is not able to save invalid Flybuys
		getPages.getAlmostDonePage().validateFlybuysText().clickChangeFlybuysLink().validateADPFlybuysPage();
		getPages.myAccountPage().enterFlybuysNumber("1234", "1111", "2222");
		getPages.getAlmostDonePage().clickSaveFlybuys().validateErrorMsgFlybuysADP().clickCancelFlybuysLink()
				.clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT228 validate keep shopping link on Almost Done page")
	public void validateKeepShoppingLinkOnAlmostDonePage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// If trolley amount is less than mov then empty trolley and add items
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Click keep shopping link
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Release 2009
	@Test(description = "WCS_CE3606- Verify that when user lands on 'Single Page Checkout' page after selecting signed only delivery option, there should be a section named Your trolley with total count of items.")
	public void validateTrolleyItemsInSinglePageCheckout() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// If trolley amount is less than mov then empty trolley and add items
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		// Adding offer product to trolley
		getPages.getHomePage().clickClearSearch().enterSearchItem(funLibrary.search_2).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "2").clickClearSearch();
		// Get Total trolley amount
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		// Get Total items present in the trolley
		int superBarTrolleyItems = getPages.getTrolleyAndCheckoutPage().getTrolleyTotalItems();
		getPages.getSuperBarPage().clickTrolley();
		// Get Total Saving amount
		double trolley_Total_Saving = getPages.getTrolleyAndCheckoutPage().getTotalSaving();
		// Proceed to checkout
		getPages.getTrolleyAndCheckoutPage().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate trolley total count of items and savings in single page
		// checkout
		getPages.getAlmostDonePage().validateYourTrolleyItems(superBarTrolleyItems)
				.validateTotalTrolleyAmount(trolley_Total_Cost).validateTotalSaving(trolley_Total_Saving)
				.clickKeepShopping();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Release 2009
	@Test(description = "WCS_CE3607- Verify when user selects substitute from 'Single Page Checkout' page and place the order then previous selection should be remembered for new order.")
	public void validateRememberedSubstituteAfterPlaceOrder() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Selecting "Don't substitute it, just leave it out" option from
		// substitute
		// Drop-down and placing order
		getPages.getAlmostDonePage().validateSubstituteLabel().selectRandomSubstituteOption().clickPayUsingCard()
				.clickAddNewCard().enterCardNumber().selectExpiryMonth().selectExpiryYear().enterCVV()
				.clickContinueButton();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Select HD slot again
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// Validating "Don't substitute it, just leave it out" is populated in
		// single
		// page checkout
		getPages.getAlmostDonePage().validateRememberedSubstitute().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Release 2009
	@Test(description = "WCS_CE3761- Validate details after entering the used 'Promo Code' on 'Single Page Checkout' page.")
	public void validateUsedPromocodeOnConfirmYourOrderPage() throws Exception {
		double mov_value = 50;
		String promocode = funLibrary.test_data;
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Getting trolley amount
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost < mov_value) {
			// Add products to trolley
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Checkout
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Getting trolley total amount
		double trolley_final_amount = getPages.getAlmostDonePage().getTotalAmount();
		// Apply promo code
		getPages.getAlmostDonePage().clickAddPromoCodeLink().enterPromoCode(promocode).clickApplyPromoCodeButton();
		// Validate promo code
		double trolley_Final_amount_AfterPromocode = getPages.getAlmostDonePage().getTotalAmount();
		double promo_discount = getPages.getAlmostDonePage().getPromoCodeDiscountAmount(promocode);
		if (trolley_Final_amount_AfterPromocode == (trolley_final_amount - promo_discount)) {
			testLog.info("Promo code aplied succesfully");
		} else {
			testLog.error("Promo code is not applied");
			selLibrary.assertCheck("validateValidPromocodeIsApplied", "Promo code is not applied");
		}
		// Apply same promo code again
		getPages.getAlmostDonePage().clickAddPromoCodeLink().enterPromoCode(promocode).clickApplyPromoCodeButton();
		// Navigate to payment page and validate keep shopping link
		getPages.getAlmostDonePage().validateUsedPromoCodeMessage(promocode);
		// Clicking on keep shopping link
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Release 2009
	@Test(description = "WCS_CE3760 - Validate that when user is on 'Single Page Checkout' page, a list of all items is display under substitute section.")
	public void validateProductDetailsOnConfirmYourOrderPage() throws Exception {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley.
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		// Fetching the trolley amount, qty of items added, brand, product name
		String trolley_amt = getPages.getTrolleyAndCheckoutPage().getTrolleyAmtFromSuperbar();
		String item_count = getPages.getTrolleyAndCheckoutPage().getTrolleyItemsCountFromSuperbar();
		String productName = getPages.getTrolleyAndCheckoutPage().getTrolleyProductNameFromSuperbar();
		String brandName = getPages.getTrolleyAndCheckoutPage().getTrolleyProductBrandNameFromSuperbar();
		// Proceeding on to the Single Page Checkout screen
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Fetching details from Single Page Checkout Screen
		String trolley_amt_SCP = getPages.getAlmostDonePage().getTrolleyAmtFromSCP();
		String item_count_SCP = getPages.getAlmostDonePage().getTrolleyItemsCountFromSCP();
		String productName_SCP = getPages.getAlmostDonePage().getTrolleyProductNameFromSCP();
		String brandName_SCP = getPages.getAlmostDonePage().getTrolleyProductBrandNameFromSCP();
		// Comparing both results
		if ((trolley_amt_SCP.equals(trolley_amt)) && (item_count_SCP.equals(item_count))
				&& (productName_SCP.equals(productName)) && (brandName_SCP.equals(brandName))) {
			testLog.info("Products are now added correctly on Single Page Checkout Screen.");
		} else {
			testLog.error("There is mismatch from SuperBar page and Single Page checkout screen");
			selLibrary.assertCheck("validateProductDetailsOnSCP",
					"There is mismatch from SuperBar page and Single Page checkout screen");
		}
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE3883 - Validate that user is able to click on Change link next to current date & time on confirm your Order page for Order type Click & Collect.")
	public void validateChangeCCDeliveryDateTimeInConfirmYourOrderPage() {
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
		// Validate change deliverySlot link
		String current_date = getPages.getAlmostDonePage().getSlotDate();
		// getPages.getAlmostDonePage().clickDeliverySlotChangeLink().clickNextButton().CCSlotSelector(true,
		// false);
		getPages.getAlmostDonePage().clickDeliverySlotChangeLink().CCSlotSelector(true, false, true);
		String updated_date = getPages.getAlmostDonePage().getSlotDate();
		if (current_date.equals(updated_date)) {
			testLog.error("Failed to update Delivery slot. Slot Date before change:" + current_date
					+ " Date after update:" + updated_date);
			selLibrary.assertCheck("",
					"Faield to upate Delivery slot. current_date:" + current_date + " updated_date" + updated_date);
		} else {
			testLog.info("selected Date was:" + current_date);
			testLog.info("updated Date is:" + updated_date);
			testLog.info("Slot date changed successfully");
		}
		// Validating Slot Date, Time and price after changing the another slot
		// in
		// Confirm your order page
		getPages.getAlmostDonePage().validateSlotDateTimePrice("CC").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE3884 - Validate that user is able to click on Change link next to current date & time on Confirm your Order page for Order type Remote Delivery")
	public void validateChangeRDDeliveryDateTimeInConfirmYourOrderPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("NT 0820");
		}
		// Select RD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate change deliverySlot link
		String current_date = getPages.getAlmostDonePage().getSlotDate();
		getPages.getAlmostDonePage().clickDeliverySlotChangeLink().RDSlotSelector("skipcurrentselectedday");
		String updated_date = getPages.getAlmostDonePage().getSlotDate();
		if (current_date.equals(updated_date)) {
			testLog.error("Failed to update Delivery slot. Slot Date before change:" + current_date
					+ " Date after update:" + updated_date);
			selLibrary.assertCheck("",
					"Faield to upate Delivery slot. current_date:" + current_date + " updated_date" + updated_date);
		} else {
			testLog.info("selected Date was:" + current_date);
			testLog.info("updated Date is:" + updated_date);
			testLog.info("Slot date changed successfully");
		}
		// Validating Slot Date, Time and price after changing the another slot
		// in
		// Confirm your order page
		getPages.getAlmostDonePage().validateSlotDateTimePrice("RD").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE3890 - Validate that user is able to click on Change link next to current collection on Confirm your Order page for Order type Click & Collect and able to change the address")
	public void validateChangeCCDeliveryAddressInConfirmYourOrderPage() {
		double mov_value = 50;
		String CCAddress = "Casuarina, 0810";
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Change click and collect delivery address
		getPages.getAlmostDonePage().validateDeliveryType("CC").clickDeliveryAddressChangeLink()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Casuarina")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);
		String updated_address = getPages.getAlmostDonePage().getDeliveryAddress();
		// Validating CC updated address
		if (updated_address.contains("Casuarina")) {
			testLog.info("Click and collect slot address changed successfully");
		} else {
			testLog.error("Failed to update Delivery address. updated address is" + updated_address);
			selLibrary.assertCheck("", "Faield to upate Delivery address. Updated address is" + updated_address);
		}
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE3891 - Validate that user is able to click on Change link next to current delivery on Confirm your Order page for Order type Remote Delivery")
	public void validateChangeRDDeliveryDetailsInConfirmYourOrderPage() throws Exception {
		double mov_value = 50;
		String ChangedRDAddress = "Tiwi Barge/Sea Swift";
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Add product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page.
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate change RD Delivery Address
		getPages.getAlmostDonePage().validateDeliveryType("RD").clickDeliveryAddressChangeLink();
		getPages.getChooseDeliveryTimePage().selectNextRDProviderAddress().RDSlotSelector("AnyDay");
		String updated_address = (getPages.getAlmostDonePage().getRD_DeliveryAddress_SPC())
				.concat(getPages.getAlmostDonePage().getRD_DeliveryAddressPostCode_SPC());
		if (updated_address.contains(ChangedRDAddress)) {
			testLog.info("Slot address changed successfully");
		} else {
			testLog.error("Failed to update Delivery address. updated address is" + updated_address);
			selLibrary.assertCheck("", "Faield to upate Delivery address. Updated address is" + updated_address);
		}
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE3893 - Validate that user is able to click on Change link next to Delivery Instructions and to add transport Provider instruction")
	public void validateChangeRDDeliveryInstructionOnConfirmOrderPage() throws Exception {
		double mov_value = 50;
		String DIMessage = "Updated Delivery Instruction Test";
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Add product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page.
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		getPages.getAlmostDonePage().clickChangeDeliveryInstruction();
		// Validating delivery instruction updated
		getPages.getTrolleyAndCheckoutPage().validateRDDelHeadings().enterRD_DeliveryInstruction(DIMessage);
		getPages.getAlmostDonePage().validateRDDeliveryInstructionADPage(DIMessage);
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE4066 - Validate the Signed only message under delivery type section")
	public void validateSignedOnlyDeliveryMessageOnConfirmYourOrderPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("NT 0820");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signaturerequired", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate delivery type message and change link should not display for
		// signed
		// only delivery slot
		getPages.getAlmostDonePage().validateDeliveryTypeMessage("signedonly").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE4067 - Validate the Signed delivery message under delivery type section")
	public void validateSignedDeliveryMessageOnConfirmYourOrderPage() {
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
		// Validate delivery type message and change link should display for
		// signed
		// delivery slot
		getPages.getAlmostDonePage().validateDeliveryTypeMessage("signed").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE4068 - Validate the Unattended delivery message under delivery type section")
	public void validateUDDeliveryMessageOnConfirmYourOrderPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Only if slot is not already selected
		if (getPages.getHomePage().isSlotSelected()) {
			funLibrary.LocalizeToHD("NT 0820");
		}
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1()
				.HDSlotSelector("customerchoiceunattended", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate delivery type message and change link should display for
		// Customer
		// Choice unattended delivery slot
		getPages.getAlmostDonePage().validateDeliveryTypeMessage("customerChoiceUnattended").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE4069 -Validate the click and collect concierge message under collection type section")
	public void validateCCDirectToBootMessageOnConfirmYourOrderPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// Selecting first CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1();
		// Filtering and selecting Click and collect Concierge address
		getPages.getDeliverySlotPage().clickChooseADifferentCCAddressLinkDSS().addFiltersOnChooseCCLocation()
				.clearAllFiltersOnChooseCCLocation().filterCCConcierge().clickShowFilteredLocations()
				.clickFirstClickAndCollectSuburbDSS().validateServiceType("Direct to Boot")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate service type message and change link should not display for
		// click
		// and collect Concierge
		getPages.getAlmostDonePage().validateCCServiceType("Direct to Boot").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_CE4070 - Validate the click and collect service desk message under collection type section")
	public void validateCCServiceDeskMessageOnConfirmYourOrderPage() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// Selecting first CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1();
		// Filtering and selecting Click and collect Service Desk address
		getPages.getDeliverySlotPage().clickChooseADifferentCCAddressLinkDSS().addFiltersOnChooseCCLocation()
				.clearAllFiltersOnChooseCCLocation().filterCCServiceDesk().clickShowFilteredLocations()
				.clickFirstClickAndCollectSuburbDSS().validateServiceType("Service Desk")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate service type message and change link should not display for
		// click
		// and collect Service Desk
		getPages.getAlmostDonePage().validateCCServiceType("Service Desk").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	/*
	 * @Test(description =
	 * "WCS_CE4071 - Validate the click and collect Coles Express message under collection type section"
	 * ) public void validateCCColesExpressMessageOnConfirmYourOrderPage() {
	 * double mov_value = 50; // Logged-in user funLibrary.login();
	 * funLibrary.LocalizeToHD("NT 0820");
	 * getPages.getSuperBarPage().clickChooseDeliverTime().
	 * clickHDAddress("NT 0820") .clickChooseALocationLinkDSS(); // Filtering
	 * and selecting Click and collect Coles express address
	 * getPages.getDeliverySlotPage().addFiltersOnChooseCCLocation()
	 * .clearAllFiltersOnChooseCCLocation().filterCCColesExpress().
	 * clickShowFilteredLocations()
	 * .clickFirstClickAndCollectSuburbDSS().validateServiceType("Coles Express"
	 * ) .clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false); if
	 * (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value); } // Proceed to checkout
	 * getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler(); // Validate service type message and change link should
	 * not display for click and collect Coles Express
	 * getPages.getAlmostDonePage().validateCCServiceType("Coles Express").
	 * clickKeepShopping(); // Logging out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */

	@Test(description = "WCS_CE4072 - Validate the click and collect service locker")
	public void validateCCServiceLockerOnConfirmYourOrderPage() {
		double mov_value = 50;
		String CCAddress = "Rowville, 3178";
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("VIC 3178");
		// Selecting first CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1();
		// Filtering and selecting Click and collect service locker address
		getPages.getDeliverySlotPage().clickChooseADifferentCCAddressLinkDSS().ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Riverton").addFiltersOnChooseCCLocation()
				.clearAllFiltersOnChooseCCLocation().filterCCServiceLocker().clickShowFilteredLocations()
				.clickFirstClickAndCollectSuburbDSS().validateServiceType("Lockers")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);
		getPages.myAccountPage().closeSuperBar();
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate collection type section should not display for service
		// lockers
		getPages.getAlmostDonePage().validateCCServiceType("Lockers").clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT228- Validate order details at 'Oder History' screen", enabled = true)
	public void validateOderAttributes() throws Exception {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();// .checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Select payment mode as card and enter details

		getPages.getAlmostDonePage().clickPayUsingPayPal().clickAtPaypalAccount().enterPaypalID().clickNext()
				.enterPaypalPassword().clickPaypalLoginButton().clickAgreeAndContinueButton();
		// getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
		// .selectExpiryYear().enterCVV().clickContinueButton();
		String delivery_type = getPages.getAlmostDonePage().getDeliveryType();
		double total_amount = getPages.getAlmostDonePage().getTotalAmount();
		double trolley_amount = getPages.getAlmostDonePage().getTrolleyAmount();
		double baggingPrice = getPages.getAlmostDonePage().getBagPrice();
		// Submit order
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder();
		// Get Order ID
		String orderId = getPages.getAlmostDonePage().getOrderId();
		// Return to home
		getPages.getOrderConfirmationPage().verifyOrderConfirmation().clickReturnToHome();
		// Navigate to Orders and select the placed order and click Modify this
		// order
		getPages.getSuperBarPage().clickOrders().clickPlacedOrder(orderId);
		// Validate order history page details
		getPages.getOrdersPage().verifyTrolleyTotal(trolley_amount).verifyTotalAmount(total_amount)
				.verifyDeliveryType(delivery_type).verifyBaggingPrice(baggingPrice);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT234- Validate trolley amount on 'Confirm your order' page for special products")
	public void validateTrolleyAndCheckOutAmountForSpecialItems() {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley();
		// .clickProceedToCheckOutButton().TrolleyHandler();
		// Getting the trolley total and savings from trolley
		double trolleyTotal = getPages.getTrolleyAndCheckoutPage().getExpandTrolleyAmount();
		double totalSavings = getPages.getTrolleyAndCheckoutPage().getTotalSaving();
		// Navigating to Checkout Page
		getPages.getTrolleyAndCheckoutPage().clickProceedToCheckOutButton().TrolleyHandler();
		// Verifying the trolley amount and savings
		getPages.getAlmostDonePage().validateTotalTrolleyAmount(trolleyTotal).validateTotalSaving(totalSavings);
		// Clicking Keep Shopping Link
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT241- Validate trolley amount on 'Confirm your order' page for special and non-special products")
	public void validateTrolleyAndCheckoutAmountForCombinationltems() {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley();
		// .clickProceedToCheckOutButton().TrolleyHandler();
		// Getting the trolley total and savings from trolley
		double trolleyTotal = getPages.getTrolleyAndCheckoutPage().getExpandTrolleyAmount();
		double totalSavings = getPages.getTrolleyAndCheckoutPage().getTotalSaving();
		// Navigating to Checkout Page
		getPages.getTrolleyAndCheckoutPage().clickProceedToCheckOutButton().TrolleyHandler();
		// Verifying the trolley amount and savings
		getPages.getAlmostDonePage().validateTotalTrolleyAmount(trolleyTotal).validateTotalSaving(totalSavings);
		// Clicking Keep Shopping Link
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	/*
	 * @Test(description =
	 * "WCS_CE4073 - Validate the click and collect mobile collect") public void
	 * validateCCMobileOnConfirmYourOrderPage() { double mov_value = 50; //
	 * Logged-in user funLibrary.login(); funLibrary.LocalizeToHD("VIC 3023");
	 * // Selecting first CC address
	 * getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1();
	 * // Filtering and selecting Click and collect service locker address
	 * getPages.getDeliverySlotPage().clickChooseADifferentCCAddressLinkDSS().
	 * addFiltersOnChooseCCLocation()
	 * .clearAllFiltersOnChooseCCLocation().filterCCMobile().
	 * clickShowFilteredLocations()
	 * .clickFirstClickAndCollectSuburbDSS().validateServiceType("Mobile")
	 * .clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false); if
	 * (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
	 * getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	 * mov_value); } // Proceed to checkout
	 * getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().
	 * TrolleyHandler(); // Validate collection type section should not display
	 * for mobile collect
	 * getPages.getAlmostDonePage().validateCCServiceType("Mobile").
	 * clickKeepShopping(); // Logging out user
	 * getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */

	@Test(description = "WCS_AUT247- Verify WA products details on 'confirm your order' page")
	public void validateTrolleyAndTotalAmountForWA() {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to mount pleasant address
		//funLibrary.LocalizeToHD("WA 6153");
		// Localize to Riverton address
		funLibrary.LocalizeToHD("WA 6148");

		if (getPages.getHomePage().isSlotSelected()) {
			testLog.info("Slot is already selected.");
		} else {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("WA 6153").HDSlotSelector_WA("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		}

		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page. Click on View Trolley button and check
		// superbar is expanded on homepage
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate total amount selecting bag option in almost done page
		getPages.getAlmostDonePage().validateTotalAmountWithBagForWA();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT248-Verify WA products details on 'confirm your order' page(CC).")
	public void validateTotalAmountBagToBaglessForCCForWA() throws Exception {
		double mov_value = 50;
		String CCAddress = "Riverton, 6148";
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("WA 6153");
		// Localize to Riverton CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Riverton")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);

		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();

		// Validate total amount selecting bag option in almost done page
		getPages.getAlmostDonePage().validateTotalAmountWithBagForWA();
		double bagPrice = getPages.getAlmostDonePage().getBagPrice();
		double totalAmt = getPages.getAlmostDonePage().getTotalAmount();
		// Validate total order cost after changing to bagless in almost done
		// page
		getPages.getAlmostDonePage().changeToNoBag().validateTotalAmountWithBagless(bagPrice, totalAmt)
				.clickKeepShoppingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT250- Verify trolley items after modify trolley from 'Confirm your order' screen for WA(HD).")
	public void validateTrolleyItemsCountAfterModifyTrolleyForHD() {
		double mov_value = 50;
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("WA 6153");

		if (getPages.getHomePage().isSlotSelected()) {
			testLog.info("Slot is already selected.");
		} else {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("WA 6153").HDSlotSelector_WA("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		}

		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page. Click on View Trolley button and check
		// superbar is expanded on homepage
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();

		int countOfItems_almostDonePage = getPages.getAlmostDonePage().almostDonePageNumberOfProducts();

		// Clicking Modifying Trolley
		getPages.getAlmostDonePage().clickModifyTrolley().validateViewTrolleyButton();
		// Getting the Number of items in trolley
		int trolleyItems = getPages.getHomePage().countOfTrolleyItems();
		if (countOfItems_almostDonePage == trolleyItems) {

			testLog.info("Great!! The number of items are same available at almost done page and modified trolley.");
		} else {
			testLog.error(
					"OOPS!! The number of items are not matching which are available at almost done page and modified trolley.");
			selLibrary.assertCheck("validateTrolleyItemsCountAfterModifyTrolleyForHD",
					"OOPS!!, No product is available in the trolley.");
		}
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT252- Verify trolley items after modify trolley from 'Confirm your order' screen for WA(CC).")
	public void validateTrolleyItemsCountAfterModifyTrolleyForCC() {
		double mov_value = 50;
		String CCAddress = "Riverton, 6148";
		// User Logged-in
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("WA 6153");
		// Localize to Riverton CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Riverton")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, false, false);

		// Adding product to trolley,
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < 50) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Process to Almost done page. Click on View Trolley button and check
		// superbar is expanded on homepage
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();

		int countOfItems_almostDonePage = getPages.getAlmostDonePage().almostDonePageNumberOfProducts();

		// Clicking Modifying Trolley
		getPages.getAlmostDonePage().clickModifyTrolley().validateViewTrolleyButton();
		// Getting the Number of items in trolley
		int trolleyItems = getPages.getHomePage().countOfTrolleyItems();
		if (countOfItems_almostDonePage == trolleyItems) {

			testLog.info("Great!! The number of items are same available at almost done page and modified trolley.");
		} else {
			testLog.error(
					"OOPS!! The number of items are not matching which are available at almost done page and modified trolley.");
			selLibrary.assertCheck("validateTrolleyItemsCountAfterModifyTrolleyForCC",
					"OOPS!!, No product is available in the trolley.");
		}
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
