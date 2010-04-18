package org.dyndns.buefield.vmm.entity;

import static org.dyndns.bluefield.craysas.html.field.Field.field;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dyndns.bluefield.craysas.html.field.Field;

@Entity
@Table(name=PhysicalHost.TABLE_NAME)
public class PhysicalHost {
	public static final String TABLE_NAME="hypervisor";

	public static final int HYPERVISOR_ESXI=1;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String name;
	public String ipAddress;
	public Integer hvType;
	public String userName;
	public String password;
	public String description;
	
	public Integer core;
	public Long memory;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDt;
	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date lastCrawlDt;
	
	
	public static final String KEY_PREFIX = "hypervisor.";
	public static final Field[] LIST_ITEMS = new Field[] {
		field("id").number().hidden(),
		field("name").text().link("show", "id", "id"),
		field("ipAddress").text(),
		field("hvTypeDisp").text(),
		field("core").number(),
		field("memory").number(),
		field("lastCrawlDt").text(),
		field("description").multiText(),
		field("ext1").string("取得").link("crawl", "id", "id"),
	};
	
	public String getHvTypeDisp() {
		return "ESXi";
	}	

}
