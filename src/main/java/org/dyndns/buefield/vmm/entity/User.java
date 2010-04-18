package org.dyndns.buefield.vmm.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name=User.TABLE_NAME)
public class User {
	public static final String TABLE_NAME="users";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public String userName;
	public String passwordHash;
	public String mailAddress;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDt;
	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDt;
}
