package com.swiftshop.pages;

import org.openqa.selenium.By;

import com.swiftshop.main.FunLibrary;

public class GuestUserHomePage extends FunLibrary {

	public GuestUserHomePage homePageCheck() {
		try {
			String title = driver.getTitle();
			if (title.contentEquals("Coles Online")) {
				testLog.info("Title is Coles Online");
			} else
				testLog.error("Title is incorrect");
			isElementPresent(OR_OR.getProperty("Coles_Logo"), "Coles_Logo");
			isElementPresent(OR_OR.getProperty("Home_icon"), "Home_icon");
			verify_xpath_text(OR_OR.getProperty("Home_SearchField_InsideText_Path"),
					OR_OR.getProperty("Home_SearchField_InsideText"));
			verify_xpath_text(OR_OR.getProperty("Everything"), "Everything");
			verify_xpath_text(OR_OR.getProperty("Bought-before"), "Bought Before");
			verify_xpath_text(OR_OR.getProperty("Special"), "Specials");
			verify_xpath_text(OR_OR.getProperty("More"), "More");
			Click_Button_Xpath(OR_OR.getProperty("More"), "More");
			verify_xpath_text(OR_OR.getProperty("More_Open_New_Tap"), "Open a new tab");
			verify_xpath_text(OR_OR.getProperty("More_Shop_Product_Selection"), "Shop Product Selections");
			isElementPresent(OR_OR.getProperty("Search_List_Item"), "Search_List_Item");
			verify_xpath_text(OR_OR.getProperty("Trolley_doller"), "$0.00");
			verify_xpath_text(OR_OR.getProperty("Trolley_Text"), "Trolley and checkout");
			verify_xpath_text(OR_OR.getProperty("Delivery_Time"), "Choose a delivery time");
			verify_xpath_text(OR_OR.getProperty("Login/Signup"), "Log in / Signup");
			verify_xpath_text(OR_OR.getProperty("Help/Support"), "Help / Support");
			assertCheck("homePageCheck", "Opps!! Somethign missing at home page");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this;
	}

	public GuestUserHomePage searchItems() {
		try {
			Sendkey_id(OR_OR.getProperty("Search_Input_Box"), search_1, "Search_Input_Field");
			Click_Button_Xpath(OR_OR.getProperty("Search_Button"), "Search_Button");
			wait(4000);
			verify_xpath_text(OR_OR.getProperty("Results_For"), "Results for " + "\'" + search_1 + "\'");

			int tabcounter = get_Tab_Counter(OR_OR.getProperty("Everything_Tab_Counter"), "Everything_Tab_Counter");
			testLog.info("Total count of searched product present in Everything is :" + tabcounter);
			String Search_Tile1 = driver.findElement(By.xpath(OR_OR.getProperty("Search_Tile1"))).getText();
			if (Search_Tile1.contains("milk")) {
				testLog.info("Searched product is milk");
			}
			Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button");
			Sendkey_id(OR_OR.getProperty("Search_Input_Box"), search_2, "Search_Input_Field");
			Click_Button_Xpath(OR_OR.getProperty("Search_Button"), "Search_Button");
			wait(4000);
			verify_xpath_text(OR_OR.getProperty("Results_For"), "Results for " + "\'" + search_2 + "\'");
			Click_Button_Xpath(OR_OR.getProperty("Search_Tile1"), "Search_Tile1");
			verify_xpath_text(OR_OR.getProperty("Product_Page_sku"), "Product_Page_sku");
			Click_Button_Xpath(OR_OR.getProperty("Back_To_Products"), "Back_To_Products");
			Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this;
	}
}
