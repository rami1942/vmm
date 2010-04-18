package org.dyndns.buefield.vmm.entity;

import static org.dyndns.bluefield.craysas.html.field.Field.field;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dyndns.bluefield.craysas.html.field.Field;

@Entity
@Table(name=VirtualMachine.TABLE_NAME)
public class VirtualMachine {
	public static final String TABLE_NAME="virtualmachine";
	
	public static final int POWERSTATUS_UNKNOWN=0;
	public static final int POWERSTATUS_OFF=1;
	public static final int POWERSTATUS_ON=2;
	public static final int POWERSTATUS_SUSPEND=3;
	
	public static final String KEY_PREFIX="virtualMachine.";
	public static final Field[] CHILDREN_ITEMS = new Field[] {
		field("name").text(),
		field("ipAddress").text(),
		field("statusDisp").text(),
		field("vcpu").number(),
		field("memory").number(),
		field("updateDt").text()
	};

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String ipAddress;
	@Temporal(TemporalType.TIMESTAMP)
	private Date bootTime;
	private Integer status;
	private Long runOn;
	private Integer hvType;
	private Integer disabled;
	
	private Integer vcpu;
	private Long memory;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDt;
	
	public Integer pjNo;
	
	@ManyToOne
	@JoinColumn(name="run_on")
	private PhysicalHost runOnMahcine;
	
	public String getStatusDisp() {
		if (status == null) return "不明";
		switch (status) {
		case POWERSTATUS_UNKNOWN:
			return "不明";
		case POWERSTATUS_OFF:
			return "停止";
		case POWERSTATUS_ON:
			return "稼動";
		case POWERSTATUS_SUSPEND:
			return "サスペンド";
		default:
			return "その他";
		}
	}
	
	public String getRunOnDisp() {
		if (runOnMahcine == null) return "不明";
		String fullName = runOnMahcine.name;
		int i = fullName.indexOf('.');
		String simpleName = null;
		if (i > 0) {
			simpleName = fullName.substring(0, i);
		} else {
			simpleName = fullName;
		}
		return simpleName + "(" + runOnMahcine.ipAddress + ")";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getBootTime() {
		return bootTime;
	}
	public void setBootTime(Date bootTime) {
		this.bootTime = bootTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getRunOn() {
		return runOn;
	}
	public void setRunOn(Long runOn) {
		this.runOn = runOn;
	}
	public Integer getHvType() {
		return hvType;
	}
	public void setHvType(Integer hvType) {
		this.hvType = hvType;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVcpu() {
		return vcpu;
	}
	public void setVcpu(Integer vcpu) {
		this.vcpu = vcpu;
	}
	public Long getMemory() {
		return memory;
	}
	public void setMemory(Long memory) {
		this.memory = memory;
	}
}
