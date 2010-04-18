package org.dyndns.buefield.vmm.action;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.dyndns.bluefield.craysas.action.BaseAction;
import org.dyndns.bluefield.craysas.html.config.ListTableConfig;
import org.dyndns.buefield.vmm.entity.DataStore;
import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.dyndns.buefield.vmm.service.HvStorageService;
import org.dyndns.buefield.vmm.service.VmwareCrawlerService;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.annotation.Execute;

public class HvStorageAction extends BaseAction {
	// DI
	@Resource protected HvStorageService hvStorageService;
	@Resource protected JdbcManager jdbcManager;
	@Resource protected VmwareCrawlerService vmwareCrawlerService;

	public ListTableConfig<DataStore> config;
	public PhysicalHost targetHost;
	
	@Execute(validator=false)
	public String index() {
		targetHost = hvStorageService.findById(1);
		List<DataStore> dataStore;

		if (targetHost == null) {
			addErrorMsg("datastore.error.crawl.failed");
			dataStore = new LinkedList<DataStore>();
			config = new ListTableConfig<DataStore>(DataStore.KEY_PREFIX, DataStore.ITEMS, dataStore);
			return "index.jsp";
		}
		try {
			dataStore = vmwareCrawlerService.getStorageInfo(targetHost);
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMsg("dataStore.error.crawl.failed");
			dataStore = new LinkedList<DataStore>();
		}
		config = new ListTableConfig<DataStore>(DataStore.KEY_PREFIX, DataStore.ITEMS, dataStore);
		return "index.jsp";
	}
}
