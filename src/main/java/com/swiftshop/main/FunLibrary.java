package com.swiftshop.main;

import static org.testng.Assert.fail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.swiftshop.pages.GetPages;
import com.swiftshop.pages.SuperBarPage;

public class FunLibrary extends SelLibrary {

	public static String RandomGeneratedUsername = null;

	public Logger testLog = Logger.getLogger("debugLogger:" + Thread.currentThread().getId());

	public int random_bag_flag = -1;

	public String method_name = "";
	public String LocationName = "";
	public String locationName = "";
	public String search_1 = "";
	public String search_2 = "";
	public String nickname = "";
	public String streetAddr = "";
	public String suburb = "";
	public String postcode = "";
	public String state = "";
	public String first_name = "";
	public String last_name = "";
	public String password = "";
	public String cardno = "";
	public String expiry_month = "";
	public String expiry_year = "";
	public String cvv = "";
	public String paypal_id = "";
	public String paypal_password = "";
	public String abn = "";
	public String username = "";
	public String test_data = "";
	public String service_Type = "";
	public GetPages getPages;
	public String winHandle = "";
	public String suburbAddr = "";
	public String mobNumber = "";
	public String suburb1 = "";
	public String mobNumber1 = "";
	public String acsAuthResult = "";

	public FunLibrary() {
		load_Obj_Repository();
		getExcelData(sheet_name);
		getPages = new GetPages();
	}

	public void getExcelData(String sheetname) {
		username = datatable.getCellData(sheet_name, "username", currentRow);
		password = datatable.getCellData(sheet_name, "password", currentRow);
		search_1 = datatable.getCellData(sheet_name, "search1", currentRow);
		search_2 = datatable.getCellData(sheet_name, "search2", currentRow);
		first_name = datatable.getCellData(sheet_name, "Firstname", currentRow);
		last_name = datatable.getCellData(sheet_name, "Lastname", currentRow);
		password = datatable.getCellData(sheet_name, "password", currentRow);
		state = datatable.getCellData(sheet_name, "state", currentRow);
		streetAddr = datatable.getCellData(sheet_name, "streetAddr", currentRow);
		suburb = datatable.getCellData(sheet_name, "suburb", currentRow);
		suburbAddr = datatable.getCellData(sheet_name, "suburbAddr", currentRow);
		postcode = datatable.getCellData(sheet_name, "postcode", currentRow);
		nickname = datatable.getCellData(sheet_name, "Nickname", currentRow);
		test_data = datatable.getCellData(sheet_name, "Data", currentRow);
		cardno = datatable.getCellData(sheet_name, "Cardno", currentRow);
		expiry_month = datatable.getCellData(sheet_name, "ExpiryMonth", currentRow);
		expiry_year = datatable.getCellData(sheet_name, "ExpiryYear", currentRow);
		cvv = datatable.getCellData(sheet_name, "CVV", currentRow);
		abn = datatable.getCellData(sheet_name, "ABN", currentRow);
		paypal_id = datatable.getCellData(sheet_name, "PaypalId", currentRow);
		paypal_password = datatable.getCellData(sheet_name, "PaypalPass", currentRow);
		service_Type = datatable.getCellData(sheet_name, "serviceType", currentRow);
		locationName = datatable.getCellData(sheet_name, "LocationName", currentRow);
		mobNumber = datatable.getCellData(sheet_name, "MobileNumber", currentRow);
		suburb1 = datatable.getCellData(sheet_name, "suburb1", currentRow);
		mobNumber1 = datatable.getCellData(sheet_name, "MobileNumber1", currentRow);
		acsAuthResult = datatable.getCellData(sheet_name, "authResult", currentRow);
	}

	public void login() {
		try {
			testLog.info("Logging in...");
			if (!(username.equals(""))) {
				testLog.info("Username for test is" + username);
				testLog.info("password for test is" + password);
				SuperBarPage superBarPage = new SuperBarPage();
				superBarPage.clickLoginSignup().enterUserName(username).enterPassword(password).clickLogin()
						.clickWelcomeBackOKButton();
				testLog.info("Login successfully");
			} else {
				testLog.error("Username is blank");
				fail("login failed!! Username is blank");
			}

		} catch (Exception e) {
			testLog.error("login failed!!");
			fail("login failed!!");
		}
	}

	public void LocalizeToHD(String HD_address_name) {
		int prev_loc_flag = 0;

		if (!(getPages.getHomePage().isSlotSelected())) {
			prev_loc_flag = 1;
		} else {
			Click_Button_Xpath(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time");
			wait(5000);
			String ChooseDeliveryTimeHeaderText = get_xpath_text(OR_OR.getProperty("CC_Selected_Header"),
					"CC_Selected_Header");
			testLog.info("value of choose delivery time is " + ChooseDeliveryTimeHeaderText);
			if (ChooseDeliveryTimeHeaderText.contains("Pick up from")) {
				prev_loc_flag = 2;
			} else if (ChooseDeliveryTimeHeaderText.contains("Delivery to")) {
				prev_loc_flag = 3;
			} else if (ChooseDeliveryTimeHeaderText.equals("Delivery")) {
				prev_loc_flag = 4;
			}
			Click_Button_Xpath(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time");
			wait(5000);
		}

		switch (prev_loc_flag) {
		case 1:
			testLog.info("No timeslot selected, Localization to HD");
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress(HD_address_name)
					.ClickCancelAndKeepShopingLink();
			break;
		case 2:
			testLog.info("CC with timeslot to HD localization");
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChooseDifferentTimeLink()
					.clickChooseDeliveryAddressLinkDSS().clickHDAddressDSS(HD_address_name)
					.ClickCancelAndKeepShopingLink();
			break;
		case 3:
			testLog.info("HD with timeslot to HD localization");
			getPages.getSuperBarPage().clickChooseDeliverTime();
			getPages.getChooseDeliveryTimePage().clickCCLocationLink().clickFirstClickAndCollectSuburbDSS()
					.clickChooseCollectionTimeButtonDSS().validateDSSPagePresent().ClickCancelAndKeepShopingLink();
			LocalizeToHD(HD_address_name);
			break;
		case 4:
			testLog.info("RD with timeslot to HD localization");
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChooseDifferentTimeLink()
					.clickTryAnotherAddressLinkDSS().clickHDAddressDSS(HD_address_name).ClickCancelAndKeepShopingLink();
			break;
		}
	}

	public void scrollTo_Xpath(String xpath, String ObjName, int division) {
		try {
			WebElement element1 = driver.findElement(By.xpath(xpath));
			int x = element1.getLocation().getX();
			int y = element1.getLocation().getY();
			wait(2000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x / division + "," + y / division + ")",
					"");
			testLog.info("Scrolled to " + ObjName);
			wait(3000);
		} catch (Exception e) {
			testLog.error("Element not present: " + ObjName);
			assertCheck("scrollTo_Xpath", "Unable to scroll to given element", ObjName, e);
		}
	}

	public void scrollTo_Pixel(int x, int y) {
		try {
			wait(2000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")", "");
			testLog.info("Scrolled successfully");
			wait(3000);
		} catch (Exception e) {
			testLog.error("Issue in scrolling");
			assertCheck("scrollTo_Pixel", "Unable to scroll to given pixels", "X:" + x + ", Y:" + y, e);
		}
	}

	public String emaild() {
		String str_email;
		str_email = "automation_";
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date dateobj = new Date();
		str_email = str_email.concat(df.format(dateobj)).concat("@mailinator.com");
		str_email = str_email.replace("_", "");
		str_email.replace('/', ' ');
		RandomGeneratedUsername = str_email;
		return RandomGeneratedUsername;
	}

	// Function to return the full month name based on the month (ex: JAN) provided
	public String getFullMonthName(String month) {
		String MonthName = "";
		try {
			String[] monthList = { "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };
			for (String month1 : monthList) {
				if (month1.toLowerCase().contains(month.toLowerCase())) {
					testLog.info("Month is: " + month1);
					MonthName = month1;
					break;
				}
			}
			if (MonthName == "") {
				testLog.warn("Month name is empty!!");
			}
			return MonthName;
		} catch (Exception e) {
			return "";
		}
	}

	// Function to return the full day name (ex: MON) provided
	public String getFullDayName(String day) {
		String dayName = "";
		try {
			String[] dayList = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
			for (String day1 : dayList) {
				if (day1.toLowerCase().contains(day.toLowerCase())) {
					testLog.info("Day is: " + day1);
					dayName = day1;
					break;
				}
			}
			if (dayName == "") {
				testLog.warn("Day name is empty!!");
			}
			return dayName;
		} catch (Exception e) {
			return "";
		}
	}

	// Function to return the day of week based on the date provided
	public String senddayofweeek(String datevalue) {
		try {
			String input_date = datevalue;
			input_date = input_date.replaceAll(" ", "/");
			System.out.println(input_date);
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MMM/yyyy");
			Date dt1 = format1.parse(input_date);
			DateFormat format2 = new SimpleDateFormat("EEEE");
			String finalDay = format2.format(dt1);
			testLog.info("Day is:" + finalDay);
			return finalDay;

		} catch (Exception e) {
			return "";
		}
	}

//	public void registerNewUser() {
//		// Register new user
//		getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterFirstNameLastName()
//				.enterRandomEmailID().enterPassword().ClickContinueButton().enterAddressDetails();
//		getPages.getRegistrationPage().validateSuccessMsg().clickContinueShoppingBtn();
//	}

}
