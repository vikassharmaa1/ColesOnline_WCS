package com.swiftshop.pages;

import org.openqa.selenium.By;

import com.swiftshop.main.FunLibrary;

public class OrdersPage extends FunLibrary {

	public OrdersPage closeSuperBar() {
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		return this;
	}

	public OrdersPage validateOrdersHeadings() {
		verify_xpath_text(OR_OR.getProperty("Past_Orders_Header"), "Your orders");
		//verify_xpath_text(OR_OR.getProperty("Past_Orders_Showing_All_Addresses_Text"), "Showing: All addresses"); //Change in requirement.
		return this;
	}

	public OrdersPage clickChange() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Showing_All_Addresses_Change_Link"),
				"Past_Orders_Showing_All_Addresses_Change_Link");
		return this;
	}

	public OrdersPage selectAddress(String AddType) {
		int allAddress = getXpathCount(OR_OR.getProperty("Past_Orders_Detail_Heading"), "Past_Orders_Detail_Heading");
		switch (AddType) {
		case "HD":
			if (isElementPresent(OR_OR.getProperty("Past_Orders_HD_Address"), "Past_Orders_HD_Address")) {
				Click_Button_Xpath(OR_OR.getProperty("Past_Orders_HD_Address"), "Past_Orders_HD_Address");
				wait(2000);
				testLog.info("Total HD past orders present in the list is " + allAddress);
				for (int i = 0; i <= allAddress; i++) {
					String header = get_xpath_text(OR_OR.getProperty("Past_Orders_Detail_Heading"),
							"Past_Orders_Detail_Heading");
					if (header.equals("Order delivered")) {
						testLog.info("Delivered HD order");
					}
					if (header.contains("cancelled")) {
						testLog.info("Cancelled HD order");
					}
					if (header.contains("Order placed")) {
						testLog.info("HD order placed");
					}

				}
			} else {
				testLog.info("No Home delivery orders pressent");
			}
			break;
		case "CC":
			if (isElementPresent(OR_OR.getProperty("Past_Orders_CC_Address"), "Past_Orders_CC_Address")) {
				Click_Button_Xpath(OR_OR.getProperty("Past_Orders_CC_Address"), "Past_Orders_CC_Address");
				wait(2000);
				testLog.info("Total CC past orders present in the list is " + allAddress);
				for (int i = 0; i <= allAddress; i++) {
					String header = get_xpath_text(OR_OR.getProperty("Past_Orders_Detail_Heading"),
							"Past_Orders_Detail_Heading");
					if (header.equals("Order collected")) {
						testLog.info("Delivered Click and collect order");
					}
					if (header.contains("cancelled")) {
						testLog.info("Cancelled CC order");
					}
					if (header.contains("Order placed")) {
						testLog.info("CC order placed");
					}
				}
			} else {
				testLog.info("No click and collect orders pressent");
			}
			break;
		case "RD":
			if (isElementPresent(OR_OR.getProperty("Past_Orders_RD_Address"), "Past_Orders_RD_Address")) {
				Click_Button_Xpath(OR_OR.getProperty("Past_Orders_RD_Address"), "Past_Orders_RD_Address");
				wait(2000);
				testLog.info("Total RD past orders present in the list is " + allAddress);
				for (int i = 0; i <= allAddress; i++) {
					String header = get_xpath_text(OR_OR.getProperty("Past_Orders_Detail_Heading"),
							"Past_Orders_Detail_Heading");
					if (header.equals("Order delivered to your freight provider")) {
						testLog.info("Delivered Remote order");
					}
					if (header.contains("cancelled")) {
						testLog.info("Cancelled RD order");
					}
					if (header.contains("Order placed")) {
						testLog.info("RD order placed");
					}
				}
			} else {
				testLog.info("No Remote delivery orders pressent");
			}
			break;
		default:
			break;
		}
		return this;
	}

	public OrdersPage verifyPastOrders() {

		testLog.info("Verifying the past orders");
		if (isElementPresent(OR_OR.getProperty("Orders_List_Container"), "Orders_List_Container")) {

			int countOfOrders = getXpathCount(OR_OR.getProperty("Orders_List"), "Orders_List");

			if (countOfOrders > 0) {

				testLog.info("Great!! User have past orders.");
			}

			else {
				testLog.error("Oops!! User does not have placed any orders.");
			}
		} else {
			testLog.error("Oops!! User does not have placed any orders.");
		}
		
		return this;

	}

	public OrdersPage clickPastOrderDelivered() {
		if (isElementPresent(OR_OR.getProperty("Past_Orders_Delivered"), "Past_Orders_Delivered")) {
			Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Delivered"), "Past_Orders_Delivered");
		} else {
			testLog.error("No delivered past orders present in the list");
			assertCheck("clickPastOrderDelivered", "No delivered past orders present in the list");
		}
		return this;
	}

	public OrdersPage clickReorderItems() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Reorder_Items"), "Past_Orders_Reorder_Items");
		return this;
	}

	public OrdersPage addAllPastOrderItems() {
		double Trolley_Total_Cost;
		TrolleyAndCheckoutPage trolleyandcheckoutpage = new TrolleyAndCheckoutPage();
		Click_Button_Xpath(OR_OR.getProperty("Past_Order_Add_All_Items"), "Past_Order_Add_All_Items");
		ExplicitWait(OR_OR.getProperty("Past_Order_Add_All_Success_Msg"), "Past_Order_Add_All_Success_Msg", 20);
		verify_xpath_text(OR_OR.getProperty("Past_Order_Add_All_Success_Msg"),
				OR_OR.getProperty("Past_Order_Add_All_Success_Msg_Text"));
		Trolley_Total_Cost = trolleyandcheckoutpage.getTrolleyAmount();
		testLog.info("Trolley Cost after adding past order item to trolley: " + Trolley_Total_Cost);
		return this;
	}

	public OrdersPage validateOrderStatus() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_List_Option1_Header"), "Past_Orders_List_Option1_Header");
		ExplicitWait(OR_OR.getProperty("ODP_Header"), "ODP_Header", 5);

		String odp_header_str = get_xpath_text(OR_OR.getProperty("ODP_Header"), "ODP_Header");
		if (odp_header_str.contains("delivered")) {
			verify_xpath_contains_text(OR_OR.getProperty("ODP_Header"), "Order delivered");
		} else if (odp_header_str.contains("collected")) {
			verify_xpath_contains_text(OR_OR.getProperty("ODP_Header"), "Order collected");
		}

		return this;
	}

	public OrdersPage clickPlacedOrder(String orderId) {
		clickSeeOlderOrders();
		int allPlacedDeliveryCount = getXpathCount(OR_OR.getProperty("Past_Order_Placed_Delivery"),
				"Past_Order_Placed_Delivery");
		String placedOrders = OR_OR.getProperty("Past_Order_All_Placed_Delivery");
		for (int i = 1; i <= allPlacedDeliveryCount; i++) {
			String ivalue = Integer.toString(i);
			String placeddelivery1 = placedOrders.replace("nvalue", ivalue);
			driver.findElement(By.xpath(placeddelivery1)).click();
			wait(2000);
			String modify_order_id = get_xpath_text(OR_OR.getProperty("Past_Order_OrderNumber"),
					"Past_Order_OrderNumber");
			modify_order_id = modify_order_id.substring(modify_order_id.length() - 9, modify_order_id.length()).trim();
			testLog.info("modify_order_id: " + modify_order_id);
			if (orderId.equals(modify_order_id)) {
				break;
			} else {
				Click_Button_Xpath(OR_OR.getProperty("Past_Order_BackToOrderList"), "Past_Order_BackToOrderList");
				wait(3000);
			}
		}
		return this;
	}

	public OrdersPage clickModifyOrder() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Order_Modify_This_Order"), "Past_Order_Modify_This_Order");
		Click_Button_Xpath(OR_OR.getProperty("Past_Order_Yes_Modify_This_Order"), "Past_Order_Yes_Modify_This_Order");
		ExplicitWait(OR_OR.getProperty("Close_Superbar"), "Close_Superbar", 25);
		wait(6000);
		return this;
	}

	public OrdersPage clickCancelOrder() {
		scrollTo_Xpath(OR_OR.getProperty("Past_Orders_Cancel_Order_Button"), "Past_Orders_Cancel_Order_Button", 1);
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Cancel_Order_Button"), "Past_Orders_Cancel_Order_Button");
		wait(5000);
		verify_xpath_text(OR_OR.getProperty("Past_Orders_Cancel_Conf_Text"),
				"Are you sure you want to cancel this order?");
		return this;
	}

	public OrdersPage clickCancelMyOrderConfirmationButton() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Cancel_Ok_Button"), "Past_Orders_Cancel_Ok_Button");
		wait(30000);
		verify_xpath_text(OR_OR.getProperty("Past_Orders_Cancel_Conf_Text"), "Your order has been cancelled");
		return this;
	}

	public OrdersPage clickOkButton() {
		Click_Button_Xpath(OR_OR.getProperty("Past_Orders_Cancel_Ok_Button"), "Past_Orders_Cancel_Ok_Button");
		wait(10000);
		return this;
	}

	public OrdersPage cancelOrders() {
		// Function to cancel all placed orders
		clickSeeOlderOrders();
		while (isElementPresent(OR_OR.getProperty("Past_Orders_First_Order_Placed_For"),
				"Past_Orders_First_Order_Placed_For")) {
			Click_Button_Xpath(OR_OR.getProperty("Past_Orders_First_Order_Placed_For"),
					"Past_Orders_First_Order_Placed_For");
			clickCancelOrder().clickCancelMyOrderConfirmationButton().clickOkButton();
			clickSeeOlderOrders();
		}
		return this;
	}

	public OrdersPage clickSeeOlderOrders() {
		for (int i = 1; i <= 3; i++) {
			if (isElementPresent(OR_OR.getProperty("See_Older_Orders"), "See_Older_Orders")) {
				Click_Button_Xpath(OR_OR.getProperty("See_Older_Orders"), "See_Older_Orders");
				wait(2000);
			} else {
				break;
			}
		}
		return this;
	}

	public OrdersPage verifyTrolleyTotal(double trolleyAmount) {

		ExplicitWait(OR_OR.getProperty("OrdersTab_TrolleyTotal"), "OrdersTab_TrolleyTotal", 20);
		String trolleyTotal = get_xpath_text(OR_OR.getProperty("OrdersTab_TrolleyTotal"), "OrdersTab_TrolleyTotal");
		if (trolleyTotal.contains("Y")) {
			trolleyTotal = trolleyTotal.substring(0, trolleyTotal.indexOf("Y")).trim();
		}

		trolleyTotal = trolleyTotal.replace("$", "").trim();
		testLog.info("The trolley amount is : $" + trolleyTotal);
		double trolleyTotalAmount = Double.parseDouble(trolleyTotal);
		if (trolleyAmount == trolleyTotalAmount) {
			testLog.info(
					"The trolley amount on Orders tab is matching with the checkout page trolley amount and it is :"
							+ trolleyTotalAmount);
		} else {
			testLog.error("The trolley amount at Orders tab is " + trolleyTotalAmount
					+ "not matching with checkout page trolley amount " + trolleyAmount);
			assertCheck("verifyTrolleyTotal",
					"The trolley amount at orders tab is not matching with check out page trolley amount");
		}

		return this;
	}

	public OrdersPage verifyTotalAmount(double totalAmount) {

		ExplicitWait(OR_OR.getProperty("OrdersTab_TotalAmount"), "OrdersTab_TotalAmount", 20);
		String total = get_xpath_text(OR_OR.getProperty("OrdersTab_TotalAmount"), "OrdersTab_TotalAmount");
		if (total.contains("Y")) {
			total = total.substring(0, total.indexOf("Y")).trim();
		}
		total = total.replace("$", "").trim();
		testLog.info("The trolley amount is : $" + total);
		double total_Amount = Double.parseDouble(total);
		if (totalAmount == total_Amount) {
			testLog.info(
					"The trolley amount on Orders tab is matching with the checkout page trolley amount and it is :"
							+ total_Amount);
		} else {
			testLog.error("The trolley amount at Orders tab is " + total_Amount
					+ "not matching with checkout page trolley amount " + totalAmount);
			assertCheck("verifyTrolleyTotal",
					"The trolley amount at orders tab is not matching with check out page trolley amount");
		}

		return this;
	}

	public OrdersPage verifyDeliveryType(String checkOutPageDelivery) {

		ExplicitWait(OR_OR.getProperty("OrdersTab_DeliveryType"), "OrdersTab_DeliveryType", 20);
		String del_type = get_xpath_text("OrdersTab_DeliveryType", "OrdersTab_DeliveryType");
		testLog.info("The delivery type is :" + del_type);

		if (checkOutPageDelivery.contains(del_type)) {

			testLog.info("The delivery type at orders tab is matching with checkout page delivery type and it is: "
					+ del_type);
		} else {

			testLog.error("The delivery type at orders tab is " + del_type
					+ " not matching with checkout page delivery type" + checkOutPageDelivery);
			assertCheck("verifyDeliveryType",
					"The delivery type at orders tab is not matching with delivey type at checkout page.");
		}

		return this;
	}

	public OrdersPage verifyBaggingPrice(double checkOutPage_baggingPrice) {

		ExplicitWait(OR_OR.getProperty("OrdersTab_BaggingPrice"), "OrdersTab_BaggingPrice", 20);
		String baggingPrice = get_xpath_text(OR_OR.getProperty("OrdersTab_BaggingPrice"), "OrdersTab_BaggingPrice")
				.replace("$", "").trim();

		double baggingAmount = Double.parseDouble(baggingPrice);
		if (checkOutPage_baggingPrice == baggingAmount) {

			testLog.info("Bagging price at orders tab is " + baggingAmount
					+ " is matching with checkout page bagging amount: " + checkOutPage_baggingPrice);
		} else {

			testLog.error("Bagging price at orders tab is " + baggingAmount
					+ " is not matching with checkout page bagging amount: " + checkOutPage_baggingPrice);
			assertCheck("verifyBaggingPrice",
					"Bagging price at orders tab is not matching with checkout page bagging price");
		}

		return this;
	}

}
