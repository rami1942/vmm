package org.dyndns.buefield.vmm.service;

import java.util.List;

import javax.annotation.Resource;

import org.dyndns.buefield.vmm.entity.PjUser;
import org.seasar.extension.jdbc.JdbcManager;

public class PjUserService {
	
	@Resource protected JdbcManager jdbcManager;
	
	public List<PjUser> findAll() {
		return jdbcManager.from(PjUser.class).orderBy("nameKana").getResultList();
	}
	
	public PjUser findById(Object id) {
		return jdbcManager.from(PjUser.class).where("id=?", id).getSingleResult();
	}
	
	public void insert(PjUser u) {
		jdbcManager.insert(u).execute();
	}
	
	public void update(PjUser u) {
		jdbcManager.update(u).execute();
	}
}
