package com.swiftshop.pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.swiftshop.main.FunLibrary;

public class TrolleyAndCheckoutPage extends FunLibrary {

	// Function to empty the trolley if the trolley total is greater then $0.00
	public TrolleyAndCheckoutPage emptyTrolley() {
		// Click on trolley icon and verify that empty trolley is displayed
		double Trolley_Total_Cost = getTrolleyAmount();
		if (Trolley_Total_Cost == 0.0) {
			testLog.info("The Trolley is already empty and displaying trolley total as: " + Trolley_Total_Cost);
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Text"), "Trolley_Text");
		} else {
			// Expand RHST and select Remove All button
			Click_Button_Xpath(OR_OR.getProperty("Remove_All_Button"), "Remove_All_Button");
			wait(2000);
			// Verify the Remove all confirmation message is displayed on the
			// screen
			verify_xpath_text(OR_OR.getProperty("Are_You_Sure_To_Remove_Text"),
					"Are you sure you want to remove everything from your trolley?");
			// Again click on Remove all button and select Yes, remove
			// everything button
			Click_Button_Xpath(OR_OR.getProperty("Remove_Everything_Button"), "Remove_Everything_Button");
			// Verify the Empty Trolley message
			verify_xpath_text(OR_OR.getProperty("Empty_Trolley_Message"), OR_OR.getProperty("Empty_Trolley_Text"));
			wait(4000);
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		}
		return this;
	}

	public TrolleyAndCheckoutPage clickProceedToCheckOutButton() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Proceed_To_Checkout_Button"), "Proceed_To_Checkout_Button");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR_OR.getProperty("Checkout_Page_Loader"))));
		return this;
	}

	public TrolleyAndCheckoutPage trolleyContentHandler(int optin) {
		boolean trolley_content_flag1 = isElementVisible(
				OR_OR.getProperty("SB_Trolley_Content_Display_Footer_Cnt_Button"),
				"SB_Trolley_Content_Display_Footer_Cnt_Button");
		boolean trolley_content_flag2 = isElementVisible(
				OR_OR.getProperty("SB_Trolley_Content_Display_OptIn_Cnt_Button"),
				"SB_Trolley_Content_Display_OptIn_Cnt_Button");
		if (trolley_content_flag1 == true || trolley_content_flag2 == true) {
			testLog.info("Trolley Content Displayed in Superbar appeared");
			wait(2000);
			if (trolley_content_flag1 == true) {
				scrollTo_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_Footer_Cnt_Button"),
						"SB_Trolley_Content_Display_Footer_Cnt_Button", 1);
				Click_Button_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_Footer_Cnt_Button"),
						"SB_Trolley_Content_Display_Footer_Cnt_Button");
			} else {

				int n = optin;

				switch (n) {
				case 0:
					scrollTo_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_OptOut_Link"),
							"SB_Trolley_Content_Display_OptOut_Link", 1);
					Click_Button_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_OptOut_Link"),
							"SB_Trolley_Content_Display_OptOut_Link");
					break;
				case 1:
					scrollTo_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_OptIn_Cnt_Button"),
							"SB_Trolley_Content_Display_OptIn_Cnt_Button", 1);
					Click_Button_Xpath(OR_OR.getProperty("SB_Trolley_Content_Display_OptIn_Cnt_Button"),
							"SB_Trolley_Content_Display_OptIn_Cnt_Button");
					break;
				}
			}
			wait(10000);
		}
		return this;
	}

	public TrolleyAndCheckoutPage forgottenSomethingHandler() {
		boolean forgotten_flag = isElementPresent(OR_OR.getProperty("Forgotten_Something_Header"),
				"Forgotten_Something_Header");
		if (forgotten_flag == true) {
			testLog.info("HYF/HYC overlay appeared");
			wait(2000);
			clickHYFContinueToCheckoutButton();
			wait(10000);
		}
		return this;
	}

	public DeliverySlotPage clickHYFContinueToCheckoutButton() {
		ExplicitWait(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New", 5);
		scrollTo_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New", 1);
		Click_Button_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New");
		wait(15000);
		return new DeliverySlotPage();
	}

	public TrolleyAndCheckoutPage addHYFProduct() {
		ExplicitWait(OR_OR.getProperty("Forgotten_Something_Header"), "Forgotten_Something_Header", 5);
		Click_Button_Xpath(OR_OR.getProperty("HYF_First_Product"), "HYF_First_Product");

		return this;
	}

	public TrolleyAndCheckoutPage validateAddHYFProduct() {
		ExplicitWait(OR_OR.getProperty("Forgotten_Something_Header"), "Forgotten_Something_Header", 15);
		Click_Button_Xpath(OR_OR.getProperty("HYF_First_Product"), "HYF_First_Product");

		String HYF_ProductName = get_xpath_text(OR_OR.getProperty("HYF_First_Product_Product_Name"),
				"HYF_First_Product_Product_Name");
		Click_Button_Xpath(OR_OR.getProperty("Forgotten_Something_Close_Link"), "Forgotten_Something_Close_Link");
		wait(3000);
		if (get_xpath_text(OR_OR.getProperty("Trolly_First_Item"), "Trolly_First_Item").contains(HYF_ProductName)) {
			testLog.info("Product added successfully from Forgotten something popup");
		} else {
			testLog.error("Product is not added successfully from forgotten something popup");
			assertCheck("validateAddHYFProduct", "Product is not added successfully from forgotten something popup");
		}

		return this;
	}

	public double getTrolleyAmount() {
		double emptytrolley = 0.0, Trolley_Total_Cost = 0.0;
		String element1 = get_xpath_text(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		String newelement1 = element1.replaceAll("[^0-9.]", "").trim();
		Trolley_Total_Cost = Double.parseDouble(newelement1);
		testLog.info("Extracted price is:" + Trolley_Total_Cost);

		if (Trolley_Total_Cost == emptytrolley) {
			testLog.info("The Trolley is empty with amount: " + Trolley_Total_Cost);
		} else {
			testLog.info("Already products exists in the Trolley with Trolley amount: " + Trolley_Total_Cost);
		}
		return Trolley_Total_Cost;
	}

	public TrolleyAndCheckoutPage TrolleyHandler() {
		wait(5000);
		// Trolley content handler
		// trolleyContentHandler(0);
		// HYF overlay
		if (isElementPresent(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New")) {
			HYF_ContinueToCheckout();
		}
		return this;
	}

	public AlmostDonePage saveInstructions_RDOrder() {

		if (isElementPresent(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_Save_Instructions_Button"),
				"Save Instructions Button")) {

			Click_Button_Xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_Save_Instructions_Button"),
					"Save Instructions Button");
			wait(5000);
		}

		return new AlmostDonePage();
	}

	public PaymentPage checkoutFlowHandler() {
		String checkout_header = "";

		ExplicitWait(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header", 60);
		checkout_header = get_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header");

		while (!(checkout_header.equals("How would you like to pay?"))
				&& !(checkout_header.equals("Please confirm your order details"))) {
			int i = 0;

			if ("Loading, please wait...".equals(checkout_header))
				i = 1;
			else if ("Delivery restrictions...".equals(checkout_header))
				i = 2;
			else if ("Click & Collect".equals(checkout_header) || "Remote Delivery".equals(checkout_header))
				i = 3; // 1st time Click & Collect customer - Enter Phone number
			else if (checkout_header.equals("Sorry, some of the products that were"))
				i = 4;
			else if ("Important information".equals(checkout_header))
				i = 5;
			else if ("Delivery Instructions".equals(checkout_header))
				i = 6; // Delivery Instructions for RD Customer
			else if ("Almost done...".equals(checkout_header))
				i = 7; // Credit Coupon Page
			else if ("Instructions for transport provider".equals(checkout_header))
				i = 8;// New Delivery Instructions for RD Customer
			else if ("Unattended Delivery Instructions".equals(checkout_header))
				i = 9;

			switch (i) {
			case 1:
				wait(5000);
				break;
			case 2:
				verify_xpath_text(OR_OR.getProperty("Checkout_Del_Res_Keep_Items"),
						"Keep items and choose a different time");
				verify_xpath_text(OR_OR.getProperty("Checkout_Del_Res_Keep_Delivery"),
						"Keep delivery time and remove items");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Del_Res_Keep_Delivery"),
						"Checkout_Del_Res_Keep_Delivery");
				break;
			case 3:
				FirstTimeCCOrder_EnterContactDetails();
				break;
			case 4:
				verify_xpath_text(OR_OR.getProperty("Checkout_Go_back_Find_Replacements"),
						"Go back and find replacements");
				verify_xpath_text(OR_OR.getProperty("Checkout_Continue_Anyway"), "Continue anyway");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Continue_Anyway"), "Checkout_Continue_Anyway");
				break;
			case 5:
				verify_xpath_text(OR_OR.getProperty("Checkout_Will_Someone_No_Choose_Another_Time_Link"),
						"No, choose another time");
				verify_xpath_text(OR_OR.getProperty("Checkout_Will_Someone_Yes_Someone_Button"),
						"Yes, someone will be home");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Will_Someone_Yes_Someone_Button"),
						"Checkout_Will_Someone_Yes_Someone_Button");
				break;
			case 6:
				Sendkey_xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_EditField"), "Hello",
						"Checkout_Delivery_Instructions_RD_EditField");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_Save_Instructions_Button"),
						"Checkout_Delivery_Instructions_RD_Save_Instructions_Button");
				break;
			case 7:
				if (get_xpath_text(OR_OR.getProperty("Credit_coupon_Text"), "Credit_coupon_Text")
						.contains("Would you like to use it now?")) {
					Click_Button_Xpath(OR_OR.getProperty("Checkout_Save_For_Later"), "Checkout_Save_For_Later");
				}
				break;
			case 8:
				Sendkey_xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_Other_RD"), "Hello",
						"Checkout_Delivery_Instructions_Other_RD");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_Save_Instructions_Button"),
						"Checkout_Delivery_Instructions_RD_Save_Instructions_Button");
				break;
			case 9:
				Sendkey_xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_Unattended_TextBox"),
						"Unattended delivery instruction text", "Checkout_Delivery_Instructions_Unattended_TextBox");
				Click_Button_Xpath(OR_OR.getProperty("Checkout_Delivery_Instructions_RD_Save_Instructions_Button"),
						"Checkout_Delivery_Instructions_RD_Save_Instructions_Button");
			}

			wait(10000);
			checkout_header = get_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header");
		}
		if (checkout_header.equals("How would you like to pay?"))
			testLog.info("User is on Payment page");
		else
			testLog.info("user is on Almost Done page");

		return new PaymentPage();
	}

	public boolean isHYFDisplayed() {
		if (isElementPresent(OR_OR.getProperty("Forgotten_Something_Header"), "Forgotten_Something_Header"))
			return true;
		else
			return false;
	}

	public void HYF_ContinueToCheckout() {
		// scrollTo_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button"),
		// "Continue_To_Checkout_Button", 1);
		// Click_Button_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button"),
		// "Continue_To_Checkout_Button");
		// scrollTo_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button_New"),
		// "Continue_To_Checkout_Button_New", 1);
		Click_Button_Xpath(OR_OR.getProperty("Continue_To_Checkout_Button_New"), "Continue_To_Checkout_Button_New");
		wait(18000);
	}

	public void FirstTimeCCOrder_EnterContactDetails() {
		verify_xpath_text(OR_OR.getProperty("Checkout_Contact_Details_Header2"), "Contact details");
		Sendkey_xpath(OR_OR.getProperty("Checkout_Contact_Details_Phone"), "0412345678",
				"Checkout_Contact_Details_Phone");
		Click_Button_Xpath(OR_OR.getProperty("Checkout_Contact_Details_Continue_Button"),
				"Checkout_Contact_Details_Continue_Button");
		wait(10000);
	}

	public TrolleyAndCheckoutPage enter_Value_Into_FiveorMore_rhst(String index) {
		wait(4000);
		String dropdown_product = OR_OR.getProperty("Trolley_Tile1_DropdownIcon");
		String search_term = search_1.substring(0, search_1.length() - 1);
		String dd_newElement1 = dropdown_product.replace("sku", search_term);
		driver.findElement(By.xpath(dd_newElement1)).click();
		if (isElementPresent(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore_Enable"),
				"RHST_Tile1_Dropdown_Select5orMore_Enable")) {
			Click_Button_Xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore_Enable"),
					"RHST_Tile1_Dropdown_Select5orMore_Enable");
		}
		ExplicitWait(OR_OR.getProperty("Dropdown_List_5orMore_Qty"), "Dropdown_List_5orMore_Qty", 8);
		Sendkey_xpath(OR_OR.getProperty("Dropdown_List_5orMore_Qty"), index, "Dropdown_List_5orMore_Qty");
		Click_Button_Xpath(OR_OR.getProperty("Dropdown_5orMore_AddorUpdate"), "Dropdown_5orMore_AddorUpdate");
		return this;
	}

	public TrolleyAndCheckoutPage validateTrolleyAfterRemovingAllProducts() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Trolley_doller"), "Trolley_doller", 10);
		verify_xpath_contains_text(OR_OR.getProperty("Trolley_doller"), OR_OR.getProperty("Trolley_doller_Value"));
		verify_xpath_text(OR_OR.getProperty("Trolley_Text"), "Trolley and checkout");
		ExplicitWait(OR_OR.getProperty("Trolley_Empty_Warning"), "Trolley_Empty_Warning", 10);
		verify_xpath_contains_text(OR_OR.getProperty("Trolley_Empty_Warning"),
				OR_OR.getProperty("Trolley_Empty_Warning_Text"));
		return this;
	}

	public TrolleyAndCheckoutPage validateTrolleyWarningMessage() {
		ExplicitWait(OR_OR.getProperty("Trolley_Warning_Message"), "Trolley_Warning_Message", 5);
		verify_xpath_text(OR_OR.getProperty("Trolley_Warning_Message"),
				OR_OR.getProperty("Trolley_Warning_Message_Text"));
		return this;
	}

	public TrolleyAndCheckoutPage clickGoBackToTrolley() {
		ExplicitWait(OR_OR.getProperty("Trolley_Go_Back_Button"), "Trolley_Go_Back_Button", 5);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Go_Back_Button"), "Trolley_Go_Back_Button");
		wait(2000);
		return this;
	}

	public TrolleyAndCheckoutPage trolleySortingCheck(String sort) {
		if (sort.equals("LastAdded")) {
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Change"), "Trolley_Sort_Change");
			verify_xpath_text(OR_OR.getProperty("Trolley_Sort_Option_Heading"),
					OR_OR.getProperty("Trolley_Sort_Option_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Trolley_Sort_Last_Added"),
					OR_OR.getProperty("Trolley_Sort_Last_Added_Text"));
			verify_xpath_text(OR_OR.getProperty("Trolley_Sort_Category"),
					OR_OR.getProperty("Trolley_Sort_Category_Text"));
			boolean lastadded = isElementSelected(OR_OR.getProperty("Trolley_Sort_Last_Added_CheckBox"));
			if (!lastadded) {
				Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Last_Added_CheckBox"),
						"Trolley_Sort_Last_Added_CheckBox");
			}
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Save"), "Trolley_Sort_Save");
			wait(2000);
			ExplicitWait(OR_OR.getProperty("Trolley_Sort_Heading"), "Trolley_Sort_Heading", 20);
			verify_xpath_text(OR_OR.getProperty("Trolley_Sort_Heading"),
					OR_OR.getProperty("Trolley_Sort_Heading_Last_Added"));
		}
		if (sort.equals("Category")) {
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Change"), "Trolley_Sort_Change");
			boolean category = isElementSelected(OR_OR.getProperty("Trolley_Sort_Category_CheckBox"));
			if (!category) {
				Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Category_CheckBox"),
						"Trolley_Sort_Category_CheckBox");
			}
			Click_Button_Xpath(OR_OR.getProperty("Trolley_Sort_Save"), "Trolley_Sort_Save");
			wait(2000);
			ExplicitWait(OR_OR.getProperty("Trolley_Sort_Heading"), "Trolley_Sort_Heading", 20);
			verify_xpath_text(OR_OR.getProperty("Trolley_Sort_Heading"),
					OR_OR.getProperty("Trolley_Sort_Heading_Category"));
		}
		return this;
	}

	public TrolleyAndCheckoutPage validateLastAddedSorting() {
		String xpath = OR_OR.getProperty("Trolley_N_Products");
		String[] sku_list = search_1.split(",");
		List<String> reverse = Arrays.asList(sku_list);
		Collections.reverse(reverse);
		String[] reversed_sku_list = reverse.toArray(sku_list);
		for (int i = 0; i < reversed_sku_list.length; i++) {
			String sku = reversed_sku_list[i];
			String skunum = sku.substring(0, sku.length() - 1);
			testLog.info(skunum);
			String val = Integer.toString(i + 1);
			String xp = xpath;
			String path = xp.replace("nvalue", val);
			String trolleyproduct = get_xpath_attribute_id(path, "Trolley_N_Products");
			testLog.info(trolleyproduct);
			if (trolleyproduct.contains(skunum)) {
				testLog.info("last added skus sorted correctly");
			} else {
				testLog.error("last added skus not sorted correctly");
				assertCheck("validateLastAddedSorting", "last added skus not sorted correctly");
			}

		}
		return this;
	}

	public TrolleyAndCheckoutPage validateCategorySorting() {
		int allcategory = getXpathCount(OR_OR.getProperty("Trolley_ALL_Category_Heading"),
				"Trolley_ALL_Category_Heading");
		int loop_count = allcategory - 1;
		String nth_category = OR_OR.getProperty("Trolley_N_Category_Heading");
		for (int ivalue = 1; ivalue <= loop_count; ivalue++) {

			String istrvalue = Integer.toString(ivalue);
			String jstrvalue = Integer.toString(ivalue + 1);

			String category_1 = nth_category.replace("nvalue", istrvalue);
			String category_2 = nth_category.replace("nvalue", jstrvalue);

			String strcategory_1 = driver.findElement(By.xpath(category_1)).getText();
			String strcategory_2 = driver.findElement(By.xpath(category_2)).getText();

			if (strcategory_1.compareToIgnoreCase(strcategory_2) <= 0) {
				testLog.info("The no." + istrvalue + " with category name: " + strcategory_1
						+ " is less than or equal to the category no." + jstrvalue + " with category name: "
						+ strcategory_2);

			} else {
				testLog.error("The no." + ivalue + " with category name: " + strcategory_1
						+ " is sorted wrongly and greater than the category no." + jstrvalue + " with category name: "
						+ strcategory_2);
				assertCheck("validateCategorySorting",
						"The no." + ivalue + " with category name: " + strcategory_1
								+ " is sorted wrongly and greater than the category no." + jstrvalue
								+ " with category name: " + strcategory_2);
			}
		}

		return this;
	}

	public TrolleyAndCheckoutPage validateMinimumOrderPage() {
		validateTrolleyWarningMessage();
		verify_xpath_text(OR_OR.getProperty("Trolley_Minimun_Order_Sub_Heading"),
				OR_OR.getProperty("Trolley_Minimun_Order_Sub_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Trolley_Go_Back_ToTrolley_Button"),
				OR_OR.getProperty("Trolley_Go_Back_ToTrolley_Button_Text"));
		return this;
	}

	public TrolleyAndCheckoutPage validateSlotIsNotSelected() {
		wait(5000);
		verify_xpath_text(OR_OR.getProperty("Trolley_NeedTo_Choose_DT_Heading"),
				OR_OR.getProperty("Trolley_NeedTo_Choose_DT_Text"));
		verify_xpath_text(OR_OR.getProperty("Trolley_View_Delivery_Options_Button"),
				OR_OR.getProperty("Trolley_View_Delivery_Options_Button_Text"));
		isElementPresent(OR_OR.getProperty("Trolley_View_Delivery_Options_Cancel_Button"),
				"Trolley_View_Delivery_Options_Cancel_Button");
		return this;
	}

	public TrolleyAndCheckoutPage clickViewDeliveryOptions() {
		Click_Button_Xpath(OR_OR.getProperty("Trolley_View_Delivery_Options_Button"),
				"Trolley_View_Delivery_Options_Button");
		return this;
	}

	public TrolleyAndCheckoutPage enter_Value_Into_FiveorMore_Dietcontroller(String index) {
		ExplicitWait(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown", 50);
		Click_Button_Xpath(OR_OR.getProperty("Trolley_Product_DropDown"), "Trolley_Product_DropDown");
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore"), "RHST_Tile1_Dropdown_Select5orMore");
		wait(2000);
		Sendkey_xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_Select5orMore_Qty"), index,
				"RHST_Tile1_Dropdown_Select5orMore_Qty");
		Click_Button_Xpath(OR_OR.getProperty("RHST_Tile1_Dropdown_UpdateButton_Only1Element"),
				"RHST_Tile1_Dropdown_UpdateButton_Only1Element");
		wait(2000);

		return this;
	}

	public TrolleyAndCheckoutPage validateUDInstructionsPage() {
		if (isElementPresent(OR_OR.getProperty("UD_Instruction_Sub_Heading"), "UD_Instruction_Sub_Heading")) {
			verify_xpath_text(OR_OR.getProperty("UD_Instruction_Heading"),
					OR_OR.getProperty("UD_Instruction_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("UD_Instruction_Sub_Heading"),
					OR_OR.getProperty("UD_Instruction_Sub_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("UD_Enter_Instruction_Heading"),
					OR_OR.getProperty("UD_Enter_Instruction_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Save_Button"),
					OR_OR.getProperty("UD_Instruction_Save_Button_Text"));
			verify_xpath_text(OR_OR.getProperty("UD_Instruction_Skip_Link"),
					OR_OR.getProperty("UD_Instruction_Skip_Link_Text"));
		}
		if (isElementPresent(OR_OR.getProperty("Change_Delivery_Instruction_Link"),
				"Change_Delivery_Instruction_Link")) {
			testLog.info("Delivery Instruction is already saved so removing existing instruction");
			scrollTo_Xpath(OR_OR.getProperty("Change_Delivery_Instruction_Link"), "Change_Delivery_Instruction_Link",
					2);
			Click_Button_Xpath(OR_OR.getProperty("Change_Delivery_Instruction_Link"),
					"Change_Delivery_Instruction_Link");
			wait(3000);
			Clear_Text(OR_OR.getProperty("Delivery_Instruction_Textbox"), "Delivery_Instruction_Textbox");
		}
		if (isElementPresent(OR_OR.getProperty("Add_Delivery_Instructions"), "Add_Delivery_Instructions")) {
			testLog.error("Delivery Instruction page is not displayed");
			assertCheck("validateUDInstructionsPage", "Delivery Instruction page is not displayed");
		}

		return this;
	}

	public TrolleyAndCheckoutPage enterDeliveryInstruction(String Message) {
		if (isElementPresent(OR_OR.getProperty("Delivery_Instruction_Textbox"), "Delivery_Instruction_Textbox")) {
			Clear_Text(OR_OR.getProperty("Delivery_Instruction_Textbox"), "Delivery_Instruction_Textbox");
			Sendkey_xpath(OR_OR.getProperty("Delivery_Instruction_Textbox"), Message, "Delivery_Instruction_Textbox");
			saveDeliveryInstruction();
		}
		if (isElementPresent(OR_OR.getProperty("Change_Delivery_Instruction_Link"),
				"Change_Delivery_Instruction_Link")) {
			testLog.info("Delivery Instruction is saved already");
		}
		if (isElementPresent(OR_OR.getProperty("Add_Delivery_Instructions"), "Add_Delivery_Instructions")) {
			testLog.error("Delivery Instruction page is not displayed");
			assertCheck("enterDeliveryInstruction", "Delivery Instruction page is not displayed");
		}
		return this;
	}

	public TrolleyAndCheckoutPage saveDeliveryInstruction() {
		Click_Button_Xpath(OR_OR.getProperty("Delivery_Instruction_Save_Button"), "Delivery_Instruction_Save_Button");
		wait(10000);
		return this;
	}

	public TrolleyAndCheckoutPage removeDeliveryInstruction() {
		Clear_Text(OR_OR.getProperty("Delivery_Instruction_Textbox"), "Delivery_Instruction_Textbox");
		return this;
	}

	public TrolleyAndCheckoutPage validateDeliveryRestrictionInCheckoutFlow() {
		ExplicitWait(OR_OR.getProperty("Checkout_Flow_Header"), "Checkout_Flow_Header", 10);
		verify_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "Delivery restrictions...");
		verify_xpath_text(OR_OR.getProperty("Checkout_Del_Res_Warning"),
				OR_OR.getProperty("Checkout_Del_Res_Warning_Text"));
		verify_xpath_text(OR_OR.getProperty("Checkout_Del_Res_Keep_Items"), "Keep items and choose a different time");
		verify_xpath_text(OR_OR.getProperty("Checkout_Del_Res_Keep_Delivery"), "Keep delivery time and remove items");
		return this;
	}

	public DeliverySlotPage clickDeliveryRestrictionKeepItemButton() {
		Click_Button_Xpath(OR_OR.getProperty("Checkout_Del_Res_Keep_Items"), "Checkout_Del_Res_Keep_Items");
		return new DeliverySlotPage();
	}

	public TrolleyAndCheckoutPage validateViewTrolleyButton() {
		wait(4000);
		if (getCurrentUrl().contains("home")) {
			isElementPresent(OR_OR.getProperty("Trolley_Heading"), "Trolley_Heading");
			verify_xpath_text(OR_OR.getProperty("Trolley_Heading"), "Trolley");
			isElementPresent(OR_OR.getProperty("Proceed_To_Checkout_Button"), "Proceed_To_Checkout_Button");
		} else {
			assertCheck("validateviewTrolleyButton", "User is not on Home Page");
		}
		return this;
	}

	public TrolleyAndCheckoutPage validateDeliveryInstructionPageNotDisplayed() {

		if (isElementPresent(OR_OR.getProperty("Delivery_Instruction_Textbox"), "Delivery_Instruction_Textbox")) {
			testLog.error("Delivery instruction page should not display");
			assertCheck("validateDeliveryInstructionPageNotDisplayed", "Delivery instruction page should not display");
		} else {
			testLog.info("Delivery instruction page is not displayed");
		}
		return this;
	}

	public TrolleyAndCheckoutPage clickSkipDeliveryInstructions() {
		Click_Button_Xpath(OR_OR.getProperty("UD_Instruction_Skip_Link"), "UD_Instruction_Skip_Link");
		wait(3000);
		return this;
	}

	public int getTrolleyTotalItems() {
		String trolleyItems = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		int itemsCount = Integer.parseInt(trolleyItems);
		return itemsCount;
	}

	public double getTotalSaving() {
		wait(3000);
		double Trolley_Total_Saving = 0.0;
		if (isElementPresent(OR_OR.getProperty("Trolley_Total_Saving"), "Trolley_Total_Saving")) {
			String totalTrolleySaving = get_xpath_text(OR_OR.getProperty("Trolley_Total_Saving"),
					"Trolley_Total_Saving");
			String totalSavingAmt = totalTrolleySaving.replaceAll("[^0-9.]", "").trim();
			Trolley_Total_Saving = Double.parseDouble(totalSavingAmt);
			testLog.info("Total Saving Extracted price is:" + Trolley_Total_Saving);
		} else {
			testLog.info("No savings present in the trolley");
		}
		return Trolley_Total_Saving;
	}

	public String getTrolleyAmtFromSuperbar() {
		double trolley_amt_1 = getPages.getTrolleyAndCheckoutPage().getTrolleyAmount();
		String trolley_amt = Double.toString(trolley_amt_1);
		return trolley_amt;
	}

	public String getTrolleyItemsCountFromSuperbar() {
		String superbarqty_1 = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Count"))).getText();
		return superbarqty_1;
	}

	public String getTrolleyProductNameFromSuperbar() {
		String productName = driver.findElement(By.xpath(OR_OR.getProperty("Product_Name_Superbar"))).getText();
		return productName;
	}

	public String getTrolleyProductBrandNameFromSuperbar() {
		String brandName = driver.findElement(By.xpath(OR_OR.getProperty("Brand_Name_Superbar"))).getText();
		return brandName;
	}

	public String getSignedDelInstructHeader() {

		return (get_xpath_text(OR_OR.getProperty("Delivery_Instruction_Heading"), "Delivery_Instruction_Heading"));
	}

	public String getSignedDelInstructSubHeader() {

		return (get_xpath_text(OR_OR.getProperty("Delivery_Instruction_Sub_Heading"),
				"Delivery_Instruction_Sub_Heading"));
	}

	public TrolleyAndCheckoutPage enterRD_DeliveryInstruction(String Message) {
		if (isElementPresent(OR_OR.getProperty("RDDelivery_Instruction_Textbox"), "RDDelivery_Instruction_Textbox")) {
			Clear_Text(OR_OR.getProperty("RDDelivery_Instruction_Textbox"), "RDDelivery_Instruction_Textbox");
			Sendkey_xpath(OR_OR.getProperty("RDDelivery_Instruction_Textbox"), Message,
					"RDDelivery_Instruction_Textbox");
			saveDeliveryInstruction();
		}
		if (isElementPresent(OR_OR.getProperty("Change_Delivery_Instruction_Link"),
				"Change_Delivery_Instruction_Link")) {
			testLog.info("Delivery Instruction is saved already");
		}
		if (isElementPresent(OR_OR.getProperty("Add_Delivery_Instructions"), "Add_Delivery_Instructions")) {
			testLog.error("Delivery Instruction page is not displayed");
			assertCheck("enterDeliveryInstruction", "Delivery Instruction page is not displayed");
		}
		return this;
	}

	public TrolleyAndCheckoutPage validateSignedDelHeadings() {

		isElementPresent(OR_OR.getProperty("Delivery_Instruction_Heading"), "Delivery_Instruction_Heading");
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Heading"), "Delivery Instructions");

		isElementPresent(OR_OR.getProperty("Delivery_Instruction_Sub_Heading"), "Delivery_Instruction_Sub_Heading");
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Sub_Heading"),
				OR_OR.getProperty("Delivery_Instruction_Sub_Heading_Text"));
		return this;
	}

	public TrolleyAndCheckoutPage validateRDDelHeadings() {

		isElementPresent(OR_OR.getProperty("Delivery_Instruction_Heading_RD"), "Delivery_Instruction_Heading_RD");
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Heading_RD"),
				OR_OR.getProperty("Delivery_Instruction_Heading_RD_Text"));

		isElementPresent(OR_OR.getProperty("Delivery_Instruction_Sub_Heading_RD"),
				"Delivery_Instruction_Sub_Heading_RD");
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Sub_Heading_RD"),
				OR_OR.getProperty("Delivery_Instruction_Sub_Heading_Text_RD"));
		return this;
	}

	public double getExpandTrolleyAmount() {
		double emptytrolley = 0.0, Trolley_Total_Cost = 0.0;
		wait(3000);
		ExplicitWait(OR_OR.getProperty("Checkout_TrolleyTotal"), "Checkout_TrolleyTotal", 20);
		String element1 = get_xpath_text(OR_OR.getProperty("Checkout_TrolleyTotal"), "Checkout_TrolleyTotal");
		element1 = element1.replace("$", "").trim();
		Trolley_Total_Cost = Double.parseDouble(element1);
		testLog.info("Extracted price is:" + Trolley_Total_Cost);

		if (Trolley_Total_Cost == emptytrolley) {
			testLog.info("The Trolley is empty with amount: " + Trolley_Total_Cost);
		} else {
			testLog.info("Already products exists in the Trolley with Trolley amount: " + Trolley_Total_Cost);
		}
		return Trolley_Total_Cost;
	}
}
