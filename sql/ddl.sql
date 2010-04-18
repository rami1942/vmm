-- 物理筐体
create table hypervisor (
	id bigint auto_increment primary key,
	name varchar(255) not null,
	hv_type int not null,
	user_name varchar(255),
	password varchar(255),
	description text,
	create_dt datetime,
	update_dt datetime,
	last_crawl_dt datetime,
	ip_address varchar(255),
	core int,
	memory bigint
	
) Engine=InnoDB;

-- 仮想マシン
create table virtualmachine (
	id bigint auto_increment primary key,
	name varchar(255) not null,
	ip_address varchar(255),
	boot_time datetime,
	status int,
	run_on bigint,
	hv_type int,
	disabled int,
	vcpu int,
	memory bigint,
	
	create_dt datetime,
	update_dt datetime
) Type=InnoDB;

-- データストア
create table datastore (
	id bigint auto_increment primary key,
	name varchar(255) not null,
	max_size float,
	free_size float,
	last_check datetime
	
) Engine=InnoDB;

-- ユーザー
create table users (
	id bigint auto_increment primary key,
	user_name varchar(255) not null,
	password_hash varchar(255),
	mail_address varchar(255),
	create_dt datetime,
	update_dt datetime
) Engine=InnoDB;

create table pjroom (
	id bigint auto_increment primary key,
	
	pj_no int not null,
	section varchar(255) not null,
	pj_description text,
	network varchar(255) not null,
	netmask int not null,
	
	assign_dt date,
	update_dt datetime
	
) Engine=InnoDB;

alter table virtualmachine add (
	pj_no int
);

create table pj_users (
	id bigint auto_increment primary key,
	name varchar(255),
	name_kana varchar(255),
	mail varchar(255)
	
) Engine=InnoDB;

create table pj_users_pjroom (
	id bigint auto_increment primary key,
	pj_users_id bigint not null,
	pjroom_id bigint not null,
	
	foreign key (pj_users_id) references pj_users(id),
	foreign key (pjroom_id) references pjroom(id)
) Engine=InnoDB;

--------------------------------------

alter table pj_users add (
	section varchar(255)
);