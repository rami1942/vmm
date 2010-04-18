package org.dyndns.buefield.vmm.action;

import java.util.List;

import javax.annotation.Resource;

import org.dyndns.bluefield.craysas.html.config.ListTableConfig;
import org.dyndns.bluefield.craysas.html.field.Field;
import org.dyndns.buefield.vmm.entity.VirtualMachine;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.annotation.Execute;
import static org.dyndns.bluefield.craysas.html.field.Field.field;

public class VirtualMachineAction {

	public static final Field[] ITEMS = new Field[] {
		field("name").text(),
		field("ipAddress").text(),
		field("statusDisp").text(),
		field("runOnDisp").text().link("/vpjrm/physicalHost/show", "id", "runOn"),
		field("vcpu").number(),
		field("memory").number(),
		field("updateDt").text()
	};
	
	// DI
	@Resource protected JdbcManager jdbcManager;

	//
	public ListTableConfig<VirtualMachine> config;
	
	@Execute(validator=false)
	public String index() {
		List<VirtualMachine> vms = jdbcManager.from(VirtualMachine.class)
		.leftOuterJoin("runOnMahcine").getResultList();
		
		config = new ListTableConfig<VirtualMachine>("virtualMachine.", ITEMS, vms);
		return "index.jsp";
	}
}
