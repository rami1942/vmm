package org.dyndns.buefield.vmm.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.dyndns.buefield.vmm.entity.VirtualMachine;
import org.seasar.extension.jdbc.JdbcManager;

public class HyperVisorCrawlerService {
	public JdbcManager jdbcManager;
	public VmwareCrawlerService vmwareCrawlerService;
	
	public void crawl(PhysicalHost hv) throws Exception {
		
		vmwareCrawlerService.updateStatus(hv);

		Pattern pattern1 = Pattern.compile("^portal([0-9]+)");
		Pattern pattern2 = Pattern.compile("^srv([0-9]+)-[0-9]+");
		
		jdbcManager.update(hv).execute();

		jdbcManager.updateBySql("update " + VirtualMachine.TABLE_NAME + " set disabled=1,run_on=NULL where run_on=?", Long.class).params(hv.id).execute();
		
		List<VirtualMachine> vms = vmwareCrawlerService.crawl(hv);
		for (VirtualMachine v : vms) {
			Matcher m = pattern1.matcher(v.getName());
			if (m.matches()) {
				v.pjNo = Integer.parseInt(m.group(1));
			} else {
				m = pattern2.matcher(v.getName());
				if (m.matches()) {
					v.pjNo = Integer.parseInt(m.group(1));
				}
			}
			
			VirtualMachine exist = jdbcManager.from(VirtualMachine.class).where("name=?", v.getName()).getSingleResult();
			if (exist == null) {
				Date now = new Date();
				v.setCreateDt(now);
				v.setUpdateDt(now);
				v.setDisabled(0);
				jdbcManager.insert(v).execute();
			} else {
				v.setId(exist.getId());
				v.setCreateDt(exist.getCreateDt());
				v.setUpdateDt(new Date());
				v.setDisabled(0);
				jdbcManager.update(v).execute();
			}
		}
	}

}
