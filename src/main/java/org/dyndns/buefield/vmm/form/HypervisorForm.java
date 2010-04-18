package org.dyndns.buefield.vmm.form;

import java.util.Date;

import org.dyndns.bluefield.craysas.annotation.KeyPrefix;
import org.dyndns.bluefield.craysas.html.field.Field;

import static org.dyndns.bluefield.craysas.html.field.Field.field;

/**
 * ハイパバイザー詳細フォーム
 *
 */
@KeyPrefix(HypervisorForm.KEY_PREFIX)
public class HypervisorForm {
	public static final String KEY_PREFIX = "hypervisor.";

	public static final Field[] FORM_ITEMS = new Field[] {
		field("id").number().hidden(),
		field("name").text().required(),
		field("ipAddress").text().required(),
		field("userName").text().required(),
		field("password").password(),
		field("passwordConfirm").password(),
		field("core").text(),
		field("memory").text(),
		field("description").multiText()
	};	

	public String id;
	
	public String name;
	public String ipAddress;
	public Integer hvType;
	public String userName;
	
	public String password;
	public String passwordConfirm;
	
	public String description;
	
	public Integer core;
	public Long memory;
	public Date createDt;
	public Date updateDt;
	
}
