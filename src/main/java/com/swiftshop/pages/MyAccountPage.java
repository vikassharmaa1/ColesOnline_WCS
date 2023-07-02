package com.swiftshop.pages;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.swiftshop.main.FunLibrary;

public class MyAccountPage extends FunLibrary {

	public void clickLogout() {
		ExplicitWait(OR_OR.getProperty("Logout_Button"), "Logout_Button", 1);
		Click_Button_Xpath(OR_OR.getProperty("Logout_Button"), "Logout_Button");
		checkWebPageIsReady();
		wait(2000);
	}

	public MyAccountPage clickAccountEdit() {
		ExplicitWait(OR_OR.getProperty("Account_Details_Edit_Link"), "Account_Details_Edit_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("Account_Details_Edit_Link"), "Account_Details_Edit_Link");
		return this;
	}

	public MyAccountPage validateAccountAllDetailsText() {
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_Header"), "Account details");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_GivenName"), "First name");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_FamilyName"), "Last name");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_DateOfBirth"), "Date of birth");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_PrimaryEmail"), "Primary email");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_SecondaryEmail"), "Secondary email (optional)");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_MobileNumber"), "Mobile number");
		verify_xpath_text(OR_OR.getProperty("Contact_Details_Dont_Have_MobNum"), "I don't have a mobile number");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_SaveChanges_Button"), "Save changes");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_Cancel_Link"), "Cancel");
		return this;
	}

	public String validateSecondaryEmail() {
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_SecondaryEmail"), "Secondary email (optional)");
		String second_email = driver
				.findElement(By.xpath(OR_OR.getProperty("Name_Contact_Details_SecondaryEmail_Input")))
				.getAttribute("value");
		return second_email;
	}

	public MyAccountPage enterName(String givenName, String familyName) {
		Clear_Text(OR_OR.getProperty("Contact_page_First_Name_Text"), "Contact_page_First_Name_Text");
		Sendkey_xpath(OR_OR.getProperty("Contact_page_First_Name_Text"), givenName, "Contact_page_First_Name_Text");
		Clear_Text(OR_OR.getProperty("Contact_page_Last_Name_Text"), "Contact_page_Last_Name_Text");
		Sendkey_xpath(OR_OR.getProperty("Contact_page_Last_Name_Text"), familyName, "Contact_page_Last_Name_Text");
		return this;
	}

	public MyAccountPage enterDOB(String dd, String yyyy) {
		Clear_Text(OR_OR.getProperty("Registration_DOB_Day_Text"), "Registration_DOB_Day_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_DOB_Day_Text"), dd, "Registration_DOB_Day_Text");
		Click_Button_Xpath(OR_OR.getProperty("Registration_DOB_Month_DropDown"), "Registration_DOB_Month_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("Registration_DOB_Month"), "Registration_DOB_Month");
		Clear_Text(OR_OR.getProperty("Registration_DOB_Year_Text"), "Registration_DOB_Year_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_DOB_Year_Text"), yyyy, "Registration_DOB_Year_Text");
		return this;
	}

	public MyAccountPage enterMobileNumber(String mobileNum) {
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), mobileNum,
				"Registration_Mobile_Number_Field");
		return this;
	}

	public MyAccountPage clickSaveChanges() {
		Click_Button_Xpath(OR_OR.getProperty("Name_Contact_Details_SaveChanges_Button"),
				"Name_Contact_Details_SaveChanges_Button");
		return this;
	}

	public MyAccountPage validateSuccessMsg() {
		ExplicitWait(OR_OR.getProperty("Name_Contact_Details_Success_Continue_Button"),
				"Name_Contact_Details_Success_Continue_Button", 30);
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_SuccessMsg"),
				"Your name and contact details have been saved");
		Click_Button_Xpath(OR_OR.getProperty("Name_Contact_Details_Success_Continue_Button"),
				"Name_Contact_Details_Success_Continue_Button");
		return this;
	}

	public MyAccountPage validateAccountName(String name) {
		verify_xpath_text(OR_OR.getProperty("MyAccount_Superbar_Name"), name);
		return this;
	}

	public MyAccountPage enterSecondaryEmail() {
		Sendkey_xpath(OR_OR.getProperty("Name_Contact_Details_SecondaryEmail_Input"), "secondaryemail@mailinator.com",
				"Name_Contact_Details_SecondaryEmail_Input");
		return this;
	}

	public boolean validateSecondaryEmailHeadingNotPresent() {
		if (isElementNotPresent(OR_OR.getProperty("Account_Detail_SecondaryEmail_Heading"),
				"Account_Detail_SecondaryEmail_Heading")) {
			return true;
		} else {
			return false;
		}
	}

	public MyAccountPage enterEmail(String email) {
		Clear_Text(OR_OR.getProperty("Contact_page_Emailid_Text"), "Contact_page_Emailid_Text");
		Sendkey_xpath(OR_OR.getProperty("Contact_page_Emailid_Text"), email, "Contact_page_Emailid_Text");
		return this;
	}

	public MyAccountPage enterBusinessABN(String bname, String ABN) {
		Click_Button_Xpath(OR_OR.getProperty("Name_Contact_Details_BusinessAcc_Checkbox"), "Name_Contact_Details_BusinessAcc_Checkbox");
		scrollTo_Xpath(OR_OR.getProperty("Name_Contact_Details_BusinessAcc_Checkbox"),
				"Name_Contact_Details_BusinessAcc_Checkbox", 1);
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_BusinessName"), "Your business name");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_YourABN"), "Your ABN");
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_YourIndustry"), "Your industry");
		Sendkey_xpath(OR_OR.getProperty("Name_Contact_Details_Businessname_Input"), bname,
				"Name_Contact_Details_Businessname_Input");
		Sendkey_xpath(OR_OR.getProperty("Name_Contact_Details_YourABN_Input"), ABN,
				"Name_Contact_Details_YourABN_Input");
		Select industry_dropdown = new Select(driver.findElement(By.id("industry-selection")));
		industry_dropdown.selectByValue("Other");
		return this;
	}

	public MyAccountPage validateErrorMsgs(String errorMsg) {
		switch (errorMsg) {
		case "ErrorMsg1":
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
					"Please enter a valid date of birth");
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_2"),
					"Please enter a valid primary email address");
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_3"),
					"Please enter a valid mobile number");
			break;

		case "ErrorMsg2":
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
					"Please remove these characters from the first name \"@\", \"%\"");
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_2"),
					"Please remove these characters from the last name \"&\", \"*\"");
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_3"),
					"Please enter a valid date of birth");
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_4"),
					"Please enter a valid mobile number");
			break;
		case "ErrorMsg3":
			verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_ErrorMsg_1"),
					"Please remove these characters from the business name \"@\", \"$\"");
			break;
		default:
			break;
		}

		return this;
	}

	public MyAccountPage closeSuperBar() {
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
	}

	public MyAccountPage clickBusinessUserCheckbox() {
		try {
			Click_Button_Xpath(OR_OR.getProperty("Name_Contact_Details_BusinessAcc_Checkbox"),
					"Name_Contact_Details_BusinessAcc_Checkbox");
		} catch (Exception e) {
			testLog.error("Not able to click on Business account checkbox");
		}
		return this;
	}

	public MyAccountPage clickShoppingList() {
		ExplicitWait(OR_OR.getProperty("MyAccount_SavedLists"), "MyAccount_SavedLists", 5);
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_SavedLists"), "MyAccount_SavedLists");
		wait(2000);
		return this;
	}

	public MyAccountPage clearMyListIfExist() {
		wait(3000);
		if (isElementVisible(OR_OR.getProperty("SB_MyList_Edit_Btn"), "EverythingTab_Tile1_Temp_Unavailable")) {
			clearMyList();
		}
		return this;
	}

	public MyAccountPage createList(String listName) {
		ExplicitWait(OR_OR.getProperty("SB_CreateNewList_Button"), "SB_CreateNewList_Button", 5);
		Click_Button_Xpath(OR_OR.getProperty("SB_CreateNewList_Button"), "SB_CreateNewList_Button");
		//verify_xpath_text_longwait(OR_OR.getProperty("Create_New_List_Text"), "Choose a name for this list");
		ExplicitWait(OR_OR.getProperty("SB_List_Name_Input"), "SB_List_Name_Input", 5);
		Sendkey_xpath(OR_OR.getProperty("SB_List_Name_Input"), listName, "SB_List_Name_Input");
		Click_Button_Xpath(OR_OR.getProperty("SB_CreateListForm_Save_Button"), "SB_CreateListForm_Save_Button");
		wait(3000);

		return this;
	}

	public MyAccountPage validateListSuccessMsg() {
		ExplicitWait(OR_OR.getProperty("SB_EmptySavedList_Text"), "SB_EmptySavedList_Text", 5);
		verify_xpath_contains_text(OR_OR.getProperty("SB_EmptySavedList_Text"),
				"List created! You're ready to start adding products.");
		return this;
	}

	public MyAccountPage goToMyLists() {
		ExplicitWait(OR_OR.getProperty("SB_EmptySavedList_Text"), "SB_EmptySavedList_Text", 5);
		Click_Button_Xpath(OR_OR.getProperty("SB_GotoMyLists_Button"), "SB_GotoMyLists_Button");
		wait(2000);
		return this;
	}

	public MyAccountPage clickShopNowButton() {
		ExplicitWait(OR_OR.getProperty("SB_MyList_Shop_Now_Btn"), "SB_MyList_Shop_Now_Btn", 5);
		Click_Button_Xpath(OR_OR.getProperty("SB_MyList_Shop_Now_Btn"), "SB_MyList_Shop_Now_Btn");
		return this;
	}

	public MyAccountPage validateAddAllButton() {
		int total_product = getXpathCount(OR_OR.getProperty("My_List_Tab_AllProducts"), "My_List_Tab_AllProducts");
		double price, total_price = 0.00, TrolleyTotal_D;
		String s_price;
		String price_xpath_nth = OR_OR.getProperty("My_List_Tab_Product_Price_Nvalue");
		for (int i = 1; i <= total_product; ++i) {

			String price_xpath = price_xpath_nth.replaceAll("nvalue", Integer.toString(i));
			s_price = get_xpath_text(price_xpath, "My_List_Tab_Product_Price_Nvalue");
			s_price = s_price.substring(s_price.indexOf("$") + 1).trim();
			price = Double.parseDouble(s_price);
			total_price = total_price + price;
		}
		DecimalFormat df = new DecimalFormat("#0.00");

		testLog.info(df.format(total_price));

		verify_xpath_text(OR_OR.getProperty("My_List_Tab_Heading1"),
				"You've added 0 of " + total_product + " available items in this list to your trolley");

		verify_xpath_contains_text(OR_OR.getProperty("My_List_Tab_Add_All_Button"), "$" + df.format(total_price));

		Click_Button_Xpath(OR_OR.getProperty("My_List_Tab_Add_All_Button"), "My_List_Tab_Add_All_Button");

		verify_xpath_text(OR_OR.getProperty("My_List_Tab_Heading2"),
				"All the available items in this list have been added to your trolley");

		verify_xpath_text(OR_OR.getProperty("My_List_Tab_Heading3"),
				+total_product + " available items for $" + df.format(total_price));
		// span[starts-with(@class,'draw-text') and
		// (@data-ng-bind='trolleyLinkVM.manager.trolley.formattedTotalPrice')]
		String TrolleyTotal = get_xpath_text(OR_OR.getProperty("Trolley_Total"), "Trolley_Total");
		TrolleyTotal = TrolleyTotal.substring(TrolleyTotal.indexOf("$") + 1).trim();
		TrolleyTotal_D = Double.parseDouble(TrolleyTotal);
		if (TrolleyTotal_D != total_price) {
			testLog.error("trolley price is not matching list Add All price");
		}
		return this;
	}

	public MyAccountPage validtedCreatedList(String listName) {
		ExplicitWait(OR_OR.getProperty("SB_SavedLists_Header"), "SB_SavedLists_Header", 5);
		verify_xpath_text(OR_OR.getProperty("SB_SavedLists_Header"), OR_OR.getProperty("SB_SavedLists_Header_Text"));

		String actualListName = get_xpath_text(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name");
		if (actualListName.contains(listName)) {
			testLog.info("Created list: " + listName + " is showing under My Lists,!! List Created Successfully !!");
		} else {
			testLog.error(
					"Opps!! Created list: " + listName + " is NOT showing under My Lists, Something went wrong !!");
			status = 1;
			assertCheck("validtedCreatedList",
					"Opps!! Created list: " + listName + " is NOT showing under My Lists, Something went wrong !!",
					"SB_MyList_Name");
		}
		return this;
	}

	public MyAccountPage goToMyAccountPage() {
		Click_Button_Xpath(OR_OR.getProperty("SB_MyList_Back_Link"), "SB_MyList_Back_Link");
		wait(2000);
		return this;
	}

//	public MyAccountPage DeleteAllExistingLists() {
//	
//		while(isElementVisible(OR_OR.getProperty("SB_EditList_Button"), "SB_EditList_Button")) {
//			clearMyList();
//	}
//		return this;
//	}
	
	public MyAccountPage clearMyList() {
		Click_Button_Xpath(OR_OR.getProperty("MyList_Delete_Button"), "MyList_Delete_Button");
		ExplicitWait(OR_OR.getProperty("MyList_YesDeleteIt_Button"), "MyList_YesDeleteIt_Button", 10);
		Click_Button_Xpath(OR_OR.getProperty("MyList_YesDeleteIt_Button"), "MyList_YesDeleteIt_Button");
		return this;     
	}

	public MyAccountPage validateMyListDeletedSuccessfully(String listName) {
		//ExplicitWait(OR_OR.getProperty("SB_Deleted_List"), "SB_Deleted_List", 5);
		if(isElementVisible(OR_OR.getProperty("SB_Deleted_List"), "SB_Deleted_List")) {
			
		
		String deletedListMsg = get_xpath_text(OR_OR.getProperty("SB_Deleted_List"), "SB_Deleted_List");
		if (deletedListMsg.contains(listName)) {
			testLog.info("Created list :" + listName
					+ ", deleted successfully, Confirmation message appearing under MyList");
		} else {
			testLog.error(
					"Opps!! Created list :" + listName + ", NOT deleted, deleting message NOT appearing under MyList");
			assertCheck("validateMyListDeletedSuccessfully",
					"Opps!! Created list :" + listName + ", NOT deleted, deleting message NOT appearing under MyList");
		}
	}
		boolean flag = isElementNotPresent(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name");
		if (flag == true) {
			testLog.info("Created list :" + listName + " NOT showing under My List, It's deleted successfully");

		} else {
			testLog.error("Opps!! Created list :" + listName + ", still showing under My List, Issue in deleting list");
			assertCheck("validateMyListDeletedSuccessfully",
					"Opps!! Created list :" + listName + ", still showing under My List, Issue in deleting list");
		}
		return this;
	}

	public MyAccountPage validateFlybuysLinkText() {
		ExplicitWait(OR_OR.getProperty("Account_Details_Link_Flybuys"), "Account_Details_Flybuys_Icon", 30);
		wait(2000);
		verify_xpath_text(OR_OR.getProperty("Account_Details_Link_Flybuys"), "Link Flybuys");
		verify_xpath_contains_text(OR_OR.getProperty("Account_Details_Flybuys_HeaderMsg"),
				"Link your flybuys account to earn points everytime you shop at Coles online. Don't have a flybuys account?");
		verify_xpath_contains_text(OR_OR.getProperty("Account_Details_Flybuys_Register_Today"), "Register today!");
		return this;
	}

	public MyAccountPage clickLinkFlybuys() {
		Click_Button_Xpath(OR_OR.getProperty("Account_Details_Link_Flybuys"), "Account_Details_Link_Flybuys");
		return this;
	}

	public MyAccountPage validateAllFlybuysFieldsText() {
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Heading"), "Link your flybuys account");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Heading_Intro"), "Earn flybuys points every time you shop!");
		isElementPresent(OR_OR.getProperty("Link_Flybuys_Card_Image"), "Link_Flybuys_Card_Image");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_EnterFlybuys_Heading"), "Enter your flybuys number");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_FootNote"),
				"By entering your flybuys number, you agree that flybuys and Coles can exchange information about you.");
		isElementPresent(OR_OR.getProperty("Link_Flybuys_Import_Checkbox"), "Link_Flybuys_Import_Checkbox");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Import_Checkbox_Label"),
				"Shop faster! Import items you've bought before");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Button"), "Link flybuys account");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Cancel"), "Cancel");
		return this;
	}

	public MyAccountPage enterFlybuysNumber(String secondset_digit, String thirdset_digit, String fourthset_digit) {
		Clear_Text(OR_OR.getProperty("AD_Flybuys_Secondset_Digits"), "AD_Flybuys_Secondset_Digits");
		wait(2000);
		Sendkey_xpath(OR_OR.getProperty("AD_Flybuys_Secondset_Digits"), secondset_digit, "AD_Flybuys_Secondset_Digits");
		Clear_Text(OR_OR.getProperty("AD_Flybuys_Thirdset_Digits"), "AD_Flybuys_Thirdset_Digits");
		Sendkey_xpath(OR_OR.getProperty("AD_Flybuys_Thirdset_Digits"), thirdset_digit, "AD_Flybuys_Thirdset_Digits");
		Clear_Text(OR_OR.getProperty("AD_Flybuys_Fourthset_Digits"), "AD_Flybuys_Fourthset_Digits");
		Sendkey_xpath(OR_OR.getProperty("AD_Flybuys_Fourthset_Digits"), fourthset_digit, "AD_Flybuys_Fourthset_Digits");
		return this;
	}

	public MyAccountPage ClickLinkFlybuysButton() {
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Button"), "Link_Flybuys_Button");
		return this;
	}

	public MyAccountPage validateFlybuysPasswordPageText() {
		wait(40000);
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Enter_Password_Label"),
				"To import items you've bought before enter your flybuys password");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Enter_Password_Continue_Button"), "Continue");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Enter_Pass_Forgot_Pass"), "Forgotten your password?");
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Enter_Pass_Cont_Without_Import"),
				"Continue without importing bought before items");
		return this;
	}

	public MyAccountPage enterFlybuysPassword() {
		Sendkey_xpath(OR_OR.getProperty("Link_Flybuys_Enter_Password_Field"), "test1234",
				"Link_Flybuys_Enter_Password_Field");
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Enter_Password_Continue_Button"),
				"Link_Flybuys_Enter_Password_Continue_Button");
		return this;
	}

	public MyAccountPage clickContinueWithoutImport() {
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Enter_Pass_Cont_Without_Import"),
				"Link_Flybuys_Enter_Pass_Cont_Without_Import");
		return this;
	}

	public MyAccountPage validateFlybuysSuccessMsg() {
		wait(5000);
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Success_Msg"), "Your flybuys account has been linked");
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Success_Msg_Ok"), "Link_Flybuys_Success_Msg_Ok");
		return this;
	}

	public MyAccountPage validateLinkedFlybuysNum(String flybuysNum) {
		if (flybuysNum.equals("EndsWith_5217")) {
			verify_xpath_text(OR_OR.getProperty("Linked_Flybuys_Account_Deatils"),
					"your Flybuys number starts with 6 0 0 8   9 4 and ends with   5 2 1 7 6008 94•• ••••  5217");
		}
		if (flybuysNum.equals("EndsWith_2216")) {
			verify_xpath_text(OR_OR.getProperty("Linked_Flybuys_Account_Deatils"),
					"your Flybuys number starts with 6 0 0 8 9 4 and ends with 2 2 1 6 6008 94•• •••• 2216");
		}
		if (flybuysNum.equals("EndsWith_5610")) {
			verify_xpath_text(OR_OR.getProperty("Linked_Flybuys_Account_Deatils"),
					"your Flybuys number starts with 6 0 0 8   9 4 and ends with   5 6 1 0 6008 94•• •••• 5610");
		}
		return this;
	}

	public MyAccountPage deleteLinkedFlybuysNum() {
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Edit_Link_Manage"), "Link_Flybuys_Edit_Link_Manage");
		Click_Button_Xpath(OR_OR.getProperty("Unlink_Flybuys_Number"), "Unlink_Flybuys_Number");
		Click_Button_Xpath(OR_OR.getProperty("Unlink_Flybuys_Delete"), "Unlink_Flybuys_Delete");
		return this;
	}

	public MyAccountPage validateDeleteSuccessMsg() {
		wait(10000);
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Delete_Success_Msg"), "Your flybuys number has been deleted");
		Click_Button_Xpath(OR_OR.getProperty("Link_Flybuys_Delete_Success_Msg_Ok"),
				"Link_Flybuys_Delete_Success_Msg_Ok");
		return this;
	}

	// This method will first edit the existing list and update it name
	public MyAccountPage editAndUpdateList(String listName) {
		Click_Button_Xpath(OR_OR.getProperty("SB_EditList_Button"), "SB_EditList_Button");
		wait(2000);
		Clear_Text(OR_OR.getProperty("SB_List_Name_Input"), "SB_List_Name_Input");
		Sendkey_xpath(OR_OR.getProperty("SB_List_Name_Input"), listName, "SB_List_Name_Input");
		Click_Button_Xpath(OR_OR.getProperty("SB_Done_List_Btn"), "SB_Done_List_Btn");
		wait(2000);
		return this;
	}

	// This method will validate the updated list
	public MyAccountPage validtedUpdatedList(String listName) {
		ExplicitWait(OR_OR.getProperty("SB_Updated_List"), "SB_Updated_List", 5);
		String expectedMsg = OR_OR.getProperty("SB_Updated_List_Msg");
		String updatedList = get_xpath_text(OR_OR.getProperty("SB_Updated_List"), "SB_Updated_List");

		if (updatedList.contains(listName)) {
			testLog.info("Updated list name :" + listName + ", appearing under MyList");

		} else {
			testLog.error(
					"Opps!! Updated list name :" + listName + ", NOT appearing under MyList, Something went WRONG !!");
			assertCheck("validtedUpdatedList",
					"Opps!! Updated list name :" + listName + ", NOT appearing under MyList, Something went WRONG !!");
		}

		if (updatedList.contains(expectedMsg)) {
			testLog.info("Updated list name contains expected updated message");
		} else {
			testLog.error("Opps!! Updated list NOT contains expected updated message, Something went WRONG !!");
			assertCheck("validtedUpdatedList",
					"Opps!! Updated list name :" + listName + ", NOT appearing under MyList, Something went WRONG !!");
		}
		return this;
	}

	// Function to refresh page multiple time until Coles Plus link is visible in
	// superbar and click on it.
	public MyAccountPage clickDeliveryPlusSubscriptionLink_BeforeSubscription() {

		int refreshcount = 0;

		while (refreshcount < 6)
			if (isElementVisible(OR_OR.getProperty("Superbar_ColesPlusLink"), "Superbar_ColesPlusLink")) {
				verify_xpath_text(OR_OR.getProperty("Superbar_ColesPlusLink"), "Coles Plus subscription");
				Click_Button_Xpath(OR_OR.getProperty("Superbar_ColesPlusLink"), "Superbar_ColesPlusLink");
				break;
			} else {
				driver.navigate().refresh();
				getPages.getSuperBarPage().clickMyAccount();
				++refreshcount;
			}

		// verify and click on Delivery Pass subscription in sidebar
//		verify_xpath_text(OR_OR.getProperty("Superbar_Delivery_Subscription"), "Delivery Plus subscription");
//		Click_Button_Xpath(OR_OR.getProperty("Superbar_Delivery_Subscription"), "Superbar_Delivery_Subscription");

		ExplicitWait(OR_OR.getProperty("Superbar_DelSub_Signuplink"), "Superbar_DelSub_Signuplink", 10);
		verify_xpath_text(OR_OR.getProperty("DelSub_Signup_heading"), OR_OR.getProperty("DelSub_Signup_heading_Text"));
		verify_xpath_text(OR_OR.getProperty("DelSub_Signup_Second_heading"),
				OR_OR.getProperty("DelSub_Signup_Second_heading_Text"));
		wait(3000);
		return this;
	}

	public SubscriptionPage clickSignUpButton() {
		Click_Button_Xpath(OR_OR.getProperty("Superbar_DelSub_Signuplink"), "Superbar_DelSub_Signuplink");
		ExplicitWait(OR_OR.getProperty("Header_ChooseSubscription"), "Header_ChooseSubscription", 4);
		return new SubscriptionPage();
	}

	public SubscriptionPage clickDeliveryPlusSubscriptionLink_AfterSubscription() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Coles_Plus_Subscription"), "MyAccount_Coles_Plus_Subscription", 10);
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Coles_Plus_Subscription"), "MyAccount_Coles_Plus_Subscription");
		return new SubscriptionPage();
	}

	public MyAccountPage clickMyAddress() {
		verify_xpath_text(OR_OR.getProperty("MyAccount_SavedAddresses"),
				OR_OR.getProperty("MyAccount_SavedAddresses_text"));
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_SavedAddresses"), "MyAccount_SavedAddresses");
		return this;
	}

	public MyAccountPage clickAddNewAddress() {
		verify_xpath_text(OR_OR.getProperty("SavedAddresses_Adda_New_Address"), "Add a new address");
		Click_Button_Xpath(OR_OR.getProperty("SavedAddresses_Adda_New_Address"), "SavedAddresses_Adda_New_Address");
		return this;
	}

	public MyAccountPage clickBackLink() {
		Click_Button_Xpath(OR_OR.getProperty("Back_Link"), "Back_Link");
		return this;
	}

	public MyAccountPage clickEditButton() {
		ExplicitWait(OR_OR.getProperty("SavedAddress_Edit_second_address"), "SavedAddress_Edit_second_address", 80);
		Click_Button_Xpath(OR_OR.getProperty("SavedAddress_Edit_second_address"), "SavedAddress_Edit_second_address");
		return this;
	}

	public MyAccountPage validateUpdatedIcon() {
		boolean sucess_updated_icon;
		String updated_text = "";
		ExplicitWait(OR_OR.getProperty("My_Addresses_Success_Updated_Icon"), "My_Addresses_Success_Updated_Icon", 80);
		sucess_updated_icon = isElementPresent(OR_OR.getProperty("My_Addresses_Success_Updated_Icon"),
				"My_Addresses_Success_Updated_Icon");
		testLog.info("Icon Present: " + sucess_updated_icon);
		wait(3000);
		updated_text = driver.findElement(By.xpath(
				"//li[@class='address is-success']/div[@class='success-message']/p[@class='success-message-text']"))
				.getText();
		testLog.info("updated_text: " + updated_text);
		if (true == sucess_updated_icon && updated_text.contains("updated")) {
			testLog.info("Successfully updated address.");
		} else {
			testLog.error("Failed to update address");
			assertCheck("validateUpdatedIcon", "Successfully updated address");
		}
		return this;
	}

	public MyAccountPage validateNickName() {
		ExplicitWait(OR_OR.getProperty("Add_Address_Nickname_header"), "Add_Address_Nickname_header", 80);
		verify_xpath_text(OR_OR.getProperty("Add_Address_Nickname_header"),
				"The nickname FirstTestAddress has already been used. Please use another nickname.");
		verify_xpath_text(OR_OR.getProperty("Add_Address_Nickname_header2"),
				"Name this address E.g.Home or Mum’s House");
		Sendkey_xpath(OR_OR.getProperty("SB_Add_Address_Form1_Nickname_Input_new"), "FirstNickName",
				"SB_Add_Address_Form1_Nickname_Input_new");
		Click_Button_Xpath(OR_OR.getProperty("Add_Address_Nickname_Continue"), "Add_Address_Nickname_Continue");
		return this;
	}

	public MyAccountPage validateDeletedIcon() {
		boolean sucess_delete_icon;
		String deleted_text = "";
		ExplicitWait(OR_OR.getProperty("My_Addresses_Success_Deleted_Icon"), "My_Addresses_Success_Deleted_Icon", 80);
		sucess_delete_icon = isElementPresent(OR_OR.getProperty("My_Addresses_Success_Deleted_Icon"),
				"My_Addresses_Success_Deleted_Icon");
		testLog.info("Icon Present: " + sucess_delete_icon);
		wait(3000);
		deleted_text = driver.findElement(By.xpath(
				"//li[@class='address is-success is-deleted']/div[@class='success-message']//*[contains(@id,'success-message-text-')]"))
				.getText();
		testLog.info("deleted_text: " + deleted_text);
		if (true == sucess_delete_icon && deleted_text.contains("deleted")) {
			testLog.info("Successfully deleted address.");
			wait(3000);
		} else {
			testLog.error("Failed to delete address");
			assertCheck("validateDeletedIcon", "Failed to delete address");
		}
		return this;
	}

	public MyAccountPage enterAddressDetails(String reg_streetAddr, String reg_nickname) {
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 2);

		Clear_Text(OR_OR.getProperty("Street_Address_Input"), "Street_Address_Input");
		Sendkey_xpath(OR_OR.getProperty("Street_Address_Input"), streetAddr, "Street_Address_Input");
		Clear_Text(OR_OR.getProperty("Suburb_Input"), "Suburb_Input");
		Sendkey_xpath(OR_OR.getProperty("Suburb_Input"), suburb, "Suburb_Input");
		Clear_Text(OR_OR.getProperty("Postcode_Input"), "Postcode_Input");
		Sendkey_xpath(OR_OR.getProperty("Postcode_Input"), postcode, "Postcode_Input");

		Select state_dropdown = new Select(driver.findElement(By.id("state-selection")));
		state_dropdown.selectByValue(state);

		Clear_Text(OR_OR.getProperty("Registration_Address_Nickname_Text"), "Registration_Address_Nickname_Text");
		Sendkey_xpath(OR_OR.getProperty("Registration_Address_Nickname_Text"), reg_nickname,
				"Registration_Address_Nickname_Text");
		Clear_Text(OR_OR.getProperty("Registration_Mobile_Number_Field"), "Registration_Mobile_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("Registration_Mobile_Number_Field"), "0412345678",
				"Registration_Mobile_Number_Field");
		Click_Button_Xpath(OR_OR.getProperty("AddNewAddress_continue_button"), "AddNewAddress_continue_button");
		wait(5000);
		ExplicitWait(OR_OR.getProperty("Registration_Address_Header"), "Registration_Address_Header", 30);
		verify_xpath_text(OR_OR.getProperty("Registration_Address_Header"), "Please verify your address...");
		Click_Button_Xpath(OR_OR.getProperty("Registration_Select_Address_HD"), "Registration_Select_Address_HD");
		return this;
	}

	public String getNickName() {
		String reg_nickname = "";
		DateFormat df = new SimpleDateFormat("HH_mm_ss");
		Date dateobj = new Date();
		reg_nickname = reg_nickname.concat("Home");
		reg_nickname = reg_nickname.concat(df.format(dateobj));
		reg_nickname = reg_nickname.replace("_", "");
		reg_nickname.replace('/', ' ');
		return reg_nickname;
	}

	public MyAccountPage deleteAllAddress() {
		if (isElementPresent(OR_OR.getProperty("My_Address_All_DeleteLink"), "My_Address_All_DeleteLink")) {
			String deleteLinks = OR_OR.getProperty("My_Address_DeleteLink_N");
			int loop_count = getXpathCount(OR_OR.getProperty("My_Address_All_DeleteLink"), "My_Address_All_DeleteLink");
			for (int i = 1; i < loop_count; i++) {
				String iaddvalue = Integer.toString(1);
				String dellink1 = deleteLinks.replace("nvalue", iaddvalue);
				driver.findElement(By.xpath(dellink1)).click();
				verify_xpath_text(OR_OR.getProperty("DeleteAddress_verify"), OR_OR.getProperty("DeleteAddress_text"));
				Click_Button_Xpath(OR_OR.getProperty("DeleteAddress_button"), "DeleteAddress_button");
				wait(2000);
				validateDeletedIcon();
			}
		} else {
			testLog.info("Customer has only one address and that is not delete able");
		}
		return this;
	}

	public MyAccountPage clickCommunicationPreferences() {
		try {
			verify_xpath_text(OR_OR.getProperty("MyAccount_Communicationpreferences"), "Communication preferences");
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Communicationpreferences"),
					"MyAccount_Communicationpreferences");

		} catch (Exception e) {
			testLog.error("Not able to click Communication preferences");
		}
		return this;
	}

	public MyAccountPage validateAllCommunicationPrefFields() {
		verify_xpath_text(OR_OR.getProperty("Communicationpreferences_heading"), "Communication preferences");
		verify_xpath_text(OR_OR.getProperty("Communicationpreferences_heading1"),
				"Send me special offers, promotions, and news");
		verify_xpath_text(OR_OR.getProperty("Communicationpreferences_ViaEmail"), "Via email");
		verify_xpath_text(OR_OR.getProperty("Communicationpreferences_ViaSMS"), "Via SMS or phone");
		verify_xpath_text(OR_OR.getProperty("Save_Preferences"), "Save preferences");
		verify_xpath_text(OR_OR.getProperty("Cancel_Preferences_link"), "Cancel");
		return this;
	}

	public MyAccountPage checkCommPref(String commpref) {
		boolean emailCheckBox = driver
				.findElement(By.xpath(OR_OR.getProperty("Communicationpreferences_ViaEmail_Checkbox"))).isSelected();
		boolean smsCheckBox = driver
				.findElement(By.xpath(OR_OR.getProperty("Communicationpreferences_ViaSMS_Checkbox"))).isSelected();
		switch (commpref) {
		case "Email":
			if (!emailCheckBox) {
				testLog.info("Email option is not selected so selecting Email option");
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaEmail_Checkbox"),
						"Communicationpreferences_ViaEmail_Checkbox");
			}
			if (smsCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaSMS_Checkbox"),
						"Communicationpreferences_ViaSMS_Checkbox");
			}
			break;
		case "SMS":
			if (!smsCheckBox) {
				testLog.info("SMS or Phone option is not selected so selecting SMS or Phone option");
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaSMS_Checkbox"),
						"Communicationpreferences_ViaSMS_Checkbox");
			}
			if (emailCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaEmail_Checkbox"),
						"Communicationpreferences_ViaEmail_Checkbox");
			}
		case "EmailAndSMS":
			if (!emailCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaEmail_Checkbox"),
						"Communicationpreferences_ViaEmail_Checkbox");
			}
			if (!smsCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Communicationpreferences_ViaSMS_Checkbox"),
						"Communicationpreferences_ViaSMS_Checkbox");
			}
		default:
			break;
		}

		return this;
	}

	public MyAccountPage clickSavePreferences() {
		Click_Button_Xpath(OR_OR.getProperty("Save_Preferences"), "Save_Preferences");
		return this;
	}

	public MyAccountPage validatePreferenceUpdatedIcon() {
		boolean sucess_updated_icon;
		String updated_text = "";
		ExplicitWait(OR_OR.getProperty("Preferences_Success_Updated_Icon"), "Preferences_Success_Updated_Icon", 80);
		sucess_updated_icon = isElementPresent(OR_OR.getProperty("Preferences_Success_Updated_Icon"),
				"Preferences_Success_Updated_Icon");
		testLog.info("Icon Present: " + sucess_updated_icon);
		wait(3000);
		updated_text = driver
				.findElement(By.xpath("//*[@class='success-message']//*[@id='pref-update-success-heading']")).getText();
		testLog.info("updated_text: " + updated_text);
		if (true == sucess_updated_icon && updated_text.contains("updated")) {
			testLog.info("Successfully updated perferences.");
		} else {
			testLog.error("Failed to update perferences.");
			assertCheck("validatePreferenceUpdatedIcon", "Failed to update perferences");
		}
		return this;
	}

	public MyAccountPage clickDeliveryPreferences() {
		verify_xpath_text(OR_OR.getProperty("MyAccount_Deliverypreferences"), "Delivery preferences");
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Deliverypreferences"), "MyAccount_Deliverypreferences");
		return this;
	}

	public MyAccountPage validateAllDeliveryPreferencesFields() {
		verify_xpath_text(OR_OR.getProperty("Deliverypreferences_heading"), "Delivery preferences");
		verify_xpath_text(OR_OR.getProperty("Deliverypreferences_heading1"), "What should we do if you're not home?");
		verify_xpath_text(OR_OR.getProperty("Deliverypreferences_delivery_AtMyDoor"),
				"Leave my delivery unattended at my door");
		verify_xpath_text(OR_OR.getProperty("Save_Preferences"), "Save preferences");
		verify_xpath_text(OR_OR.getProperty("Cancel_Preferences_link"), "Cancel");
		return this;
	}

	public MyAccountPage DelPrefCheckBox(String DPCheck) {
		boolean DPCheckBox = driver
				.findElement(By.xpath(OR_OR.getProperty("Deliverypreferences_delivery_AtMyDoor_Checkbox")))
				.isSelected();
		switch (DPCheck) {
		case "check":
			if (!DPCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Deliverypreferences_delivery_AtMyDoor_Checkbox"),
						"Deliverypreferences_delivery_AtMyDoor_Checkbox");
			}
			break;

		case "uncheck":
			if (DPCheckBox) {
				Click_Button_Xpath(OR_OR.getProperty("Deliverypreferences_delivery_AtMyDoor_Checkbox"),
						"Deliverypreferences_delivery_AtMyDoor_Checkbox");
			}
		default:
			break;
		}
		return this;
	}

	public MyAccountPage clickPreferredSubstitutes() {
		verify_xpath_text(OR_OR.getProperty("MyAccount_Preferred_Substitute"),
				OR_OR.getProperty("MyAccount_Preferred_Substitute_Text"));
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Preferred_Substitute"), "MyAccount_Preferred_Substitute");

		return this;
	}

	public MyAccountPage validateAllPreferredSubsFields() {
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Heading"),
				OR_OR.getProperty("Preferred_Subs_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Intro"), OR_OR.getProperty("Preferred_Subs_Intro_Text"));
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Search_Label"),
				OR_OR.getProperty("Preferred_Subs_Search_Label_Text"));
		isElementPresent(OR_OR.getProperty("Preferred_Substitute_SearchInputField"),
				"Preferred_Substitute_SearchInputField");
		isElementPresent(OR_OR.getProperty("Preferred_Subs_Search_Button"), "Preferred_Subs_Search_Button");
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Continue_Shopping"),
				OR_OR.getProperty("Preferred_Subs_Continue_Shopping_Text"));

		return this;
	}

	public MyAccountPage clickFirstProductchoosePrefSub() {
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Product1_Choose_Substitute_Link"),
				"Preferred_Subs_Product1_Choose_Substitute_Link");
		return this;
	}

	public MyAccountPage clickSecondProductchoosePrefSub() {
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Product2_Choose_Substitute_Link"),
				"Preferred_Subs_Product2_Choose_Substitute_Link");
		return this;
	}

	public MyAccountPage validatePrefSubPopup() {
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Popup_Header"),
				OR_OR.getProperty("Preferred_Subs_Popup_Header_Text"));
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Popup_Label"),
				OR_OR.getProperty("Preferred_Subs_Popup_Label_Text"));
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_SearchOverlay"),
				OR_OR.getProperty("Preferred_Subs_SearchOverlay_Text"));
		return this;
	}

	public MyAccountPage clickProductSetAsSubs(String product) {
		if (product.equals("SetProduct1")) {
			Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Product1_Set_as_substitute"),
					"Preferred_Subs_Product1_Set_as_substitute");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Product2_Set_as_substitute"),
					"Preferred_Subs_Product2_Set_as_substitute");
		}
		ExplicitWait(OR_OR.getProperty("Preferred_Subs_Set_as_substitute_Green"),
				"Preferred_Subs_Set_as_substitute_Green", 50);
		ExplicitWait(OR_OR.getProperty("Preferred_Subs_Success_Icon"), "Preferred_Subs_Success_Icon", 50);
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Overlay_Close_Button"),
				"Preferred_Subs_Overlay_Close_Button");
		return this;
	}

	public MyAccountPage clearAllSubstitutes() {
		if (isElementPresent(OR_OR.getProperty("Preferred_Subs_Substituted_Products_Clear_Button"),
				"Preferred_Subs_Substituted_Products_Clear_Button")) {
			int allClearLink = getXpathCount(OR_OR.getProperty("Preferred_Subs_Substituted_Products_Clear_Button"),
					"Preferred_Subs_Substituted_Products_Clear_Button");
			testLog.info("Number of Clear button displayed is :" + allClearLink);
			for (int i = 1; i <= allClearLink; i++) {
				Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Substituted_Products_Clear_Button"),
						"Preferred_Subs_Substituted_Products_Clear_Button");
				wait(2000);
			}
		} else {
			testLog.info("No substitues added to clear");
		}
		return this;
	}

	public MyAccountPage clickBackToHomePage() {
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_BackTo_HomePage"), "Preferred_Subs_BackTo_HomePage");
		return this;
	}

	public MyAccountPage clickChangeYourPassword() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Change_Your_Password"), "MyAccount_Change_Your_Password", 5);
//		verify_xpath_text(OR_OR.getProperty("MyAccount_Change_Your_Password"),
//				OR_OR.getProperty("MyAccount_Change_Your_Password_Text"));
		// stepup authentication changes--> change your password test changed to
		// password and security preferences
		verify_xpath_text(OR_OR.getProperty("MyAccount_Password_And_Security_Preferences"),
				OR_OR.getProperty("MyAccount_Password_And_Security_Preferences_Text"));
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Change_Your_Password"), "MyAccount_Change_Your_Password");
		return this;
	}

	public MyAccountPage enterOldPassword(String oldPassword) {
		ExplicitWait(OR_OR.getProperty("MyAccount_Old_Password"), "MyAccount_Old_Password", 5);
		Clear_Text(OR_OR.getProperty("MyAccount_Old_Password"), "MyAccount_Old_Password");
		Sendkey_xpath(OR_OR.getProperty("MyAccount_Old_Password"), oldPassword, "MyAccount_Old_Password");
		return this;
	}

	public MyAccountPage enterNewPassword(String newPassword) {
		Clear_Text(OR_OR.getProperty("MyAccount_New_Password"), "MyAccount_New_Password");
		Sendkey_xpath(OR_OR.getProperty("MyAccount_New_Password"), newPassword, "MyAccount_New_Password");
		return this;
	}

	public MyAccountPage clickSaveButton() {
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_New_Password"), "MyAccount_Save_New_Password");
		return this;
	}

	public MyAccountPage validatePasswordChangeSuccessMessage() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Password_Change_Message"), "MyAccount_Password_Change_Message", 5);

		String message = get_xpath_text(OR_OR.getProperty("MyAccount_Password_Change_Message"),
				"MyAccount_Password_Change_Message");
		String message1 = OR_OR.getProperty("MyAccount_Password_Change_Message_Text_Old");
		String message2 = OR_OR.getProperty("MyAccount_Password_Change_Message_Text");
		String message3 = OR_OR.getProperty("MyAccount_Password_Change_Message_Text_Old1");
//		verify_xpath_text(OR_OR.getProperty("MyAccount_Password_Change_Message"),
//				OR_OR.getProperty("MyAccount_Password_Change_Message_Text_Old"));
		if ((message.equals(message1) || message.equals(message2) || message.equals(message3))) {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Password_Change_Ok_Btn"),
					"MyAccount_Password_Change_Ok_Btn");
			ExplicitWait(OR_OR.getProperty("MyAccount_Change_Your_Password"), "MyAccount_Change_Your_Password", 5);
		}
		return this;
	}

	public MyAccountPage clickChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Substituted_Products_Change_Button"),
				"Preferred_Subs_Substituted_Products_Change_Button");
		return this;
	}

	public MyAccountPage clickSortingChangeBtn() {
		Click_Button_Xpath(OR_OR.getProperty("Preffered_Subs_Sortedby_Change_Button"),
				"Preffered_Subs_Sortedby_Change_Button");
		ExplicitWait(OR_OR.getProperty("Preffered_Subs_Sort_Options_Heading"), "Preffered_Subs_Sort_Options_Heading",
				10);
		return this;
	}

	public MyAccountPage clickSortingOptionBrand() {
		Click_Button_Xpath(OR_OR.getProperty("Preffered_Subs_Sort_Options_Brand(A-Z)_RadioButton"),
				"Preffered_Subs_Sort_Options_Brand(A-Z)_RadioButton");
		return this;
	}

	public MyAccountPage clickSortingOptionProduct() {
		Click_Button_Xpath(OR_OR.getProperty("Preffered_Subs_Sort_Options_Product(A-Z)_RadioButton"),
				"Preffered_Subs_Sort_Options_Product(A-Z)_RadioButton");
		return this;
	}

	public MyAccountPage clickSortingOptionSaveBtn() {
		Click_Button_Xpath(OR_OR.getProperty("Preffered_Subs_Sort_Options_Save_Button"),
				"Preffered_Subs_Sort_Options_Save_Button");
		return this;
	}

	public MyAccountPage validateSortedByBrand() {
		int product_count = getXpathCount(OR_OR.getProperty("Preffered_Subs_All_Products"),
				"Preffered_Subs_All_Products");
		String nth_brand_name = OR_OR.getProperty("Preffered_Subs_Product_BrandName_Nvalue");

		for (int nvalue = 1; nvalue < product_count; nvalue++) {

			String strvalue1 = Integer.toString(nvalue);
			String strvalue2 = Integer.toString(nvalue + 1);

			String bname_1 = nth_brand_name.replace("nvalue", strvalue1);
			String bname_2 = nth_brand_name.replace("nvalue", strvalue2);

			String strbname_1 = driver.findElement(By.xpath(bname_1)).getText();
			String strbname_2 = driver.findElement(By.xpath(bname_2)).getText();

			if (strbname_1.compareToIgnoreCase(strbname_2) <= 0) {
				testLog.info("The tile no." + strvalue1 + " with brand name: " + strbname_1
						+ " is less than or equal to the tile no." + strvalue2 + " with brand name: " + strbname_2);

			} else {
				testLog.error("The tile no." + strvalue1 + " with brand name: " + strbname_1
						+ " is sorted wrongly and greater than the tile no." + strvalue2 + " with brand name: "
						+ strbname_2);

				assertCheck("validateSortedByBrand",
						"\"The tile no.\" + strvalue1 + \" with brand name: \" + strbname_1\r\n"
								+ "+ \" is sorted wrongly and greater than the tile no.\" + strvalue2 + \" with brand name: \"\r\n"
								+ "+ strbname_2",
						"");
			}
		}
		return this;
	}

	public MyAccountPage validateSortedByProduct() {
		int product_count = getXpathCount(OR_OR.getProperty("Preffered_Subs_All_Products"),
				"Preffered_Subs_All_Products");
		String nth_product_name = OR_OR.getProperty("Preffered_Subs_Product_ProductName_Nvalue");

		for (int nvalue = 1; nvalue < product_count; nvalue++) {

			String strvalue1 = Integer.toString(nvalue);
			String strvalue2 = Integer.toString(nvalue + 1);

			String pname_1 = nth_product_name.replace("nvalue", strvalue1);
			String pname_2 = nth_product_name.replace("nvalue", strvalue2);

			String strpname_1 = driver.findElement(By.xpath(pname_1)).getText();
			String strpname_2 = driver.findElement(By.xpath(pname_2)).getText();

			if (strpname_1.compareToIgnoreCase(strpname_2) <= 0) {
				testLog.info("The tile no." + strvalue1 + " with product name: " + strpname_1
						+ " is less than or equal to the tile no." + strvalue2 + " with product name: " + strpname_2);

			} else {
				testLog.error("The tile no." + strvalue1 + " with product name: " + strpname_1
						+ " is sorted wrongly and greater than the tile no." + strvalue2 + " with product name: "
						+ strpname_2);

				status = 1;
				assertCheck("validateSortedByBrand",
						"The tile no." + strvalue1 + " with product name: " + strpname_1 + ""
								+ "is sorted wrongly and greater than the tile no." + strvalue2 + " with product name:"
								+ strpname_2,
						"");
			}
		}
		return this;
	}

	public boolean isCardDetailSaved() {
		wait(2000);
		String text = get_xpath_text(OR_OR.getProperty("MyAccoutn_Payment_Details_Heading3"),
				"MyAccoutn_Payment_Details_Heading3");
		if (!(text.equals(OR_OR.getProperty("MyAccoutn_Payment_Details_Heading_Text3")))) {
			return true;
		} else {
			return false;
		}
	}

	public MyAccountPage validateSavedCardDetails() {
		String cardEndDigit = cardno.substring(cardno.length() - 4);
		isElementPresent(OR_OR.getProperty("MyAccount_Payment_Details_Heading2"), "MyAccount_Payment_Details_Heading2");
		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_Heading2"),
				OR_OR.getProperty("MyAccount_Payment_Details_Heading_Text2"));

		isElementPresent(OR_OR.getProperty("MyAccount_Payment_Details_SavedCardNo"),
				"MyAccount_Payment_Details_SavedCardNo");
		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_SavedCardNo"),
				"Card number ending in " + cardEndDigit);

		isElementPresent(OR_OR.getProperty("MyAccount_Payment_Details_SavedCardExpiryDetail"),
				"MyAccount_Payment_Details_SavedCardExpiryDetail");
		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_SavedCardExpiryDetail"),
				"Valid up to " + expiry_month + " / " + expiry_year);

		isElementPresent(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_Btn"),
				"MyAccount_Payment_Details_Delete_Card_Btn");
		return this;
	}

	public MyAccountPage clickCardDetailsDeleteButton() {
		wait(4000);
		ExplicitWait(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_Btn"),
				"MyAccount_Payment_Details_Delete_Card_Btn", 10);

		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_Btn"),
				"MyAccount_Payment_Details_Delete_Card_Btn");
		ExplicitWait(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_ConfirmationBtn"),
				"MyAccount_Payment_Details_Delete_Card_ConfirmationBtn", 10);

		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_WarningHeading1"),
				OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_WarningHeading1_Text"));
//		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_WarningHeading2"),
//				OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_WarningHeading2_Text"));
		isElementPresent(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_ConfirmationBtn"),
				"MyAccount_Payment_Details_Delete_Card_ConfirmationBtn");
		return this;
	}

	public MyAccountPage clickCardDetailsConfirmDeleteButton() {
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_ConfirmationBtn"),
				"MyAccount_Payment_Details_Delete_Card_ConfirmationBtn");
		ExplicitWait(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_SuccessMsg"),
				"MyAccount_Payment_Details_Delete_Card_SuccessMsg", 30);

		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_SuccessMsg"),
				OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_SuccessMsg_Text"));
		return this;
	}

	public MyAccountPage validateDeleteConfirmationMessage() {
		verify_xpath_text(OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_SuccessMsg"),
				OR_OR.getProperty("MyAccount_Payment_Details_Delete_Card_SuccessMsg_Text"));
		return this;
	}

	public MyAccountPage validatePrefSubsPageWithoutBBProducts() {
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Heading"),
				OR_OR.getProperty("Preferred_Subs_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Preferred_Subs_Intro"), OR_OR.getProperty("Preferred_Subs_Intro_Text"));
		verify_xpath_text(OR_OR.getProperty("Preffered_subs_Without_BB_Products_Header"),
				OR_OR.getProperty("Preffered_subs_Without_BB_Products_Header_Text"));
		return this;
	}

	public MyAccountPage clickShoppingReminders() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("MyAccount_Shopping_Reminders"), "MyAccount_Shopping_Reminders", 20);
		verify_xpath_text(OR_OR.getProperty("MyAccount_Shopping_Reminders"),
				OR_OR.getProperty("MyAccount_Shopping_Reminders_Text"));
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Shopping_Reminders"), "MyAccount_Shopping_Reminders");
		return this;
	}

	public MyAccountPage selectRemindMeOption() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("MyAccount_Save_Preferences_Btn"), "MyAccount_Save_Preferences_Btn", 20);
		boolean flag = isElementSelected(OR_OR.getProperty("MyAccount_Shopping_Reminders_Checkbox"));

		if (flag == true) {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_Preferences_Back_Lnk"),
					"MyAccount_Save_Preferences_Back_Lnk");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Shopping_Reminders_Checkbox"),
					"MyAccount_Shopping_Reminders_Checkbox");
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_Preferences_Btn"), "MyAccount_Save_Preferences_Btn");
		}
		return this;
	}

	public MyAccountPage unSelectRemindMeOption() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("MyAccount_Save_Preferences_Btn"), "MyAccount_Save_Preferences_Btn", 20);
		boolean flag = isElementSelected(OR_OR.getProperty("MyAccount_Shopping_Reminders_Checkbox"));

		if (flag == true) {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Shopping_Reminders_Checkbox"),
					"MyAccount_Shopping_Reminders_Checkbox");
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_Preferences_Btn"), "MyAccount_Save_Preferences_Btn");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_Preferences_Back_Lnk"),
					"MyAccount_Save_Preferences_Back_Lnk");
		}
		return this;
	}

	public MyAccountPage searchPrefSubsItem(String item) {
		ExplicitWait(OR_OR.getProperty("Preferred_Substitute_SearchInputField"), "Preferred_Substitute_SearchInputField", 60);
		wait(5000);
		driver.findElement(By.xpath(OR_OR.getProperty("Preferred_Substitute_SearchInputField"))).click();
		Sendkey_xpath(OR_OR.getProperty("Preferred_Substitute_SearchInputField"), item,
				"Preferred_Substitute_SearchInputField");
		return this;
	}

	public MyAccountPage clickPrefSubsSearchButton() {
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("Preferred_Subs_Search_Button"), "Preferred_Subs_Search_Button");
		ExplicitWait(OR_OR.getProperty("Preffered_subs_Clear_button"), "Preffered_subs_Clear_button", 20);
		return this;
	}

	public MyAccountPage validateSearchResultText(String item) {
		wait(2000);
		if (item.contains("Milk")) {
			ExplicitWait(OR_OR.getProperty("Preffered_subs_Search_Result"), "Preffered_subs_Search_Result", 50);
			verify_xpath_contains_text(OR_OR.getProperty("Preffered_subs_Search_Result"),
					"found for " + "\"" + search_1 + "\"");
		} else {
			ExplicitWait(OR_OR.getProperty("Preffered_subs_Search_Result"), "Preffered_subs_Search_Result", 50);
			verify_xpath_contains_text(OR_OR.getProperty("Preffered_subs_Search_Result"),
					"found for " + "\"" + search_2 + "\"");
		}
		return this;
	}

	public MyAccountPage validateSearchedProductName(String item) {
		int product_count = getXpathCount(OR_OR.getProperty("Preffered_Subs_All_Products"),
				"Preffered_Subs_All_Products");
		String nth_product_name = OR_OR.getProperty("Preffered_subs_All_Product_Nvalue");
		for (int nvalue = 1; nvalue < product_count; nvalue++) {

			String strvalue1 = Integer.toString(nvalue);
			String productname_1 = nth_product_name.replace("nvalue", strvalue1);
			String strproductname_1 = driver.findElement(By.xpath(productname_1)).getText();
			testLog.info(strproductname_1);
			wait(2000);
			if (strproductname_1.contains(item)) {
				testLog.info("Product name: " + strproductname_1 + " contains searched item " + item);

			} else {
				testLog.error("Product name not contains searched item " + item);
				assertCheck("validateSearchedProductName",
						strproductname_1 + " does not contains searched item" + item);
			}
		}
		return this;
	}

	public MyAccountPage clickClearButton() {
		wait(5000);
		Click_Button_Xpath(OR_OR.getProperty("Preffered_subs_Clear_button"), "Preffered_subs_Clear_button");
		return this;
	}

	public MyAccountPage clickSortingOptionTimesBought() {
		boolean TBcheckbox = isElementSelected(
				OR_OR.getProperty("Preffered_Subs_Sort_Options_TimesBought_RadioButton"));
		if (!TBcheckbox) {
			Click_Button_Xpath(OR_OR.getProperty("Preffered_Subs_Sort_Options_TimesBought_RadioButton"),
					"Preffered_Subs_Sort_Options_TimesBought_RadioButton");
		}
		return this;
	}

	public ArrayList<String> getAllProductName() {

		ArrayList<String> allProducts = new ArrayList<String>();
		int product_count = getXpathCount(OR_OR.getProperty("Preffered_subs_All_Product_Name"),
				"Preffered_subs_All_Product_Name");
		String nth_product_name = OR_OR.getProperty("Preffered_subs_All_Product_Nvalue");
		for (int nvalue = 1; nvalue < product_count; nvalue++) {
			String strvalue1 = Integer.toString(nvalue);
			String productname_1 = nth_product_name.replace("nvalue", strvalue1);
			String strproductname_1 = driver.findElement(By.xpath(productname_1)).getText();
			testLog.info(strproductname_1);
			allProducts.add(strproductname_1);
		}
		return allProducts;
	}

	public MyAccountPage validateSortedByTimesBought(ArrayList<String> beforesorting) {
		ArrayList<String> aftersorting = this.getAllProductName();
		if (aftersorting.equals(beforesorting)) {
			testLog.info("All Bought before products are sorted as times bought");
		}
		return this;
	}

	public MyAccountPage clickDietryAndPreferences() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Dietry_And_Lifestyle"), "MyAccount_Dietry_And_Lifestyle", 5);
		verify_xpath_text(OR_OR.getProperty("MyAccount_Dietry_And_Lifestyle"),
				OR_OR.getProperty("MyAccount_Dietry_And_Lifestyle_Text"));
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Dietry_And_Lifestyle"), "MyAccount_Dietry_And_Lifestyle");
		return this;
	}

	public MyAccountPage selectDietryAndPreferencesOption() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Save_Preference_Btn"),
				"MyAccount_Dietry_And_Prefences_Save_Preference_Btn", 20);
		boolean flag = isElementSelected(OR_OR.getProperty("MyAccount_Firt_Checkbox"));

		if (flag == true) {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Save_Preference_Btn"),
					"MyAccount_Dietry_And_Prefences_Save_Preference_Btn");
		} else {
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Firt_Checkbox"), "MyAccount_Firt_Checkbox");
			Click_Button_Xpath(OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Save_Preference_Btn"),
					"MyAccount_Dietry_And_Prefences_Save_Preference_Btn");
		}
		return this;
	}

	public MyAccountPage validateDietryAndPreferencesSuccessMessage() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Updated"),
				"MyAccount_Dietry_And_Prefences_Updated", 5);
		verify_xpath_text(OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Updated"),
				OR_OR.getProperty("MyAccount_Dietry_And_Prefences_Updated_Text"));
		return this;
	}

	public MyAccountPage clickColesEmployee() {
		ExplicitWait(OR_OR.getProperty("MyAccount_Coles_Employee"), "MyAccount_Coles_Employee", 5);
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Coles_Employee"), "MyAccount_Coles_Employee");

		return this;
	}

	public MyAccountPage saveColesNumber(String number) {
		ExplicitWait(OR_OR.getProperty("MyAccount_MyColes_Number_Field"), "MyAccount_MyColes_Number_Field", 3);
		Clear_Text(OR_OR.getProperty("MyAccount_MyColes_Number_Field"), "MyAccount_MyColes_Number_Field");
		Sendkey_xpath(OR_OR.getProperty("MyAccount_MyColes_Number_Field"), number, "MyAccount_MyColes_Number_Field");
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Save_MyColes_Number_Btn"), "MyAccount_Save_MyColes_Number_Btn");
		return this;
	}

	public MyAccountPage validateSuccessMessage() {
		ExplicitWait(OR_OR.getProperty("MyColes_Number_Confimation_Message"), "MyColes_Number_Confimation_Message", 3);
		verify_xpath_text(OR_OR.getProperty("MyColes_Number_Confimation_Message"),
				OR_OR.getProperty("MyColes_Number_Confimation_Message_Text"));
		return this;
	}

	public MyAccountPage clickOk() {
		Click_Button_Xpath(OR_OR.getProperty("MyColes_Saved_Number_Ok_Btn"), "MyColes_Saved_Number_Ok_Btn");
		return this;
	}

	public MyAccountPage validateUpdatedMessage() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("MyAccount_Coles_Employee"), "MyAccount_Coles_Employee", 3);
		verify_xpath_contains_text(OR_OR.getProperty("MyAccount_Coles_Employee"),
				OR_OR.getProperty("MyAccount_Coles_Change_Employee_Text"));
		return this;
	}

	public MyAccountPage validateFlybuysInvalidMsg() {
		ExplicitWait(OR_OR.getProperty("Fly_buy_skip"), "Fly_buy_skip", 5);
		verify_xpath_text(OR_OR.getProperty("Link_Flybuys_Invalid_Msg"),
				OR_OR.getProperty("Link_Flybuys_Invalid_Msg_Text"));
		return this;
	}

	public MyAccountPage clickSkipStep() {
		Click_Button_Xpath(OR_OR.getProperty("Fly_buy_skip"), "Fly_buy_skip");
		return this;
	}

	public MyAccountPage unLinkFybuys() {
		if (isElementPresent(OR_OR.getProperty("Flybuys_Linked_Number"), "Flybuys_Linked_Number")) {
			this.deleteLinkedFlybuysNum();
			this.validateDeleteSuccessMsg();
		} else {
			testLog.info("No Flybuys number is linked");
		}
		return this;
	}

	public MyAccountPage validateErrorMessage() {
		ExplicitWait(OR_OR.getProperty("MyColes_Number_Confimation_Message"), "MyColes_Number_Confimation_Message", 3);
		verify_xpath_text(OR_OR.getProperty("MyColes_Number_Confimation_Message"),
				OR_OR.getProperty("MyColes_Invalid_Number_Msg"));
		return this;
	}

	public MyAccountPage clickBackToMyAccount() {
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Go_Back_Link"), "MyAccount_Go_Back_Link");
		ExplicitWait(OR_OR.getProperty("Logout_Button"), "Logout_Button", 5);
		return this;
	}

	public MyAccountPage clickShopNow() {
		ExplicitWait(OR_OR.getProperty("List_Shop_Now_Btn"), "List_Shop_Now_Btn", 2);
		Click_Button_Xpath(OR_OR.getProperty("List_Shop_Now_Btn"), "List_Shop_Now_Btn");
		return this;
	}

	public MyAccountPage clickMyShoppingList() {
		ExplicitWait(OR_OR.getProperty("MyAccount_My_Shopping_List_Link"), "MyAccount_My_Shopping_List_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_My_Shopping_List_Link"), "MyAccount_My_Shopping_List_Link");
		wait(5000);
		return this;
	}

	public MyAccountPage validateAddedProductCount(String searchskus) {
		String[] sku_list = searchskus.split(",");
		ExplicitWait(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name", 20);
		String listName = get_xpath_text(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name");
		int listCount = sku_list.length;
		// String listCount = listName.substring(listName.indexOf('(') + 1,
		// listName.indexOf(')'));

		if (listName.contains(Integer.toString(listCount))) {
			testLog.info("**" + listCount + "**" + "Product(s)/SKU(s) successfully added into **List**");

		} else {
			testLog.error("Opps!! Something went wrong!! ***Product/SKU(s)*** NOT added to list");
			assertCheck("validateAddedProductCount", "Products/SKU's are NOT added into list", listName);
		}
//		assertInfo("validateAddedProductCount", "Products/SKU's: " + listCount + " are NOT added into list",
//				listName);
		return this;
	}

	public MyAccountPage clickEdit() {
		Click_Button_Xpath(OR_OR.getProperty("SB_EditList_Button"), "SB_EditList_Button");
		wait(2000);
		return this;
	}

	public MyAccountPage validateProdutInList(String brandName, String productName) {
		ExplicitWait(OR_OR.getProperty("MyAccount_Product_Brand_Name"), "MyAccount_Product_Brand_Name", 10);
		verify_xpath_contains_text(OR_OR.getProperty("MyAccount_Product_Brand_Name"), brandName);
		verify_xpath_contains_text(OR_OR.getProperty("MyAccount_Product_Brand_Name"), productName);
		return this;
	}

	public MyAccountPage goToMyListFromEditList() {
		ExplicitWait(OR_OR.getProperty("SB_MyList_Back_Link"), "SB_MyList_Back_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("SB_MyList_Back_Link"), "SB_MyList_Back_Link");
		wait(2000);
		return this;
	}

	public MyAccountPage deleteProductFromList() {
		Click_Button_Xpath(OR_OR.getProperty("MyAccount_Delete_Product_Icon"), "MyAccount_Delete_Product_Icon");
		wait(2000);
		return this;
	}

	public MyAccountPage validateProductDeletedFromList() {
		ExplicitWait(OR_OR.getProperty("MyAccount_List_Empty_Msg"), "MyAccount_List_Empty_Msg", 10);
		verify_xpath_contains_text(OR_OR.getProperty("MyAccount_List_Empty_Msg"),
				OR_OR.getProperty("MyAccount_List_Empty_Msg_Text"));
		boolean flag = isElementNotPresent(OR_OR.getProperty("MyAccount_Product_Brand_Name"),
				"MyAccount_Product_Brand_Name");
		if (flag == true) {
			testLog.info("Product successfully deleted from the list");
		} else {
			testLog.error("Opps!! Something went wrong, Product still appearing in list");
			assertCheck("validateProductDeletedFromList",
					"Opps!! Something went wrong, Product still appearing in list", "validateProductDeletedFromList");
		}

		return this;
	}

	public MyAccountPage validateProductCountAfterDeletedFromlist(String searchskus) {
		String[] sku_list = searchskus.split(",");
		ExplicitWait(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name", 20);
		String listName = get_xpath_text(OR_OR.getProperty("SB_MyList_Name"), "SB_MyList_Name");
		int listCount = sku_list.length - 1;
		// String listCount = listName.substring(listName.indexOf('(') + 1,
		// listName.indexOf(')'));

		if (listName.contains(Integer.toString(listCount))) {
			testLog.info("**" + listCount + "**" + "Product(s)/SKU(s) available in **List**");

		} else {
			testLog.error("Opps!! Something went wrong!! ***Product/SKU(s)*** NOT deleted from list");
			assertCheck("validateAddedProductCount", "Products/SKU's are NOT deleted from list", listName);
		}
//		assertInfo("validateAddedProductCount", "Products/SKU's: " + listCount + " are NOT delted from list",
//				listName);
		return this;
	}

	public SubscriptionPage VerifyDeliveryPlusOptionisNotThereOnSuperbar() {
		// Verify delivery plus subscription on superbar
		boolean DelPlusSub = isElementPresent(OR_OR.getProperty("Superbar_Delivery_Subscription"),
				"Superbar_Delivery_Subscription");

		if (!DelPlusSub) {
			testLog.info("Delivery plus option is not available as expected");
		} else {
			testLog.error(
					"Delivery plus option is available when the user is not there in the Delivery Plus invitee segment");
			assertCheck("VerifyDeliveryPlusOptionisNotThereOnSuperbar",
					"Delivery plus option is available when the user is not there in the Delivery Plus invitee segment");
		}
		return new SubscriptionPage();
	}
	
	public MyAccountPage validateSavedDOB() {
		verify_xpath_text(OR_OR.getProperty("Name_Contact_Details_DateOfBirth_Value"),"10 February 1990");
		return this;
	}
	
	public MyAccountPage clickMyAddressesLink() {
		int refreshcount = 0;
		ExplicitWait(OR_OR.getProperty("Superbar_MyAddressesLink"), "Superbar_MyAddressesLink", 10);
		while (refreshcount < 6)
			if (isElementVisible(OR_OR.getProperty("Superbar_MyAddressesLink"), "Superbar_MyAddressesLink")) {				
				Click_Button_Xpath(OR_OR.getProperty("Superbar_MyAddressesLink"), "Superbar_MyAddressesLink");
				break;
			} else {
				driver.navigate().refresh();
				getPages.getSuperBarPage().clickMyAccount();
				++refreshcount;
			}
		return this;
	}
	
	public RegistrationPage clickAddAddressButton() {
		ExplicitWait(OR_OR.getProperty("Superbar_Add_Address_Button"), "Superbar_Add_Address_Button", 10);
		Click_Button_Xpath(OR_OR.getProperty("Superbar_Add_Address_Button"), "Superbar_Add_Address_Button");
		return new RegistrationPage();
	}
}
