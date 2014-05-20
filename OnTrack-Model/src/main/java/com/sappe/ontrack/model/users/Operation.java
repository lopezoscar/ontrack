package com.sappe.ontrack.model.users;

/**
 * Entity que almacena las operaciones que poseen los permisos
 * CREATE
 * READ
 * UPDATE
 * DELETE
 * ALL
 *
 * Sirve para habilitar/deshabilitar operaciones dentro de los permisos.
 * 
 * @author Oscar
 *
 */
public class Operation {
	
	private Integer code;
	
	private String desc;
	
	private String acronym;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	

}
