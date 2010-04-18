package org.dyndns.buefield.vmm.service;


import java.util.List;

import javax.annotation.Resource;

import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.seasar.extension.jdbc.JdbcManager;

public class PhysicalHostService {
	@Resource protected JdbcManager jdbcManager;

	public List<PhysicalHost> findAll() {
		return jdbcManager.from(PhysicalHost.class).orderBy("name").getResultList();
	}
}
