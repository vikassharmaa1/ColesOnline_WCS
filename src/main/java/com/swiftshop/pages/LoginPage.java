package com.swiftshop.pages;

import org.openqa.selenium.By;

import com.swiftshop.main.FunLibrary;
import com.swiftshop.utilities.DatabaseUtilities;

public class LoginPage extends FunLibrary {

	public LoginPage enterUserName(String username) {
		//ExplicitWait(OR_OR.getProperty("Email_ID"), "Email_ID", 10);
		wait(5000);
		Clear_Text(OR_OR.getProperty("Email_ID"), "Email_ID");
		Sendkey_xpath(OR_OR.getProperty("Email_ID"), username, "Email_ID");
		return this;
	}

	public LoginPage enterPassword(String password) {
		//ExplicitWait(OR_OR.getProperty("LoginForm_Header"), "LoginForm_Header", 10);
		wait(5000);
		verify_xpath_text(OR_OR.getProperty("LoginForm_Header"), OR_OR.getProperty("LoginForm_Header_Text"));
		Clear_Text(OR_OR.getProperty("Password"), "Password");
		Sendkey_xpath(OR_OR.getProperty("Password"), password, "Password");
		return this;
	}

	public HomePage clickLogin() throws Exception {
		Click_Button_Xpath(OR_OR.getProperty("Login_Button"), "Login_Button");
		wait(12000);
		boolean flag = isElementPresent(OR_OR.getProperty("Login_Page_Verify_Button"), "Login_Page_Verify_Button");
		if (flag == true) {
			DatabaseUtilities dbConn = new DatabaseUtilities();
			int i = 0;
			String otp = "";
			do {
				otp = dbConn.getOTP(username);
				++i;
			} while (i < 4 && otp.equals(""));

			testLog.info("Auto generated OTP is: " + otp);
			Sendkey_xpath(OR_OR.getProperty("Login_Page_Verification_Code_TextBox"), otp,
					"Login_Page_Verification_Code_TextBox");
			Click_Button_Xpath(OR_OR.getProperty("Login_Page_Verify_Button"), "Login_Page_Verify_Button");
			dbConn.closeDBConnection();
			wait(1000);

		} else {
			testLog.info("Great!! User is already authenticated");
		}
		return new HomePage();
	}

	public RegistrationPage clickNewToColesLink() {
		ExplicitWait(OR_OR.getProperty("New_to_coles_online_link"), "New_to_coles_online_link", 2);
		Click_Button_Xpath(OR_OR.getProperty("New_to_coles_online_link"), "New_to_coles_online_link");
		ExplicitWait(OR_OR.getProperty("Registration_First_Name_Text"), "Registration_First_Name_Text", 30);
		return new RegistrationPage();
	}

	public LoginPage clickLoginLink() {
		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Login_Link"), "Choose_Delivery_Login_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Login_Link"), "Choose_Delivery_Login_Link");
		return this;
	}

	public RegistrationPage clickSignupLink() {
		ExplicitWait(OR_OR.getProperty("Choose_Delivery_Signup_Link"), "Choose_Delivery_Signup_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Choose_Delivery_Signup_Link"), "Choose_Delivery_Signup_Link");
		return new RegistrationPage();
	}

	public LoginPage clickForgotPasswordLink() {
		ExplicitWait(OR_OR.getProperty("Email_ID"), "Email_ID", 10);
		Click_Button_Xpath(OR_OR.getProperty("Login_Forgot_Password_Link"), "Login_Forgot_Password_Link");
		wait(5000);
		return this;
	}

	public LoginPage enterEmailID(String username) {
		//ExplicitWait(OR_OR.getProperty("Forgot_Password_Email"), "Forgot_Password_Email", 10);
		wait(5000);
		Clear_Text(OR_OR.getProperty("Forgot_Password_Email"), "Forgot_Password_Email");
		Sendkey_xpath(OR_OR.getProperty("Forgot_Password_Email"), username, "Forgot_Password_Email");
		return this;
	}

	public LoginPage clickResetPasswordBtn() {
		Click_Button_Xpath(OR_OR.getProperty("Reset_My_Password_Btn"), "Reset_My_Password_Btn");
		wait(20000);
		return this;
	}

	public LoginPage clickLoginBtn() {
		Click_Button_Xpath(OR_OR.getProperty("Login_Button"), "Login_Button");
		return this;
	}

	public LoginPage validateLoginErrorMsg() {
		ExplicitWait(OR_OR.getProperty("Log_In_Again_Button"), "Log_In_Again_Button", 5);
		verify_xpath_text(OR_OR.getProperty("Login_Failed_Title"), OR_OR.getProperty("Login_Failed_Title_Msg"));
		return this;
	}

	public LoginPage clickTryAgainBtn() {
		ExplicitWait(OR_OR.getProperty("Login_Try_Again_Btn"), "Login_Try_Again_Btn", 2);
		Click_Button_Xpath(OR_OR.getProperty("Login_Try_Again_Btn"), "Login_Try_Again_Btn");
		return this;
	}

	public LoginPage validateUserNameText() {
		String username = get_xpath_text(OR_OR.getProperty("AfterLogin_UserName"), "AfterLogin_UserName");
		verify_xpath_text(OR_OR.getProperty("AfterLogin_UserName"), username);
		return this;
	}

	public LoginPage validateOrdersText() {
		verify_xpath_text(OR_OR.getProperty("AfterLogin_Orders"), "Orders");
		return this;
	}

	public LoginPage validateResetPasswordConfimationMsg() {
		ExplicitWait(OR_OR.getProperty("Reset_Password_Confirmation_Popup"), "Reset_Password_Confirmation_Popup", 5);
		verify_xpath_text(OR_OR.getProperty("Reset_Password_Confirmation_Popup"),
				OR_OR.getProperty("Reset_Password_Confirmation_Popup_Msg"));
		String actualEmailSendMsg = driver.findElement(By.xpath(OR_OR.getProperty("Reset_Password_Send_Email")))
				.getText().trim();
		String expectedEmailSendMsg = OR_OR.getProperty("Reset_Password_Send_Email_Msg");
		if (actualEmailSendMsg.contains(expectedEmailSendMsg)) {
			testLog.info("String :" + actualEmailSendMsg + " - contains :" + expectedEmailSendMsg);
		} else {
			testLog.info("String :" + actualEmailSendMsg + " - NOT contains :" + expectedEmailSendMsg);
			assertCheck("validateResetPasswordConfimationMsg",
					"String :" + actualEmailSendMsg + " - NOT contains :" + expectedEmailSendMsg);
		}
		return this;
	}

	public LoginPage clickOKBtn() {
		ExplicitWait(OR_OR.getProperty("Popup_Ok_Btn"), "Popup_Ok_Btn", 2);
		Click_Button_Xpath(OR_OR.getProperty("Popup_Ok_Btn"), "Popup_Ok_Btn");

		return this;
	}

	public LoginPage clickForgetPasswordLink() {
		ExplicitWait(OR_OR.getProperty("Forget_Password_Link"), "Forget_Password_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Forget_Password_Link"), "Forget_Password_Link");
		return this;
	}

	public LoginPage validateCustomerSupportDetails() {
		ExplicitWait(OR_OR.getProperty("Customer_Support_Contact_Details"), "Customer_Support_Contact_Details", 2);
		String actualCustomerSupportDetails = driver
				.findElement(By.xpath(OR_OR.getProperty("Customer_Support_Contact_Details"))).getText().trim();
		String expectedCustomerSupportMsg = OR_OR.getProperty("Customer_Support_Contact_Details_Msg");
		if (actualCustomerSupportDetails.contains(expectedCustomerSupportMsg)) {
			testLog.info("String :" + actualCustomerSupportDetails + " - contains :" + expectedCustomerSupportMsg);
		} else {
			testLog.info("String :" + actualCustomerSupportDetails + " - NOT contains :" + expectedCustomerSupportMsg);
			assertCheck("validateCustomerSupportDetails",
					"String :" + actualCustomerSupportDetails + " - NOT contains " + expectedCustomerSupportMsg);
		}
		return this;
	}

	public LoginPage validateCustomerSupportCellNumber() {
		ExplicitWait(OR_OR.getProperty("Customer_Support_Contact_Details"), "Customer_Support_Contact_Details", 1);
		String actualCustomerSupportDetails = driver
				.findElement(By.xpath(OR_OR.getProperty("Customer_Support_Contact_Details"))).getText().trim();
		String expectedCustomerSupportCellNumber = OR_OR.getProperty("Customer_Support_Contact_Details_Number")
				.toString();
		if (actualCustomerSupportDetails.contains(expectedCustomerSupportCellNumber)) {
			testLog.info(
					"String :" + actualCustomerSupportDetails + " - contains :" + expectedCustomerSupportCellNumber);
		} else {
			testLog.info("String :" + actualCustomerSupportDetails + " - NOT contains :"
					+ expectedCustomerSupportCellNumber);
			assertCheck("validateCustomerSupportCellNumber", "String :" + actualCustomerSupportDetails
					+ " - NOT contains :" + expectedCustomerSupportCellNumber);
		}
		return this;
	}

	public LoginPage clickForgetEmailLink() {
		ExplicitWait(OR_OR.getProperty("Forget_Email_Link"), "Forget_Email_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Forget_Email_Link"), "Forget_Email_Link");
		return this;
	}

	public LoginPage clickBackToForgetPassword() {
		ExplicitWait(OR_OR.getProperty("Back_To_Forget_Password_Link"), "Back_To_Forget_Password_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Back_To_Forget_Password_Link"), "Back_To_Forget_Password_Link");
		return this;
	}

	public LoginPage clickWelcomePopupCloseButton() {
		ExplicitWait(OR_OR.getProperty("AfterLogin_Popup_Header"), "AfterLogin_Popup_Header", 75);
		verify_xpath_text(OR_OR.getProperty("AfterLogin_Popup_Header"), "Welcome Back!");
		String username = get_xpath_text(OR_OR.getProperty("AfterLogin_UserName"), "AfterLogin_UserName");
		verify_xpath_text(OR_OR.getProperty("AfterLogin_Popup_Msg"), "You have been logged in as " + username);
		Click_Button_Xpath(OR_OR.getProperty("Close_Welcome_Popup"), "Close_Welcome_Popup");
		return this;
	}
}
