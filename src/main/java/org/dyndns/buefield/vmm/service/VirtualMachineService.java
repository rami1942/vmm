package org.dyndns.buefield.vmm.service;


import java.util.List;

import javax.annotation.Resource;

import org.dyndns.buefield.vmm.entity.VirtualMachine;
import org.seasar.extension.jdbc.JdbcManager;

public class VirtualMachineService {

	@Resource protected JdbcManager jdbcManager;
	
	public List<VirtualMachine> findByRoomNo(Object pjNo) {
		return jdbcManager.from(VirtualMachine.class)
			.where("pjNo=?", pjNo)
			.leftOuterJoin("runOnMahcine").getResultList();
	}
}
