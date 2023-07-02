package com.swiftshop.tests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class MyAccountTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT078- Verify  that user is able to create List successfully ")
	public void validateMyListCreated_LoggedInUser() {
		String myListName = "MyList01";
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clearMyListIfExist().createList(myListName)
				.validateListSuccessMsg().goToMyLists().validtedCreatedList(myListName).goToMyAccountPage()
				.clickLogout();
	}

	@Test(description = "WCS_AUT079- Verify  user is able to edit List successfully")
	public void validateListEditedSuccefully_LoggedInUser() {
		String myListName = "MyList01";
		String myUpdatedList = "MyList02";
		// Logged-in with registered user
		funLibrary.login();
		// Validating list edited successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clearMyListIfExist().createList(myListName)
				.goToMyLists().editAndUpdateList(myUpdatedList).validtedUpdatedList(myUpdatedList).goToMyAccountPage()
				.clickLogout();
	}

	@Test(description = "WCS_AUT080- Verify  user is able to delete created List successfully ")
	public void validateMyListdeleted_LoggedInUser() {
		String myListName = "MyList01";
		// Logged-in with registered user
		funLibrary.login();
		// Validating list deleted successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clearMyListIfExist().createList(myListName)
				.validateListSuccessMsg().goToMyLists().validtedCreatedList(myListName).clearMyList()
				.validateMyListDeletedSuccessfully(myListName).goToMyAccountPage().clickLogout();
	}

	@Test(description = "WCS_AUT081- Verify user can edit name, DOB, email, phone number and business number with vaild details")
	public void validateAccountDetailsWithValidData_LoggedInUser() {
		String updatedFirstName = "Automation", updateLastName = "Tester";
		String originalFirstName = "Test", OriginalLastName = "User";
		// Logged-in with registered user
		funLibrary.login();
		getPages.getSuperBarPage().clickMyAccount().clickAccountEdit().validateAccountAllDetailsText()
				.enterName(updatedFirstName, updateLastName).enterDOB("20", "2020").enterMobileNumber("0412345678");
		getPages.myAccountPage().clickSaveChanges().validateSuccessMsg()
				.validateAccountName(updatedFirstName + "" + updateLastName).clickAccountEdit()
				.enterName(originalFirstName, OriginalLastName).enterDOB("10", "1999").clickSaveChanges()
				.validateSuccessMsg().validateAccountName(originalFirstName + "" + OriginalLastName).closeSuperBar();
		getPages.getSuperBarPage().clickMyAccount().clickAccountEdit().clickBusinessUserCheckbox()
				.enterBusinessABN("Test BUsiness User", "45004189708").clickSaveChanges().validateSuccessMsg();
	}

	@Test(description = "WCS_AUT082- Verify user can edit name, DOB, email, phone number and business number with invaild details")
	public void validateAccountDetailsWithInValidData_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();

		getPages.getSuperBarPage().clickMyAccount().clickAccountEdit().validateAccountAllDetailsText()
				.enterName("test-user'aaa one", "tester-user'bbb two").enterDOB("aa", "1980").enterEmail("abcdefg@abcd")
				.enterMobileNumber("041234567").clickSaveChanges().validateErrorMsgs("ErrorMsg1")
				.enterName("test@%", "user&*").enterDOB("32", "2030").enterEmail("abcdefg@abcd.com")
				.enterMobileNumber("+041234567").clickSaveChanges().validateErrorMsgs("ErrorMsg2").closeSuperBar();
		getPages.getSuperBarPage().clickMyAccount().clickAccountEdit().enterBusinessABN("@$test", "abcdef")
				.clickSaveChanges().validateErrorMsgs("ErrorMsg3");
	}

	@Test(description = "WCS_AUT083- Add Secondary email adress")
	public void validateAddSecondaryEmail_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();

		getPages.getSuperBarPage().clickMyAccount();
		if (getPages.myAccountPage().validateSecondaryEmailHeadingNotPresent()) {
			testLog.info("Secondary email is not present");
		}
		getPages.myAccountPage().clickAccountEdit().validateAccountAllDetailsText().enterSecondaryEmail()
				.clickSaveChanges().validateSuccessMsg();

		if (getPages.myAccountPage().validateSecondaryEmailHeadingNotPresent()) {
			testLog.error("Secondary email already present. Please clear the secondary email");
			selLibrary.assertCheck("validateSecondaryEmailHeadingNotPresent", "Secondary email already present",
					"Account_Detail_SecondaryEmail_Heading");

		} else {
			testLog.info("Secondary email has been added successfully");
		}

		getPages.myAccountPage().clickAccountEdit();
		selLibrary.Clear_Text(funLibrary.OR_OR.getProperty("Name_Contact_Details_SecondaryEmail_Input"),
				"Name_Contact_Details_SecondaryEmail_Input");
		getPages.myAccountPage().clickSaveChanges();
	}

	@Test(description = "WCS_AUT084- Verify Secondary email adress")
	public void validateSecondaryEmailDetails_LoggedInUser() {
		String secondary_email = "";
		// Logged-in with registered user
		funLibrary.login();

		getPages.getSuperBarPage().clickMyAccount().clickAccountEdit().validateAccountAllDetailsText();
		secondary_email = getPages.myAccountPage().validateSecondaryEmail();
		if (secondary_email.isEmpty()) {
			testLog.error("Secondary email is empty");
			selLibrary.assertCheck("validateSecondaryEmail", "Secondary email is empty",
					"Name_Contact_Details_SecondaryEmail_Input");
		} else {
			testLog.info("Secondary Email is present");
		}
	}

	@Test(description = "WCS_AUT085- Secondary email adress negative scenario")
	public void validateSecondaryEmailNotAdded_LoggedInUser() {
		String secondary_email = "";
		// Logged-in with registered user
		funLibrary.login();

		getPages.getSuperBarPage().clickMyAccount();
		if (getPages.myAccountPage().validateSecondaryEmailHeadingNotPresent()) {
			testLog.info("Secondary email is not present");
		}
		getPages.myAccountPage().clickAccountEdit().validateAccountAllDetailsText().validateSecondaryEmail();
		secondary_email = getPages.myAccountPage().validateSecondaryEmail();
		if (secondary_email.isEmpty()) {
			testLog.info("Secondary Email is empty");

		} else {
			testLog.error("Secondary email is present:secondary_email");
			selLibrary.assertCheck("validateSecondaryEmail", "Secondary email is not empty",
					"Name_Contact_Details_SecondaryEmail_Input");
		}
	}

	@Test(description = "WCS_AUT086- Verify user can link flybuys number when user click on link flybuys in my account")
	public void validateLinkFlybuysNum_LoggedInUser() {
		// Logged-in with registered user
		funLibrary.login();

		// Link flybuys number and validate success message, at the last delete linked
		// Fly buys number linked validation
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys().validateFlybuysLinkText().clickLinkFlybuys()
				.validateAllFlybuysFieldsText().enterFlybuysNumber("9420", "0109", "5217").ClickLinkFlybuysButton()
				.validateFlybuysPasswordPageText().clickContinueWithoutImport().validateFlybuysSuccessMsg()
				.validateLinkedFlybuysNum("EndsWith_5217").deleteLinkedFlybuysNum().validateDeleteSuccessMsg()
				.clickLogout();
	}

	@Test(description = "WCS_AUT101- Verify user can add the address in my account")
	public void validateAddNewAddress_LoggedInUser() {
		funLibrary.login();
		// Add new address in user profile
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().clickAddNewAddress()
				.enterAddressDetails(funLibrary.streetAddr, funLibrary.nickname);
		// Delete added address and logging-out user
		getPages.myAccountPage().deleteAllAddress().clickBackLink().clickLogout();
	}

	@Test(description = "WCS_AUT102- Verify user can edit the address in my account")
	public void validateEditAddress_LoggedInUser() {
		funLibrary.login();
		// Add new address
		String reg_nickname = getPages.myAccountPage().getNickName();
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().clickAddNewAddress()
				.enterAddressDetails(funLibrary.streetAddr, funLibrary.nickname);
		// Edit the same address and delete the address
		getPages.myAccountPage().clickEditButton().enterAddressDetails("20 bayview", reg_nickname).validateUpdatedIcon()
				.deleteAllAddress().clickBackLink().clickLogout();
	}

	@Test(description = "WCS_AUT103- Verify user can remove address in my account")
	public void validateDeleteAddress_LoggedInUser() {
		funLibrary.login();
		// Add new address
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().clickAddNewAddress()
				.enterAddressDetails(funLibrary.streetAddr, funLibrary.OR_OR.getProperty("NewHDAddress01"));
		// Add new address via entering same nickname
		getPages.myAccountPage().clickAddNewAddress().enterAddressDetails(funLibrary.streetAddr,
				funLibrary.OR_OR.getProperty("NewHDAddress01"));
		// Validating nickname form via entering new nickname, at the last deleted all
		// address
		getPages.myAccountPage().validateNickName().clickBackLink().closeSuperBar();
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().clickBackLink().clickLogout();
	}

	@Test(description = "WCS_AUT104- Verify user can select Email option as communication preferences")
	public void validateCommunicationPreferenceViaEmail_LoggedInUser() {
		// User login
		funLibrary.login();
		// Save communication preferences via Email
		getPages.getSuperBarPage().clickMyAccount().clickCommunicationPreferences().validateAllCommunicationPrefFields()
				.checkCommPref("Email").clickSavePreferences().validatePreferenceUpdatedIcon();
		// Save communication preferences via both Email and SMS
		getPages.myAccountPage().clickCommunicationPreferences().checkCommPref("EmailAndSMS").clickSavePreferences()
				.validatePreferenceUpdatedIcon().clickLogout();
	}

	@Test(description = "WCS_AUT105- Verify user can select SMS option as communication preferences")
	public void validateCommunicationPreferenceViaSMS_LoggedInUser() {
		// User login
		funLibrary.login();
		// Save communication preferences via SMS
		getPages.getSuperBarPage().clickMyAccount().clickCommunicationPreferences().validateAllCommunicationPrefFields()
				.checkCommPref("SMS").clickSavePreferences().validatePreferenceUpdatedIcon();
		// Save communication preferences via both Email and SMS
		getPages.myAccountPage().clickCommunicationPreferences().checkCommPref("EmailAndSMS").clickSavePreferences()
				.validatePreferenceUpdatedIcon().clickLogout();
	}

	// This functionality has been obsolted, 'User Story-CE-14503'
	/*
	 * @Test(description = "WCS_AUT106- Verify user can save Delivery preferences")
	 * public void validateDeliveryPreferences_LoggedInUser() { // User login
	 * funLibrary.login(); // Save delivery preferences
	 * getPages.getSuperBarPage().clickMyAccount().clickDeliveryPreferences().
	 * validateAllDeliveryPreferencesFields()
	 * .DelPrefCheckBox("check").clickSavePreferences().
	 * validatePreferenceUpdatedIcon(); // Remove delivery preferences and logging
	 * out user
	 * getPages.myAccountPage().clickDeliveryPreferences().DelPrefCheckBox("uncheck"
	 * ).clickSavePreferences() .validatePreferenceUpdatedIcon().clickLogout(); }
	 */

	@Test(description = "WCS_AUT107- Verify user can add and clear preferred substitutes")
	public void validateAddPreferredSubstitute() {
		// User login
		funLibrary.login();
		// Add preferred substitutes and clear all substitutes
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().validateAllPreferredSubsFields()
				.clearAllSubstitutes().clickFirstProductchoosePrefSub().validatePrefSubPopup()
				.clickProductSetAsSubs("SetProduct1").clickSecondProductchoosePrefSub()
				.clickProductSetAsSubs("SetProduct1").clearAllSubstitutes().clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT108- Verify user can change preferred substitutes")
	public void validateChangePreferredSubstitute() {
		// User login
		funLibrary.login();
		// Change preferred substitutes and clear all substitutes
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().validateAllPreferredSubsFields()
				.clearAllSubstitutes().clickFirstProductchoosePrefSub().validatePrefSubPopup()
				.clickProductSetAsSubs("SetProduct1").clickChangeLink().clickProductSetAsSubs("SetProduct2")
				.clearAllSubstitutes().clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT109- Verify Preffered substitute screen when no products available in BB Tab")
	public void validatePreferredSubstituteWithoutBBProducts() {
		// User login
		funLibrary.login();
		// Validate no bought before products available
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().validatePrefSubsPageWithoutBBProducts()
				.clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT110- Verify Preffered Susbtitue search")
	public void validatePreferredSubstituteSearch() {
		// User login
		funLibrary.login();
		// Search preferred substitutes
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().searchPrefSubsItem(funLibrary.search_1)
				.clickPrefSubsSearchButton().validateSearchResultText(funLibrary.search_1)
				.validateSearchedProductName(funLibrary.search_1).clickClearButton()
				.searchPrefSubsItem(funLibrary.search_2).clickPrefSubsSearchButton()
				.validateSearchResultText(funLibrary.search_2).validateSearchedProductName(funLibrary.search_2)
				.clickClearButton().clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT111- Verify Preffered Susbtitue Time Bought sorting")
	public void validatePreferredSubstituteTimesBoughtSorting() {
		// User login
		funLibrary.login();
		// Sort product by times bought and validate the sorting
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes();
		ArrayList<String> allproductNames = getPages.myAccountPage().getAllProductName();
		getPages.myAccountPage().clickSortingChangeBtn().clickSortingOptionTimesBought().clickSortingOptionSaveBtn()
				.validateSortedByTimesBought(allproductNames).clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT112- Verify Brand name (A-Z) sorting for producst in Preffered substitue list")
	public void validatePreferredSubstituteBrandNameSorting() {
		// User login
		funLibrary.login();
		// Sort product by brand name and validate the sorting
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().clickSortingChangeBtn()
				.clickSortingOptionBrand().clickSortingOptionSaveBtn().validateSortedByBrand().clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT113- Verify Product name (A-Z) sorting for producst in Preffered substitue list")
	public void validatePreferredSubstituteProductNameSorting() {
		// User login
		funLibrary.login();
		// Sort product by product name and validate the sorting
		getPages.getSuperBarPage().clickMyAccount().clickPreferredSubstitutes().clickSortingChangeBtn()
				.clickSortingOptionProduct().clickSortingOptionSaveBtn().validateSortedByProduct()
				.clickBackToHomePage();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT114- Verify user can change  password successfully from my account page", enabled = false)
	public void validateAccountPasswordChangedSuccessfully() {
		String newPassword = "newpassw0rd";
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Validate password changed successfully and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickChangeYourPassword().enterOldPassword(funLibrary.password)
				.enterNewPassword(newPassword).clickSaveButton().validatePasswordChangeSuccessMessage().clickLogout();
	}

	@Test(description = "WCS_AUT115- Verify user can select/unselect shopping reminders")
	public void validateDietryAndLifeStypePreferences() {
		// User login
		funLibrary.login();
		// Validate dietry and preferences option selected successfully and loggin out
		// user
		getPages.getSuperBarPage().clickMyAccount().clickDietryAndPreferences().selectDietryAndPreferencesOption()
				.validateDietryAndPreferencesSuccessMessage().clickLogout();
	}

	@Test(description = "WCS_AUT116- Verify user can select/unselect shopping reminders")
	public void validateShoppingReminderOption() {
		// User login
		funLibrary.login();
		// Validate shopping reminder option selected
		getPages.getSuperBarPage().clickMyAccount().clickShoppingReminders().selectRemindMeOption().closeSuperBar();
		// Validate shopping reminder option unselected and logging out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingReminders().unSelectRemindMeOption().clickLogout();
	}

	@Test(description = "WCS_AUT117- Verify Forgotten Something popup is displayed if shopping reminder is on ")
	public void validateForgottenSomthingPopupIsDispalyed() {
		double mov_value = 50;
		// User login
		funLibrary.login();
		// Navigate shopping reminder and selected the option
		getPages.getSuperBarPage().clickMyAccount().clickShoppingReminders().selectRemindMeOption().closeSuperBar();
		// Adding product to trolley and process to checkout
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton();
		// Validate the forgotten something popup
		getPages.getHomePage().validateFogottenPopupIsDisplayed().clickContinueCheckout();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT118- Verify Forgotten Something popup is not displayed if shopping reminder is off")
	public void validateForgottenSomthingPopupNotDispalyed() {
		double mov_value = 50;
		// User login
		funLibrary.login();
		// Navigate shopping reminder and selected the option
		getPages.getSuperBarPage().clickMyAccount().clickShoppingReminders().unSelectRemindMeOption().closeSuperBar();
		// Adding product to trolley and process to checkout
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton();
		// Validate the forgotten something popup
		getPages.getHomePage().validateForgottenPopupIsNotDisplayed();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT119- Verify user can save card while placing order and verify it in my account page")
	public void validateSavedCardDetails() {
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
		// Adding product to trolley, process to Almost done page
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().checkRememberCheckbox().clickContinueButton();
		// Clicking keep shopping link
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Validate saved card details
		getPages.getSuperBarPage().clickMyAccount().validateSavedCardDetails().clickLogout();
	}

	@Test(description = "WCS_AUT120- Verify user can delete saved card in my account page")
	public void validateDeleteSavedCard() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		// Check and add if card is not already saved
		if (!(getPages.getSuperBarPage().clickMyAccount().isCardDetailSaved())) {

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
					.clickProceedToCheckOutButton().TrolleyHandler();
			// checkoutFlowHandler();
			// Placing order
			getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
					.selectExpiryYear().enterCVV().checkRememberCheckbox().clickContinueButton();
			// Submit order
			getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
			getPages.getSuperBarPage().clickMyAccount();
		}
		// delete saved card
		getPages.myAccountPage().clickCardDetailsDeleteButton().clickCardDetailsConfirmDeleteButton()
				.validateDeleteConfirmationMessage().clickLogout();
	}

	@Test(description = "WCS_AUT121- Verify user is able to save Mycoles number successfully.", enabled = false)
	public void validateMyColesNumberSavedSuccessfully() {
		String colesNumber = "2772150719306";
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Adding coles number,validating success message and logging out
		getPages.getSuperBarPage().clickMyAccount().clickColesEmployee().saveColesNumber(colesNumber)
				.validateSuccessMessage().clickOk().validateUpdatedMessage().clickLogout();
	}

	@Test(description = "WCS_AUT122- Verify user is able to Change Mycoles number.", enabled = false)
	public void validateMyColesNumberUpdatedSuccessfully() {
		String colesNumber = "2772150719306";
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Adding coles number,validating success message and logging out
		getPages.getSuperBarPage().clickMyAccount().clickColesEmployee().saveColesNumber(colesNumber)
				.validateSuccessMessage().clickOk().clickColesEmployee().saveColesNumber(colesNumber)
				.validateSuccessMessage().clickOk();
	}

	@Test(description = "WCS_AUT123- Verify user is not able to save an invalid Mycoles number.", enabled = false)
	public void validateNotAbleToSavedInvalidMyColesNumber() {
		String colesNumber = "1112150719306";
		// Register new user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on continue button and skipping the flybys number
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Adding coles number,validating error message and logging out
		getPages.getSuperBarPage().clickMyAccount().clickColesEmployee().saveColesNumber(colesNumber)
				.validateErrorMessage().clickBackToMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT124-Verify user is able to save multiple valid flybuys number")
	public void validateSaveMultipleValidFlybuys() {
		// Logged-in with registered user
		funLibrary.login();
		// Save multiple flybuys number and validate success message
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys().validateFlybuysLinkText().clickLinkFlybuys()
				.validateAllFlybuysFieldsText().enterFlybuysNumber("9420", "0109", "5217").ClickLinkFlybuysButton()
				.validateFlybuysPasswordPageText().clickContinueWithoutImport().validateFlybuysSuccessMsg()
				.validateLinkedFlybuysNum("EndsWith_5217").deleteLinkedFlybuysNum().validateDeleteSuccessMsg()
				.clickLinkFlybuys().enterFlybuysNumber("9476", "6727", "2216").ClickLinkFlybuysButton()
				.validateFlybuysPasswordPageText().clickContinueWithoutImport().validateFlybuysSuccessMsg()
				.validateLinkedFlybuysNum("EndsWith_2216").deleteLinkedFlybuysNum().validateDeleteSuccessMsg()
				.clickLogout();
	}

	@Test(description = "WCS_AUT125-Verify user is not able to save invalid flybuys number")
	public void validateSaveInValidFlybuys() {
		// Logged-in with registered user
		funLibrary.login();
		// Trying to save invalid flybuys number and validate invalid message
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys().validateFlybuysLinkText().clickLinkFlybuys()
				.validateAllFlybuysFieldsText().enterFlybuysNumber("1111", "0000", "2222").ClickLinkFlybuysButton()
				.validateFlybuysInvalidMsg().clickSkipStep().clickLogout();
	}

	@Test(description = "WCS_AUT128- Verify user can delete product(s) using My Shopping list")
	public void validateProductDeletedFromList() {
		String myListName = "MyFirstList";
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clearMyListIfExist().createList(myListName)
				.validateListSuccessMsg().goToMyLists().clickShopNow();
		// Adding SKU's to list
		getPages.getHomePage().add_SKUs_To_List(funLibrary.search_1);
		// Getting product name and product brand
		String productBrand = getPages.getHomePage().getProductBrand();
		String productName = getPages.getHomePage().getProductName();
		// Clear search result
		getPages.getHomePage().clickClearSearch();
		// Navigate to edit list screen and validate that product deleted from list
		// successfully
		getPages.getSuperBarPage().clickMyAccount().clickMyShoppingList().validateAddedProductCount(funLibrary.search_1)
				.clickEdit().validateProdutInList(productBrand, productName).deleteProductFromList()
				.validateProductDeletedFromList().goToMyListFromEditList()
				.validateProductCountAfterDeletedFromlist(funLibrary.search_1).goToMyAccountPage().clickLogout();
	}

	@Test(description = "WCS_AUT129-Verify user can select deli products")
	public void validateDeliProducts() {
		// User login
		funLibrary.login();
		// Validate dietry and preferences option selected successfully and loggin out
		// user
		getPages.getSuperBarPage().clickMyAccount().clickDietryAndPreferences().selectDietryAndPreferencesOption()
				.validateDietryAndPreferencesSuccessMessage().clickLogout();
	}

	@Test(description = "WCS_AUT131- Verify user can add product(s) using My Shopping list")
	public void validateProductAddedToList() {
		String myListName = "MyFirstList";
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clearMyListIfExist().createList(myListName)
				.validateListSuccessMsg().goToMyLists().clickShopNow();
		// Adding SKU's to list
		getPages.getHomePage().add_SKUs_To_List(funLibrary.search_1);
		// Getting product name and product brand
		String productBrand = getPages.getHomePage().getProductBrand();
		String productName = getPages.getHomePage().getProductName();
		// Clear search result
		getPages.getHomePage().clickClearSearch();
		// Navigate to edit list screen and validate the added product brand and name
		getPages.getSuperBarPage().clickMyAccount().clickMyShoppingList().validateAddedProductCount(funLibrary.search_1)
				.clickEdit().validateProdutInList(productBrand, productName).goToMyListFromEditList()
				.goToMyAccountPage().clickLogout();
	}

	@Test(description = "WCS_AUT132-Verify user is not able to add all producst from MyList usng add all button")
	public void validateMyListAddAllButton() {
		// Logged-in with registered user
		funLibrary.login();
		// Trying to save invalid flybuys number and validate invalid message
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNowButton().validateAddAllButton();
		// Empty the trolley if the trolley total is greater then $0.00
		getPages.getSuperBarPage().clickTrolley().emptyTrolley();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT133- Verify the Top products Sorting functionality for My shopping List")
	public void validateTopProductsSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().validateShoppingListSortedByText("TopProducts").clickShoppingListChangeButton()
				.clickShoppingListSortOption("special").ClickShoppingListSave()
				.validateShoppingListSortedByText("Special").clickShoppingListChangeButton()
				.clickShoppingListSortOption("TopProducts").ClickShoppingListSave()
				.validateShoppingListSortedByText("TopProducts");
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT134- Verify the On Special Sorting functionality for  My shopping List")
	public void validateSpecialSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().clickShoppingListChangeButton().clickShoppingListSortOption("Special")
				.ClickShoppingListSave().validateShoppingListSpecialSort();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT135- Verify the Lowest unit price Sorting functionality for My shopping List")
	public void validateLowestUnitPriceSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().clickShoppingListChangeButton().clickShoppingListSortOption("LowestUnit_Price")
				.ClickShoppingListSave().validateShoppingListUnitPriceSorting();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT136- Verify the Lowest price Sorting functionality for My shopping List")
	public void validateLowestPriceSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().clickShoppingListChangeButton().clickShoppingListSortOption("Lowest_Price")
				.ClickShoppingListSave().validateLowestPriceSorting("Shopping_List");
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT137- Verify the Brand(A-Z) Sorting functionality for My shopping List")
	public void validateBrandSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().clickShoppingListChangeButton().clickShoppingListSortOption("Brand(A-Z)")
				.ClickShoppingListSave().validateBrandSorting("Shopping_List");
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT138- Verify the Product name(A-Z) Sorting functionality for My shopping List")
	public void validateProductNameSorting_ShoppingList() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating top products sorting functionality
		getPages.getHomePage().clickShoppingListChangeButton().clickShoppingListSortOption("ProductName(A-Z)")
				.ClickShoppingListSave().validateProductSorting("Shopping_List");
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT139- Verify the filter functionality for My Shopping List")
	public void validateShoppingListFilter() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating list created successfully and logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickShoppingList().clickShopNow();
		// Validating filter by brand functionality
		getPages.getHomePage().clickShowFilterList().clickFilterByBrandList().verifyFilterSearch()
				.filterPopup_SelectRandomCheckbox().clickShowFilteredProducts().validateListFilterResult();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT153-Verify system removes all products from trolley")
	public void validateAllProductsRemovedFromTrolley() {
		// Logged-in with registered user
		funLibrary.login();
		// Adding and removing the all products from trolley
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1).removeAllItemsFromTrolley();
		// Validate trolley
		getPages.getHomePage().clickTrolley().validateTrolleyAfterRemovingAllProducts();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT155-Verify system donot allow to proceed to checkout without selecting slot")
	public void validateCheckoutWthoutDeliverySlot() {
		// Logged-in with registered user
		funLibrary.login();
		// Adding and removing the all products from trolley
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton();// verifyChooseDeliveryWindowButton();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT156-Verify system donot allow to proceed to checkout without selecting slot")
	public void validateAddProductFromHYFOverlay() {
		double mov_value = 50;
		// Logged-in with registered user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		// Adding product in trolley
		getPages.getHomePage().clickTrolley().emptyTrolley();
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().validateAddHYFProduct();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT157-Verify system allow to proceed to checkout without selecting slot")
	public void validateSkipHYFOverlay() {
		double mov_value = 50;
		// Logged-in with registered user
		funLibrary.login();
		// Only if slot is not already selected
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Sot is already selected");
		}
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		// Adding product in trolley
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().clickHYFContinueToCheckoutButton()
				.ClickCancelAndKeepShopingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT159-Verify user not able to proceed to checkout without selecting slot and trolley count is less than 50$")
	public void validateProceedToCheckoutWithAllConditions() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding product to trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "1").clickTrolley().clickProceedToCheckOutButton();
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getTrolleyAndCheckoutPage().validateSlotIsNotSelected().clickViewDeliveryOptions();
			// Select HD Address
			getPages.getChooseDeliveryTimePage().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		} else {
			getPages.myAccountPage().closeSuperBar();
			getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton();
		}
		// Validate choose delivery options page and minimum order page is displayed
		getPages.getTrolleyAndCheckoutPage().validateMinimumOrderPage().clickGoBackToTrolley()
				.enter_Value_Into_FiveorMore_Dietcontroller("10").clickProceedToCheckOutButton();
		// Clicking keep shopping link
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT160- Verify user is able to view all saved addresses")
	public void validateAllSavedAddress_ChooseDelivery() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating added HD and RD address
		getPages.getSuperBarPage().clickChooseDeliverTime().validateAddressTitle().validateAddress("HD1")
				.validateAddress("RD1");
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT161-Verify that user is able to select address from the list")
	public void validateAddressSelected_ChooseDelivery() {
		// Logged-in with registered user
		funLibrary.login();
		// Validating HD address at DSS page
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstHDAddressLink().validateAddress_DSS("HD")
				.ClickCancelAndKeepShopingLink();
		// Getting RD provider address and could be used in future if need to validate
		// RD address at DSS page
		String rdAddress = getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink()
				.getRDProviderAddress();
		// Validating DSS page title and RD provider address
		getPages.getChooseDeliveryTimePage().selectFirstRDProviderAddress().validateDSSTitle()
				.ClickCancelAndKeepShopingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT162- Verify that user is able to add new address.")
	public void validateAddNewAddress_ChooseDelivery() {
		String dIMessage = "Unattended delivery instruction message";
		// Logged-in with registered user
		funLibrary.login();
		// Localize to 'Rostrevor SA 5073 HD address' and Delete other address if
		// already present
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().closeSuperBar();
		// Add new address in choose delivery time and validate saved address in DSS
		// page
		String reg_nickname = getPages.myAccountPage().getNickName();
		getPages.getSuperBarPage().clickChooseDeliverTime().clickAddAnAddress().enterNewDeliveryDetails(reg_nickname,
				dIMessage);
		getPages.getDeliverySlotPage().validateSavedHDAddress(reg_nickname).ClickCancelAndKeepShopingLink();
		funLibrary.LocalizeToHD("NT 0820");
		// Delete saved address in my account
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT195- Verify delivery instruction should not  available for HD signed delivery.")
	public void validateDelInstructionPageNotDisplayedForSignedDelivery() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true, false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Validate delivery instruction page is not displayed for SD
		getPages.getTrolleyAndCheckoutPage().validateDeliveryInstructionPageNotDisplayed();
		getPages.getAlmostDonePage().clickKeepShopping();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT196- Verify user is able to add delivery instruction while adding new address, "
			+ "WCS_AUT197- Verify user is able to edit delivery instruction from Almost done page, "
			+ "WCS_AUT198- Verify user is able to remove delivery instruction from Almost done page."
			+ "WCS_CE3892- Validate that clicking on Add Instruction on Confirm your Order page navigate user to the Signed Delivery Instructions")
	public void validateDeliveryInstructionAddEditRemoved() {
		double mov_value = 50;
		String dIMessage = "Delivery Instruction Test";
		String UpdatedDIMessage = "Updated Delivery Instruction Test";
		// Logged-in with registered user
		funLibrary.login();

		// Only if slot is already selected
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickAddress("HD");
			getPages.getDeliverySlotPage().ClickChooseLocation().clickClickAndCollectLocation()
					.clickChooseCollectionTime().ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// Added products to trolley if not products not added or added less than 50 $
		// amount
		double trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		if (trolley_Total_Cost <= mov_value) {
			// Adding product to trolley
			getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value);
		}
		// Localize to 'Rostrevor SA 5073 HD address' and Delete other address if
		// already present
		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickMyAccount().clickMyAddress().deleteAllAddress().closeSuperBar();
		// Add new address in choose delivery time and validate saved address in DSS
		// page
		String reg_nickname = getPages.myAccountPage().getNickName();
		getPages.getSuperBarPage().clickChooseDeliverTime().clickAddAnAddress().enterNewDeliveryDetails(reg_nickname,
				dIMessage);
		getPages.getDeliverySlotPage().ClickCancelAndKeepShopingLink();
		// Selecting slot for second added address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickSecondHDAddressLink().HDSlotSelector("signed", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Proceed to checkout
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		// checkoutFlowHandler();
		// Validate added delivery instruction
		getPages.getAlmostDonePage().validateDeliveryInstructionADPage(dIMessage).clickChangeDeliveryInstruction();
		// WCS_CE3892 - Validating delivery instruction screen heading & Sub heading for
		// signed Delivery Type
		getPages.getTrolleyAndCheckoutPage().validateSignedDelHeadings();
		// WCS_AUT197 - Validating delivery instruction updated
		getPages.getTrolleyAndCheckoutPage().enterDeliveryInstruction(UpdatedDIMessage);
		getPages.getAlmostDonePage().validateDeliveryInstructionADPage(UpdatedDIMessage);
		// WCS_AUT198 - Validating delivery instruction removed
		getPages.getAlmostDonePage().clickChangeDeliveryInstruction();
		getPages.getTrolleyAndCheckoutPage().removeDeliveryInstruction().saveDeliveryInstruction();
		// Validate DI shouldn't display in almost done page
		getPages.getAlmostDonePage().validateRemovedDeliveryInstructionADPage().clickKeepShoppingLink();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT199- Delivery Instructions Verify that system allow user to skip instructions")
	public void validateSkipDeliveryInstructions() {
		double mov_value = 50;
		// Logged-in user
		funLibrary.login();

		funLibrary.LocalizeToHD("NT 0820");
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("unattendedonly", true,
				false);
		getPages.myAccountPage().closeSuperBar();
		// Adding product to trolley
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().TrolleyHandler();
		// Validate delivery instruction pop up and save instruction
		getPages.getTrolleyAndCheckoutPage().validateUDInstructionsPage().clickSkipDeliveryInstructions();
		getPages.getAlmostDonePage().clickKeepShoppingLink();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT216- Validate user is able to change Flybuy from superbar")
	public void validateChangeFlybuyInSuperbar() {
		// Logged-in user
		funLibrary.login();
		// Link flybuys number
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys().validateFlybuysLinkText().clickLinkFlybuys()
				.validateAllFlybuysFieldsText().enterFlybuysNumber("9420", "0109", "5217").ClickLinkFlybuysButton()
				.validateFlybuysPasswordPageText().clickContinueWithoutImport().validateFlybuysSuccessMsg()
				.validateLinkedFlybuysNum("EndsWith_5217");
		// Unlink flybuy
		getPages.myAccountPage().deleteLinkedFlybuysNum().validateDeleteSuccessMsg();
		// link another flybuy
		getPages.myAccountPage().clickLinkFlybuys().validateAllFlybuysFieldsText()
				.enterFlybuysNumber("9450", "9890", "5610").ClickLinkFlybuysButton().validateFlybuysPasswordPageText()
				.clickContinueWithoutImport().validateFlybuysSuccessMsg().validateLinkedFlybuysNum("EndsWith_5610")
				.deleteLinkedFlybuysNum().validateDeleteSuccessMsg().clickLogout();
	}

	@Test(description = "WCS_AUT217- Validate link invalid Flybuy vaidation from superbar")
	public void validateInvalidFlybuyInSuperbar() {
		// Logged-in user
		funLibrary.login();
		// Link invalid flybuys number
		getPages.getSuperBarPage().clickMyAccount().unLinkFybuys().validateFlybuysLinkText().clickLinkFlybuys()
				.validateAllFlybuysFieldsText().enterFlybuysNumber("9420", "0109", "4555").ClickLinkFlybuysButton()
				.validateFlybuysInvalidMsg().clickSkipStep().closeSuperBar();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}