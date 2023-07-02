package com.swiftshop.tests;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.swiftshop.api.EbayOrders;

public class EbayTests extends EbayOrders{	
	
	public String ebayId;
	CloseableHttpResponse closeableHttpResponse;
	EbayOrders ebayOrders;

	@Test(description = "WCS_AUT233-Verify ebay delivery external order creation with valid API")
	public void validateEbayExternalOrder_API() throws JsonGenerationException, JsonMappingException, IOException,
			KeyManagementException, NoSuchAlgorithmException {
		// Set all POST request headers
		HashMap<String, String> headers = ebayAPIHeaders("post");
		// Get current date
		String currentDate = getCurrentDate();
		// Get unique ebay number
		ebayId = getUniqueEbayOrderID();
		String ebayPostReqJson = postRequestJson(ebayId, currentDate);
		// Send Ebay POST request to place Ebay order
		closeableHttpResponse = post(ebay_posturl, ebayPostReqJson, headers);
		// Validate success status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		validateEbayOrderPlaced(statusCode);
	}

	@Test(description = "WCS_AUT234-Verify ebay delivery external order creation with invalid request")
	public void validateEbayOrderWithInvalidReq_API()
			throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException {
		
		// Set all POST request headers
		HashMap<String, String> headers = ebayAPIHeaders("post");
		// Get yesterday's date (Invalid date)
		String yesterdayDate = getYesterdayDate();
		// Using existing EbayID for invalid request
		String ebayPostReqJson = postRequestJson(ebayId, yesterdayDate);
		// Send Ebay POST invalid request with yesterday's date
		closeableHttpResponse = post(ebay_posturl, ebayPostReqJson, headers);
		// Validate Bad status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		validateEbayOrderNotPlaced(statusCode);
	}

	@Test(description = "WCS_AUT235-Verify ebay delivery for modify order with valid API")
	public void validateModifyEbayOrder_API() throws JsonGenerationException, JsonMappingException, IOException,
			KeyManagementException, NoSuchAlgorithmException {
		
		HashMap<String, String> headers = ebayAPIHeaders("post");
		// Place Ebay order
		this.validateEbayExternalOrder_API();
		// Get available slot to modify Ebay order
		getEbaySlots(ebayId);
		// Modify Json request with available slot details
		String ebayModifiedJson = modifyputRequestJson(ebayId);
		// Send Ebay PUT request to modify Ebay order
		closeableHttpResponse = put(ebay_puturl, ebayModifiedJson, headers);
		// Validate success status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		validateModifyEbayOrder(statusCode);
	}

	@Test(description = "WCS_AUT236-Verify ebay delivery  for modify order with invalid request")
	public void validateModifyEbayOrderWithInvalidReq_API() throws JsonGenerationException, JsonMappingException,
			KeyManagementException, NoSuchAlgorithmException, IOException {
		
		HashMap<String, String> headers = ebayAPIHeaders("post");
		// Place Ebay order
		this.validateEbayExternalOrder_API();
		// Get invalid slot details to modify Ebay order
		getInValidEbaySlots();
		// Modify Json request with invalid slot details
		String ebayModifiedJson = modifyputRequestJson(ebayId);
		// Send Ebay PUT request to modify Ebay order
		closeableHttpResponse = put(ebay_puturl, ebayModifiedJson, headers);
		// Validate Bad status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		validateEbayOrderNotModified(statusCode);
	}
}
