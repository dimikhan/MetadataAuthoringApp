/**
 * 
 */
package com.ibm.industry;

/**
 * @author ibm
 *
 */
public abstract class BaseModel {
	
	private Message message;
	
	public BaseModel(){
		message=new Message();
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		
		this.message = message;
	}
}
