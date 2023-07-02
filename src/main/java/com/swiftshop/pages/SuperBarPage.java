package com.swiftshop.pages;

import com.swiftshop.main.FunLibrary;

public class SuperBarPage  extends FunLibrary {
	
//	public SuperBarPage() {
//		getPages = new GetPages();
//	}
	
	public LoginPage clickLoginSignup() {
		
		driver.manage().deleteAllCookies();
		ExplicitWait(OR_OR.getProperty("SB_Login_Signup_Option"), "SB_Login_Signup_Option", 5);
		verify_xpath_text(OR_OR.getProperty("SB_Login_Signup_Option"),
				OR_OR.getProperty("SB_Login_Signup_Option_text"));
		Click_Button_Xpath(OR_OR.getProperty("SB_Login_Signup_Option"), "SB_Login_Signup_Option");
		return new LoginPage();
	}

	public MyAccountPage clickMyAccount() {
		ExplicitWait(OR_OR.getProperty("Account_Name"), "Account_Name", 10);
		Click_Button_Xpath(OR_OR.getProperty("Account_Name"), "Account_Name");
		return new MyAccountPage();
	}

	public ChooseDeliveryTimePage clickChooseDeliverTime() {
		wait(6000);
		Click_Button_Xpath(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time");
		return new ChooseDeliveryTimePage();
	}

	public TrolleyAndCheckoutPage clickTrolley() {
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		return new TrolleyAndCheckoutPage();
	}

	public HelpAndSupportPage clickHelpSupport() {
		ExplicitWait(OR_OR.getProperty("SB_Help_Support_Option"), "SB_Help_Support_Option", 5);
		Click_Button_Xpath(OR_OR.getProperty("SB_Help_Support_Option"), "SB_Help_Support_Option");
		wait(5000);
		return new HelpAndSupportPage();
	}

	public OrdersPage clickOrders() {
		ExplicitWait(OR_OR.getProperty("AfterLogin_Orders"), "AfterLogin_Orders", 50);
		Click_Button_Xpath(OR_OR.getProperty("AfterLogin_Orders"), "AfterLogin_Orders");
		return new OrdersPage();
	}
	
	public SuperBarPage skipFlybuys() {
		ExplicitWait(OR_OR.getProperty("Fly_buy_skip"), "Fly_buy_skip", 10);
		Click_Button_Xpath(OR_OR.getProperty("Fly_buy_skip"), "Fly_buy_skip");			
		return this; 
	}

}
