package org.dyndns.buefield.vmm.entity;

import static org.dyndns.bluefield.craysas.html.field.Field.field;

import org.dyndns.bluefield.craysas.html.field.Field;

public class ResourceAllocation {
	public static final String KEY_PREFIX="ralloc.";
	public static final Field[] RESOURCE_ITEMS = new Field[] {
		field("name").text().link("show", "id", "id"),
		field("vcpu").number(),
		field("core").number(),
		field("vmem").number(),
		field("memory").number(),
		field("calloc").percent(),
		field("malloc").percent(),
	};

	
	public Long id;
	public String name;
	public Integer vcpu;
	public Integer core;
	public Integer vmem;
	public Integer memory;
	public Float calloc;
	public Float malloc;
}
