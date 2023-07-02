package com.swiftshop.tests;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class HomePageTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT041 -Verify Home page is loaded successfully")
	public void validateHomePage_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating home page
		getPages.getHomePage().validatePageTitle().validateColesLogo().validateHomeIcon().validateSearchBoxText()
				.validateEverythingText().validateBoughtBeforeText().validateSpecialText().validateMorebutton()
				.validateTrolleyText().validateDeliveryTimeText().validateHelpText();
		getPages.getLoginPage().validateUserNameText().validateOrdersText();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT042 -Verify welcome popup should close when user click close button")
	public void validateUserCloseWelcomepopup_LoggedInUser() throws Exception {
		// Logged-in with registered user
		getPages.getSuperBarPage().clickLoginSignup().enterUserName(funLibrary.username)
				.enterPassword(funLibrary.password).clickLogin().verifyWelcomeBackPopupMsg().clickWelcomeBackOKButton();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT043-Verify user can search any products in home page")
	public void validateProductSearch_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validate search product and results
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateResultText(funLibrary.search_1).validateEverythingCount().clickClearSearch()
				.enterSearchItem(funLibrary.search_2).clickSearchButton().validateResultText(funLibrary.search_2)
				.validateEverythingCount().clickProduct().validatePrductCode().clickBacktoProduct().clickClearSearch();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT044-Verify user can search invalid products in home page")
	public void validateInvalidProductSearch_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validate invalid product message
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateInvalidProductMsg();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT045-Verify user can save and search multiple products in home page")
	public void validateMultiSearch_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validate multi search clear list
		getPages.getHomePage().clickMultiSearchLink().sendItemsToMultiSearch(funLibrary.sheet_name)
				.clickSecondItemMultiSearch().clickMultiSearchHideButton().validateMultiSearchClearList();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT046-Shop from saved items in multisearch")
	public void validateAddProductsFromMultisearch_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().clickMultiSearchLink().sendItemsToMultiSearch(funLibrary.sheet_name)
				.addFirstItemToTrolley().validateSuccessGreenIcon().clickSecondItemMultiSearch().addFirstItemToTrolley()
				.validateSuccessGreenIcon();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT047- Verify the count of a particular LHS Sub-Category and select that Sub-Category and  verify the products")
	public void validateSubcategoryProducts_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating sub-category products
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(3).validateSubCatProducts();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT048 - For logged in user - verify Sorted by - Top Products functionality for Sub-Category Page")
	public void validateEverythingTabTopProductsSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating sort by text
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1)
				.validateSortedByText("EverythingTab", "TopProducts").clickEverythingChangeButton()
				.clickSortOption("EverythingTab", "special").ClickEverythingSave()
				.validateSortedByText("EverythingTab", "Special").clickEverythingChangeButton()
				.clickSortOption("EverythingTab", "TopProducts").ClickEverythingSave()
				.validateSortedByText("EverythingTab", "TopProducts");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT049- Verify the Top products Sorting functionality for Sub-Category Page ")
	public void validateEverythingTabOnSpecialSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating every things on special sort
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "special").ClickEverythingSave()
				.validateEverythingOnSpecialSort();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT050- Verify the Lowest unit price Sorting functionality for Sub-Category Page ")
	public void validateLowestUnitPriceSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating unit price sorting
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(4).clickLHSCategory(3)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "lowest unit price")
				.ClickEverythingSave().validateEverythingUnitPriceSorting("EverythingTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT051- Verify the Lowest price Sorting functionality for Sub-Category Page")
	public void validateLowestPriceSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating lowest price sorting
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(8).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "lowest price").ClickEverythingSave()
				.validateLowestPriceSorting("EverythingTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT052- Verify the Brand(A-Z) Sorting functionality for Sub-Category Page")
	public void validateBrandSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating brand sorting
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(2).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "Brand(A-Z)").ClickEverythingSave()
				.validateBrandSorting("EverythingTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT053- Verify the Product name(A-Z) Sorting functionality for Sub-Category Page ")
	public void validateProductNameSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating product sorting
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(3).clickLHSCategory(1)
				.clickEverythingChangeButton().clickSortOption("EverythingTab", "Product name (A-Z)")
				.ClickEverythingSave().validateProductSorting("EverythingTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT054 - Verify Filter functionality in Everything tab")
	public void validateEverythingTabFilter_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating filter results
		getPages.getHomePage().clickEverythingTab().clickLHSCategory(5).clickLHSCategory(1)
				.clickShowFilterBtn_EverythingTab().clickFilterByBrand().verifyFilterSearch()
				.filterPopup_SelectRandomCheckbox().clickShowFilteredProducts().ValidateFilterResult("EverythingTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT055 - Verify user is able to see any products in bought before tab")
	public void validateBoughtBeforeTab_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating product bought before tab
		getPages.getHomePage().clickBoughtBeforeTab().validateProdutsinBoughtBeforeTab();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT056- Verify user can see special products in special tab")
	public void validateSpecialTabProducts_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating special tile colors
		getPages.getHomePage().clickSpecialTab().validateSpecialTiles();
		// Commented below method as could be used later
		// validateSpecialTileColors();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT057- Verify Filter functionality in Special tab")
	public void validateSpecialTabFilter_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating filer results
		getPages.getHomePage().clickSpecialTab().clickShowFilterBtn_SpecialTab().clickFilterByBrand_SpecialTab()
				.verifyFilterSearch().filterPopup_SelectRandomCheckbox().clickShowFilteredProducts()
				.ValidateFilterResult("special tab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT058- verify Sorted by - Top Products functionality for Specail Tab")
	public void validateSpecialTabTopProductsSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating sorting by text
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader().validateTopProdText()
				.clickSpecialSave().validateSortedByText("SpecialTab", "TopProducts");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT059- Verify the Lowest unit price Sorting functionality for Special Tab ")
	public void validateSpecialTabLowestUnitPriceSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating unit price sorting //New One Category added, earlier it was
		// clickLHSCategory(1)
		getPages.getHomePage().clickSpecialTab().clickLHSCategory(1).clickLHSCategory(2).clickChangeButton()
				.validateSpecialHeader().validateSpecialLowestUnitText().clickSpecialLowestUnitPrice()
				.clickSpecialSave().validateSortedByText("SpecialTab", "lowestUnitPrice").validateSplUnitPriceSorting();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT060- Verify the Lowest price Sorting functionality for Special Tab ")
	public void validateSpecialTabLowestPriceSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating lowest price sorting
		getPages.getHomePage().clickSpecialTab().clickChangeButton().validateSpecialHeader()
				.validateSpecialLowestPriceText().clickSpecialLowestPrice().clickSpecialSave()
				.validateSortedByText("SpecialTab", "lowestprice").validateLowestPriceSorting("SpecialTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT061 - Verify the Brand A-Z Sorting functionality for Special Tab ")
	public void validateSpecialTabBrandSorting_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating brand sorting
		getPages.getHomePage().clickSpecialTab().clickLHSCategory(1).clickChangeButton().validateSpecialHeader()
				.validateSpecialBrandAtoZText().clickSortOption("SpecialTab", "Brand(A-Z)").clickSpecialSave()
				.validateSortedByText("SpecialTab", "brandatoz").validateBrandSorting("SpecialTab");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT062- Verify the product name (A-Z) Sorting functionality for Special Sub-Category Page")
	public void validateSpecialTabProductNameSorting_LoggedInUser() throws AWTException, Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating product sorting
		getPages.getHomePage().clickSpecialTab().clickLHSCategory(5).clickChangeButton()
				.clickSortOption("SpecialTab", "Product name (A-Z)").ClickSpecialSave()
				.validateProductSorting("SpecialTab");
		// Commented below code, as it could be use later
		/*
		 * getPages.getHomePage().clickSpecialTab().clickChangeButton().
		 * clickSortOption("SpecialTab", "Product name (A-Z)")
		 * .ClickSpecialSave().validateProductSorting("SpecialTab");
		 */
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT063- Verify the product added by logged customer")
	public void validateAddProducts_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateResultText(funLibrary.search_1).addFirstItemToTrolley_search_parameter().clickClearSearch()
				.enterSearchItem(funLibrary.search_2).clickSearchButton().validateResultText(funLibrary.search_2)
				.addFirstItemToTrolley_search_parameter();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT064- Verify the Product_Tile, Brand_Name, Product_Name, FatController, FC_Dropdown is present for the searched SKU")
	public void validateProductDetails_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Searching item and validating product details
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateProductDetails("HomePage").clickClearSearch().enterSearchItem(funLibrary.search_2)
				.clickSearchButton().validateProductDetails("HomePage").clickProduct()
				.validateProductDetails("ProductDetailPage");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT065- Verify Pagination")
	public void validatePagination_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Searching item and validating pagination
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validatePagination();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT066- Verify Tobacco and Liquor espots tile for restrictions")
	public void validateEverythingTabTobaccoLiquorEspotsTile_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("VIC 3166");
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateTobaccoWarningMsg()
				.clickHomeIcon().enterSearchItem(funLibrary.search_2).clickSearchButton().validateLiquorWarningMsg();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT067- Verify user can able add tobacco and liquor when user age >18")
	public void validateTobaccoLiquorRestrictionsMoreThanEighteen_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Selecting delivery location to Oakleigh
		funLibrary.LocalizeToHD("VIC 3166");
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateTobaccoEspotWarningMsg()
				.addFirstItemToTrolley().clickClearSearch().enterSearchItem(funLibrary.search_2).clickSearchButton()
				.validateLiquorEspotWarningMsg().addFirstItemToTrolley();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT068- Verify Tobacco and Liquor message at tile when user age <18")
	public void validateTobaccoLiquorRestrictionsLessThanEighteen_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("VIC 3166");
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().validateTobaccoWarningMsg()
				.clickHomeIcon().enterSearchItem(funLibrary.search_2).clickSearchButton()
				.validateLiquorEspotWarningMsg();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT069- Verify Temporarily Unavailable product fat controller and tile")
	public void validateTemporarilyUnavailableProduct_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Searching product and validating unavailable product title
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateTemporarilyUnavailableProductTile();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT070- Verify user can get redemption limit message when user trying to add more than product limit")
	public void validateRedemtionLimitMsg_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Searching product and validating redemtion limit msg
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateRedemtionLimitMsg("Home");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();

	}

	@Test(description = "WCS_AUT071- Verify user can get round up message when user trying to add decimal numbers")
	public void validateRoundUpMsg_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Searching product and validating error msg
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateRoundUpOrRoundDownMsg("Home", "RoundUp").clickTrolley();
		getPages.getHomePage().validateRoundUpOrRoundDownMsg("Trolley", "RoundUp");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT072- Verify user can get  round down message when user trying to add decimal numbers ")
	public void validateRoundDownMsg_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Searching product and validating error msg
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateRoundUpOrRoundDownMsg("Home", "RoundDown").clickTrolley();
		getPages.getHomePage().validateRoundUpOrRoundDownMsg("Trolley", "RoundDown");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT076- Verify WCS is displaying Help/Support in superbar")
	public void validateHelpAndSupportDetails_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating page title, customer details and email text
		getPages.getSuperBarPage().clickHelpSupport().validatePageTitle().validateCusomerDetails().validateEmailText()
				.closeSuperbar();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT077- Verify user can scrollup an click on red arrow button at buttom of the page")
	public void validateScrollUpArrow_LoggedInUser() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Validating home page header
		getPages.getHomePage().scrollDownHomePage(0, 2000).clickBackToTopIcon().validateHomePageHeader();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT126- Verify that user is able to add products to cart from Fat conroller.Verify and compare price and quantity in superbar")
	public void validateAddproductFromFatcontroller() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Add 1 quantity of searched product from Fat controller and compare
		// the price
		// in trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.add_update_fatcontroller_verifyDC("1", "Add", funLibrary.search_1);
		// Update to 4 quantity of searched product from Fat controller and
		// compare the
		// price in trolley
		getPages.getHomePage().add_update_fatcontroller_verifyDC("4", "Update", funLibrary.search_1);
		// Update to 3 quantity in trolley diet controller and compare the price
		getPages.getHomePage().update_dietcontroller_verifyFC("3", funLibrary.search_1);
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT127- Verify that user is able to add products from Product details page and verify price and quantity added in superbar.")
	public void validateAddproductFromPDPage() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Add 1 quantity of searched product from PD page and compare the price
		// in
		// trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton().clickProduct()
				.add_update_fatcontroller_verifyDC("1", "Add", funLibrary.search_1);
		getPages.getHomePage().add_update_fatcontroller_verifyDC("4", "Update", funLibrary.search_1);
		// Update to 3 quantity in trolley diet controller and compare the price
		getPages.getHomePage().update_dietcontroller_verifyFC("3", funLibrary.search_1);
		Thread.sleep(5000);
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT130-Verify user can add products using multi search")
	public void validateAddProductsFromMultiSearch() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing products from trolley
		getPages.getHomePage().clickTrolley().emptyTrolley();
		// Validating multi search
		getPages.getHomePage().clearMultiSearchList().clickSearchForListOfItems().sendItemsToMultiSearch1()
				.clickMultiSearchSaveAndSearchButton().ValidateMultiSearchIsFirstItemActiveByDefault()
				.clickMultiSearchHideButton().clickViewMySearchList().validateMultiSearch();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT147-Verify that user is able to update products from fat controller")
	public void validateUpdateproductFromFatcontroller() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Add 1 quantity of searched product from Fat controller
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.add_update_fatcontroller_verifyDC("1", "Add", funLibrary.search_1);
		// Update to 4 quantity of searched product from Fat controller
		getPages.getHomePage().add_update_fatcontroller_verifyDC("2", "Update", funLibrary.search_1);
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT148-Verify user can add 5 or more quantity of a product in cart from LHS/product tile FC")
	public void validateAdd5orMoreProductQtyFromFatcontroller() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Add 5orMore product quantity in fat controller
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "8").enter_Value_Into_FiveorMore("Update", "10");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT149-Verify user can remove one or more product from LHS/product tile FC")
	public void validateRemoveProductQtyFromFatcontroller() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Remove 4 to 1 quantity in fat controller
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "8").removeProductQtyFromFC();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT150-Verify that user is able to update quantity of products from Superbar")
	public void validateUpdateproductQtyFromTorlley() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding product to trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "8").clickTrolley();
		// update product quantity from trolley
		getPages.getTrolleyAndCheckoutPage().enter_Value_Into_FiveorMore_rhst("9")
				.enter_Value_Into_FiveorMore_rhst("12");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT151-Verify user can add 5 or more quantity of a product in cart from Superbar")
	public void validateUpdateQuantityMoreThan5InSuperbar() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding product to trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "2").clickTrolley();
		// Click 5 or more link in drop down and update qty
		getPages.getTrolleyAndCheckoutPage().enter_Value_Into_FiveorMore_rhst("5")
				.enter_Value_Into_FiveorMore_rhst("8");
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT152-Verify user is able to remove one or more products from trolley")
	public void validateRemoveProductQtyFromTrolley() {
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding product to trolley
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.enter_Value_Into_FiveorMore("Add", "8").clickClearSearch().enterSearchItem(funLibrary.search_2)
				.clickSearchButton().enter_Value_Into_FiveorMore("Add", "8");
		// Remove 1 product from trolley and Remove 4 to 1 quantity in diet
		// controller/
		// trolley
		getPages.getHomePage().removeFirstProductFromDC().removeProductQtyFromDC();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT154-Verify that system sorts the products added in cart according to the selected value in sort option")
	public void validateTrolleySorting() {
		double mov_value = 50;
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Adding multiple category products to trolley and validating last
		// added and
		// category sorting
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.trolleySortingCheck("LastAdded").validateLastAddedSorting().trolleySortingCheck("Category")
				.validateCategorySorting();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT158-Verify that user is not able to place order if cart value is less than $50")
	public void validateNotAbleToPlaceOrderLessThanFiftyDollar() throws Exception {
		double mov_value = 10;
		// Logged-in with registered user
		funLibrary.login();
		getPages.getHomePage().removeAllItemsFromTrolley();
		if (!getPages.getHomePage().isSlotSelected()) {
			getPages.getSuperBarPage().clickChooseDeliverTime().clickHDSlotLink1().HDSlotSelector("signed", true,
					false);
			getPages.myAccountPage().closeSuperBar();
		} else {
			testLog.info("Slot is already selected");
		}
		// Add Products from in trolley
		getPages.getHomePage().add_SKUs_To_Trolley(funLibrary.search_1, "Y", mov_value).clickTrolley()
				.clickProceedToCheckOutButton().validateTrolleyWarningMessage().clickGoBackToTrolley();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT221 - validate that user is able to change HD slot to CC slot")
	public void validateChangeDeliverySlotHDToCC() throws Exception {
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
				false);
		// change to CC address and select CC slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickChooseDifferentTimeLink().clickChooseALocationLinkDSS()
				.enterCCSuburb("0820 bayview").clickFirstClickAndCollectSuburbDSS()
				.clickChooseCollectionTimeButtonDSS();
		// select cc slot
		getPages.getDeliverySlotPage().CCSlotSelector(true, false, false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT222 - validate that user is able to change HD slot to RD slot")
	public void validateChangeDeliverySlotHDToRD() throws Exception {
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
				false);
		// change to RD address and select RD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickChangeDelivery().clickHDAddress("NT 0822");
		getPages.getChooseDeliveryTimePage().selectFirstRDProviderAddress().RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT223 - validate that user is able to change CC slot to HD slot")
	public void validateChangeDeliverySlotCCToHD() throws Exception {
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select CC slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		// change to HD address and select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickChooseDifferentTimeLink()
				.clickChooseDeliveryAddressLinkDSS().clickHDAddressDSS("NT 0820").HDSlotSelector("signed", true, false);

		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT224 - validate that user is able to change CC slot to RD slot")
	public void validateChangeDeliverySlotCCToRD() throws Exception {
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select CC slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		funLibrary.LocalizeToHD("NT 0820");
		// select RD address
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT225 - validate that user is able to change RD slot to HD slot")
	public void validateChangeDeliverySlotRDToHD() throws Exception {
		// Logged-in user
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select RD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select HD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickHDAddress("NT 0820").HDSlotSelector("signed", true,
				false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "WCS_AUT226 - validate that user is able to change RD slot to CC slot")
	public void validateChangeDeliverySlotRDToCC() throws Exception {
		// Logged-in userfgf
		funLibrary.login();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select RD slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickFirstRDAddressLink().selectFirstRDProviderAddress()
				.RDSlotSelector("AnyDay");
		getPages.myAccountPage().closeSuperBar();
		// Localize to HD address
		funLibrary.LocalizeToHD("NT 0820");
		// Select CC slot
		getPages.getSuperBarPage().clickChooseDeliverTime().clickCCSlotLink1().CCSlotSelector(true, false, false);
		// Logging out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "validate pricing for single item with multi buy discount - Multisave Single SKU", enabled = false)
	public void validateSingleItemWithMultiBuyDiscount() throws Exception {
		// Logged-in with registered user
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		// Verifying the pricing for multiple products
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateMultiSave_SingleSKUPrice(true).enter_Value_Into_FiveorMore("Add", "1")
				.validateSingleItemPrice().validateSuccessGreenIcon_MultiBuy()
				.validateProductPricing_withTrollyAmount();
		// Logging-out user
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}

	@Test(description = "validate pricing for multiple items for multi buy discount - Multisave Multi SKU", enabled = false)
	public void validateMultipleItemsWithMultiBuyDiscount() throws Exception {
		// Logged-in with registered user
		int quantityOfProducts = 2;
		funLibrary.login();
		// Removing all items from trolley
		getPages.getHomePage().removeAllItemsFromTrolley();
		getPages.getHomePage().enterSearchItem(funLibrary.search_1).clickSearchButton()
				.validateMultiSave_SingleSKUPrice(false)
				.enter_Value_Into_FiveorMore("Add", String.valueOf(quantityOfProducts))
				.validateMultipleItemPrice(quantityOfProducts).validateSuccessGreenIcon_MultiBuy()
				.validateProductPricing_withTrollyAmount();
		getPages.getSuperBarPage().clickMyAccount().clickLogout();
	}
}
