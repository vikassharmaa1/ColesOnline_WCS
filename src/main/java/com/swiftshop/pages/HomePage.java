package com.swiftshop.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import com.swiftshop.main.FunLibrary;

public class HomePage extends FunLibrary {

	public double Trolley_Total_Cost;
	int filtered_productCount;
	String BrandName;

	double updated_Trolley_Total;

	public HomePage clickWelcomeBackOKButton() {
		ExplicitWait(OR_OR.getProperty("AfterLogin_Popup_Header"), "AfterLogin_Popup_Header", 75);
		verify_xpath_text(OR_OR.getProperty("AfterLogin_Popup_Header"), "Welcome Back!");
		Click_Button_Xpath(OR_OR.getProperty("AfterLogin_Popup_StartShopping_Button"),
				"AfterLogin_Popup_StartShopping_Button");
		return this;
	}

	/*
	 * public HomePage validateSuccessMsg() {
	 * ExplicitWait(OR_OR.getProperty("AfterLogin_Popup_Msg"),
	 * "AfterLogin_Popup_Msg", 5);
	 * verify_xpath_text(OR_OR.getProperty("AfterLogin_Popup_Msg"),
	 * OR_OR.getProperty("AfterLogin_Popup_Expected_Msg")); Assert(); return
	 * this; }
	 */

	public HomePage verifyWelcomeBackPopupMsg() {
		ExplicitWait(OR_OR.getProperty("AfterLogin_Popup_Header"), "AfterLogin_Popup_Header", 75);
		String username = get_xpath_text(OR_OR.getProperty("AfterLogin_UserName"), "AfterLogin_UserName");
		verify_xpath_text(OR_OR.getProperty("AfterLogin_Popup_Msg"), "You have been logged in as " + username);
		return this;
	}

	public HomePage validateRegistrationConfirmationPopup() {
		wait(3000);
		ExplicitWait(OR_OR.getProperty("Alldone_User_Confirmation_Msg"), "Alldone_User_Confirmation_Msg", 20);
		verify_xpath_text(OR_OR.getProperty("Alldone_User_Confirmation_Msg"),
				OR_OR.getProperty("Alldone_User_Confirmation_Msg_Text"));
		return this;
	}

	public SuperBarPage clickRegistrationPopupContinue_Button() {
		ExplicitWait(OR_OR.getProperty("Alldone_Confirmation_Popup_Continue_Button"),
				"Alldone_Confirmation_Popup_Continue_Button", 20);
		Click_Button_Xpath(OR_OR.getProperty("Alldone_Confirmation_Popup_Continue_Button"),
				"Alldone_Confirmation_Popup_Continue_Button");
		return new SuperBarPage();
	}

	// Function to add or update the FC for weighted items and verify details
	// reflecting in super bar & DC
	public void wt_add_update_fatcontroller_verifyDC(String index, String addorupdate, String search_sku) {
		testLog.info("function wt_add_update_fatcontroller_verifyDC started");
		boolean bool2, bool3, bool4;
		String strfctext_1, strfctext_2, strfctext_3, strfctext_4, strfcprice_1, strfcprice_2, strfcprice_4, strqty_1,
				strqty_2;
		String strrhstfcprice_1;
		double strdoublefcprice_1, strdoublefcprice_2, updated_strdoubleqty_1, updated_strdoubleqty_2;
		int strintqty_1, strintqty_2;
		double strdoublefcprice_4, strrhstdoublefcprice_1;

		strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController_A")))
				.getAttribute("innerHTML");
		// testLog.info("strfctext_1: "+strfctext_1);

		// Code to check if the product is a weighted Item - If Yes, set the
		// wtflag
		// (String value) to 1-kg, 2-g
		String wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
		String OnlineSizeDesc = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		int wtflag = 0;
		String wt_multiplier_index = null;
		testLog.info("wt_item_text1 value is " + wt_item_text1 + "and OnlineSizeDesc" + OnlineSizeDesc);
		if (wt_item_text1.endsWith("g")) {
			wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
			// testLog.info("1: "+wt_item_text1);
			OnlineSizeDesc = OnlineSizeDesc.substring(8, OnlineSizeDesc.length() - 1);
			// testLog.info("2: "+OnlineSizeDesc);

			if (wt_item_text1.endsWith("k")) {
				wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
				// testLog.info("3: "+wt_item_text1);
				OnlineSizeDesc = OnlineSizeDesc.substring(0, OnlineSizeDesc.length() - 1);
				// testLog.info("4: "+OnlineSizeDesc);
				wtflag = 1;
			} else {
				wtflag = 2;
			}
		}
		testLog.info("value of wtflag is" + wtflag);
		double doub_qty1, doub_qty2;
		int int_qty1, int_qty2, base_qty = 0;

		switch (wtflag) {
		case 1:
			doub_qty1 = Double.parseDouble(wt_item_text1);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);

			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty = (int) doub_temp;
			testLog.info("base_qty: " + base_qty);

			int tempval1 = Integer.parseInt(index);
			double tempval2 = (double) Math.round((doub_qty2 * tempval1) * 100) / 100;
			wt_multiplier_index = Double.toString(tempval2);
			testLog.info("wt_multiplier_index: " + wt_multiplier_index);
			break;
		case 2:
			int_qty1 = Integer.parseInt(wt_item_text1);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty = int_qty1 / int_qty2;
			testLog.info("base_qty: " + base_qty);
			int tempval3 = Integer.parseInt(index);
			int tempval4 = int_qty2 * tempval3;
			wt_multiplier_index = Integer.toString(tempval4);

			break;
		default:
			testLog.error("Element not found: Check the searched SKU is a Weighted Item");
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"Element not found: Check the searched SKU is a Weighted Item");
			break;
		}

		bool2 = strfctext_1.contains("Add");
		testLog.info("bool2: " + bool2);
		if (bool2 == true) {
			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ZeroCount"))).getText();
			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strintqty_1 = 0;
		} else {
			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strintqty_1 = base_qty;
			strdoublefcprice_1 = (double) Math.round((strdoublefcprice_1 / strintqty_1) * 100) / 100;
		}

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		String superbarqty_1, superbarqty_2;
		int superbarintqty_1, superbarintqty_2;

		if (Trolley_Total_Cost == 0.0) {
			superbarintqty_1 = 0;
		} else {
			superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
			superbarintqty_1 = Integer.parseInt(superbarqty_1);
		}

		click_FC_Select_Item(OR_OR.getProperty("Tile1_Dropdown"), "Tile1_Dropdown",
				OR_OR.getProperty("Tile1_Dropdown_SelectN"), "Tile1_Dropdown_SelectN", index, addorupdate, search_sku,
				wt_multiplier_index);
		wait(5000);
		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);

		strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_2 = strfcprice_2.replace(",", "");
		strfcprice_2 = strfcprice_2.substring(strfcprice_2.lastIndexOf("$") + 1);
		if (strfcprice_2.contains(" ")) {
			strfcprice_2 = strfcprice_2.substring(0, strfcprice_2.indexOf(" "));
		}
		strdoublefcprice_2 = Double.parseDouble(strfcprice_2);

		int base_qty2 = 0;

		strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
		strqty_2 = strqty_2.replaceAll("\\s+", "");
		switch (wtflag) {
		case 1:
			testLog.info("Entered case1");
			strqty_2 = strqty_2.substring(0, strqty_2.length() - 2);
			doub_qty1 = Double.parseDouble(strqty_2);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);
			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty2 = (int) doub_temp;
			testLog.info("base_qty2 :" + base_qty2);

			break;
		case 2:
			testLog.info("Entered case2");
			strqty_2 = strqty_2.substring(0, strqty_2.length() - 1);
			int_qty1 = Integer.parseInt(strqty_2);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty2 = int_qty1 / int_qty2;
			testLog.info("base_qty2 :" + base_qty2);
			break;
		default:
			break;
		}

		strintqty_2 = base_qty2;
		updated_strdoubleqty_2 = (double) Math.round(strintqty_2 * strdoublefcprice_1 * 100) / 100;
		int temp_strintqty_1, temp_strintqty_2;
		if (addorupdate.equals("Add")) {
			temp_strintqty_2 = 1;
			temp_strintqty_1 = 0;
		} else {
			temp_strintqty_2 = 0;
			temp_strintqty_1 = 0;
		}

		if (superbarintqty_2 == (superbarintqty_1 + (temp_strintqty_2 - temp_strintqty_1))) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
					+ superbarintqty_1 + " to " + superbarintqty_2);
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1 + " to "
							+ strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
							+ superbarintqty_1 + " to " + superbarintqty_2);
		}

		if (strdoublefcprice_2 == (updated_strdoubleqty_2)) {
			testLog.info("The FC value updated correctly to selected QTY: " + strintqty_2 + " and Price: "
					+ strdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
					+ strdoublefcprice_2);
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
							+ strdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((strintqty_2 - strintqty_1) * strdoublefcprice_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);
		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);
		if (updated_Trolley_Total == Trolley_Total_Cost) {
			testLog.info("Updated Trolley Total after adding item with cost " + strdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
		}

		/*
		 * *********************************************************************
		 * ******** **
		 */

		String pName = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_ProductName"))).getText();
		String onlineSD = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		String space = " ";
		String pName1 = pName.concat(space).concat(onlineSD);
		testLog.info("Product name extracted from LHS product tile is :" + pName1);

		strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_4 = strfcprice_4.replace(",", "");
		strfcprice_4 = strfcprice_4.substring(strfcprice_4.lastIndexOf("$") + 1);
		if (strfcprice_4.contains(" ")) {
			strfcprice_4 = strfcprice_4.substring(0, strfcprice_4.indexOf(" "));
		}
		strdoublefcprice_4 = Double.parseDouble(strfcprice_4);

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(2000);
		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);

		String rHSTpName;
		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_Only1element")))
					.getText();
		} else {
			testLog.info("Multiple elements are prenset in the rhst");
			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_MoreElements")))
					.getText();
		}

		if (rHSTpName.contains(pName1)) {
			testLog.info("The product name in FatController & the 1st item displayed in RHST are same as: " + pName1);

		} else {
			testLog.error("The product added last is not appearing at the first position in RHST");
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"The product added last is not appearing at the first position in RHST");
		}

		String rHSTFC;
		if (listchanges.size() == 2) {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price"))).getText();
		} else {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price"))).getText();
		}
		wait(3000);
		strrhstfcprice_1 = rHSTFC.substring(rHSTFC.lastIndexOf("$") + 1);
		strrhstfcprice_1 = strrhstfcprice_1.replace(",", "");
		if (strrhstfcprice_1.contains(" ")) {
			strrhstfcprice_1 = strrhstfcprice_1.substring(0, strrhstfcprice_1.indexOf(" "));
		}
		strrhstdoublefcprice_1 = Double.parseDouble(strrhstfcprice_1);

		if (strdoublefcprice_4 == strrhstdoublefcprice_1) {
			testLog.info("The cost added for FC and RHST DC - both are same with value: " + strrhstdoublefcprice_1);

		} else {
			testLog.error("The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
					+ " and DC: " + strrhstdoublefcprice_1);
			assertCheck("wt_add_update_fatcontroller_verifyDC",
					"The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
							+ " and DC: " + strrhstdoublefcprice_1);
		}
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
	}

	// Function to add or update the DC for weighted items and verify details
	// relecting in superbar & FC
	public void wt_update_dietcontroller_verifyFC(String index, String search_sku) {
		String superbarqty_1, superbarqty_2, rhststrfctext_1, rhststrfcprice_1, rhststrqty_1;
		int superbarintqty_1, superbarintqty_2, rhststrintqty_1, lhsstrintqty_1, rhststrintqty_2;
		double rhststrdoublefcprice_1, rhststrdoublefcpriceqty_1, strlhsdoublefcprice_1, rhststrdoublefcprice_2,
				updated_rhststrdoubleqty_2;
		String rhststrfctext_2, rhststrfcprice_2, rhststrqty_2, lhsFC, strlhsfcprice_1, lhsstrqty_1;
		String dd_newElement1, dd_newElement2;
		String wt_item_text1;

		if (isElementPresent(OR_OR.getProperty("RHST_SuperBar_Open"), "RHST_SuperBar_Open") != true) {
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		}
		wait(3000);
		superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
		superbarintqty_1 = Integer.parseInt(superbarqty_1);

		// Code to check if the product is a weighted Item - If Yes, set the
		// wtflag
		// (String value) to 1-kg, 2-g
		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);
		if (listchanges.size() == 2) {
			wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_qty_ValidCount_OneItem")))
					.getText();
		} else {
			wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_qty_ValidCount_MoreItems")))
					.getText();
		}

		String OnlineSizeDesc = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		int wtflag = 0;
		String wt_multiplier_index = null;

		if (wt_item_text1.endsWith("g")) {
			wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
			// testLog.info("1: "+wt_item_text1);
			OnlineSizeDesc = OnlineSizeDesc.substring(8, OnlineSizeDesc.length() - 1);
			// testLog.info("2: "+OnlineSizeDesc);

			if (wt_item_text1.endsWith("k")) {
				wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
				// testLog.info("3: "+wt_item_text1);
				OnlineSizeDesc = OnlineSizeDesc.substring(0, OnlineSizeDesc.length() - 1);
				// testLog.info("4: "+OnlineSizeDesc);
				wtflag = 1;
			} else {
				wtflag = 2;
			}
		}

		double doub_qty1, doub_qty2;
		int int_qty1, int_qty2, base_qty = 0;

		switch (wtflag) {
		case 1:
			doub_qty1 = Double.parseDouble(wt_item_text1);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);
			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty = (int) doub_temp;
			testLog.info("base_qty: " + base_qty);

			int tempval1 = Integer.parseInt(index);
			double tempval2 = (double) Math.round((doub_qty2 * tempval1) * 100) / 100;
			wt_multiplier_index = Double.toString(tempval2);
			testLog.info("wt_multiplier_index: " + wt_multiplier_index);
			break;
		case 2:
			int_qty1 = Integer.parseInt(wt_item_text1);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty = int_qty1 / int_qty2;
			testLog.info("base_qty: " + base_qty);

			int tempval3 = Integer.parseInt(index);
			int tempval4 = int_qty2 * tempval3;
			wt_multiplier_index = Integer.toString(tempval4);
			break;
		default:
			testLog.error("Element not found: Check the searched SKU is a Weighted Item");
			assertCheck("wt_update_dietcontroller_verifyFC",
					"Element not found: Check the searched SKU is a Weighted Item");
			break;
		}

		String dropdown_product = OR_OR.getProperty("RHST_Tile1_DropdownIcon_Only1Element");
		if (search_sku.endsWith("P")) {
			String search_term = search_sku.substring(0, search_sku.length() - 1);
			dd_newElement1 = dropdown_product.replace("sku", search_term);
		} else {
			dd_newElement1 = dropdown_product.replace("sku", search_sku);
		}

		String searchsku = search_sku;
		int ind = Integer.parseInt(index);

		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		}

		rhststrfcprice_1 = rhststrfctext_1.substring(rhststrfctext_1.lastIndexOf("$") + 1);
		rhststrfcprice_1 = rhststrfcprice_1.replace(",", "");
		rhststrdoublefcprice_1 = Double.parseDouble(rhststrfcprice_1);

		rhststrintqty_1 = base_qty;
		testLog.info("qty before adding to trolley on fc is: " + rhststrintqty_1);

		rhststrdoublefcpriceqty_1 = (double) Math.round((rhststrdoublefcprice_1 / rhststrintqty_1) * 100) / 100;

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		switch (ind) {
		case 0:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select0"))).click();
			break;
		case 1:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select1"))).click();
			break;
		case 2:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select2"))).click();
			break;
		case 3:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select3"))).click();
			break;
		case 4:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select4"))).click();
			break;
		default:
			wait(2000);
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore"))).click();

			String fiveorMore = OR_OR.getProperty("Select5orMore");
			if (searchsku.endsWith("P")) {
				String search_term = searchsku.substring(0, searchsku.length() - 1);
				String newElement1 = fiveorMore.replace("sku", search_term);
				driver.findElement(By.xpath(newElement1)).clear();
				wait(2000);
				Sendkey_xpath(newElement1, wt_multiplier_index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			} else {
				String newElement2 = fiveorMore.replace("sku", searchsku);
				driver.findElement(By.xpath(newElement2)).clear();
				wait(2000);
				Sendkey_xpath(newElement2, wt_multiplier_index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			}
			break;
		}

		// Code to get quantity & price, trolley price after updating the
		// quantity
		int base_qty2 = 0;
		if (ind == 0) {
			rhststrintqty_2 = 0;
			rhststrdoublefcprice_2 = 0.00;
			wait(4000);
		} else {
			if (listchanges.size() == 2) {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty")))
						.getText();
			} else {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty")))
						.getText();
			}

			rhststrfcprice_2 = rhststrfctext_2.substring(rhststrfctext_2.lastIndexOf("$") + 1);
			rhststrfcprice_2 = rhststrfcprice_2.replace(",", "");
			rhststrdoublefcprice_2 = Double.parseDouble(rhststrfcprice_2);

			switch (wtflag) {
			case 1:
				// testLog.info("Entered case1");
				rhststrqty_2 = rhststrqty_2.substring(0, rhststrqty_2.length() - 2);
				// testLog.info("strqty_2: "+rhststrqty_2);
				doub_qty1 = Double.parseDouble(rhststrqty_2);
				doub_qty2 = Double.parseDouble(OnlineSizeDesc);
				double doub_temp = (doub_qty1) / (doub_qty2);
				base_qty2 = (int) doub_temp;
				testLog.info("base_qty2 :" + base_qty2);
				break;
			case 2:
				// testLog.info("Entered case2");
				rhststrqty_2 = rhststrqty_2.substring(0, rhststrqty_2.length() - 1);
				// testLog.info("strqty_2: "+rhststrqty_2);
				int_qty1 = Integer.parseInt(rhststrqty_2);
				int_qty2 = Integer.parseInt(OnlineSizeDesc);
				base_qty2 = int_qty1 / int_qty2;
				testLog.info("base_qty2 :" + base_qty2);
				break;
			default:
				break;
			}

			rhststrintqty_2 = base_qty2;
		}

		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);

		updated_rhststrdoubleqty_2 = (double) Math.round(rhststrintqty_2 * rhststrdoublefcpriceqty_1 * 100) / 100;

		if (superbarintqty_2 == superbarintqty_1) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
					+ " to " + rhststrintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from "
					+ rhststrintqty_1 + " to " + rhststrintqty_2
					+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1 + " to "
					+ superbarintqty_2);
			assertCheck("wt_update_dietcontroller_verifyFC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
							+ " to " + rhststrintqty_2
							+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1
							+ " to " + superbarintqty_2);
		}

		if (rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2)) {
			testLog.info("The FC value updated correctly to selected QTY: " + rhststrintqty_2 + " and Price: "
					+ rhststrdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
					+ rhststrdoublefcprice_2);
			assertCheck("wt_update_dietcontroller_verifyFC", "The FC price is not udpated correctly when selecting QTY:"
					+ rhststrintqty_2 + " and price: " + rhststrdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((rhststrintqty_2 - rhststrintqty_1) * rhststrdoublefcpriceqty_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);

		if (updated_Trolley_Total == Trolley_Total_Cost) {
			testLog.info("Updated Trolley Total after adding item with cost " + rhststrdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
			assertCheck("wt_update_dietcontroller_verifyFC",
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
		}

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		int base_qty3 = 0;

		if (ind == 0) {
			String str11 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
			testLog.info(str11);
			boolean b1 = str11.contains("Add");
			testLog.info(b1);
			if (b1 == true) {
				testLog.info(
						"This product is no more added in the trolley and verified from the Fat controller of the product tile");

			} else {
				testLog.error(
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
				assertCheck("wt_update_dietcontroller_verifyFC",
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
			}
		} else {
			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strlhsfcprice_1 = lhsFC.substring(lhsFC.lastIndexOf("$") + 1);
			strlhsfcprice_1 = strlhsfcprice_1.replace(",", "");
			strlhsdoublefcprice_1 = Double.parseDouble(strlhsfcprice_1);

			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();

			switch (wtflag) {
			case 1:
				lhsFC = lhsFC.substring(0, lhsFC.length() - 2);
				doub_qty1 = Double.parseDouble(lhsFC);
				doub_qty2 = Double.parseDouble(OnlineSizeDesc);
				double doub_temp = (doub_qty1) / (doub_qty2);
				base_qty3 = (int) doub_temp;
				testLog.info("base_qty3 :" + base_qty3);

				break;
			case 2:
				lhsFC = lhsFC.substring(0, lhsFC.length() - 1);
				int_qty1 = Integer.parseInt(lhsFC);
				int_qty2 = Integer.parseInt(OnlineSizeDesc);
				base_qty3 = int_qty1 / int_qty2;
				testLog.info("base_qty3 :" + base_qty3);

				break;
			default:
				break;
			}

			lhsstrintqty_1 = base_qty3;

			if ((strlhsdoublefcprice_1 == rhststrdoublefcprice_2) & (lhsstrintqty_1 == rhststrintqty_2)) {
				testLog.info("The cost added for FC and RHST DC - both are same with Qty: " + lhsstrintqty_1
						+ " and Price: " + strlhsdoublefcprice_1);

			} else {
				testLog.error("The cost added for FC and RHST DC - both are not same with values- FC Price: "
						+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
				assertCheck("wt_update_dietcontroller_verifyFC",
						"The cost added for FC and RHST DC - both are not same with values- FC Price: "
								+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
			}
		}
	}

	// Function to add or update the FC for a special non weighted item and
	// verify
	// details reflecting in super bar & DC
	public void special_add_update_fatcontroller_verifyDC(String index, String addorupdate, String search_sku) {
		testLog.info("Starting special_add_update_fatcontroller_verifyDC function ");
		boolean bool2, bool3, bool4;
		String strfctext_1, strfctext_2, strfctext_3, strfctext_4, strfcprice_1, strfcprice_2, strfcprice_4, strqty_1,
				strqty_2;
		String strrhstfcprice_1;
		double strdoublefcprice_1, strdoublefcprice_2, updated_strdoubleqty_1, updated_strdoubleqty_2;
		int strintqty_1, strintqty_2;
		double strdoublefcprice_4, strrhstdoublefcprice_1;
		String strsaveprice_1, strsaveprice_2;
		double strdoublesaveprice_1, strdoublesaveprice_2;
		int wt_multiplier = 1;
		String wt_multiplier_index;

		boolean buttonsuccess1 = isElementPresent(OR_OR.getProperty("Tile_Dropdown_Success"), "Tile_Dropdown_Success");
		if (buttonsuccess1 == false) {
			testLog.info("No product is added to trolley for this SKU");

			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ZeroCount"))).getText();
			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strintqty_1 = 0;

			/*
			 * strsaveprice_1 = driver.findElement(By.xpath(OR_OR.getProperty(
			 * "Tile1_SavePrice_ValidCout"))). getText(); strsaveprice_1 =
			 * strsaveprice_1.replace(",", ""); strsaveprice_1 =
			 * strsaveprice_1.substring(strsaveprice_1.lastIndexOf("$") + 1); if
			 * (strsaveprice_1.contains(" ")) { strsaveprice_1 =
			 * strsaveprice_1.substring(0, strfcprice_1.indexOf(" ")); }
			 * strdoublesaveprice_1 = Double.parseDouble(strsaveprice_1);
			 */
		} else {
			testLog.info("Aleady this product is added to trolley");

			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
			strqty_1 = strqty_1.replaceAll("\\s+", "");
			strintqty_1 = Integer.parseInt(strqty_1);
			strdoublefcprice_1 = (double) Math.round((strdoublefcprice_1 / strintqty_1) * 100) / 100;

			/*
			 * strsaveprice_1 = driver.findElement(By.xpath(OR_OR.getProperty(
			 * "Tile1_SavePrice_ValidCout"))). getText(); strsaveprice_1 =
			 * strsaveprice_1.replace(",", ""); strsaveprice_1 =
			 * strsaveprice_1.substring(strsaveprice_1.lastIndexOf("$") + 1); if
			 * (strsaveprice_1.contains(" ")) { strsaveprice_1 =
			 * strsaveprice_1.substring(0, strfcprice_1.indexOf(" ")); }
			 * strdoublesaveprice_1 = Double.parseDouble(strsaveprice_1);
			 * strdoublesaveprice_1 = (double) Math.round((strdoublesaveprice_1
			 * / strintqty_1) * 100) / 100;
			 * testLog.info("strdoublesaveprice_1: " + strdoublesaveprice_1);
			 * ATUReports .add("strdoublesaveprice_1:", "", "",
			 * Double.toString(strdoublesaveprice_1), LogAs.INFO, new
			 * CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			 */
		}

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		String superbarqty_1, superbarqty_2;
		int superbarintqty_1, superbarintqty_2;

		if (Trolley_Total_Cost == 0.0) {
			superbarintqty_1 = 0;
		} else {
			superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
			superbarintqty_1 = Integer.parseInt(superbarqty_1);
		}

		int temp_intval = Integer.parseInt(index);
		wt_multiplier = wt_multiplier * temp_intval;
		wt_multiplier_index = Integer.toString(wt_multiplier);

		click_FC_Select_Item(OR_OR.getProperty("Tile1_Dropdown"), "Tile1_Dropdown",
				OR_OR.getProperty("Tile1_Dropdown_SelectN"), "Tile1_Dropdown_SelectN", index, addorupdate, search_sku,
				wt_multiplier_index);
		wait(5000);

		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);
		testLog.info("superbarintqty_2: " + superbarintqty_2);

		strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_2 = strfcprice_2.replace(",", "");
		strfcprice_2 = strfcprice_2.substring(strfcprice_2.lastIndexOf("$") + 1);
		if (strfcprice_2.contains(" ")) {
			strfcprice_2 = strfcprice_2.substring(0, strfcprice_2.indexOf(" "));
		}
		strdoublefcprice_2 = Double.parseDouble(strfcprice_2);
		testLog.info("strdoublefcprice_2: " + strdoublefcprice_2);

		strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
		strqty_2 = strqty_2.replaceAll("\\s+", "");
		strintqty_2 = Integer.parseInt(strqty_2);
		updated_strdoubleqty_2 = (double) Math.round(strintqty_2 * strdoublefcprice_1 * 100) / 100;

		/*
		 * strsaveprice_2 = driver.findElement(By.xpath(OR_OR.getProperty(
		 * "Tile1_SavePrice_ValidCout"))). getText(); strsaveprice_2 =
		 * strsaveprice_2.replace(",", ""); strsaveprice_2 =
		 * strsaveprice_2.substring(strsaveprice_2.lastIndexOf("$") + 1); if
		 * (strsaveprice_2.contains(" ")) { strsaveprice_2 =
		 * strsaveprice_2.substring(0, strfcprice_1.indexOf(" ")); }
		 * strdoublesaveprice_2 = Double.parseDouble(strsaveprice_2);
		 */
		if (superbarintqty_2 == (superbarintqty_1 + (strintqty_2 - strintqty_1))) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
					+ superbarintqty_1 + " to " + superbarintqty_2);

			assertCheck("special_add_update_fatcontroller_verifyDC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1 + " to "
							+ strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
							+ superbarintqty_1 + " to " + superbarintqty_2);
		}

		if (strdoublefcprice_2 == (updated_strdoubleqty_2)) {
			testLog.info("The FC value updated correctly to selected QTY: " + strintqty_2 + " and Price: "
					+ strdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
					+ strdoublefcprice_2);
			assertCheck("special_add_update_fatcontroller_verifyDC",
					"The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
							+ strdoublefcprice_2);
		}
		/*
		 * int temp_intvalue=1; if (strintqty_2-strintqty_1<0) {
		 * temp_intvalue=-1; }
		 */
		/*
		 * double temp_doubvalue = (double) Math.round((strintqty_2) *
		 * strdoublesaveprice_1 * 100) / 100; testLog.info(temp_doubvalue); if
		 * (strdoublesaveprice_2 == temp_doubvalue) {
		 * testLog.info("The save value updated correctly to the value: " +
		 * strdoublesaveprice_2);
		 * ATUReports.add("The save value updated correctly to the value:", "",
		 * "", Double.toString(strdoublesaveprice_2), LogAs.PASSED, new
		 * CaptureScreen(ScreenshotOf.BROWSER_PAGE)); } else { testLog.
		 * error("The save value is not udpated correctly and displayed as: " +
		 * strdoublesaveprice_2);
		 * atu_Failure("The save value is not udpated correctly and displayed as:"
		 * , "", "", Double.toString(strdoublesaveprice_2)); screenshot();
		 * status1 = 1; }
		 */
		updated_Trolley_Total = Trolley_Total_Cost + ((strintqty_2 - strintqty_1) * strdoublefcprice_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);

		if (updated_Trolley_Total == Trolley_Total_Cost) {
			testLog.info("Updated Trolley Total after adding item with cost " + strdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
			assertCheck("special_add_update_fatcontroller_verifyDC",
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
		}

		/*
		 * *********************************************************************
		 * ******** **
		 */

		String pName = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_ProductName"))).getText();
		String onlineSD = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		if (onlineSD.contains(" ")) {
			onlineSD = (onlineSD.substring(0, onlineSD.indexOf(" "))).trim();
		}
		String space = " ";
		String pName1 = pName.concat(space).concat(onlineSD);
		testLog.info("Product name extracted from LHS product tile is :" + pName1);

		strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_4 = strfcprice_4.replace(",", "");
		strfcprice_4 = strfcprice_4.substring(strfcprice_4.lastIndexOf("$") + 1);
		if (strfcprice_4.contains(" ")) {
			strfcprice_4 = strfcprice_4.substring(0, strfcprice_4.indexOf(" "));
		}
		strdoublefcprice_4 = Double.parseDouble(strfcprice_4);

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(2000);
		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);

		String rHSTpName;
		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_Only1element")))
					.getText();
			testLog.info(rHSTpName);
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_MoreElements")))
					.getText();
			testLog.info(rHSTpName);
		}

		if (rHSTpName.contains(pName1)) {
			testLog.info("The product name in FatController & the 1st item displayed in RHST are same as: " + pName1);

		} else {
			testLog.error("The product added last is not appearing at the first position in RHST");
			assertCheck("special_add_update_fatcontroller_verifyDC",
					"The product added last is not appearing at the first position in RHST");
		}

		String rHSTFC, saverHSTFC;
		double saverHSTFCdouble;
		if (listchanges.size() == 2) {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price"))).getText();
			testLog.info(rHSTpName + "and price is" + rHSTFC);
		} else {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price"))).getText();
			testLog.info("else statement " + rHSTpName + "and price is" + rHSTFC);
		}

		wait(3000);
		strrhstfcprice_1 = rHSTFC.substring(rHSTFC.lastIndexOf("$") + 1);
		strrhstfcprice_1 = strrhstfcprice_1.replace(",", "");
		if (strrhstfcprice_1.contains(" ")) {
			strrhstfcprice_1 = strrhstfcprice_1.substring(0, strrhstfcprice_1.indexOf(" "));
		}
		strrhstdoublefcprice_1 = Double.parseDouble(strrhstfcprice_1);
		/*
		 * saverHSTFC = driver.findElement(By.xpath(OR_OR.getProperty(
		 * "RHST_Tile1_SavePrice_ValidCout"))).getText(); saverHSTFC =
		 * saverHSTFC.substring(rHSTFC.lastIndexOf("$") + 1); saverHSTFC =
		 * saverHSTFC.replace(",", ""); if (saverHSTFC.contains(" ")) {
		 * saverHSTFC = saverHSTFC.substring(0, saverHSTFC.indexOf(" ")); }
		 * saverHSTFCdouble = Double.parseDouble(saverHSTFC);
		 */
		if (strdoublefcprice_4 == strrhstdoublefcprice_1) {
			testLog.info("The cost added for FC and RHST DC - both are same with value: " + strrhstdoublefcprice_1);

		} else {
			testLog.error("The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
					+ " and DC: " + strrhstdoublefcprice_1);

			assertCheck("special_add_update_fatcontroller_verifyDC",
					"The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
							+ " and DC: " + strrhstdoublefcprice_1);
		}

		/*
		 * if (saverHSTFCdouble == strdoublesaveprice_2) { testLog.
		 * info("The save amount is correctly displayed in RHST with value :" +
		 * saverHSTFCdouble); ATUReports.
		 * add("The save amount is correctly displayed in RHST with value :",
		 * "", "", Double.toString(saverHSTFCdouble), LogAs.PASSED, new
		 * CaptureScreen(ScreenshotOf.BROWSER_PAGE)); } else { testLog.
		 * error("The save amount is not correctly displayed in RHST with value :"
		 * + saverHSTFCdouble + " and FC with save value: " +
		 * strdoublesaveprice_2);
		 * 
		 * atu_Failure("The save amount is not correctly displayed in RHST with value:"
		 * , Double.toString(saverHSTFCdouble), "and FC with save value:",
		 * Double.toString(strdoublesaveprice_2)); screenshot(); status1 = 1; }
		 */
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
	}

	// Function to update the product quantity for a special item in RHST
	// product
	// tile and verify the same in fat controller
	public void special_update_dietcontroller_verifyFC(String index, String search_sku) {
		wait(2000);
		String superbarqty_1, superbarqty_2, rhststrfctext_1, rhststrfcprice_1, rhststrqty_1;
		int superbarintqty_1, superbarintqty_2, rhststrintqty_1, lhsstrintqty_1, rhststrintqty_2;
		double rhststrdoublefcprice_1, rhststrdoublefcpriceqty_1, strlhsdoublefcprice_1, rhststrdoublefcprice_2,
				updated_rhststrdoubleqty_2;
		String rhststrfctext_2, rhststrfcprice_2, rhststrqty_2, lhsFC, strlhsfcprice_1, lhsstrqty_1;
		String dd_newElement1, dd_newElement2;

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(3000);
		superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
		superbarintqty_1 = Integer.parseInt(superbarqty_1);

		String dropdown_product = OR_OR.getProperty("RHST_Tile1_DropdownIcon_Only1Element");
		if (search_sku.endsWith("P")) {
			String search_term = search_sku.substring(0, search_sku.length() - 1);
			dd_newElement1 = dropdown_product.replace("sku", search_term);
		} else {
			dd_newElement1 = dropdown_product.replace("sku", search_sku);
		}

		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);
		String searchsku = search_sku;
		int ind = Integer.parseInt(index);

		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		}

		rhststrfcprice_1 = rhststrfctext_1.substring(rhststrfctext_1.lastIndexOf("$") + 1);
		rhststrfcprice_1 = rhststrfcprice_1.replace(",", "");
		rhststrdoublefcprice_1 = Double.parseDouble(rhststrfcprice_1);

		rhststrqty_1 = rhststrqty_1.replaceAll("\\s+", "");
		rhststrintqty_1 = Integer.parseInt(rhststrqty_1);
		testLog.info("qty before adding to trolley on fc is: " + rhststrintqty_1);

		rhststrdoublefcpriceqty_1 = (double) Math.round((rhststrdoublefcprice_1 / rhststrintqty_1) * 100) / 100;

		/*
		 * String saverHSTFC_1; double saverHSTFCdouble_1, basesaverHSTFCdouble;
		 * 
		 * saverHSTFC_1 = driver.findElement(By.xpath(OR_OR.getProperty(
		 * "RHST_Tile1_SavePrice_ValidCout"))).getText(); saverHSTFC_1 =
		 * saverHSTFC_1.substring(saverHSTFC_1.lastIndexOf("$") + 1);
		 * saverHSTFC_1 = saverHSTFC_1.replace(",", ""); if
		 * (saverHSTFC_1.contains(" ")) { saverHSTFC_1 =
		 * saverHSTFC_1.substring(0, saverHSTFC_1.indexOf(" ")); }
		 * saverHSTFCdouble_1 = Double.parseDouble(saverHSTFC_1);
		 * basesaverHSTFCdouble = (double) Math.round((saverHSTFCdouble_1 /
		 * rhststrintqty_1) * 100) / 100;
		 */

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		switch (ind) {
		case 0:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select0"))).click();
			break;
		case 1:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select1"))).click();
			break;
		case 2:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select2"))).click();
			break;
		case 3:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select3"))).click();
			break;
		case 4:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select4"))).click();
			break;
		default:
			wait(2000);
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore"))).click();
			wait(2000);
			String fiveorMore = OR_OR.getProperty("Select5orMore");
			if (searchsku.endsWith("P")) {
				String search_term = searchsku.substring(0, searchsku.length() - 1);
				String newElement1 = fiveorMore.replace("sku", search_term);
				driver.findElement(By.xpath(newElement1)).clear();
				wait(2000);
				Sendkey_xpath(newElement1, index, "Select5orMore");
				wait(2000);
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			} else {
				String newElement2 = fiveorMore.replace("sku", searchsku);
				driver.findElement(By.xpath(newElement2)).clear();
				wait(2000);
				Sendkey_xpath(newElement2, index, "Select5orMore");
				wait(2000);
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			}
			break;
		}

		// Code to get quantity & price, trolley price after updating the
		// quantity
		double saverHSTFCdouble_2;
		String saverHSTFC_2;

		if (ind == 0) {
			rhststrintqty_2 = 0;
			rhststrdoublefcprice_2 = 0.00;
			saverHSTFCdouble_2 = 0.0;
			wait(4000);
		} else {
			if (listchanges.size() == 2) {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty")))
						.getText();
			} else {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty")))
						.getText();
			}

			rhststrfcprice_2 = rhststrfctext_2.substring(rhststrfctext_2.lastIndexOf("$") + 1);
			rhststrfcprice_2 = rhststrfcprice_2.replace(",", "");
			rhststrdoublefcprice_2 = Double.parseDouble(rhststrfcprice_2);
			rhststrqty_2 = rhststrqty_2.replaceAll("\\s+", "");
			rhststrintqty_2 = Integer.parseInt(rhststrqty_2);

			saverHSTFC_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_SavePrice_ValidCout"))).getText();
			saverHSTFC_2 = saverHSTFC_2.substring(saverHSTFC_2.lastIndexOf("$") + 1);
			saverHSTFC_2 = saverHSTFC_2.replace(",", "");
			if (saverHSTFC_2.contains(" ")) {
				saverHSTFC_2 = saverHSTFC_2.substring(0, saverHSTFC_2.indexOf(" "));
			}
			saverHSTFCdouble_2 = Double.parseDouble(saverHSTFC_2);

		}

		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);

		updated_rhststrdoubleqty_2 = (double) Math.round(rhststrintqty_2 * rhststrdoublefcpriceqty_1 * 100) / 100;

		if (superbarintqty_2 == (superbarintqty_1 + (rhststrintqty_2 - rhststrintqty_1))) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
					+ " to " + rhststrintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from "
					+ rhststrintqty_1 + " to " + rhststrintqty_2
					+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1 + " to "
					+ superbarintqty_2);
			assertCheck("special_update_dietcontroller_verifyFC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
							+ " to " + rhststrintqty_2
							+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1
							+ " to " + superbarintqty_2);
		}

		if (rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2)) {
			testLog.info("The FC value updated correctly to selected QTY: " + rhststrintqty_2 + " and Price: "
					+ rhststrdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
					+ rhststrdoublefcprice_2);
			assertCheck("special_update_dietcontroller_verifyFC",
					"The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
							+ rhststrdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((rhststrintqty_2 - rhststrintqty_1) * rhststrdoublefcpriceqty_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);

		if (updated_Trolley_Total == Trolley_Total_Cost) {
			testLog.info("Updated Trolley Total after adding item with cost " + rhststrdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
			assertCheck("special_update_dietcontroller_verifyFC",
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
		}

		/*
		 * double temp_doubleval = (double) Math.round((basesaverHSTFCdouble *
		 * ind) * 100) / 100; if (saverHSTFCdouble_2 == temp_doubleval) {
		 * testLog.info("Save amount is updated correctly to: " +
		 * saverHSTFCdouble_2);
		 * ATUReports.add("Save amount is updated correctly to:", "", "",
		 * Double.toString(saverHSTFCdouble_2), LogAs.PASSED, new CaptureScreen(
		 * ScreenshotOf.BROWSER_PAGE)); } else { testLog.
		 * error("Save amount is not udpated correctly and displayed as: " +
		 * saverHSTFCdouble_2);
		 * atu_Failure("Save amount is not udpated correctly and displayed as:",
		 * "", "", Double.toString(saverHSTFCdouble_2)); screenshot(); status1 =
		 * 1; }
		 */
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(2000);
		String lhssave_Price;
		double lhssavedouble_Price;

		if (ind == 0) {
			String str11 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
			testLog.info(str11);
			boolean b1 = str11.contains("Add");
			testLog.info(b1);
			if (b1 == true) {
				testLog.info(
						"This product is no more added in the trolley and verified from the Fat controller of the product tile");

			} else {
				testLog.error(
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
				assertCheck("special_update_dietcontroller_verifyFC",
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
			}
		} else {
			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strlhsfcprice_1 = lhsFC.substring(lhsFC.lastIndexOf("$") + 1);
			strlhsfcprice_1 = strlhsfcprice_1.replace(",", "");
			strlhsdoublefcprice_1 = Double.parseDouble(strlhsfcprice_1);

			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
			lhsstrqty_1 = lhsFC.replaceAll("\\s+", "");
			lhsstrintqty_1 = Integer.parseInt(lhsstrqty_1);

			lhssave_Price = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_SavePrice_ValidCout")))
					.getAttribute("innerHTML");
			lhssave_Price = lhssave_Price.replace(",", "");
			lhssave_Price = lhssave_Price.substring(lhssave_Price.lastIndexOf("$") + 1);
			lhssavedouble_Price = Double.parseDouble(lhssave_Price);

			if ((strlhsdoublefcprice_1 == rhststrdoublefcprice_2) & (lhsstrintqty_1 == rhststrintqty_2)
					& (lhssavedouble_Price == saverHSTFCdouble_2)) {
				testLog.info("The cost added for FC and RHST DC - both are same with Qty: " + lhsstrintqty_1
						+ ", Price: " + strlhsdoublefcprice_1 + " and save amount: " + lhssavedouble_Price);

			} else {
				testLog.error("The cost added for FC and RHST DC - both are not same with values- FC Price: "
						+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
				assertCheck("special_update_dietcontroller_verifyFC",
						"The cost added for FC and RHST DC - both are not same with values- FC Price: "
								+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
			}
		}
	}

	// Function to add or update the product quantity for a special weighted
	// item
	// and verify the same in RHST
	public void wt_special_add_update_fatcontroller_verifyDC(String index, String addorupdate, String search_sku) {
		testLog.info("Starting wt_special_add_update_fatcontroller_verifyDC function");
		boolean bool2, bool3, bool4;
		String strfctext_1, strfctext_2, strfctext_3, strfctext_4, strfcprice_1, strfcprice_2, strfcprice_4, strqty_1,
				strqty_2;
		String strrhstfcprice_1;
		double strdoublefcprice_1, strdoublefcprice_2, updated_strdoubleqty_1, updated_strdoubleqty_2;
		int strintqty_1, strintqty_2;
		double strdoublefcprice_4, strrhstdoublefcprice_1;

		strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
		testLog.info("strfctext_1: " + strfctext_1);

		// Code to check if the product is a weighted Item - If Yes, set the
		// wtflag
		// (String value) to 1-kg, 2-g
		String wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
		String OnlineSizeDesc = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		OnlineSizeDesc = OnlineSizeDesc.replace("on special", "").trim();

		int wtflag = 0;
		String wt_multiplier_index = null;

		if (wt_item_text1.endsWith("g")) {
			wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
			testLog.info("1: " + wt_item_text1);
			OnlineSizeDesc = OnlineSizeDesc.substring(8, OnlineSizeDesc.length() - 1);
			testLog.info("2: " + OnlineSizeDesc);

			if (wt_item_text1.endsWith("k")) {
				wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
				testLog.info("3: " + wt_item_text1);
				OnlineSizeDesc = OnlineSizeDesc.substring(0, OnlineSizeDesc.length() - 1);
				testLog.info("4: " + OnlineSizeDesc);
				wtflag = 1;
			} else {
				wtflag = 2;
			}
		}

		double doub_qty1, doub_qty2;
		int int_qty1, int_qty2, base_qty = 0;

		switch (wtflag) {
		case 1:
			doub_qty1 = Double.parseDouble(wt_item_text1);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);
			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty = (int) doub_temp;
			testLog.info("base_qty: " + base_qty);

			int tempval1 = Integer.parseInt(index);
			double tempval2 = (double) Math.round((doub_qty2 * tempval1) * 100) / 100;
			wt_multiplier_index = Double.toString(tempval2);
			testLog.info("wt_multiplier_index: " + wt_multiplier_index);

			break;
		case 2:
			int_qty1 = Integer.parseInt(wt_item_text1);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty = int_qty1 / int_qty2;
			testLog.info("base_qty: " + base_qty);

			int tempval3 = Integer.parseInt(index);
			int tempval4 = int_qty2 * tempval3;
			wt_multiplier_index = Integer.toString(tempval4);

			break;
		default:
			testLog.error("Element not found: Check the searched SKU is a Weighted Item");
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"Element not found: Check the searched SKU is a Weighted Item");
			break;
		}

		String strsaveprice_1;
		double strdoublesaveprice_1;
		/*
		 * bool2 = strfctext_1.contains("Add"); testLog.info("bool2: "+bool2);
		 */
		boolean buttonsuccess_1 = isElementPresent(OR_OR.getProperty("Tile_Dropdown_Success"), "Tile_Dropdown_Success");
		if (buttonsuccess_1 == false) {
			testLog.info("Entered false statement: which means it is not added to trolley");
			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ZeroCount"))).getText();

			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strintqty_1 = 0;

			driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Dropdown"))).click();
			wait(2000);

			strsaveprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_SavePrice_DropdownOp1_Value")))
					.getText();
			driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Dropdown"))).click();

			testLog.info("strsaveprice_1: " + strsaveprice_1);
			// strsaveprice_1=strsaveprice_1.replaceAll(",", "");
			strsaveprice_1 = strsaveprice_1.replace(",", "");
			strsaveprice_1 = strsaveprice_1.substring(strsaveprice_1.lastIndexOf("$") + 1);
			testLog.info("Final strsave " + strsaveprice_1);
			if (strsaveprice_1.contains(" ")) {
				strsaveprice_1 = strsaveprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublesaveprice_1 = Double.parseDouble(strsaveprice_1);
			testLog.info("strdoublesaveprice_1 :" + strdoublesaveprice_1);
		} else {
			strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strfcprice_1 = strfcprice_1.replace(",", "");
			strfcprice_1 = strfcprice_1.substring(strfcprice_1.lastIndexOf("$") + 1);
			if (strfcprice_1.contains(" ")) {
				strfcprice_1 = strfcprice_1.substring(0, strfcprice_1.indexOf(" "));
			}
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			strintqty_1 = base_qty;
			// testLog.info("strintqty_1: "+strintqty_1);
			strdoublefcprice_1 = (double) Math.round((strdoublefcprice_1 / strintqty_1) * 100) / 100;
			/*
			 * strsaveprice_1 = driver.findElement(By.xpath(OR_OR.getProperty(
			 * "Tile1_SavePrice_ValidCout"))). getText(); strsaveprice_1 =
			 * strsaveprice_1.replace(",", ""); strsaveprice_1 =
			 * strsaveprice_1.substring(strsaveprice_1.lastIndexOf("$") + 1); if
			 * (strsaveprice_1.contains(" ")) { strsaveprice_1 =
			 * strsaveprice_1.substring(0, strfcprice_1.indexOf(" ")); }
			 * strdoublesaveprice_1 = Double.parseDouble(strsaveprice_1);
			 * strdoublesaveprice_1 = (double) Math.round((strdoublesaveprice_1
			 * / strintqty_1) * 100) / 100;
			 * testLog.info("strdoublesaveprice_1: " + strdoublesaveprice_1);
			 */
		}

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		String superbarqty_1, superbarqty_2;
		int superbarintqty_1, superbarintqty_2;

		if (Trolley_Total_Cost == 0.0) {
			superbarintqty_1 = 0;
		} else {
			superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
			superbarintqty_1 = Integer.parseInt(superbarqty_1);
		}

		click_FC_Select_Item(OR_OR.getProperty("Tile1_Dropdown"), "Tile1_Dropdown",
				OR_OR.getProperty("Tile1_Dropdown_SelectN"), "Tile1_Dropdown_SelectN", index, addorupdate, search_sku,
				wt_multiplier_index);
		wait(5000);
		// testLog.info("Chkpt1");

		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);

		strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_2 = strfcprice_2.replace(",", "");
		strfcprice_2 = strfcprice_2.substring(strfcprice_2.lastIndexOf("$") + 1);
		if (strfcprice_2.contains(" ")) {
			strfcprice_2 = strfcprice_2.substring(0, strfcprice_2.indexOf(" "));
		}
		strdoublefcprice_2 = Double.parseDouble(strfcprice_2);

		int base_qty2 = 0;

		strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
		strqty_2 = strqty_2.replaceAll("\\s+", "");
		switch (wtflag) {
		case 1:
			testLog.info("Entered case1");
			strqty_2 = strqty_2.substring(0, strqty_2.length() - 2);
			// testLog.info("strqty_2: "+strqty_2);
			doub_qty1 = Double.parseDouble(strqty_2);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);
			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty2 = (int) doub_temp;
			testLog.info("base_qty2 :" + base_qty2);
			break;
		case 2:
			testLog.info("Entered case2");
			strqty_2 = strqty_2.substring(0, strqty_2.length() - 1);
			// testLog.info("strqty_2: "+strqty_2);
			int_qty1 = Integer.parseInt(strqty_2);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty2 = int_qty1 / int_qty2;
			testLog.info("base_qty2 :" + base_qty2);
			break;
		default:
			break;
		}
		testLog.info("Check 0");
		strintqty_2 = base_qty2;
		updated_strdoubleqty_2 = (double) Math.round(strintqty_2 * strdoublefcprice_1 * 100) / 100;
		int temp_strintqty_1, temp_strintqty_2;
		if (addorupdate.equals("Add")) {
			temp_strintqty_2 = 1;
			temp_strintqty_1 = 0;
		} else {
			temp_strintqty_2 = 0;
			temp_strintqty_1 = 0;
		}
		testLog.info("Check 1");
		String strsaveprice_2;
		double strdoublesaveprice_2;

		/*
		 * Try to implement new code for making the hidden element visible for
		 * the Save statement and the save amount to display on the screen
		 * WebElement elementx=
		 * driver.findElement(By.cssSelector("div[style*='none']"));
		 * JavascriptExecutor executor = JavascriptExecutor; executor.
		 * executeScript("arguments[0].setAttribute('style','display: visible;');"
		 * ,elementx); wait(5000);
		 * testLog.info("Executed Executor java script");
		 */
		/*
		 * strsaveprice_2 = driver.findElement(By.xpath(OR_OR.getProperty(
		 * "Tile1_SavePrice_ValidCout"))). getText(); strsaveprice_2 =
		 * strsaveprice_2.replace(",", ""); strsaveprice_2 =
		 * strsaveprice_2.substring(strsaveprice_2.lastIndexOf("$") + 1); if
		 * (strsaveprice_2.contains(" ")) { strsaveprice_2 =
		 * strsaveprice_2.substring(0, strfcprice_1.indexOf(" ")); }
		 * strdoublesaveprice_2 = Double.parseDouble(strsaveprice_2);
		 * testLog.info("strdoublesaveprice_2: "+strdoublesaveprice_2);
		 */
		if (superbarintqty_2 == (superbarintqty_1 + (temp_strintqty_2 - temp_strintqty_1))) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
					+ " to " + strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
					+ superbarintqty_1 + " to " + superbarintqty_2);
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1 + " to "
							+ strintqty_2 + " , the trolley quantity is not updated correctly and changed from "
							+ superbarintqty_1 + " to " + superbarintqty_2);
		}

		if (strdoublefcprice_2 == (updated_strdoubleqty_2) || strdoublefcprice_2 == ((updated_strdoubleqty_2 - 0.01))
				|| strdoublefcprice_2 == ((updated_strdoubleqty_2 - 0.02))
				|| strdoublefcprice_2 == ((updated_strdoubleqty_2 - 0.03))) {
			testLog.info("The FC value updated correctly to selected QTY: " + strintqty_2 + " and Price: "
					+ strdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
					+ strdoublefcprice_2);
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
							+ strdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((strintqty_2 - strintqty_1) * strdoublefcprice_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);

		if (updated_Trolley_Total == Trolley_Total_Cost || ((updated_Trolley_Total - 0.01) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total - 0.02) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total - 0.03) == Trolley_Total_Cost)) {
			testLog.info("Updated Trolley Total after adding item with cost " + strdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
		}

		/*
		 * *********************************************************************
		 * ******** **
		 */

		String pName = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_ProductName"))).getText();
		String onlineSD = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		String space = " ";
		String pName1 = pName.concat(space).concat(onlineSD);
		testLog.info("Product name extracted from LHS product tile is :" + pName1);

		strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
		strfcprice_4 = strfcprice_4.replace(",", "");
		strfcprice_4 = strfcprice_4.substring(strfcprice_4.lastIndexOf("$") + 1);
		if (strfcprice_4.contains(" ")) {
			strfcprice_4 = strfcprice_4.substring(0, strfcprice_4.indexOf(" "));
		}
		strdoublefcprice_4 = Double.parseDouble(strfcprice_4);

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(2000);
		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);

		String rHSTpName;
		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_Only1element")))
					.getText();
			testLog.info(rHSTpName);
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_MoreElements")))
					.getText();
			testLog.info(rHSTpName);
		}

		if (rHSTpName.contains(pName1)) {
			testLog.info("The product name in FatController & the 1st item displayed in RHST are same as: " + pName1);

		} else {
			testLog.error("The product added last is not appearing at the first position in RHST");
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"The product added last is not appearing at the first position in RHST");
		}

		String rHSTFC;
		if (listchanges.size() == 2) {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price"))).getText();
			testLog.info(rHSTpName);
		} else {
			rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price"))).getText();
		}

		wait(3000);
		strrhstfcprice_1 = rHSTFC.substring(rHSTFC.lastIndexOf("$") + 1);
		strrhstfcprice_1 = strrhstfcprice_1.replace(",", "");
		if (strrhstfcprice_1.contains(" ")) {
			strrhstfcprice_1 = strrhstfcprice_1.substring(0, strrhstfcprice_1.indexOf(" "));
		}
		strrhstdoublefcprice_1 = Double.parseDouble(strrhstfcprice_1);

		String saverHSTFC;
		double saverHSTFCdouble;
		saverHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_SavePrice_ValidCout"))).getText();
		saverHSTFC = saverHSTFC.substring(rHSTFC.lastIndexOf("$") + 1);
		saverHSTFC = saverHSTFC.replace(",", "");
		if (saverHSTFC.contains(" ")) {
			saverHSTFC = saverHSTFC.substring(0, saverHSTFC.indexOf(" "));
		}
		saverHSTFCdouble = Double.parseDouble(saverHSTFC);

		if (strdoublefcprice_4 == strrhstdoublefcprice_1) {
			testLog.info("The cost added for FC and RHST DC - both are same with value: " + strrhstdoublefcprice_1);

		} else {
			testLog.error("The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
					+ " and DC: " + strrhstdoublefcprice_1);
			assertCheck("wt_special_add_update_fatcontroller_verifyDC",
					"The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
							+ " and DC: " + strrhstdoublefcprice_1);
		}
		/*
		 * if (saverHSTFCdouble == strdoublesaveprice_2) { testLog.
		 * info("The save amount is correctly displayed in RHST with value :" +
		 * saverHSTFCdouble); ATUReports.
		 * add("The save amount is correctly displayed in RHST with value :",
		 * "", "", Double.toString(saverHSTFCdouble), LogAs.PASSED, new
		 * CaptureScreen(ScreenshotOf.BROWSER_PAGE)); } else { testLog.
		 * error("The save amount is not correctly displayed in RHST with value :"
		 * + saverHSTFCdouble + " and FC with save value: " +
		 * strdoublesaveprice_2);
		 * 
		 * atu_Failure("The save amount is not correctly displayed in RHST with value :"
		 * , Double.toString(saverHSTFCdouble), "and FC with save value:",
		 * Double.toString(strdoublesaveprice_2)); screenshot(); status1 = 1; }
		 */
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
	}

	// Function to update the product quantity for a special weighted item in
	// RHST
	// product tile and verify the same in fat controller
	public void wt_special_update_dietcontroller_verifyFC(String index, String search_sku) {
		String superbarqty_1, superbarqty_2, rhststrfctext_1, rhststrfcprice_1, rhststrqty_1;
		int superbarintqty_1, superbarintqty_2, rhststrintqty_1, lhsstrintqty_1, rhststrintqty_2;
		double rhststrdoublefcprice_1, rhststrdoublefcpriceqty_1, strlhsdoublefcprice_1, rhststrdoublefcprice_2,
				updated_rhststrdoubleqty_2;
		String rhststrfctext_2, rhststrfcprice_2, rhststrqty_2, lhsFC, strlhsfcprice_1, lhsstrqty_1;
		String dd_newElement1, dd_newElement2;
		String wt_item_text1;

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(3000);
		superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
		superbarintqty_1 = Integer.parseInt(superbarqty_1);

		// Code to check if the product is a weighted Item - If Yes, set the
		// wtflag
		// (String value) to 1-kg, 2-g
		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);
		if (listchanges.size() == 2) {
			wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_qty_ValidCount_OneItem")))
					.getText();
		} else {
			wt_item_text1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_qty_ValidCount_MoreItems")))
					.getText();
		}

		String OnlineSizeDesc = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
		OnlineSizeDesc = OnlineSizeDesc.replace("on special", "").trim();
		int wtflag = 0;
		String wt_multiplier_index = null;

		if (wt_item_text1.endsWith("g")) {
			wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
			// testLog.info("1: "+wt_item_text1);
			OnlineSizeDesc = OnlineSizeDesc.substring(8, OnlineSizeDesc.length() - 1);
			// testLog.info("2: "+OnlineSizeDesc);

			if (wt_item_text1.endsWith("k")) {
				wt_item_text1 = wt_item_text1.substring(0, wt_item_text1.length() - 1);
				// testLog.info("3: "+wt_item_text1);
				OnlineSizeDesc = OnlineSizeDesc.substring(0, OnlineSizeDesc.length() - 1);
				// testLog.info("4: "+OnlineSizeDesc);
				wtflag = 1;
			} else {
				wtflag = 2;
			}
		}

		double doub_qty1, doub_qty2;
		int int_qty1, int_qty2, base_qty = 0;

		switch (wtflag) {
		case 1:
			doub_qty1 = Double.parseDouble(wt_item_text1);
			doub_qty2 = Double.parseDouble(OnlineSizeDesc);
			double doub_temp = (doub_qty1) / (doub_qty2);
			base_qty = (int) doub_temp;
			testLog.info("base_qty: " + base_qty);

			int tempval1 = Integer.parseInt(index);
			double tempval2 = (double) Math.round((doub_qty2 * tempval1) * 100) / 100;
			wt_multiplier_index = Double.toString(tempval2);
			testLog.info("wt_multiplier_index: " + wt_multiplier_index);

			break;
		case 2:
			int_qty1 = Integer.parseInt(wt_item_text1);
			int_qty2 = Integer.parseInt(OnlineSizeDesc);
			base_qty = int_qty1 / int_qty2;
			testLog.info("base_qty: " + base_qty);

			int tempval3 = Integer.parseInt(index);
			int tempval4 = int_qty2 * tempval3;
			wt_multiplier_index = Integer.toString(tempval4);

			break;
		default:
			testLog.error("Element not found: Check the searched SKU is a Weighted Item");
			assertCheck("wt_special_update_dietcontroller_verifyFC",
					"Element not found: Check the searched SKU is a Weighted Item");
			break;
		}

		String dropdown_product = OR_OR.getProperty("RHST_Tile1_DropdownIcon_Only1Element");
		if (search_sku.endsWith("P")) {
			String search_term = search_sku.substring(0, search_sku.length() - 1);
			dd_newElement1 = dropdown_product.replace("sku", search_term);
		} else {
			dd_newElement1 = dropdown_product.replace("sku", search_sku);
		}

		String searchsku = search_sku;
		int ind = Integer.parseInt(index);

		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
		}

		rhststrfcprice_1 = rhststrfctext_1.substring(rhststrfctext_1.lastIndexOf("$") + 1);
		rhststrfcprice_1 = rhststrfcprice_1.replace(",", "");
		rhststrdoublefcprice_1 = Double.parseDouble(rhststrfcprice_1);

		rhststrintqty_1 = base_qty;
		testLog.info("qty before adding to trolley on fc is: " + rhststrintqty_1);

		rhststrdoublefcpriceqty_1 = (double) Math.round((rhststrdoublefcprice_1 / rhststrintqty_1) * 100) / 100;

		String saverHSTFC_1;
		double saverHSTFCdouble_1, basesaverHSTFCdouble;

		saverHSTFC_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_SavePrice_ValidCout"))).getText();
		saverHSTFC_1 = saverHSTFC_1.substring(saverHSTFC_1.lastIndexOf("$") + 1);
		saverHSTFC_1 = saverHSTFC_1.replace(",", "");
		if (saverHSTFC_1.contains(" ")) {
			saverHSTFC_1 = saverHSTFC_1.substring(0, saverHSTFC_1.indexOf(" "));
		}
		saverHSTFCdouble_1 = Double.parseDouble(saverHSTFC_1);
		basesaverHSTFCdouble = (double) Math.round((saverHSTFCdouble_1 / rhststrintqty_1) * 100) / 100;

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		switch (ind) {
		case 0:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select0"))).click();
			break;
		case 1:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select1"))).click();
			break;
		case 2:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select2"))).click();
			break;
		case 3:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select3"))).click();
			break;
		case 4:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select4"))).click();
			break;
		default:
			wait(2000);
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore"))).click();

			String fiveorMore = OR_OR.getProperty("Select5orMore");
			if (searchsku.endsWith("P")) {
				String search_term = searchsku.substring(0, searchsku.length() - 1);
				String newElement1 = fiveorMore.replace("sku", search_term);
				driver.findElement(By.xpath(newElement1)).clear();
				wait(2000);
				Sendkey_xpath(newElement1, wt_multiplier_index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			} else {
				String newElement2 = fiveorMore.replace("sku", searchsku);
				driver.findElement(By.xpath(newElement2)).clear();
				wait(2000);
				Sendkey_xpath(newElement2, wt_multiplier_index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			}
			break;
		}

		// Code to get quantity & price, trolley price after updating the
		// quantity
		double saverHSTFCdouble_2;
		String saverHSTFC_2;

		int base_qty2 = 0;
		if (ind == 0) {
			rhststrintqty_2 = 0;
			rhststrdoublefcprice_2 = 0.00;
			saverHSTFCdouble_2 = 0.0;
			wait(4000);
		} else {
			if (listchanges.size() == 2) {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty")))
						.getText();
			} else {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty")))
						.getText();
			}

			rhststrfcprice_2 = rhststrfctext_2.substring(rhststrfctext_2.lastIndexOf("$") + 1);
			rhststrfcprice_2 = rhststrfcprice_2.replace(",", "");
			rhststrdoublefcprice_2 = Double.parseDouble(rhststrfcprice_2);

			switch (wtflag) {
			case 1:
				// testLog.info("Entered case1");
				rhststrqty_2 = rhststrqty_2.substring(0, rhststrqty_2.length() - 2);
				// testLog.info("strqty_2: "+rhststrqty_2);
				doub_qty1 = Double.parseDouble(rhststrqty_2);
				doub_qty2 = Double.parseDouble(OnlineSizeDesc);
				double doub_temp = (doub_qty1) / (doub_qty2);
				base_qty2 = (int) doub_temp;
				testLog.info("base_qty2 :" + base_qty2);
				break;
			case 2:
				// testLog.info("Entered case2");
				rhststrqty_2 = rhststrqty_2.substring(0, rhststrqty_2.length() - 1);
				// testLog.info("strqty_2: "+rhststrqty_2);
				int_qty1 = Integer.parseInt(rhststrqty_2);
				int_qty2 = Integer.parseInt(OnlineSizeDesc);
				base_qty2 = int_qty1 / int_qty2;
				testLog.info("base_qty2 :" + base_qty2);
				break;
			default:
				break;
			}
			rhststrintqty_2 = base_qty2;

			saverHSTFC_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_SavePrice_ValidCout"))).getText();
			saverHSTFC_2 = saverHSTFC_2.substring(saverHSTFC_2.lastIndexOf("$") + 1);
			saverHSTFC_2 = saverHSTFC_2.replace(",", "");
			if (saverHSTFC_2.contains(" ")) {
				saverHSTFC_2 = saverHSTFC_2.substring(0, saverHSTFC_2.indexOf(" "));
			}
			saverHSTFCdouble_2 = Double.parseDouble(saverHSTFC_2);

		}

		superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
		superbarintqty_2 = Integer.parseInt(superbarqty_2);

		updated_rhststrdoubleqty_2 = (double) Math.round(rhststrintqty_2 * rhststrdoublefcpriceqty_1 * 100) / 100;

		if (superbarintqty_2 == superbarintqty_1) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
					+ " to " + rhststrintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from "
					+ rhststrintqty_1 + " to " + rhststrintqty_2
					+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1 + " to "
					+ superbarintqty_2);
			assertCheck("wt_special_update_dietcontroller_verifyFC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
							+ " to " + rhststrintqty_2
							+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1
							+ " to " + superbarintqty_2);
		}

		if (rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2)
				|| rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2 - 0.01)
				|| rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2 - 0.02)
				|| rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2 - 0.03)) {
			testLog.info("The FC value updated correctly to selected QTY: " + rhststrintqty_2 + " and Price: "
					+ rhststrdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
					+ rhststrdoublefcprice_2);
			assertCheck("wt_special_update_dietcontroller_verifyFC",
					"The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
							+ rhststrdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((rhststrintqty_2 - rhststrintqty_1) * rhststrdoublefcpriceqty_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);
		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);
		if (updated_Trolley_Total == Trolley_Total_Cost || ((updated_Trolley_Total - 0.01) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total - 0.02) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total - 0.03) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total + 0.01) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total + 0.02) == Trolley_Total_Cost)
				|| ((updated_Trolley_Total + 0.03) == Trolley_Total_Cost)) {
			testLog.info("Updated Trolley Total after adding item with cost " + rhststrdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
			assertCheck("wt_special_update_dietcontroller_verifyFC",
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
		}

		double temp_doubleval = (double) Math.round((basesaverHSTFCdouble * ind) * 100) / 100;
		if (saverHSTFCdouble_2 == temp_doubleval || (saverHSTFCdouble_2 == temp_doubleval + 0.01)
				|| (saverHSTFCdouble_2 == temp_doubleval + 0.02) || (saverHSTFCdouble_2 == temp_doubleval + 0.03)
				|| (saverHSTFCdouble_2 == temp_doubleval - 0.01) || (saverHSTFCdouble_2 == temp_doubleval - 0.02)
				|| (saverHSTFCdouble_2 == temp_doubleval - 0.03)) {
			testLog.info("Save amount is updated correctly to: " + saverHSTFCdouble_2);

		} else {
			testLog.error("Save amount is not udpated correctly and displayed as: " + saverHSTFCdouble_2);
			assertCheck("wt_special_update_dietcontroller_verifyFC",
					"Save amount is not udpated correctly and displayed as: " + saverHSTFCdouble_2);
		}

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		String lhssave_Price;
		double lhssavedouble_Price;
		int base_qty3 = 0;

		if (ind == 0) {
			String str11 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
			testLog.info(str11);
			boolean b1 = str11.contains("Add");
			testLog.info(b1);
			if (b1 == true) {
				testLog.info(
						"This product is no more added in the trolley and verified from the Fat controller of the product tile");

			} else {
				testLog.error(
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
				assertCheck("wt_special_update_dietcontroller_verifyFC",
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
			}
		} else {
			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
			strlhsfcprice_1 = lhsFC.substring(lhsFC.lastIndexOf("$") + 1);
			strlhsfcprice_1 = strlhsfcprice_1.replace(",", "");
			strlhsdoublefcprice_1 = Double.parseDouble(strlhsfcprice_1);

			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();

			switch (wtflag) {
			case 1:
				lhsFC = lhsFC.substring(0, lhsFC.length() - 2);
				doub_qty1 = Double.parseDouble(lhsFC);
				doub_qty2 = Double.parseDouble(OnlineSizeDesc);
				double doub_temp = (doub_qty1) / (doub_qty2);
				base_qty3 = (int) doub_temp;
				testLog.info("base_qty3 :" + base_qty3);
				break;
			case 2:
				lhsFC = lhsFC.substring(0, lhsFC.length() - 1);
				int_qty1 = Integer.parseInt(lhsFC);
				int_qty2 = Integer.parseInt(OnlineSizeDesc);
				base_qty3 = int_qty1 / int_qty2;
				testLog.info("base_qty3 :" + base_qty3);
				break;
			default:
				break;
			}

			lhsstrintqty_1 = base_qty3;
			/*
			 * lhssave_Price = driver.findElement(By.xpath(OR_OR.getProperty(
			 * "Tile1_SavePrice_ValidCout"))). getText(); lhssave_Price =
			 * lhssave_Price.replace(",", ""); lhssave_Price =
			 * lhssave_Price.substring(lhssave_Price.lastIndexOf("$") + 1);
			 * lhssavedouble_Price = Double.parseDouble(lhssave_Price);
			 * 
			 * if ((strlhsdoublefcprice_1 == rhststrdoublefcprice_2) &
			 * (lhsstrintqty_1 == rhststrintqty_2) & (lhssavedouble_Price ==
			 * saverHSTFCdouble_2)) { testLog.
			 * info("The cost added for FC and RHST DC - both are same with Qty: "
			 * + lhsstrintqty_1 + ", Price: " + strlhsdoublefcprice_1 +
			 * " and save amount: " + lhssavedouble_Price); ATUReports.
			 * add("The cost added for FC and RHST DC - both are same with Qty:"
			 * , Integer.toString(lhsstrintqty_1), "Price:", Double
			 * .toString(strlhsdoublefcprice_1), LogAs.PASSED, new
			 * CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			 * ATUReports.add("The same amount is updated correctly as:", "",
			 * "", Double.toString(lhssavedouble_Price), LogAs.PASSED, new
			 * CaptureScreen( ScreenshotOf.BROWSER_PAGE)); } else { testLog.
			 * error("The cost added for FC and RHST DC - both are not same with values- FC Price: "
			 * + strlhsdoublefcprice_1 + " and DC Price: " +
			 * rhststrdoublefcprice_2);
			 * 
			 * atu_Failure("The cost added for FC and RHST DC - both are not same with values- FC Price:"
			 * , Double .toString(strlhsdoublefcprice_1), "and DC Price:",
			 * Double.toString(rhststrdoublefcprice_2)); screenshot(); status1 =
			 * 1; }
			 */
			if ((strlhsdoublefcprice_1 == rhststrdoublefcprice_2) & (lhsstrintqty_1 == rhststrintqty_2)) {
				testLog.info("The cost added for FC and RHST DC - both are same with Qty: " + lhsstrintqty_1
						+ ", Price: " + strlhsdoublefcprice_1);

			} else {
				testLog.error("The cost added for FC and RHST DC - both are not same with values- FC Price: "
						+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
				assertCheck("wt_special_update_dietcontroller_verifyFC",
						"The cost added for FC and RHST DC - both are not same with values- FC Price: "
								+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
			}
		}
	}

	// Function to click on FC item from product tile and add/ update the
	// product
	// quantity based on index provided
	public void click_FC_Select_Item(String ElementFCDDI, String ObjName1, String FCDDL, String ObjName2, String index,
			String AddorUpdate, String searchsku, String multiplier_index) {

		int index_add;
		int index_update;
		String str_index_update;
		String newElement;
		int ind = Integer.parseInt(index);

		if (AddorUpdate.equals("Add")) {
			index_add = Integer.parseInt(index);
			newElement = FCDDL.replace("nvalue", index);
		} else {
			index_update = Integer.parseInt(index);
			index_update = index_update + 1;
			str_index_update = Integer.toString(index_update);
			newElement = FCDDL.replace("nvalue", str_index_update);
		}

		testLog.info("integer value is: " + ind);

		boolean bool_espotflag = isElementPresent(OR_OR.getProperty("Liquor_Espot_Text"), "Liquor_Espot_Text");
		if (bool_espotflag == true) {
			testLog.info("Espot is present in tile 1, Working with tile 2");
		}

		switch (ind) {
		case 0:
			if (AddorUpdate.equals("Add")) {
				testLog.info("Entered 0 value - which means to tap on the FC item to add to trolley");

				if (bool_espotflag == true) {
					Click_Button_Xpath(OR_OR.getProperty("Tile2_FatController"), "Tile2_FatController");
				} else {
					Click_Button_Xpath(OR_OR.getProperty("Tile1_FatController"), "Tile1_FatController");
				}
			} else {
				driver.findElement(By.xpath(ElementFCDDI)).click();
				wait(3000);
				driver.findElement(By.xpath(newElement)).click();
				testLog.info("Attempted to remove the item from Trolley. Use remove function to clear the trolley");
			}
			break;
		case 1:
			driver.findElement(By.xpath(ElementFCDDI)).click();
			ExplicitWait(newElement, "newElement", 10);
			driver.findElement(By.xpath(newElement)).click();
			break;
		case 2:
			driver.findElement(By.xpath(ElementFCDDI)).click();
			ExplicitWait(newElement, "newElement", 10);
			driver.findElement(By.xpath(newElement)).click();
			break;
		case 3:
			driver.findElement(By.xpath(ElementFCDDI)).click();
			ExplicitWait(newElement, "newElement", 10);
			driver.findElement(By.xpath(newElement)).click();
			break;
		case 4:
			driver.findElement(By.xpath(ElementFCDDI)).click();
			ExplicitWait(newElement, "newElement", 10);
			driver.findElement(By.xpath(newElement)).click();
			break;
		default:
			driver.findElement(By.xpath(ElementFCDDI)).click();
			ExplicitWait(ElementFCDDI, "ElementFCDDI", 10);
			if (AddorUpdate.equals("Add")) {
				if (bool_espotflag == true) {
					driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Dropdown_Select5"))).click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Dropdown_Select5"))).click();
				}
			} else {
				if (bool_espotflag == true) {
					driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Dropdown_Select6"))).click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Dropdown_Select6"))).click();
				}
			}

			String fiveorMore = OR_OR.getProperty("Select5orMore");
			if (searchsku.endsWith("P")) {
				String search_term = searchsku.substring(0, searchsku.length() - 1);
				String newElement1 = fiveorMore.replace("sku", search_term);
				driver.findElement(By.xpath(newElement1)).clear();
				Sendkey_xpath(newElement1, multiplier_index, "Select5orMore");
			} else {
				String newElement2 = fiveorMore.replace("sku", searchsku);
				driver.findElement(By.xpath(newElement2)).clear();
				Sendkey_xpath(newElement2, multiplier_index, "Select5orMore");
			}

			if (AddorUpdate.equals("Add")) {
				if (bool_espotflag == true) {
					Click_Button_Xpath(OR_OR.getProperty("Tile2_Select5orMoreAddbutton"),
							"Tile2_Select5orMoreAddbutton");
				} else {
					Click_Button_Xpath(OR_OR.getProperty("Select5orMoreAddbutton"), "Select5orMoreAddbutton");
				}
			} else {
				if (bool_espotflag == true) {
					Click_Button_Xpath(OR_OR.getProperty("Tile2_Select5orMoreUpdatebutton"),
							"Tile2_Select5orMoreUpdatebutton");
				} else {
					Click_Button_Xpath(OR_OR.getProperty("Select5orMoreUpdatebutton"), "Select5orMoreUpdatebutton");
				}
			}
			break;
		}
		wait(2000);// added
	}

	// Function to add or update the FC for a normal product and verify details
	// reflecting in super bar & DC
	public void add_update_fatcontroller_verifyDC(String index, String addorupdate, String search_sku) {
		boolean bool2, bool3;
		String strfctext_1, strfcprice_1, strfcprice_2, strfcprice_4, strqty_1, strqty_2;
		String strrhstfcprice_1;
		double strdoublefcprice_1, strdoublefcprice_2, updated_strdoubleqty_2, strdoublefcprice_4,
				strrhstdoublefcprice_1;
		int strintqty_1, strintqty_2;
		int wt_multiplier = 1;
		String wt_multiplier_index;

		boolean bool_espotflag;
		if (isElementPresent(OR_OR.getProperty("Liquor_Espot_Text"), "Liquor_Espot_Text")
				|| isElementPresent(OR_OR.getProperty("Tobacco_Warning_Tile"), "Tobacco_Warning_Tile")) {
			bool_espotflag = true;
		} else {
			bool_espotflag = false;
		}
		if (bool_espotflag == true) {
			testLog.info("Espot is present in tile 1, Working with tile 2");
		}

		if (bool_espotflag == true) {
			strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_FatController"))).getText();
		} else {
			if (isElementPresent(OR_OR.getProperty("PDP1_FatController"), "PDP1_FatController")) {
				strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_FatController"))).getText();
			} else if (isElementPresent(OR_OR.getProperty("Tile1_FatController"), "Tile1_FatController")) {

				strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
			} else {
				strfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_FatController"))).getText();
			}

		}

		bool2 = strfctext_1.contains("Add");

		// to check if item is added in the cart. if true tick mark is visible
		if (isElementPresent(OR_OR.getProperty("PDP1_Price_ZeroCount"), "PDP1_Price_ZeroCount")) {
			bool3 = isElementPresent(OR_OR.getProperty("FCControl_Addicon_PDP"), "FCControl_Addicon_PDP");
		} else {
			bool3 = isElementPresent(OR_OR.getProperty("FCControl_Addicon"), "FCControl_Addicon");
		}

		if (bool2 == true || bool3 == false) {
			if (bool_espotflag == true) {
				strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Price_ZeroCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_Price_ZeroCount"), "PDP1_Price_ZeroCount")) {
					strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_Price_ZeroCount"))).getText();
				} else {
					strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ZeroCount"))).getText();
				}

			}
			strfcprice_1 = strfcprice_1.replaceAll("[^0-9,.]", "").trim();
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);

			strintqty_1 = 0;
		} else {
			if (bool_espotflag == true) {
				strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Price_ValidCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_Price_ValidCount"), "PDP1_Price_ValidCount")) {
					strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_Price_ValidCount"))).getText();
				} else {
					strfcprice_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
				}
			}
			strfcprice_1 = strfcprice_1.replaceAll("[^0-9,.]", "").trim();
			strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
			if (bool_espotflag == true) {
				strqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_qty_ValidCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_qty_ValidCount"), "PDP1_qty_ValidCount")) {
					strqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_qty_ValidCount"))).getText();
				} else {
					strqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
				}

			}
			strqty_1 = strqty_1.replaceAll("\\s+", "");
			strintqty_1 = Integer.parseInt(strqty_1);
			strdoublefcprice_1 = (double) Math.round((strdoublefcprice_1 / strintqty_1) * 100) / 100;
		}

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		String superbarqty_1, superbarqty_2;
		int superbarintqty_1, superbarintqty_2;

		if (Trolley_Total_Cost == 0.0) {
			superbarintqty_1 = 0;
		} else {
			superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
			superbarintqty_1 = Integer.parseInt(superbarqty_1);
		}

		int temp_intval = Integer.parseInt(index);
		wt_multiplier = wt_multiplier * temp_intval;
		wt_multiplier_index = Integer.toString(wt_multiplier);

		if (bool_espotflag == true) {

			click_FC_Select_Item(OR_OR.getProperty("Tile2_Dropdown"), "Tile2_Dropdown",
					OR_OR.getProperty("Tile2_Dropdown_SelectN"), "Tile2_Dropdown_SelectN", index, addorupdate,
					search_sku, wt_multiplier_index);
		} else {

			if (isElementPresent(OR_OR.getProperty("PDP1_Dropdown"), "PDP1_Dropdown")) {
				click_FC_Select_Item(OR_OR.getProperty("PDP1_Dropdown"), "PDP1_Dropdown",
						OR_OR.getProperty("PDP1_Dropdown_SelectN"), "PDP1_Dropdown_SelectN", index, addorupdate,
						search_sku, wt_multiplier_index);
			} else {
				click_FC_Select_Item(OR_OR.getProperty("Tile1_Dropdown"), "Tile1_Dropdown",
						OR_OR.getProperty("Tile1_Dropdown_SelectN"), "Tile1_Dropdown_SelectN", index, addorupdate,
						search_sku, wt_multiplier_index);
			}
		}

		wait(5000);
		// If the searched product is Alcohol/ Tobacco, then skip all below
		// steps
		if (bool_espotflag == false) {
			superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
			superbarintqty_2 = Integer.parseInt(superbarqty_2);

			if (bool_espotflag == true) {
				strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Price_ValidCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_Price_ValidCount"), "PDP1_Price_ValidCount")) {
					strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_Price_ValidCount"))).getText();
				} else {
					strfcprice_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
				}

			}
			strfcprice_2 = strfcprice_2.replaceAll("[^0-9,.]", "").trim();
			strdoublefcprice_2 = Double.parseDouble(strfcprice_2);

			if (bool_espotflag == true) {
				strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_qty_ValidCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_qty_ValidCount"), "PDP1_qty_ValidCount")) {
					strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_qty_ValidCount"))).getText();
				} else {
					strqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getText();
				}
			}
			strqty_2 = strqty_2.replaceAll("\\s+", "");
			strintqty_2 = Integer.parseInt(strqty_2);
			updated_strdoubleqty_2 = (double) Math.round(strintqty_2 * strdoublefcprice_1 * 100) / 100;

			if (superbarintqty_2 == (superbarintqty_1 + (strintqty_2 - strintqty_1))) {
				testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
						+ " to " + strintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
						+ superbarintqty_2);

			} else {
				testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from "
						+ strintqty_1 + " to " + strintqty_2
						+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1
						+ " to " + superbarintqty_2);
				assertCheck("add_update_fatcontroller_verifyDC",
						"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + strintqty_1
								+ " to " + strintqty_2
								+ " , the trolley quantity is not updated correctly and changed from "
								+ superbarintqty_1 + " to " + superbarintqty_2);
			}

			if (strdoublefcprice_2 == (updated_strdoubleqty_2)) {
				testLog.info("The FC value updated correctly to selected QTY: " + strintqty_2 + " and Price: "
						+ strdoublefcprice_2);

			} else {
				testLog.error("The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
						+ strdoublefcprice_2);
				assertCheck("add_update_fatcontroller_verifyDC",
						"The FC price is not udpated correctly when selecting QTY:" + strintqty_2 + " and price: "
								+ strdoublefcprice_2);
			}

			updated_Trolley_Total = Trolley_Total_Cost + ((strintqty_2 - strintqty_1) * strdoublefcprice_1);
			updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
			testLog.info("calculated cost: " + updated_Trolley_Total);

			Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
			testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);
			if (updated_Trolley_Total == Trolley_Total_Cost || (Trolley_Total_Cost == (updated_Trolley_Total + (0.01)))
					|| (Trolley_Total_Cost == (updated_Trolley_Total - (0.01)))) {
				testLog.info("Updated Trolley Total after adding item with cost " + strdoublefcprice_1 + " is "
						+ updated_Trolley_Total);

			} else {
				testLog.error(
						"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
				assertCheck("add_update_fatcontroller_verifyDC",
						"Updated Trolley Total after adding 1 item to trolley is not matching with the calculate price");
			}

			/*
			 * *****************************************************************
			 * ************ **
			 */

			String pName, onlineSD;
			if (bool_espotflag == true) {
				pName = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_ProductName"))).getText();
				onlineSD = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_OnlineSD"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("Product_Name_PDPage"), "Product_Name_PDPage")) {
					pName = driver.findElement(By.xpath(OR_OR.getProperty("Product_Name_PDPage"))).getText();
				} else {
					pName = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_ProductName"))).getText();
				}

				onlineSD = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_OnlineSD"))).getText();
			}
			if (onlineSD.contains(" ")) {
				onlineSD = (onlineSD.substring(0, onlineSD.indexOf(" "))).trim();
			}
			String space = " ";
			String pName1 = pName.concat(space).concat(onlineSD);
			testLog.info("Product name extracted from LHS product tile is :" + pName1);

			if (bool_espotflag == true) {
				strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("Tile2_Price_ValidCount"))).getText();
			} else {
				if (isElementPresent(OR_OR.getProperty("PDP1_Price_ValidCount"), "PDP1_Price_ValidCount")) {
					strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("PDP1_Price_ValidCount"))).getText();
				} else {
					strfcprice_4 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getText();
				}

			}
			strfcprice_4 = strfcprice_4.replaceAll("[^0-9,.]", "").trim();
			strdoublefcprice_4 = Double.parseDouble(strfcprice_4);

			Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
			wait(1000);
			By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
			java.util.List<WebElement> listchanges = driver.findElements(getChanges);

			String rHSTpName;
			if (listchanges.size() == 2) {
				testLog.info("only 1 element is present in the rhst");
				wait(1000);
				rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_Only1element")))
						.getText();
				testLog.info(rHSTpName);
			} else {
				testLog.info("Multiple elements are prenset in the rhst");

				rHSTpName = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_ProductName_MoreElements")))
						.getText();
			}
			testLog.info("The product name added in RHS Trolley is: " + rHSTpName);
			testLog.info("Pname is " + pName1 + "and" + "RSHST is " + rHSTpName);

			if (rHSTpName.contains(pName1)) {
				testLog.info(
						"The product name in FatController & the 1st item displayed in RHST are same as: " + pName1);
			} else {
				testLog.error("The product added last is not appearing at the first position in RHST");
				assertCheck("add_update_fatcontroller_verifyDC",
						"The product added last is not appearing at the first position in RHST");
			}

			String rHSTFC;
			if (listchanges.size() == 2) {
				rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price"))).getText();
			} else {
				rHSTFC = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price"))).getText();
			}

			wait(1000);
			strrhstfcprice_1 = rHSTFC.replaceAll("[^0-9,.]", "").trim();
			strrhstdoublefcprice_1 = Double.parseDouble(strrhstfcprice_1);

			if (strdoublefcprice_4 == strrhstdoublefcprice_1) {
				testLog.info("The cost added for FC and RHST DC - both are same with value: " + strrhstdoublefcprice_1);

			} else {
				testLog.error("The cost added for FC and RHST DC - both are not same with values- FC: "
						+ strdoublefcprice_4 + " and DC: " + strrhstdoublefcprice_1);
				assertCheck("add_update_fatcontroller_verifyDC",
						"The cost added for FC and RHST DC - both are not same with values- FC: " + strdoublefcprice_4
								+ " and DC: " + strrhstdoublefcprice_1);
			}
			// wait(2000);
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		}
	}

	// Function to update a product quantity in RHST and verify the same in the
	// product tile
	public void update_dietcontroller_verifyFC(String index, String search_sku) {
		String superbarqty_1, superbarqty_2 = null, rhststrfctext_1, rhststrfcprice_1, rhststrqty_1;
		int superbarintqty_1, superbarintqty_2 = 0, rhststrintqty_1, lhsstrintqty_1, rhststrintqty_2;
		double rhststrdoublefcprice_1, rhststrdoublefcpriceqty_1, strlhsdoublefcprice_1, rhststrdoublefcprice_2,
				updated_rhststrdoubleqty_2;
		String rhststrfctext_2, rhststrfcprice_2, rhststrqty_2, lhsFC, strlhsfcprice_1, lhsstrqty_1;
		String dd_newElement1, dd_newElement2;

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		wait(3000);
		superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		superbarqty_1 = superbarqty_1.replaceAll("\\s+", "");
		superbarintqty_1 = Integer.parseInt(superbarqty_1);

		String dropdown_product = OR_OR.getProperty("RHST_Tile1_DropdownIcon_Only1Element");
		if (search_sku.endsWith("P")) {
			String search_term = search_sku.substring(0, search_sku.length() - 1);
			dd_newElement1 = dropdown_product.replace("sku", search_term);
		} else {
			dd_newElement1 = dropdown_product.replace("sku", search_sku);
		}

		By getChanges = By.xpath(OR_OR.getProperty("DropDown_Icons_Count"));
		java.util.List<WebElement> listchanges = driver.findElements(getChanges);
		String searchsku = search_sku;
		int ind = Integer.parseInt(index);

		if (listchanges.size() == 2) {
			testLog.info("only 1 element is present in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
			wait(1000);
		} else {
			testLog.info("Multiple elements are prenset in the rhst");

			rhststrfctext_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
					.getText();
			rhststrqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty"))).getText();
			driver.findElement(By.xpath(dd_newElement1)).click();
			wait(1000);
		}

		rhststrfcprice_1 = rhststrfctext_1.replaceAll("[^0-9,.]", "").trim();
		rhststrdoublefcprice_1 = Double.parseDouble(rhststrfcprice_1);

		rhststrqty_1 = rhststrqty_1.replaceAll("\\s+", "");
		rhststrintqty_1 = Integer.parseInt(rhststrqty_1);
		testLog.info("qty before adding to trolley on fc is: " + rhststrintqty_1);

		rhststrdoublefcpriceqty_1 = (double) Math.round((rhststrdoublefcprice_1 / rhststrintqty_1) * 100) / 100;

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost before adding item to trolley: " + Trolley_Total_Cost);

		switch (ind) {
		case 0:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select0"))).click();
			break;
		case 1:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select1"))).click();
			break;
		case 2:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select2"))).click();
			break;
		case 3:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select3"))).click();
			break;
		case 4:
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select4"))).click();
			break;
		default:
			wait(2000);
			driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore"))).click();

			String fiveorMore = OR_OR.getProperty("Select5orMore");
			if (searchsku.endsWith("P")) {
				String search_term = searchsku.substring(0, searchsku.length() - 1);
				String newElement1 = fiveorMore.replace("sku", search_term);
				driver.findElement(By.xpath(newElement1)).clear();
				wait(2000);
				Sendkey_xpath(newElement1, index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			} else {
				String newElement2 = fiveorMore.replace("sku", searchsku);
				driver.findElement(By.xpath(newElement2)).clear();
				wait(2000);
				Sendkey_xpath(newElement2, index, "Select5orMore");
				if (listchanges.size() == 2) {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element")))
							.click();
				} else {
					driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_MoreElements")))
							.click();
				}
			}
			break;

		}

		// Code to get quantity & price, trolley price after updating the
		// quantity

		if (ind == 0) {
			rhststrintqty_2 = 0;
			rhststrdoublefcprice_2 = 0.00;
			wait(4000);
		} else {
			if (listchanges.size() == 2) {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_Only1Element_qty")))
						.getText();
			} else {
				wait(3000);
				rhststrfctext_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price")))
						.getText();
				rhststrqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_qty")))
						.getText();
			}

			rhststrfcprice_2 = rhststrfctext_2.replaceAll("[^0-9,.]", "").trim();
			rhststrdoublefcprice_2 = Double.parseDouble(rhststrfcprice_2);
			rhststrqty_2 = rhststrqty_2.replaceAll("\\s+", "");
			rhststrintqty_2 = Integer.parseInt(rhststrqty_2);
		}

		testLog.info("debug msg 1");
		boolean boolvar = isElementPresent(OR_OR.getProperty("Trolley_Count"), "Trolley_Count");
		if (boolvar == true) {
			superbarqty_2 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			if (superbarqty_2.matches("[0-9]+")) {
				superbarqty_2 = superbarqty_2.replaceAll("\\s+", "");
				superbarintqty_2 = Integer.parseInt(superbarqty_2);
			} else {
				superbarintqty_2 = 0;
			}
		} else {
			superbarintqty_2 = 0;
		}

		updated_rhststrdoubleqty_2 = (double) Math.round(rhststrintqty_2 * rhststrdoublefcpriceqty_1 * 100) / 100;

		if (superbarintqty_2 == (superbarintqty_1 + (rhststrintqty_2 - rhststrintqty_1))) {
			testLog.info("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
					+ " to " + rhststrintqty_2 + " , the trolley quantity is updated from " + superbarintqty_1 + " to "
					+ superbarintqty_2);

		} else {
			testLog.error("Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from "
					+ rhststrintqty_1 + " to " + rhststrintqty_2
					+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1 + " to "
					+ superbarintqty_2);
			assertCheck("update_dietcontroller_verifyFC",
					"Initial Quantity is: " + superbarintqty_1 + ". After updating the qty from " + rhststrintqty_1
							+ " to " + rhststrintqty_2
							+ " , the trolley quantity is not updated correctly and changed from " + superbarintqty_1
							+ " to " + superbarintqty_2);

		}

		if (rhststrdoublefcprice_2 == (updated_rhststrdoubleqty_2)) {
			testLog.info("The FC value updated correctly to selected QTY: " + rhststrintqty_2 + " and Price: "
					+ rhststrdoublefcprice_2);

		} else {
			testLog.error("The FC price is not udpated correctly when selecting QTY:" + rhststrintqty_2 + " and price: "
					+ rhststrdoublefcprice_2);
			assertCheck("update_dietcontroller_verifyFC", "The FC price is not udpated correctly when selecting QTY:"
					+ rhststrintqty_2 + " and price: " + rhststrdoublefcprice_2);
		}

		updated_Trolley_Total = Trolley_Total_Cost + ((rhststrintqty_2 - rhststrintqty_1) * rhststrdoublefcpriceqty_1);
		updated_Trolley_Total = (double) Math.round(updated_Trolley_Total * 100) / 100;
		testLog.info("calculated cost: " + updated_Trolley_Total);

		double updated_Trolley_Total_1 = (double) Math.round((updated_Trolley_Total + 0.01) * 10) / 10;
		double updated_Trolley_Total_2 = (double) Math.round((updated_Trolley_Total - 0.01) * 10) / 10;

		Trolley_Total_Cost = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();

		testLog.info("Trolley Cost after adding item to trolley: " + Trolley_Total_Cost);

		if (updated_Trolley_Total == Trolley_Total_Cost || (Trolley_Total_Cost == (updated_Trolley_Total + (0.01)))
				|| (Trolley_Total_Cost == (updated_Trolley_Total - (0.01)))
				|| updated_Trolley_Total_1 == Trolley_Total_Cost || updated_Trolley_Total_2 == Trolley_Total_Cost) {
			testLog.info("Updated Trolley Total after adding item with cost " + rhststrdoublefcprice_1 + " is "
					+ updated_Trolley_Total);

		} else {
			testLog.error(
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
			assertCheck("update_dietcontroller_verifyFC",
					"Updated Trolley Total after adding item to trolley is not matching with the calculate price");
		}

		Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");

		if (ind == 0) {
			String str11 = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_FatController"))).getText();
			boolean b1 = str11.contains("Add");
			if (b1 == true) {
				testLog.info(
						"This product is no more added in the trolley and verified from the Fat controller of the product tile");

			} else {
				testLog.error(
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
				assertCheck("update_dietcontroller_verifyFC",
						"After removing this product from trolley, the quantity is not correctly updated in the Fat Controller of the product tile");
			}
		} else {
			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_Price_ValidCount"))).getAttribute("innerHTML");
			strlhsfcprice_1 = lhsFC.replaceAll("[^0-9,.]", "").trim();
			strlhsdoublefcprice_1 = Double.parseDouble(strlhsfcprice_1);

			lhsFC = driver.findElement(By.xpath(OR_OR.getProperty("Tile1_qty_ValidCount"))).getAttribute("innerHTML");
			lhsstrqty_1 = lhsFC.replaceAll("\\s+", "");
			lhsstrintqty_1 = Integer.parseInt(lhsstrqty_1);

			if ((strlhsdoublefcprice_1 == rhststrdoublefcprice_2) & (lhsstrintqty_1 == rhststrintqty_2)) {
				testLog.info("The cost added for FC and RHST DC - both are same with Qty: " + lhsstrintqty_1
						+ " and Price: " + strlhsdoublefcprice_1);

			} else {
				testLog.error("The cost added for FC and RHST DC - both are not same with values- FC Price: "
						+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
				assertCheck("update_dietcontroller_verifyFC",
						"The cost added for FC and RHST DC - both are not same with values- FC Price: "
								+ strlhsdoublefcprice_1 + " and DC Price: " + rhststrdoublefcprice_2);
			}
		}
	}

	public HomePage enterSearchItem(String item) {
		wait(4000);
		Sendkey_id(OR_OR.getProperty("Search_Input_Box"), item, "Search_Input_Field");
		return this;
	}

	public HomePage clickSearchButton() {
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Search_Button"), "Search_Button");
		ExplicitWait(OR_OR.getProperty("Clear_Search_Link"), "Clear_Search_Link", 20);
		// wait(3000);
		ExplicitWait(OR_OR.getProperty("Show_Filter_Btn_OR_No_Result_Found"), "Show_Filter_Btn_OR_No_Result_Found", 20);
		return this;
	}

	public HomePage validateInvalidProductMsg() {
		verify_xpath_text(OR_OR.getProperty("Invalid_Search_Msg"),
				OR_OR.getProperty("Invalid_Search_Msg_Text") + "'" + search_1 + "'");
		return this;
	}

	public HomePage validatePageTitle() {
		String title = driver.getTitle();
		if (title.contentEquals("Coles Online")) {
			testLog.info("Title is Coles Online");
		} else {
			testLog.error("Title is incorrect");
			assertCheck("validatePageTitle", "Title is incorrect");
		}
		return this;
	}

	public HomePage validateColesLogo() {
		isElementPresent(OR_OR.getProperty("Coles_Logo"), "Coles_Logo");
		return this;
	}

	public HomePage validateHomeIcon() {
		isElementPresent(OR_OR.getProperty("Home_icon"), "Home_icon");
		return this;
	}

	public HomePage validateSearchBoxText() {
		verify_xpath_text(OR_OR.getProperty("Home_SearchField_InsideText_Path"),
				OR_OR.getProperty("Home_SearchField_InsideText"));
		return this;
	}

	public HomePage validateEverythingText() {
		verify_xpath_text(OR_OR.getProperty("Everything"), "Everything");
		return this;
	}

	public HomePage validateBoughtBeforeText() {
		verify_xpath_text(OR_OR.getProperty("Bought-before"), "Bought Before");
		return this;
	}

	public HomePage validateSpecialText() {
		verify_xpath_text(OR_OR.getProperty("Special"), "Specials");
		return this;
	}

	public HomePage validateMorebutton() {
		verify_xpath_text(OR_OR.getProperty("More"), "More");
		Click_Button_Xpath(OR_OR.getProperty("More"), "More");
		verify_xpath_text(OR_OR.getProperty("More_Open_New_Tap"), "Open a new tab");
		verify_xpath_text(OR_OR.getProperty("More_Shop_Product_Selection"), "Shop Product Selections");
		return this;
	}

	public HomePage validateSearchListText() {
		isElementPresent(OR_OR.getProperty("Search_List_Item"), "Search_List_Item");
		return this;
	}

	public HomePage validateTrolleyText() {
		verify_xpath_contains_text(OR_OR.getProperty("Trolley_doller"), "$");
		verify_xpath_text(OR_OR.getProperty("Trolley_Text"), "Trolley and checkout");
		return this;
	}

	public HomePage validateDeliveryTimeText() {
		verify_xpath_text(OR_OR.getProperty("Delivery_Time"), "Choose a delivery time");
		return this;
	}

	public HomePage validateLoginText() {
		verify_xpath_text(OR_OR.getProperty("Login/Signup"), "Log in / Signup");
		return this;
	}

	public HomePage validateHelpText() {
		verify_xpath_text(OR_OR.getProperty("Help/Support"), "Help / Support");
		return this;
	}

	public HomePage validateResultText(String item) {
		if (item.toLowerCase().contains(item)) {
			ExplicitWait(OR_OR.getProperty("Results_For"), "Results_For", 5);
			verify_xpath_text(OR_OR.getProperty("Results_For"), "Results for " + "\'" + search_1 + "\'");
		} else {
			ExplicitWait(OR_OR.getProperty("Results_For"), "Results_For", 5);
			verify_xpath_text(OR_OR.getProperty("Results_For"), "Results for " + "\'" + search_2 + "\'");
		}
		return this;
	}

	public HomePage clickBoughtBeforeTab() {
		isElementPresent(OR_OR.getProperty("Bought-before"), "Bought-before");
		Click_Button_Xpath(OR_OR.getProperty("Bought-before"), "Bought-before");
		return this;
	}

	public HomePage validateBestBeforeMsg() {
		ExplicitWait(OR_OR.getProperty("Best_Before_Msg"), "Best_Before_Msg", 5);
		verify_xpath_text(OR_OR.getProperty("Best_Before_Msg"), OR_OR.getProperty("Best_Before_Msg_Text"));
		return this;
	}

	public HomePage validateEverythingCount() {
		int tabcounter = get_Tab_Counter(OR_OR.getProperty("Everything_Tab_Counter"), "Everything_Tab_Counter");
		testLog.info("Total count of searched product present in Everything is :" + tabcounter);
		String Search_Tile1 = driver.findElement(By.xpath(OR_OR.getProperty("Search_Tile1"))).getText();
		if (Search_Tile1.contains("milk")) {
			testLog.info("Searched product is milk");
		}
		return this;
	}

	public HomePage clickClearSearch() {
		wait(3000);
		Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button");
		return this;
	}

	public HomePage clickProduct() {
		Click_Button_Xpath(OR_OR.getProperty("Search_Tile1"), "Search_Tile1");
		return this;
	}

	public HomePage validatePrductCode() {
		verify_xpath_text(OR_OR.getProperty("Product_Page_sku"), search_2);
		return this;
	}

	public HomePage clickBacktoProduct() {
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Back_To_Products"), "Back_To_Products");
		return this;

	}

	public HomePage clickSpecialTab() {
		Click_Button_Xpath(OR_OR.getProperty("Special"), "Special");
		wait(5000);
		return this;
	}

	public HomePage clickChangeButton() {
		ExplicitWait(OR_OR.getProperty("Specials_Sortedby_ChangeLink"), "Specials_Sortedby_ChangeLink", 20);
		Click_Button_Xpath(OR_OR.getProperty("Specials_Sortedby_ChangeLink"), "Specials_Sortedby_ChangeLink");
		return this;
	}

	public HomePage validateSpecialHeader() {
		ExplicitWait(OR_OR.getProperty("Special_header"), "Special_header", 5);
		verify_xpath_text(OR_OR.getProperty("Special_header"), "Sort options for 'Specials'");
		return this;
	}

	public HomePage validateSpecialLowestPriceText() {
		verify_xpath_text(OR_OR.getProperty("Special_Lowest_Price"), "Lowest price");
		return this;
	}

	public HomePage clickSpecialLowestPrice() {
		Click_Button_Xpath(OR_OR.getProperty("Special_Lowest_Price_Checkbox"), "Special_Lowest_Price_Checkbox");
		isElementSelected(OR_OR.getProperty("Special_Lowest_Price_Checkbox"));
		return this;
	}

	public HomePage validateSpecialBrandAtoZText() {
		verify_xpath_text(OR_OR.getProperty("Special_Brand_AtoZ_RadioBtn"), "Brand (A-Z)");
		return this;
	}

	public HomePage clickSpecialBrandAtoZ() {
		Click_Button_Xpath(OR_OR.getProperty("Special_Brand_AtoZ_Checkbox"), "Special_Brand_AtoZ_Checkbox");
		isElementSelected(OR_OR.getProperty("Special_Brand_AtoZ_Checkbox"));
		return this;
	}

	public HomePage validateSpecialLowestUnitText() {
		verify_xpath_text(OR_OR.getProperty("Special_Lowest_Unit_Price"), "Lowest unit price");
		return this;
	}

	public HomePage validateTopProdText() {
		verify_xpath_text(OR_OR.getProperty("Special_header"), "Sort options for 'Specials'");
		verify_xpath_text(OR_OR.getProperty("Special_Top_Products"), "Top products");
		isElementSelected(OR_OR.getProperty("Special_Top_Product_Checkbox"));
		return this;
	}

	public HomePage clickSpecialSave() {
		Click_Button_Xpath(OR_OR.getProperty("Special_Save_Button"), "Special_Save_Button");
		checkWebPageIsReady();
		// wait(5000);
		return this;
	}

	public HomePage validateSpecailSortByText(String options) {

		switch (options) {
		case "topProducts":
			verify_xpath_text(OR_OR.getProperty("Specials_Sorted_By"),
					"Products on special are displayed below and are Sorted by: top products");
			break;
		case "lowestUnitPrice":
			verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
					"Products on special are displayed below and are Sorted by: lowest unit price");
			break;
		case "lowestPrice":
			verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
					"Products on special are displayed below and are Sorted by: lowest price");
			break;
		default:
			testLog.info("Products on special header is not displayed");
			break;
		}
		return this;
	}

	// public HomePage validateSpecailTopProductSortByText() {
	// verify_xpath_text(OR_OR.getProperty("Specials_Sorted_By"),
	// "Products on special are displayed below and are Sorted by: top
	// products");
	//
	// return this;
	// }

	public HomePage specialLowestUnitPrice() {
		verify_xpath_text(OR_OR.getProperty("Special_header"), "Sort options for 'Specials'");
		verify_xpath_text(OR_OR.getProperty("Special_Lowest_Unit_Price"), "Lowest unit price");
		return this;
	}

	public HomePage clickSpecialLowestUnitPrice() {
		Click_Button_Xpath(OR_OR.getProperty("Special_Lowest_Unit_Checkbox"), "Special_Lowest_Unit_Checkbox");
		isElementSelected(OR_OR.getProperty("Special_Lowest_Unit_Checkbox"));
		return this;
	}

	public HomePage validateSpecialUnitPriceSortByText() {
		verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
				"Products on special are displayed below and are Sorted by: lowest unit price");
		return this;
	}

	public HomePage validateSplUnitPriceSorting() {
		String tab_up_nval = OR_OR.getProperty("SpecialTab_Search_UnitPrice_N");
		int tabcounter = getXpathCount(OR_OR.getProperty("Special_All_Items"), "Special_All_Items");
		testLog.info("tabcounter is : " + tabcounter);
		int loop_count;
		if (tabcounter >= 6) {
			loop_count = 5;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			wait(3000);
			String up = tab_up_nval;
			String up_1 = up.replace("nvalue", istrvalue);
			String up_2 = up.replace("nvalue", jstrvalue);

			String str_up_1 = driver.findElement(By.xpath(up_1)).getText();
			String str_up_2 = driver.findElement(By.xpath(up_2)).getText();

			if (str_up_1 != null && !str_up_1.isEmpty()) {
				str_up_1 = str_up_1.substring(str_up_1.lastIndexOf("$") + 1);
				if (str_up_1.contains(" ")) {
					String[] str_up = str_up_1.split(" ");
					if (str_up[str_up.length - 1].contains("100G") || str_up[str_up.length - 1].contains("100mL")) {
						double amount = Double.valueOf(str_up[0]) * 10;
						str_up_1 = String.valueOf(amount);
					} else {
						str_up_1 = str_up_1.substring(0, str_up_1.indexOf(" "));
					}
				}
			} else {
				str_up_1 = "0";
				testLog.error("No unit price is displayed for product at " + istrvalue + " position");
				assertCheck("validateSplUnitPriceSorting",
						"No unit price is displayed for product at " + istrvalue + " position");
			}
			if (str_up_2 != null && !str_up_2.isEmpty()) {
				str_up_2 = str_up_2.substring(str_up_2.lastIndexOf("$") + 1);
				if (str_up_2.contains(" ")) {
					String[] str_up = str_up_2.split(" ");
					if (str_up[str_up.length - 1].contains("100G") || str_up[str_up.length - 1].contains("100mL")) {
						double amount = Double.valueOf(str_up[0]) * 10;
						str_up_2 = String.valueOf(amount);
					} else {
						str_up_2 = str_up_2.substring(0, str_up_2.indexOf(" "));
					}
				}
			} else {
				str_up_2 = "0";
				testLog.error("No unit price is displayed for product at " + jstrvalue + " position");
				assertCheck("validateSplUnitPriceSorting",
						"No unit price is displayed for product at " + jstrvalue + " position");
			}
			double doub_up_1 = Double.valueOf(str_up_1);
			double doub_up_2 = Double.valueOf(str_up_2);

			if (doub_up_1 <= doub_up_2) {
				testLog.info("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);

			} else {
				testLog.warn("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is not less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);
				assertCheck("validateSplUnitPriceSorting", "The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is not less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);
			}
		}
		return this;
	}

	public HomePage validateLowestPriceSorting(String tab_name) {
		int tabcounter = 0;
		if (tab_name.equalsIgnoreCase("EverythingTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
		} else if (tab_name.equalsIgnoreCase("SpecialTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Special_All_Items"), "Special_All_Items");
		} else if (tab_name.equalsIgnoreCase("Shopping_List")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter"),
					"Shopping_List_ProductTile_xpath_Counter");
		}

		String nth_itemprice = OR_OR.getProperty("Tile_N_Price_ValidCount1");

		int loop_count;
		if (tabcounter >= 12) {
			loop_count = 11;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {

			boolean bool1 = false;

			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(ivalue + 1);
			String item1_price = nth_itemprice.replace("nvalue", istrvalue);
			String item2_price = nth_itemprice.replace("nvalue", jstrvalue);
			boolean price_flag1 = isElementPresent(item1_price, "item1_price");
			boolean price_flag2 = isElementPresent(item2_price, "item2_price");

			if (price_flag1 == false && bool1 == false || price_flag2 == false && bool1 == false) {
				testLog.warn("Product price is not present for tile at position: " + ivalue);
				continue;
			} else {
				bool1 = true;
				if (price_flag2 == true) {
					String strfcprice_1 = driver.findElement(By.xpath(item1_price)).getText();
					String strfcprice_2 = driver.findElement(By.xpath(item2_price)).getText();
					// strfcprice_1 = strfcprice_1.replace(",", "");
					// strfcprice_1 =
					// strfcprice_1.substring(strfcprice_1.lastIndexOf("$") +
					// 1);
					strfcprice_1 = strfcprice_1.replaceAll("[^.0-9]", "");
					// strfcprice_2 = strfcprice_2.replace(",", "");
					// strfcprice_2 =
					// strfcprice_2.substring(strfcprice_2.lastIndexOf("$") +
					// 1);
					strfcprice_2 = strfcprice_2.replaceAll("[^.0-9]", "");
					double strdoublefcprice_1 = Double.parseDouble(strfcprice_1);
					double strdoublefcprice_2 = Double.parseDouble(strfcprice_2);

					if (strdoublefcprice_1 <= strdoublefcprice_2) {
						testLog.info("The tile no." + ivalue + " with unit price: " + strdoublefcprice_1
								+ " is less than or equal to the tile no." + jstrvalue + " with unit price: "
								+ strdoublefcprice_2);
					} else {
						testLog.error("The tile no." + ivalue + " with unit price: " + strdoublefcprice_1
								+ " is not less than or equal to the tile no." + jstrvalue + " with unit price: "
								+ strdoublefcprice_2);
						assertCheck("validateLowestPriceSorting",
								"The tile no." + ivalue + " with unit price: " + strdoublefcprice_1
										+ " is not less than or equal to the tile no." + jstrvalue
										+ " with unit price: ");

					}
				} else {
					testLog.error(
							"Product at position: " + ivalue + " and" + jstrvalue + "are not sorted by lowest price");
					assertCheck("validateLowestPriceSorting",
							"Product at position: " + ivalue + " and" + jstrvalue + "are not sorted by lowest price");
				}
			}
		}

		return this;
	}

	public HomePage validateSplLowestPriceSorting() {
		String tab_lp_dollar_nval = OR_OR.getProperty("SpecialTab_N_Dollar");
		String tab_lp_dollarcent_nval = OR_OR.getProperty("SpecialTab_N_DollarCent");
		int tabcounter = getXpathCount(OR_OR.getProperty("Special_All_Items"), "Special_All_Items");

		testLog.info("tabcounter is : " + tabcounter);
		int loop_count;
		if (tabcounter >= 6) {
			loop_count = 5;
		} else {
			loop_count = tabcounter - 1;
		}
		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			wait(3000);
			String lp = tab_lp_dollar_nval;
			String lp_1 = lp.replace("nvalue", istrvalue);
			String lp_2 = lp.replace("nvalue", jstrvalue);

			String str_lp_1 = driver.findElement(By.xpath(lp_1)).getText();
			String str_lp_2 = driver.findElement(By.xpath(lp_2)).getText();

			String dc = tab_lp_dollarcent_nval;
			String dc_1 = dc.replace("nvalue", istrvalue);
			String dc_2 = dc.replace("nvalue", jstrvalue);

			String str_dc_1 = driver.findElement(By.xpath(dc_1)).getText();
			String str_dc_2 = driver.findElement(By.xpath(dc_2)).getText();

			if (str_lp_1 != null && !str_lp_1.isEmpty()) {
				testLog.info("Doller price is displayed for product at " + istrvalue + " position");
			} else {
				testLog.error("No doller price is displayed for product at " + istrvalue + " position");
				assertCheck("validateSplLowestPriceSorting",
						"No doller price is displayed for product at " + istrvalue + " position");
			}
			if (str_lp_2 != null && !str_lp_2.isEmpty()) {
				testLog.info("Doller price is displayed for product at " + istrvalue + " position");
			} else {
				str_lp_2 = "0";
				testLog.error("No doller price is displayed for product at " + istrvalue + " position");
				assertCheck("validateSplLowestPriceSorting",
						"No doller price is displayed for product at " + istrvalue + " position");
			}

			int int_lp_1 = Integer.valueOf(str_lp_1);
			int int_lp_2 = Integer.valueOf(str_lp_2);

			double doub_lp_1 = Double.valueOf(str_dc_1);
			double doub_lp_2 = Double.valueOf(str_dc_2);
			if (int_lp_1 < int_lp_2) {
				testLog.info("The tile no." + ivalue + " with doller price: " + int_lp_1
						+ " is less than to the tile no." + jvalue + " with doller price: " + int_lp_2);
			} else if (int_lp_1 == int_lp_2) {
				testLog.info("The tile no." + ivalue + " with doller price: " + int_lp_1 + " is equal to the tile no."
						+ jvalue + " with doller price: " + int_lp_2);
				testLog.info("Doller values are equal so taking cent value");
				if (doub_lp_1 <= doub_lp_2) {
					testLog.info("The tile no." + ivalue + " with Cent price: " + doub_lp_1
							+ " is less than to the tile no." + jvalue + " with Cent price: " + doub_lp_2);
				}
			} else {
				testLog.warn("The tile no." + ivalue + " with doller price: " + int_lp_1
						+ " is not less than or equal to the tile no." + jvalue + " with doller price: " + int_lp_2);
				assertCheck("validateSplLowestPriceSorting", "The tile no." + ivalue + " with doller price: " + int_lp_1
						+ " is not less than or equal to the tile no." + jvalue + " with doller price: " + int_lp_2);
			}
		}
		return this;
	}

	public HomePage clickEverythingTab() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Everything_Tab"), "Everything_Tab");
		Click_Button_Xpath(OR_OR.getProperty("Everything_Tab"), "Everything_Tab");
		ExplicitWait(OR_OR.getProperty("Browse_By_Category_Level_0"), "Browse_By_Category_Level_0", 2);
		return this;
	}

	public HomePage validateEverythingUnitPriceSorting(String tab_name) {
		String tab_up_nval = null;

		if (tab_name.equalsIgnoreCase("EverythingTab")) {
			tab_up_nval = OR_OR.getProperty("EverythingTab_UnitPrice_N");

		} else if (tab_name.equalsIgnoreCase("SpecialTab")) {
			tab_up_nval = OR_OR.getProperty("SpecialTab_Search_UnitPrice_N");
		}

		int tabcounter = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
		testLog.info("tabcounter is : " + tabcounter);
		int loop_count;
		if (tabcounter >= 6) {
			loop_count = 5;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			wait(3000);
			String up = tab_up_nval;
			String up_1 = up.replace("nvalue", istrvalue);
			String up_2 = up.replace("nvalue", jstrvalue);

			String str_up_1 = driver.findElement(By.xpath(up_1)).getText();
			String str_up_2 = driver.findElement(By.xpath(up_2)).getText();

			if (str_up_1 != null && !str_up_1.isEmpty()) {
				str_up_1 = str_up_1.substring(str_up_1.lastIndexOf("$") + 1);
				if (str_up_1.contains(" ")) {
					str_up_1 = str_up_1.substring(0, str_up_1.indexOf(" "));
				}
			} else {
				str_up_1 = "0";
				testLog.warn("No unit price is displayed for product at " + istrvalue + " position");
			}
			if (str_up_2 != null && !str_up_2.isEmpty()) {
				str_up_2 = str_up_2.substring(str_up_2.lastIndexOf("$") + 1);
				if (str_up_2.contains(" ")) {
					str_up_2 = str_up_2.substring(0, str_up_2.indexOf(" "));
				}
			} else {
				str_up_2 = "0";
				testLog.warn("No unit price is displayed for product at " + jstrvalue + " position");
			}
			double doub_up_1 = Double.valueOf(str_up_1);
			double doub_up_2 = Double.valueOf(str_up_2);

			if (doub_up_1 <= doub_up_2) {
				testLog.info("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);
			} else {
				testLog.error("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is not less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);
				assertCheck("validateEverythingUnitPriceSorting",
						"The tile no." + ivalue + " with unit price: " + doub_up_1
								+ " is not less than or equal to the tile no." + jvalue + " with unit price: "
								+ doub_up_2);
			}
		}
		return this;
	}

	public HomePage clickRandomLHSCategory() {

		List<WebElement> totalCategories = driver.findElements(By.xpath(OR_OR.getProperty("LHS_Count")));
		int count = totalCategories.size();
		Random rand = new Random();

		int random_cat = 1 + rand.nextInt(count);

		String val = Integer.toString(random_cat);
		String xp = OR_OR.getProperty("Highlighted_Cat_Name");
		xp = xp.replace("nvalue", val);
		String cat_name = driver.findElement(By.xpath(xp)).getText();
		if (cat_name.equals("Tobacco") || cat_name.equals("Liquor")) {
			clickRandomLHSCategory();

		} else {
			testLog.info("random category selected is:" + driver.findElement(By.xpath(xp)).getText());
			Click_Button_Xpath(xp, "LHS_category");
		}
		return this;
	}

	public HomePage clickLHSCategory(int index) {

		String val = Integer.toString(index);
		String xp = OR_OR.getProperty("Highlighted_Cat_Counter");
		xp = xp.replace("nvalue", val);

		Click_Button_Xpath(xp, "LHS_category");
		wait(5000);
		return this;
	}

	public int getCategoryCount(int index) {

		String val = Integer.toString(index);
		String xp = OR_OR.getProperty("Highlighted_Cat_Counter");
		xp = xp.replace("nvalue", val);

		String scount = get_xpath_text(xp, "Highlighted_Cat_Counter");

		int count = Integer.parseInt(scount);

		return count;
	}

	public HomePage clickEverythingChangeButton() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Everything_Sortedby_ChangeLink"), "Everything_Sortedby_ChangeLink");

		return this;
	}

	public HomePage clickSortOption(String tab, String sort_option) {
		int val = 0;
		if (tab.equalsIgnoreCase("EverythingTab")) {
			val = 1;
		} else if (tab.equalsIgnoreCase("BBTab")) {
			val = 2;
		} else if (tab.equalsIgnoreCase("SpecialTab")) {
			val = 3;
		}
		wait(2000);
		switch (val) {
		case 1:
			if (sort_option.equalsIgnoreCase("TopProducts")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Top_Products"), "Everything_Top_Products");
			} else if (sort_option.equalsIgnoreCase("special")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_On_Special"), "Everything_On_Special");
			} else if (sort_option.equalsIgnoreCase("lowest unit price")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Lowest_Unit_Price"), "Everything_Lowest_Unit_Price");
			} else if (sort_option.equalsIgnoreCase("lowest price")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Lowest_Price"), "Everything_Lowest_Price");
			} else if (sort_option.equalsIgnoreCase("Brand(A-Z)")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Brand"), "Everything_Brand");
			} else if (sort_option.equalsIgnoreCase("Product name (A-Z)")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Product_Name"), "Everything_Product_Name");
			}
			break;
		case 2:
			if (sort_option.equalsIgnoreCase("special")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_On_Special"), "Everything_On_Special");
			} else if (sort_option.equalsIgnoreCase("Product name (A-Z)")) {
				Click_Button_Xpath(OR_OR.getProperty("Specials_Product_Name"), "Specials_Product_Name");
			}
			break;
		case 3:
			if (sort_option.equalsIgnoreCase("Product name (A-Z)")) {
				ExplicitWait(OR_OR.getProperty("Specials_Product_Name"), "Specials_Product_Name", 8);
				Click_Button_Xpath(OR_OR.getProperty("Specials_Product_Name"), "Specials_Product_Name");
			} else if (sort_option.equalsIgnoreCase("Brand(A-Z)")) {
				Click_Button_Xpath(OR_OR.getProperty("Special_Brand_AtoZ_RadioBtn"), "Special_Brand_AtoZ_RadioBtn");
			} else if (sort_option.equalsIgnoreCase("TopProducts")) {
				ExplicitWait(OR_OR.getProperty("Specials_Product_TopProducts"), "Specials_Product_TopProducts", 8);
				Click_Button_Xpath(OR_OR.getProperty("Specials_Product_TopProducts"), "Specials_Product_TopProducts");
			}
			break;
		}

		return this;

	}

	public HomePage ClickEverythingSave() {
		Click_Button_Xpath(OR_OR.getProperty("Everything_Save_Button"), "Everything_Save_Button");
		ExplicitWait(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter", 7);

		return this;
	}

	public HomePage validateEverythingOnSpecialSort() {

		String tab_spl_bd_nval = OR_OR.getProperty("EverythingTab_Specials_Badge_nvalue");

		String productname_xpath_nvalue = OR_OR.getProperty("ProductName_xpath_nvalue");

		String Merch_Assoc_nvalue = OR_OR.getProperty("Everything_Merch_Assoc_nvalue");

		int product_count = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
		int loop_count = 0;

		if (product_count >= 6) {
			loop_count = 5;
		} else {
			loop_count = product_count - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			boolean m_assoc_flag = false;
			boolean pname_1_flag = false;
			boolean pname_2_flag = false;

			String m_assoc = Merch_Assoc_nvalue;
			m_assoc = m_assoc.replace("nvalue", istrvalue);
			m_assoc_flag = isElementPresent(m_assoc, "Merch_Assoc_nvalue");
			if (true == m_assoc_flag) {
				testLog.info("Merc association is present for this product. Skipping next/ associated product");
				jvalue = jvalue + 1;
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}

			String pname = productname_xpath_nvalue;
			String pname_1 = pname.replace("nvalue", istrvalue);
			String pname_2 = pname.replace("nvalue", jstrvalue);
			pname_1_flag = isElementNotPresent(pname_1, "productname_xpath_nvalue");
			if (true == pname_1_flag) {
				testLog.info("Product tile is not present at position: " + ivalue
						+ " due to wrapping of the merc association products appearing on last position");
				ivalue = ivalue + 1;
				jvalue = jvalue + 1;
				loop_count = loop_count + 1;
				istrvalue = Integer.toString(ivalue);
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}
			pname_2_flag = isElementNotPresent(pname_2, "productname_xpath_nvalue");
			if (true == pname_2_flag) {
				testLog.info("Product tile is not present at position: " + ivalue
						+ " due to wrapping of the merc association products appearing on last position");
				jvalue = jvalue + 1;
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}

			String sp_icon = tab_spl_bd_nval;
			String sp_icon_1 = sp_icon.replace("nvalue", istrvalue);
			String sp_icon_2 = sp_icon.replace("nvalue", jstrvalue);
			boolean boolval1 = isElementPresent(sp_icon_1, "Specials Icon");
			boolean boolval2 = isElementPresent(sp_icon_2, "Specials Icon");
			if (boolval1 == true) {
				testLog.info("Specials Icon is present for tile at position: " + ivalue);

			} else {
				testLog.warn("Specials Icon is not present for tile at position: " + ivalue);

			}
			if (boolval2 == true) {
				testLog.info("Specials Icon is present for tile at position: " + jvalue);

			} else {
				testLog.warn("Specials Icon is not present for tile at position: " + jvalue);

			}

			if (true == m_assoc_flag) {
				ivalue = ivalue + 1;
				loop_count = loop_count + 1;
			}
			if (true == pname_2_flag) {
				ivalue = ivalue + 1;
				loop_count = loop_count + 1;
			}

			// End of new code
		}

		return this;

	}

	// public HomePage validateTopProductsSorting(String tab_name) {
	// if (tab_name.equalsIgnoreCase("EverythingTab")) {
	// verify_xpath_text(OR_OR.getProperty("Everything_Sorted_By"),
	// "Products are displayed below and are Sorted by: top products");
	// } else if (tab_name.equalsIgnoreCase("BBTab")) {
	//
	// } else if (tab_name.equalsIgnoreCase("SpecialTab")) {
	//
	// }
	// return this;
	// }
	public HomePage validateSortedByText(String tab_name, String sort_option) {
		int val = 0;
		if (tab_name.equalsIgnoreCase("EverythingTab")) {
			val = 1;
		} else if (tab_name.equalsIgnoreCase("BBTab")) {
			val = 2;
		} else if (tab_name.equalsIgnoreCase("SpecialTab")) {
			val = 3;
		}
		wait(4000);
		switch (val) {
		case 1:
			if (sort_option.equalsIgnoreCase("TopProducts")) {
				verify_xpath_text(OR_OR.getProperty("Everything_Sorted_By"),
						"Products are displayed below and are Sorted by: top products");
			} else if (sort_option.equalsIgnoreCase("special")) {
				verify_xpath_contains_text(OR_OR.getProperty("Everything_Sorted_By"),
						"Products are displayed below and are Sorted by: on special");
			} else if (sort_option.equalsIgnoreCase("lowest unit price")) {
				verify_xpath_contains_text(OR_OR.getProperty("Everything_Sorted_By"),
						"Products are displayed below and are Sorted by: lowest unit price");
			} else if (sort_option.equalsIgnoreCase("lowest price")) {
				verify_xpath_contains_text(OR_OR.getProperty("Everything_Sorted_By"),
						"Products are displayed below and are Sorted by: lowest price");
			}
			break;
		case 2:
			if (sort_option.equalsIgnoreCase("special")) {

			} else if (sort_option.equalsIgnoreCase("lowestunitprice")) {

			} else if (sort_option.equalsIgnoreCase("lowestprice")) {

			}
			break;
		case 3:
			if (sort_option.equalsIgnoreCase("TopProducts")) {
				wait(2000);
				verify_xpath_text(OR_OR.getProperty("Specials_Sorted_By"),
						"Products on special are displayed below and are Sorted by: top products");
			} else if (sort_option.equalsIgnoreCase("LowestUnitPrice")) {
				verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
						"Products on special are displayed below and are Sorted by: lowest unit price");
			} else if (sort_option.equalsIgnoreCase("LowestPrice")) {
				verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
						"Products on special are displayed below and are Sorted by: lowest price");
			} else if (sort_option.equalsIgnoreCase("brandatoz")) {
				verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"),
						"Products on special are displayed below and are Sorted by: brand (a-z)");
			}
			break;

		default:
			testLog.error("Selected value is either not correct or not displayed on the screen.");
			assertCheck("validateSortedByText", "Selected value is either not correct or not displayed on the screen.");
		}

		return this;
	}

	public void search_Add_Products_To_Trolley_And_Validate(String searchskus) {

		String pname_list = searchskus;
		String[] psku_list = pname_list.split(",");
		for (int i = 0; i <= psku_list.length - 1; i++) {

			// search with the sku value
			Sendkey_id(OR_OR.getProperty("Search_Input_Box"), psku_list[i], "Search_Input_Field");
			Click_Button_Xpath(OR_OR.getProperty("Search_Button"), "Search_Button");
			ExplicitWait(OR_OR.getProperty("Clear_Search_Link"), "Clear_Search_Link", 20);
			testLog.info("Searched SKU: " + psku_list[i]);
			// First add product via click at Add button>> Validate login page>>
			// close login
			// page

			addProduct().validateLoginPage().closeLoginPage();
			Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Link"), "Clear_Search_Link");
			wait(1000);
		}
	}

	public HomePage addProduct() {
		Click_Button_Xpath(OR_OR.getProperty("Guest_Add_Product_Button"), "Guest_Add_Product_Button");
		return this;

	}

	public HomePage validateLoginPage() {
		ExplicitWait(OR_OR.getProperty("Login_Page_Heading"), "Login_Page_Heading", 2);
		/*
		 * verify_xpath_text(OR_OR.getProperty("Login_Page_Heading"),
		 * OR_OR.getProperty("Login_Page_Heading_Text"));
		 */
		verify_xpath_text(OR_OR.getProperty("Choose_Delivery_Login_Link"), "Log in");
		verify_xpath_text(OR_OR.getProperty("Choose_Delivery_Signup_Link"), "Sign up");

		return this;

	}

	public HomePage closeLoginPage() {

		Click_Button_Xpath(OR_OR.getProperty("Close_Login_Page_Btn"), "Close_Login_Page_Btn");
		wait(2000);

		return this;
	}

	public HomePage validateBrandSorting(String tab_name) {

		int tabcounter = 0;
		String nth_brand_name = "";
		if (tab_name.equalsIgnoreCase("EverythingTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
			nth_brand_name = OR_OR.getProperty("BrandName_xpath_nvalue");
		} else if (tab_name.equalsIgnoreCase("SpecialTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Special_All_Items"), "Special_All_Items");
			nth_brand_name = OR_OR.getProperty("SpecialTab_N_BrandName");
		} else if (tab_name.equalsIgnoreCase("Shopping_List")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter"),
					"Shopping_List_ProductTile_xpath_Counter");
			nth_brand_name = OR_OR.getProperty("Shopping_List_BrandName_xpath_nvalue");
		}

		int loop_count;
		if (tabcounter >= 12) {
			loop_count = 11;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {

			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(ivalue + 1);

			String bname_1 = nth_brand_name.replace("nvalue", istrvalue);
			String bname_2 = nth_brand_name.replace("nvalue", jstrvalue);

			String strbname_1 = driver.findElement(By.xpath(bname_1)).getText();
			String strbname_2 = driver.findElement(By.xpath(bname_2)).getText();

			if (strbname_1.compareToIgnoreCase(strbname_2) <= 0) {
				testLog.info("The tile no." + istrvalue + " with brand name: " + strbname_1
						+ " is less than or equal to the tile no." + jstrvalue + " with brand name: " + strbname_2);

			} else {
				testLog.error("The tile no." + ivalue + " with brand name: " + strbname_1
						+ " is sorted wrongly and greater than the tile no." + jstrvalue + " with brand name: "
						+ strbname_2);
				assertCheck("validateBrandSorting", "The tile no." + ivalue + " with brand name: " + strbname_1
						+ " is sorted wrongly and greater than the tile no." + jstrvalue + " with brand name: ");
			}
		}
		return this;
	}

	public HomePage validateProductSorting(String tab_name) {
		String nth_product_name = null;
		int tabcounter = 0;
		if (tab_name.equalsIgnoreCase("EverythingTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
			nth_product_name = OR_OR.getProperty("ProductName_xpath_nvalue");
		} else if (tab_name.equalsIgnoreCase("SpecialTab")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Special_All_Items"), "Special_All_Items");
			nth_product_name = OR_OR.getProperty("Specials_ProductName_xpath_nvalue");
		} else if (tab_name.equalsIgnoreCase("Shopping_List")) {
			tabcounter = getXpathCount(OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter"),
					"Shopping_List_ProductTile_xpath_Counter");
			nth_product_name = OR_OR.getProperty("Shopping_List_ProductName_xpath_nvalue");
		}

		// String nth_product_name =
		// OR_OR.getProperty("ProductName_xpath_nvalue");

		int loop_count;
		if (tabcounter >= 12) {
			loop_count = 11;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {

			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(ivalue + 1);

			String pname_1 = nth_product_name.replace("nvalue", istrvalue);
			String pname_2 = nth_product_name.replace("nvalue", jstrvalue);

			String strpname_1 = driver.findElement(By.xpath(pname_1)).getText();
			String strpname_2 = driver.findElement(By.xpath(pname_2)).getText();

			if (strpname_1.compareToIgnoreCase(strpname_2) <= 0) {
				testLog.info("The tile no." + istrvalue + " with product name: " + strpname_1
						+ " is less than or equal to the tile no." + jstrvalue + " with product name: " + strpname_2);

			} else {
				testLog.error("The tile no." + istrvalue + " with product name: " + strpname_1
						+ " is sorted wrongly and greater than the tile no." + jstrvalue + " with product name: "
						+ strpname_2);
				assertCheck("validateProductSorting", "Products name sorting is not fucntioning correctly");
			}
		}
		return this;
	}

	public HomePage validateFirstSpecialTile() {
		ExplicitWait(OR_OR.getProperty("Special_Tiles"), "Special_Tiles", 20);
		String clsValue = get_xpath_attribute_class(OR_OR.getProperty("Special_Tiles"), "Special_Tiles");

		if (clsValue.contains("specials")) {
			testLog.info("The class attribute: " + clsValue + " contains: Special");
		} else {
			testLog.info("The class attribute: " + clsValue + " NOT contains: Special");
			assertCheck("validateFirstSpecialTile", "The class attribute: " + clsValue + " NOT contains: Special");
		}
		return this;
	}

	public HomePage validateFirstSpecialTileColor() {
		ExplicitWait(OR_OR.getProperty("Special_Tiles"), "Special_Tiles", 20);
		String clsValue = driver.findElement(By.xpath(OR_OR.getProperty("Special_Tiles"))).getCssValue("color");

		if (clsValue.contains("rgb")) {
			testLog.info("The product tile color: " + clsValue + " contains: RGB");

		} else {
			testLog.error("The product tile color: " + clsValue + " NOT contains: RGB");
			assertCheck("validateFirstSpecialTileColor", "The product tile color: " + clsValue + " NOT contains: RGB");
		}
		return this;
	}

	public HomePage validateSpecialTiles() {
		ExplicitWait(OR_OR.getProperty("Special_Tiles"), "Special_Tiles", 25);
		int tilesCount = getXpathCount(OR_OR.getProperty("Special_Tiles"), "Special_Tiles");
		String nth_tile_value = OR_OR.getProperty("Special_Tiles_nvalue");

		for (int ivalue = 1; ivalue <= tilesCount; ivalue++) {
			String istrvalue = Integer.toString(ivalue);
			String tilepath = nth_tile_value.replace("nvalue", istrvalue);
			String clsValue = driver.findElement(By.xpath(tilepath)).getAttribute("class");

			if (clsValue.contains("specials")) {
				testLog.info(
						"The class attribute: " + clsValue + " contains: Special" + " in product tile: " + istrvalue);
			} else {
				testLog.error("The class attribute: " + clsValue + " NOT contains: Special" + " in product tile: "
						+ istrvalue);
				assertCheck("validateSpecialTiles", "The class attribute: " + clsValue + " NOT contains: Special"
						+ " in product tile: " + istrvalue);
			}
		}
		return this;
	}

	public HomePage validateSpecialTileColors() {
		String expectedColor = "#ffdd00";
		ExplicitWait(OR_OR.getProperty("Special_Tiles"), "Special_Tiles", 25);
		int tilesCount = getXpathCount(OR_OR.getProperty("Special_Tiles"), "Special_Tiles");
		String nth_tile_value = OR_OR.getProperty("Special_Tiles_Color_nvalue");

		for (int ivalue = 1; ivalue <= tilesCount; ivalue++) {
			String istrvalue = Integer.toString(ivalue);
			String tilepath = nth_tile_value.replace("nvalue", istrvalue);
			String clsValue = driver.findElement(By.xpath(tilepath)).getCssValue("background-color");
			String colorHexCode = Color.fromString(clsValue).asHex();

			if (colorHexCode.equals(expectedColor)) {
				testLog.info("The product tile:" + istrvalue + " border is showing in yellow color, having HEX Code: "
						+ colorHexCode);

			} else {
				testLog.error("The product tile:" + istrvalue + " border NOT showing in yellow color, having HEX Code: "
						+ colorHexCode);
				assertCheck("validateSpecialTileColors", "The product tile:" + istrvalue
						+ " border NOT showing in yellow color, having HEX Code: " + colorHexCode);
			}
		}
		return this;
	}

	public HomePage ClickSpecialSave() {
		Click_Button_Xpath(OR_OR.getProperty("Specials_Save_Button"), "Specials_Save_Button");
		ExplicitWait(OR_OR.getProperty("Special_Tiles"), "Special_Tiles", 10);
		return this;
	}

	public HomePage validateSubCatProducts() {
		int xpathCount = 0, merc_assoc_count_1 = 0, calculation = 0, productname_counter;
		int sum = 0;
		int ipagecount = 1;

		// List<WebElement> totalCategories =
		// driver.findElements(By.xpath(OR_OR.getProperty("LHS_Count")));
		// int count = totalCategories.size();
		// Random rand = new Random();
		//
		// int random_cat =1 + rand.nextInt(count);
		wait(5000);
		int catcount = getCategoryCount(1);
		testLog.info("Category total count is : " + catcount);
		clickLHSCategory(1);

		if (catcount > 48) {
			List<WebElement> pagecount = driver.findElements(By.xpath(OR_OR.getProperty("pagecount")));
			ipagecount = pagecount.size();
			if (ipagecount < 1) {
				testLog.warn("Only one page is displayed for products more than 48");
			}
		}
		for (int i = 1; i <= ipagecount; i++) {

			xpathCount = getXpathCount(OR_OR.getProperty("ProductTile_xpath_Counter"), "ProductTile_xpath_Counter");
			merc_assoc_count_1 = getXpathCount(OR_OR.getProperty("Everything_Merch_Assoc_Box"),
					"Everything_Merch_Assoc_Box");
			productname_counter = getXpathCount(OR_OR.getProperty("ProductName_xpath_Counter"),
					"ProductName_xpath_Counter");

			// producttile_counter =
			// getXpathCount(OR_OR.getProperty("ProductTiles_Counter"),"ProductTiles_Counter");

			calculation = xpathCount - merc_assoc_count_1;

			if ((xpathCount == productname_counter)) {
				testLog.info(
						"ProductTile Count, BrandName Count and ProductName Count are all equal. Product Tiles Displayed Correctly.");
			} else {
				testLog.error(
						"ProductTile Count, BrandName Count and ProductName Count are not equal. Product Tiles Displayed Incorrectly.");
				assertCheck("validateSubCatProducts",
						"ProductTile Count, BrandName Count and ProductName Count are not equal. Product Tiles Displayed Incorrectly.");
			}

			sum = sum + calculation;

			if ((i + 1) <= ipagecount) {
				String istr = Integer.toString(i + 1);
				String pagination = OR_OR.getProperty("Load_More_Button");
				pagination = pagination.replaceAll("nvalue", istr);

				driver.findElement(By.xpath(pagination)).click();
			}
		}

		if (sum == catcount) {
			testLog.info("Number of Products displayed: " + sum + " is equal to the subcategory counter: " + catcount);
		} else {
			testLog.error("number of product displayed on the page" + sum + " are not matching to the category counter "
					+ catcount);
			assertCheck("validateSubCatProducts", "number of product displayed on the page" + sum
					+ " are not matching to the category counter " + catcount);
		}
		return this;
	}

	/*
	 * public HomePage clickMultiSearchLink() {
	 * 
	 * Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"),
	 * "MultiSearchLink"); verify_xpath_text(OR_OR.getProperty(
	 * "MultiSearchManage_Overlay_withoutItem_Heading"),
	 * "Search for a list of items"); verify_xpath_text(OR_OR.getProperty(
	 * "MultiSearchManage_Overlay_withoutItem_SubHeading"),
	 * "Type or paste your list below putting each item on a new line.");
	 * verify_xpath_text(OR_OR.getProperty(
	 * "MultiSearchManage_Overlay_withoutItem_PopupBody"),
	 * "Start typing or paste your list here"); Assert(); return this; }
	 */

	public HomePage clickMultiSearchLink() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Hide_button"), "MultiSearchExpanded_Hide_button")) {
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_Manage_button"),
					"MultiSearchExpanded_Manage_button");
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withItem_ClearList_Link"),
					"MultiSearchManage_Overlay_withItem_ClearList_Link");
			ExplicitWait(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
					"MultiSearchManage_Overlay_withoutItem_Save&Search_Button", 20);
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
					"MultiSearchManage_Overlay_withoutItem_Save&Search_Button");
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		}
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Heading"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_SubHeading"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_SubHeading_Text"));
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_PopupBody"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_PopupBody_Text"));
		return this;
	}

	public HomePage clickSecondItemMultiSearch() {
		Click_Button_Xpath(OR_OR.getProperty("MulitSearchManage_Overlay_secondItem"),
				"MulitSearchManage_Overlay_secondItem");
		ExplicitWait(OR_OR.getProperty("Results_For"), "Results_For", 20);
		ExplicitWait(OR_OR.getProperty("Everything_Sortedby_ChangeLink"), "Everything_Sortedby_ChangeLink", 20);
		String result2 = get_xpath_text(OR_OR.getProperty("Results_For"), "Results_For");
		testLog.info("result is " + result2);
		return this;
	}

	public HomePage validateMultiSearchClearList() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_Manage_button"), "MultiSearchExpanded_Manage_button");
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withItem_ClearList_Link"),
				"MultiSearchManage_Overlay_withItem_ClearList_Link");
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
				"MultiSearchManage_Overlay_withoutItem_Save&Search_Button");
		return this;
	}

	public HomePage validateTobaccoWarningMsg() {
		String expectedTobaccoWarningMsg = OR_OR.getProperty("Everything_Tobacco_Tile_Warning_Msg");
		String tobaccoWarningMsg = get_xpath_text(OR_OR.getProperty("Everything_Tobacco_Tile_Warning"),
				"Everything_Tobacco_Tile_Warning");
		if (tobaccoWarningMsg.contains(expectedTobaccoWarningMsg)) {
			testLog.info("String :" + tobaccoWarningMsg + " contains-> " + expectedTobaccoWarningMsg);
		} else {
			testLog.error("String :" + tobaccoWarningMsg + " NOT contains-> " + expectedTobaccoWarningMsg);
			assertCheck("validateTobaccoWarningMsg",
					"String :" + tobaccoWarningMsg + " NOT contains-> " + expectedTobaccoWarningMsg);
		}
		return this;
	}

	public HomePage validateLiquorWarningMsg() {
		ExplicitWait(OR_OR.getProperty("Liquor_Warning_Tile"), "Liquor_Warning_Tile", 5);
		// Not getting below message, adding only for future reffernece -
		String expectedLiquorMsg = OR_OR.getProperty("Everthing_Liquor_Tile_Warning_Msg");
		verify_xpath_text(OR_OR.getProperty("Everthing_Liquor_Tile_Warning"), expectedLiquorMsg);
		String expectedLiquorWarningMsg = OR_OR.getProperty("Liquor_Espot_Warning_Tile_Msg1");
		String liquorWarningMsg = get_xpath_text(OR_OR.getProperty("Liquor_Espot_Warning_Tile"),
				"Liquor_Espot_Warning_Tile");
		if (liquorWarningMsg.contains(expectedLiquorWarningMsg)) {
			testLog.info("String :" + liquorWarningMsg + ", contains IN-> " + expectedLiquorWarningMsg);

		} else {
			testLog.error("String :" + liquorWarningMsg + ", NOT contains IN-> " + expectedLiquorWarningMsg);
			assertCheck("validateLiquorWarningMsg",
					"String :" + liquorWarningMsg + ", NOT contains IN-> " + expectedLiquorWarningMsg);
		}
		wait(1000);
		return this;
	}

	public HomePage clickShowTobaccoProductBtn() {
		Click_Button_Xpath(OR_OR.getProperty("Show_These_Tobacco_Products_Btn"), "Show_These_Tobacco_Products_Btn");
		return this;
	}

	public HomePage validateTobaccoProductDetailsWarningMsg() {
		verify_xpath_text(OR_OR.getProperty("Everything_Tobacco_Product_Details_Warning"),
				OR_OR.getProperty("Everything_Tobacco_Product_Details_Warning_Msg"));
		wait(1000);
		return this;
	}

	public HomePage clickHomeIcon() {
		Click_Button_Xpath(OR_OR.getProperty("Home_icon"), "Home_icon");
		wait(1000);
		return this;
	}

	public HomePage clickViewLiquorLicenceLink() {
		ExplicitWait(OR_OR.getProperty("View_Liquor_Licence_Link"), "View_Liquor_Licence_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("View_Liquor_Licence_Link"), "View_Liquor_Licence_Link");
		return this;
	}

	public HomePage validateLiquorLicencePageTitle() {
		ExplicitWait(OR_OR.getProperty("Liquor_Licence_Page_Title"), "Liquor_Licence_Page_Title", 2);
		verify_xpath_text(OR_OR.getProperty("Liquor_Licence_Page_Title"),
				OR_OR.getProperty("Liquor_Licence_Page_Title_Msg"));
		return this;
	}

	public HomePage clickGeoLocationChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("Geo_Location_Bar_Change_Link"), "Geo_Location_Bar_Change_Link");
		return this;
	}

	public HomePage enterGeoLocation(String suburb_str) {
		Sendkey_id(OR_OR.getProperty("Geo_Location_Bar_SearchField"), suburb_str, "Geo_Location_Bar_SearchField");
		wait(3000);
		return this;
	}

	// Function which allows guest user to geo locate to different localisation
	public HomePage selectFirstSuggestedGeoLocation() {
		Click_Button_Xpath(OR_OR.getProperty("Geo_Location_Bar_First_Suggested_Suburb"),
				"Geo_Location_Bar_First_Suggested_Suburb");
		wait(8000);
		ExplicitWait(OR_OR.getProperty("SearchField_InsideText_Path"), "SearchField_InsideText_Path", 40);
		return this;
	}

	public HomePage selectSuggestedGeoLocation() {
		int suggestlist = getXpathCount(OR_OR.getProperty("Geo_Location_Bar_Suggested_Suburb"),
				"Geo_Location_Bar_Suggested_Suburb");
		for (int i = 1; i <= suggestlist; i++) {
			String istr = Integer.toString(i);
			String Suggested_N_List = OR_OR.getProperty("Geo_Location_Bar_Nth_Suggested_Suburb");
			Suggested_N_List = Suggested_N_List.replace("nvalue", istr);
			String suggest_Test = driver.findElement(By.xpath(Suggested_N_List)).getText();
			if (suggest_Test.contains("Bayview, NT, 0820")) {
				driver.findElement(By.xpath(Suggested_N_List)).click();
			}
			if (suggest_Test.contains("Oakleigh East, VIC, 3166")) {
				driver.findElement(By.xpath(Suggested_N_List)).click();
				break;
			}
		}
		wait(8000);
		ExplicitWait(OR_OR.getProperty("SearchField_InsideText_Path"), "SearchField_InsideText_Path", 40);
		return this;
	}

	public HomePage validateGeoLocation() {
		int product_count;
		clickEverythingTab();
		ExplicitWait(OR_OR.getProperty("Browse_By_Category_Level_0"), "Browse_By_Category_Level_0", 2);

		// product_count =
		// getXpathCount(OR_OR.getProperty("ProductName_firstProduct"),
		// "ProductName_firstProduct");
		//
		// if (product_count > 0) {
		// testLog.info("Number of products displayed on home page for " +
		// LocationName + " are " + product_count);
		// } else {
		// testLog.error("Number of products displayed for " + LocationName +
		// "is " + product_count);
		//
		// }

		java.util.List<WebElement> listchanges = driver.findElements(By.xpath(OR_OR.getProperty("LHS_Count")));

		if (listchanges.size() < 10) {
			testLog.error("Number of categories displayed for " + LocationName + " in Everything tab are "
					+ Integer.toString(listchanges.size()));
			testLog.error("URL for the location " + driver.getCurrentUrl());
			assertCheck("validateGeoLocation", "Number of categories displayed for " + LocationName
					+ " in Everything tab are " + Integer.toString(listchanges.size()));

		} else {
			testLog.info("Number of categories displayed on home page for " + LocationName + " are "
					+ Integer.toString(listchanges.size()));
			testLog.info(
					"Localisation Bar Suburb: " + get_xpath_text(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"),
							"Geo_Location_Bar_Localised_Suburb").trim());
		}
		return this;
	}

	public HomePage scrollDownHomePage(int x, int y) {
		ExplicitWait(OR_OR.getProperty("Home_icon"), "Home_icon", 5);
		scrollTo_Pixel(x, y);
		return this;
	}

	public HomePage clickBackToTopIcon() {
		ExplicitWait(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow", 5);
		Click_Button_Xpath(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow");
		return this;
	}

	public HomePage validateHomePageHeader() {
		ExplicitWait(OR_OR.getProperty("Home_icon"), "Home_icon", 2);
		isElementPresent(OR_OR.getProperty("Home_icon"), "Home_icon");
		isElementPresent(OR_OR.getProperty("Coles_Logo"), "Coles_Logo");
		wait(2000);
		return this;
	}

	/**** Filter Products *****/

	public HomePage clickShowFilterBtn_EverythingTab() {
		Click_Button_Xpath(OR_OR.getProperty("ShowFilters"), "show Filters");
		verify_xpath_text(OR_OR.getProperty("Heading_FilterProducts"),
				OR_OR.getProperty("Heading_FilterProducts_Text"));
		verify_xpath_text(OR_OR.getProperty("FilterBy_Brand"), OR_OR.getProperty("FilterBy_Brand_Text"));
		return this;
	}

	public HomePage clickShowFilterBtn_SpecialTab() {
		Click_Button_Xpath(OR_OR.getProperty("ShowFilters_Special"), "show Filters Special tab");
		verify_xpath_text(OR_OR.getProperty("Heading_FilterProducts_Special"),
				OR_OR.getProperty("Heading_FilterProducts_Text"));
		verify_xpath_text(OR_OR.getProperty("FilterBy_Brand_Special"), OR_OR.getProperty("FilterBy_Brand_Text"));
		return this;
	}

	public HomePage clickFilterByBrand() {
		Click_Button_Xpath(OR_OR.getProperty("FilterBy_Brand"), "FilterBy_Brand");
		return this;
	}

	public HomePage clickFilterByBrand_SpecialTab() {
		Click_Button_Xpath(OR_OR.getProperty("FilterBy_Brand_Special"), "FilterBy_Brand");
		return this;
	}

	public HomePage verifyFilterSearch() {
		String xp = OR_OR.getProperty("FilterPopup_BrandCheckbox");
		int count;

		ExplicitWait(OR_OR.getProperty("FilterPopup_Search_Input"), "FilterPopup_Search_Input", 5);
		Sendkey_xpath(OR_OR.getProperty("FilterPopup_Search_Input"), "Coles", "FilterPopup_Search_Input");
		List<WebElement> TotalFilterOptions = driver
				.findElements(By.xpath(OR_OR.getProperty("FilterPopup_CheckboxCount")));
		count = TotalFilterOptions.size();
		if (get_xpath_text(OR_OR.getProperty("FilterPopup_FilterValueHeading"), "FilterPopup_FilterValueHeading")
				.equalsIgnoreCase(count + " brand filter found for \"Coles\"")) {
			for (int i = 1; i <= count; ++i) {
				String val = Integer.toString(i);
				xp = xp.replace("nvalue", val);
				String BrandName = driver.findElement(By.xpath(xp)).getAttribute("name");
				if (!BrandName.contains("Coles")) {
					testLog.error("invalid Search result:" + BrandName
							+ ". Search result do not contains search keyword Coles");
					assertCheck("verifyFilterSearch", "invalid Search result:" + BrandName
							+ ". Search result do not contains search keyword Coles");
				}
			}
		}
		Click_Button_Xpath(OR_OR.getProperty("Filter_Popup_ClearSearch"), "Filter_Popup_ClearSearch'");
		return this;
	}

	public HomePage filterPopup_SelectRandomCheckbox() {
		List<WebElement> TotalFilterOptions = driver
				.findElements(By.xpath(OR_OR.getProperty("FilterPopup_CheckboxCount")));
		int count = TotalFilterOptions.size();
		Random rand = new Random();

		int random_cat = 1 + rand.nextInt(count);

		String val = Integer.toString(random_cat);
		String xp = OR_OR.getProperty("FilterPopup_BrandCheckbox");
		String xp1 = OR_OR.getProperty("FilterPopup_TotalFilteredProduct");
		xp = xp.replace("nvalue", val);
		xp1 = xp1.replace("nvalue", val);

		Click_Button_Xpath(xp, "FilterPopup_BrandCheckbox");
		BrandName = driver.findElement(By.xpath(xp)).getAttribute("name");

		String productcount = get_xpath_text(xp1, "FilterPopup_TotalFilteredProduct");
		productcount = productcount.replaceAll("[^0-9]", "");
		filtered_productCount = Integer.parseInt(productcount);

		testLog.info("selected brand is: " + BrandName + ". Total products for the selected Brand is: " + productcount);
		return this;
	}

	public HomePage clickShowFilteredProducts() {
		Click_Button_Xpath(OR_OR.getProperty("FilterPopup_ShowFilteredProducts"), "FilterPopup_ShowFilteredProducts");
		return this;
	}

	public HomePage ValidateFilterResult(String tabname) {
		// get total no of products compare with filtered count
		int xpathCount = 0, merc_assoc_count_1 = 0, calculation = 0, productname_counter;
		int sum = 0;
		int ipagecount = 1;
		String pagination = OR_OR.getProperty("Load_More_Button");
		String nth_brand_name;
		String ProductTile_xpath_Counter = "", ProductName_xpath_Counter = "", merchTile_Xpath_counter = "";

		if (tabname.equalsIgnoreCase("EverythingTab")) {
			nth_brand_name = OR_OR.getProperty("BrandName_xpath_nvalue");
			ProductTile_xpath_Counter = OR_OR.getProperty("ProductTile_xpath_Counter");
			ProductName_xpath_Counter = OR_OR.getProperty("ProductName_xpath_Counter");
			merchTile_Xpath_counter = OR_OR.getProperty("Everything_Merch_Assoc_Box");

		} else {
			nth_brand_name = OR_OR.getProperty("SpecialTab_N_BrandName");
			ProductTile_xpath_Counter = OR_OR.getProperty("Specials_ProductTiles_Counter");// ("Special_All_Items");
			ProductName_xpath_Counter = OR_OR.getProperty("Specials_ProductName_xpath_Counter");
			merchTile_Xpath_counter = OR_OR.getProperty("Specials_Merch_Assoc_Box");
		}

		if (filtered_productCount > 48) {
			if (isElementPresent(OR_OR.getProperty("pagecount"), "pagecount")) {
				List<WebElement> pagecount = driver.findElements(By.xpath(OR_OR.getProperty("pagecount")));
				ipagecount = pagecount.size();
			} else {
				testLog.error("All filtered products are not visible");
				assertCheck("ValidateFilterResult", "All filtered products are not visible");
			}
		}

		for (int i = 1; i <= ipagecount; i++) {
			xpathCount = getXpathCount(ProductTile_xpath_Counter, "ProductTile_xpath_Counter");

			merc_assoc_count_1 = getXpathCount(merchTile_Xpath_counter, "Everything_Merch_Assoc_Box");
			productname_counter = getXpathCount(ProductName_xpath_Counter, "ProductName_xpath_Counter");

			calculation = xpathCount - merc_assoc_count_1;
			if ((calculation == productname_counter)) {
				testLog.info("ProductTile Count and BrandName is correctly displayed.");
			} else {
				testLog.error("ProductTile Count and BrandName is not correct.");
				assertCheck("ValidateFilterResult", "ProductTile Count and BrandName is not correct.");
			}

			sum = sum + calculation;

			for (int j = 1; j <= calculation; ++j) {

				String bname = nth_brand_name.replace("nvalue", Integer.toString(j));
				String bname_txt = driver.findElement(By.xpath(bname)).getText();
				if (!bname_txt.equals(BrandName)) {
					testLog.error(
							"Brand name :" + bname_txt + "is not equal to the Filter applied for brand:" + BrandName);
					assertCheck("ValidateFilterResult",
							"Brand name :" + bname_txt + "is not equal to the Filter applied for brand:" + BrandName);
				} else {
					testLog.info("The tile no. " + j + " with brand name: " + bname_txt + " is same as filtered brand: "
							+ BrandName);
				}
			}

			if ((i + 1) <= ipagecount) {
				String istr = Integer.toString(i + 1);
				pagination = pagination.replaceAll("nvalue", istr);
				driver.findElement(By.xpath(pagination)).click();
			}
		}
		if (sum == filtered_productCount) {
			testLog.info("Number of Products displayed " + sum + " is equal to the filtered products count"
					+ filtered_productCount);
		} else {
			testLog.error("number of product displayed on the page" + sum
					+ " are not matching to the filtered products count " + filtered_productCount);
			assertCheck("ValidateFilterResult", "number of product displayed on the page" + sum
					+ " are not matching to the filtered products count " + filtered_productCount);
		}
		return this;
	}

	public HomePage validatePagination() {
		int tabcounter = get_Tab_Counter(OR_OR.getProperty("Everything_Tab_Counter"), "Everything_Tab_Counter");
		wait(5000);
		if (tabcounter > 48) {
			if (isElementPresent(OR_OR.getProperty("Navigate_To_Products_First_Page"),
					"Navigate_To_Products_First_Page")) {
				String pagination = OR_OR.getProperty("Load_More_Button");
				pagination = pagination.replaceAll("nvalue", "2");
				Click_Button_Xpath(pagination, "pagination");
				// Not removing below line as could be use later
				// verify_xpath_contains_text(OR_OR.getProperty("First_Product_ProductName"),
				// search_1);
				ExplicitWait(OR_OR.getProperty("First_Product_ProductName"), "First_Product_ProductName", 15);
				if ((isElementPresent(OR_OR.getProperty("First_Product_ProductName"), "First_Product_ProductName"))) {
					testLog.info("After clicking on pagination, Tile(s) & Product(s) name is appearing");
				} else {
					testLog.error("After clicking on pagination, Tile(s) & Product(s) name is NOT appearing");
					assertCheck("validatePagination", "Pagination is not visible on the page");
				}
			} else {
				testLog.error("Pagination is not visible on the page");
				assertCheck("validatePagination", "Pagination is not visible on the page");
			}
		}
		return this;
	}

	public HomePage removeAllItemsFromTrolley() {
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley_Text");
		if (isElementPresent(OR_OR.getProperty("Trolley_RemoveAll"), "Trolley_RemoveAll")) {
			Click_Button_Xpath(OR_OR.getProperty("Trolley_RemoveAll"), "Trolley_RemoveAll");
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Yes_Remove_Everything"), "Trolley_Yes_Remove_Everything");
			if (isElementPresent(OR_OR.getProperty("Trolley_Is_Empty_Test"), "Trolley_Is_Empty_Test")) {
				testLog.info("Successfully removed all items from trolley");
			}
		} else if (isElementPresent(OR_OR.getProperty("Trolley_Is_Empty_Test"), "Trolley_Is_Empty_Test")) {
			testLog.info("Trolley is already is empty");
		}
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
	}

	public HomePage addFirstItemToTrolley() {
		scrollTo_Xpath(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow", 4);
		ExplicitWait(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow", 5);
		Click_Button_Xpath(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController");
		String firstProductName = get_xpath_text(OR_OR.getProperty("Search_Tile1"), "Search_Tile1");
		testLog.info("First Product name: " + firstProductName);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley and checkout");
		ExplicitWait(OR_OR.getProperty("Trolly_First_Item"), "Trolly_First_Item", 5);
		wait(2000);
		String trollyfirstProductName = get_xpath_text(OR_OR.getProperty("Trolly_First_Item"), "Trolly_First_Item");
		wait(3000);
		testLog.info("Trolley Product name: " + trollyfirstProductName);

		if (trollyfirstProductName.contains(firstProductName)) {
			testLog.info("Multisearch added item: " + firstProductName + ", is contain in Trolley-> "
					+ trollyfirstProductName);
		} else {
			testLog.error("Multisearch added item: " + firstProductName + ", is NOT contain in Trolley-> "
					+ trollyfirstProductName);
			assertCheck("addFirstItemToTrolley", "Multisearch added item: " + firstProductName
					+ ", is NOT contain in Trolley-> " + trollyfirstProductName);
		}
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
	}

	public HomePage addFirstItemToTrolley_search_parameter() {
		scrollTo_Xpath(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow", 4);
		ExplicitWait(OR_OR.getProperty("Home_Back_To_Top_Arrow"), "Home_Back_To_Top_Arrow", 5);
		Click_Button_Xpath(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController");
		String firstProductName = get_xpath_text(OR_OR.getProperty("Search_Tile1"), "Search_Tile1");
		testLog.info("First Product name: " + firstProductName);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley and checkout");
		ExplicitWait(OR_OR.getProperty("Trolly_First_Item"), "Trolly_First_Item", 5);
		wait(2000);
		String trollyfirstProductName = get_xpath_text(OR_OR.getProperty("Trolly_First_Item"), "Trolly_First_Item");
		wait(3000);
		testLog.info("Trolley Product name: " + trollyfirstProductName);

		if (trollyfirstProductName.toLowerCase().contains(firstProductName.toLowerCase())) {
			testLog.info("Multisearch added item: " + firstProductName + ", is contain in Trolley-> "
					+ trollyfirstProductName);
		} else {
			testLog.error("Multisearch added item: " + firstProductName + ", is NOT contain in Trolley-> "
					+ trollyfirstProductName);
			assertCheck("addFirstItemToTrolley_search_parameter", "Multisearch added item: " + firstProductName
					+ ", is NOT contain in Trolley-> " + trollyfirstProductName);
		}
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
		// &&
		// (trollyfirstProductName.toLowerCase()).contains(search_1.toLowerCase()))
	}

	public HomePage validateSuccessGreenIcon() {
		isElementPresent(OR_OR.getProperty("MultiSearch_Success_Icon"), "MultiSearch_Success_Icon");
		String expectedColor = "#008a23";
		String clsValue = driver.findElement(By.xpath(OR_OR.getProperty("MultiSearch_Success_Icon_Text")))
				.getCssValue("color");
		String colorHexCode = Color.fromString(clsValue).asHex();
		if (colorHexCode.equals(expectedColor)) {
			testLog.info("The multisearch product is showing in green color, having HEX Code: " + colorHexCode);
		} else {
			testLog.info("The multisearch product is not showing in green color, having HEX Code: " + colorHexCode);
			assertCheck("validateSuccessGreenIcon",
					"The multisearch product is not showing in green color, having HEX Code: " + colorHexCode);
		}
		return this;
	}

	public HomePage validateProdutsinBoughtBeforeTab() {
		String boughtbefore_nval = OR_OR.getProperty("Bought_Before_N_Product");
		String boughtbeforeheader_nval = OR_OR.getProperty("Bought_Before_N_Product_HeaderTag");
		int tabcounter = getXpathCount(OR_OR.getProperty("Bought_Before_Products"), "Bought_Before_Products");
		testLog.info("Total count of searched product present in Everything is :" + tabcounter);
		for (int i = 1; i <= tabcounter; i++) {
			String istrvalue = Integer.toString(i);
			String bb = boughtbefore_nval;
			String bbh = boughtbeforeheader_nval;
			String bb_1 = bb.replace("nvalue", istrvalue);
			String bbh_1 = bbh.replace("nvalue", istrvalue);
			String str_bb_1 = driver.findElement(By.xpath(bb_1)).getText();
			String str_bbh_1 = driver.findElement(By.xpath(bbh_1)).getText();
			testLog.info("Bought Before product " + i + " : " + str_bb_1);
			testLog.info("Bought Before product header " + i + " : " + str_bbh_1);
		}
		return this;
	}

	public HomePage validateProductDetails(String page) {
		boolean bool1, bool2, bool3, bool4, bool5;
		if (page.equals("HomePage")) {
			ExplicitWait(OR_OR.getProperty("First_Product_Tile"), "First_Product_Tile", 5);
			bool1 = isElementPresent(OR_OR.getProperty("First_Product_Tile"), "First_Product_Tile");
			bool2 = isElementPresent(OR_OR.getProperty("First_Product_Brand"), "First_Product_Brand");
			bool3 = isElementPresent(OR_OR.getProperty("First_Product_Name"), "First_Product_Name");
			bool4 = isElementPresent(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController");
			bool5 = isElementPresent(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown");
		} else {
			String fat_controller = OR_OR.getProperty("Product_FatController_PDPage");
			String fat_controllerPDPage = fat_controller.replace("nvalue", search_2);
			String fat_conDropdown = OR_OR.getProperty("Product_Dropdown_PDPage");
			String fat_conDropdownPDPage = fat_conDropdown.replace("nvalue", search_2);
			bool1 = isElementPresent(OR_OR.getProperty("Product_Tile_PDPage"), "Product_Tile_PDPage");
			bool2 = isElementPresent(OR_OR.getProperty("Product_Brand_PDPage"), "Product_Brand_PDPage");
			bool3 = isElementPresent(OR_OR.getProperty("Product_Name_PDPage"), "Product_Name_PDPage");
			bool4 = isElementPresent(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController");
			bool5 = isElementPresent(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown");
		}
		if (bool1 == true && bool2 == true && bool3 == true && bool4 == true && bool5 == true) {
			testLog.info(
					"Product_Tile, Brand_Name, Product_Name, FatController, FC_Dropdown is present for the searched SKU");
		} else {
			testLog.error(
					"Product_Tile, Brand_Name, Product_Name, FatController, FC_Dropdown : is not present for the searched SKU");
			assertCheck("validateProductDetails",
					"Product_Tile, Brand_Name, Product_Name, FatController, FC_Dropdown : is not present for the searched SKU");
		}
		return this;
	}

	public HomePage validateRedemtionLimitMsg(String pageName) {
		getPages.getHomePage().enter_Value_Into_FiveorMore("Add", "30");
		wait(5000);
		String msg = get_xpath_text(OR_OR.getProperty("Superbar_RedemtionLimitMsg"), "Superbar_RedemtionLimitMsg");
		String productName = get_xpath_text(OR_OR.getProperty("First_Product_Name"), "First_Product_Name");
		String productBrand = get_xpath_text(OR_OR.getProperty("First_Product_Brand"), "First_Product_Brand");
		if (msg.contains("Sorry, the most you can order of " + productBrand + " " + productName)) {
			testLog.info("Redemption limit message popup is displayed when user trying to add more than product limit");

		} else {
			testLog.error(
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
			assertCheck("validateRedemtionLimitMsg",
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
		}
		isElementPresent(OR_OR.getProperty("RedemtionLimitMsg_Ok_AddtoMyTrolly"), "RedemtionLimitMsg_Ok_AddtoMyTrolly");
		verify_xpath_text(OR_OR.getProperty("RedemtionLimitMsg_Dont_Add"), "I've changed my mind, don't add any");
		Click_Button_Xpath(OR_OR.getProperty("RedemtionLimitMsg_Dont_Add"), "RedemtionLimitMsg_Dont_Add");

		getPages.getHomePage().enter_Value_Into_FiveorMore("Add", "30");
		Click_Button_Xpath(OR_OR.getProperty("RedemtionLimitMsg_Ok_Add"), "RedemtionLimitMsg_Ok_Add");
		wait(3000);

		getPages.getHomePage().enter_Value_Into_FiveorMore("Update", "30");
		String msg1 = get_xpath_text(OR_OR.getProperty("Redemption_Max_Text1"), "Redemption_Max_Text1");
		if (msg1.contains("You have reached the maximum order limit for " + productBrand + " " + productName)) {
			testLog.info("Redemption limit message popup is displayed when user trying to add more than product limit");

		} else {
			testLog.error(
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
			assertCheck("validateRedemtionLimitMsg",
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
		}
		Click_Button_Xpath(OR_OR.getProperty("Redemption_Already_Max_Ok_Button"), "Redemption_Already_Max_Ok_Button");
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley_Text");

		getPages.getTrolleyAndCheckoutPage().enter_Value_Into_FiveorMore_rhst("30");
		if (msg1.contains("You have reached the maximum order limit for " + productBrand + " " + productName)) {
			testLog.info("Redemption limit message popup is displayed when user trying to add more than product limit");

		} else {
			testLog.error(
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
			assertCheck("validateRedemtionLimitMsg",
					"Redemption limit message popup is not displayed when user trying to add more than product limit");
		}
		return this;
	}

	public HomePage validateRoundUpOrRoundDownMsg(String pageName, String RoundUpOrRoundDown) {
		wait(2000);
		String productBrand = get_xpath_text(OR_OR.getProperty("First_Product_Brand"), "First_Product_Brand");
		String productName = get_xpath_text(OR_OR.getProperty("First_Product_Name"), "First_Product_Name");
		int tcount_bef;
		int rounddig = 0;
		String tcount_before;
		if (Trolley_Total_Cost == 0.0) {
			tcount_bef = 0;
		} else {
			tcount_before = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
			tcount_before = tcount_before.replaceAll("\\s+", "");
			tcount_bef = Integer.parseInt(tcount_before);
		}
		if (pageName.equals("Home")) {
			getPages.getHomePage().enter_Value_Into_FiveorMore("Add", "6.5");
		} else {
			getPages.getTrolleyAndCheckoutPage().enter_Value_Into_FiveorMore_rhst("6.5");
		}
		wait(2000);
		String message1 = "Sorry, you can only buy ".concat(productBrand).concat(" ").concat(productName).concat(" ")
				.concat("in multiples of 1");
		verify_xpath_text(OR_OR.getProperty("Roundoff_Text"), message1);
		verify_xpath_text(OR_OR.getProperty("RoundUp_Button"), "Round up to 7");
		verify_xpath_text(OR_OR.getProperty("RoundDown_Button"), "Round down to 6");
		verify_xpath_text(OR_OR.getProperty("Roundoff_Dont_Add_Link"), "I've changed my mind, don't add any");
		if (RoundUpOrRoundDown.equals("RoundUp")) {
			Click_Button_Xpath(OR_OR.getProperty("RoundUp_Button"), "RoundUp_Button");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("RoundDown_Button"), "RoundDown_Button");
		}
		ExplicitWait(OR_OR.getProperty("Tile1_qty_ValidCount"), "Tile1_qty_ValidCount", 2);
		wait(3000);
		if (RoundUpOrRoundDown.equals("RoundUp")) {
			verify_xpath_text(OR_OR.getProperty("Tile1_qty_ValidCount"), "7");
			rounddig = 7;
		} else {
			verify_xpath_text(OR_OR.getProperty("Tile1_qty_ValidCount"), "6");
			rounddig = 6;
		}
		;
		validateSuperbar_qny(tcount_bef, rounddig);
		if (pageName.equals("Home")) {
			getPages.getHomePage().enter_Value_Into_FiveorMore("Update", "8.5");
		} else {
			getPages.getTrolleyAndCheckoutPage().enter_Value_Into_FiveorMore_rhst("8.5");
		}
		wait(2000);
		verify_xpath_text(OR_OR.getProperty("Roundoff_Text"), message1);
		if (RoundUpOrRoundDown.equals("RoundUp")) {
			Click_Button_Xpath(OR_OR.getProperty("RoundUp_Button"), "RoundUp_Button");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("RoundDown_Button"), "RoundDown_Button");
		}
		ExplicitWait(OR_OR.getProperty("Tile1_qty_ValidCount"), "Tile1_qty_ValidCount", 2);
		wait(3000);
		if (RoundUpOrRoundDown.equals("RoundUp")) {
			verify_xpath_text(OR_OR.getProperty("Tile1_qty_ValidCount"), "9");
		} else {
			verify_xpath_text(OR_OR.getProperty("Tile1_qty_ValidCount"), "8");
		}
		return this;
	}

	public HomePage validateSuperbar_qny(int tcount_bef, int rounddig) {
		String tcount_after = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		int tcount_aft = Integer.parseInt(tcount_after);
		if (tcount_aft == (tcount_bef + rounddig)) {
			testLog.info("The Super bar quantity is updated correctly from: " + tcount_bef + " to " + tcount_aft);
		} else {
			testLog.error("The Super bar quantity is not updated correctly from: " + tcount_bef + " to " + tcount_aft);
			assertCheck("validateSuperbar_qny",
					"The Super bar quantity is not updated correctly from: " + tcount_bef + " to " + tcount_aft);
		}
		return this;
	}

	public TrolleyAndCheckoutPage clickTrolley() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley_Text");
		return new TrolleyAndCheckoutPage();
	}

	public HomePage validateTobaccoEspotWarningMsg() {
		wait(10000);
		ExplicitWait(OR_OR.getProperty("Tobacco_Warning_Tile"), "Tobacco_Warning_Tile", 5);
		String expectedTobaccoWarningMsg = OR_OR.getProperty("Tobacco_Espot_Quit_Msg");
		String tobaccoWarningMsg = get_xpath_text(OR_OR.getProperty("Tobacco_Espot_Quitline"),
				"Tobacco_Espot_Quitline");
		if (tobaccoWarningMsg.contains(expectedTobaccoWarningMsg)) {
			testLog.info("String :" + tobaccoWarningMsg + ", contains IN-> " + expectedTobaccoWarningMsg);

		} else {
			testLog.error("String :" + tobaccoWarningMsg + ", NOT contains IN-> " + expectedTobaccoWarningMsg);
			assertCheck("validateTobaccoEspotWarningMsg",
					"String :" + tobaccoWarningMsg + ", NOT contains IN-> " + expectedTobaccoWarningMsg);
		}
		return this;
	}

	public HomePage validateLiquorEspotWarningMsg() {
		ExplicitWait(OR_OR.getProperty("Liquor_Warning_Tile"), "Liquor_Warning_Tile", 5);
		String expectedLiquorWarningMsg = OR_OR.getProperty("Liquor_Espot_Warning_Tile_Msg");
		String expectedLiquorWarningMsg1 = OR_OR.getProperty("Liquor_Espot_Warning_Tile_Msg1");
		String liquorWarningMsg = get_xpath_text(OR_OR.getProperty("Liquor_Espot_Warning_Tile"),
				"Liquor_Espot_Warning_Tile");
		if (liquorWarningMsg.contains(expectedLiquorWarningMsg)
				|| liquorWarningMsg.contains(expectedLiquorWarningMsg1)) {
			testLog.info("String :" + liquorWarningMsg + ", contains IN-> " + expectedLiquorWarningMsg1 + "Or"
					+ expectedLiquorWarningMsg);
		} else {
			testLog.error("String :" + liquorWarningMsg + ", NOT contains IN-> " + expectedLiquorWarningMsg);
			assertCheck("validateLiquorEspotWarningMsg",
					"String :" + liquorWarningMsg + ", contains IN-> " + expectedLiquorWarningMsg);
		}
		return this;
	}

	public ChooseDeliveryTimePage clickPostLoginGeoLocationChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("Geo_Location_Bar_Change_Link"), "Geo_Location_Bar_Change_Link");
		return new ChooseDeliveryTimePage();
	}

	public String getcurrentLoacaliseAddress() {
		String selectedAddress = "";
		selectedAddress = get_xpath_text(OR_OR.getProperty("Geo_Location_Bar_Localised_Suburb"),
				"Geo_Location_Bar_Localised_Suburb");
		testLog.info("currently user account is localise to:" + selectedAddress);
		return selectedAddress;
	}

	public HomePage validateLocalization(String newSelectedAddress, String currentLocalisedAddress) {
		testLog.info("Selected new address to localise is:" + newSelectedAddress);
		testLog.info("Current localised address in home page is:" + currentLocalisedAddress);
		if (newSelectedAddress.contains(currentLocalisedAddress)) {
			testLog.info("Successfully Localised to new selected address:" + currentLocalisedAddress);
		} else {
			testLog.error("Address do not match. Failed to localise to new address");
			assertCheck("validateHDLocalization", "Address do not match. Failed to localise to new HD address",
					newSelectedAddress);
		}
		return this;
	}

	public HomePage validateTemporarilyUnavailableProductTile() {
		ExplicitWait(OR_OR.getProperty("Temporarily_Unavailable_Tile"), "Temporarily_Unavailable_Tile", 5);
		String expectedUnavailableProductMsg = OR_OR.getProperty("Temporarily_Unavailable_Product_Msg");
		verify_xpath_text(OR_OR.getProperty("Temporarily_Unavailable_Product"), expectedUnavailableProductMsg);
		isElementPresent(OR_OR.getProperty("Temporarily_Unavailable_Product"), "Temporarily_Unavailable_Tile_Btn");
		boolean flag = isElementNotPresent(OR_OR.getProperty("Tile_Fat_Controller_First"), "Tile_Fat_Controller_First");
		if (flag == true) {
			testLog.info("Fat controller is NOT present in - Temporarily Unavailable product tile");

		} else {
			testLog.error("Fat controller is present in - Temporarily Unavailable product tile");
			assertCheck("validateTemporarilyUnavailableProductTile",
					"Fat controller is present in - Temporarily Unavailable product tile");
		}
		return this;
	}

	/*
	 * Function to add products to trolley from the array of products list
	 * provided searchskus - Provide the list of sku's separated by a ','
	 * movmet: "Y" - Keep adding products to trolley till it meets the MOV value
	 * : "N" - Just add one item to trolley and make sure MOV is not met
	 */

	public HomePage add_SKUs_To_Trolley(String searchskus, String movmet, double mov_amount) {

		String[] sku_list = searchskus.split(",");
		int i = 0;
		String product_type;

		getPages.getHomePage().clickTrolley().emptyTrolley();

		while (i < sku_list.length) {

			getPages.getHomePage().enterSearchItem(sku_list[i]).clickSearchButton();
			// V8 changes
			// wait(10000);
			product_type = getProductType();

			switch (product_type) {
			case "Temporarity unavailable":
				testLog.info("This product is temporarity unavailable. Skipping to adding this item to trolley");
				break;
			case "Liquor Espot":
				add_update_fatcontroller_verifyDC("4", "Add", sku_list[i]);
				break;
			case "Special weighted":
				wt_special_add_update_fatcontroller_verifyDC("4", "Add", sku_list[i]);
				break;
			case "Special Non-Weighted":
				special_add_update_fatcontroller_verifyDC("4", "Add", sku_list[i]);
				break;
			case "Non-special Weighted":
				wt_add_update_fatcontroller_verifyDC("4", "Add", sku_list[i]);
				break;
			case "Non-special Non-Weighted":
				add_update_fatcontroller_verifyDC("4", "Add", sku_list[i]);
				break;
			default:
				testLog.info("Prodduct Type:" + product_type + "is invalid");
				break;
			}

			if (movmet.equalsIgnoreCase("Y")
					&& (getPages.getTrolleyAndCheckoutPage().getTrolleyAmount()) >= mov_amount) {

				testLog.info("The Trolley total has met the minimum order value of " + mov_amount
						+ " and displayed as: " + Trolley_Total_Cost);
				break;
			}
			Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button");
			wait(2000);
			++i;
		}
		return this;
	}

	public String getProductType() {
		if (isElementVisible(OR_OR.getProperty("EverythingTab_Tile1_Temp_Unavailable"),
				"EverythingTab_Tile1_Temp_Unavailable")) {
			testLog.info("This product is temporarity unavailable. Skipping to adding this item to trolley");
			return "Temporarity unavailable";
		} else if (isElementPresent(OR_OR.getProperty("Liquor_Espot_Text"), "Liquor_Espot_Text")) {
			testLog.info("Espot is present in tile 1, Working with tile 2");
			return "Liquor Espot";
		} else {
			boolean bool_special_item = isElementPresent(OR_OR.getProperty("Everything_SP_Badge_value_tile1"),
					"Specials Icon");
			String product_text = get_xpath_text(OR_OR.getProperty("Tile1_qty_ValidCount"), "Tile1_qty_ValidCount");

			if (bool_special_item == true) {
				if (product_text.endsWith("g")) {
					testLog.info(
							"This product is a special weighted item. Starting the function to add this item to trolley");
					return "Special weighted";
				} else {
					testLog.info(
							"This product is a normal special item. Starting the function to add this item to trolley");
					return "Special Non-Weighted";
				}
			} else {
				testLog.info("This product is not a special item");
				if (product_text.endsWith("g")) {
					testLog.info(
							"This product is a non special weighted item. Starting the function to add this item to trolley");
					return "Non-special Weighted";
				} else {
					testLog.info("This product is a normal item. Starting the function to add this item to trolley");
					return "Non-special Non-Weighted";
				}
			}
		}
	}

	public boolean isSlotSelected() {
		String slotinfo = get_xpath_text(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time");
		if (slotinfo.contains("am") || slotinfo.contains("pm")) {
			return true;
		} else {
			return false;
		}
	}

	public HomePage validateFogottenPopupIsDisplayed() {
		ExplicitWait(OR_OR.getProperty("Forgotten_Something_Header"), "Forgotten_Something_Header", 5);
		verify_xpath_text(OR_OR.getProperty("Forgotten_Something_Header"),
				OR_OR.getProperty("MyAccount_Forgotten_Something_Popup_Header_Text"));

		boolean flag = isElementPresent(OR_OR.getProperty("Continue_To_Checkout_Button_New"),
				"Continue_To_Checkout_Button_New");
		if (flag == true) {
			testLog.info("***Continue to Checkout*** button is present on Forgotten something popup");

		} else {
			testLog.error(
					"Opps!Something went wrong!! ***Continue to Checkout*** button is NOT present on Forgotten someting popup");
			assertCheck("validateFogottenPopupIsDisplayed",
					"Opps!Something went wrong!! ***Continue to Checkout*** button is NOT present on Forgotten someting popup");
		}
		return this;
	}

	public HomePage validateForgottenPopupIsNotDisplayed() {
		boolean flag = isElementPresent(OR_OR.getProperty("Forgotten_Popup_Continue_To_Checkout_Btn"),
				"Forgotten_Popup_Continue_To_Checkout_Btn");
		if (flag == false) {
			testLog.info("***Fogotten popup*** is NOT displayed");

		} else {
			testLog.error("Opps!Something went wrong!! ***Forgotten popup*** button is displayed");
			assertCheck("validateForgottenPopupIsNotDisplayed",
					"Opps!Something went wrong!! ***Forgotten popup*** button is displayed");
		}
		return this;
	}

	public HomePage clickContinueCheckout() {
		Click_Button_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New");
		return this;
	}

	public HomePage add_SKUs_To_List(String searchskus) {
		String[] sku_list = searchskus.split(",");
		for (int i = 0; i < sku_list.length; i++) {
			wait(5000);
			getPages.getHomePage().enterSearchItem(sku_list[i]).clickSearchButton();
			if (isElementPresent(OR_OR.getProperty("Everything_Show_One_Result_Button"),
					"Everything_Show_One_Result_Button")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Show_One_Result_Button"),
						"Everything_Tile_Add_Remove_DropDown");
			}
			ExplicitWait(OR_OR.getProperty("Everything_Tile_Add_Remove_DropDown"),
					"Everything_Tile_Add_Remove_DropDown", 5);
			Click_Button_Xpath(OR_OR.getProperty("Everything_Tile_Add_Remove_DropDown"),
					"Everything_Tile_Add_Remove_DropDown");
			Click_Button_Xpath(OR_OR.getProperty("Everything_Add_Remove_List_Button"),
					"Everything_Add_Remove_List_Button");
			wait(1000);
		}
		return this;
	}

	public String getProductName() {
		wait(2000);
		// ExplicitWait(OR_OR.getProperty("Search_Tile_Product_Name"),
		// "Search_Tile_Product_Name", 5);
		String productName = get_xpath_text(OR_OR.getProperty("Search_Tile_Product_Name"), "Search_Tile_Product_Name");
		testLog.info("Searched Product name: ***" + productName);
		return productName;
	}

	public String getProductBrand() {
		wait(2000);
		// ExplicitWait(OR_OR.getProperty("Search_Tile_Brand_Name"),
		// "Search_Tile_Brand_Name", 5);
		String productBrand = get_xpath_text(OR_OR.getProperty("Search_Tile_Brand_Name"), "Search_Tile_Brand_Name");
		testLog.info("Searched Product brand: ***" + productBrand);
		return productBrand;
	}

	public HomePage clearMultiSearchList() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Hide_button"), "MultiSearchExpanded_Hide_button")) {
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_Manage_button"),
					"MultiSearchExpanded_Manage_button");
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withItem_ClearList_Link"),
					"MultiSearchManage_Overlay_withItem_ClearList_Link");
			ExplicitWait(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
					"MultiSearchManage_Overlay_withoutItem_Save&Search_Button", 20);
		}
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
				"MultiSearchManage_Overlay_withoutItem_Save&Search_Button");
		return this;
	}

	public HomePage clickMultiSearchHideButton() {
		// click and Verifying that Hide Caret is displayed after search
		if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Hide_button"), "MultiSearchExpanded_Hide_button")) {
			testLog.info("Hide button is displayed after searching for products as expected");
			Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_Hide_button"), "MultiSearchExpanded_Hide_button");

			if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Manage_button"),
					"MultiSearchExpanded_Manage_button")) {
				testLog.error("Multisearch is not hidden after clicking on hide button");

				assertCheck("validateMultiSearch", "Multisearch is not hidden after clicking on hide button",
						"MultiSearchExpanded_Hide_button");
			}
			// Verifying that View my Search result text is displayed
			verify_xpath_text(OR_OR.getProperty("MultiSearchLink"), OR_OR.getProperty("MultiSearchLink_WithItem_Text"));
		} else {
			testLog.error("Hide button is not displayed after searching for products");
			assertCheck("clickMultiSearchHideButton", "Hide button is not displayed after searching for products");
		}
		return this;
	}

	public HomePage clickViewMySearchList() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Hide_button"), "MultiSearchExpanded_Hide_button");
		isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Manage_button"), "MultiSearchExpanded_Manage_button");
		isElementNotPresent(OR_OR.getProperty("Results_For"), "Results_For");
		return this;
	}

	public HomePage clickMultiSearchFirstItem() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_First_Product"), "MultiSearchExpanded_First_Product");
		ExplicitWait(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct", 3);
		return this;
	}

	public String getMultiSearchExpandedFirstItem() {
		String FirstNameProduct = get_xpath_text(OR_OR.getProperty("MultiSearchExpanded_First_ProductName"),
				"MultiSearchExpanded_First_ProductName");
		FirstNameProduct = FirstNameProduct.replaceAll("Search for", "").trim();
		return FirstNameProduct;
	}

	public HomePage addFirstProduct() {
		scrollTo_Xpath(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct", 4);
		Click_Button_Xpath(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct");
		return this;
	}

	public HomePage validateMultiSearch() {

		String[] firstProduct = search_1.split(",");

		for (String item : firstProduct) {
			// click
			clickMultiSearchFirstItem();
			// String First_Item = getMultiSearchExpandedFirstItem();
			// testLog.info("product displayed name "+First_Item);
			String product_name = getProductName();
			// check if search result are correct
			if (!(product_name.toLowerCase().contains(item.toLowerCase()))) {
				testLog.error("Search result product: " + product_name + " does not match multisearch product:" + item);
				assertCheck("ValidateMultiSearchIsFirstItemActiveByDefault",
						"Search result product: " + product_name + " does not match multisearch product:" + item);
			} else {
				testLog.info("Result is as expected.");
			}
			addFirstProduct();
			scrollTo_Xpath(OR_OR.getProperty("Geo_Location_Bar"), "Geo_Location_Bar", 1);
			String AddedProduct = get_xpath_text(OR_OR.getProperty("MultiSearchExpanded_Added_ProductName"),
					"MultiSearchExpanded_Added_ProductName");
			AddedProduct = AddedProduct.substring(0, AddedProduct.indexOf("Already added")).trim();
			testLog.info("product added is " + AddedProduct);

			// clicking on hide button and verifying that added term is removed
			// COL-515_TC_019
			clickMultiSearchHideButton();
			if (isElementNotPresent(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink")) {
				clickViewMySearchList();
			} else {
				testLog.info("Added product is not present as expected");
				break;
			}
			if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Added_Product"),
					"MultiSearchExpanded_Added_Product")) {
				testLog.error("Added product is still present");
				assertCheck("ValidateMultiSearchIsFirstItemActiveByDefault", "Added product is still present");
			} else {
				testLog.info("Added product is not present as expected");
			}
		}
		return this;
	}

	public HomePage ValidateMultiSearchIsFirstItemActiveByDefault() {
		// entered Search term verifying that first term is active search term
		String[] firstProduct = search_1.split(",");
		testLog.info("first element of search term is" + firstProduct[0]);
		wait(2000);
		String result = get_xpath_text(OR_OR.getProperty("Results_For"), "Results_For");
		if (result.contains(firstProduct[0])) {
			testLog.info("First Srarch term is active search term");
		} else {
			testLog.error("First Srarch term is not active search term and " + result + " is displayed instead of"
					+ firstProduct[0]);
			assertCheck("ValidateMultiSearchIsFirstItemActiveByDefault",
					"First Srarch term is not active search term and " + result + " is displayed instead of"
							+ firstProduct[0]);
		}
		return this;
	}

	public void MultiSearchItemAdded() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchExpanded_First_Product"), "MultiSearchExpanded_First_Product");
		ExplicitWait(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct", 3);
		String FirstNameProduct = get_xpath_text(OR_OR.getProperty("MultiSearchExpanded_First_ProductName"),
				"MultiSearchExpanded_First_ProductName");
		FirstNameProduct = FirstNameProduct.replaceAll("Search for", "").trim();
		testLog.info("product displayed name " + FirstNameProduct);

		scrollTo_Xpath(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct", 4);
		Click_Button_Xpath(OR_OR.getProperty("MultiSearch_Add_FirstProduct"), "MultiSearch_Add_FirstProduct");
		scrollTo_Xpath(OR_OR.getProperty("Geo_Location_Bar"), "Geo_Location_Bar", 1);
		String AddedProduct = get_xpath_text(OR_OR.getProperty("MultiSearchExpanded_Added_ProductName"),
				"MultiSearchExpanded_Added_ProductName");
		AddedProduct = AddedProduct.substring(0, AddedProduct.indexOf("Already added")).trim();
		testLog.info("product added is " + AddedProduct);

	}

	public void MultiSearchItemRemoved() {
		if (isElementPresent(OR_OR.getProperty("MultiSearchExpanded_Added_Product"),
				"MultiSearchExpanded_Added_Product")) {
			testLog.error("Added product is still present");
			assertCheck("MultiSearchItemRemoved", "Added product is still present");
		} else {
			testLog.info("Added product is not present as expected");
		}
	}

	public HomePage clickSearchForListOfItems() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchLink"), "MultiSearchLink");
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Heading"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_SubHeading"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_SubHeading_Text"));
		verify_xpath_text(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_PopupBody"),
				OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_PopupBody_Text"));
		return this;
	}

	public HomePage sendItemsToMultiSearch(String sheetname) {
		String searchskus = search_1;
		Sendkey_xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_EditBox"), searchskus,
				"MultiSearchManage_Overlay_withoutItem_EditBox");
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
				"MultiSearchManage_Overlay_withoutItem_Save&Search_Button");
		String[] firstProduct = searchskus.split(",");
		testLog.info("first element of search term is" + firstProduct[0]);
		ExplicitWait(OR_OR.getProperty("Results_For"), "Results_For", 20);
		ExplicitWait(OR_OR.getProperty("Everything_Sortedby_ChangeLink"), "Everything_Sortedby_ChangeLink", 20);
		String result = get_xpath_text(OR_OR.getProperty("Results_For"), "Results_For");
		testLog.info("result is " + result);
		return this;
	}

	public HomePage sendItemsToMultiSearch1() {
		// String searchskus = datatable.getCellData(sheetname, "search1",
		// RowNo);
		Sendkey_xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_EditBox"), search_1,
				"MultiSearchManage_Overlay_withoutItem_EditBox");
		return this;
	}

	public HomePage clickMultiSearchSaveAndSearchButton() {
		Click_Button_Xpath(OR_OR.getProperty("MultiSearchManage_Overlay_withoutItem_Save&Search_Button"),
				"MultiSearchManage_Overlay_withoutItem_Save&Search_Button");
		ExplicitWait(OR_OR.getProperty("Results_For"), "Results_For", 20);
		ExplicitWait(OR_OR.getProperty("Everything_Sortedby_ChangeLink"), "Everything_Sortedby_ChangeLink", 20);
		return this;
	}

	public HomePage validateShoppingListSortedByText(String sort_option) {
		if (sort_option.equalsIgnoreCase("TopProducts")) {
			verify_xpath_contains_text(OR_OR.getProperty("Shopping_List_Sorted_By"), "Sorted by: top products");
		} else if (sort_option.equalsIgnoreCase("special")) {
			verify_xpath_contains_text(OR_OR.getProperty("Shopping_List_Sorted_By"), "Sorted by: on special");
		} else if (sort_option.equalsIgnoreCase("lowest unit price")) {
			verify_xpath_contains_text(OR_OR.getProperty("Shopping_List_Sorted_By"), "Sorted by: lowest unit price");
		} else if (sort_option.equalsIgnoreCase("lowest price")) {
			verify_xpath_contains_text(OR_OR.getProperty("Shopping_List_Sorted_By"), "Sorted by: lowest price");
		} else if (sort_option.equalsIgnoreCase("brandatoz")) {
			verify_xpath_contains_text(OR_OR.getProperty("Specials_Sorted_By"), "Sorted by: brand (a-z)");
		}
		return this;
	}

	public HomePage clickShoppingListChangeButton() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Sortedby_Change_Link"),
				"Shopping_List_Sortedby_Change_Link");
		return this;
	}

	public HomePage ClickShoppingListSave() {
		Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Save_Button"), "Shopping_List_Save_Button");
		ExplicitWait(OR_OR.getProperty("Shopping_List_Products_Brand_Name"), "Shopping_List_Products_Brand_Name", 10);
		return this;
	}

	public HomePage clickShoppingListSortOption(String sort_option) {
		if (sort_option.equalsIgnoreCase("TopProducts")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Top_Products"), "Shopping_List_Top_Products");
		} else if (sort_option.equalsIgnoreCase("Special")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_On_Special"), "Shopping_List_On_Special");
		} else if (sort_option.equalsIgnoreCase("LowestUnit_Price")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Lowest_Unit_Price"), "Shopping_List_Lowest_Unit_Price");
		} else if (sort_option.equalsIgnoreCase("Lowest_Price")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Lowest_Price"), "Shopping_List_Lowest_Price");
		} else if (sort_option.equalsIgnoreCase("Brand(A-Z)")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Product_Brand"), "Shopping_List_Product_Brand");
		} else if (sort_option.equalsIgnoreCase("ProductName(A-Z)")) {
			Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Product_Name"), "Shopping_List_Product_Name");
		}
		return this;
	}

	public HomePage validateShoppingListSpecialSort() {

		String tab_spl_bd_nval = OR_OR.getProperty("Shopping_List_Specials_Badge_nvalue");
		String productname_xpath_nvalue = OR_OR.getProperty("Shopping_List_ProductName_xpath_nvalue");
		String Merch_Assoc_nvalue = OR_OR.getProperty("Shopping_List_Merch_Assoc_nvalue");
		int product_count = getXpathCount(OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter"),
				"Shopping_List_ProductTile_xpath_Counter");
		int loop_count = 0;

		if (product_count >= 6) {
			loop_count = 5;
		} else {
			loop_count = product_count - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			boolean m_assoc_flag = false;
			boolean pname_1_flag = false;
			boolean pname_2_flag = false;

			String m_assoc = Merch_Assoc_nvalue;
			m_assoc = m_assoc.replace("nvalue", istrvalue);
			m_assoc_flag = isElementPresent(m_assoc, "Merch_Assoc_nvalue");
			if (true == m_assoc_flag) {
				testLog.info("Merc association is present for this product. Skipping next/ associated product");
				jvalue = jvalue + 1;
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}

			String pname = productname_xpath_nvalue;
			String pname_1 = pname.replace("nvalue", istrvalue);
			String pname_2 = pname.replace("nvalue", jstrvalue);
			pname_1_flag = isElementNotPresent(pname_1, "productname_xpath_nvalue");
			if (true == pname_1_flag) {
				testLog.info("Product tile is not present at position: " + ivalue
						+ " due to wrapping of the merc association products appearing on last position");
				ivalue = ivalue + 1;
				jvalue = jvalue + 1;
				loop_count = loop_count + 1;
				istrvalue = Integer.toString(ivalue);
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}
			pname_2_flag = isElementNotPresent(pname_2, "productname_xpath_nvalue");
			if (true == pname_2_flag) {
				testLog.info("Product tile is not present at position: " + ivalue
						+ " due to wrapping of the merc association products appearing on last position");
				jvalue = jvalue + 1;
				jstrvalue = Integer.toString(jvalue);
				testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);
			}

			String sp_icon = tab_spl_bd_nval;
			String sp_icon_1 = sp_icon.replace("nvalue", istrvalue);
			String sp_icon_2 = sp_icon.replace("nvalue", jstrvalue);
			boolean boolval1 = isElementPresent(sp_icon_1, "Specials Icon");
			boolean boolval2 = isElementPresent(sp_icon_2, "Specials Icon");
			if (boolval1 == true) {
				testLog.info("Specials Icon is present for tile at position: " + ivalue);

			} else {
				testLog.warn("Specials Icon is not present for tile at position: " + ivalue);

			}
			if (boolval2 == true) {
				testLog.info("Specials Icon is present for tile at position: " + jvalue);

			} else {
				testLog.warn("Specials Icon is not present for tile at position: " + jvalue);

			}

			if (true == m_assoc_flag) {
				ivalue = ivalue + 1;
				loop_count = loop_count + 1;
			}
			if (true == pname_2_flag) {
				ivalue = ivalue + 1;
				loop_count = loop_count + 1;
			}
			// End of new code
		}

		return this;
	}

	public HomePage validateShoppingListUnitPriceSorting() {
		String tab_up_nval = OR_OR.getProperty("Shopping_List_UnitPrice_N");
		int tabcounter = getXpathCount(OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter"),
				"Shopping_List_ProductTile_xpath_Counter");
		testLog.info("tabcounter is : " + tabcounter);
		int loop_count;
		if (tabcounter >= 6) {
			loop_count = 5;
		} else {
			loop_count = tabcounter - 1;
		}

		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {
			int jvalue = ivalue + 1;
			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(jvalue);
			testLog.info("istrvalue, jstrvalue: " + istrvalue + " " + jstrvalue);

			wait(3000);
			String up = tab_up_nval;
			String up_1 = up.replace("nvalue", istrvalue);
			String up_2 = up.replace("nvalue", jstrvalue);

			String str_up_1 = driver.findElement(By.xpath(up_1)).getText();
			String str_up_2 = driver.findElement(By.xpath(up_2)).getText();

			if (str_up_1 != null && !str_up_1.isEmpty()) {
				str_up_1 = str_up_1.substring(str_up_1.lastIndexOf("$") + 1);
				if (str_up_1.contains(" ")) {
					str_up_1 = str_up_1.substring(0, str_up_1.indexOf(" "));
				}
			} else {
				str_up_1 = "0";
				testLog.warn("No unit price is displayed for product at " + istrvalue + " position");

			}
			if (str_up_2 != null && !str_up_2.isEmpty()) {
				str_up_2 = str_up_2.substring(str_up_2.lastIndexOf("$") + 1);
				if (str_up_2.contains(" ")) {
					str_up_2 = str_up_2.substring(0, str_up_2.indexOf(" "));
				}
			} else {
				str_up_2 = "0";
				testLog.warn("No unit price is displayed for product at " + jstrvalue + " position");
			}
			double doub_up_1 = Double.valueOf(str_up_1);
			double doub_up_2 = Double.valueOf(str_up_2);

			if (doub_up_1 <= doub_up_2) {
				testLog.info("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);

			} else {
				testLog.error("The tile no." + ivalue + " with unit price: " + doub_up_1
						+ " is not less than or equal to the tile no." + jvalue + " with unit price: " + doub_up_2);
				assertCheck("validateShoppingListUnitPriceSorting",
						"The tile no." + ivalue + " with unit price: " + doub_up_1
								+ " is not less than or equal to the tile no." + jvalue + " with unit price: "
								+ doub_up_2);
			}
		}
		return this;
	}

	public HomePage enter_Value_Into_FiveorMore(String AddorUpdate, String index) {
		Click_Button_Xpath(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown");
		wait(2000);
		if (AddorUpdate.equals("Add")) {
			wait(2000);
			Click_Button_Xpath(OR_OR.getProperty("Dropdown_List_5orMore"), "Dropdown_List_5orMore");
			ExplicitWait(OR_OR.getProperty("Dropdown_List_5orMore_Qty"), "Dropdown_List_5orMore_Qty", 8);
			wait(3000);
			Sendkey_xpath(OR_OR.getProperty("Dropdown_List_5orMore_Qty"), index, "Dropdown_List_5orMore_Qty");
		} else {
			Sendkey_xpath(OR_OR.getProperty("Dropdown_List_5orMore_Qty"), index, "Dropdown_List_5orMore_Qty");
		}
		Click_Button_Xpath(OR_OR.getProperty("Dropdown_5orMore_AddorUpdate"), "Dropdown_5orMore_AddorUpdate");
		wait(3000);
		return this;
	}

	public HomePage removeProductQtyFromFC() {
		ExplicitWait(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown", 25);
		Click_Button_Xpath(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown");
		wait(2000);
		String dropdowns = OR_OR.getProperty("Tile1_Dropdown_SelectN");
		for (int ivalue = 5; ivalue >= 2; ivalue--) {
			String istrvalue = Integer.toString(ivalue);
			String dropdown1 = dropdowns.replace("nvalue", istrvalue);
			String strdropdown_1 = driver.findElement(By.xpath(dropdown1)).getText();
			String qty = strdropdown_1.substring(0, 1);
			driver.findElement(By.xpath(dropdown1)).click();
			wait(2000);
			String trolleyQty = get_xpath_text(OR_OR.getProperty("Trolley_Quantity"), "Trolley_Quantity");
			if ((qty.equals(trolleyQty))) {
				testLog.info("Fatcontroller qty and trolley qty both are same ");
			} else {
				testLog.error("Fatcontroller qty and trolley qty both are same ");
				assertCheck("removeProductQtyFromFC", "Fatcontroller qty and trolley qty both are same ");
			}
			Click_Button_Xpath(OR_OR.getProperty("First_Product_Dropdown"), "First_Product_Dropdown");
		}
		return this;
	}

	public HomePage removeProductQtyFromDC() {
		ExplicitWait(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown", 25);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown");
		wait(2000);
		String dropdowns = OR_OR.getProperty("Trolley_DropDown_SelectN");
		for (int ivalue = 5; ivalue >= 2; ivalue--) {
			String istrvalue = Integer.toString(ivalue);
			String dropdown1 = dropdowns.replace("nvalue", istrvalue);
			String strdropdown_1 = driver.findElement(By.xpath(dropdown1)).getText();
			String qty = strdropdown_1.substring(0, 1);
			driver.findElement(By.xpath(dropdown1)).click();
			wait(2000);
			String trolleyQty = get_xpath_text(OR_OR.getProperty("Trolley_Quantity"), "Trolley_Quantity");
			if ((qty.equals(trolleyQty))) {
				testLog.info("Dietcontroller qty and trolley qty both are same ");
			} else {
				testLog.error("Dietcontroller qty and trolley qty both are same ");
				assertCheck("removeProductQtyFromDC", "Dietcontroller qty and trolley qty both are same ");
			}
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown");
		}
		return this;
	}

	public HomePage clickShowFilterList() {
		Click_Button_Xpath(OR_OR.getProperty("Shopping_List_Show_Filter_Btn"), "Shopping_List_Show_Filter_Btn");
		verify_xpath_text(OR_OR.getProperty("Shopping_List_Heading_FilterProducts"),
				OR_OR.getProperty("Shopping_List_Heading_FilterProducts_Text"));
		return this;
	}

	public HomePage clickFilterByBrandList() {
		ExplicitWait(OR_OR.getProperty("Shopping_List_FilterBy_Brand"), "Shopping_List_FilterBy_Brand", 10);
		Click_Button_Xpath(OR_OR.getProperty("Shopping_List_FilterBy_Brand"), "Shopping_List_FilterBy_Brand");
		return this;
	}

	public HomePage validateListFilterResult() {
		// get total no of products compare with filtered count
		int xpathCount = 0, merc_assoc_count_1 = 0, calculation = 0, productname_counter;
		int sum = 0;
		int ipagecount = 1;
		String pagination = OR_OR.getProperty("Load_More_Button");

		String nth_brand_name = OR_OR.getProperty("Shopping_List_BrandName_xpath_nvalue");
		String ProductTile_xpath_Counter = OR_OR.getProperty("Shopping_List_ProductTile_xpath_Counter");
		String ProductName_xpath_Counter = OR_OR.getProperty("Shopping_List_ProductName_xpath_Counter");
		String merchTile_Xpath_counter = OR_OR.getProperty("Shopping_List_Merch_Assoc_Box");

		if (filtered_productCount > 48) {
			if (isElementPresent(OR_OR.getProperty("pagecount"), "pagecount")) {
				List<WebElement> pagecount = driver.findElements(By.xpath(OR_OR.getProperty("pagecount")));
				ipagecount = pagecount.size();
			} else {
				testLog.error("All filtered products are not visible");
			}
		}

		for (int i = 1; i <= ipagecount; i++) {

			xpathCount = getXpathCount(ProductTile_xpath_Counter, "ProductTile_xpath_Counter");

			merc_assoc_count_1 = getXpathCount(merchTile_Xpath_counter, "Shopping_List_Merch_Assoc_Box");
			productname_counter = getXpathCount(ProductName_xpath_Counter, "Shopping_List_ProductName_xpath_Counter");

			calculation = xpathCount - merc_assoc_count_1;
			if ((calculation == productname_counter)) {
				testLog.info("ProductTile Count and BrandName is correctly displayed.");
			} else {
				testLog.error("ProductTile Count and BrandName is not correct.");
				status = 1;
			}

			sum = sum + calculation;

			for (int j = 1; j <= calculation; ++j) {

				String bname = nth_brand_name.replace("nvalue", Integer.toString(j));
				String bname_txt = driver.findElement(By.xpath(bname)).getText();
				if (!bname_txt.equals(BrandName)) {
					testLog.error(
							"Brand name :" + bname_txt + "is not equal to the Filter applied for brand:" + BrandName);
					status = 1;
				} else {
					testLog.info("The tile no. " + j + " with brand name: " + bname_txt + " is same as filtered brand: "
							+ BrandName);
				}
			}

			if ((i + 1) <= ipagecount) {
				String istr = Integer.toString(i + 1);
				pagination = pagination.replaceAll("nvalue", istr);
				driver.findElement(By.xpath(pagination)).click();
			}
		}

		if (sum == filtered_productCount) {
			testLog.info("Number of Products displayed " + sum + " is equal to the filtered products count"
					+ filtered_productCount);

		} else {
			testLog.error("Number of product displayed on the page" + sum
					+ " are not matching to the filtered products count " + filtered_productCount);
			assertCheck("validateListFilterResult", "Number of product displayed on the page" + sum
					+ " are not matching to the filtered products count " + filtered_productCount);
		}

		return this;
	}

	public HomePage add_SKUs_To_Trolley(String searchskus) {
		String[] sku_list = searchskus.split(",");

		for (int i = 0; i < sku_list.length; i++) {
			wait(5000);

			// Clear_Text(OR_OR.getProperty("Search_Input_Box"),
			// "Search_Input_Field");
			getPages.getHomePage().enterSearchItem(sku_list[i]).clickSearchButton();
			if (isElementPresent(OR_OR.getProperty("Everything_Show_One_Result_Button"),
					"Everything_Show_One_Result_Button")) {
				Click_Button_Xpath(OR_OR.getProperty("Everything_Show_One_Result_Button"),
						"Everything_Tile_Add_Remove_DropDown");
			}
			// V8 changes
			wait(10000);
			ExplicitWait(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController", 5);
			Click_Button_Xpath(OR_OR.getProperty("First_Product_FatController"), "First_Product_FatController");
			ExplicitWait(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button", 10);
			Click_Button_Xpath(OR_OR.getProperty("Clear_Search_Button"), "Clear_Search_Button");
			wait(2000);
		}
		return this;
	}

	public HomePage removeFirstProductFromDC() {
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley_Text");
		ExplicitWait(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown", 25);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select0"), "RHST_Tile1_Dropdown_Select0");
		wait(4000);
		return this;
	}

	// Verifying the Multi Buy Offer Price with Add Product Button Price
	public HomePage validateMultiSave_SingleSKUPrice(boolean singleQuantity) {

		String multiBuyAmount = "", multiBuyQuantity = "", multiSaveOffer = "", addButtonTotalText = "";
		// Getting the Multi Save Quantity and Price
		if (isElementPresent(OR_OR.getProperty("MultiBuy_Units"), "MultiBuy_Units")) {

			multiBuyQuantity = get_xpath_text(OR_OR.getProperty("MultiBuy_Units"), "Multi Buy Units");
			multiBuyAmount = get_xpath_text(OR_OR.getProperty("MultiBuy_Price"), "MultiBuy_Price");
			if (singleQuantity == true) {
				multiSaveOffer = "Add " + multiBuyQuantity + " $"
						+ String.format("%.2f", Double.valueOf(multiBuyAmount));
			} else {
				multiSaveOffer = "Pick " + multiBuyQuantity + " for $"
						+ String.format("%.2f", Double.valueOf(multiBuyAmount));
			}

			testLog.info("The multi buy offer text is: " + multiSaveOffer);
			// Getting the Add Product Button Text
			String addButtonQuantity = get_xpath_text(OR_OR.getProperty("AddProductButton_Quantity"),
					"AddProductButton_Quantity");
			String addButtonPrice = get_xpath_text(OR_OR.getProperty("AddProductButton_Price"),
					"AddProductButton_Price");
			String addButtonProductText = get_xpath_text(OR_OR.getProperty("AddProductButton_ProductText"),
					"Add button product text");

			if (singleQuantity == true) {
				addButtonTotalText = "Add " + addButtonQuantity + " " + addButtonProductText + " " + addButtonPrice;
			} else {
				addButtonTotalText = "Pick Any " + addButtonQuantity + " " + addButtonProductText + " "
						+ addButtonPrice;
			}

			testLog.info("The add button text is: " + addButtonTotalText);

			if (multiSaveOffer.equals(addButtonTotalText)) {
				testLog.info("The multi buy offer value is: " + multiSaveOffer
						+ " and it is matching with Add products button text: " + addButtonTotalText);
			} else {
				testLog.error("Multi buy offer value " + multiSaveOffer
						+ " is not matching with the add product button text " + addButtonTotalText);
				assertCheck("validateMultiSave_SingleSKUPrice", "Multi buy offer value " + multiSaveOffer
						+ " is not matching with the add product button text " + addButtonTotalText);
			}
		}

		else {
			testLog.error("Multi buy offer value is not available for the product");
			assertCheck("validateMultiSave_SingleSKUPrice", "Multi buy offer value is not available for the product");
		}

		return this;
	}

	// Validating the single item added priced
	public HomePage validateSingleItemPrice() {

		if (isElementPresent(OR_OR.getProperty("PerUnitPrice_DollarValue"), "Product price per unit")) {
			String singleUnitPrice = "", dollarValue = "", centValue = "";
			String addButton_Price = "";
			dollarValue = get_xpath_text(OR_OR.getProperty("PerUnitPrice_DollarValue"),
					"Dollar value of Product price");
			centValue = get_xpath_text(OR_OR.getProperty("PerUnitPrice_CentValue"), "Cent value of Product price");
			singleUnitPrice = dollarValue + centValue;
			testLog.info("The item price per unit is: " + singleUnitPrice);
			addButton_Price = get_xpath_text(OR_OR.getProperty("AddProductButton_Price"), "AddProductButton_Price");
			addButton_Price = addButton_Price.replace("$", "").trim();
			testLog.info("The price for added single Product is: " + addButton_Price);

			if (singleUnitPrice.equals(addButton_Price)) {

				testLog.info("The product price per unit is: " + singleUnitPrice
						+ " is matching with price of single product added in trolly: " + addButton_Price);
			} else {
				testLog.error("Product price per unit is:  " + singleUnitPrice
						+ " is not matching with price of single item added: " + addButton_Price + "");
				assertCheck("validateSingleItemPrice", "Product price per unit is:  " + singleUnitPrice
						+ " is not matching with price of single item added: " + addButton_Price + "");
			}
		} else {
			testLog.error("Product per unit price is not visible");
			assertCheck("validateSingleItemPrice", "product price per unit is not visible");
		}
		return this;
	}

	// Validating the single item added priced
	public HomePage validateMultipleItemPrice(int itemQuantity) {

		if (isElementPresent(OR_OR.getProperty("PerUnitPrice_DollarValue"), "Product price per unit")) {
			String singleUnitPrice = "", dollarValue = "", centValue = "";
			String addButton_Price = "", savings = "";
			dollarValue = get_xpath_text(OR_OR.getProperty("PerUnitPrice_DollarValue"),
					"Dollar value of Product price");
			centValue = get_xpath_text(OR_OR.getProperty("PerUnitPrice_CentValue"), "Cent value of Product price");
			singleUnitPrice = dollarValue + centValue;
			Double totlaPriceOfProducts = Double.valueOf(singleUnitPrice) * itemQuantity;
			testLog.info("Total price of the products are: " + totlaPriceOfProducts);
			addButton_Price = get_xpath_text(OR_OR.getProperty("AddProductButton_Price"), "AddProductButton_Price");
			addButton_Price = addButton_Price.replace("$", "").trim();
			savings = get_xpath_text(OR_OR.getProperty("ProductSavingAmount"), "Savings on buying multiple products");
			savings = savings.replace("$", "");
			Double totalAmountPostAddingProductsInTrolly = Double.valueOf(addButton_Price) + Double.valueOf(savings);
			testLog.info("The price for added multiple Products in trolly are after adding savings: "
					+ totalAmountPostAddingProductsInTrolly);

			if (totlaPriceOfProducts.equals(totalAmountPostAddingProductsInTrolly)) {

				testLog.info("The amount of 2 products without savings are: " + totlaPriceOfProducts
						+ " is matching with price of products added in trolly without savings: "
						+ totalAmountPostAddingProductsInTrolly);
			} else {
				testLog.error("Product price per unit is:  " + totlaPriceOfProducts
						+ " is not matching with price of single item added: " + totalAmountPostAddingProductsInTrolly
						+ "");
				assertCheck("validateSingleItemPrice",
						"Product price per unit is:  " + totlaPriceOfProducts
								+ " is not matching with price of single item added: "
								+ totalAmountPostAddingProductsInTrolly + "");
			}
		} else {
			testLog.error("Product per unit price is not visible");
			assertCheck("validateSingleItemPrice", "product price per unit is not visible");
		}
		return this;
	}

	public HomePage validateSuccessGreenIcon_MultiBuy() {
		isElementPresent(OR_OR.getProperty("MultiBuy_SuccessIcon"), "MultiBuy_SuccessIcon");
		String expectedColor = "#007d1d";
		String clsValue = driver.findElement(By.xpath(OR_OR.getProperty("MultiBuy_SuccessIcon")))
				.getCssValue("border-color");
		String colorHexCode = Color.fromString(clsValue).asHex();
		if (colorHexCode.equals(expectedColor)) {
			testLog.info(
					"When multi buy product is added to trolly then it showing in green color border, having HEX Code: "
							+ colorHexCode);
		} else {
			testLog.info(
					"When multi buy product is added to trolly then it is not showing in green color border, having HEX Code: "
							+ colorHexCode);
			assertCheck("validateSuccessGreenIcon",
					"When multi buy product is added to trolly then it is not showing in green color border, having HEX Code: "
							+ colorHexCode);
		}
		return this;
	}

	// Validating the price details showing in trolly with the Product pricing
	// details
	public HomePage validateProductPricing_withTrollyAmount() {

		String multiBuyAmount = "", multiBuyQuantity = "", multiSaveOffer = "";
		String trolly_SingleItemAmount = "", trolly_OfferAmount = "";
		// Getting the Multi Save Quantity and Price
		if (isElementPresent(OR_OR.getProperty("MultiBuy_Units"), "MultiBuy_Units")) {

			multiBuyQuantity = get_xpath_text(OR_OR.getProperty("MultiBuy_Units"), "Multi Buy Units");
			multiBuyAmount = get_xpath_text(OR_OR.getProperty("MultiBuy_Price"), "MultiBuy_Price");
			multiSaveOffer = multiBuyQuantity + " $" + String.format("%.2f", Double.valueOf(multiBuyAmount));

			if (!multiSaveOffer.contains("for")) {
				multiSaveOffer = multiBuyQuantity + " for $" + String.format("%.2f", Double.valueOf(multiBuyAmount));
			}
			testLog.info("The multi buy offer text is: " + multiSaveOffer);
			// Getting the Add Product Button Text
			String addButtonQuantity = get_xpath_text(OR_OR.getProperty("AddProductButton_Quantity"),
					"AddProductButton_Quantity");
			String addButtonPrice = get_xpath_text(OR_OR.getProperty("AddProductButton_Price"),
					"AddProductButton_Price");
			String addButtonProductText = get_xpath_text(OR_OR.getProperty("AddProductButton_ProductText"),
					"Add button product text");

			String addButtonTotalText = addButtonQuantity + " " + addButtonProductText + " " + addButtonPrice;
			testLog.info("The add button text is: " + addButtonTotalText);

			// Checking trolly Pricing Details
			clickTrolley();
			wait(5000);
			String trollyAddedProductQnty = get_xpath_text(OR_OR.getProperty("TrollyAddedProduct_Quantity"),
					"Quantity of Product added in trolly");
			String trollyAddedProductAmount = get_xpath_text(OR_OR.getProperty("RHST_Tile1_FC_MoreElements_Price"),
					"Amount of added product in trolly");
			trolly_SingleItemAmount = trollyAddedProductQnty + " for " + trollyAddedProductAmount;

			if (trolly_SingleItemAmount.equals(addButtonTotalText)) {

				testLog.info("The amount for single unit of Product is same at home page and in trolly.");
			} else {
				testLog.error("The cost of single unit of product is: " + addButtonTotalText
						+ " is not matching with trolly amount: " + trolly_SingleItemAmount + "");
				assertCheck("validateProductPricing_withTrollyAmount", "The cost of single unit of product is: "
						+ addButtonTotalText + " is not matching with trolly amount: " + trolly_SingleItemAmount + "");
			}

			trolly_OfferAmount = get_xpath_text(OR_OR.getProperty("TrollyAdedProdct_OfferAmount"),
					"Offer amount for added product");
			if (trolly_OfferAmount.equals(multiSaveOffer)) {

				testLog.info("The offer amount for single unit of Product is same at home page and in trolly.");
			} else {
				testLog.error("The offer amount of product at homepage is: " + multiSaveOffer
						+ " is not matching with offer amount available in trolly: " + trolly_OfferAmount + "");
				assertCheck("validateProductPricing_withTrollyAmount",
						"The offer amount of product at homepage is: " + multiSaveOffer
								+ " is not matching with offer amount available in trolly: " + trolly_OfferAmount + "");
			}
		}

		else {
			testLog.error("Multi buy offer value is not available for the product");
			assertCheck("validateProductPricing_withTrollyAmount",
					"Multi buy offer value is not available for the product");
		}

		return this;
	}

	public int countOfTrolleyItems() {

		testLog.info("Getting the count of items in trolley:-");
		int totalProducts = 0;
		wait(5000);
		if (isElementPresent(OR_OR.getProperty("Trolley_Products_Container"), "Trolley_Products_Container")) {
			totalProducts = getXpathCount(OR_OR.getProperty("Trolley_Items_Count"), "Trolley_Items_Count");
		} else {

			testLog.error("No Product is available in the trolley");
			assertCheck("countOfTrolleyItems", "OOPS!!, No product is available in the trolley.");
		}

		return totalProducts;
	}
}
