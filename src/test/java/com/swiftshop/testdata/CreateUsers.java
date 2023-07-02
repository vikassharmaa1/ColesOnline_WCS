package com.swiftshop.testdata;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;
import com.swiftshop.pages.GetPages;

public class CreateUsers extends Base_Class_Browser {
	GetPages getPages = new GetPages();
	public String username = "";
	public String password = "";
	public String first_name = "";
	public String last_name = "";
	public String reg_streetAddr = "";
	public String reg_suburb = "";
	public String reg_postcode = "";
	public String reg_state = "";
	public String reg_nickname = "";
	String sheetname = "";
	@Test
	public void createNewUsers() throws Exception {

//		String userid_list = datatable.getCellData("Test_Data", "Email_ID", RowNo);
//		String[] userid = userid_list.split(",");

		testLog.info("TD_Register_HD_Addresses started");
		
		for (int i = 2; i <= datatable.get().getRowCount(sheetname); i++) {
			username=datatable.get().getCellData(sheetname, "username",i);
			password=datatable.get().getCellData(sheetname, "password",i);
			first_name = datatable.get().getCellData(sheetname, "Firstname",i);
			testLog.info("Firstname for test is " + first_name);
			last_name = datatable.get().getCellData(sheetname, "Lastname", i);
			testLog.info("lastname for test  is  " + last_name);					
			//reg_password = datatable.getCellData(sheetname, "password", i);
			//testLog.info("password for test  is  " + reg_password);	//				
			reg_streetAddr=datatable.get().getCellData(sheetname, "streetAddr", i);
			reg_suburb=datatable.get().getCellData(sheetname, "suburb", i);
			reg_postcode=datatable.get().getCellData(sheetname, "postcode", i);
			reg_state=datatable.get().getCellData(sheetname, "state", i);
			reg_nickname = datatable.get().getCellData(sheetname, "Nickname", i);
			
//			getPages.getSuperBarPage().clickLoginSignup().clickNewToColesLink().enterFirstNameLastName().enterEmailID(username)
//					.enterPassword().ClickContinueButton().enterAddressDetails().validateAccountCreationConfirmationPopup("HD");

			getPages.getRegistrationPage().validateSuccessMsg().clickContinueShoppingBtn();
			getPages.getSuperBarPage().clickMyAccount().clickLogout();

		}
	}
	
	@BeforeTest
	public void beforeTest() {
		sheetname = "TestData";
	}

}
