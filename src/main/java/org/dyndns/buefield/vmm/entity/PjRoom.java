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
@Table(name=PjRoom.TABLE_NAME)
public class PjRoom {
	public static final String TABLE_NAME="pjroom";
	public static final String KEY_PREFIX="pjroom.";
	
	public static final Field[] INDEX_CONFIG = new Field[] {
		field("pjNo").number(),
		field("section").text(),
		field("pjDescription").text().link("show", "id", "id"),
		field("network").text(),
		field("netmask").number(),
		field("assignDt").text(),
		field("updateDt").text(),
	};

	public static final Field[] EDIT_CONFIG = new Field[] {
		field("id").number().hidden(),
		field("pjNo").number(),
		field("section").text(),
		field("pjDescription").text(),
		field("network").text(),
		field("netmask").number(),
		field("assignDt").text(),
	};

	public static final Field[]CREATE_CONFIG = new Field[] {
		field("pjNo").number(),
		field("section").text(),
		field("pjDescription").text(),
		field("network").text(),
		field("netmask").number(),
		field("assignDt").text(),
	};

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public Integer pjNo;
	
	public String section;
	public String pjDescription;
	public String network;
	public Integer netmask;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date assignDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDt;
}
