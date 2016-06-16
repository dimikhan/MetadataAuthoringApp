package com.ibm.industry.metadatamanager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.industry.BaseModel;

@XmlRootElement(name = "Node")
public class Node extends BaseModel {
	
	private String DataFieldId;
	private String DataFieldLogicalName;
	private String DataFieldPhysicalName;
	private String DataFieldDesc;
	private String DataFieldType;
	private String DataFieldFormat;
	private String DataFieldLength;
	private String DataFieldRule;
	private String DataFieldView;
	private boolean Mandatory;
	private String DataFieldStatus;
	
	@XmlElement(name="DataFieldId")
	public String getDataFieldId() {
		return DataFieldId;
	}
	public void setDataFieldId(String dataFieldId) {
		DataFieldId = dataFieldId;
	}
	
	@XmlElement(name="DataFieldLogicalName")
	public String getDataFieldLogicalName() {
		return DataFieldLogicalName;
	}
	public void setDataFieldLogicalName(String dataFieldLogicalName) {
		DataFieldLogicalName = dataFieldLogicalName;
	}
	
	@XmlElement(name="DataFieldPhysicalName")
	public String getDataFieldPhysicalName() {
		return DataFieldPhysicalName;
	}
	public void setDataFieldPhysicalName(String dataFieldPhysicalName) {
		DataFieldPhysicalName = dataFieldPhysicalName;
	}
	
	@XmlElement(name="DataFieldDesc")
	public String getDataFieldDesc() {
		return DataFieldDesc;
	}
	public void setDataFieldDesc(String dataFieldDesc) {
		DataFieldDesc = dataFieldDesc;
	}
	
	@XmlElement(name="DataFieldType")
	public String getDataFieldType() {
		return DataFieldType;
	}
	public void setDataFieldType(String dataFieldType) {
		DataFieldType = dataFieldType;
	}
	
	@XmlElement(name="DataFieldFormat")
	public String getDataFieldFormat() {
		return DataFieldFormat;
	}
	public void setDataFieldFormat(String dataFieldFormat) {
		DataFieldFormat = dataFieldFormat;
	}
	
	@XmlElement(name="DataFieldLength")
	public String getDataFieldLength() {
		return DataFieldLength;
	}
	public void setDataFieldLength(String dataFieldLength) {
		DataFieldLength = dataFieldLength;
	}
	
	@XmlElement(name="DataFieldRule")
	public String getDataFieldRule() {
		return DataFieldRule;
	}
	public void setDataFieldRule(String dataFieldRule) {
		DataFieldRule = dataFieldRule;
	}
	
	@XmlElement(name="DataFieldView")
	public String getDataFieldView() {
		return DataFieldView;
	}
	public void setDataFieldView(String dataFieldView) {
		DataFieldView = dataFieldView;
	}
	
	@XmlElement(name="Mandatory")
	public boolean isMandatory() {
		return Mandatory;
	}
	public void setMandatory(boolean mandatory) {
		Mandatory = mandatory;
	}
	
	@XmlElement(name="DataFieldStatus")
	public String getDataFieldStatus() {
		return DataFieldStatus;
	}
	public void setDataFieldStatus(String dataFieldStatus) {
		DataFieldStatus = dataFieldStatus;
	}
}