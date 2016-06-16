package com.ibm.industry.metadatamanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.lightcouch.DocumentConflictException;
import org.lightcouch.NoDocumentException;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.ibm.industry.util.CloudantClientMgr_MetaData;
import com.ibm.industry.util.TiscoLogger;

@Path("/MetadataManager")
public class MetadataManager {
	
	private static final String sClassName = "MetadataManager";
	private static final String sPackageName = "com.ibm.tisco.metadatamanager";

	@POST
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	@Path("/createMetadata")
	public DataSet createMetadata(DataSet dataSet){
		
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);
		
		logger.debug("createMetadata() operation called ");
		
		//validate request
		if (dataSet == null){
			
			dataSet = new DataSet();
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("Empty DataSet");
			
			return dataSet;
		}
		else if (dataSet.get_id()==null || dataSet.get_id().equals("")){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No Root provided");
			
			return dataSet;
		}
		else if (dataSet.getNode().length == 0){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No node added in the root");
			
			return dataSet;
		}
		//validate request
		
		
		Database db = null;
		//HashMap<String, Object> dbObj = null;
		
		try
		{
			String sDBName = "metadatastore";
			db = getDB(sDBName);
		}
		catch(Exception re)
		{
			re.printStackTrace();
			dataSet.getMessage().setId("300");
			dataSet.getMessage().setText("Cannot get db: "+re.getMessage());
			
			return dataSet;
		}
		
		//Create document as per _id
		try{
			Response resp = db.post(dataSet);
			String sRev = resp.getRev();
			dataSet.set_rev(sRev);
		}catch(DocumentConflictException conflictEx){
			
			conflictEx.printStackTrace();
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("There is an existing Metadata with the same name: "+conflictEx.getMessage());
			
			return dataSet;
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("can not create doc: "+ex.getMessage());
			
			return dataSet;
		}
		//Create document as per _id
		
		/*HashMap<String, Object> objDataSet = db.find(HashMap.class,resp.getId());
		objDataSet.put("_id", sDataSetName);
		db.update(objDataSet);*/
		
		
		
		/*if(dbObj!=null){
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", name);			
			data.put("_id", id+"");
			data.put("value", value);
			data.put("creation_date", new Date().toString());
			db.save(data);
		}*/

		dataSet.getMessage().setText("Success");
		return dataSet;
	}
	

	@GET
	@Produces({"application/xml","application/json"})
	@Path("/viewMetadata/{dataset}")
	public DataSet viewMetadata(@PathParam("dataset") String dataSetName){
		
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);
		
		logger.debug("viewMetadata() operation called ");
		
		System.out.println("Inside view Metadata");
		DataSet dataSet = new DataSet();
		
		Database db = null;
		
		try
		{
			String sDBName = "metadatastore";
			db = getDB(sDBName);
		}
		catch(Exception re)
		{
			re.printStackTrace();
			dataSet.getMessage().setId("300");
			dataSet.getMessage().setText("Cannot get db: "+re.getMessage());
			
			return dataSet;
		}
		
		try{
			DataSet retrievedDataSet = db.find(DataSet.class,dataSetName);
			dataSet = retrievedDataSet;
		}catch(NoDocumentException noDocEx){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No DataSet found as per specified name");
			
			return dataSet;
		}
		
		dataSet.getMessage().setText("Success");
		return dataSet;
	}
	
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	@Path("/updateMetadata")
	public DataSet updateMetadata(DataSet dataSet){
		
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);
		
		logger.debug("updateMetadata() operation called ");
		
		//validate request
		if (dataSet == null){
			
			dataSet = new DataSet();
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("Empty DataSet");
			
			return dataSet;
		}
		else if (dataSet.get_id()==null || dataSet.get_id().equals("")){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No Root provided");
			
			return dataSet;
		}
		else if (dataSet.getNode().length == 0){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No node added in the root");
			
			return dataSet;
		}
		//validate request
		
		Database db = null;
		//HashMap<String, Object> dbObj = null;
		
		try
		{
			String sDBName = "metadatastore";
			db = getDB(sDBName);
		}
		catch(Exception re)
		{
			re.printStackTrace();
			dataSet.getMessage().setId("300");
			dataSet.getMessage().setText("Cannot get db: "+re.getMessage());
			
			return dataSet;
		}
		
		String s_id = dataSet.get_id();
		HashMap<String, Object> oldDataSet = db.find(HashMap.class,s_id);
		
		oldDataSet.put("Node",dataSet.getNode());
		Response resp= db.update(oldDataSet);
		String sRev = resp.getRev();
		dataSet.set_rev(sRev);
		/*
		//process dataSet to see changes
		Node[] nodes = dataSet.getNode();
		int iNode_Num = nodes.length;
		for(int i=0;i<iNode_Num;i++){
			
			Node node = nodes[i];
			String sFieldStatus = node.getDataFieldStatus();
			
			if (sFieldStatus.equalsIgnoreCase("A")){
				
				
				Node[] oldNodes = (Node[])oldDataSet.get("Node");
				oldNodes[oldNodes.length+1] = node;
			}
			else if(sFieldStatus.equalsIgnoreCase("D")){
				
				
				int iDataFieldId = node.getDataFieldId();
			}
		}*/

		dataSet.getMessage().setText("Success");
		return dataSet;
	}
	

	@POST
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	@Path("/deleteMetadata")
	public DataSet deleteMetadata(DataSet dataSet){
		
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);		
		logger.debug("deleteMetadata() operation called ");
		
		String dataSetName = dataSet.get_id();
		
		Database dataBase = null;
		
		try
		{
			String sDBName = "metadatastore";
			dataBase = getDB(sDBName);
		}
		catch(Exception re)
		{
			re.printStackTrace();
			dataSet.getMessage().setId("300");
			dataSet.getMessage().setText("Cannot get db: "+re.getMessage());
			
			return dataSet;
		}
		
		try{
			
			DataSet retrievedDataSet = dataBase.find(DataSet.class,dataSetName);
			
				dataBase.remove(retrievedDataSet.get_id(),retrievedDataSet.get_rev());
				
				dataSet = retrievedDataSet;
				
		}catch(NoDocumentException noDocEx){
			
			dataSet.getMessage().setId("100");
			dataSet.getMessage().setText("No DataSet found as per specified name");
			
			return dataSet;
		}
		
		dataSet.getMessage().setText("Success");
		return dataSet;
	}
	
	@SuppressWarnings("rawtypes")
	@GET
	@Produces({"application/xml","application/json"})
	@Path("/getMetadata")
	public DoccumentBean getAllMetadata(){
		
		String sLoggerName = sPackageName+"."+sClassName;		
		TiscoLogger tiscoLogger = new TiscoLogger();
		Logger logger = tiscoLogger.getLogger(sLoggerName);
		
		logger.debug("getAllMetadata() operation called ");
		
		DoccumentBean dBean = new DoccumentBean();
		
		Database db = null;
		
		try
		{
			String sDBName = "metadatastore";
			db = getDB(sDBName);
		}
		catch(Exception re)
		{
			re.printStackTrace();
			dBean.getMessage().setId("300");
			dBean.getMessage().setText("Cannot get db: "+re.getMessage());
			
			return dBean;
		}
		//JsonObject resultObject = new JsonObject();
		//JsonArray jsonArray = new JsonArray();	
		List<String> aList=new ArrayList<String>();

		try{
			
			
			List<HashMap> allDocs = db.view("_all_docs").query(HashMap.class); 

			
			if(allDocs.size()==0)
			{
				//					allDocs = initializeSampleData(db);
			}

			for(HashMap doc : allDocs)
			{
				DataSet retrievedDataSet = db.find(DataSet.class,doc.get("id")+"");
				aList.add(retrievedDataSet.get_id());
				
			}
			dBean.setListOfDoccuments(aList);
			
		}
		catch(NoDocumentException noDocEx){
			
			dBean.getMessage().setId("100");
			dBean.getMessage().setText("No DataSet found as per specified name");
			
			return dBean;
		}

		dBean.getMessage().setText("Success");
		return dBean;
	            
	}
	
	private Database getDB(String sDBName)
	{
		return CloudantClientMgr_MetaData.getDB(sDBName);
	}
}
