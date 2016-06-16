package com.ibm.industry.metadatamanager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.industry.BaseModel;

@XmlRootElement(name = "DataSet")
public class DataSet extends BaseModel {
	
	private String _id;
	private String _rev;
	private boolean Private;
	private Node[] Node;
	
	@XmlElement(name="_id")
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	@XmlElement(name="_rev")
	public String get_rev() {
		return _rev;
	}
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	
	@XmlElement(name="Private")
	public boolean isPrivate() {
		return Private;
	}
	public void setPrivate(boolean private1) {
		Private = private1;
	}
	
	@XmlElement(name="Node")
	public Node[] getNode() {
		return Node;
	}
	public void setNode(Node[] node) {
		Node = node;
	}
}