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
@Table(name=DataStore.TABLE_NAME)
public class DataStore {
	public static final String TABLE_NAME="datastore";
	public static final String KEY_PREFIX="dataStore.";
	public static final Field[] ITEMS = new Field[] {
		field("name").text(),
		field("maxSize").number(),
		field("freeSize").number(),
		field("useSpace").percent(),
		field("lastCheck").text()
	};

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String name;
	public Float maxSize;
	public Float freeSize;
	@Temporal(TemporalType.TIMESTAMP)
	public Date lastCheck;
	
	public Float getUseSpace() {
		if (maxSize != 0) {
			return (float)(1.0 - freeSize / maxSize) * 100;
		} else {
			return null;
		}
	}
}
