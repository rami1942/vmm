package org.dyndns.buefield.vmm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=PjUser.TABLE_NAME)
public class PjUser {
	public static final String TABLE_NAME="pj_users";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

	public String section;

	public String name;
	public String nameKana;
	public String mail;
}
