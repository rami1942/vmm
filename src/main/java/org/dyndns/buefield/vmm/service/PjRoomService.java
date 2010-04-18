package org.dyndns.buefield.vmm.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dyndns.buefield.vmm.entity.PjRoom;
import org.seasar.extension.jdbc.JdbcManager;


public class PjRoomService {
	@Resource protected JdbcManager jdbcManager;
	
	public List<PjRoom> findAll() {
		return jdbcManager.from(PjRoom.class).orderBy("pjNo").getResultList();
	}
	
	public PjRoom findById(Object id) {
		if (id == null) return null;
		return jdbcManager.from(PjRoom.class).where("id=?", id).getSingleResult();
	}
		
	public void update(PjRoom pjr) {
		pjr.updateDt = new Date();
		jdbcManager.update(pjr).execute();
	}
	
	public void insert(PjRoom pjr) {
		pjr.updateDt = new Date();
		jdbcManager.insert(pjr).execute();
	}
}
