package com.telespazio.hsaf.ordermanagement.main;

import java.io.IOException;

// This class wraps the UMARF functionalities to retrieve orders, to acknowledge order or to reject an order.
public class ClientHsafWebPortal {

	private static final String ACKNOWLEDGE_ORDER_CMD = "/UMARF/safclient/bin/scripts/hsafWebAcknowledgeOrder.sh";
	private static final String GET_ORDER_LIST_CMD = "/UMARF/safclient/bin/scripts/hsafWebGetOrderList.sh";
	private static final String ORDERMANAGER_MAIN = "/UMARF/safclient/autoUMARF/orderManager_Main.sh";

	public static void acknowledgeOrder(String orderId) {

		

			Runtime rt = Runtime.getRuntime();

			AutomaticOrderManager.logger
					.info("Acknowledging order : executed script <" + ClientHsafWebPortal.GET_ORDER_LIST_CMD + ">");
			Process proc;
			

			try {
				proc = rt.exec(ClientHsafWebPortal.ACKNOWLEDGE_ORDER_CMD + " " + orderId);
				proc.waitFor();
			} catch (IOException | InterruptedException e) {
				String msg = "ClientHsafWebPortal-acknowledgeOrder: "  + e.getMessage();
				AutomaticOrderManager.emailServer.sendmailFailureToAdmin(msg);
				AutomaticOrderManager.logger.warning(msg);
			}



	}

	public static void getOrderList() {



			Runtime rt = Runtime.getRuntime();

			AutomaticOrderManager.logger
					.info("Get Web Orders List: executed script <" + ClientHsafWebPortal.GET_ORDER_LIST_CMD + ">");

			Process proc = null;
			try {
				proc = rt.exec(ClientHsafWebPortal.GET_ORDER_LIST_CMD);
				proc.waitFor();
			} catch (IOException | InterruptedException e) {
				
				
				String msg = "ClientHsafWebPortal-getOrderList: "  + e.getMessage();
				AutomaticOrderManager.emailServer.sendmailFailureToAdmin(msg);
				AutomaticOrderManager.logger.warning(msg);
			}	

	}
	
	public static void collectOrdersData() {



		Runtime rt = Runtime.getRuntime();

		AutomaticOrderManager.logger
				.info("Execute < " + ClientHsafWebPortal.ORDERMANAGER_MAIN + ">");

		Process proc = null;
		
		String[] cmd = { "/bin/sh", "-c", "nohup ." + ClientHsafWebPortal.ORDERMANAGER_MAIN };
		try {
			proc = rt.exec(cmd);
			proc.waitFor();
		} catch (IOException | InterruptedException e) {
			
			
			String msg = "ClientHsafWebPortal.ORDERMANAGER_MAIN: "  + e.getMessage();
			AutomaticOrderManager.emailServer.sendmailFailureToAdmin(msg);
			AutomaticOrderManager.logger.warning(msg);
		}	

}

}
