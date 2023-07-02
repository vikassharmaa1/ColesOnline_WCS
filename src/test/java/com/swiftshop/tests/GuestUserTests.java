package com.swiftshop.tests;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class GuestUserTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT001- Verify Home page is loaded successfully")
	public void validateHomePage_GuestUser() throws Exception {
		getPages.getHomePage().validatePageTitle().validateColesLogo().validateHomeIcon().validateSearchBoxText()
				.validateEverythingText().validateBoughtBeforeText().validateSpecialText().validateMorebutton()
				.validateTrolleyText().validateDeliveryTimeText().validateLoginText().validateHelpText();
	}

	@Test(description = "WCS_AUT002- Verify Guest user can search any products in home page")
	public void validateProductSearch_GuestUser() throws Exception {
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateResultText(funLibrary.search_1).validateEverythingCount().clickClearSearch()
				.enterSearchItem(funLibrary.search_2).clickSearchButton().validateResultText(funLibrary.search_2)
				.validateEverythingCount().clickProduct().validatePrductCode().clickBacktoProduct().clickClearSearch();
	}

	@Test(description = "WCS_AUT003- Verify Guest user can search invalid product")
	public void validateInvalidProductSearch_GuestUser() throws Exception {
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateInvalidProductMsg();
	}

	@Test(description = "WCS_AUT004- Verify Guest user can save and search multiple products in home page")
	public void validateMultiSearch_GuestUser() throws Exception {
		getPages.getHomePage().clickMultiSearchLink().sendItemsToMultiSearch(funLibrary.sheet_name)
				.clickSecondItemMultiSearch().clickMultiSearchHideButton().validateMultiSearchClearList();
	}

	@Test(description = "WCS_AUT005- Verify sub category counter and products")
	public void validateSubcategoryProducts_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1).validateSubCatProducts();
	}

	@Test(description = "WCS_AUT006- verify Sorted by - Top Products functionality for Sub-Category Page")
	public void validateTopProductsSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickGeoLocationChangeLink().enterGeoLocation(funLibrary.locationName)
				.selectFirstSuggestedGeoLocation().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.validateSortedByText("EverythingTab", "TopProducts").clickEverythingChangeButton()
				.clickSortOption("EverythingTab", "special").ClickEverythingSave()
				.validateSortedByText("EverythingTab", "Special").clickEverythingChangeButton()
				.clickSortOption("EverythingTab", "TopProducts").ClickEverythingSave()
				.validateSortedByText("EverythingTab", "TopProducts");
	}

	@Test(description = "WCS_AUT007- verify Sorted by - On special functionality for Sub-Category Page")
	public void validateSpecialSorting_GuestUser() throws Exception {

		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "special").ClickEverythingSave()
				.validateEverythingOnSpecialSort().clickEverythingChangeButton()
				.clickSortOption("EverythingTab", "TopProducts").ClickEverythingSave();

	}

	@Test(description = "WCS_AUT008- verify Sorted by - Lowest unit price functionality for Sub-Category Page")
	public void validateLowestUnitPriceSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(3)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "lowest unit price")
				.ClickEverythingSave().validateEverythingUnitPriceSorting("EverythingTab");
	}

	@Test(description = "WCS_AUT009- verify Sorted by - Lowest price functionality for Sub-Category Page")
	public void validateLowestPriceSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "lowest price").ClickEverythingSave()
				.validateLowestPriceSorting("EverythingTab");
	}

	@Test(description = "WCS_AUT010- Verify the Brand(A-Z) Sorting functionality for Sub-Category Page")
	public void validateBrandSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "Brand(A-Z)").ClickEverythingSave()
				.validateBrandSorting("EverythingTab");
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "TopProducts").ClickEverythingSave();
	}

	@Test(description = "WCS_AUT011- Verify the Product name(A-Z) Sorting functionality for Sub-Category Page")
	public void validateProductNameSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "Product name (A-Z)")
				.ClickEverythingSave().validateProductSorting("EverythingTab");
	}

	@Test(description = "WCS_AUT012 - Verify Filter functionality in Everything tab")
	public void validateEverythingTabFilter_GuestUser() throws Exception {
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1)
				// .clickRandomLHSCategory().clickRandomLHSCategory()
				.clickShowFilterBtn_EverythingTab().clickFilterByBrand().verifyFilterSearch()
				.filterPopup_SelectRandomCheckbox().clickShowFilteredProducts().ValidateFilterResult("EverythingTab");
	}

	@Test(description = "WCS_AUT013- Verify Guest user is not able to see any products in bought before tab")
	public void validateBoughtBeforeTab_GuestUser() throws Exception {
		getPages.getHomePage().clickBoughtBeforeTab().validateBestBeforeMsg();
	}

	@Test(description = "WCS_AUT014- Verify Guest user can see special products in special tab")
	public void validateSpecialTabProducts_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().validateSpecialTiles();
	}

	@Test(description = "WCS_AUT015- Verify Filter functionality in Special tab")
	public void validateSpecialTabFilter_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickShowFilterBtn_SpecialTab().clickFilterByBrand_SpecialTab()
				.verifyFilterSearch().filterPopup_SelectRandomCheckbox().clickShowFilteredProducts()
				.ValidateFilterResult("special tab");
	}

	@Test(description = "WCS_AUT016- Verify the Top Products Sorting functionality for Special Tab ")
	public void validateSpecialTabTopProductsSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader().validateTopProdText()
				.clickSortOption("SpecialTab", "TopProducts").clickSpecialSave()
				.validateSortedByText("SpecialTab", "TopProducts");

		// Commented below code as it could be used later
		/*
		 * getPages.getHomePage().clickSpecialTab().clickLHSCategory(2).
		 * clickChangeButton().validateSpecialHeader()
		 * .validateTopProdText().clickSortOption("SpecialTab",
		 * "TopProducts").clickSpecialSave() .validateSortedByText("SpecialTab",
		 * "TopProducts");
		 */
	}

	@Test(description = "WCS_AUT017- Verify the Lowest unit price Sorting functionality for Special Tab ")
	public void validateSpecialTabLowestUnitPriceSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickLHSCategory(2).clickChangeButton().validateSpecialHeader()
				.validateSpecialLowestUnitText().clickSpecialLowestUnitPrice().clickSpecialSave()
				.validateSortedByText("SpecialTab", "lowestUnitPrice").validateSplUnitPriceSorting();
	}

	@Test(description = "WCS_AUT018- Verify the Lowest price Sorting functionality for Special Tab ")
	public void validateSpecialTabLowestPriceSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader()
				.validateSpecialLowestPriceText().clickSpecialLowestPrice().clickSpecialSave()
				.validateSortedByText("SpecialTab", "lowestprice").validateLowestPriceSorting("SpecialTab");// .validateSplLowestPriceSorting();
	}

	@Test(description = "WCS_AUT019 - Verify the Brand A-Z Sorting functionality for Special Tab ")
	public void validateSpecialTabBrandSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader()
				.validateSpecialBrandAtoZText().clickSortOption("SpecialTab", "Brand(A-Z)").clickSpecialSave()
				.validateSortedByText("SpecialTab", "brandatoz").validateBrandSorting("SpecialTab");
	}

	@Test(description = "WCS_AUT020- Verify the product name (A-Z) Sorting functionality for Special Sub-Category Page")
	public void validateSpecialTabProductNameSorting_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().clickLHSCategory(3).clickChangeButton()
				.clickSortOption("SpecialTab", "Product name (A-Z)").ClickSpecialSave()
				.validateProductSorting("SpecialTab");
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader().validateTopProdText()
				.clickSortOption("SpecialTab", "TopProducts").clickSpecialSave();

		// Commented below code as it could be used later
		/*
		 * getPages.getHomePage().clickSpecialTab().clickChangeButton().
		 * clickSortOption( "SpecialTab", "Product name (A-Z)")
		 * .ClickSpecialSave().validateProductSorting("SpecialTab");
		 */
	}

	@Test(description = "WCS_AUT021- Verify that Guest customer is NOT able to add product")
	public void validateAddProduct_GuestUser() throws Exception {
		getPages.getHomePage().search_Add_Products_To_Trolley_And_Validate(funLibrary.search_1);
	}

	@Test(description = "WCS_AUT023- Verify the RHS when user click on choose delivery time in superbar")
	public void validateSuperbarChooseDelivery_GuestUser() throws Exception {
		getPages.getSuperBarPage().clickChooseDeliverTime().validateDeliveryInfoMsg().validateDeliveryLinkText();
	}

	@Test(description = "WCS_AUT024- Verify user can Login via choose delivery time")
	public void validateChooseDeliveryForceLogin_GuestUser() throws Exception {
		// User logged-in via 'Choose Delivery Time'
		getPages.getSuperBarPage().clickChooseDeliverTime().clickViewDeliveryCollectionLink().clickLoginLink()
				.enterUserName(funLibrary.username).enterPassword(funLibrary.password).clickLogin()
				.verifyWelcomeBackPopupMsg().clickWelcomeBackOKButton();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT025- Verify user can Sign up  via choose delivery time", enabled = false)
	public void validateChooseDeliveryForceRegistration_GuestUser() throws Exception {
		// Registering user via selecting 'View Delivery & Collection time'
		getPages.getSuperBarPage().clickChooseDeliverTime().clickViewDeliveryCollectionLink().clickSignupLink()
				.enterRandomEmailID().enterFirstNameLastName().enterPassword().enterSuburb_Postcode("Suburb")
				.enterMobileNumber(funLibrary.mobNumber).clickStartShoppingButton().alertPopupHandler("newUser");
		// Verifying user registered successfully and clicking on continue button
		getPages.getHomePage().clickRegistrationPopupContinue_Button().skipFlybuys();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT026- Verify product restrictions in everything tab for tabacco and liquor")
	public void validateEverythingTabTobaccoLiquorRestrictions_GuestUser() throws Exception {
		getPages.getHomePage().clickGeoLocationChangeLink().enterGeoLocation(funLibrary.suburb)
				.selectSuggestedGeoLocation().validateGeoLocation();
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateTobaccoWarningMsg()
				.clickShowTobaccoProductBtn().validateTobaccoProductDetailsWarningMsg().clickHomeIcon()
				.enterSearchItem(funLibrary.search_2).clickSearchButton().validateLiquorWarningMsg()
				.clickViewLiquorLicenceLink().validateLiquorLicencePageTitle();
	}

	@Test(description = "WCS_AUT027- Verify Guest user can see special products in special tab with yellow tag")
	public void validateSpecialProductsTags_GuestUser() throws Exception {
		getPages.getHomePage().clickSpecialTab().validateSpecialTiles();
		// Commented below method, as could be use later
		// validateSpecialTileColors();
	}

	@Test(description = "WCS_AUT028- Verify Guest user can scrollup an click on red arrow button at buttom of the page")
	public void validateScrollUpArrow_GuestUser() throws Exception {
		getPages.getHomePage().scrollDownHomePage(0, 2000).clickBackToTopIcon().validateHomePageHeader();
	}

	@Test(description = "WCS_AUT029- Verify Tobacco and Liquor espots tile for restrictions")
	public void validateEverythingTabTobaccoLiquorEspotsTile_GuestUser() throws Exception {
		getPages.getHomePage().clickGeoLocationChangeLink().enterGeoLocation(funLibrary.suburb)
				.selectSuggestedGeoLocation().validateGeoLocation();
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateTobaccoWarningMsg()
				.clickHomeIcon().enterSearchItem(funLibrary.search_2).clickSearchButton().validateLiquorWarningMsg();
	}

	@Test(description = "WCS_AUT030- Verify Pagination")
	public void validatePagination_GuestUser() throws Exception {
		getPages.getHomePage().clickGeoLocationChangeLink().enterGeoLocation(funLibrary.locationName)
				.selectFirstSuggestedGeoLocation().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validatePagination();
	}
}
