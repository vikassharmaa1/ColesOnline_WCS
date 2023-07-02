package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class RecipeToOrderTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT229-Verify that user is able to login to coles and add products from recipeToOrder")
	public void validateLoginColesFromRecipeToOrder() throws Exception {
		
		// Navigate to R2O url, Add products from taste.com.au and login to coles online
		getPages.getRecipeToOrderPage().navigateToRecipeToOrderURL().clickRecipesLink().clickVegetarianRecipesLink().clickFirstRecipe()
				.addIngredients().clickShopIngredients().r2oColesLogout().validateR2OTotalAmt().clickAddToTrolley()
				.switchWindows().enterR2OColesUserName(funLibrary.username).enterR2OColesPassword(funLibrary.password)
				.clickR2OColesLogin().validateSuccessPopup().clickCheckOutAtColesOnlineButton();
		if (getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickAddress("HD");
			getPages.getDeliverySlotPage().ClickChooseLocation().clickClickAndCollectLocation()
					.clickChooseCollectionTime().ClickCancelAndKeepShopingLink();
		} else {
			testLog.info("Slot is NOT already selected");
		}
		// select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector( "signed", true,
				false);
		getPages.getHomePage().clickTrolley().clickProceedToCheckOutButton().TrolleyHandler();
		//checkoutFlowHandler().selectAnyAvailablePaymentOption();
		// Placing order
		getPages.getAlmostDonePage().clickPayUsingCard().clickAddNewCard().enterCardNumber().selectExpiryMonth()
				.selectExpiryYear().enterCVV().clickContinueButton();
		getPages.getAlmostDonePage().clickAgreementCheckbox().clickCompleteOrder().clickReturnToHome();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
