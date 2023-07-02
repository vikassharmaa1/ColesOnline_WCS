package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class LoginTests extends Base_Class_Browser {
	
	@Test(description = "WCS_AUT036 - Verify user NOT able to login with invalid user credentials")
	public void validateUserLoginWithInvalidCredentials() throws Exception {

		String invalidUsername = "xxxxyyyy@dummy.com";
		String invalidPwd = "xxxxyyyy";
		// Validating error message, when try to logged-in with in-valid credentials
		getPages.getSuperBarPage().clickLoginSignup().enterUserName(invalidUsername).enterPassword(invalidPwd)
				.clickLoginBtn().validateLoginErrorMsg().clickTryAgainBtn().enterUserName(funLibrary.username)
				.enterPassword(invalidPwd).clickLoginBtn().validateLoginErrorMsg().clickTryAgainBtn()
				.enterUserName(invalidUsername).enterPassword(funLibrary.password).clickLoginBtn()
				.validateLoginErrorMsg();

	}

	@Test(description = "WCS_AUT037 - Verify user logged-in successfully with valid user")
	public void validateUserLoginWithValidCredentials() throws Exception {
		// Validating user logged-in
		getPages.getSuperBarPage().clickLoginSignup().enterUserName(funLibrary.username)
				.enterPassword(funLibrary.password).clickLogin().verifyWelcomeBackPopupMsg().clickWelcomeBackOKButton();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT038- Verify popup window when user reset password from login page")
	public void validateForgetPassword() throws Exception {
		// Validating user successfully able to reset his/her password
		getPages.getSuperBarPage().clickLoginSignup().clickForgotPasswordLink().clickForgetEmailLink()
				.validateCustomerSupportDetails().validateCustomerSupportCellNumber().clickBackToForgetPassword()
				.enterEmailID(funLibrary.username).clickResetPasswordBtn().validateResetPasswordConfimationMsg().clickOKBtn();
	}
}
