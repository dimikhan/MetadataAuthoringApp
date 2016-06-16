package com.ibm.industry.authenticate;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.cloudant.client.api.Database;
import com.ibm.industry.util.CloudantClientMgr_Data;
import com.ibm.industry.util.TiscoLogger;

@Path("/LoginService")
public class LoginService {
	
	private static final String sClassName = "LoginService";
	private static final String sPackageName = "com.ibm.tisco.authenticate";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	@Path("/authenticateUser")
	public User authenticateUser(User user){
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);
		
		logger.debug("authenticateUser() operation called ");
		
		boolean auth = false;
		String sUId = user.getUserId().trim();
		String sPW = user.getPassword().trim();
		
		Database db = null;
		HashMap<String, Object> docObj = null;
		HashMap<String, String> obj_UIdPW = new HashMap();
		obj_UIdPW.put("user_name", sUId);
		obj_UIdPW.put("password", sPW);
		
		try
		{
			String sDBName = "datastore";
			db = getDB(sDBName);
			docObj = db.find(HashMap.class , "authentication");
			if (docObj == null)
				throw new Exception("authetication db not found");
		}
		catch(Exception re)
		{
			re.printStackTrace();
			user.getMessage().setText("Cannot get login db: "+re.getMessage());
		}
			
		if (docObj.containsValue(obj_UIdPW)){
			
			auth = true;
		}
		
		if (auth)
			user.setAuthenticated(true);
		else
			user.setAuthenticated(false);
		
		return user;
	}
	
	private Database getDB(String sDBName)
	{
		return CloudantClientMgr_Data.getDB(sDBName);
	}
}
