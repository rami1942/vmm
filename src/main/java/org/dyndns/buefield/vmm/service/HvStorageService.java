package org.dyndns.buefield.vmm.service;


import javax.annotation.Resource;

import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.seasar.extension.jdbc.JdbcManager;

public class HvStorageService {
	@Resource protected JdbcManager jdbcManager;

	public PhysicalHost findById(Object id) {
		return jdbcManager.from(PhysicalHost.class).where("id=?", id).getSingleResult();
	}
}
