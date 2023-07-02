package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class DeliverySlotTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT073- Verify user is able to localize to home address")
	public void validateHDLocalization_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().clickPostLoginGeoLocationChangeLink();
		String newSelectedAddress = getPages.getChooseDeliveryTimePage().selectOtherThanSelctedHDAddress();
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		String currentLocalisedAddress = getPages.getHomePage().getcurrentLoacaliseAddress();
		getPages.getHomePage().validateLocalization(newSelectedAddress, currentLocalisedAddress);
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT074- Verify user can localized to CC address when click on change link in home page")
	public void validateCCLocalization_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().clickPostLoginGeoLocationChangeLink();
		String newSelectedAddress = getPages.getChooseDeliveryTimePage().selectCCAddress();
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		String currentLocalisedAddress = getPages.getHomePage().getcurrentLoacaliseAddress();
		getPages.getHomePage().validateLocalization(newSelectedAddress, currentLocalisedAddress)
				.clickPostLoginGeoLocationChangeLink();
		getPages.getChooseDeliveryTimePage().clickHDSlotLink1().ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT075- Verify user can localized to RD address when click on change link in home page")
	public void validateRDLocalization_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().clickPostLoginGeoLocationChangeLink();
		String newSelectedAddress = getPages.getChooseDeliveryTimePage().selectRDAddress();
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		String currentLocalisedAddress = getPages.getHomePage().getcurrentLoacaliseAddress();
		getPages.getHomePage().validateLocalization(newSelectedAddress, currentLocalisedAddress)
				.clickPostLoginGeoLocationChangeLink();
		getPages.getChooseDeliveryTimePage().clickHDSlotLink1().ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT163- Verify user is able to select HD slot")
	public void validateHDSlotSelected() {
		// Logged-in user
		funLibrary.login();
		// Only if slot is already selected
		if (getPages.getHomePage().isSlotSelected()) {
			testLog.info("A slot is already selected. Removing the slt and localising user to NT");
			funLibrary.LocalizeToHD("NT 0820");
		}
		// Creating HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("HD").HDSlotSelector("signed", true, false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT164- Verify user is able to select CC slot")
	public void validateCCSlotSelected() {
		// Logged-in user
		funLibrary.login();
		// Only if slot is already selected
		// funLibrary.LocalizeToHD("Bayview NT");
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChooseDifferentTimeLink()
					.clickChooseDeliveryAddressLinkDSS().clickHDAddressDSS("Bayview").ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// Creating HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT165- Verify user can select RD slot")
	public void validateRDSlotSelction() {
		// Logged-in with registered user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT166- Verify user can select RD slot")
	public void validateDeliveryRestrictionForUD() {
		double mov_value = 50;
		// Logged-in with registered user
		funLibrary.login();
		funLibrary.LocalizeToHD("VIC 3166");
		// empty trolley before selecting slot
		getPages.getHomePage().clickTrolley().emptyTrolley();
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1()
				.HDSlotSelector("CustomerChoiceUnattended", true, false);
		getPages.myAccountPage().closeSuperBar();
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler().validateDeliveryRestrictionInCheckoutFlow()
				.clickDeliveryRestrictionKeepItemButton().clickAlreadySelectedWindowSlot()
				.validateDeliveryRestrictionInDSSPage("CustomerChoiceUnattended", funLibrary.search_1)
				.clickDSSSlotPopupContinueBtn().ClickCancelAndKeepShopingLink();
		funLibrary.LocalizeToHD("VIC 3166");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("UnattendedOnly", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler()
				.validateDeliveryRestrictionInCheckoutFlow().clickDeliveryRestrictionKeepItemButton()
				.clickAlreadySelectedWindowSlot()
				.validateDeliveryRestrictionInDSSPage("UnattendedOnly", funLibrary.search_2)
				.clickDSSDeliveryRestrictionKeepItemButton().ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT167-Verify delivery instructions for unattended delivery, WCS_AUT200- Verify that user is able to enter instructions and save successfully, WCS_AUT201- Verify that user is able to edit instructions and save successfully, WCS_AUT202- Verify that user is able to remove instructions.")
	public void validateDeliveryInstructionForUD() {
		String dIMessage = "Unattended delivery instruction message";
		String updateDIMessage = "Updating Unattended delivery instruction message";
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("unattendedonly", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Validate delivery instruction pop up and save instruction
		getPages.getTrolleyAndCheckoutPage().validateUDInstructionsPage().enterDeliveryInstruction(dIMessage);
		// Validating WCS_AUT200 Saved delivery instruction in almost done page
		getPages.getAlmostDonePage().validateDeliveryTypeADPage("unattended")
				.validateDeliveryInstructionADPage(dIMessage).clickChangeDeliveryInstruction();
		// Validating WCS_AUT201 Edit delivery instruction
		getPages.getTrolleyAndCheckoutPage().enterDeliveryInstruction(updateDIMessage);
		getPages.getAlmostDonePage().validateDeliveryTypeADPage("unattended")
				.validateDeliveryInstructionADPage(updateDIMessage).clickKeepShoppingLink();
		// Validate DI pop up shouldn't display when instruction is already
		// saved
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption();
		getPages.getAlmostDonePage().clickChangeDeliveryInstruction();
		// Validating WCS_AUT202 Remove delivery instruction
		getPages.getTrolleyAndCheckoutPage().removeDeliveryInstruction().saveDeliveryInstruction();
		// Validate DI shouldn't display in almost done page
		getPages.getAlmostDonePage().validateRemovedDeliveryInstructionADPage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT168-Verify user is able to select Signed delivery window")
	public void validateSignedDelivery() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Validate Delivery type is signed delivery in almost done page
		getPages.getAlmostDonePage().validateDeliveryTypeADPage("signed");
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT169-Verify user is able to select UD delivery window")
	public void validateUnattendedDelivery() {
		String dIMessage = "Unattended delivery instruction message";
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("unattendedonly", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		if (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount() < mov_value) {
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Proceed to checkout
		getPages.getSuperBarPage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// Enter delivery instruction
		getPages.getTrolleyAndCheckoutPage().enterDeliveryInstruction(dIMessage);
		// Validate Delivery type is Unattended delivery in almost done page
		getPages.getAlmostDonePage().validateDeliveryTypeADPage("unattended");
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT170- Verify that user gets bagging option at the time of slot selection")
	public void validateBaggingOptionsAtDSSPopup() {
		// Logged-in user
		funLibrary.login();
		// Only if slot is already selected
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickAddress("HD");
			getPages.getDeliverySlotPage().ClickChooseLocation().clickClickAndCollectLocation()
					.clickChooseCollectionTime().ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// Creating HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().validateDSSPopupWindow("Any Day",
				"customerchoiceunattended", true);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT171- Verify user donot get bag option if selecting unattended window. Bag must be already selected.")
	public void validateBaggingOptionsAtDSSUnattendedWindow() {
		// Logged-in user
		funLibrary.login();
		// Only if slot is already selected
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickAddress("HD");
			getPages.getDeliverySlotPage().ClickChooseLocation().clickClickAndCollectLocation()
					.clickChooseCollectionTime().ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// Creating HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().validateDSSPopupWindow("Any Day",
				"unattendedonly", true);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Functional change- noBagOption functionality has been obsolete
	// @Test(description = "WCS_AUT172- Verify Remember my bagging preference on
	// DSS page in customer choice widow popup and AD page")
	// public void validateRememberBaggingPreferenceCheckbox() {
	// // Register new user
	// funLibrary.registerNewUser();
	// // selecting remember bag preference true
	// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed",
	// false, false);
	// getPages.myAccountPage().closeSuperBar();
	//// funLibrary.LocalizeToHD("NT 0820");
	// // Again selecting slot and validate bag option should come as selected.
	//// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().validateBaggingPreferenceCheckbox()
	//// .clickDSSSlotPopupContinueBtn();
	// // Add products to cart
	// double mov_value = 50;
	// getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	// mov_value).clickTrolley()
	// .clickProceedToCheckOutButton().TrolleyHandler();
	// // checkoutFlowHandler().selectAnyAvailablePaymentOption();
	// // validating correct bag option is selected or not on AD Page. Bag
	// option must
	// // be as passed in parameter.
	// getPages.getAlmostDonePage().validateSelectedBagOption("Bag").clickKeepShoppingLink();
	// // Logging out user
	// getPages.getSuperBarPage().clickMyAccount().clickLogout();
	// }

	// Functional change- noBagOption functionality has been obsolete
	// @Test(description = "WCS_AUT173- Change bagging preference from bag to
	// bagless and validate it in DSS and AD")
	// public void validateChangeBaggingPreference() {
	// // Register new user
	// funLibrary.registerNewUser();
	// // selecting remember bag preference true
	// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed",
	// true, true);
	// getPages.myAccountPage().closeSuperBar();
	// // funLibrary.LocalizeToHD("NT 0820");
	// // Again selecting slot and validate bag option should come as selected.
	//// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().validateBaggingPreferenceCheckbox()
	//// .clickDSSSlotPopupContinueBtn();
	//// funLibrary.LocalizeToHD("NT 0820");
	//// // selecting preference checkbox true for bagless
	//// getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector(
	// "signed",
	//// false, true);
	// /*
	// * funLibrary.LocalizeToHD("NT 0820");
	// * getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().
	// * validateBaggingPreferenceCheckbox();
	// */
	// // Add products to cart
	// double mov_value = 50;
	// getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y",
	// mov_value).clickTrolley()
	// .clickProceedToCheckOutButton().TrolleyHandler();
	// // checkoutFlowHandler().selectAnyAvailablePaymentOption();
	// // validating correct bag option is selected or not on AD Page. Bag
	// option must
	// // be as passed in parameter.
	// getPages.getAlmostDonePage().validateSelectedBagOption("Bag").clickKeepShoppingLink();
	// // Logging out user
	// getPages.getSuperBarPage().clickMyAccount().clickLogout();
	// }

	@Test(description = "WCS_AUT0175- Verifies user is able to select bagless option if previous preference selected is bagged")
	public void validateChangeBaggingPreferenceForCC() {
		double mov_value = 50;
		String CCAddress = "Casuarina, 0810";
		// Register new user
		// funLibrary.registerNewUser();
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Closing the popup and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Localize to Casuarina CC address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Casuarina")
				.clickChooseCollectionTimeButtonDSS().CCSlotSelector(true, true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption();
		getPages.getAlmostDonePage().clickChangeBaggingOptionLink().validateCCBaggingPreference("Bag").changeToNoBag()
				.clickChangeBaggingOptionLink().clickRememberMyPreference().clickChangeBaggingOptionLink()
				.validateCCBaggingPreference("Bagless").clickKeepShoppingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT177- place RD order and check order total in Almost Done Page")
	public void validateBagOptionForRDOrder() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// select RD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption()
		getPages.getAlmostDonePage().checkRDBagOption().clickKeepShoppingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Functional change- noBagOption functionality has been obsolete
	// @Test(description = "WCS_AUT178- Validate HD slot no bag selected error
	// messages")
	// public void validateDSSHDBagOptionErrorMsg() {
	// // Logged-in user
	// funLibrary.login();
	// funLibrary.LocalizeToHD("NT 0820");
	// getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstHDAddressLink().validateDSSHDSlotNoBagSelected();
	//
	// // Logging out user
	// getPages.getSuperBarPage().clickMyAccount().clickLogout();
	// }

	@Test(description = "WCS_AUT179- Validate CC slot no bag selected error messages")
	public void validateDSSCCBagOptionErrorMsg() {
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().validateDSSCCSlotNoBagSelected();
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT181- Verify DSS page when no HD slots are available", enabled = false)
	public void validateHDSlotsNotAvailableDSSPage() {
		// Logged-in user
		funLibrary.login();
		// Validate HD slots are not available in DSS page
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NSW").validateHDSlotsNotAvailable()
				.ClickCancelAndKeepShopingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT182- Verify DSS page when no CC slots are available", enabled = false)
	public void validateCCSlotsNotAvailableDSSPage() {
		// Logged-in user
		funLibrary.login();
		// Validate CC slots are not available in DSS page
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().validateCCSlotsNotAvailable()
				.ClickCancelAndKeepShopingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT183- Verify DSS page when no RD slots are available", enabled = false)
	public void validateRDSlotsNotAvailableDSSPage() {
		// Logged-in user
		funLibrary.login();
		// Validate RD slots are not available in DSS page
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink();
		getPages.getDeliverySlotPage().selectThirdRDProviderAddress().validateRDSlotsNotAvailable()
				.ClickCancelAndKeepShopingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT184- Verify that user gets a popup while selecting slot for restricted items.")
	public void validateDeliveryRestrictionPopupDSSPage() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding restricted product (Donuts) to trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "1");
		// Validate user getting delivery restricted popup
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDRistrictedSlotSelector()
				.ClickCancelAndKeepShopingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT185- Change Delivery Location from superbar")
	public void validateSuperBarChangeDeliveryLocation() {
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// selecting slot for location NT 0820
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
				false);
		// changing delivery location to VIC 3166
		getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeLink().clickHDAddress("VIC 3166")
				.HDSlotSelector("signed", true, false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	// Functional change- NextAndPrevious will not be available at DSS page
	/*
	 * @Test(description =
	 * "WCS_AUT186- Validate next and previous button in DSS page") public void
	 * validateDSSNextAndPreviousButton() { // Logged-in user funLibrary.login();
	 * funLibrary.LocalizeToHD("NT 0820"); // selecting slot for location NT 0820
	 * getPages.getSuperBarPage().clickChooseDeliverTime().
	 * clickHDAddress("NT 0820")
	 * .clickNextButton().clickPreviousButton().ClickCancelAndKeepShopingLink(); //
	 * Logging out user getPages.getSuperBarPage().clickMyAccount().clickLogout(); }
	 */
	
	@Test(description = "WCS_AUT245- Verify bagging price for WA at overlay popup window on DSS page(HD).")
	public void validateHDBaggingOptionsAtDSSPopupForWA() {
		// Logged-in user
		funLibrary.login();
		// Localize to mount pleasant address
		//funLibrary.LocalizeToHD("WA 6153");
		// Localize to Riverton address
		funLibrary.LocalizeToHD("WA 6148");
		// Only if slot is already selected
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickAddress("HD");
			getPages.getDeliverySlotPage().ClickChooseLocation().clickClickAndCollectLocation()
					.clickChooseCollectionTime().ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// Creating HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().validateHDDSSPopupWindowForWA("Any Day",
				"customerchoiceunattended", true);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
	
	@Test(description = "WCS_AUT246- Verify bagging price for WA at overlay popup window on DSS page(CC).")
	public void validateCCBaggingOptionsAtDSSPopupForWA() {
		String CCAddress = "Riverton, 6148";
		// Logged-in user
		funLibrary.login();
		// Localize to Riverton address
		funLibrary.LocalizeToHD("WA 6148");
		// Select slot from DSS page and validate the bagging price on overlay popup window
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().clickChooseADifferentCCAddressLinkDSS()
				.ClearandSearchAutoSuggestion(CCAddress).SearchCCAddress("Riverton")
				.clickChooseCollectionTimeButtonDSS().validateCCbaggingprice_WA();
		// Closing superbar
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
