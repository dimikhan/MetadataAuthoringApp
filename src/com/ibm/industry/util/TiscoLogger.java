package com.ibm.industry.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class TiscoLogger {
	
	public Logger getLogger(String sLogger){
		
		//This log.4j file is placed in C:\IBM\WID7_WTE\runtimes\bi_v7\profiles\AppSrv01\
		DOMConfigurator.configure("log4j.xml");
		Logger logger = Logger.getLogger(sLogger);
		
		return logger;
	}

}
