package org.dyndns.buefield.vmm.form;


import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.LongType;
import org.seasar.struts.annotation.Required;

public class PjRoomForm {
	@LongType 
	public String id;

	@IntegerType @Required 
	public String pjNo;
	@Required 
	public String section;
	@Required
	public String pjDescription;
	@Required 
	public String network;
	@Required @IntegerType 
	public String netmask;
	
	@Required
	public String assignDt;
	public String updateDt;

}
