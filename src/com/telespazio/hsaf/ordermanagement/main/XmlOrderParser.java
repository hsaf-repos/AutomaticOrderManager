package com.telespazio.hsaf.ordermanagement.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import com.telespazio.hsaf.ordermanagement.orderlist.xsd.autogeneratedClasses.*;

public abstract class XmlOrderParser {

	private static String context = "com.telespazio.hsaf.ordermanagement.orderlist.xsd.autogeneratedClasses";
	private static Unmarshaller unmarshaller;

	public XmlOrderParser(long userId) {

	}

	public static Orderlist parseXMLFile(String xmlFile) {

		JAXBContext jc;
		Orderlist ordList = null;

		try {
			jc = JAXBContext.newInstance(context);
			unmarshaller = jc.createUnmarshaller();


			File xmlOrderFile = null;

			// load EUMETSAT Order file using xmlbeans
			if (!xmlFile.isEmpty()) {
				xmlOrderFile = new File(xmlFile);
			}

			if (xmlOrderFile.isFile()) {

				AutomaticOrderManager.logger.info("Loading order attributes file.. " + xmlOrderFile);

				JAXBElement<Orderlist> orderList = (JAXBElement<Orderlist>) unmarshaller.unmarshal(new File(xmlFile));

				AutomaticOrderManager.logger.info("Loaded order attributes");

				ordList = orderList.getValue();

			}

		} catch (JAXBException e) {
			
			String msg="XmlOrderParser-parseXMLFile: "  + e.getMessage();
			AutomaticOrderManager.logger.info(msg);
			AutomaticOrderManager.emailServer.sendmailFailureToAdmin(msg);
		
		}
		
		return ordList;

	}

}