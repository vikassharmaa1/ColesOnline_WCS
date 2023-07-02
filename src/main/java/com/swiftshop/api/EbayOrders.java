package com.swiftshop.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class EbayOrders {	
	
	public String ebay_posturl="";
	public String ebay_geturl="";
	public String ebay_puturl="";

	private final String POST_SLOTS_REQUEST_ACCESS_TOKEN = "BmC4HhpzaUSlldW8ojmgEw==";
	private final String GET_SLOTS_REQUEST_ACCESS_TOKEN = "788aba50-25e5-4a14-ad36-214414f075dd";
	private final String GET_SLOTS_REQUEST_CONTENT_TYPE = "application/json";
	private final String GET_SLOTS_REQUEST_EBAY = "eBay";
	public String UniqueEbayID;
	public String id = null;
	public String shiftId = null;
	public String slotTimeStart = null;
	public String slotTimeEnd = null;
	public String utc = null;
	public String local = null;
	CloseableHttpResponse closeableHttpResponse;
	public static Logger testLog = Logger.getLogger("debugLogger:" + Thread.currentThread().getId());
	
	@BeforeMethod
	public void setUp() throws Exception {
		Properties logProperties = new Properties();
		logProperties
				.load(new FileInputStream("C:/HCL/TESTAUTOMATION/ColesOnline_WCS/src/main/java/baseConfig.properties"));		

		PropertyConfigurator.configure(logProperties);
		for (String name : logProperties.stringPropertyNames()) {
			String value = logProperties.getProperty(name);
			System.setProperty(name, value);
		}
		ebay_posturl = System.getProperty("EbayPostURL");
		ebay_geturl = System.getProperty("EbayGetURL");
		ebay_puturl = System.getProperty("EbayPutURL");
		
		logProperties.load(new FileInputStream(
				"C:/HCL/TESTAUTOMATION/ColesOnline_WCS" + "/src/main/java/conflib/log4j_Firefox.properties"));
		PropertyConfigurator.configure(logProperties);		
	}
	
	public void assertCheck(String methodname, String errormsg) {
		Assert.assertEquals(1, 0, "MethodName:" + methodname + ", ErrorMsg:" + errormsg);
	}
	public void wait(int wait) {
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> ebayAPIHeaders(String req) {
		HashMap<String, String> headerMap = new HashMap<String, String>();
		if(req.equals("post")) {
			headerMap.put("Content-Type", GET_SLOTS_REQUEST_CONTENT_TYPE);
			headerMap.put("Access-Token", POST_SLOTS_REQUEST_ACCESS_TOKEN);
			headerMap.put("From", GET_SLOTS_REQUEST_EBAY);
		}if(req.equals("get")) {
			headerMap.put("Content-Type", GET_SLOTS_REQUEST_CONTENT_TYPE);
			headerMap.put("Access-Token", GET_SLOTS_REQUEST_ACCESS_TOKEN);
			headerMap.put("From", GET_SLOTS_REQUEST_EBAY);
		}
		return headerMap;
	}
	
	public String getCurrentDate() {
		Date date = new Date();
		String PresentDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		testLog.info("Current date is: "+PresentDate);
		return PresentDate;
	}
	
	public String getYesterdayDate() {
		 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.DATE, -1);
		 Date yesterday = calendar.getTime();
		 String yesterdayDate= new SimpleDateFormat("yyyy-MM-dd").format(yesterday);
		 testLog.info("Yesterday's date is: "+yesterdayDate);
		 return yesterdayDate;
	}
	
	public String getUniqueEbayOrderID() {
		wait(10000);
		Date date = new Date();
		String currentdatentime= new SimpleDateFormat("ddMMyyyyHHmmss").format(date);
		String str1 = currentdatentime.substring(1, 4);
		String str2 = currentdatentime.substring(10, 13);
		String Id = str1+str2;
		UniqueEbayID = "EBAY"+Id;
	    testLog.info("Unique Ebay Order ID is: "+UniqueEbayID);
		return UniqueEbayID;
	}
	
	public String postRequestJson(String EbayID, String PresentDate) {
		String ebayOrderJsonString = "{" + "  \"order\":{" + "     \"orderInformation\":{" + "        \"externalOrderId\":\""+EbayID+"\"," + "        \"channelId\":\"eBay\"," + "        \"correlationId\":\"41921\"," + "        \"baggingPreference\":2," + "        \"placedDate\":\""+PresentDate+"T17:45:30.000Z\"," + "        \"createDate\":\""+PresentDate+"T17:45:30.000Z\"" + "     }," + "     \"orderAmount\":{" + "        \"grandTotal\":\"10.40\"," + "        \"totalProductPrice\":\"9.40\"," + "        \"totalAdjustment\":\"0.00\"," + "        \"deliveryFee\":\"1.00\"," + "        \"gstAmountIncluded\":\"0.94\"," + "        \"totalSavings\":\"7.70\"" + "     }," + "     \"paymentInformation\":{" + "        \"payments\":[" + "           {" + "              \"receiptId\":\"6SH70436AN613511\"," + "              \"type\":\"PAYPAL\"," + "              \"transactionId\":\"45621\"," + "              \"amount\":\"10.40\"" + "           }" + "        ]" + "     }," + "     \"orderItems\":[" + "        {" + "           \"externalOrderItemId\":337489," + "           \"partNumber\":\"8432858\"," + "           \"name\":\"Bubble Bath\"," + "           \"brand\":\"Coles\"," + "           \"quantity\":3," + "           \"amount\":{" + "              \"unitPrice\":\"1.80\"," + "              \"totalPrice\":\"1.80\"," + "              \"totalAdjustment\":\"0.00\"," + "              \"savings\":\"4.74\"," + "              \"gstAmountIncluded\":\"0.16\"," + "              \"freeGift\":false" + "           }," + "           \"createDate\":\""+PresentDate+"T17:45:30.000Z\"," + "           \"lastUpdateDate\":\""+PresentDate+"T17:45:30.000Z\"," + "           \"totalWeight\":\"9.0\"," + "           \"onSpecial\":false," + "           \"substituteAllowed\":false" + "        }," + "        {" + "           \"externalOrderItemId\":1374890," + "           \"partNumber\":\"6834861\"," + "           \"name\":\"Talcum Powder\"," + "           \"brand\":\"Coles\"," + "           \"quantity\":2," + "           \"amount\":{" + "              \"unitPrice\":\"2.00\"," + "              \"totalPrice\":\"2.00\"," + "              \"totalAdjustment\":\"0.00\"," + "              \"savings\":\"2.96\"," + "              \"gstAmountIncluded\":\"0.18\"," + "              \"freeGift\":false" + "           }," + "           \"createDate\":\""+PresentDate+"T17:45:30.000Z\"," + "           \"lastUpdateDate\":\""+PresentDate+"T17:45:30.000Z\"," + "           \"totalWeight\":\"12.0\"," + "           \"onSpecial\":false," + "           \"substituteAllowed\":false" + "        }" + "     ]," + "     \"deliveryInformation\":{" + "     	\"deliveryLinkIdentifier\": \"ce7fe903700bb3b4dc2b81dd5c613eb2\"," + "        \"deliveryType\":{" + "           \"serviceType\":\"HD\"," + "           \"subServiceType\":\"UD\"" + "        }," + "        \"deliveryAddress\":{" + "           \"contactName\":{" + "              \"firstName\":\"testebay\"," + "              \"lastName\":\"user\"" + "           }," + "           \"address\":{" + "              \"addressLine1\":\"123 High Street\"," + "              \"addressLine2\":\"test\"," + "              \"suburb\":\"BAYVIEW\"," + "              \"state\":\"NT\"," + "              \"country\":\"AU\"," + "              \"postcode\":\"0820\"," + "              \"externalAddressId\":\"test45321\"," + "              \"latitude\":-12.43979977," + "              \"longitude\":130.854011" + "           }," + "           \"phone\":[" + "              {" + "                 \"type\":\"Mobile\"," + "                 \"value\":\"0400123456\"" + "              }" + "           ]," + "           \"email\":[" + "              {" + "                 \"preferred\":true," + "                 \"value\":\"Neeraj.Rana@coles.com.au\"" + "              }" + "           ]" + "        }" + "     }," + "     \"customerInformation\":{" + "        \"externalCustomerId\":\"1181706\"," + "        \"contactName\":{" + "         \"firstName\":\"testebay\"," + "         \"lastName\":\"user\"" + "        }," + "        \"phone\":[" + "           {" + "              \"type\":\"Mobile\"," + "              \"value\":\"0400123456\"" + "           }" + "        ]," + "        \"email\":[" + "           {" + "              \"preferred\":true," + "              \"value\":\"Neeraj.Rana@coles.com.au\"" + "           }" + "        ]" + "     }" + "  }" + "}";
		return ebayOrderJsonString;
	}
	
	public EbayOrders validateEbayOrderPlaced(int statusCode) {
		testLog.info("Status Code is: "+statusCode);
		if(statusCode==201) {
			testLog.info("Ebay External order is placed");
		}else {
			testLog.error("Ebay External order is not placed due to may be >=6 hours slots are not present in DSS page OR EbayOrderID in not Unique");
			assertCheck("validateEbayOrderPlaced", "Ebay External order is not placed due to may be >=6 hours slots are not present in DSS page OR EbayOrderID in not Unique");
		}
		return this;
	}
	
	public EbayOrders validateEbayOrderNotPlaced(int statusCode) {
		testLog.info("Status Code is: "+statusCode);
		if(!(statusCode==201)) {
			testLog.info("Ebay External order is Not placed due to invalid request");
		}else {
			testLog.error("Ebay External Should not place due to invalid request");
			assertCheck("validateEbayOrderNotPlaced", "Ebay External Should not place due to invalid request");
		}
		return this;
	}

	public EbayOrders validateGetAllEbayOrder(int statusCode) {
		testLog.info("Status Code is: "+statusCode);
		if(statusCode==201) {
			testLog.info("All available slots are present to modify");
		}else {
			testLog.error("No slots are present to modify");
			assertCheck("validateGetAllEbayOrder", "No slots are present to modify");
		}
		return this;
	}
	
	public EbayOrders validateModifyEbayOrder(int statusCode) {
		testLog.info("Status Code is: "+statusCode);
		if(statusCode==201) {
			testLog.info("Ebay order is modified");
		}else {
			testLog.error("Ebay order is not modified");
			assertCheck("validateModifyEbayOrder", "Ebay order is not modified");
		}
		return this;
	}
	
	public EbayOrders validateEbayOrderNotModified(int statusCode) {
		testLog.info("Status Code is: "+statusCode);
		if(!(statusCode==201)) {
			testLog.info("Ebay order is Not modified due to invalid request");
		}else {
			testLog.error("Ebay order Should not modify due to invalid request");
			assertCheck("validateEbayOrderNotModified", "Ebay order Should not modify due to invalid request");
		}
		return this;
	}
	
	public EbayOrders getValuesFromResponseToModify(JSONObject responseJson) {
		id=getValueByJPath(responseJson, "slots/homeDelivery[1]/id");
	    shiftId=getValueByJPath(responseJson, "slots/homeDelivery[1]/shiftId");
	    slotTimeStart=getValueByJPath(responseJson, "slots/homeDelivery[1]/slotTime/start");
	    slotTimeEnd=getValueByJPath(responseJson, "slots/homeDelivery[1]/slotTime/end");
		utc=getValueByJPath(responseJson, "slots/homeDelivery[1]/cutoffTime/utc");
		local=getValueByJPath(responseJson, "slots/homeDelivery[1]/cutoffTime/local");
		if((id != null) && (shiftId != null) && (slotTimeStart != null) && (slotTimeEnd != null) && (utc != null) && (local != null) ) {
			 testLog.info("ID value from response is: "+id);
			 testLog.info("ShiftId value from response is: "+shiftId);
			 testLog.info("SlotTimeStart value from response is: "+slotTimeStart);
			 testLog.info("SlotTimeEnd value from response is: "+slotTimeEnd);
			 testLog.info("UTC value from response is: "+utc);
			 testLog.info("Local value from response is: "+local);
		}else {
			testLog.error("values are not present to modify the slot");
			assertCheck("getValuesFromResponseToModify", "values are not present to modify the slot");
		}
		return this;
	}
	
	public EbayOrders getInValidValuesToModify(JSONObject responseJson) {
		id=getValueByJPath(responseJson, "slots/homeDelivery[1]/id");
	    shiftId=getValueByJPath(responseJson, "slots/homeDelivery[1]/shiftId");
	    slotTimeStart=this.getYesterdayDate();
	    slotTimeEnd=this.getYesterdayDate();
		utc=getValueByJPath(responseJson, "slots/homeDelivery[1]/cutoffTime/utc");
		local=getValueByJPath(responseJson, "slots/homeDelivery[1]/cutoffTime/local");
		if((id != null) && (shiftId != null) && (utc != null) && (local != null) ) {
			 testLog.info("ID value from response is: "+id);
			 testLog.info("ShiftId value from response is: "+shiftId);
			 testLog.info("UTC value from response is: "+utc);
			 testLog.info("Local value from response is: "+local);
		}else {
			testLog.error("values are not present to modify the slot");
			assertCheck("getInValidValuesToModify", "values are not present to modify the slot");
		}
		
		return this;
	}
	
	public String modifyputRequestJson(String ebayID) {
		String modifiedJson ="{" + "	\"order\": {" + "		\"orderInformation\": {" + "			\"externalOrderId\": \""+ebayID+"\"," + "			\"channelId\": \"eBay\"," + "			\"correlationId\": \"41921\"," + "			\"baggingPreference\": 2," + "			\"placedDate\": \"2019-08-14T17:45:30.000Z\"," + "			\"createDate\": \"2019-08-14T17:45:30.000Z\"" + "		}," + "		\"orderAmount\": {" + "			\"grandTotal\": \"10.40\"," + "			\"totalProductPrice\": \"9.40\"," + "			\"totalAdjustment\": \"0.00\"," + "			\"deliveryFee\": \"1.00\"," + "			\"gstAmountIncluded\": \"0.94\"," + "			\"totalSavings\": \"7.70\"" + "		}," + "		\"paymentInformation\": {" + "			\"payments\": [{" + "				\"receiptId\": \"6SH70436AN613511\"," + "				\"type\": \"PAYPAL\"," + "				\"transactionId\": \"45621\"," + "				\"amount\": \"10.40\"" + "			}]" + "		}," + "		\"orderItems\": [{" + "			\"externalOrderItemId\": 337489," + "			\"partNumber\": \"8432858\"," + "			\"name\": \"Bubble Bath\"," + "			\"brand\": \"Coles\"," + "			\"quantity\": 3," + "			\"amount\": {" + "				\"unitPrice\": \"1.80\"," + "				\"totalPrice\": \"1.80\"," + "				\"totalAdjustment\": \"0.00\"," + "				\"savings\": \"4.74\"," + "				\"gstAmountIncluded\": \"0.16\"," + "				\"freeGift\": false" + "			}," + "			\"createDate\": \"2019-08-14T17:45:30.000Z\"," + "			\"lastUpdateDate\": \"2019-08-14T17:45:30.000Z\"," + "			\"totalWeight\": \"9.0\"," + "			\"onSpecial\": false," + "			\"substituteAllowed\": false" + "		}," + "		{" + "			\"externalOrderItemId\": 1374890," + "			\"partNumber\": \"6834861\"," + "			\"name\": \"Talcum Powder\"," + "			\"brand\": \"Coles\"," + "			\"quantity\": 2," + "			\"amount\": {" + "				\"unitPrice\": \"2.00\"," + "				\"totalPrice\": \"2.00\"," + "				\"totalAdjustment\": \"0.00\"," + "				\"savings\": \"2.96\"," + "				\"gstAmountIncluded\": \"0.18\"," + "				\"freeGift\": false" + "			}," + "			\"createDate\": \"2019-08-14T17:45:30.000Z\"," + "			\"lastUpdateDate\": \"2019-08-14T17:45:30.000Z\"," + "			\"totalWeight\": \"12.0\"," + "			\"onSpecial\": false," + "			\"substituteAllowed\": false" + "		}]," + "		\"deliveryInformation\": {" + "			\"deliveryLinkIdentifier\": \"ce7fe903700bb3b4dc2b81dd5c613eb2\"," + "			\"deliveryWindow\": {" + "				\"cutoffTime\": {" + "					\"utc\": \""+utc+"\"," + "					\"local\": \""+local+"\"" + "				}," + "				\"id\": \""+id+"\"," + "				\"serviceType\": \"HD\"," + "				\"shiftId\": \""+shiftId+"\"," + "				\"shipCharge\": \"14.00\"," + "				\"slotTime\": {" + "					\"start\": \""+slotTimeStart+"\"," + "					\"end\": \""+slotTimeEnd+"\"" + "				}," + "				\"status\": \"OPEN\"" + "			}," + "			\"deliveryType\": {" + "				\"serviceType\": \"HD\"," + "				\"subServiceType\": \"UD\"" + "			}," + "			\"deliveryAddress\": {" + "				\"contactName\": {" + "					 \"firstName\":\"testebay\"," + "            		 \"lastName\":\"user\"" + "				}," + "				\"address\": {" + "					\"addressLine1\": \"123 High Street\"," + "					\"addressLine2\": \"test\"," + "					\"suburb\": \"BAYVIEW\"," + "					\"state\": \"NT\"," + "					\"country\": \"AU\"," + "					\"postcode\": \"0820\"," + "					\"externalAddressId\": \"test45321\"," + "					\"latitude\": -12.43979977," + "					\"longitude\": 130.854011" + "				}," + "				\"phone\": [{" + "					\"type\": \"Mobile\"," + "					\"value\": \"0400123456\"" + "				}]," + "				\"email\": [{" + "					\"preferred\": true," + "					\"value\": \"0400123456\"" + "				}]" + "			}" + "		}," + "		\"customerInformation\": {" + "			\"externalCustomerId\": \"1181706\"," + "			\"contactName\": {" + "				\"firstName\":\"testebay\"," + "        		\"lastName\":\"user\"" + "			}," + "			\"phone\": [{" + "				\"type\": \"Mobile\"," + "				\"value\": \"0400123456\"" + "			}]," + "			\"email\": [{" + "				\"preferred\": true," + "				\"value\": \"Neeraj.Rana@coles.com.au\"" + "			}]" + "		}" + "	}" + "}";
		return modifiedJson;
	}
	
	public EbayOrders getEbaySlots(String ebayId) throws JsonGenerationException, JsonMappingException, IOException, KeyManagementException, NoSuchAlgorithmException {
		// Set all GET request headers
		HashMap<String, String> headers = this.ebayAPIHeaders("get");
		String geturlEbayId = ebay_geturl.replace("EBAY907285", ebayId);
		// Send Ebay GET request to get all available slots
		closeableHttpResponse = this.get(geturlEbayId,headers);
		// Validate success status code 
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		this.validateGetAllEbayOrder(statusCode);
		// Get Response in JSON
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		// Get available slot details from response to modify
		this.getValuesFromResponseToModify(responseJson);
		return this;
		
	}
	
	public EbayOrders getInValidEbaySlots() throws JsonGenerationException, JsonMappingException, IOException, KeyManagementException, NoSuchAlgorithmException {
		// Set all GET request headers
		HashMap<String, String> headers = this.ebayAPIHeaders("get");
		String geturlEbayId = ebay_geturl.replace("EBAY907285", UniqueEbayID);
		// Send Ebay GET request to get all available slots
		closeableHttpResponse = this.get(geturlEbayId,headers);
		// Validate success status code 
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		this.validateGetAllEbayOrder(statusCode);
		// Get Response in JSON
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		// Get available slot details from response to modify
		this.getInValidValuesToModify(responseJson);
		return this;
		
	}
	
	//Get method 
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getAllTrustingTrustManager(), new java.security.SecureRandom());
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setHostnameVerifier(getAllTrustingHostNameVerifier()).build();
		HttpGet httpget = new HttpGet(url);//http get request
		for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
		return closeableHttpResponse;
	}
	
	// Post Method
	public  CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException ,ClientProtocolException, NoSuchAlgorithmException, KeyManagementException	 {
		SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getAllTrustingTrustManager(), new java.security.SecureRandom());
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setHostnameVerifier(getAllTrustingHostNameVerifier()).build();
		HttpPost httppost = new HttpPost(url); // http post req
		httppost.setEntity(new StringEntity(entityString)); //for payload
		for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
		return closeableHttpResponse;
	}
	
	//Put Method
	public  CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws IOException ,ClientProtocolException, NoSuchAlgorithmException, KeyManagementException	 {
		SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getAllTrustingTrustManager(), new java.security.SecureRandom());
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setHostnameVerifier(getAllTrustingHostNameVerifier()).build();
		HttpPut httpput = new HttpPut(url);
		httpput.setEntity(new StringEntity(entityString));
		for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
			httpput.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpput);
		return closeableHttpResponse;	
	}
	
	public  TrustManager[] getAllTrustingTrustManager() {
        final String METHOD_NAME = "getAllTrustingTrustManager";

        // Create and return a trust manager that does not validate certificate chains
        return new TrustManager[] { new X509TrustManager() {
              public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[] {};
              }
              public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    System.out.println("Overriding checkClientTrusted method to not valiate client certificate." + new Object[] {
                                certs, authType });
              }
              public void checkServerTrusted(X509Certificate[] certs, String authType) {
            	  System.out.println("Overriding checkServerTrusted method to not valiate server certificate." + new Object[] {
                                certs, authType });
              }
        } };
  }

	public  X509HostnameVerifier getAllTrustingHostNameVerifier() {
        final String METHOD_NAME = "getAllTrustingHostNameVerifier";
        final String logMessage = "Overriding verify method to trust all hosts.";
        // Create and return all-trusting host name verifier
        return new X509HostnameVerifier() {
	              public boolean verify(String hostname, SSLSession session) {
	                    System.out.println(logMessage + new Object[] { hostname, session });
	                    return true;
	              }
	              public void verify(String paramString, SSLSocket paramSSLSocket) throws IOException {
	            	    System.out.println(logMessage + new Object[] { paramString, paramSSLSocket });
	              }
	              public void verify(String paramString, X509Certificate paramX509Certificate) throws SSLException {
	            	    System.out.println(logMessage + new Object[] { paramString, paramX509Certificate });
	              }
	              public void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) throws SSLException {
	            	    System.out.println(logMessage + new Object[] { paramString, paramArrayOfString1, paramArrayOfString2 });
	              }
	        };
	  }
	
	public String getValueByJPath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;
		if (((JSONObject) obj).length() == 1) {
			jpath = jpath.replace("1", "0");
		}
		for (String s : jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if (s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}

}
