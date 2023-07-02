package com.swiftshop.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtilities {

	private Statement dbStatement = null;
	private Connection conn = null;

	public DatabaseUtilities() {
		Initializer();
	}

	private void Initializer() {
		String userName = "cautotes";
		String password = "Auto@2025#";
		
		//V7 Database
		//String hostName = "utecommdb1"; String DBName = "TZCSA00";
		
		//V8 Database
		String DBName = "TZCSG00"; 	String hostName = "utecommdb10.cmltd.net.au";

		String dbURL = "jdbc:db2://" + hostName + ":60000/" + DBName;

		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection(dbURL, userName, password);
			if (conn != null) {
				System.out.println("Great!! Successfully! connected with DB2 Database.");
			} else {
				System.out.println("Oops!! Something went wrong! DB2 connection Failed! ");
			}
			dbStatement = conn.createStatement();

		} catch (Exception e) {
			System.out.println("Connection Issue " + e.getMessage());
		}

	}
    
	// Function getting OTP for stepUp authentication from database
	public String getOTP(final String emailId) throws Exception {
		String otp = "";
		final String sql = "select stringvalue from mbrattrval where mbrattr_id=800 and "
				+ "member_id = (select users_id from users where lower(field1)=?)";
		try {
			final PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, emailId.toLowerCase());
			final ResultSet rs = ps.executeQuery();
			//wait(3000);
			while (rs.next()) {
				otp = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while fetching OTP: " + e.getMessage());
		}
		return otp;
	}
	
public void InsertUserIntoMBRGRPMBR(final String emailId) throws Exception {		
	
	final String sql = "INSERT INTO MBRGRPMBR (MEMBER_ID,MBRGRP_ID,FIELD1,CUSTOMERID,EXCLUDE,OPTCOUNTER) \r\n" + 
			"VALUES ((select users_id from users where field1 = '"+emailId.toLowerCase()+"'),8800000000000116023,null,null,'0',1)";
		
		try {
			Statement statement = conn.createStatement();
			// insert the user into delivery plus invitee segment data
			statement.executeUpdate(sql);
			
			conn.commit();
			
		System.out.println("Insert query committed successfully");
			
			Thread.sleep(2000);	
			
		} catch (SQLException e) {
			System.out.println("Something went wrong while inserting data into table MBRGRPMBR: " + e.getMessage());
		}
	}

	public void InsertUserIntoColesPlusInviteeFlybuysTrail(final String emailId) throws Exception {
		
		final String sql = "INSERT INTO MBRGRPMBR (MEMBER_ID,MBRGRP_ID,FIELD1,CUSTOMERID,EXCLUDE,OPTCOUNTER) \r\n" + 
				"VALUES ((select users_id from users where field1 = '"+emailId.toLowerCase()+"'),8800000000000116027,null,null,'0',1)";
		try {
			Statement statement1 = conn.createStatement();
			// insert the user into ColesPlusInviteeFlybuysTrailsegment data
			statement1.executeUpdate(sql);	
			
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Something went wrong while inserting data into table ColesPlusInviteeFlybuysTrail: " + e.getMessage());
		}
	}

	// Function closing the database connection
	public void closeDBConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
			System.out.println("Closing database connection...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
