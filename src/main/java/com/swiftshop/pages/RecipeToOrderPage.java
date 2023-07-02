package com.swiftshop.pages;

import java.util.Set;

import org.openqa.selenium.By;

import com.swiftshop.main.FunLibrary;

public class RecipeToOrderPage extends FunLibrary {
	public static double R2O_Trolley_Total_Cost;

	public RecipeToOrderPage navigateToRecipeToOrderURL() {
		driver.get(System.getProperty("R2O_URL"));
		return this;
	}

	public RecipeToOrderPage clickRecipesLink() {
		ExplicitWait(OR_OR.getProperty("R2O_Recipes_Link"), "R2O_Recipes_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("R2O_Recipes_Link"), "R2O_Recipes_Link");
		return this;
	}

	public RecipeToOrderPage clickVegetarianRecipesLink() {
		ExplicitWait(OR_OR.getProperty("R2O_Vegetarian_Recipe_Link"), "R2O_Vegetarian_Recipe_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("R2O_Vegetarian_Recipe_Link"), "R2O_Vegetarian_Recipe_Link");
		return this;
	}

	public RecipeToOrderPage clickFirstRecipe() {
		ExplicitWait(OR_OR.getProperty("R2O_First_Vegetarian_Recipe_Link"), "R2O_First_Vegetarian_Recipe_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("R2O_First_Vegetarian_Recipe_Link"), "R2O_First_Vegetarian_Recipe_Link");
		ExplicitWait(OR_OR.getProperty("R2O_Recipe_Img_Link"), "R2O_Recipe_Img_Link", 10);
		Click_Button_Xpath(OR_OR.getProperty("R2O_Recipe_Img_Link"), "R2O_Recipe_Img_Link");
		Click_Button_Xpath(OR_OR.getProperty("R2O_Recipe_Img_Link"), "R2O_Recipe_Img_Link");
		return this;
	}

	public RecipeToOrderPage addIngredients() {
		scrollTo_Pixel(50, 900);
		ExplicitWait(OR_OR.getProperty("R2O_Ingredients_Heading"), "R2O_Ingredients_Heading", 10);
		int checkboxCount = getXpathCount(OR_OR.getProperty("R2O_All_Ingredients_Checkbox"),
				"R2O_All_Ingredients_Checkbox");
		String checkbox = OR_OR.getProperty("R2O_Nvalue_Ingredients_Checkbox");
		int ingredientsCnt = 0;
		if (checkboxCount >= 8) {
			ingredientsCnt = 8;
		} else {
			ingredientsCnt = checkboxCount;
		}

		for (int i = 1; i <= ingredientsCnt; i++) {
			String ivalue = Integer.toString(i);
			String clickCheckbox = checkbox.replace("nvalue", ivalue);
			driver.findElement(By.xpath(clickCheckbox)).click();
			wait(2000);
		}
		return this;
	}

	public RecipeToOrderPage clickShopIngredients() {
		ExplicitWait(OR_OR.getProperty("R2O_Shop_Ingredients_Now_At_Coles_Button"),
				"R2O_Shop_Ingredients_Now_At_Coles_Button", 10);
		Click_Button_Xpath(OR_OR.getProperty("R2O_Shop_Ingredients_Now_At_Coles_Button"),
				"R2O_Shop_Ingredients_Now_At_Coles_Button");
		wait(5000);
		return this;
	}

	public RecipeToOrderPage r2oColesLogout() {
		ExplicitWait(OR_OR.getProperty("R2O_Coles_Total_Price"), "R2O_Coles_Total_Price", 10);
		if (!isElementPresent(OR_OR.getProperty("R2O_Coles_Register_Link"), "R2O_Coles_Register_Link")) {
			Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_Logout_Link"), "R2O_Coles_Logout_Link");
			wait(3000);
		} else {
			testLog.info("User is already logged out");
			wait(3000);
		}
		return this;
	}

	public double getR2OTrolleyAmount() {
		double emptytrolley = 0.0, Trolley_Total_Cost = 0.0;
		String totalPrice = get_xpath_text(OR_OR.getProperty("R2O_Coles_Total_Price"), "R2O_Coles_Total_Price");
		String newelement1 = totalPrice.substring(1);
		newelement1 = newelement1.replace(",", "");
		R2O_Trolley_Total_Cost = Double.parseDouble(newelement1);
		testLog.info("Extracted price is:" + Trolley_Total_Cost);

		if (R2O_Trolley_Total_Cost == emptytrolley) {
			testLog.info("The Trolley is empty with amount: " + R2O_Trolley_Total_Cost);
		} else {
			testLog.info("Already products exists in the Trolley with Trolley amount: " + R2O_Trolley_Total_Cost);

		}
		return R2O_Trolley_Total_Cost;
	}

	public RecipeToOrderPage validateR2OTotalAmt() {

		double r2o_trolley_amount = this.getR2OTrolleyAmount();
		if (r2o_trolley_amount >= 50) {
			testLog.info("Trolley total amount is: " + R2O_Trolley_Total_Cost);
		} else {
			String qty = OR_OR.getProperty("R2O_Coles_NthProduct_Qty_Textbox");
			for (int i = 1; i <= 8; i++) {
				String ivalue = Integer.toString(i);
				String nqty = qty.replace("nvalue", ivalue);
				Clear_Text(nqty, "qty1");
				if (isElementPresent(OR_OR.getProperty("R2O_Coles_Go_Back_Button"), "R2O_Coles_Go_Back_Button")) {
					Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_Go_Back_Button"), "R2O_Coles_Go_Back_Button");
				}
				Sendkey_xpath(nqty, "5", "qty1");
				Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_Total_Price"), "R2O_Coles_Total_Price");
				wait(3000);
				double updated_r2o_trolley_amount = this.getR2OTrolleyAmount();
				if (updated_r2o_trolley_amount >= 50) {
					testLog.info("Trolley updated total amount is: " + R2O_Trolley_Total_Cost);
					break;
				} else {
					testLog.info("Trolley total amount still not reached $50");
				}
			}
		}
		return this;
	}

	public RecipeToOrderPage clickAddToTrolley() {
		Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_Add_To_Trolley_Button"), "R2O_Coles_Add_To_Trolley_Button");
		wait(15000);
		return this;
	}

	public RecipeToOrderPage switchWindows() {
		Set<String> allWindows = driver.getWindowHandles();
		testLog.info(allWindows.size());
		for (String wh : allWindows) {
			driver.switchTo().window(wh);
			String title = driver.getTitle();
			testLog.info("title is:" + title);
		}
		return this;
	}

	public RecipeToOrderPage switchDefaultWindow() {
		driver.switchTo().defaultContent();
		wait(5000);
		return this;
	}

	public RecipeToOrderPage enterR2OColesUserName(String username) {
		ExplicitWait(OR_OR.getProperty("R2O_Coles_Login_EmailID"), "R2O_Coles_Login_EmailID", 10);
		Clear_Text(OR_OR.getProperty("R2O_Coles_Login_EmailID"), "R2O_Coles_Login_EmailID");
		Sendkey_xpath(OR_OR.getProperty("R2O_Coles_Login_EmailID"), username, "R2O_Coles_Login_EmailID");
		return this;
	}

	public RecipeToOrderPage enterR2OColesPassword(String password) {
		ExplicitWait(OR_OR.getProperty("R2O_Coles_Login_Password"), "R2O_Coles_Login_Password", 10);
		Clear_Text(OR_OR.getProperty("R2O_Coles_Login_Password"), "R2O_Coles_Login_Password");
		Sendkey_xpath(OR_OR.getProperty("R2O_Coles_Login_Password"), password, "R2O_Coles_Login_Password");
		return this;
	}

	public RecipeToOrderPage clickR2OColesLogin() {
		Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_Login_Button"), "R2O_Coles_Login_Button");
		wait(10000);
		this.switchWindows();
		return this;
	}

	public RecipeToOrderPage validateSuccessPopup() {
		ExplicitWait(OR_OR.getProperty("R2O_Coles_Login_Success"), "R2O_Coles_Login_Success", 10);
		isElementPresent(OR_OR.getProperty("R2O_Coles_Login_Success"), "R2O_Coles_Login_Success");
		verify_xpath_text(OR_OR.getProperty("R2O_Coles_Login_Success_Header"),
				OR_OR.getProperty("R2O_Coles_Login_Success_Header_Text"));
		return this;
	}

	public RecipeToOrderPage clickCheckOutAtColesOnlineButton() {
		Click_Button_Xpath(OR_OR.getProperty("R2O_Coles_View_My_Trolley_Check_Out_At_Coles_Online"),
				"R2O_Coles_View_My_Trolley_Check_Out_At_Coles_Online");
		wait(15000);
		this.switchWindows();
		ExplicitWait(OR_OR.getProperty("Coles_Logo"), "Coles_Logo", 10);
		wait(10000);
		Click_Button_Xpath(OR_OR.getProperty("Close_Superbar"), "Close_Superbar");
		wait(5000);
		return this;

	}
}
