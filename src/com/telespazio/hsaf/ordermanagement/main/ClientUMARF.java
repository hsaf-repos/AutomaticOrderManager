package com.telespazio.hsaf.ordermanagement.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


// This class wraps the UMARF functionalities to retrieve orders, to acknowledge order or to reject an order.
public class ClientUMARF {

	private static final String GET_ORDER_LIST_CMD         = "/UMARF/safclient/bin/scripts/hsafGetOrderList.sh";
	private static final String COMPLETE_ORDER_FAIL_CMD    = "/UMARF/safclient/bin/scripts/hsafCompleteOrder_fail.sh";
	private static final String ACKNOWLEDGE_ORDER_CMD      = "/UMARF/safclient/bin/scripts/hsafAcknowledgeOrder.sh";
	
	public static void getOrderList(){
		
		try {
			
			
			Runtime rt = Runtime.getRuntime();
			
			
			System.out.println("DEBUG > CLICK BUTTON REFRESH ORDERS : executed script <" + ClientUMARF.GET_ORDER_LIST_CMD + ">");

			
			Process proc = rt.exec(ClientUMARF.GET_ORDER_LIST_CMD);
			proc.waitFor();
			
			System.out.println("DEBUG > CLICK BUTTON REFRESH ORDERS : All table items removed");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void completeOrder_fail(String orderId, String comment){
		
		try {
			
			
			Runtime rt = Runtime.getRuntime();
			
			
			System.out.println("DEBUG > CLICK BUTTON REJECT ORDERS : executed script <" + ClientUMARF.COMPLETE_ORDER_FAIL_CMD + ">");

			
			Process proc = rt.exec(ClientUMARF.COMPLETE_ORDER_FAIL_CMD + " " + orderId + " \"" + comment + "\"");
			proc.waitFor();
			
			System.out.println("DEBUG > CLICK BUTTON REJECT ORDERS : All table items removed");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void acknowledgeOrder(String orderId){
		
		try {
			
			
			Runtime rt = Runtime.getRuntime();
			
			
			System.out.println("DEBUG > CLICK BUTTON ACCEPT ORDERS : executed script <" + ClientUMARF.ACKNOWLEDGE_ORDER_CMD + ">");

			
			Process proc = rt.exec(ClientUMARF.ACKNOWLEDGE_ORDER_CMD + " " + orderId);
			proc.waitFor();
			
			System.out.println("DEBUG > CLICK BUTTON ACCEPT ORDERS : All table items removed");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
