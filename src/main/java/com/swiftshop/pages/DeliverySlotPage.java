package com.swiftshop.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.swiftshop.main.FunLibrary;

public class DeliverySlotPage extends FunLibrary {
	static String day = "";
	static String day_month = "";
	static String day_selector = "";
	static String day_selector2 = "";
	String date_0 = "";
	String slot;
	String HD_slot;
	String hd_slot_already_sel;
	String hd_slot_price;
	String hd_slot_time;
	String hd_slot_header;
	String hd_show_sold_out_link = "";
	String cc_show_sold_out_link = "";
	String cc_slot_list = "";
	String cc_slot = "";
	String cc_slot_already_sel = "";
	String cc_slot_price = "";
	String rd_slot_list = "";
	String rd_slot = "";
	String rd_slot_already_sel = "";
	String rd_slot_price = "";
	public static String hd_date = "";
	public static String hd_time = "";
	public static String cc_date = "";
	public static String cc_time = "";
	public static String rd_date = "";
	public static String rd_time = "";
	public static String extracted_price = "";
	public static String Day = "";
	boolean bagflag;
	boolean baglessflag;

	public DeliverySlotPage clickDSSAlertWindowCloseButton() {
		if (isElementPresent(OR_OR.getProperty("DSS_Alert_Window_Close_Button"), "DSS_Alert_Window_Close_Button")) {
			testLog.info(" Alert box is displayed on DSS page so closing the Alert Box");
			Click_Button_Xpath(OR_OR.getProperty("DSS_Alert_Window_Close_Button"), "DSS_Alert_Window_Close_Button");
		}
		return this;
	}

	public DeliverySlotPage clickEmergencyMessageCloseButton() {
		if (isElementPresent(OR_OR.getProperty("EmergencyMessage_close_button"), "EmergencyMessage_close_button")) {
			testLog.info("Emergency message is displayed on DSS page so Closing the Emergency message");
			Click_Button_Xpath(OR_OR.getProperty("EmergencyMessage_close_button"), "EmergencyMessage_close_button");
		}
		return this;
	}

	public HomePage ClickCancelAndKeepShopingLink() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link");
		ExplicitWait(OR_OR.getProperty("Home_SearchField_InsideText_Path"), "Home_SearchField_InsideText_Path", 30);
		wait(5000);
		return new HomePage();
	}

	public DeliverySlotPage selectUnattendedDelivery() {
		Click_Button_Xpath(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn"),
				"DSS_Customer_Choice_Window_Unattended_RadioBtn");
		return this;
	}

	public DeliverySlotPage selectSignedDelivery() {
		Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_delivery_Opt_Signed"), "DSS_Overlay_delivery_Opt_Signed");
		return this;
	}

	public void setDSSXpath() {
		if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"), "DSS_Airline_Date_Selector")) {
			day = OR_OR.getProperty("DSS_Day_Selector_AirlineV");
			day_month = OR_OR.getProperty("DSS_Day_Selector_AirlineV2");

			slot = OR_OR.getProperty("DSS_HD_Day_SlotList_dynamic");
			HD_slot = OR_OR.getProperty("DSS_HD_Slot_picker_dynamic");
			hd_slot_price = OR_OR.getProperty("DSS_HD_Day_SlotPrice_dynamic");
			hd_slot_time = OR_OR.getProperty("DSS_HD_Day_SlotTime");
			hd_slot_header = OR_OR.getProperty("DSS_HD_Day_SlotHeader");
			hd_show_sold_out_link = OR_OR.getProperty("DSS_HD_Show_Sold_Out_Slots_Link");
			cc_slot_list = OR_OR.getProperty("DSS_CC_SlotsList_dynamic");
			cc_slot = OR_OR.getProperty("DSS_CC_Slot_picker_dynamic");
			cc_slot_already_sel = OR_OR.getProperty("DSS_CC_Day_Slot_Already_Selected");
			cc_slot_price = OR_OR.getProperty("DSS_CC_SlotPrice_dynamic");
			cc_show_sold_out_link = OR_OR.getProperty("DSS_CC_Show_Sold_Out_Slots_Link");
		} else {

			day = OR_OR.getProperty("DSS_Day_Selector");

			rd_slot_list = OR_OR.getProperty("DSS_RD_Day_SlotsList");
			rd_slot = OR_OR.getProperty("DSS_RD_Day_SlotPicker_dynamic");
			rd_slot_already_sel = OR_OR.getProperty("DSS_CC_Day_Slot_Already_Selected");
			rd_slot_price = OR_OR.getProperty("DSS_RD_Day_Slot_Price");
		}
	}

	public int getCurrentDay() {
		int win_start;
		if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"), "DSS_Airline_Date_Selector")) {
			String Window_id = get_xpath_attribute_id(OR_OR.getProperty("DSS_ActiveWindow_Displayed"),
					"DSS_ActiveWindow_Displayed");
			win_start = Integer.valueOf(Window_id.substring((Window_id.lastIndexOf("-") + 1), Window_id.length()));
			testLog.info("window start" + win_start);

		} else {
			String Window_id = get_xpath_attribute_id(OR_OR.getProperty("DSS_ActiveWindow_Displayed_Base_Code"),
					"DSS_ActiveWindow_Displayed_Base_Code");
			win_start = Integer.valueOf(Window_id.replaceAll("[^0-9]", "").trim());
			testLog.info("window start" + win_start);
		}
		return win_start;
	}

	/*
	 * This function selects slot on the one day from the current selected day
	 */
	public DeliverySlotPage selectNextSlot() {
		return this;
	}

	public DeliverySlotPage clickNextButton() {
		isElementPresent(OR_OR.getProperty("DSS_Airline_Next7Days"), "DSS_Airline_Next7Days");
		Click_Button_Xpath(OR_OR.getProperty("DSS_Airline_Next7Days"), "DSS_Airline_Next7Days");
		ExplicitWait(OR_OR.getProperty("DSS_Airline_Prev7Days"), "DSS_Airline_Prev7Days", 2);
		return this;
	}

	public DeliverySlotPage clickPreviousButton() {
		isElementPresent(OR_OR.getProperty("DSS_Airline_Prev7Days"), "DSS_Airline_Prev7Days");
		Click_Button_Xpath(OR_OR.getProperty("DSS_Airline_Prev7Days"), "DSS_Airline_Prev7Days");
		ExplicitWait(OR_OR.getProperty("DSS_Airline_Next7Days"), "DSS_Airline_Next7Days", 2);
		return this;
	}

	public void clickDay(String dayno) {
		// if (isElementPresent(OR_OR.getProperty("DSS_Day_Selector_AirlineV"),
		// "DSS_Day_Selector_AirlineV")) {
		if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"), "DSS_Airline_Date_Selector")) {
			day_selector = day.replace("nvalue", dayno);
			day_selector2 = day_month.replace("nvalue", dayno);
		} else {
			day_selector = day.replace("nvalue", dayno);
		}
		driver.findElement(By.xpath(day_selector)).click();
	}

	/*
	 * public boolean isCorrectDaySelected(String day_type, String daystr) {
	 * boolean isCorrectDay = false; String DSS_Day;
	 * 
	 * if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"),
	 * "DSS_Airline_Date_Selector")) { if (daystr.equals("8") &&
	 * isElementVisible(OR_OR.getProperty("DSS_Airline_Next7Days"),
	 * "DSS_Airline_Next7Days")) {
	 * testLog.info("No slot found on current page. Click Next button");
	 * clickNextButton(); } DSS_Day = OR_OR.getProperty("DSS_Day"); DSS_Day =
	 * DSS_Day.replace("nvalue", daystr); Day = get_xpath_text(DSS_Day,
	 * "DSS_Day");
	 * 
	 * } else { if (daystr.equals("8") &&
	 * isElementVisible(OR_OR.getProperty("DSS_Next_7_Days_Link"),
	 * "DSS_Next_7_Days_Link")) {
	 * Click_Button_Xpath(OR_OR.getProperty("DSS_Next_7_Days_Link"),
	 * "DSS_Next_7_Days_Link"); wait(4000); } DSS_Day =
	 * OR_OR.getProperty("DSS_RD_Current_Day"); DSS_Day =
	 * DSS_Day.replace("nvalue", daystr); Day = get_xpath_text(DSS_Day,
	 * "DSS_Day"); }
	 * 
	 * day_type = day_type.replaceAll("\\W", ""); day_type =
	 * day_type.toUpperCase();
	 * 
	 * switch (day_type) { case "MIDDAY": if (Day.equalsIgnoreCase("Today")) {
	 * int dayno = Integer.parseInt(daystr); dayno = dayno + 2; daystr =
	 * Integer.toString(dayno); DSS_Day = OR_OR.getProperty("DSS_Day"); DSS_Day
	 * = DSS_Day.replace("nvalue", daystr); String Next2ndDay =
	 * get_xpath_text(DSS_Day, "DSS_Day");
	 * 
	 * if (Next2ndDay.equalsIgnoreCase("Thrusday") ||
	 * Next2ndDay.equalsIgnoreCase("Friday") ||
	 * Next2ndDay.equalsIgnoreCase("Saturday")) { isCorrectDay = true; } } else
	 * if (Day.equalsIgnoreCase("Tomorrow")) { int dayno =
	 * Integer.parseInt(daystr); dayno = dayno + 1; daystr =
	 * Integer.toString(dayno); DSS_Day = OR_OR.getProperty("DSS_Day"); DSS_Day
	 * = DSS_Day.replace("nvalue", daystr); String Next2ndDay =
	 * get_xpath_text(DSS_Day, "DSS_Day"); if
	 * (Next2ndDay.equalsIgnoreCase("WED") || Next2ndDay.equalsIgnoreCase("THU")
	 * || Next2ndDay.equalsIgnoreCase("FRI")) { isCorrectDay = true; } } else if
	 * (Day.equalsIgnoreCase("TUE") || Day.equalsIgnoreCase("WED") ||
	 * Day.equalsIgnoreCase("THU")) { isCorrectDay = true; } break; case
	 * "ANYDAY": isCorrectDay = true; break; case "NOTMIDDAY": if
	 * (Day.equalsIgnoreCase("Today")) { int dayno = Integer.parseInt(daystr);
	 * dayno = dayno + 2; daystr = Integer.toString(dayno); DSS_Day =
	 * OR_OR.getProperty("DSS_Day"); DSS_Day = DSS_Day.replace("nvalue",
	 * daystr); String Next2ndDay = get_xpath_text(DSS_Day, "DSS_Day");
	 * 
	 * if (!Next2ndDay.equalsIgnoreCase("THU") &&
	 * !Next2ndDay.equalsIgnoreCase("FRI") &&
	 * !Next2ndDay.equalsIgnoreCase("SAT")) { isCorrectDay = true; } } else if
	 * (Day.equalsIgnoreCase("Tomorrow")) { int dayno =
	 * Integer.parseInt(daystr); dayno = dayno + 1; daystr =
	 * Integer.toString(dayno); DSS_Day = OR_OR.getProperty("DSS_Day"); DSS_Day
	 * = DSS_Day.replace("nvalue", daystr); String Next2ndDay =
	 * get_xpath_text(DSS_Day, "DSS_Day"); if
	 * (!Next2ndDay.equalsIgnoreCase("WED") &&
	 * !Next2ndDay.equalsIgnoreCase("THU") &&
	 * !Next2ndDay.equalsIgnoreCase("FRI")) { isCorrectDay = true; } } else if
	 * (!Day.equalsIgnoreCase("TUE") && !Day.equalsIgnoreCase("WED") &&
	 * !Day.equalsIgnoreCase("THU")) { isCorrectDay = true; } break; default:
	 * testLog.
	 * error("Please select a valid value. Valid Values are: Mid Day, ANYDAY, Not Mid Day"
	 * ); assertCheck("isCorrectDaySelected",
	 * "Please select a valid value. Valid Values are: Mid Day, ANYDAY, Not Mid Day"
	 * ); } testLog.info("Day is:" + Day);
	 * 
	 * return isCorrectDay; }
	 */

	/* bag_option values: 1. Bagless 2. Bagged */
	public DeliverySlotPage selectBagOption(boolean bag_option) {

		if (bag_option) {

			if (isElementVisible(OR_OR.getProperty("DSS_Overlay_Bagged_Option_RButton"),
					"DSS_Overlay_Bagged_Option_RButton")) {
				/*
				 * click_Element_By_ID(OR_OR.getProperty(
				 * "DSS_Overlay_Bagged_Option_RButton_id"),
				 * "DSS_Overlay_Bagged_Option_RButton_id");
				 */
				Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Bagged_Option_RButton_id"),
						"DSS_Overlay_Bagged_Option_RButton_id");
			} else if (isElementVisible(OR_OR.getProperty("DSS_Overlay_Bagless_Option_RButton"),
					"DSS_Overlay_Bagless_Option_RButton")) {
				testLog.error("Bagged option is not present. Only Bagless option is available for this cc point");
				assertCheck("selectCCBagOption", "Bagged option is not available for this cc point",
						"DSS_Overlay_Bagged_Option_RButton");
			} else {
				testLog.error("No Bag option is visible");
				assertCheck("selectCCBagOption", "No Bag option is visible", "DSS_Overlay_Bagless_Option_RButton");
			}
		} else {
			if (isElementVisible(OR_OR.getProperty("DSS_Overlay_Bagless_Option_RButton"),
					"DSS_Overlay_Bagless_Option_RButton")) {
				/*
				 * click_Element_By_ID(OR_OR.getProperty(
				 * "DSS_Overlay_Bagless_Option_RButton_id"),
				 * "DSS_Overlay_Bagless_Option_RButton_id");
				 */
				Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Bagless_Option_RButton_id"),
						"DSS_Overlay_Bagless_Option_RButton_id");
			} else {
				testLog.error("Bagless option is not visible");
				assertCheck("selectCCBagOption", "No Bag option is visible", "DSS_Overlay_Bagless_Option_RButton");
			}
		}
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "Continue");
		return this;
	}

	/*
	 * This function return true if slot date, day and time from DSS page
	 * matches in superbar else false
	 */
	public boolean isSlotSelectedSuccessfully(String slot_type, String slot_date, String slot_time) {
		boolean bool = true;
		String sb_actual_slottext = "";
		ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time", 10);
		String sb_time_date = driver.findElement(By.xpath(OR_OR.getProperty("Superbar_Slot_Information"))).getText();
		String date = slot_date.substring(0, 1);
		if (date.equals("0")) {
			slot_date = slot_date.substring(1, slot_date.length());
		}
		String slot_date_time = slot_time.replaceAll("\\n+", "") + " " + slot_date;
		testLog.info("Super bar date time: " + sb_time_date);
		testLog.info("DSS date time: " + slot_date_time);

		if (sb_time_date.replaceAll("\\s+", "").equalsIgnoreCase(slot_date_time.replaceAll("\\s+", ""))) {
			testLog.info("The selected time slot in DSS is displayed correctly in the Superbar as: " + sb_time_date);
		} else {
			testLog.warn("The selected time slot in DSS is: " + slot_date + " is not displayed correctly in super bar: "
					+ sb_time_date);
			bool = false;
		}

		Click_Button_Xpath(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time");
		wait(5000);
		String sb_slotconfirmation_text = get_xpath_text(OR_OR.getProperty("HD_Superbar_Slotselected_Text"),
				"HD_Superbar_Slotselected_Text");

		String date_1 = date_0.replaceAll("[^A-Z,a-z]", "");
		testLog.info("month: " + date_1);
		String month_text = getFullMonthName(date_1);
		testLog.info("Month full name received is: " + month_text);
		String dateMonth = date_0.replaceAll("[A-Z,a-z]", "") + month_text;
		String date_2 = slot_date.replaceAll(date_1, month_text);
		String dayofweeek = senddayofweeek(date_2);
		if (slot_type.equals("CC")) {
			sb_actual_slottext = "You can collect your shopping ".concat(dayofweeek).concat(" ").concat(dateMonth)
					.concat(" ").concat(slot_time);
		} else if (slot_type.equals("RD")) {
			sb_actual_slottext = "Your delivery is booked for ".concat(dayofweeek).concat(" ").concat(dateMonth)
					.concat(" ").concat(slot_time);
		} else if (slot_type.equals("HD")) {
			sb_actual_slottext = "Your delivery is booked for ".concat(dayofweeek).concat(" ").concat(dateMonth)
					.concat(" ").concat(slot_time);

		}

		testLog.info("Actual CC slot text: " + sb_actual_slottext);
		testLog.info("Superbar CC slot text: " + sb_slotconfirmation_text);

		if (sb_slotconfirmation_text.replaceAll(" ", "").trim()
				.equalsIgnoreCase(sb_actual_slottext.replaceAll(" ", "").trim())) {
			testLog.info("The selected time slot in DSS is displayed correctly after expanding the Superbar as: "
					+ sb_actual_slottext);
		} else {
			testLog.warn("The selected time slot in DSS is: " + slot_time
					+ " is not displayed correctly after expanding the super bar: " + sb_actual_slottext);
			bool = false;
		}
		// verify_xpath_text(OR_OR.getProperty("CC_Selected_Choose_A_Different_Time_Link"),
		// "Choose a different time");
		// Click_Button_Xpath(OR_OR.getProperty("Choose_A_Delivery_Time"),
		// "Choose_A_Delivery_Time");

		return bool;
	}

	public DeliverySlotPage selectNextDay() {
		clickDay(Integer.toString(getCurrentDay() + 1));
		return this;
	}

	/*
	 * This function selects HD slot. day_type: "Mid Day", "Any Day",
	 * "Not Mid Day", "skipcurrentselectedday" window_type : "signed",
	 * "customerchoiceunattended", "unattendedonly"
	 */

	public void HDSlotSelector(String window_type, boolean bag, boolean remember_bagging_preference) {
		wait(12000);
		boolean stflag = false;
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			// loop to iterate through days
			outerloop: for (int i = 2; i <= 7; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);

				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}
				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);

				// Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in the selected
					// section
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					// String partnerDel =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]/button//span[@data-visual-display-text='PARTNERDELIVERY']";
					// String slotStatus =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						String partnerDel = OR_OR.getProperty("DSS_Partner_Delivery");
						partnerDel = partnerDel.replace("nvalue", daystr);
						partnerDel = partnerDel.replace("kvalue", kstr1);
						partnerDel = partnerDel.replace("mvalue", kstr);

						String slotStatus = OR_OR.getProperty("DSS_Slot_Status");
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						String status = driver.findElement(By.xpath(slotStatus)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						Boolean isPartnerDelevery = isElementPresent(partnerDel, "partnerDel");
						if (isPartnerDelevery) {
							testLog.info("This is a Partner Delivery slot. Skiping this slot");
							continue;
						}
						// if ("Expired".equals(extracted_price) || "Sold
						// out".equals(extracted_price)
						// || "Closed".equals(extracted_price) ||
						// deliveryViaYello
						// || yellowindowAttr.toLowerCase().contains("yello")) {
						// testLog.info(
						// "This is an expired/ Sold out slot/Delivery Via Yello
						// slot, checking next slot");
						// continue;
						// }

						if ("Expired".equals(status) || "Sold out".equals(status) || "Closed".equals(status)
								|| deliveryViaYello || yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);
						//
						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						DelType = DelType.replaceAll("[^a-zA-Z]", "");
						window_type = window_type.toLowerCase();
						window_type = window_type.replaceAll("\\s+", "");
						switch (window_type) {
						case "signed":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(8000);
								selectSignedDelivery();
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag); if
								 * (remember_bagging_preference) {
								 * 
								 * if(isElementVisible(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox"))
								 * { Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * }else { testLog.
								 * error("Remember bagging preference checkbox is not visible"
								 * ); assertCheck(
								 * "HDSlotSelector","Remember bagging preference checkbox is not visible"
								 * ,"DSS_Overlay_Remember_Preference_Checkbox");
								 * }
								 * 
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "signaturerequired":
							// if (DelType.equalsIgnoreCase("SIGNATURE
							// REQUIRED"))
							if (DelType.equalsIgnoreCase("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag);
								 * 
								 * if (remember_bagging_preference) {
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								selectUnattendedDelivery();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							// if (DelType.equalsIgnoreCase("UNATTENDED ONLY"))
							// /* Changed in DSS page UI */
							if (DelType.equalsIgnoreCase("UNATTENDED")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code as per new functional
								// change at overlay popup window
								/*
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 * ExplicitWait(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button", 10);
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button");
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 */
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}
				}
			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(5000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}

			wait(5000);

			// if (isSlotSelectedSuccessfully("HD", hd_date, hd_time)) {
			// testLog.info("Slot selected successfully");
			// } else {
			// testLog.error("Slot is not selected properly");
			// assertCheck("HDSlotSelector", "Slot is not selected properly");
			// }

		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

	/*
	 * This function selects CC slot and validates it day_type: "Mid Day",
	 * "Any Day", Not Mid Day", bag_option: "bagged", "bagless"
	 */
	public void CCSlotSelector(boolean bag_option, boolean remember_bagging_preference, boolean next_Day) {
		try {
			boolean stflag = true;
			boolean changeslot = false;
			int win_start = 0;
			wait(10000);

			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();
			win_start = getCurrentDay();

			if (next_Day == true) {
				win_start++;
			}

			outerloop: for (int i = 2; i <= 7; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = cc_slot_list.replace("nvalue", daystr);
				String cc_show_sold_out_link1 = cc_show_sold_out_link.replace("nvalue", daystr);
				// --Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				for (int k = 1; k <= 3; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String cc_show_sold_out_link_Final = cc_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final")) {
						Click_Button_Xpath(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}

					By getChanges = By.xpath(slot_selector_FinalXpath);
					testLog.info("getchanges:" + getChanges);
					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of CC slots for day:" + day_selector + " is " + slot_elements.size());

					if (slot_elements.size() == 0) {
						continue;
					}

					String cc_slot_selector = "";
					String cc_slot_price_selector = "";
					String cc_slot_already_selected = "";
					// String slotStatus =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";
					for (int j = 1; j <= slot_elements.size(); j++) {
						String slotStatus = "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time'] | //div[@id='daynvalue-time-slots']//div[contains(@class,'cc-n-rd-slots')]//div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//*[contains(@class,'times-slot')][mvalue]//span[contains(@class,'times-slot-time')]";
						String kstr = Integer.toString(j);
						cc_slot_already_selected = cc_slot_already_sel.replaceAll("nvalue", daystr);

						cc_slot_selector = cc_slot.replace("nvalue", daystr);
						cc_slot_selector = cc_slot_selector.replace("kvalue", kstr1);
						cc_slot_selector = cc_slot_selector.replace("mvalue", kstr);
						cc_slot_price_selector = cc_slot_price.replace("nvalue", daystr);
						cc_slot_price_selector = cc_slot_price_selector.replace("kvalue", kstr1);
						cc_slot_price_selector = cc_slot_price_selector.replace("mvalue", kstr);

						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);
						String getslotStatus = driver.findElement(By.xpath(slotStatus)).getText().trim();

						extracted_price = driver.findElement(By.xpath(cc_slot_price_selector)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);
						// if ("Expired".equals(extracted_price) || "Sold
						// out".equals(extracted_price)
						// || "Closed".equals(extracted_price)) {
						// testLog.info("This is an expired/ Sold out slot,
						// checking next slot");
						// continue;
						// }

						if ("Expired".equals(getslotStatus) || "Sold out".equals(getslotStatus)
								|| "Closed".equals(getslotStatus)) {
							testLog.info("This is an expired/ Sold out slot, checking next slot");
							continue;
						}

						boolean boolval = isElementPresent(cc_slot_already_selected, "hd_slot_already_selected");
						if (boolval == true) {

							testLog.warn("Slot is already selected");
							break;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						cc_date = date_0 + "" + year;
						cc_time = driver.findElement(By.xpath(cc_slot_selector)).getText();
						cc_time = cc_time.substring(0, 18);
						testLog.info("CC Date extracted is: " + cc_date);
						testLog.info("CC Time extracted from slot is: " + cc_time);

						driver.findElement(By.xpath(cc_slot_selector)).click();
						wait(8000);
						ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button",
								25);
						verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotRes_Heading"),
								OR_OR.getProperty("DSS_Overlay_SlotRes_Heading_Collection_Text"));

						testLog.info("Slot selected successfully in DSS");
						testLog.info("Selecting Bag option");
						if (changeslot) {
							testLog.info("Bagging option will not display after changing the CC slot");
						} else {
							selectBagOption(bag_option);
						}
						if (remember_bagging_preference) {
							if (isElementVisible(OR_OR.getProperty("DSS_Overlay_Remember_Preference_Checkbox"),
									"DSS_Overlay_Remember_Preference_Checkbox")) {
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Remember_Preference_Checkbox"),
										"DSS_Overlay_Remember_Preference_Checkbox");
							} else {
								testLog.error("Remember bagging preference checkbox is not visible");
								assertCheck("HDSlotSelector", "Remember bagging preference checkbox is not visible",
										"DSS_Overlay_Remember_Preference_Checkbox");
							}

							if (bag_option == true)
								bagflag = true;
							else
								baglessflag = true;
						}
						Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
								"DSS_Overlay_Continue_Button");
						wait(12000);
						// if (isSlotSelectedSuccessfully("CC", cc_date,
						// cc_time)) {
						// testLog.info("Slot selected successfully");
						// stflag = true;
						// } else {
						// stflag = false;
						// }
						break outerloop;
					}
				}
			}
			if (stflag == false) {
				testLog.error("DSS slot not selected properly");
				assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
			}

		} catch (Exception e) {
			testLog.error("Failed to select the DSS CC slot");
			assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
		}
	}

	public void RDSlotSelector(String day_type) {

		clickDSSAlertWindowCloseButton();
		clickEmergencyMessageCloseButton();
		setDSSXpath();
		// verify_xpath_text(OR_OR.getProperty("DSS_Header"),
		// OR_OR.getProperty("DSS_Header_Text"));
		// verify_xpath_text(OR_OR.getProperty("DSS_Second_Header"),
		// OR_OR.getProperty("DSS_Second_Header_text"));

		int win_start = getCurrentDay();
		if (day_type.equals("skipcurrentselectedday")) {
			++win_start;
			day_type = "ANYDAY";
		}
		for (int i = win_start; i <= 7; i++) {
			String daystr = Integer.toString(i);
			clickDay(daystr);
			String DSS_RD_Slot_Not_Available = OR_OR.getProperty("DSS_RD_Slot_Not_Available");
			if (isElementVisible(DSS_RD_Slot_Not_Available, "DSS_Overlay_Bagless_Option_RButton")) {
				continue;
			}

			String slot_selector = rd_slot_list.replace("nvalue", daystr);
			String slot_price_list = rd_slot_price.replace("nvalue", daystr);

			// ==Adding to get Day
			String DSS_Day = "//a[contains(@id ,'daynvalue')]//span[@class='slot-day']"; // OR_OR.getProperty("DSS_Day");
			DSS_Day = DSS_Day.replace("nvalue", daystr);
			System.out.println("====> " + get_xpath_text(DSS_Day, "DSS_Day").trim());
			testLog.info("Selected slot day is : " + get_xpath_text(DSS_Day, "DSS_Day").trim());
			Day = getDayRDSlots(get_xpath_text(DSS_Day, "DSS_Day").trim());
			List<WebElement> slot_list = driver.findElements(By.xpath(slot_selector));
			testLog.info("count of RD slots for day:" + day_selector + " is " + slot_list.size());
			List<WebElement> slot_Price_list = driver.findElements(By.xpath(slot_price_list));

			if (slot_list.size() == 0) {
				continue;
			}

			String rd_slot_already_selected = rd_slot_already_sel.replaceAll("nvalue", daystr);

			Iterator<WebElement> ItrSlot = slot_list.iterator();
			Iterator<WebElement> ItrPrice = slot_Price_list.iterator();
			while (ItrSlot.hasNext()) {
				extracted_price = ItrPrice.next().getText().trim();
				testLog.info("Extracted price from time slot is: " + extracted_price);
				WebElement slot = ItrSlot.next();

				if ("Expired".equals(extracted_price) || "Sold out".equals(extracted_price)) {
					testLog.info("This is an expired/ Sold out slot, checking next slot");
					continue;
				}
				boolean boolval = isElementPresent(rd_slot_already_selected, "cc_slot_already_selected");
				if (boolval == true) {
					break;
				}

				date_0 = driver.findElement(By.xpath(day_selector)).getText();
				int year = Calendar.getInstance().get(Calendar.YEAR);
				rd_date = date_0.concat(" " + year);
				testLog.info("rd Date extracted is: " + rd_date);

				rd_time = slot.getText();
				rd_time = rd_time.substring(0, 18);
				testLog.info("rd_time is: " + rd_time);

				slot.click();
				testLog.info("RD Slot selected successfully in DSS");
				wait(5000);
				ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button", 25);
				Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button");
				wait(5000);
				// ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"),
				// "Choose_A_Delivery_Time", 10);

				// if (isSlotSelectedSuccessfully("RD", rd_date, rd_time)) {
				// testLog.info("Slot selected successfully");
				// break;
				// } else {
				// assertCheck("RDSlotSelector", "Slot is not selected
				// properly");
				// }
				break;
			}
			break;
		}
	}

	public DeliverySlotPage clickHDAddressDSS(String HD_address_name) {
		By getChanges = By.xpath(OR_OR.getProperty("DSS_Total_HD_Addresses"));
		java.util.List<WebElement> slot_elements = driver.findElements(getChanges);
		int total_addresses_count = slot_elements.size();
		testLog.info("Total HD addresses in the list are: " + total_addresses_count);

		for (int j = 1; j <= total_addresses_count; j++) {
			String jval = Integer.toString(j);
			String hd_Addr = OR_OR.getProperty("DSS_HD_Address_Nvalue");
			hd_Addr = hd_Addr.replaceAll("nvalue", jval);
			if (driver.findElement(By.xpath(hd_Addr)).getText().toLowerCase().contains(HD_address_name.toLowerCase())) {
				Click_Button_Xpath(hd_Addr, "DSS_HD_Address_Nvalue");
				break;
			}
		}
		if (isElementPresent(OR_OR.getProperty("Click_Collect_Form_Header"), "Click_Collect_Form_Header")) {
			testLog.error(HD_address_name + " is not found");
			assertCheck("clickHDAddress", HD_address_name + " is not found", "");
		}
		if (isElementPresent("//*[@class='msg-warning']", "poup")) {
			testLog.error("Popup displayed: Sorry, we are having trouble completing the request. Please try again.");
			assertCheck("clickHDAddress", "Error poup displayed",
					"we are having trouble completing the request. Please try again.");
		}
		wait(5000);
		return this;
	}

	public DeliverySlotPage clickChooseDeliveryAddressLinkDSS() {
		if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"), "DSS_Airline_Date_Selector")) {
			ExplicitWait(OR_OR.getProperty("CC_Choose_A_Delivery_Address_Link"), "CC_Choose_A_Delivery_Address_Link",
					10);
			Click_Button_Xpath(OR_OR.getProperty("CC_Choose_A_Delivery_Address_Link"),
					"CC_Choose_A_Delivery_Address_Link");
			verify_xpath_text(OR_OR.getProperty("DSS_HD_Address_List"),
					"Where would you like your shopping delivered?");
		} else {
			testLog.error("DSS airline view is not loaded properly");

			assertCheck("clickChooseDeliveryAddressLinkDSS", "DSS airline view is not loaded properly");
		}
		return this;
	}

	public DeliverySlotPage clickChooseALocationLinkDSS() {
		ExplicitWait(OR_OR.getProperty("HD_Choose_A_Location_Link"), "HD_Choose_A_Location_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("HD_Choose_A_Location_Link"), "HD_Choose_A_Location_Link");
		return this;
	}

	public DeliverySlotPage clickChooseCollectionTimeButtonDSS() {
		wait(2000);
		Click_Button_Xpath(OR_OR.getProperty("CC_MapPage_LocationPage_Choosetime_button"),
				"CC_MapPage_LocationPage_Choosetime_button");
		wait(5000);
		return this;
	}

	public DeliverySlotPage validateDSSPagePresent() {
		wait(3000);
		if (isElementPresent(OR_OR.getProperty("DSS_Airline_Date_Selector"), "DSS_Airline_Date_Selector")) {
			testLog.info("Great!! Successfully redirect at DSS page.");
		} else {
			testLog.error("Opps!! there is some issue to redirect at DSS page");
			assertCheck("validateDSSPagePresent", "Opps!! we are not able to redirect at DSS page");
		}
		return this;
	}

	public DeliverySlotPage clickFirstClickAndCollectSuburbDSS() {
		if (isElementNotPresent(OR_OR.getProperty("CC_MapPage_First_Suggested_Suburb_button"),
				"CC_MapPage_First_Suggested_Suburb_button")) {
			Click_Button_Xpath(OR_OR.getProperty("CC_MapPage_Suburb_input"), "CC_MapPage_Suburb_input");
			Clear_Text(OR_OR.getProperty("CC_MapPage_Suburb_input"), "CC_MapPage_Suburb_input");
			Sendkey_xpath(OR_OR.getProperty("CC_MapPage_Suburb_input"), "Bayview 0820", "CC_MapPage_Suburb_input");
			wait(5000);
			Click_Button_Xpath(OR_OR.getProperty("CC_MapPage_First_Suggested_Suburb_input"),
					"CC_MapPage_First_Suggested_Suburb_input");
		}

		Click_Button_Xpath(OR_OR.getProperty("CC_MapPage_First_Suggested_Suburb_button"),
				"CC_MapPage_First_Suggested_Suburb_button");
		return this;
	}

	public DeliverySlotPage clickTryAnotherAddressLinkDSS() {
		ExplicitWait(OR_OR.getProperty("DSS_Try_Another_Address_Link"), "DSS_Try_Another_Address_Link", 4);
		Click_Button_Xpath(OR_OR.getProperty("DSS_Try_Another_Address_Link"), "DSS_Try_Another_Address_Link");
		wait(2000);
		verify_xpath_text(OR_OR.getProperty("DSS_HD_Address_List"), "Where would you like your shopping delivered?");
		return this;
	}

	public DeliverySlotPage validateAddress_DSS(String address) {

		switch (address) {
		case "HD":
			ExplicitWait(OR_OR.getProperty("HD_DSS_Main_Header"), "HD_DSS_Main_Header", 10);
			if (isElementPresent(OR_OR.getProperty("HD_DSS_Content_Header"), "HD_DSS_Content_Header")) {
				String header = get_xpath_text(OR_OR.getProperty("HD_DSS_Content_Header"), "HD_DSS_Content_Header");
				testLog.info("HD address is appearing at DSS page : => " + header);
				String streetAddress = OR_OR.getProperty("First_HD_Street_Address");
				String suburb = OR_OR.getProperty("First_HD_Suburb");
				String state = OR_OR.getProperty("First_HD_State");
				String postCode = OR_OR.getProperty("First_HD_Postcode");

				if (header.contains(streetAddress)) {
					testLog.info("HD DSS page contains: " + streetAddress);
				} else {
					testLog.error("HD DSS page NOT contains: " + streetAddress);
					assertCheck("validateAddress_DSS", "Street address is not showing at HD DSS page");
				}
				if (header.contains(suburb)) {
					testLog.info("HD DSS page contains: " + suburb);
				} else {
					testLog.error("HD DSS page NOT contains: " + suburb);
					assertCheck("validateAddress_DSS", "Suburb is not showing at HD DSS page");
				}
				if (header.contains(state)) {
					testLog.info("HD DSS page contains: " + state);
				} else {
					testLog.error("HD DSS page NOT contains: " + state);
					assertCheck("validateAddress_DSS", "State is not showing at HD DSS page");
				}
				if (header.contains(postCode)) {
					testLog.info("HD DSS page contains: " + postCode);
				} else {
					testLog.error("HD DSS page NOT contains: " + postCode);
					assertCheck("validateAddress_DSS", "Postcode is not showing at HD DSS page");
				}
			} else {
				testLog.error("HD address NOT present at DSS page");
				assertCheck("validateAddress_DSS", "HD address NOT present at DSS page");
			}
			break;

		case "RD":
			// TODO: Implement if required in future
		}
		return this;
	}

	public DeliverySlotPage validateDSSTitle() {
		ExplicitWait(OR_OR.getProperty("RD_DSS_Main_Content_Header_Title"), "RD_DSS_Main_Content_Header_Title", 10);
		verify_xpath_text(OR_OR.getProperty("RD_DSS_Main_Content_Header_Title"),
				OR_OR.getProperty("RD_DSS_Main_Content_Header_Title_Text"));
		return this;
	}

	public DeliverySlotPage validateSavedHDAddress(String nickname) {
		wait(5000);
		String savedAddress = get_xpath_text(OR_OR.getProperty("DSS_Page_HD_Address_Heading"),
				"DSS_Page_HD_Address_Heading");
		boolean Addname = savedAddress.contains(nickname);
		boolean suburb1 = savedAddress.contains(suburb);
		boolean postcode1 = savedAddress.contains(postcode);
		if (Addname && suburb1 && postcode1) {
			testLog.info("Saved address is correct");
		} else {
			testLog.error("Saved address is not correct");
			assertCheck("validateSavedHDAddress", "Saved address is not correct");
		}
		return this;
	}

	public DeliverySlotPage ClickChooseLocation() {
		// Click_Button_Xpath(OR_OR.getProperty("Choose_A_Location_Link"),
		// "Choose_A_Location_Link");
		ExplicitWait(OR_OR.getProperty("CC_Choose_A_Delivery_Address_Link"), "CC_Choose_A_Delivery_Address_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("CC_Choose_A_Delivery_Address_Link"), "CC_Choose_A_Delivery_Address_Link");
		ExplicitWait(OR_OR.getProperty("Home_SearchField_InsideText_Path"), "Home_SearchField_InsideText_Path", 30);
		return this;
	}

	public DeliverySlotPage clickClickAndCollectLocation() {
		ExplicitWait(OR_OR.getProperty("Click_And_Collect_Location_First"), "Click_And_Collect_Location_First", 15);
		Click_Button_Xpath(OR_OR.getProperty("Click_And_Collect_Location_First"), "Click_And_Collect_Location_First");
		return this;
	}

	public DeliverySlotPage clickChooseCollectionTime() {
		wait(3000);
		scrollTo_Xpath(OR_OR.getProperty("Choose_A_Collection_Time_Btn"), "Choose_A_Collection_Time_Btn", 40);
		Click_Button_Xpath(OR_OR.getProperty("Choose_A_Collection_Time_Btn"), "Choose_A_Collection_Time_Btn");
		wait(10000);
		/*
		 * ExplicitWait(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"),
		 * "Cancel_And_Keep_Shopping_Link", 30);
		 */
		return this;
	}

	public DeliverySlotPage validateDSSOverlayPopup() {
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SubHeading"),
				OR_OR.getProperty("DSS_Overlay_SubHeading_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SignedDelivery_Label"),
				OR_OR.getProperty("DSS_Overlay_SignedDelivery_Label_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SignedDelivery_Desc"),
				OR_OR.getProperty("DSS_Overlay_SignedDelivery_Desc_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_UnattendedDelivery_Label"),
				OR_OR.getProperty("DSS_Overlay_UnattendedDelivery_Label_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_UnattendedDelivery_Desc"),
				OR_OR.getProperty("DSS_Overlay_UnattendedDelivery_Desc_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Baggage_Heading"),
				OR_OR.getProperty("DSS_Overlay_Baggage_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Baggage_Sub_Heading"),
				OR_OR.getProperty("DSS_Overlay_Baggage_Sub_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bagless_Label"),
				OR_OR.getProperty("DSS_Overlay_Bagless_Label_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bagless_Sub_Heading"),
				OR_OR.getProperty("DSS_Overlay_Bagless_Sub_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bagged_Label"),
				OR_OR.getProperty("DSS_Overlay_Bagged_Label_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bagged_Sub_Heading"),
				OR_OR.getProperty("DSS_Overlay_Bagged_Sub_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Remember_Bagging_Lable"),
				OR_OR.getProperty("DSS_Overlay_Remember_Bagging_Text"));
		if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Signed_Delivery_Selected"),
				"DSS_Overlay_Signed_Delivery_Selected")) {
			testLog.info("Signed Delivery is selected");
		}
		if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Unattended_Delivery_Selected"),
				"DSS_Overlay_Unattended_Delivery_Selected")) {
			testLog.info("Unattended Delivery is selected");
		}
		if (isElementPresent(OR_OR.getProperty("DSS_Overlay_NoBag_Selected"), "DSS_Overlay_NoBag_Selected")) {
			testLog.info("No Bag is selected");
		}
		if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Bag_Selected"), "DSS_Overlay_Bag_Selected")) {
			testLog.info("Bag my groceries is selected");
		}
		return this;
	}

	public DeliverySlotPage clickAlreadySelectedWindowSlot() {
		int day = getCurrentDay();
		String currentday = Integer.toString(day);
		String currentSelectedSlot = OR_OR.getProperty("DSS_HD_Day_Slot_Already_Selected");
		currentSelectedSlot = currentSelectedSlot.replace("nvalue", currentday);

		Click_Button_Xpath(currentSelectedSlot, "currentSelectedSlot");
		return this;
	}

	public DeliverySlotPage clickDSSSlotPopupContinueBtn() {
		Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button");
		wait(15000);
		return this;
	}

	public DeliverySlotPage validateDeliveryRestrictionInDSSPage(String window_type, String restricted_item) {

		switch (window_type) {
		case "CustomerChoiceUnattended":
			wait(4000);
			// check unattended radio button is disabled
			if (!(driver.findElement(By.xpath(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn")))
					.isEnabled())) {
				testLog.info("unattended option is disabled as the cart contains restricted item");
			} else {
				testLog.error("Unattended option should not be enabled for the restrited items");
				assertCheck("validateDeliveryRestrictionInDSSPage",
						"Unattended option should not be enabled for the restrited items");
			}

			verify_xpath_text(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RestrictedItem_Warning"),
					OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RestrictedItem_Warning_Text"));

			break;
		case "UnattendedOnly":

			ExplicitWait(OR_OR.getProperty("DeliveryRestriction_overlay_Text"), "DeliveryRestriction_overlay_Text", 10);
			verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_overlay_Text"), "Delivery Restriction");
			verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button"),
					OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button_Text"));
			verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_Keep_DeliveryTime_Button"),
					OR_OR.getProperty("DeliveryRestriction_Keep_DeliveryTime_Button_Text"));

			String product_name = get_xpath_text(OR_OR.getProperty("DeliveryRestriction_overlay_ProductName1"),
					"DeliveryRestriction_overlay_ProductName1");
			if (product_name.contains(restricted_item)) {
				testLog.info("Restricted item in Delivery Restriction s properly displayed");
			} else {
				testLog.error("Restricted item is not displayed in Delivery restriction popup window");
				assertCheck("validateDeliveryRestrictionInDSSPage",
						"Restricted item is not displayed in Delivery restriction popup window");
			}

			break;
		}

		return this;
	}

	public DeliverySlotPage validateBagOptionsAtDSSPopup() {

		boolean noBagOptionPresent = isElementPresent(OR_OR.getProperty("DSS_Overlay_NoBag_Option"),
				"DSS_Overlay_NoBag_Option");
		boolean noBagOptionEnable = isElementEnable(OR_OR.getProperty("DSS_Overlay_NoBag_Option"),
				"DSS_Overlay_NoBag_Option");
		boolean bagOptionPresent = isElementPresent(OR_OR.getProperty("DSS_Overlay_Baged_Option"),
				"DSS_Overlay_Baged_Option");
		boolean bagOptionEnable = isElementEnable(OR_OR.getProperty("DSS_Overlay_Baged_Option"),
				"DSS_Overlay_Baged_Option");

		if (noBagOptionPresent == true && noBagOptionEnable == true) {
			testLog.info("Great!! 'No Bags' option is present and enable at DSS popup window");
		} else {
			testLog.error("Opps!! Something went wrong, 'No Bags' option is not present or enable at DSS popup window");
			assertCheck("validateBagOptionsAtDSSPopup",
					"Opps!! Something went wrong, 'No Bags' option is not present or enable at DSS popup window");
		}
		if (bagOptionPresent == true && bagOptionEnable == true) {
			testLog.info("Great!! 'Bag Groceries' option is present and enable at DSS popup window");
		} else {
			testLog.error(
					"Opps!! Something went wrong, 'Bag Groceries' option is not present or enable at DSS popup window");
			assertCheck("validateBagOptionsAtDSSPopup",
					"Opps!! Something went wrong, 'Bag Groceries' option is not present or enable at DSS popup window");
		}
		return this;
	}

	public void validateDSSPopupWindow(String day_type, String window_type, boolean bag) {
		wait(4000);
		boolean stflag = false;
		String hd_date = "", hd_time = "";

		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			int win_start = getCurrentDay();
			// loop to iterate through days
			outerloop: for (int i = 2; i <= 14; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in (morning, noon
					// and night slot
					// sections)
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						// --New Added Code To Get the slot status
						String slotStatus = "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";

						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						String currentSlotStatus = driver.findElement(By.xpath(slotStatus)).getText().trim();

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);
						if ("Expired".equals(currentSlotStatus) || "Sold out".equals(currentSlotStatus)
								|| "Closed".equals(currentSlotStatus) || deliveryViaYello
								|| yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot//Delivery Via Yello slot, checking next slot");
							continue;
						}

						// --Commenting the old code
						/*
						 * if ("Expired".equals(extracted_price) ||
						 * "Sold out".equals(extracted_price) ||
						 * "Closed".equals(extracted_price) || deliveryViaYello
						 * || yellowindowAttr.toLowerCase().contains("yello")) {
						 * testLog.info(
						 * "This is an expired/ Sold out slot//Delivery Via Yello slot, checking next slot"
						 * ); continue; }
						 */

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);

						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						window_type = window_type.toLowerCase();
						switch (window_type) {
						case "signed":
							if (DelType.equals("") || DelType.contains("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								// Commented below method due to noBagOption
								// functional change
								validateBagOptionsAtDSSPopup();

								if (DelType.equals("")) {
									Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_delivery_Opt_Signed"),
											"DSS_Overlay_delivery_Opt_Signed");
								}
								// Commented below methods due to noBagOption
								// functional change
								// selectBagOption(bag);
								// validateDSSOverlayPopup();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time", 10);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equals("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								validateBagOptions_CustomerChoice();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn"),
										"DSS_Customer_Choice_Window_Unattended_RadioBtn");
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							if (DelType.contains("Unattended")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(3000);
								// Unattended functionality got changed, below
								// code could be use in future
								/*
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 */
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								validateBaggedOption_UnattededWindow();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}

				}

			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(4000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

	public DeliverySlotPage clickDSSDeliveryRestrictionKeepItemButton() {
		Click_Button_Xpath(OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button"),
				"DeliveryRestriction_Keep_Items_Button");
		return this;
	}

	public DeliverySlotPage validateBaggedOption_UnattededWindow() {
		verify_xpath_text(OR_OR.getProperty("DSS_Unattended_Window_Bagged_Option"),
				OR_OR.getProperty("DSS_Unattended_Window_Bagged_Option_Msg"));
		return this;
	}

	public DeliverySlotPage validateBagOptions_CustomerChoice() {

		boolean bagOptionPresent = isElementPresent(OR_OR.getProperty("DSS_Overlay_Bagged_Option_RButton"),
				"DSS_Overlay_Bagged_Option_RButton");
		boolean bagOptionEnable = isElementEnable(OR_OR.getProperty("DSS_Overlay_Bagged_Option_RButton"),
				"DSS_Overlay_Bagged_Option_RButton");
		if (bagOptionPresent == true && bagOptionEnable == true) {
			testLog.info("Great!! 'Bag Groceries' option is present and enable at DSS popup window");
		} else {
			testLog.error(
					"Opps!! Something went wrong, 'Bag Groceries' option is not present or enable at DSS popup window");
			assertCheck("validateBagOptionsAtDSSPopup",
					"Opps!! Something went wrong, 'Bag Groceries' option is not present or enable at DSS popup window");
		}
		if (isElementPresent(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn"),
				"DSS_Customer_Choice_Window_Unattended_RadioBtn")) {
			testLog.info("Great!! 'Unattended delivery' option is present at DSS popup window");
		} else {
			testLog.error(
					"Opps!! Something went wrong, 'Unattended delivery' option is not present at DSS popup window");
			assertCheck("validateBagOptionsAtDSSPopup",
					"Opps!! Something went wrong, 'Unattended delivery' option is not present at DSS popup window");
		}
		if (isElementPresent(OR_OR.getProperty("DSS_Customer_Choice_Window_Signed_RadioBtn"),
				"DSS_Customer_Choice_Window_Signed_RadioBtn")) {
			testLog.info("Great!! 'Signed delivery' option is present at DSS popup window");
		} else {
			testLog.error("Opps!! Something went wrong, 'Signed delivery' option is not present at DSS popup window");
			assertCheck("validateBagOptionsAtDSSPopup",
					"Opps!! Something went wrong, 'Signed delivery' option is not present at DSS popup window");
		}
		return this;
	}

	public DeliverySlotPage SearchCCAddress(String CC_Address) {
		int ccAddressCount = getXpathCount(OR_OR.getProperty("CC_Address_List_button"), "CC_Address_List_button");
		String nth_ccadd_value = OR_OR.getProperty("CC_Address_Name_Heading");
		for (int i = 1; i < ccAddressCount; i++) {
			String istrvalue = Integer.toString(i);
			String ccaddpath = nth_ccadd_value.replace("nvalue", istrvalue);
			String CCAddressheading = driver.findElement(By.xpath(ccaddpath)).getText();
			String CCaddfullname = "Coles " + CC_Address;
			// if (CCAddressheading.equals(CCaddfullname)) {
			if (CCAddressheading.contains(CCaddfullname)) {
				driver.findElement(By.xpath(ccaddpath)).click();
				wait(2000);
				break;
			}
		}
		return this;
	}

	public DeliverySlotPage ClearandSearchAutoSuggestion(String address) {
		wait(3000);
		ExplicitWait(OR_OR.getProperty("CC_Clear_Search_button"), "CC_Clear_Search_button", 10);
		Click_Button_Xpath(OR_OR.getProperty("CC_Clear_Search_button"), "CC_Clear_Search_button");

		Sendkey_xpath(OR_OR.getProperty("CC_Search_inputBox"), address, "CC_Search_inputBox");
		int allsuggns = getXpathCount(OR_OR.getProperty("CC_Search_Auto_suggestions"), "CC_Search_Auto_suggestions");
		String nth_autosug_value = OR_OR.getProperty("CC_Search_Auto_Nth_suggestions");
		for (int i = 1; i <= allsuggns; i++) {
			String istrvalue = Integer.toString(i);
			String ccaddpath = nth_autosug_value.replace("nvalue", istrvalue);
			String nth_autosug_text = driver.findElement(By.xpath(ccaddpath)).getText();
			if (nth_autosug_text.contains(address)
					|| nth_autosug_text.endsWith(address.substring(address.length() - 4))) {
				driver.findElement(By.xpath(ccaddpath)).click();
				wait(2000);
				break;
			}
		}
		return this;
	}

	public DeliverySlotPage clickChooseADifferentCCAddressLinkDSS() {
		Click_Button_Xpath(OR_OR.getProperty("CC_Choose_A_Different_Address_Link"),
				"CC_Choose_A_Different_Address_Link");
		wait(5000);
		return this;
	}

	public DeliverySlotPage validateBaggingPreferenceCheckbox() {
		wait(4000);
		boolean stflag = false;
		// String hd_date = "", hd_time = "";

		clickDSSAlertWindowCloseButton();
		clickEmergencyMessageCloseButton();
		setDSSXpath();

		int win_start = getCurrentDay();
		// loop to iterate through days
		outerloop: for (int i = win_start; i <= 14; i++) {

			String daystr = Integer.toString(i);

			// check if at least one slot is available for the selected day else
			// continue to
			// next day
			String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
			DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
			if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))) {
				testLog.info("No slot available for the selected slot. Select a different day");
				continue;
			}

			String slot_selector = slot.replace("nvalue", daystr);
			String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
			// loop to select either Morning afternoon or evening slot, which
			// ever is
			// available first

			for (int k = 1; k <= 4; k++) {
				String kstr1 = Integer.toString(k);
				String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
				String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
				if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
					Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
				}
				By getChanges = By.xpath(slot_selector_FinalXpath);

				if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
					testLog.info("Slots are not available. Moving to next section of the day");
					continue;
				}

				List<WebElement> slot_elements = driver.findElements(getChanges);
				testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
				if (slot_elements.size() == 0) {
					continue;
				}

				// loop to iterate thru all available slot in (morning, noon and
				// night slot
				// sections)
				// selects the first available slot
				String hd_slot_price_1 = "";
				String HD_slot_selector = "";
				// String HD_slot_time1 = "";
				String hd_slot_header1 = "";
				for (int j = 1; j <= slot_elements.size(); j++) {

					String kstr = Integer.toString(j);

					String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
					DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
					DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
					DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);
					hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
					hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
					hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
					HD_slot_selector = HD_slot.replace("nvalue", daystr);
					HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
					HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
					hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
					hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
					hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

					boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
					String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
							.getAttribute("data-time-slots");
					String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
					testLog.info("Extracted price from time slot is: " + extracted_price);
					if ("Expired".equals(extracted_price) || "Sold out".equals(extracted_price)
							|| "Closed".equals(extracted_price) || deliveryViaYello
							|| yellowindowAttr.toLowerCase().contains("yello")) {
						testLog.info("This is an expired/ Sold out slot//Delivery Via Yello slot, checking next slot");
						continue;
					}

					// check if slot is customer choice then validate else
					// select another window
					String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);

					if (DelType.equals("") || DelType.contains("SIGNATURE REQUIRED")) {

						driver.findElement(By.xpath(HD_slot_selector)).click();

						Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_delivery_Opt_Signed"),
								"DSS_Overlay_delivery_Opt_Signed");

						// validating if bag is selected as per user preference
						if (bagflag == true) {
							testLog.info("Bagging preference was Bag My Grocerries");
							if (driver.findElement(By.id(OR_OR.getProperty("DSS_Overlay_Bagged_Option_RButton_id")))
									.isSelected()) {
								testLog.info("As expected Bag my grocerries is selected already.");
								testLog.info("User preference is intact");
							} else {
								testLog.error("As per user preference Bag my grocerries should come as selected");
								assertCheck("validateBaggingPreferences",
										"As per user preference Bag my grocerries should come as selected");
							}
						} else if (baglessflag == true) {
							testLog.info("Bagging preference was Bagless");
							if (driver.findElement(By.id(OR_OR.getProperty("DSS_Overlay_Bagless_Option_RButton")))
									.isSelected()) {
								testLog.info("As expected Bagless is selected already.");
								testLog.info("User preference is intact");
							} else {
								testLog.error("As per user preference Bagless sould come as selected");
								assertCheck("validateBaggingPreferences",
										"As per user preference Bagless should come as selected");
							}
						} else {
							testLog.error("Bagging preference is not properly selected");
							assertCheck("validateBaggingPreferences", "Bagging preference is not properly selected");
						}
						stflag = true;

					} else {
						continue;
					}
					if (stflag)
						break outerloop;
				}
			}
		}

		wait(5000);

		for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
			String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
					"DSS_Overlay_SlotConfirmation_Text1");
			if ("Please wait while we hold this slot for you".equals(overlay_text)) {
				wait(5000);
			} else {
				break;
			}
		}
		return this;
	}

	public DeliverySlotPage validateHDSlotsNotAvailable() {
		wait(4000);
		day = OR_OR.getProperty("DSS_Day_Selector_AirlineV");
		day_month = OR_OR.getProperty("DSS_Day_Selector_AirlineV2");
		slot = OR_OR.getProperty("DSS_HD_No_Slots_Available");
		String slot_not_availale = OR_OR.getProperty("DSS_Day_Selector_Status_Heading");
		int win_start = getCurrentDay();

		for (int i = win_start; i <= 7; i++) {
			String daystr = Integer.toString(i);
			clickDay(daystr);
			String slot_selector = slot.replace("nvalue", daystr);
			String day_selector_status = slot_not_availale.replace("nvalue", daystr);
			verify_xpath_text(day_selector_status, OR_OR.getProperty("DSS_Day_Selector_Status_Heading_Text"));
			for (int k = 1; k <= 4; k++) {
				String kstr1 = Integer.toString(k);
				String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
				switch (k) {
				case 1:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Overnight_Slots_Not_Available_Text"));
					break;
				case 2:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Morning_Slots_Not_Available_Text"));
					break;
				case 3:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Afternoon_Slots_Not_Available_Text"));
					break;
				case 4:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Evening_Slots_Not_Available_Text"));
					break;
				default:
					break;
				}
			}

		}
		return this;
	}

	public DeliverySlotPage validateCCSlotsNotAvailable() {
		wait(4000);
		day = OR_OR.getProperty("DSS_Day_Selector_AirlineV");
		day_month = OR_OR.getProperty("DSS_Day_Selector_AirlineV2");
		slot = OR_OR.getProperty("DSS_CC_No_Slots_Available");
		String slot_not_availale = OR_OR.getProperty("DSS_Day_Selector_Status_Heading");
		int win_start = getCurrentDay();
		for (int i = win_start; i <= 5; i++) {
			String daystr = Integer.toString(i);
			clickDay(daystr);
			String slot_selector = slot.replace("nvalue", daystr);
			String day_selector_status = slot_not_availale.replace("nvalue", daystr);
			verify_xpath_text(day_selector_status, OR_OR.getProperty("DSS_Day_Selector_Status_Heading_Text"));
			for (int k = 1; k <= 3; k++) {
				String kstr1 = Integer.toString(k);
				String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
				switch (k) {
				case 1:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Morning_Slots_Not_Available_Text"));
					break;
				case 2:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Afternoon_Slots_Not_Available_Text"));
					break;
				case 3:
					verify_xpath_contains_text(slot_selector_FinalXpath,
							OR_OR.getProperty("DSS_Evening_Slots_Not_Available_Text"));
					break;
				default:
					break;
				}
			}

		}
		return this;
	}

	public DeliverySlotPage validateDSSHDSlotNoBagSelected() {
		wait(4000);
		boolean stflag = false;
		String hd_date = "", hd_time = "";

		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			int win_start = getCurrentDay();
			// loop to iterate through days
			outerloop: for (int i = win_start; i <= 14; i++) {

				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in (morning, noon
					// and night slot
					// sections)
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);
						if ("Expired".equals(extracted_price) || "Sold out".equals(extracted_price)
								|| "Closed".equals(extracted_price) || deliveryViaYello
								|| yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);

						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);

						if (DelType.equalsIgnoreCase("") || DelType.equalsIgnoreCase("SIGNATURE REQUIRED")) {
							driver.findElement(By.xpath(HD_slot_selector)).click();
							validateBagOptionsAtDSSPopup();
							Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_delivery_Opt_Signed"),
									"DSS_Overlay_delivery_Opt_Signed");
							// clicking continue button without selecting any
							// bag option
							Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
									"DSS_Overlay_Continue_Button");
							// validating no bag selected message
							if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading"),
									"DSS_Overlay_Bag_Opt_Error_Heading")) {
								verify_xpath_contains_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading"),
										OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading_Text"));
								verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc"),
										OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc_Text"));
								verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc1"),
										OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc_Text"));

							} else {
								// assertCheck("validateDSSHDSlotNoBagSelected",
								// "Select Bag Alert msg is not
								// displayed");
								testLog.warn("validateDSSHDSlotNoBagSelected-Select Bag Alert msg is not displayed");
							}

							selectBagOption(true);
							Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
									"DSS_Overlay_Continue_Button");
							ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time", 10);
							stflag = true;

						} else
							continue;

						if (stflag)
							break outerloop;
					}
				}

			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(5000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}

		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
		return this;
	}

	public DeliverySlotPage validateDSSCCSlotNoBagSelected() {
		try {
			boolean stflag = true;
			wait(4000);

			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();
			int win_start = getCurrentDay();
			/*
			 * //outerloop: for (int i = win_start; i <= 5; i++) { outerloop:
			 * for (int i = 2; i <= 5; i++) { String daystr =
			 * Integer.toString(i); clickDay(daystr); String slot_selector =
			 * cc_slot_list.replace("nvalue", daystr);
			 * 
			 * for (int k = 1; k <= 3; k++) { String kstr1 =
			 * Integer.toString(k); String slot_selector_FinalXpath =
			 * slot_selector.replace("kvalue", kstr1);
			 */
			outerloop: for (int i = win_start; i <= 5; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = cc_slot_list.replace("nvalue", daystr);
				String cc_show_sold_out_link1 = cc_show_sold_out_link.replace("nvalue", daystr);
				// --Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				for (int k = 1; k <= 3; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String cc_show_sold_out_link_Final = cc_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final")) {
						Click_Button_Xpath(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}

					By getChanges = By.xpath(slot_selector_FinalXpath);
					testLog.info("getchanges:" + getChanges);
					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of CC slots for day:" + day_selector + " is " + slot_elements.size());

					if (slot_elements.size() == 0) {
						continue;
					}

					String cc_slot_selector = "";
					String cc_slot_price_selector = "";
					String cc_slot_already_selected = "";

					for (int j = 1; j <= slot_elements.size(); j++) {
						String slotStatus = "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time'] | //div[@id='daynvalue-time-slots']//div[contains(@class,'cc-n-rd-slots')]//div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//*[contains(@class,'times-slot')][mvalue]//span[contains(@class,'times-slot-time')]";
						String kstr = Integer.toString(j);
						cc_slot_already_selected = cc_slot_already_sel.replaceAll("nvalue", daystr);

						cc_slot_selector = cc_slot.replace("nvalue", daystr);
						cc_slot_selector = cc_slot_selector.replace("kvalue", kstr1);
						cc_slot_selector = cc_slot_selector.replace("mvalue", kstr);
						cc_slot_price_selector = cc_slot_price.replace("nvalue", daystr);
						cc_slot_price_selector = cc_slot_price_selector.replace("kvalue", kstr1);
						cc_slot_price_selector = cc_slot_price_selector.replace("mvalue", kstr);
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);
						String getslotStatus = driver.findElement(By.xpath(slotStatus)).getText().trim();
						String extracted_price = driver.findElement(By.xpath(cc_slot_price_selector)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						if ("Expired".equals(getslotStatus) || "Sold out".equals(getslotStatus)
								|| "Closed".equals(getslotStatus)) {
							testLog.info("This is an expired/ Sold out slot, checking next slot");
							continue;
						}

						/*
						 * if ("Expired".equals(extracted_price) ||
						 * "Sold out".equals(extracted_price) ||
						 * "Closed".equals(extracted_price)) { testLog.
						 * info("This is an expired/ Sold out slot, checking next slot"
						 * ); continue; }
						 */

						boolean boolval = isElementPresent(cc_slot_already_selected, "hd_slot_already_selected");
						if (boolval == true) {

							testLog.warn("Slot is already selected");
							break;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						String cc_date = date_0 + "" + year;
						String cc_time = driver.findElement(By.xpath(cc_slot_selector)).getText();
						cc_time = cc_time.substring(0, 18);

						testLog.info("CC Date extracted is: " + cc_date);
						testLog.info("CC Time extracted from slot is: " + cc_time);

						driver.findElement(By.xpath(cc_slot_selector)).click();
						wait(2000);
						ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button",
								25);
						verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotRes_Heading"),
								OR_OR.getProperty("DSS_Overlay_SlotRes_Heading_Collection_Text"));

						testLog.info("Slot selected successfully in DSS");

						Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
								"DSS_Overlay_Continue_Button");

						// validating no bag selected message
						if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading"),
								"DSS_Overlay_Bag_Opt_Error_Heading")) {
							verify_xpath_contains_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading"),
									OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Heading_Text"));
							verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc"),
									OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc_Text"));
							verify_xpath_text(OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc1"),
									OR_OR.getProperty("DSS_Overlay_Bag_Opt_Error_Desc_Text"));

						} else {
							assertCheck("validateDSSHDSlotNoBagSelected", "Select Bag Alert msg is not displayed");
						}

						testLog.info("Selecting Bag option");
						selectBagOption(true);
						Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
								"DSS_Overlay_Continue_Button");

						if (isSlotSelectedSuccessfully("CC", cc_date, cc_time)) {
							testLog.info("Slot selected successfully");
						} else {
							stflag = false;
						}
						break outerloop;
					}
				}
			}
			if (stflag == false) {
				testLog.error("DSS slot not selected properly");
				assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
			}

		} catch (Exception e) {
			testLog.error("Failed to select the DSS CC slot");
			assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
		}
		return this;
	}

	public DeliverySlotPage selectSecondRDProviderAddress() {
		ExplicitWait(OR_OR.getProperty("RD_Second_Remote_Provider_Address"), "RD_Second_Remote_Provider_Address", 5);
		Click_Button_Xpath(OR_OR.getProperty("RD_Second_Remote_Provider_Address"), "RD_Second_Remote_Provider_Address");
		wait(4000);
		return this;
	}

	public DeliverySlotPage selectThirdRDProviderAddress() {
		ExplicitWait(OR_OR.getProperty("RD_Third_Remote_Provider_Address"), "RD_Third_Remote_Provider_Address", 5);
		Click_Button_Xpath(OR_OR.getProperty("RD_Third_Remote_Provider_Address"), "RD_Third_Remote_Provider_Address");
		wait(4000);
		return this;
	}

	public DeliverySlotPage validateRDSlotsNotAvailable() {
		wait(4000);
		day = OR_OR.getProperty("DSS_Day_Selector");
		String rd_day = OR_OR.getProperty("DSS_RD_Day_Selector");
		String rd_no_slot = OR_OR.getProperty("DSS_RD_Slots_Not_Available_Text");
		int win_start = getCurrentDay();
		for (int i = win_start; i <= 14; i++) {
			String daystr = Integer.toString(i);
			clickDay(daystr);
			String rd_day_n = rd_day.replace("nvalue", daystr);
			String rd_no_slot_n = rd_no_slot.replace("nvalue", daystr);
			String day_text = driver.findElement(By.xpath(rd_day_n)).getText().toLowerCase();
			String rd_no_slot_avl = driver.findElement(By.xpath(rd_no_slot_n)).getText().toLowerCase();
			String rd_no_slot_text = "there are no more delivery slots available for " + day_text
					+ ". please select an alternate day.";
			if (rd_no_slot_avl.equals(rd_no_slot_text)) {
				testLog.info("RD slots not available and RD text is matched");
			} else {
				testLog.error("RD slots are may be available or RD text is not matched");
				assertCheck("validateRDSlotsNotAvailable", "RD slots are may be available or RD text is not matched");
			}

		}
		return this;
	}

	public DeliverySlotPage HDRistrictedSlotSelector() {
		wait(4000);
		boolean stflag = false;
		String hd_date = "", hd_time = "";
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			int win_start = getCurrentDay();
			// loop to iterate through days
			outerloop: for (int i = win_start; i <= 7; i++) {
				String daystr = Integer.toString(i);
				String day_type = "ANYDAY";
				clickDay(daystr);
				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in (morning, noon
					// and night slot
					// sections)
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);
						// added code to get the data for validation
						String slotStatus = OR_OR.getProperty("DSS_Slot_Status");
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);
						if ("Expired".equals(extracted_price) || "Sold out".equals(extracted_price)
								|| "Closed".equals(extracted_price) || deliveryViaYello
								|| yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}
						// above condition is comparing status with price ie not
						// working, so created below condition
						String status = driver.findElement(By.xpath(slotStatus)).getText().trim();
						if (status.equals("Closed") || status.equals("Expired") || status.equals("Sold out")
								|| deliveryViaYello || yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);
						String attvalue = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						if (attvalue.contains("DONUTS")) {
							driver.findElement(By.xpath(HD_slot_selector)).click();
							wait(5000);
							validateDeliveryRestrictionPopup();
							Click_Button_Xpath(OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button"),
									"DeliveryRestriction_Keep_Items_Button");
							stflag = true;
						} else
							continue;
						break;

					}
					if (stflag)
						break outerloop;
				}
			}
			if (stflag == false) {
				testLog.error("Restriced slots are not available in DSS page");
				assertCheck("HDRistrictedSlotSelector", "Restriced slots are not available in DSS page");
			}

		} catch (Exception e) {
			testLog.error("Restriced slots are not available in DSS page");
			assertCheck("HDRistrictedSlotSelector", "Restriced slots are not available in DSS page");
		}

		return this;
	}

	public DeliverySlotPage validateDeliveryRestrictionPopup() {
		wait(3000);
		verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_overlay_Header"),
				OR_OR.getProperty("DeliveryRestriction_overlay_Header_Text"));
		verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_overlay_Sub_Header"),
				OR_OR.getProperty("DeliveryRestriction_overlay_Sub_Header_Text"));
		verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button"),
				OR_OR.getProperty("DeliveryRestriction_Keep_Items_Button_Text"));
		verify_xpath_text(OR_OR.getProperty("DeliveryRestriction_Keep_DeliveryTime_Button"),
				OR_OR.getProperty("DeliveryRestriction_Keep_DeliveryTime_Button_Text"));

		return this;
	}

	public DeliverySlotPage enterCCSuburb(String suburb) {
		wait(3000);
		Sendkey_xpath(OR_OR.getProperty("CC_Enter_Suburb"), suburb, "CC_Enter_Suburb");
		Click_Button_Xpath(OR_OR.getProperty("CC_Suburb_Auto_Suggestion"), "CC_Suburb_Auto_Suggestion");
		return this;
	}

	public void HDSlotSelectorWithMachingSlotDateTime(String SlotDate, String SlotTime) {
		wait(4000);
		String hd_time = "";
		boolean stflag = false;
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();
			int win_start = getCurrentDay();
			String daystr = "";
			// loop to iterate through days
			for (int i = win_start; i <= 14; i++) {
				daystr = Integer.toString(i);
				String dssDates = OR_OR.getProperty("DSS_Nth_Date_Selector");
				String dssDaySelector = dssDates.replace("nvalue", daystr);
				String datetext = driver.findElement(By.xpath(dssDaySelector)).getText();
				if (datetext.contains(SlotDate)) {
					testLog.info("Slot Date is:" + datetext);
					driver.findElement(By.xpath(dssDaySelector)).click();
					break;
				}
			}
			String slot_selector = slot.replace("nvalue", daystr);
			String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
			outerloop: for (int k = 1; k <= 4; k++) {
				String kstr1 = Integer.toString(k);
				String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
				String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
				if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
					Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
				}
				By getChanges = By.xpath(slot_selector_FinalXpath);

				if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
					testLog.info("Slots are not available. Moving to next section of the day");
					continue;
				}

				List<WebElement> slot_elements = driver.findElements(getChanges);
				testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
				if (slot_elements.size() == 0) {
					continue;
				}

				// loop to iterate thru all available slot in (morning, noon and
				// night slot
				// sections)
				// selects the first available slot
				String hd_slot_price_1 = "";
				String HD_slot_selector = "";
				String HD_slot_time1 = "";
				String hd_slot_header1 = "";
				for (int j = 1; j <= slot_elements.size(); j++) {

					String kstr = Integer.toString(j);

					String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
					DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
					DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
					DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

					HD_slot_selector = HD_slot.replace("nvalue", daystr);
					HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
					HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
					hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
					hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
					hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
					HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
					HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
					HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
					hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
					hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
					hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

					boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
					String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
							.getAttribute("data-time-slots");
					String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
					testLog.info("Extracted price from time slot is: " + extracted_price);
					if ("Expired".equals(extracted_price) || "Sold out".equals(extracted_price)
							|| "Closed".equals(extracted_price) || deliveryViaYello
							|| yellowindowAttr.toLowerCase().contains("yello")) {
						testLog.info("This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
						continue;
					}
					hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText().trim();
					testLog.info("HD Time extracted from slot is: " + hd_time);
					SlotTime.trim();
					if (hd_time.equalsIgnoreCase(SlotTime)) {
						driver.findElement(By.xpath(HD_slot_selector)).click();
						stflag = true;
						break;
					} else {
						continue;
					}

				}
				if (stflag)
					break outerloop;
			}
			if (!stflag) {
				testLog.error("Failed to select the DSS slot due to Slot Time is not present in DSS page");
				assertCheck("HDSlotSelectorWithMachingSlotDateTime",
						"Failed to select the DSS slot due to Slot Time is not present in DSS page");
			}
			selectSignedDelivery();
			selectBagOption(true);
			Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button");
			ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time", 10);

			wait(5000);
		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot due to Slot Date is not present in DSS page");
			assertCheck("HDSlotSelectorWithMachingSlotDateTime",
					"Failed to select the DSS slot due to Slot Date is not present in DSS page");
		}

	}

	public DeliverySlotPage addFiltersOnChooseCCLocation() {
		ExplicitWait(OR_OR.getProperty("CC_Choose_Location_Add_Filters"), "CC_Choose_Location_Add_Filters", 20);
		Click_Button_Xpath(OR_OR.getProperty("CC_Choose_Location_Add_Filters"), "CC_Choose_Location_Add_Filters");
		return this;
	}

	public DeliverySlotPage clearAllFiltersOnChooseCCLocation() {
		ExplicitWait(OR_OR.getProperty("CC_Choose_Location_Clear_All_Filters"), "CC_Choose_Location_Clear_All_Filters",
				20);
		Click_Button_Xpath(OR_OR.getProperty("CC_Choose_Location_Clear_All_Filters"),
				"CC_Choose_Location_Clear_All_Filters");
		return this;
	}

	public DeliverySlotPage filterCCConcierge() {
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Type"),
				OR_OR.getProperty("CC_Filter_By_Collection_Type_Test"));
		if (isElementPresent(OR_OR.getProperty("CC_Concierge_Checkbox"), "CC_Concierge_Checkbox")) {
			verify_xpath_text(OR_OR.getProperty("CC_DirectToBoot"), OR_OR.getProperty("CC_DirectToBoo_Text"));
			Click_Button_Xpath(OR_OR.getProperty("CC_Concierge_Checkbox"), "CC_Concierge_Checkbox");
		} else {
			testLog.error("Click and collect Concierge service type option is not available");
			assertCheck("filterCCConcierge",
					"Click and collect Concierge/Direct To Boot service type option is not available");
		}
		return this;
	}

	public DeliverySlotPage clickShowFilteredLocations() {
		Click_Button_Xpath(OR_OR.getProperty("CC_Show_Filtered_Locations_Button"), "CC_Show_Filtered_Locations_Button");
		wait(2000);
		ExplicitWait(OR_OR.getProperty("CC_Choose_Location_Add_Filters"), "CC_Choose_Location_Add_Filters", 20);
		return this;
	}

	public DeliverySlotPage validateServiceType(String servicetype) {
		wait(2000);
		String serviceTypeAddr = get_xpath_text(OR_OR.getProperty("CC_Service_Type_Address_Select"),
				"CC_Service_Type_Address_Select");
		if (serviceTypeAddr.contains(servicetype)) {
			testLog.info("Click and collect " + servicetype + " service type is selected ");
		} else {
			testLog.error("Click and collect " + servicetype + " service type is not selected");
			assertCheck("validateServiceType", "Click and collect " + servicetype + " service type is not selected");
		}
		return this;
	}

	public DeliverySlotPage filterCCServiceDesk() {
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Type"),
				OR_OR.getProperty("CC_Filter_By_Collection_Type_Test"));
		if (isElementPresent(OR_OR.getProperty("CC_Service_Desk_Checkbox"), "CC_Service_Desk_Checkbox")) {
			verify_xpath_text(OR_OR.getProperty("CC_Service_Desk"), OR_OR.getProperty("CC_Service_Desk_Text"));
			Click_Button_Xpath(OR_OR.getProperty("CC_Service_Desk_Checkbox"), "CC_Service_Desk_Checkbox");
		} else {
			testLog.error("Click and collect service desk option is not available");
			assertCheck("filterCCServiceDesk", "Click and collect service desk option is not available");
		}
		return this;
	}

	public DeliverySlotPage filterCCColesExpress() {
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Type"),
				OR_OR.getProperty("CC_Filter_By_Collection_Type_Test"));
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Location"),
				OR_OR.getProperty("CC_Filter_By_Collection_Location_Text"));

		if (isElementPresent(OR_OR.getProperty("CC_Coles_Express_Checkbox"), "CC_Coles_Express_Checkbox")
				&& isElementPresent(OR_OR.getProperty("CC_Coles_Express_Location_Checkbox"),
						"CC_Coles_Express_Location_Checkbox")) {
			verify_xpath_text(OR_OR.getProperty("CC_Coles_Express"), OR_OR.getProperty("CC_Coles_Express_Text"));
			verify_xpath_text(OR_OR.getProperty("CC_Coles_Express_Location"),
					OR_OR.getProperty("CC_Coles_Express_Location_Text"));
			Click_Button_Xpath(OR_OR.getProperty("CC_Coles_Express_Checkbox"), "CC_Coles_Express_Checkbox");
			Click_Button_Xpath(OR_OR.getProperty("CC_Coles_Express_Location_Checkbox"),
					"CC_Coles_Express_Location_Checkbox");
		} else {
			testLog.error("Click and collect coles express option is not available");
			assertCheck("filterCCColesExpress", "Click and collect coles express option is not available");
		}
		return this;
	}

	public DeliverySlotPage filterCCServiceLocker() {
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Type"),
				OR_OR.getProperty("CC_Filter_By_Collection_Type_Test"));
		if (isElementPresent(OR_OR.getProperty("CC_Service_Locker_Checkbox"), "CC_Service_Locker_Checkbox")) {
			verify_xpath_text(OR_OR.getProperty("CC_Service_Locker"), OR_OR.getProperty("CC_Service_Locker_Text"));
			Click_Button_Xpath(OR_OR.getProperty("CC_Service_Locker_Checkbox"), "CC_Service_Locker_Checkbox");
			Click_Button_Xpath(OR_OR.getProperty("CC_Coles_Express_Location_Checkbox"),
					"CC_Coles_Express_Location_Checkbox");
		} else {
			testLog.error("Click and collect service locker option is not available");
			assertCheck("filterCCServiceLocker", "Click and collect service locker option is not available");
		}
		return this;
	}

	public DeliverySlotPage filterCCMobile() {
		verify_xpath_text(OR_OR.getProperty("CC_Filter_By_Collection_Type"),
				OR_OR.getProperty("CC_Filter_By_Collection_Type_Test"));
		if (isElementPresent(OR_OR.getProperty("CC_Mobile_Collect_Checkbox"), "CC_Mobile_Collect_Checkbox")) {
			verify_xpath_text(OR_OR.getProperty("CC_Mobile_Collect"), OR_OR.getProperty("CC_Mobile_Collect_Text"));
			Click_Button_Xpath(OR_OR.getProperty("CC_Mobile_Collect_Checkbox"), "CC_Mobile_Collect_Checkbox");
		} else {
			testLog.error("Click and collect mobile collect option is not available");
			assertCheck("filterCCServiceLocker", "Click and collect mobile collect option is not available");
		}
		return this;
	}

	public String getDayRDSlots(String day) {

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		String dayVal;
		if (day.equals("Today")) {
			c.setTime(date);
			dayVal = new SimpleDateFormat("EEEE").format(date);
			testLog.info("Today the day is :" + dayVal);
		} else if (day.equals("Tomorrow")) {
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			dayVal = new SimpleDateFormat("EEEE").format(c.getTime());
			testLog.info("Tomorrow the day is :" + dayVal);
		} else {
			dayVal = day;
		}
		return dayVal;
	}

	public void PDSlotSelector(String window_type, boolean bag, boolean remember_bagging_preference) {
		wait(12000);
		boolean stflag = false;
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			// loop to iterate through days
			outerloop: for (int i = getCurrentDay(); i <= 7; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);

				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}
				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);

				// Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in the selected
					// section
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					// String partnerDel =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]/button//span[@data-visual-display-text='PARTNERDELIVERY']";
					// String slotStatus =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						String partnerDel = OR_OR.getProperty("DSS_Partner_Delivery");
						partnerDel = partnerDel.replace("nvalue", daystr);
						partnerDel = partnerDel.replace("kvalue", kstr1);
						partnerDel = partnerDel.replace("mvalue", kstr);

						String slotStatus = OR_OR.getProperty("DSS_Slot_Status");
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						String status = driver.findElement(By.xpath(slotStatus)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						Boolean isPartnerDelevery = isElementPresent(partnerDel, "partnerDel");
						if (!isPartnerDelevery) {
							testLog.info("This is a not Partner Delivery slot. Skiping this slot");
							continue;
						}

						if ("Expired".equals(status) || "Sold out".equals(status) || "Closed".equals(status)
								|| deliveryViaYello || yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);
						//
						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						DelType = DelType.replaceAll("[^a-zA-Z]", "");
						window_type = window_type.toLowerCase();
						window_type = window_type.replaceAll("\\s+", "");
						switch (window_type) {
						case "signed":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(8000);
								selectSignedDelivery();
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag); if
								 * (remember_bagging_preference) {
								 * 
								 * if(isElementVisible(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox"))
								 * { Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * }else { testLog.
								 * error("Remember bagging preference checkbox is not visible"
								 * ); assertCheck(
								 * "HDSlotSelector","Remember bagging preference checkbox is not visible"
								 * ,"DSS_Overlay_Remember_Preference_Checkbox");
								 * }
								 * 
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "signaturerequired":
							// if (DelType.equalsIgnoreCase("SIGNATURE
							// REQUIRED"))
							if (DelType.equalsIgnoreCase("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag);
								 * 
								 * if (remember_bagging_preference) {
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								selectUnattendedDelivery();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							// if (DelType.equalsIgnoreCase("UNATTENDED ONLY"))
							// /* Changed in DSS page UI */
							if (DelType.equalsIgnoreCase("UNATTENDED")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code as per new functional
								// change at overlay popup window
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}
				}
			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(5000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}

			wait(5000);

			// if (isSlotSelectedSuccessfully("HD", hd_date, hd_time)) {
			// testLog.info("Slot selected successfully");
			// } else {
			// testLog.error("Slot is not selected properly");
			// assertCheck("HDSlotSelector", "Slot is not selected properly");
			// }

		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

	public void HDSlotSelector(String window_type, boolean bag, boolean remember_bagging_preference, boolean next_day) {
		wait(12000);
		boolean stflag = false;
		int win_start = 0;
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			win_start = getCurrentDay();

			if (next_day == true) {
				win_start++;
			}

			// loop to iterate through days
			outerloop: for (int i = win_start; i <= 7; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);

				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}
				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);

				// Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in the selected
					// section
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					// String partnerDel =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]/button//span[@data-visual-display-text='PARTNERDELIVERY']";
					// String slotStatus =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						String partnerDel = OR_OR.getProperty("DSS_Partner_Delivery");
						partnerDel = partnerDel.replace("nvalue", daystr);
						partnerDel = partnerDel.replace("kvalue", kstr1);
						partnerDel = partnerDel.replace("mvalue", kstr);

						String slotStatus = OR_OR.getProperty("DSS_Slot_Status");
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						String status = driver.findElement(By.xpath(slotStatus)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						Boolean isPartnerDelevery = isElementPresent(partnerDel, "partnerDel");
						if (isPartnerDelevery) {
							testLog.info("This is a Partner Delivery slot. Skiping this slot");
							continue;
						}
						// if ("Expired".equals(extracted_price) || "Sold
						// out".equals(extracted_price)
						// || "Closed".equals(extracted_price) ||
						// deliveryViaYello
						// || yellowindowAttr.toLowerCase().contains("yello")) {
						// testLog.info(
						// "This is an expired/ Sold out slot/Delivery Via Yello
						// slot, checking next slot");
						// continue;
						// }

						if ("Expired".equals(status) || "Sold out".equals(status) || "Closed".equals(status)
								|| deliveryViaYello || yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);
						//
						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						DelType = DelType.replaceAll("[^a-zA-Z]", "");
						window_type = window_type.toLowerCase();
						window_type = window_type.replaceAll("\\s+", "");
						switch (window_type) {
						case "signed":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(8000);
								selectSignedDelivery();
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag); if
								 * (remember_bagging_preference) {
								 * 
								 * if(isElementVisible(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox"))
								 * { Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * }else { testLog.
								 * error("Remember bagging preference checkbox is not visible"
								 * ); assertCheck(
								 * "HDSlotSelector","Remember bagging preference checkbox is not visible"
								 * ,"DSS_Overlay_Remember_Preference_Checkbox");
								 * }
								 * 
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "signaturerequired":
							// if (DelType.equalsIgnoreCase("SIGNATURE
							// REQUIRED"))
							if (DelType.equalsIgnoreCase("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag);
								 * 
								 * if (remember_bagging_preference) {
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								selectUnattendedDelivery();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							// if (DelType.equalsIgnoreCase("UNATTENDED ONLY"))
							// /* Changed in DSS page UI */
							if (DelType.equalsIgnoreCase("UNATTENDED")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code as per new functional
								// change at overlay popup window
								/*
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 * ExplicitWait(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button", 10);
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button");
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 */
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}
				}
			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(5000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}

			wait(5000);

			// if (isSlotSelectedSuccessfully("HD", hd_date, hd_time)) {
			// testLog.info("Slot selected successfully");
			// } else {
			// testLog.error("Slot is not selected properly");
			// assertCheck("HDSlotSelector", "Slot is not selected properly");
			// }

		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

	public AlmostDonePage skipUnattendedDeliveryInstructions() {
		boolean flag = isElementPresent(OR_OR.getProperty("Delivery_Instruction_Save_Button"),
				"Delivery_Instruction_Save_Button");
		if (flag == true) {
			Click_Button_Xpath(OR_OR.getProperty("Delivery_Instruction_Save_Button"),
					"Delivery_Instruction_Save_Button");
			wait(10000);
		}
		return new AlmostDonePage();
	}

	public void validateHDDSSPopupWindowForWA(String day_type, String window_type, boolean bag) {
		wait(4000);
		boolean stflag = false;
		String hd_date = "", hd_time = "";

		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			int win_start = getCurrentDay();
			// loop to iterate through days
			outerloop: for (int i = 2; i <= 14; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);
					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in (morning, noon
					// and night slot
					// sections)
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						// --New Added Code To Get the slot status
						String slotStatus = "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";

						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						String currentSlotStatus = driver.findElement(By.xpath(slotStatus)).getText().trim();

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						String extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);
						if ("Expired".equals(currentSlotStatus) || "Sold out".equals(currentSlotStatus)
								|| "Closed".equals(currentSlotStatus) || deliveryViaYello
								|| yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot//Delivery Via Yello slot, checking next slot");
							continue;
						}

						// --Commenting the old code
						/*
						 * if ("Expired".equals(extracted_price) ||
						 * "Sold out".equals(extracted_price) ||
						 * "Closed".equals(extracted_price) || deliveryViaYello
						 * || yellowindowAttr.toLowerCase().contains("yello")) {
						 * testLog.info(
						 * "This is an expired/ Sold out slot//Delivery Via Yello slot, checking next slot"
						 * ); continue; }
						 */

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);

						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						window_type = window_type.toLowerCase();
						switch (window_type) {
						case "signed":
							if (DelType.equals("") || DelType.contains("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								// Commented below method due to noBagOption
								// functional change
								validateBagOptionsAtDSSPopup();

								if (DelType.equals("")) {
									Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_delivery_Opt_Signed"),
											"DSS_Overlay_delivery_Opt_Signed");
								}
								// Commented below methods due to noBagOption
								// functional change
								selectBagOption(bag);
								// validateDSSOverlayPopup();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								ExplicitWait(OR_OR.getProperty("Choose_A_Delivery_Time"), "Choose_A_Delivery_Time", 10);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equals("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								validateBagOptions_CustomerChoice();
								verifyBaggingPriceForWA();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn"),
										"DSS_Customer_Choice_Window_Unattended_RadioBtn");
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							if (DelType.contains("Unattended")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(3000);
								// Unattended functionality got changed, below
								// code could be use in future
								/*
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 */
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								validateBaggedOption_UnattededWindow();

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}

				}

			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(4000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

	public DeliverySlotPage verifyBaggingPriceForWA() {

		ExplicitWait(OR_OR.getProperty("Bagging_Price_WA"), "Bagging_Price_WA", 10);
		if (isElementPresent(OR_OR.getProperty("Bagging_Price_WA"), "Bagging_Price_WA")) {
			verify_xpath_text(OR_OR.getProperty("Bagging_Price_WA"), OR_OR.getProperty("Bagging_Price_Message_WA"));

		} else {
			assertCheck("validate_baggingprice_wa",
					"Bagging price for western australis is not correct. It should be 0.20 AUD");
		}
		return this;
	}

	public DeliverySlotPage validateCCbaggingprice_WA() {
		try {
			boolean stflag = true;
			wait(4000);

			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();
			int win_start = getCurrentDay();
			/*
			 * //outerloop: for (int i = win_start; i <= 5; i++) { outerloop:
			 * for (int i = 2; i <= 5; i++) { String daystr =
			 * Integer.toString(i); clickDay(daystr); String slot_selector =
			 * cc_slot_list.replace("nvalue", daystr);
			 * 
			 * for (int k = 1; k <= 3; k++) { String kstr1 =
			 * Integer.toString(k); String slot_selector_FinalXpath =
			 * slot_selector.replace("kvalue", kstr1);
			 */
			outerloop: for (int i = win_start; i <= 5; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);
				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}

				String slot_selector = cc_slot_list.replace("nvalue", daystr);
				String cc_show_sold_out_link1 = cc_show_sold_out_link.replace("nvalue", daystr);
				// --Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				for (int k = 1; k <= 3; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String cc_show_sold_out_link_Final = cc_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final")) {
						Click_Button_Xpath(cc_show_sold_out_link_Final, "cc_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}

					By getChanges = By.xpath(slot_selector_FinalXpath);
					testLog.info("getchanges:" + getChanges);
					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of CC slots for day:" + day_selector + " is " + slot_elements.size());

					if (slot_elements.size() == 0) {
						continue;
					}

					String cc_slot_selector = "";
					String cc_slot_price_selector = "";
					String cc_slot_already_selected = "";

					for (int j = 1; j <= slot_elements.size(); j++) {
						String slotStatus = "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time'] | //div[@id='daynvalue-time-slots']//div[contains(@class,'cc-n-rd-slots')]//div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//*[contains(@class,'times-slot')][mvalue]//span[contains(@class,'times-slot-time')]";
						String kstr = Integer.toString(j);
						cc_slot_already_selected = cc_slot_already_sel.replaceAll("nvalue", daystr);

						cc_slot_selector = cc_slot.replace("nvalue", daystr);
						cc_slot_selector = cc_slot_selector.replace("kvalue", kstr1);
						cc_slot_selector = cc_slot_selector.replace("mvalue", kstr);
						cc_slot_price_selector = cc_slot_price.replace("nvalue", daystr);
						cc_slot_price_selector = cc_slot_price_selector.replace("kvalue", kstr1);
						cc_slot_price_selector = cc_slot_price_selector.replace("mvalue", kstr);
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);
						String getslotStatus = driver.findElement(By.xpath(slotStatus)).getText().trim();
						String extracted_price = driver.findElement(By.xpath(cc_slot_price_selector)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						if ("Expired".equals(getslotStatus) || "Sold out".equals(getslotStatus)
								|| "Closed".equals(getslotStatus)) {
							testLog.info("This is an expired/ Sold out slot, checking next slot");
							continue;
						}

						/*
						 * if ("Expired".equals(extracted_price) ||
						 * "Sold out".equals(extracted_price) ||
						 * "Closed".equals(extracted_price)) { testLog.
						 * info("This is an expired/ Sold out slot, checking next slot"
						 * ); continue; }
						 */

						boolean boolval = isElementPresent(cc_slot_already_selected, "hd_slot_already_selected");
						if (boolval == true) {

							testLog.warn("Slot is already selected");
							break;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						String cc_date = date_0 + "" + year;
						String cc_time = driver.findElement(By.xpath(cc_slot_selector)).getText();
						cc_time = cc_time.substring(0, 18);

						testLog.info("CC Date extracted is: " + cc_date);
						testLog.info("CC Time extracted from slot is: " + cc_time);

						driver.findElement(By.xpath(cc_slot_selector)).click();
						wait(2000);
						ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button",
								25);
						verify_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotRes_Heading"),
								OR_OR.getProperty("DSS_Overlay_SlotRes_Heading_Collection_Text"));

						// testLog.info("Slot selected successfully in DSS");

						// Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
						// "DSS_Overlay_Continue_Button");

						// validating no bag selected message
						if (isElementPresent(OR_OR.getProperty("Bagging_Price_WA"), "Bagging_Price_WA")) {

							verify_xpath_text(OR_OR.getProperty("Bagging_Price_WA"),
									OR_OR.getProperty("Bagging_Price_Message_WA"));

						} else {
							assertCheck("validate_baggingprice_wa",
									"Bagging price for western australia is not correct. It should be 0.20 AUD");
						}

						if (isElementPresent(OR_OR.getProperty("DSS_Overlay_Remember_Preference"),
								"DSS_Overlay_Remember_Preference")) {

							testLog.info("Great!!, Remember bagging preference checkbox is present on overlay popup.");
						} else {
							assertCheck("validate_baggingprice_wa",
									"OOPS!!, Remember bagging preference checkbox is not present on overlay popup.");
						}

						testLog.info("Selecting Bag option");
						selectBagOption(true);
						Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
								"DSS_Overlay_Continue_Button");

						if (isSlotSelectedSuccessfully("CC", cc_date, cc_time)) {
							testLog.info("Slot selected successfully");
						} else {
							stflag = false;
						}
						break outerloop;
					}
				}
			}
			if (stflag == false) {
				testLog.error("DSS slot not selected properly");
				assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
			}

		} catch (Exception e) {
			testLog.error("Failed to select the DSS CC slot");
			assertCheck("SlotSelectorCC", "DSS slot not selected properly", "");
		}
		return this;
	}

	public void HDSlotSelector_WA(String window_type, boolean bag, boolean remember_bagging_preference) {
		wait(12000);
		boolean stflag = false;
		try {
			clickDSSAlertWindowCloseButton();
			clickEmergencyMessageCloseButton();
			setDSSXpath();

			// loop to iterate through days
			outerloop: for (int i = 2; i <= 7; i++) {
				String daystr = Integer.toString(i);
				clickDay(daystr);
				// check if at least one slot is available for the selected day
				// else continue to
				// next day
				String DSS_Day_Info_Price = OR_OR.getProperty("DSS_Day_Info_Price");
				DSS_Day_Info_Price = DSS_Day_Info_Price.replace("nvalue", daystr);

				if (!(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("From $"))
						&& !(get_xpath_text(DSS_Day_Info_Price, "DSS_Day_Info_Price").contains("Free"))) {
					testLog.info("No slot available for the selected slot. Select a different day");
					continue;
				}
				String slot_selector = slot.replace("nvalue", daystr);
				String hd_show_sold_out_link1 = hd_show_sold_out_link.replace("nvalue", daystr);

				// Adding To Get Day
				String DSS_Day = OR_OR.getProperty("DSS_Day");
				DSS_Day = DSS_Day.replace("nvalue", daystr);
				Day = get_xpath_text(DSS_Day, "DSS_Day");
				testLog.info("Today the selected day is: " + Day);
				// loop to select either Morning afternoon or evening slot,
				// which ever is
				// available first

				for (int k = 1; k <= 4; k++) {
					String kstr1 = Integer.toString(k);
					String slot_selector_FinalXpath = slot_selector.replace("kvalue", kstr1);

					String hd_show_sold_out_link_Final = hd_show_sold_out_link1.replace("kvalue", kstr1);
					if (isElementPresent(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final")) {
						Click_Button_Xpath(hd_show_sold_out_link_Final, "hd_show_sold_out_link_Final");
						// all slots are sold out for this section. Moving to
						// next
						continue;
					}
					By getChanges = By.xpath(slot_selector_FinalXpath);

					if (isElementNotPresent(slot_selector_FinalXpath, "Slot selector")) {
						testLog.info("Slots are not available. Moving to next section of the day");
						continue;
					}

					List<WebElement> slot_elements = driver.findElements(getChanges);
					testLog.info("count of HD slots for day:" + day_selector + " is " + slot_elements.size());
					if (slot_elements.size() == 0) {
						continue;
					}

					// loop to iterate thru all available slot in the selected
					// section
					// selects the first available slot
					String hd_slot_price_1 = "";
					String HD_slot_selector = "";
					String HD_slot_time1 = "";
					String hd_slot_header1 = "";
					// String partnerDel =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]/button//span[@data-visual-display-text='PARTNERDELIVERY']";
					// String slotStatus =
					// "//*[@id='daynvalue-time-slots']/div[contains(@class,'hd-slots-wrapper')]/div[@class='timeSlots-innerWrap']//div[starts-with(@class,'js-')][kvalue]//div[starts-with(@class,'times-slot')][mvalue]//span[@class='times-slot-time']";
					for (int j = 1; j <= slot_elements.size(); j++) {

						String kstr = Integer.toString(j);

						String DSS_Delivery_Type = OR_OR.getProperty("DSS_Delivery_Type");
						DSS_Delivery_Type = DSS_Delivery_Type.replace("nvalue", daystr);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("kvalue", kstr1);
						DSS_Delivery_Type = DSS_Delivery_Type.replace("mvalue", kstr);

						String partnerDel = OR_OR.getProperty("DSS_Partner_Delivery");
						partnerDel = partnerDel.replace("nvalue", daystr);
						partnerDel = partnerDel.replace("kvalue", kstr1);
						partnerDel = partnerDel.replace("mvalue", kstr);

						String slotStatus = OR_OR.getProperty("DSS_Slot_Status");
						slotStatus = slotStatus.replace("nvalue", daystr);
						slotStatus = slotStatus.replace("kvalue", kstr1);
						slotStatus = slotStatus.replace("mvalue", kstr);

						HD_slot_selector = HD_slot.replace("nvalue", daystr);
						HD_slot_selector = HD_slot_selector.replace("kvalue", kstr1);
						HD_slot_selector = HD_slot_selector.replace("mvalue", kstr);
						hd_slot_price_1 = hd_slot_price.replace("nvalue", daystr);
						hd_slot_price_1 = hd_slot_price_1.replace("kvalue", kstr1);
						hd_slot_price_1 = hd_slot_price_1.replace("mvalue", kstr);
						HD_slot_time1 = hd_slot_time.replace("nvalue", daystr);
						HD_slot_time1 = HD_slot_time1.replace("kvalue", kstr1);
						HD_slot_time1 = HD_slot_time1.replace("mvalue", kstr);
						hd_slot_header1 = hd_slot_header.replace("nvalue", daystr);
						hd_slot_header1 = hd_slot_header1.replace("kvalue", kstr1);
						hd_slot_header1 = hd_slot_header1.replace("mvalue", kstr);

						boolean deliveryViaYello = isElementPresent(hd_slot_header1, "deliveryViaYello");
						String yellowindowAttr = driver.findElement(By.xpath(HD_slot_selector))
								.getAttribute("data-time-slots");
						extracted_price = driver.findElement(By.xpath(hd_slot_price_1)).getText().trim();
						String status = driver.findElement(By.xpath(slotStatus)).getText().trim();
						testLog.info("Extracted price from time slot is: " + extracted_price);

						Boolean isPartnerDelevery = isElementPresent(partnerDel, "partnerDel");
						if (isPartnerDelevery) {
							testLog.info("This is a Partner Delivery slot. Skiping this slot");
							continue;
						}

						if ("Expired".equals(status) || "Sold out".equals(status) || "Closed".equals(status)
								|| deliveryViaYello || yellowindowAttr.toLowerCase().contains("yello")) {
							testLog.info(
									"This is an expired/ Sold out slot/Delivery Via Yello slot, checking next slot");
							continue;
						}

						date_0 = (driver.findElement(By.xpath(day_selector)).getText()).concat(" ")
								.concat(driver.findElement(By.xpath(day_selector2)).getText());

						int year = Calendar.getInstance().get(Calendar.YEAR);
						hd_date = date_0.concat("" + year);
						hd_time = driver.findElement(By.xpath(HD_slot_time1)).getText();

						testLog.info("HD Date extracted is: " + hd_date);
						testLog.info("HD Time extracted from slot is: " + hd_time);
						//
						String DelType = get_xpath_text(DSS_Delivery_Type, DSS_Delivery_Type);
						DelType = DelType.replaceAll("[^a-zA-Z]", "");
						window_type = window_type.toLowerCase();
						window_type = window_type.replaceAll("\\s+", "");
						switch (window_type) {
						case "signed":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(8000);
								selectSignedDelivery();
								// Commented below code due to CE-5972
								// functional changes

								selectBagOption(bag);
								if (remember_bagging_preference) {

									if (isElementVisible(OR_OR.getProperty("DSS_Overlay_Remember_Preference_Checkbox"),
											"DSS_Overlay_Remember_Preference_Checkbox")) {
										Click_Button_Xpath(
												OR_OR.getProperty("DSS_Overlay_Remember_Preference_Checkbox"),
												"DSS_Overlay_Remember_Preference_Checkbox");
									} else {
										testLog.error("Remember bagging preference checkbox is not visible");
										assertCheck("HDSlotSelector",
												"Remember bagging preference checkbox is not visible",
												"DSS_Overlay_Remember_Preference_Checkbox");
									}

									if (bag == true)
										bagflag = true;
									else
										baglessflag = true;
								}

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "signaturerequired":
							// if (DelType.equalsIgnoreCase("SIGNATURE
							// REQUIRED"))
							if (DelType.equalsIgnoreCase("Signed")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code due to CE-5972
								// functional changes
								/*
								 * selectBagOption(bag);
								 * 
								 * if (remember_bagging_preference) {
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Remember_Preference_Checkbox"),
								 * "DSS_Overlay_Remember_Preference_Checkbox");
								 * if (bag == true) bagflag = true; else
								 * baglessflag = true; }
								 */

								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								wait(5000);
								stflag = true;

							} else
								continue;

							break;
						case "customerchoiceunattended":
							if (DelType.equalsIgnoreCase("")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								selectUnattendedDelivery();
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;

							break;

						case "unattendedonly":
							// if (DelType.equalsIgnoreCase("UNATTENDED ONLY"))
							// /* Changed in DSS page UI */
							if (DelType.equalsIgnoreCase("UNATTENDED")) {
								driver.findElement(By.xpath(HD_slot_selector)).click();
								wait(5000);
								// Commented below code as per new functional
								// change at overlay popup window
								/*
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 * ExplicitWait(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button", 10);
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "DSS_Overlay_Continue_Button"),
								 * "DSS_Overlay_Continue_Button");
								 * Click_Button_Xpath(OR_OR.getProperty(
								 * "Continue_With_UnattendedOnly"),
								 * "Continue_With_UnattendedOnly");
								 */
								ExplicitWait(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button", 10);
								Click_Button_Xpath(OR_OR.getProperty("DSS_Overlay_Continue_Button"),
										"DSS_Overlay_Continue_Button");
								stflag = true;
							} else
								continue;
							break;

						default:
							break;
						}
						if (stflag)
							break outerloop;
					}
				}
			}

			if (stflag == false) {
				testLog.error("Failed to select slot");
				assertCheck("HDSlotSelector", "Failed to select slot or no slot found");
			}

			wait(5000);

			for (int loop_cnt = 1; loop_cnt <= 10; loop_cnt++) {
				String overlay_text = get_xpath_text(OR_OR.getProperty("DSS_Overlay_SlotConfirmation_Text1"),
						"DSS_Overlay_SlotConfirmation_Text1");
				if ("Please wait while we hold this slot for you".equals(overlay_text)) {
					wait(5000);
				} else {
					break;
				}
			}

			wait(5000);

			// if (isSlotSelectedSuccessfully("HD", hd_date, hd_time)) {
			// testLog.info("Slot selected successfully");
			// } else {
			// testLog.error("Slot is not selected properly");
			// assertCheck("HDSlotSelector", "Slot is not selected properly");
			// }

		} catch (Exception e) {
			testLog.error("Failed to select the DSS slot");
		}
	}

}
