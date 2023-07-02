package com.swiftshop.tests;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class LocalizationTests extends Base_Class_Browser {

	@Test(description = "WCS_AUT022 - Verify guest user is able to localize to some other suburb")
	public void validateLocalization() throws AWTException, InterruptedException, Exception {
        // Validating the localization for different GEO locations
		for (int RowNo = 2; RowNo <= datatable.get().getRowCount("Localization"); RowNo++) {
			funLibrary.LocationName = datatable.get().getCellData("Localization", "LocationName", RowNo);
			getPages.getHomePage().clickGeoLocationChangeLink().enterGeoLocation(funLibrary.LocationName)
					.selectFirstSuggestedGeoLocation().validateGeoLocation();
		}
	}
}
