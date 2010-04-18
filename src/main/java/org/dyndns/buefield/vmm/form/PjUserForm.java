package org.dyndns.buefield.vmm.form;

import org.dyndns.bluefield.craysas.annotation.KeyPrefix;
import org.dyndns.bluefield.craysas.html.field.Field;
import org.seasar.struts.annotation.Required;

import static org.dyndns.bluefield.craysas.html.field.Field.field;

@KeyPrefix(PjUserForm.KEY_PREFIX)
public class PjUserForm {
	public static final String KEY_PREFIX="pjUser.";
	public static final Field[] LIST_FIELDS = new Field[] {
		field("section").text(),
		field("name").text().link("edit", "id", "id"),
		field("nameKana").text(),
		field("mail").text()
	};
	public static final Field[] EDIT_FIELDS = new Field[] {
		field("id").number().hidden(),
		field("section").text(),
		field("name").text(),
		field("nameKana").text(),
		field("mail").text()
	};
	
	public String id;
	
	@Required
	public String section;
	
	@Required
	public String name;
	@Required
	public String nameKana;
	@Required
	public String mail;
}
