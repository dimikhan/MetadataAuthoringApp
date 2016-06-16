package com.ibm.industry.metadatamanager;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.industry.BaseModel;
@XmlRootElement(name = "DoccumentBean")
public class DoccumentBean extends BaseModel {
	
	
	private List<String> listOfDoccuments;

	@XmlElement(name="listOfDoccuments")
	public List<String> getListOfDoccuments() {
		return listOfDoccuments;
	}

	public void setListOfDoccuments(List<String> listOfDoccuments) {
		this.listOfDoccuments = listOfDoccuments;
	}
	
	

}
