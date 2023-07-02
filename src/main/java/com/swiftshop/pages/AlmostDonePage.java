package com.swiftshop.pages;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.swiftshop.main.FunLibrary;

public class AlmostDonePage extends FunLibrary {
	public static String selectedSubstituteText = "";

	// click Slot change button in 'Almost Done' screen
	public DeliverySlotPage clickChangeSlot() {
		verify_xpath_text(OR_OR.getProperty("Checkout_Flow_Header"), "Please confirm your order details");
		Click_Button_Xpath(OR_OR.getProperty("AD_Slot_Change_Link"), "AD_Slot_Change_Link");
		return new DeliverySlotPage();
	}

	// click "Accept agreement" check-box
	public AlmostDonePage clickAgreementCheckbox() {
		// checkBaggingOption();
		wait(2000);
		// verify_xpath_text(OR_OR.getProperty("AlmostDonePage_PlaceOrder_Button"),
		// "Place order");
		verify_xpath_text(OR_OR.getProperty("AlmostDonePage_CompleteOrder_Button"),
				OR_OR.getProperty("AlmostDonePage_CompleteOrder_Button_Text"));
		scrollTo_Xpath(OR_OR.getProperty("AlmostDonePage_CompleteOrder_Button"), "AlmostDonePage_CompleteOrder_Button",
				2);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_CustomerAgreement_Checkbox"),
				"AlmostDonePage_CustomerAgreement_Checkbox");
		wait(5000);
		return this;
	}

	public AlmostDonePage checkBaggingOption() {

		if (isElementPresent(OR_OR.getProperty("No_Bagging_Option_Selected_Error"),
				"No_Bagging_Option_Selected_Error")) {
			Click_Button_Xpath(OR_OR.getProperty("Choose_Bagging_Option"), "Choose_Bagging_Option");
			ExplicitWait(OR_OR.getProperty("Bagging_Option_Heading"), "Bagging_Option_Heading", 25);

			boolean bagless_rb = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button"), "No_Bags_Radio_Button");
			boolean bagged_rb = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button"),
					"Bag_My_Groceries_Radio_Button");
			boolean bagless_rb_selected = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button_Selected"),
					"No_Bags_Radio_Button_Selected");
			boolean bagged_rb_selected = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button_Selected"),
					"Bag_My_Groceries_Radio_Button_Selected");

			if (bagless_rb && bagged_rb) {
				testLog.info("Bagging option is not selected, so selection NO BAGS OPTION");
				Click_Button_Xpath(OR_OR.getProperty("No_Bags_Radio_Button"), "No_Bags_Radio_Button");
				Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
				wait(15000);
			}
			if (bagless_rb_selected || bagged_rb_selected) {
				Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
				wait(15000);
			}

		} else {
			testLog.info("Bagging option is selected");
		}
		return this;
	}

	// click Place Order button
	public OrderConfirmationPage clickCompleteOrder() {
		if (driver.findElement(By.xpath(OR_OR.getProperty("AlmostDonePage_CustomerAgreement_Checkbox"))).isSelected()) {
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_CompleteOrder_Button"),
					"AlmostDonePage_CompleteOrder_Button");
			wait(8000);
		} else {
			testLog.error("Almost done Agreement checkbox is not selected");
			assertCheck("clickCompleteOrder()", "Almost done agreement checkbox is not selected");
		}
		return new OrderConfirmationPage();
	}

	public AlmostDonePage checkRDBagOption() {
		if (!isElementPresent(OR_OR.getProperty("Bagging_Heading"), "Bagging_Heading")) {
			testLog.info("As expected Bagging option is not available for RD orders");
		} else {
			assertCheck("checkRDBagOption", "Bagging option should not be visible for RD orders");
		}
		return this;
	}

	// This function returns trolley/cart amount in double format
	public double getTrolleyAmount() {
		double AD_Trolley_Amount = 0.0;
		ExplicitWait(OR_OR.getProperty("AD_TSummary_Cost"), "AD_TSummary_Cost", 30);
		if (isElementPresent(OR_OR.getProperty("AD_TSummary_Cost"), "AD_TSummary_Cost")) {
			String trolley_amount = get_xpath_text(OR_OR.getProperty("AD_TSummary_Cost"), "AD_TSummary_Cost");
			trolley_amount = trolley_amount.replaceAll("[^0-9,.]", "").trim();
			AD_Trolley_Amount = Double.parseDouble(trolley_amount);
			testLog.info("Actual Trolley Cost:" + AD_Trolley_Amount);
		} else {
			testLog.error("Trolley amount is not visible in AlmostDone page");
			assertCheck("getTrolleyAmount()", "Trolley amount is not visible in AlmostDone page", "AD_TSummary_Cost");
		}
		return AD_Trolley_Amount;
	}

	// This return total amount to be paid(i.e amount which include all other
	// charges and discounts)
	public double getTotalAmount() {
		wait(2000);
		double AD_Total_Price = 0;
		((JavascriptExecutor) driver).executeScript("scroll(0,600 )");
		if (isElementPresent(OR_OR.getProperty("AD_Total_Cost"), "AD_Total_Cost")) {
			String total_cost = get_xpath_text(OR_OR.getProperty("AD_Total_Cost"), "AD_Total_Cost");
			total_cost = total_cost.replaceAll("[^0-9,.]", "").trim();
			AD_Total_Price = Double.parseDouble(total_cost);
		} else {
			testLog.error("Total amount is not visible in AlmostDone page");
			assertCheck("getTrolleyAmount()", "Total amount is not visible in AlmostDone page", "AD_Total_Cost");
		}
		return AD_Total_Price;
	}

	// This function return the trolley/cart saving amount(include savings on
	// products only and not the promo discounts)
	public double getTrolleySaving() {
		double AD_TrolleySaving = 0;
		if (isElementPresent(OR_OR.getProperty("AD_TSummary_Sav_Cost"), "AD_TSummary_Sav_Cost") == true) {
			String trolley_sav_cost = get_xpath_text(OR_OR.getProperty("AD_TSummary_Sav_Cost"), "AD_TSummary_Sav_Cost");
			trolley_sav_cost = trolley_sav_cost.replaceAll("[^0-9,.]", "").trim();
			AD_TrolleySaving = Double.parseDouble(trolley_sav_cost);
		}
		return AD_TrolleySaving;
	}

	// returns total saving of user. includes all discounts: promo and product
	// discounts
	public double getTotalSaving() {
		double AD_Total_saving = 0;
		if (isElementPresent(OR_OR.getProperty("AD_TSummary_Sav_Cost"), "AD_TSummary_Sav_Cost") == true) {
			String total_sav_cost = get_xpath_text(OR_OR.getProperty("AD_Total_Sav_Cost"), "AD_Total_Sav_Cost");
			total_sav_cost = total_sav_cost.replaceAll("[^0-9,.]", "").trim();
			AD_Total_saving = Double.parseDouble(total_sav_cost);
		}
		return AD_Total_saving;
	}

	// returns price of time slot i.e delivery charges
	public double getTimeSlotPrice() {
		double AD_Slot_Price = 0;
		String Delivery_fee = get_xpath_text(OR_OR.getProperty("AD_Delivery_Fee"), "AD_Delivery_Fee");
		if (Delivery_fee.equals("Free")) {
			Delivery_fee = "0.0";
		}
		Delivery_fee = Delivery_fee.replaceAll("[^0-9,.]", "").trim();
		AD_Slot_Price = Double.parseDouble(Delivery_fee);
		return AD_Slot_Price;
	}

	// return bag price and return 0 if bag less is selected
	public double getBagPrice() {
		double AD_Bag_Price = 0;
		String AD_Bag_Opt_Section_Cost = driver.findElement(By.xpath(OR_OR.getProperty("AD_Bag_Opt_Section_Cost")))
				.getText();
		if (isElementPresent(OR_OR.getProperty("AD_Bag_Opt_Section_Cost"), "AD_Bag_Opt_Section_Cost")
				&& !(AD_Bag_Opt_Section_Cost.equals("Free"))) {
			scrollTo_Xpath(OR_OR.getProperty("AD_Bag_Opt_Section_Cost"), "AD_Bag_Opt_Section_Cost", 1);
			String bag_price = get_xpath_text(OR_OR.getProperty("AD_Bag_Opt_Section_Cost"), "AD_Bag_Opt_Section_Cost");
			bag_price = bag_price.replaceAll("[^0-9,.]", "").trim();
			AD_Bag_Price = Double.parseDouble(bag_price);
			testLog.info("bag_price: " + AD_Bag_Price);
		} else {
			AD_Bag_Price = 0.00;
			testLog.info("bag_price: " + AD_Bag_Price);
		}
		return AD_Bag_Price;
	}

	public double getTrolleyTotal_BagPrice() {
		double Bag_Price = 0;
		if (isElementPresent(OR_OR.getProperty("Trolley_Total_Bag_Price"), "Trolley_Total_Bag_Price")) {
			scrollTo_Xpath(OR_OR.getProperty("Trolley_Total_Bag_Price"), "Trolley_Total_Bag_Price", 1);
			String AD_Bag_Price = driver.findElement(By.xpath(OR_OR.getProperty("Trolley_Total_Bag_Price"))).getText();
			AD_Bag_Price = AD_Bag_Price.replaceAll("[^0-9,.]", "").trim();
			Bag_Price = Double.parseDouble(AD_Bag_Price);
			testLog.info("bag_price: " + Bag_Price);
		} else {
			Bag_Price = 0.00;
			testLog.info("bag_price: " + Bag_Price);
		}

		return Bag_Price;

	}

	// return delivery plus discount amount if that promo is applied else return
	// 0
	public double getDeliveryPlusPromoDiscount() {
		wait(5000);
		double AD_DeliveryPlus_Discount = 0.00;
		verify_xpath_text(OR_OR.getProperty("AD_Section_Credit_Coupon_Heading"),
				OR_OR.getProperty("AD_Section_Credit_Coupon_Heading_Text"));
		// if (isElementPresent(OR_OR.getProperty("AD_Coupon_Discount"),
		// "AD_Coupon_Discount")) {

		By getChanges = By.xpath(OR_OR.getProperty("AD_Credit_Coupon_Count"));
		java.util.List<WebElement> located_elements = driver.findElements(getChanges);

		for (int i = 1; i <= located_elements.size(); i++) {
			String promo_xpath = OR_OR.getProperty("AD_Credit_Coupon_Text_nvalue");
			String credit_xpath = OR_OR.getProperty("AD_Credit_Coupon_Deduction_nvalue");
			promo_xpath = promo_xpath.replaceAll("nvalue", Integer.toString(i));
			credit_xpath = credit_xpath.replaceAll("nvalue", Integer.toString(i));

			if (isElementPresent(promo_xpath, "AD_Credit_Coupon_Text_nvalue")
					&& isElementPresent(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue")) {
				testLog.info("Promo and Deductions are displayed.");
				String promo_str = get_xpath_text(promo_xpath, "AD_Credit_Coupon_Text_nvalue");

				if (promo_str.contains(OR_OR.getProperty("AD_Credit&Promos_DeliveryPlusHeading"))) {
					String credit_str = get_xpath_text(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue");
					credit_str = credit_str.replaceAll("[^0-9,.]", "");
					AD_DeliveryPlus_Discount = Double.parseDouble(credit_str);
					testLog.info("Promo Text: \"" + promo_str + "\" Promo Deduction Value: \""
							+ AD_DeliveryPlus_Discount + "\"");
				}
			}
		}

		// }
		return AD_DeliveryPlus_Discount;
	}

	// Return total amount of all promo codes applied on Almost done screen
	public double getTotalPromoDiscount() {
		double AD_total_promo_discount = 0.0;
		verify_xpath_text(OR_OR.getProperty("AD_Section_Credit_Coupon_Heading"),
				OR_OR.getProperty("AD_Section_Credit_Coupon_Heading_Text"));
		// if (isElementPresent(OR_OR.getProperty("AD_Coupon_Discount"),
		// "AD_Coupon_Discount")) {

		By getChanges = By.xpath(OR_OR.getProperty("AD_Credit_Coupon_Count"));
		java.util.List<WebElement> located_elements = driver.findElements(getChanges);

		int i = 0;
		for (i = 1; i <= located_elements.size(); i++) {
			String promo_xpath = OR_OR.getProperty("AD_Credit_Coupon_Text_nvalue");
			String credit_xpath = OR_OR.getProperty("AD_Credit_Coupon_Deduction_nvalue");
			promo_xpath = promo_xpath.replaceAll("nvalue", Integer.toString(i));
			credit_xpath = credit_xpath.replaceAll("nvalue", Integer.toString(i));

			if (isElementPresent(promo_xpath, "AD_Credit_Coupon_Text_nvalue")
					&& isElementPresent(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue")) {
				testLog.info("Promo and Deductions are displayed.");
				String promo_str = get_xpath_text(promo_xpath, "AD_Credit_Coupon_Text_nvalue");
				String credit_str = get_xpath_text(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue");
				testLog.info("Promo Text: \"" + promo_str + "\" Promo Deduction Value: \"" + credit_str + "\"");

				credit_str = credit_str.replaceAll("[^0-9,.]", "");
				AD_total_promo_discount = AD_total_promo_discount + Double.parseDouble(credit_str);

			} else {
				testLog.error("Either Coupon Text:" + promo_xpath + " or Coupon discount:" + credit_xpath
						+ " is not displayed.");
				assertCheck("getTotalPromoDiscount",
						"Either Coupon Text:\" +promo_xpath+\" or Coupon discount:\"+ credit_xpath+\" is not displayed.");
			}
		}
		// }
		return AD_total_promo_discount;
	}

	// verify if promotion is applied successfully
	public AlmostDonePage verifySubscriptionisApplied() {

		double AD_Bag_Price = 0.0;
		double AD_Slot_Price = 0.0;
		double AD_Total_Price;
		double AD_DeliveryPlus_Promo_Discount = 0.0;
		double AD_Total_Promo_Discount = 0.0;

		double trolley_amount = 0;

		// get cart price
		trolley_amount = getTrolleyAmount();
		// get total amount
		AD_Total_Price = getTotalAmount();
		// get Time slot price
		AD_Slot_Price = getTimeSlotPrice();
		// get bag price
		AD_Bag_Price = getTrolleyTotal_BagPrice();

		AD_DeliveryPlus_Promo_Discount = getDeliveryPlusPromoDiscount();
		AD_Total_Promo_Discount = getTotalPromoDiscount();

		double FinalTotal = (trolley_amount + AD_Bag_Price + AD_Slot_Price) - AD_Total_Promo_Discount;
		FinalTotal = Math.round(FinalTotal * 100.0) / 100.0;

		// Delivery Plus promo discount must be more than 0.
		if (AD_DeliveryPlus_Promo_Discount == 0.00) {
			testLog.error("Promo and/or Deductions are not displayed.");
			assertCheck("verifySubscription", "Subscription promo code is not visible in promotion section");
		} else {
			testLog.info("Delivery subscription plus discount is visible in promo section. Delivery plus discount is :"

					+ AD_DeliveryPlus_Promo_Discount);
		}

		// Delivery Plus promo discount must be equal delivery charges/slot
		// price
		if (AD_DeliveryPlus_Promo_Discount == AD_Slot_Price) {
			testLog.info(
					"Delivery subscription is applied successfully. Subscription discount is equal to the Delivery Charges");
		} else {
			testLog.error(
					"Delivery subscription is not applied successfully. Delivery charges do not match the discount price");
			assertCheck("verifySubscription",
					"Delivery subscription is not applied successfully. Delivery charges do not match the discount price");
		}

		// check if trolley amount is properly calculated and does not include
		// delivery
		// charges
		if (AD_Total_Price == FinalTotal) {
			testLog.info("Total order amount is correctly calculated and does not include delivery charges");
			testLog.info("Total_price to be paid by user:" + AD_Total_Price);
		} else {
			testLog.error(
					"Delivery subscription is applied successfully in promo section but Total order amount is not calculated properly.");
			assertCheck("verifySubscription",
					"Delivery subscription is applied successfully in promo section but Total order amount is not calculated properly.");
		}
		return this;
	}

	// verify cases where promotion should not be applied
	public AlmostDonePage verifySubscriptionisNotApplied() {
		double AD_Bag_Price = 0.0;
		double AD_Slot_Price = 0.0;
		double AD_Total_Price;
		double AD_DeliveryPlus_Promo_Discount = 0.0;
		double AD_Total_Promo_Discount = 0.0;
		double trolley_amount = 0;

		// get cart price
		trolley_amount = getTrolleyAmount();
		// get total amount
		AD_Total_Price = getTotalAmount();
		// get Time slot price
		AD_Slot_Price = getTimeSlotPrice();
		// get bag price
		AD_Bag_Price = getTrolleyTotal_BagPrice();

		AD_DeliveryPlus_Promo_Discount = getDeliveryPlusPromoDiscount();
		AD_Total_Promo_Discount = getTotalPromoDiscount();

		double ActualTotal = (trolley_amount + AD_Bag_Price + AD_Slot_Price) - AD_Total_Promo_Discount;
		ActualTotal = Math.round(ActualTotal * 100.0) / 100.0;

		testLog.info("Trolley amount is:" + trolley_amount);

		// Delivery Plus promo discount must be more than 0.
		if (AD_DeliveryPlus_Promo_Discount != 0.00) {
			testLog.error("Delievry Plus Promo is visible. Discount applied is:" + AD_DeliveryPlus_Promo_Discount);
			assertCheck("verifySubscription", "Subscription promo code is visible in promotion section");
		} else {
			testLog.info(
					"Delivery subscription plus discount is not visible in promo section. Delivery plus discount is :"
							+ AD_DeliveryPlus_Promo_Discount);
		}

		// check if trolley amount is properly calculated and includes delivery
		// charges
		if (AD_Total_Price == ActualTotal) {
			testLog.info("Total order amount is correctly calculated and includes delivery charges");
			testLog.info("Total_price to be paid by user:" + AD_Total_Price);
		} else {
			testLog.error("Total order amount is invalid and does not include delvery charges");
			assertCheck("verifySubscription", "Total order amount is invalid and does not include delvery charges");
		}
		return this;
	}

	public HomePage clickKeepShopping() {
		if (isElementPresent(OR_OR.getProperty("Keep_Shopping_Link"), "Keep_Shopping_Link")) {
			Click_Button_Xpath(OR_OR.getProperty("Keep_Shopping_Link"), "Keep_Shopping_Link");
		} else {
			testLog.error("Keep shopping link is NOT present at Almost done..page");
			assertCheck("clickKeepShopping", "Keep shopping link is NOT present at Almost done..page");
		}
		ExplicitWait(OR_OR.getProperty("Everything_Tab"), "Everything_Tab", 20);

		return new HomePage();
	}

	public String getOrderId() {
		wait(8000);
		verify_xpath_contains_text(OR_OR.getProperty("Done_Order_No_Text"), "Your order number:");
		String order_id = get_xpath_text(OR_OR.getProperty("Done_Order_No_Text"), "Done_Order_No_Text");
		order_id = order_id.substring(order_id.length() - 9, order_id.length()).trim();
		testLog.info("order_id is : " + order_id);

		return order_id;
	}

	public AlmostDonePage validateDeliveryTypeADPage(String deliveryType) {
		wait(3000);
		verify_xpath_text(OR_OR.getProperty("Delivery_Type_Heading"), OR_OR.getProperty("Delivery_Type_Heading_Text"));
		if (deliveryType.equals("signed")) {
			verify_xpath_text(OR_OR.getProperty("Delivery_Type"),
					OR_OR.getProperty("Delivery_Type_Signed_Delivery_Text"));
		}
		if (deliveryType.equals("unattended")) {
			verify_xpath_contains_text(OR_OR.getProperty("Delivery_Type"),
					OR_OR.getProperty("Delivery_Type_Unattended_Delivery_Text"));
		}
		return this;
	}

	public AlmostDonePage validateDeliveryInstructionADPage(String dIMessage) {
		scrollTo_Xpath(OR_OR.getProperty("Delivery_Instruction_Heading_SPC"), "Delivery_Instruction_Heading_SPC", 2);
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Heading_SPC"),
				OR_OR.getProperty("Delivery_Instruction_Heading_SPC_Text"));
		verify_xpath_text(OR_OR.getProperty("Saved_Delivery_Instructions"), dIMessage);
		return this;
	}

	public AlmostDonePage validateRDDeliveryInstructionADPage(String dIMessage) {
		scrollTo_Xpath(OR_OR.getProperty("Delivery_Instruction_Heading_SPC"), "Delivery_Instruction_Heading_SPC", 2);
		verify_xpath_text(OR_OR.getProperty("Delivery_Instruction_Heading_SPC"),
				OR_OR.getProperty("RDDelivery_Instruction_Heading_SPC_Text"));
		verify_xpath_text(OR_OR.getProperty("Saved_RDDelivery_Instructions"), dIMessage);
		return this;
	}

	public AlmostDonePage clickChangeDeliveryInstruction() {
		scrollTo_Xpath(OR_OR.getProperty("Change_Delivery_Instruction_Link"), "Change_Delivery_Instruction_Link", 2);
		Click_Button_Xpath(OR_OR.getProperty("Change_Delivery_Instruction_Link"), "Change_Delivery_Instruction_Link");
		wait(3000);
		return this;
	}

	public AlmostDonePage validateRemovedDeliveryInstructionADPage() {
		wait(5000);
		if (isElementPresent(OR_OR.getProperty("Add_Delivery_Instructions"), "Add_Delivery_Instructions")) {
			testLog.info("Delivery Instruction is removed");
		} else {
			testLog.error("Delivery Instruction is not removed");
			assertCheck("validateRemovedDeliveryInstructionADPage", "Delivery Instruction is not removed");
		}
		return this;
	}

	public HomePage clickKeepShoppingLink() {
		Click_Button_Xpath(OR_OR.getProperty("Cancel_And_Keep_Shopping_Link"), "Cancel_And_Keep_Shopping_Link");
		ExplicitWait(OR_OR.getProperty("Home_SearchField_InsideText_Path"), "Home_SearchField_InsideText_Path", 30);
		return new HomePage();
	}

	public AlmostDonePage validateTotalAmountWithBag() {
		wait(10000);
		verify_xpath_text(OR_OR.getProperty("Bagging_Heading"), OR_OR.getProperty("Bagging_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Bagging_Subheading"),
				OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries"));
		verify_xpath_contains_text(OR_OR.getProperty("Bagging_Subheading_Desc"),
				OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries_Desc_Text"));
		return this;
	}

	public AlmostDonePage validateTotalAmountWithBagless(double bagPrice, double totalAmt) {
		verify_xpath_text(OR_OR.getProperty("Bagging_Heading"), OR_OR.getProperty("Bagging_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Bagging_Subheading"), OR_OR.getProperty("Bagging_Subheading_No_Bag"));
		verify_xpath_text(OR_OR.getProperty("Bagging_Subheading_Desc"),
				OR_OR.getProperty("Bagging_Subheading_No_Bag_Desc_Text"));
		double baglessPrice = getBagPrice();
		double baglessTotalAmt = getTotalAmount();
		double estimated_output = totalAmt - bagPrice;
		testLog.info("Bag on my groceries - bag price is: " + bagPrice);
		testLog.info("Bag on my groceries - total amount is: " + totalAmt);
		testLog.info("No Bag - bag less price is: " + baglessPrice);
		testLog.info("No Bag - total amount is: " + baglessTotalAmt);
		testLog.info("Estimated total amount is: " + estimated_output);
		if (estimated_output == baglessTotalAmt && baglessPrice == 0.00) {
			testLog.info("Order Total has been adjusted accordingly.");
		} else {
			testLog.error("Order Total has not been adjusted accordingly.");
			assertCheck("validateTotalAmountWithBagless", "Order Total has not been adjusted accordingly.");
		}
		return this;
	}

	public AlmostDonePage changeToNoBag() {
		Click_Button_Xpath(OR_OR.getProperty("Change_Bagging_Option"), "Change_Bagging_Option");
		ExplicitWait(OR_OR.getProperty("Bagging_Option_Heading"), "Bagging_Option_Heading", 25);
		boolean bagless_rb = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button"), "No_Bags_Radio_Button");
		boolean bagless_rb_selected = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button_Selected"),
				"No_Bags_Radio_Button_Selected");
		if (bagless_rb_selected) {
			Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
			wait(15000);
		}
		if (bagless_rb) {
			testLog.info("selecting NO BAGS OPTION");
			Click_Button_Xpath(OR_OR.getProperty("No_Bags_Radio_Button"), "No_Bags_Radio_Button");
			Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
			wait(15000);
		} else {
			testLog.error("Not able to select NO BAGS OPTION");
			assertCheck("changeToNoBag", "Not able to select NO BAGS OPTION");
		}
		return this;
	}

	public AlmostDonePage changeToBagMyGroceries() {
		ExplicitWait(OR_OR.getProperty("Bagging_Option_Heading"), "Bagging_Option_Heading", 25);
		boolean bag_rb = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button"),
				"Bag_My_Groceries_Radio_Button");
		boolean bag_rb_selected = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button_Selected"),
				"Bag_My_Groceries_Radio_Button_Selected");
		if (bag_rb_selected) {
			Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
			wait(15000);
		}
		if (bag_rb) {
			testLog.info("selecting BAG MY GROCERIES OPTION");
			Click_Button_Xpath(OR_OR.getProperty("Bag_My_Groceries_Radio_Button"), "Bag_My_Groceries_Radio_Button");
			Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
			wait(15000);
		} else {
			testLog.error("Not able to select BAG MY GROCERIES OPTION");
			assertCheck("changeToBagMyGroceries", "Not able to select BAG MY GROCERIES OPTION");
		}
		return this;
	}

	public AlmostDonePage validateSelectedBagOption(String bag_option) {
		verify_xpath_text(OR_OR.getProperty("Bagging_Heading"), OR_OR.getProperty("Bagging_Heading_Text"));
		switch (bag_option.toUpperCase()) {
		case "BAG":
			verify_xpath_text(OR_OR.getProperty("Bagging_Subheading"),
					OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries"));
			verify_xpath_text(OR_OR.getProperty("Bagging_Subheading_Desc"),
					OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries_Desc_Text"));
			// verify_xpath_contains_text(OR_OR.getProperty("AD_Bag_Opt_Section_Bagged_Opt_SubHeading_Desc2"),
			// "Approx. 1 bag at $0.15 per bag");
			break;
		case "BAGLESS":
			verify_xpath_text(OR_OR.getProperty("Bagging_Subheading"), "No Bags");
			verify_xpath_text(OR_OR.getProperty("Bagging_Subheading_Desc"),
					OR_OR.getProperty("Bagging_Subheading_No_Bag_Desc_Text"));
			break;
		}

		return this;
	}

	public AlmostDonePage clickChangeBaggingOptionLink() {
		Click_Button_Xpath(OR_OR.getProperty("Change_Bagging_Option"), "Change_Bagging_Option");
		ExplicitWait(OR_OR.getProperty("Bagging_Option_Heading"), "Bagging_Option_Heading", 25);
		return this;
	}

	public AlmostDonePage validateCCBaggingPreference(String bag_option) {

		boolean bagless_rb_selected = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button_Selected"),
				"No_Bags_Radio_Button_Selected");
		boolean bag_rb_selected = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button_Selected"),
				"Bag_My_Groceries_Radio_Button_Selected");
		switch (bag_option.toUpperCase()) {
		case "BAG":
			if (bag_rb_selected) {
				testLog.info("As expected Bag my grocerries is selected already.");
			} else {
				testLog.error("As per user preference Bag my grocerries should come as selected");
				assertCheck("validateCCBaggingPreference",
						"As per user preference Bag my grocerries should come as selected");
			}
			Click_Button_Xpath(OR_OR.getProperty("Close_Bagging_Option"), "Close_Bagging_Option");
			break;
		case "BAGLESS":
			if (bagless_rb_selected) {
				testLog.info("As expected N0 Bag is selected already.");
			} else {
				testLog.error("As per user preference No Bag should come as selected");
				assertCheck("validateCCBaggingPreference", "As per user preference No Bag should come as selected");
			}
			Click_Button_Xpath(OR_OR.getProperty("Close_Bagging_Option"), "Close_Bagging_Option");
			break;
		}
		return this;
	}

	public AlmostDonePage clickRememberMyPreference() {
		Click_Button_Xpath(OR_OR.getProperty("Remember_My_Preference"), "Remember_My_Preference");
		Click_Button_Xpath(OR_OR.getProperty("Save_Bagging_OPtion"), "Save_Bagging_OPtion");
		wait(15000);
		return this;
	}

	public TrolleyAndCheckoutPage clickModifyTrolley() {
		Click_Button_Xpath(OR_OR.getProperty("AD_View_Trolley_Items_Sidebar"), "AD_View_Trolley_Items_Sidebar");
		return new TrolleyAndCheckoutPage();
	}

	public TrolleyAndCheckoutPage clickViewTrolleyLink() {
		Click_Button_Xpath(OR_OR.getProperty("AD_View_Items"), "AD_View_Items");
		return new TrolleyAndCheckoutPage();
	}

	public DeliverySlotPage clickDeliverySlotChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("AD_Slot_Change_Link"), "AD_Slot_Change_Link");
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickDeliveryAddressChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("AD_HDAddress_ChangeLink"), "AD_HDAddress_ChangeLink");
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickDeliveryTypeChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"), "AD_Delivery_Type_ChangeLink");
		// ExplicitWait(OR_OR.getProperty("DSS_Overlay_SubHeading"),
		// "DSS_Overlay_SubHeading", 10);
		wait(5000);
		isElementPresent(OR_OR.getProperty("DSS_Customer_Choice_Window_Unattended_RadioBtn"),
				"DSS_Customer_Choice_Window_Unattended_RadioBtn");
		isElementPresent(OR_OR.getProperty("DSS_Overlay_Continue_Button"), "DSS_Overlay_Continue_Button");
		return new DeliverySlotPage();
	}

	public DeliverySlotPage clickDeliveryInstructionChangeLink() {
		Click_Button_Xpath(OR_OR.getProperty("Change_Delivery_Instruction_Link"), "Change_Delivery_Instruction_Link");
		return new DeliverySlotPage();
	}

	public PaymentPage clickChangePayment() {
		ExplicitWait(OR_OR.getProperty("Checkout_Page_Payment_Change_Link"), "Checkout_Page_Payment_Change_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("Checkout_Page_Payment_Change_Link"), "Checkout_Page_Payment_Change_Link");
		wait(8000);
		return new PaymentPage();
	}

	public String getSlotTime() {
		return (get_xpath_text(OR_OR.getProperty("AD_Slot_Time"), "AD_Slot_Time"));
	}

	public String getSlotDate() {
		return (get_xpath_text(OR_OR.getProperty("AD_Slot_Date"), "AD_Slot_Date"));
	}

	public String getDeliveryAddress() {

		return (get_xpath_text(OR_OR.getProperty("AD_HDAddress_Full"), "AD_HDAddress_Full"));
	}

	public String getDeliveryType() {

		ExplicitWait(OR_OR.getProperty("AD_Delivery_Type"), "AD_Delivery_Type", 10);
		return (get_xpath_text(OR_OR.getProperty("AD_Delivery_Type"), "AD_Delivery_Type"));
	}

	public AlmostDonePage clickAddPromoCodeLink() {
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Add_Promo_Code_Button"), "AlmostDonePage_Add_Promo_Code_Button",
				10);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Add_Promo_Code_Button"),
				"AlmostDonePage_Add_Promo_Code_Button");
		return this;
	}

	public AlmostDonePage enterPromoCode(String promocode) {
		Sendkey_xpath(OR_OR.getProperty("AlmostDonePage_Promo_Code_Input_Field"), promocode,
				"AlmostDonePage_Promo_Code_Input_Field");
		return this;
	}

	public AlmostDonePage clickApplyPromoCodeButton() {
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Apply_Button"), "AlmostDonePage_Apply_Button");
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Add_Promo_Code_Button"), "AlmostDonePage_Add_Promo_Code_Button",
				20);
		return this;
	}

	public double getPromoCodeDiscountAmount(String promocode_name) {
		double promo_amount = 0.0;
		By getChanges = By.xpath(OR_OR.getProperty("AD_Credit_Coupon_Count"));
		java.util.List<WebElement> located_elements = driver.findElements(getChanges);

		int i = 0;
		for (i = 1; i <= located_elements.size(); i++) {
			String promo_xpath = OR_OR.getProperty("AD_Credit_Coupon_Text_nvalue");
			String credit_xpath = OR_OR.getProperty("AD_Credit_Coupon_Deduction_nvalue");
			promo_xpath = promo_xpath.replaceAll("nvalue", Integer.toString(i));
			credit_xpath = credit_xpath.replaceAll("nvalue", Integer.toString(i));

			if (isElementPresent(promo_xpath, "AD_Credit_Coupon_Text_nvalue")
					&& isElementPresent(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue")) {
				testLog.info("Promo and Deductions are displayed.");
				String promo_str = get_xpath_text(promo_xpath, "AD_Credit_Coupon_Text_nvalue");

				if (promo_str.equals(promocode_name)) {
					String amount = get_xpath_text(credit_xpath, "AD_Credit_Coupon_Deduction_nvalue");
					amount = amount.replaceAll("[^0-9,.]", "");
					promo_amount = Double.parseDouble(amount);
					break;
				}

				testLog.info("Promo Text: \"" + promo_str + "\" Promo Deduction Value: \"" + promo_amount + "\"");

			} else {
				testLog.error("Promo code:" + promocode_name + " is not displayed");
				assertCheck("getPromoCodeDiscountAmount", "Promo code:" + promocode_name + " is not displayed");
			}
		}

		return promo_amount;
	}

	public AlmostDonePage applyInvalidPromoCode_CheckErrorMessage() {
		String[] sku_list = test_data.split(",");
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Add_Promo_Code_Button"), "AlmostDonePage_Add_Promo_Code_Button",
				10);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Add_Promo_Code_Button"),
				"AlmostDonePage_Add_Promo_Code_Button");
		for (int i = 0; i < sku_list.length; i++) {
			Clear_Text(OR_OR.getProperty("AlmostDonePage_Promo_Code_Input_Field"),
					"AlmostDonePage_Promo_Code_Input_Field");
			Sendkey_xpath(OR_OR.getProperty("AlmostDonePage_Promo_Code_Input_Field"), sku_list[i],
					"AlmostDonePage_Promo_Code_Input_Field");
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Apply_Button"), "AlmostDonePage_Apply_Button");
			validatePromoCodeMessage();
		}
		return this;
	}

	public AlmostDonePage validatePromoCodeMessage() {
		verify_xpath_text(OR_OR.getProperty("AlmostDonePage_Promo_Code_Error_Msg"),
				OR_OR.getProperty("AlmostDonePage_Promo_Code_Error_Msg_Text"));
		return this;
	}

	public AlmostDonePage validateUsedPromoCodeMessage(String promoCode) {
		verify_xpath_text(OR_OR.getProperty("AlmostDonePage_Used_Promo_Code_Error_Lbl"), "Promo code already in use");
		verify_xpath_text(OR_OR.getProperty("AlmostDonePage_Used_Promo_Code_Error_Msg"),
				"The promo code" + promoCode + "has already been applied");
		return this;
	}

	public AlmostDonePage validateUDNotAbleToChangeBagging() {
		boolean bagless_rb = isElementPresent(OR_OR.getProperty("No_Bags_Radio_Button"), "No_Bags_Radio_Button");
		boolean bag_rb_selected = isElementPresent(OR_OR.getProperty("Bag_My_Groceries_Radio_Button_Selected"),
				"Bag_My_Groceries_Radio_Button_Selected");
		if (bag_rb_selected) {
			if (!bagless_rb) {
				testLog.info(
						"Bag My Groceries option is selected and No Bag option is not present, so user not able to change Bagging option");
			} else {
				testLog.error("Page shoud not display No Bag option");
				assertCheck("validateNotAbleToChangeBagging", "Page shoud not display No Bag option");
			}
		}
		// Click_Button_Xpath(OR_OR.getProperty("Close_Bagging_Option"),
		// "Close_Bagging_Option");
		return this;
	}

	public AlmostDonePage validatePaymentMethod(String paymentMode) {
		ExplicitWait(OR_OR.getProperty("Checkout_Page_Payment_Mothod"), "Checkout_Page_Payment_Mothod", 20);
		verify_xpath_text(OR_OR.getProperty("Checkout_Page_Payment_Mothod"), paymentMode);
		return this;
	}

	public AlmostDonePage clickChangeSubstitution() {
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Heading"),
				OR_OR.getProperty("ADP_substitution_Heading_Text"));
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Change_Link"), "ADP_substitution_Change_Link");
		return this;
	}

	public AlmostDonePage validateADPSubstitutionPage() {
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Page_Heading"),
				OR_OR.getProperty("ADP_substitution_Page_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Happy_For_Coles_Choose"),
				OR_OR.getProperty("ADP_substitution_Happy_For_Coles_Choose_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Dont_Choose"),
				OR_OR.getProperty("ADP_substitution_Dont_Choose_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Depends_On_Product"),
				OR_OR.getProperty("ADP_substitution_Depends_On_Product_Text"));
		return this;
	}

	public AlmostDonePage saveColeschooseOption() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Happy_For_Coles_Choose_Checkbox"),
				"ADP_substitution_Happy_For_Coles_Choose_Checkbox");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Save_And_Continue"),
				"ADP_substitution_Save_And_Continue");
		ExplicitWait(OR_OR.getProperty("ADP_substitution_Change_Link"), "ADP_substitution_Change_Link", 20);
		return this;
	}

	public AlmostDonePage saveDontchooseOption() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Dont_Choose_Checkbox"),
				"ADP_substitution_Dont_Choose_Checkbox");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Save_And_Continue"),
				"ADP_substitution_Save_And_Continue");
		ExplicitWait(OR_OR.getProperty("ADP_substitution_Change_Link"), "ADP_substitution_Change_Link", 20);
		return this;
	}

	public AlmostDonePage saveLetMechooseOption() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Depends_On_Product_Checkbox"),
				"ADP_substitution_Depends_On_Product_Checkbox");
		ExplicitWait(OR_OR.getProperty("ADP_substitution_Specific_Products"), "ADP_substitution_Specific_Products", 20);
		verify_xpath_text(OR_OR.getProperty("ADP_substitution_Specific_Products"),
				OR_OR.getProperty("ADP_substitution_Specific_Products_Text"));
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_None_Button"), "ADP_substitution_None_Button");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_All_Button"), "ADP_substitution_All_Button");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitution_Save_And_Continue"),
				"ADP_substitution_Save_And_Continue");
		ExplicitWait(OR_OR.getProperty("ADP_substitution_Change_Link"), "ADP_substitution_Change_Link", 20);
		return this;
	}

	public AlmostDonePage validateFlybuysText() {
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Heading"), OR_OR.getProperty("ADP_Flybuys_Heading_Text"));
		// no such link in new singlepagecheckout
		// verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Info"),
		// OR_OR.getProperty("ADP_Flybuys_Info_Text"));
		return this;
	}

	public AlmostDonePage clickChangeFlybuysLink() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_Add_Flybuys_Number_Link"), "ADP_Add_Flybuys_Number_Link");
		return this;
	}

	public AlmostDonePage validateADPFlybuysPage() {
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Page_Heading"),
				OR_OR.getProperty("ADP_Flybuys_Page_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Page_Sub_Heading"),
				OR_OR.getProperty("ADP_Flybuys_Page_Sub_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Page_Sub1_Heading"),
				OR_OR.getProperty("ADP_Flybuys_Page_Sub1_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Page_Footer"),
				OR_OR.getProperty("ADP_Flybuys_Page_Footer_Text"));
		return this;
	}

	public AlmostDonePage clickSaveFlybuys() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_Flybuys_Save_Button"), "ADP_Flybuys_Save_Button");
		wait(10000);
		return this;
	}

	public AlmostDonePage validateSavedFlybuysADP() {
		String flybuysNum = get_xpath_text(OR_OR.getProperty("ADP_Your_Flybuys_Number"), "ADP_Your_Flybuys_Number");
		String flybuysNumText = "6008 94 ●● ●●●● 5217 your Flybuys number starts with 6 0 0 8 9 4 and ends with 5 2 1 7";
		// String flybuysNumText = "2792001095212";
		testLog.info(flybuysNum);
		if (flybuysNum.equals(flybuysNumText)) {
			testLog.info("Saved Flybuys number displaying as expected");
		} else {
			testLog.error("Saved Flybuys number in not displaying as expected");
			assertCheck("validateSavedFlybuysADP", "Saved Flybuys number in not displaying as expected");
		}

		return this;
	}

	public AlmostDonePage validateErrorMsgFlybuysADP() {
		verify_xpath_text(OR_OR.getProperty("ADP_Flybuys_Error_Msg"), OR_OR.getProperty("ADP_Flybuys_Error_Msg_Text"));
		return this;
	}

	public AlmostDonePage clickCancelFlybuysLink() {
		Click_Button_Xpath(OR_OR.getProperty("ADP_Flybuys_Cancel_Link"), "ADP_Flybuys_Cancel_Link");
		wait(3000);
		return this;
	}

	public AlmostDonePage validateAtCheckoutPage() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Single_Page_Chackout_Heading"), "Single_Page_Chackout_Heading", 25);
		boolean flag = isElementPresent(OR_OR.getProperty("Checkout_Page"), "Checkout_Page");
		verify_xpath_contains_text(OR_OR.getProperty("Single_Page_Chackout_Heading"),
				OR_OR.getProperty("Single_Page_Chackout_Heading_Text"));
		if (flag == true) {
			testLog.info("User redirect at checkout/almost done page after entered the card details");
		} else {
			testLog.error("Opps!! Something went wrong during payment with credit card");
			assertCheck("validateAtCheckoutPage", "Opps!! Something went wrong during payment with credit card");
		}
		return this;
	}

	public AlmostDonePage validateCardDetails_CheckoutPage() {
		String svCard = get_xpath_text(OR_OR.getProperty("Checkout_Page_Saved_Card_Number"),
				"Checkout_Page_Saved_Card_Number");
		String savedCardEndDigit = svCard.substring(svCard.length() - 4);
		testLog.info("Saved end digit are: " + savedCardEndDigit);
		String cardEndDigit = cardno.substring(cardno.length() - 4);
		testLog.info("Expected card end digit are:" + cardEndDigit);
		if (cardEndDigit.contains(savedCardEndDigit)) {
			testLog.info("Saved card end digit are showing as expected card end digit at checkout page");
		} else {
			testLog.info("Saved card end digit are NOT showing as expected card end digit at checkout page");
			assertCheck("validateCardDetails_CheckoutPage",
					"Saved card end digit are NOT showing as expected card end digit at checkout page");
		}
		return this;
	}

	public AlmostDonePage clickPayUsingCard() {
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Credit_Card_Button"), "AlmostDonePage_Credit_Card_Button", 20);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Credit_Card_Button"), "AlmostDonePage_Credit_Card_Button");
		return this;
	}

	public PaymentPage clickAddNewCard() {
		wait(3000);
		if (isElementPresent(OR_OR.getProperty("AlmostDonePage_Card_Different_Link"),
				"AlmostDonePage_Card_Different_Link")) {
			scrollTo_Xpath(OR_OR.getProperty("AlmostDonePage_Card_Different_Link"),
					"AlmostDonePage_Card_Different_Link", 2);
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Card_Different_Link"),
					"AlmostDonePage_Card_Different_Link");
		} else {
			ExplicitWait(OR_OR.getProperty("AlmostDonePage_Add_New_Card"), "AlmostDonePage_Add_New_Card", 20);
			scrollTo_Xpath(OR_OR.getProperty("AlmostDonePage_Add_New_Card"), "AlmostDonePage_Add_New_Card", 2);
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Add_New_Card"), "AlmostDonePage_Add_New_Card");
		}
		// verify_xpath_text(OR_OR.getProperty("Checkout_Flow_IPG_Header"),
		// "Enter new
		// credit / debit card details");
		switch_Frame("IPG_iFrame_id");
		return new PaymentPage();
	}

	public AlmostDonePage clickPayUsingPayPal() {
		wait(3000);
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_PayPal_Button"), "AlmostDonePage_PayPal_Button", 20);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_PayPal_Button"), "AlmostDonePage_PayPal_Button");
		return this;
	}

	public AlmostDonePage clickPayUsingFlypay() {
		wait(3000);
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Flypay_Button"), "AlmostDonePage_Flypay_Button", 20);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Flypay_Button"), "AlmostDonePage_Flypay_Button");
		return this;
	}

	public PaymentPage clickAtPaypalAccount() {
		wait(4000);
		boolean payPalDiffernt_Link = isElementPresent(OR_OR.getProperty("AlmostDonePage_PayPal_Different_Link"),
				"AlmostDonePage_PayPal_Different_Link");
		/*
		 * boolean linkAccount_Button = isElementPresent(OR_OR.getProperty(
		 * "AlmostDonePage_PayPal_Link_Account_Button"),
		 * "AlmostDonePage_PayPal_Link_Account_Button");
		 */
		if (payPalDiffernt_Link == true) {
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_PayPal_Different_Link"),
					"AlmostDonePage_PayPal_Different_Link");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
			}
			wait(5000);
		} else {
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_PayPal_Link_Account_Button"),
					"AlmostDonePage_PayPal_Link_Account_Button");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
			}
			wait(5000);
		}
		return new PaymentPage();
	}

	public PaymentPage clickAtFlypayAccount() {
		wait(4000);
		boolean flyPay_Button = isElementPresent(OR_OR.getProperty("AlmostDonePage_Flypay_Link_Account_Button"),
				"AlmostDonePage_Flypay_Link_Account_Button");

		if (flyPay_Button == true) {
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Flypay_Link_Account_Button"),
					"AlmostDonePage_Flypay_Link_Account_Button");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
				System.out.println("Winhandel Value: " + winHandle);
			}
			wait(5000);
		} else {
			Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Flypay_Link_Account_Button"),
					"AlmostDonePage_Flypay_Link_Account_Button");
			// switch window
			wait(5000);
			winHandle = driver.getWindowHandle();
			for (String newWin : driver.getWindowHandles()) {
				driver.switchTo().window(newWin);
			}
			wait(5000);
		}
		return new PaymentPage();
	}

	public AlmostDonePage validateYourTrolleyItems(int superbarItemCount) {
		wait(3000);
		int totalProductsqty = 0;
		ExplicitWait(OR_OR.getProperty("SinglePageCheckout_Order_Summary"), "SinglePageCheckout_Order_Summary", 20);
		verify_xpath_text(OR_OR.getProperty("SinglePageCheckout_Order_Summary"),
				OR_OR.getProperty("SinglePageCheckout_Order_Summary_Text"));
		verify_xpath_contains_text(OR_OR.getProperty("SinglePageCheckout_Your_Trolley"),
				OR_OR.getProperty("SinglePageCheckout_Your_Trolley_Text"));
		String trolleyItems = get_xpath_text(OR_OR.getProperty("SinglePageCheckout_Trolley_Items"),
				"SinglePageCheckout_Trolley_Items");
		String items = trolleyItems.substring(0, trolleyItems.indexOf(" "));
		int totalItems = Integer.parseInt(items);
		testLog.info("Total items present in the trolley is: " + totalItems);
		int totalProducts = getXpathCount(OR_OR.getProperty("SinglePageCheckout_Total_Products"),
				"SinglePageCheckout_Total_Products");
		for (int i = 1; i <= totalProducts; i++) {
			String productQty = OR_OR.getProperty("SinglePageCheckout_Nth_Product_Qty");
			String ivalue = Integer.toString(i);
			String qty = productQty.replace("nvalue", ivalue);
			String qtystr = driver.findElement(By.xpath(qty)).getText();
			String qtystr1 = qtystr.substring(0, qtystr.indexOf(" "));
			totalProductsqty = totalProductsqty + Integer.parseInt(qtystr1);
		}
		if (totalItems == totalProductsqty && totalItems == superbarItemCount) {
			testLog.info("Product count is matching as per the items count present in single page checkout");
		} else {
			testLog.error("Product count is not matching as per the items count present in single page checkout");
			assertCheck("validateYourTrolleyItems",
					"product count is not matching as per the items count present in single page checkout");
		}
		return this;
	}

	public AlmostDonePage validateTotalTrolleyAmount(double trolleytotal) {
		ExplicitWait(OR_OR.getProperty("SinglePageCheckout_Trolley_Total_Amt"), "SinglePageCheckout_Trolley_Total_Amt",
				20);
		String totalTrolleyAmt = get_xpath_text(OR_OR.getProperty("SinglePageCheckout_Trolley_Total_Amt"),
				"SinglePageCheckout_Trolley_Total_Amt");
		String totalAmt = totalTrolleyAmt.replaceAll("[^0-9.]", "").trim();
		double Trolley_Total_Cost = Double.parseDouble(totalAmt);
		testLog.info("Extracted price is: " + Trolley_Total_Cost);
		if (Trolley_Total_Cost == trolleytotal) {
			testLog.info("Trolley total amount is: " + Trolley_Total_Cost);
		} else {
			testLog.error("Trolley total amount is is not matching");
			assertCheck("validateTotalTrolleyAmount", "Trolley total amount is is not matching");
		}
		return this;
	}

	public AlmostDonePage validateTotalSaving(double trolleySaving) {
		if (isElementPresent(OR_OR.getProperty("SinglePageCheckout_Trolley_Total_Saving"),
				"SinglePageCheckout_Trolley_Total_Saving")) {
			String totalSPCSaving = get_xpath_text(OR_OR.getProperty("SinglePageCheckout_Trolley_Total_Saving"),
					"SinglePageCheckout_Trolley_Total_Saving");
			String totalSaveAmt = totalSPCSaving.replaceAll("[^0-9.]", "").trim();
			double SPC_Total_Saving = Double.parseDouble(totalSaveAmt);
			testLog.info("Extracted price is: " + SPC_Total_Saving);

			if (SPC_Total_Saving == trolleySaving) {
				testLog.info("Total Saving amount is matching");
			} else {
				testLog.error("Total Saving amount is is not matching");
				assertCheck("validateTotalSaving", "Total Saving amount is is not matching");
			}

		} else {
			testLog.info("No Saving amount/ No offer in this order");
		}
		return this;
	}

	public AlmostDonePage validateSubstituteLabel() {
		wait(5000);
		ExplicitWait(OR_OR.getProperty("Single_Page_Chackout_Heading"), "Single_Page_Chackout_Heading", 20);
		verify_xpath_text(OR_OR.getProperty("ADP_substitute_Stock_Label"),
				OR_OR.getProperty("ADP_substitute_Stock_Label_Text"));
		isElementPresent(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown");
		return this;
	}

	public AlmostDonePage chooseSubstituteItNoExtraCost() {
		ExplicitWait(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown", 20);
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_DropDown_ValueOne"), "ADP_substitute_DropDown_ValueOne");
		verify_xpath_text(OR_OR.getProperty("ADP_substitute_Stock_DropDown"),
				OR_OR.getProperty("ADP_substitute_DropDown_ValueOne_Text"));
		return this;
	}

	public AlmostDonePage chooseDontSubstituteIt() {
		ExplicitWait(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown", 20);
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_DropDown_ValueTwo"), "ADP_substitute_DropDown_ValueTwo");
		verify_xpath_text(OR_OR.getProperty("ADP_substitute_Stock_DropDown"),
				OR_OR.getProperty("ADP_substitute_DropDown_ValueTwo_Text"));
		return this;
	}

	public AlmostDonePage chooseWhatCanBeSubstituted() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown", 20);
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown");
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_DropDown_ValueThree"),
				"ADP_substitute_DropDown_ValueThree");
		verify_xpath_text(OR_OR.getProperty("ADP_substitute_Stock_DropDown"),
				OR_OR.getProperty("ADP_substitute_DropDown_ValueThree_Text"));
		int allowsubstitute = getXpathCount(OR_OR.getProperty("ADP_Allow_substitutes_All_Checkbox"),
				"ADP_Allow_substitutes_All_Checkbox");
		String checkboxes = OR_OR.getProperty("ADP_Allow_substitutes_Nth_Checkbox");
		for (int i = 1; i <= allowsubstitute; i++) {
			String ivalue = Integer.toString(i);
			String ckbox = checkboxes.replace("nvalue", ivalue);
			boolean eachckbox = driver.findElement(By.xpath(ckbox)).isSelected();
			if (eachckbox) {
				testLog.info("allow substitute checkbox selected");
			} else {
				scrollTo_Xpath(ckbox, "ckbox", 2);
				Click_Button_Xpath(ckbox, "ckbox");
				testLog.info("selected allow substitute checkbox");
			}

		}
		return this;
	}

	public AlmostDonePage selectRandomSubstituteOption() {
		ExplicitWait(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown", 20);
		Click_Button_Xpath(OR_OR.getProperty("ADP_substitute_Stock_DropDown"), "ADP_substitute_Stock_DropDown");
		List<WebElement> allSubsItems = driver
				.findElements(By.xpath(OR_OR.getProperty("ADP_substitute_DropDown_All_Values")));
		int listCount = allSubsItems.size();
		int subsRandmOpt = ThreadLocalRandom.current().nextInt(0, listCount);
		testLog.info("Random number is: " + subsRandmOpt);
		selectedSubstituteText = allSubsItems.get(subsRandmOpt).getText();
		testLog.info("Selected substitute is: " + selectedSubstituteText);
		allSubsItems.get(subsRandmOpt).click();
		return this;
	}

	public AlmostDonePage validateRememberedSubstitute() {
		wait(5000);
		ExplicitWait(OR_OR.getProperty("Single_Page_Chackout_Heading"), "Single_Page_Chackout_Heading", 20);
		String dropdownTxt = driver.findElement(By.xpath(OR_OR.getProperty("ADP_substitute_Stock_DropDown"))).getText();
		if (dropdownTxt.equals(selectedSubstituteText)) {
			testLog.info("Selected substitution option is displaying as expected");
		} else {
			testLog.error("selected substitution option is not displaying as expected");
			assertCheck("validateRememberedSubstitute", "selected substitution option is not displaying as expected");
		}
		return this;
	}

	public String getTrolleyAmtFromSCP() {
		String trolley_amt_String_SCP = driver.findElement(By.xpath(OR_OR.getProperty("SCP_product_qty_price")))
				.getText();
		int xpathLength = trolley_amt_String_SCP.length();
		int beginIndex = trolley_amt_String_SCP.indexOf("$");
		String trolley_amt_SCP = trolley_amt_String_SCP.substring(beginIndex + 1, (xpathLength - 1));
		return trolley_amt_SCP;
	}

	public String getTrolleyItemsCountFromSCP() {
		String superbarqtyprice_SCP = driver.findElement(By.xpath(OR_OR.getProperty("SCP_product_qty_price")))
				.getText();
		String superbarqty_SCP = superbarqtyprice_SCP.substring(0, 1);
		return superbarqty_SCP;
	}

	public String getTrolleyProductNameFromSCP() {
		String productName_SCP = driver.findElement(By.xpath(OR_OR.getProperty("SCP_product_name"))).getText();
		return productName_SCP;
	}

	public String getTrolleyProductBrandNameFromSCP() {
		String scpbrandName = driver.findElement(By.xpath(OR_OR.getProperty("SCP_product_brand"))).getText();
		return scpbrandName;
	}

	public String getRD_DeliveryAddress_SPC() {

		return (get_xpath_text(OR_OR.getProperty("AD_RDAddress_Full"), "AD_RDAddress_Full"));
	}

	public String getRD_DeliveryAddressPostCode_SPC() {

		return (get_xpath_text(OR_OR.getProperty("AD_RDAddress_PostCode"), "AD_RDAddress_PostCode"));
	}

	public AlmostDonePage validateSlotDateTimePrice(String delType) {
		wait(3000);
		String dss_slot_date = "";
		String dss_slot_time = "";
		// Getting the slot date and time for matching delivery type and storing
		// in
		// variables
		if (delType == "HD") {
			dss_slot_date = DeliverySlotPage.hd_date;
			dss_slot_time = DeliverySlotPage.hd_time;
		}
		if (delType == "CC") {
			dss_slot_date = DeliverySlotPage.cc_date;
			dss_slot_time = DeliverySlotPage.cc_time;
		}
		if (delType == "RD") {
			dss_slot_date = DeliverySlotPage.rd_date;
			dss_slot_time = DeliverySlotPage.rd_time;
		}

		String date = dss_slot_date.substring(0, dss_slot_date.indexOf(" "));
		String month = dss_slot_date.replaceAll("[0-9.]", "").trim();
		String fullmonth = getFullMonthName(month);
		String dss_slot_day = DeliverySlotPage.Day;
		testLog.info("DSS Slot Day: " + dss_slot_day);
		String slot_day_fullname = getFullDayName(dss_slot_day);
		// DSS page slot date
		String combined_Slot_Details = slot_day_fullname + " " + date + " " + fullmonth;
		testLog.info("DSS page slot Full Date and Day is: " + combined_Slot_Details);
		// Confirm your order page slot date
		String updated_date = getPages.getAlmostDonePage().getSlotDate();
		wait(3000);
		testLog.info("Confirm your order page slot Date and Day is: " + updated_date);

		if (combined_Slot_Details.equals(updated_date)) {
			testLog.info(
					"Compared DSS page slot date to Confirm your order page slot date. Slot date are displaying properly as expected");
		} else {
			testLog.error("Slot date are Not displaying properly as expected");
			assertCheck("validateSlotDateTimePrice", "Slot date are Not displaying properly as expected");
		}

		// DSS page slot time
		String slot_time = dss_slot_time.replaceAll(" ", "").toUpperCase().trim();
		testLog.info("DSS Page Slot time is: " + slot_time);
		// Confirm your order page slot time
		String updated_time = getPages.getAlmostDonePage().getSlotTime();
		wait(3000);
		String time = updated_time.replace("to", "");
		String updated_time1 = time.replace(" ", "").trim();
		testLog.info("Confirm your order page slot time is: " + updated_time1);

		if (slot_time.equals(updated_time1)) {
			testLog.info(
					"Compared DSS page slot time to Confirm your order page slot time. Slot time are displaying properly as expected");
		} else {
			testLog.error("Slot time are Not displaying properly as expected");
			assertCheck("validateSlotDateTimePrice", "Slot time are Not displaying properly as expected");
		}

		// DSS page slot price
		String dss_slot_price = DeliverySlotPage.extracted_price;
		if (!dss_slot_price.contains("Free")) {
			String slot_price = dss_slot_price.substring(0, dss_slot_date.indexOf(" ") + 1).trim();
			slot_price = slot_price.replace(".", "").trim();
			testLog.info("DSS page slot Price is: " + slot_price);
			// Confirm your order page slot price
			String deliveryprice = get_xpath_text(OR_OR.getProperty("SinglePageCheckout_Delivery_Price"),
					"SinglePageCheckout_Delivery_Price");
			String price = deliveryprice.substring(0, deliveryprice.indexOf("."));
			testLog.info("Confirm your order page slot price is: " + price);
			if (price.equals(slot_price)) {
				testLog.info(
						"Compared DSS page slot price to Confirm your order page slot price. Slot price are displaying properly as expected");
			} else {
				testLog.error("Slot price are Not displaying properly as expected");
				assertCheck("validateSlotDateTimePrice", "Slot price are Not displaying properly as expected");
			}
		} else {
			testLog.info("Selected slot is Free and not having price.");
		}
		return this;
	}

	public AlmostDonePage validateDeliveryType(String delType) {
		wait(4000);
		// ExplicitWait(OR_OR.getProperty("COP_Delivery_Heading"),
		// "COP_Delivery_Heading", 20);
		// verify_xpath_text(OR_OR.getProperty("COP_Delivery_Heading"),
		// OR_OR.getProperty("COP_Delivery_Heading_Text"));
		if (delType == "HD") {
			verify_xpath_text(OR_OR.getProperty("COP_Delivery_Type_Heading"),
					OR_OR.getProperty("COP_HD_Delivery_Type_Heading_Text"));
		}
		if (delType == "CC") {
			verify_xpath_text(OR_OR.getProperty("COP_Delivery_Type_Heading"),
					OR_OR.getProperty("COP_CC_Delivery_Type_Heading_Text"));
		}
		if (delType == "RD") {
			verify_xpath_text(OR_OR.getProperty("COP_Delivery_Type_Heading"),
					OR_OR.getProperty("COP_RD_Delivery_Type_Heading_Text"));
		}
		return this;
	}

	public AlmostDonePage validateDeliveryTypeMessage(String dssSlot) {
		boolean changelink;
		wait(2000);
		ExplicitWait(OR_OR.getProperty("Delivery_Type_Heading"), "Delivery_Type_Heading", 20);
		switch (dssSlot) {
		case "signedonly":
			verify_xpath_text(OR_OR.getProperty("Delivery_Type_Heading"),
					OR_OR.getProperty("Delivery_Type_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Delivery_Type_Signed_Delivery"),
					OR_OR.getProperty("Delivery_Type_Signed_Delivery_Text"));
			changelink = isElementNotPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("Delivery type change link should not display for signed only delivery slot");
				assertCheck("validateDeliveryTypeMessage",
						"Delivery type change link should not display for signed only delivery slot");
			}
			break;
		case "signed":
			verify_xpath_text(OR_OR.getProperty("Delivery_Type_Heading"),
					OR_OR.getProperty("Delivery_Type_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Delivery_Type_Signed_Delivery"),
					OR_OR.getProperty("Delivery_Type_Signed_Delivery_Text"));
			changelink = isElementPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("Delivery type change link should display for signed delivery slot");
				assertCheck("validateDeliveryTypeMessage",
						"Delivery type change link should display for signed delivery slot");
			}
			break;
		case "customerChoiceUnattended":
			// verify_xpath_text(OR_OR.getProperty("Delivery_Type_Heading"),
			// OR_OR.getProperty("Delivery_Type_Heading_Text"));
			verify_xpath_contains_text(OR_OR.getProperty("UD_Delivery_msg"),
					OR_OR.getProperty("Delivery_Type_Unattended_Delivery_Text"));
			changelink = isElementPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("Delivery type change link should display for Customer Choice unattended delivery slot");
				assertCheck("validateDeliveryTypeMessage",
						"Delivery type change link should display for Customer Choice unattended delivery slot");
			}
			break;
		default:
			break;
		}
		return this;
	}

	public AlmostDonePage validateCCServiceType(String servicetype) {
		boolean changelink;
		wait(2000);
		boolean collectiontype = isElementNotPresent(OR_OR.getProperty("Collection_Type_Heading"),
				"Collection_Type_Heading");
		switch (servicetype) {
		case "Direct to Boot":
			verify_xpath_text(OR_OR.getProperty("Collection_Type_Heading"),
					OR_OR.getProperty("Collection_Type_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Direct_To_Boot_Text"));
			ExplicitWait(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type_Message"),
					"Confirm_Order_Page_CC_Service_Type_Message", 10,
					OR_OR.getProperty("Confirm_Order_Page_CC_Direct_To_Boot_Message_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type_Message"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Direct_To_Boot_Message_Text"));
			changelink = isElementNotPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("CC service type change link should not display for click and collect concierge");
				assertCheck("validateCCServiceType",
						"CC service type change link should not display for click and collect concierge/Direct to Boot");
			}
			break;
		case "Service Desk":
			verify_xpath_text(OR_OR.getProperty("Collection_Type_Heading"),
					OR_OR.getProperty("Collection_Type_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Service_Desk_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type_Message"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Service_Desk_Message_Text"));
			changelink = isElementNotPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("CC service type change link should not display for click and collect service desk");
				assertCheck("validateCCServiceType",
						"CC service type change link should not display for click and collect service desk");
			}
			break;
		case "Coles Express":
			verify_xpath_text(OR_OR.getProperty("Collection_Type_Heading"),
					OR_OR.getProperty("Collection_Type_Heading_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Coles_Express_Text"));
			verify_xpath_text(OR_OR.getProperty("Confirm_Order_Page_CC_Service_Type_Message"),
					OR_OR.getProperty("Confirm_Order_Page_CC_Coles_Express_Message_Text"));
			changelink = isElementNotPresent(OR_OR.getProperty("AD_Delivery_Type_ChangeLink"),
					"AD_Delivery_Type_ChangeLink");
			if (!changelink) {
				testLog.error("CC service type change link should not display for click and collect coles express");
				assertCheck("validateCCServiceType",
						"CC service type change link should not display for click and collect coles express");
			}
			break;
		case "Lockers":
			if (!collectiontype) {
				testLog.error("Collection type section shoud not display for service lockers");
				assertCheck("validateCCServiceType", "Collection type section shoud not display for service lockers");
			} else {
				testLog.info("Collection type section is not displayed for service lockers");
			}
		case "Mobile":
			if (!collectiontype) {
				testLog.error("Collection type section shoud not display for mobile collect");
				assertCheck("validateCCServiceType", "Collection type section shoud not display for mobile collect");
			} else {
				testLog.info("Collection type section is not displayed for mobile collect");
			}
		default:
			break;
		}
		return this;
	}

	public AlmostDonePage clickPayOnDelivery() {
		ExplicitWait(OR_OR.getProperty("AlmostDonePage_Pay_On_Delivery"), "AlmostDonePage_Pay_On_Delivery", 20);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDonePage_Pay_On_Delivery"), "AlmostDonePage_Pay_On_Delivery");
		return this;
	}

	// Pay On Collection For CC Address
	public AlmostDonePage clickPayOnCollection() {
		ExplicitWait(OR_OR.getProperty("AlmostDone_Pay_On_Collection"), "AlmostDone_Pay_On_Collection", 20);
		Click_Button_Xpath(OR_OR.getProperty("AlmostDone_Pay_On_Collection"), "AlmostDone_Pay_On_Collection");
		return this;
	}

	public AlmostDonePage verifyErrorMessage_Order() {
		wait(2000);
		ExplicitWait(OR_OR.getProperty("AlmostDone_No_Payment_Method_ErrorMsg"),
				"AlmostDone_No_Payment_Method_ErrorMsg", 20, OR_OR.getProperty("No_Card_ErrorMsg"));
		// Getting error message
		String errorMsg = driver.findElement(By.xpath(OR_OR.getProperty("AlmostDone_No_Payment_Method_ErrorMsg")))
				.getText().trim();
		if (errorMsg.equals(OR_OR.get("No_Card_ErrorMsg"))) {
			testLog.info("Great!! we are getting expected error message while submit order without card.");
		} else {
			testLog.error("Error message " + OR_OR.getProperty("No_Card_ErrorMsg") + " is not displayed.");
			assertCheck("verifyErrorMessage_noCard",
					"Opps!! Expected error message is not displayed while submitting order without card.");
		}
		return this;
	}

	public AlmostDonePage validateTotalAmountWithBagForWA() {
		wait(10000);
		verify_xpath_text(OR_OR.getProperty("Bagging_Heading"), OR_OR.getProperty("Bagging_Heading_Text"));
		verify_xpath_text(OR_OR.getProperty("Bagging_Subheading"),
				OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries"));
		verify_xpath_contains_text(OR_OR.getProperty("Bagging_Subheading_Desc"),
				OR_OR.getProperty("Bagging_Subheading_Bag_My_Groceries_Desc_Text_WA").trim());

		double Trolley_Total_Cost = getTrolleyAmount();
		double bagging_price = getBagPrice();
		double total_Amount = getTotalAmount();
		double slot_Price = getTimeSlotPrice();

		if (total_Amount == (Trolley_Total_Cost + bagging_price + slot_Price)) {

			testLog.info(
					"Great!! total amount is displaying correct and it is having trolley , bagging and slot prices in it.");
		} else {
			testLog.error("Total amount is not correctly displayed. It should be " + total_Amount + " and it is "
					+ (Trolley_Total_Cost + bagging_price + slot_Price));
			assertCheck("validateTotalAmountWithBagForWA",
					"Opps!! Expected total amount is not correctly displayed.It should be " + total_Amount
							+ " and it is " + (Trolley_Total_Cost + bagging_price + slot_Price));
		}

		return this;
	}

	public int almostDonePageNumberOfProducts() {

		int numberOfItems = 0;
		wait(5000);
		testLog.info("Getting the count of products available at almost done page:-");
		// if(isElementPresent(OR_OR.getProperty("ADP_substitute_Stock_DropDown"),
		// "ADP_substitute_Stock_DropDown")){
		numberOfItems = getXpathCount(OR_OR.getProperty("AlmostDonePage_Products_Count"),
				"AlmostDonePage_Products_Count");
		testLog.info("The number of products at almost done page is: " + numberOfItems);
		// }
		/*
		 * else{ testLog.error("No product is available at almost done page.");
		 * assertCheck("almostDonePageNumberOfProducts",
		 * "Opps!! No products are available at almost done page."); }
		 */

		return numberOfItems;
	}

}
