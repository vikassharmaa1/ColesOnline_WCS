package com.swiftshop.tests;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class RegistrationTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT031- Register with new user")
	public void validateNewUserRegistration() throws Exception {
		// Registering user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Verifying user registered successfully and clicking on continue button
		getPages.getHomePage().validateRegistrationConfirmationPopup().clickRegistrationPopupContinue_Button()
				.skipFlybuys();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT032- Verify Business user registration", enabled = false)
	public void validateNewBusinessUserRegistration() throws Exception {
		// Entering business user registration details
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().clickBusinessUserCheckbox().enterBusinessName().enterABN()
				.selectIndustry("Environmental").enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("businessUser");
		// Validating user registration success message
		getPages.getRegistrationPage().validateSuccessMsg();
		// Clicking on confirmation window continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT033 - Verify address and phone validation", enabled = false)
	public void validateRegisterationAddressandPhoneNumerFieldValidation() throws InterruptedException, AWTException {
		// Entering user details
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on confirmation window continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Validating error message for invalid data
		getPages.getSuperBarPage().clickMyAccount().clickMyAddressesLink().clickAddAddressButton().enterInvalidAddress()
				.clickAddressContinueButton().validateErrMsg();

	}

	@Test(description = "WCS_AUT034 - Verify user can select valid addres after enter valid address in address form", enabled = false)
	public void validateRegisterationwithValidAddress() throws InterruptedException, AWTException {
		// Registering user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Verifying user registered successfully and clicking on continue
		// button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Adding address
		getPages.getSuperBarPage().clickMyAccount().clickMyAddressesLink().clickAddAddressButton()
				.enterAddressDetails();
		// Validating address
		getPages.getRegistrationPage().validateAddressDetails();
		// Closing SuperBar
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT035 - Verify user not able to select addres after enter invalid address in address form", enabled = false)
	public void validateRegisterationwithInvalidAddress() throws InterruptedException, AWTException {
		// Registering user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Clicking on confirmation window continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Validating through adding invalid address
		getPages.getSuperBarPage().clickMyAccount().clickMyAddressesLink().clickAddAddressButton().enterInvalidAddress()
				.clickAddressContinueButton().validateNoAddFoundText();
	}

	@Test(description = "WCS_AUT039 - Verify password validation in registeration page", enabled = false)
	public void validatePasswordField() {
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().validatePasswordField();
	}

	@Test(description = "WCS_AUT040- Verify user is able to register with RD address", enabled = false)
	public void validateNewUserRDRegistration() throws Exception {
		// Registering user
		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterRandomEmailID()
				.enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Verifying user registered successfully and clicking on continue button
		getPages.getHomePage().validateRegistrationConfirmationPopup().clickRegistrationPopupContinue_Button()
				.skipFlybuys();
		// Adding address
		getPages.getSuperBarPage().clickMyAccount().clickMyAddressesLink().clickAddAddressButton()
				.enterAddressDetails();
		// Validating address
		getPages.getRegistrationPage().validateAddressDetails();
		// Closing SuperBar
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
