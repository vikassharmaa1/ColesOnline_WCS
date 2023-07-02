package com.swiftshop.pages;

import com.swiftshop.main.FunLibrary;

public class HelpAndSupportPage extends FunLibrary {

//	public HelpAndSupportPage() {
//		
//		getPages = new GetPages();
//	}

	public HelpAndSupportPage closeSuperbar() {
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
	}

	public HelpAndSupportPage validatePageTitle() {
		switch_Frame("Help_support_iframe");
		ExplicitWait(OR_OR.getProperty("Help_Support_Title"), "Help_Support_Title", 5);
		String expectedTitle = OR_OR.getProperty("Help_Support_Title_Text");
		String actualTitle = get_xpath_text(OR_OR.getProperty("Help_Support_Title"), "Help_Support_Title");

		if (actualTitle.contains(expectedTitle)) {
			testLog.info("String :" + actualTitle + ", contains IN-> " + expectedTitle);
		} else {
			testLog.error("String :" + actualTitle + ", NOT contains IN-> " + expectedTitle);
			assertCheck("validatePageTitle", "String :" + actualTitle + ", NOT contains IN-> " + expectedTitle);
		}
		switch_To_DefaultFrame();
		return this;
	}

	public HelpAndSupportPage validateEmailText() {
		switch_Frame("Help_support_iframe");
		// ExplicitWait(OR_OR.getProperty("Email_Title"), "Email_Title", 5);
		wait(4000);
		String expectedTitle = OR_OR.getProperty("Email_Title_Text");
		String actualTitle = get_xpath_text(OR_OR.getProperty("Email_Title"), "Email_Title");
		if (actualTitle.contains(expectedTitle)) {
			testLog.info("String :" + actualTitle + ", contains IN-> " + expectedTitle);
		} else {
			testLog.error("String :" + actualTitle + ", NOT contains IN-> " + expectedTitle);
			assertCheck("validateEmailText", "String :" + actualTitle + ", NOT contains IN-> " + expectedTitle);
		}
		switch_To_DefaultFrame();
		return this;
	}

	public HelpAndSupportPage validateCusomerDetails() {
		switch_Frame("Help_support_iframe");
		// ExplicitWait(OR_OR.getProperty("Customer_Care_Text"), "Customer_Care_Text",
		// 5);
		wait(4000);
		String expectedTextNumber = OR_OR.getProperty("Customer_Care_Text_Number");
		String actualTextNumber = get_xpath_text(OR_OR.getProperty("Customer_Care_Text"), "Customer_Care_Text");
		if (actualTextNumber.contains(expectedTextNumber)) {
			testLog.info("Text number :" + actualTextNumber + "available at Help/support page.");
		} else {
			testLog.error("Opps!! Text number :" + actualTextNumber + "is NOT available at Help/support page.");
			assertCheck("validateCusomerDetails",
					"Text number :" + actualTextNumber + "is NOT available at Help/support page.");
		}
		switch_To_DefaultFrame();
		return this;
	}

}
