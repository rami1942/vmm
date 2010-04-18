package org.dyndns.buefield.vmm.service;


import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import org.dyndns.buefield.vmm.entity.DataStore;
import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.dyndns.buefield.vmm.entity.VirtualMachine;

import com.vmware.vim25.DatastoreInfo;
import com.vmware.vim25.DatastoreSummary;
import com.vmware.vim25.HostCpuInfo;
import com.vmware.vim25.HostHardwareInfo;
import com.vmware.vim25.VirtualMachineConfigSummary;
import com.vmware.vim25.VirtualMachineGuestSummary;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.VirtualMachineSummary;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;

public class VmwareCrawlerService {
	
	public void updateStatus(PhysicalHost hv) 
		throws MalformedURLException, RemoteException {
		ServiceInstance si = null;
		try {
			si = new ServiceInstance(
					new URL("https://" + hv.ipAddress + "/sdk"),
					hv.userName, hv.password, true);
			Folder rootFolder = si.getRootFolder();
			HostSystem host = (HostSystem)new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", hv.name);
			HostHardwareInfo hwInfo = host.getHardware();
			HostCpuInfo cpuInfo = hwInfo.cpuInfo;
			hv.memory = hwInfo.memorySize / 1024 / 1024;
			hv.core = (int)cpuInfo.numCpuCores;
		} finally {
			if (si != null && si.getServerConnection() != null) si.getServerConnection().logout();
		}		
	}
	
	List<VirtualMachine> crawl(PhysicalHost hv) 
		throws MalformedURLException, RemoteException {
		LinkedList<VirtualMachine> result = new LinkedList<VirtualMachine>();
		
		ServiceInstance si = null;
		try {
			si = new ServiceInstance(
						new URL("https://" + hv.ipAddress + "/sdk"),
						hv.userName, hv.password, true);
			Folder rootFolder = si.getRootFolder();
			ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
						
			for (ManagedEntity e : mes) {
				VirtualMachine entity = new VirtualMachine();
				com.vmware.vim25.mo.VirtualMachine vm = (com.vmware.vim25.mo.VirtualMachine)e;

				// VM名
				entity.setName(vm.getName());
				entity.setRunOn(hv.id);
				entity.setHvType(hv.hvType);

				VirtualMachineSummary summary = vm.getSummary();
				
				// CPU, メモリ
				VirtualMachineConfigSummary configSummary = summary.getConfig();
				entity.setVcpu(configSummary.getNumCpu());
				entity.setMemory(configSummary.getMemorySizeMB().longValue());
				// プライマリIP
				
				VirtualMachineGuestSummary guestSummary = summary.getGuest();
				entity.setIpAddress(guestSummary.ipAddress);
				
				// 最終起動時刻
				VirtualMachineRuntimeInfo rt = vm.getRuntime();
				if (rt.bootTime != null) {
					entity.setBootTime(rt.bootTime.getTime());
				} else {
					entity.setBootTime(null);
				}
				// 電源状態
				VirtualMachinePowerState pstat = rt.powerState;
				if (pstat == VirtualMachinePowerState.poweredOff) {
					entity.setStatus(VirtualMachine.POWERSTATUS_OFF);
				} else if (pstat == VirtualMachinePowerState.poweredOn) {
					entity.setStatus(VirtualMachine.POWERSTATUS_ON);
				} else if (pstat == VirtualMachinePowerState.suspended) {
					entity.setStatus(VirtualMachine.POWERSTATUS_SUSPEND);
				} else {
					entity.setStatus(VirtualMachine.POWERSTATUS_UNKNOWN);
				}
				
				// リストへの登録
				result.add(entity);
			}
		} finally {
			if (si != null && si.getServerConnection() != null) si.getServerConnection().logout();			
		}
		return result;		
	}
	
	/**
	 * ストレージ情報の取得
	 * @param hv 対象物理ホスト
	 * @return データストアのリスト
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public List<DataStore> getStorageInfo(PhysicalHost hv) throws MalformedURLException, RemoteException {
		LinkedList<DataStore> result = new LinkedList<DataStore>();
		
		ServiceInstance si = null;
		try {
			si = new ServiceInstance(
						new URL("https://" + hv.ipAddress + "/sdk"),
						hv.userName, hv.password, true);
			Folder rootFolder = si.getRootFolder();
			HostSystem hs = (HostSystem)new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", hv.name);
			if (hs == null) return null;

			Datastore[] ds = hs.getDatastores();
			for (Datastore d : ds) {
				DataStore entity = new DataStore();
				d.refreshDatastoreStorageInfo();
				DatastoreSummary dsSummary = d.getSummary();
				DatastoreInfo dsInfo = d.getInfo();
				
				entity.name = dsSummary.getName();
				entity.maxSize = (float)(dsSummary.getCapacity() / 1024 / 1024 / 1024);
				entity.freeSize = (float)(dsSummary.getFreeSpace() / 1024 / 1024 / 1024);
				entity.lastCheck = dsInfo.timestamp.getTime();
				result.add(entity);
			}
			return result;
		} finally {
			if (si != null && si.getServerConnection() != null) si.getServerConnection().logout();			
		}
	}
}
