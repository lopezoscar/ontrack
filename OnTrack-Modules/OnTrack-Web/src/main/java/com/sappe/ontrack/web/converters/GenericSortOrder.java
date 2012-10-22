package com.sappe.ontrack.web.converters;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.component.SortOrder;

@ManagedBean(name="sortcolumn")
@ViewScoped
public class GenericSortOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378372500531391849L;
	private SortOrder column = SortOrder.unsorted;
	
	
	
	public void sort(){
		column = SortOrder.unsorted;
		if(column.equals(SortOrder.ascending)){
			setColumn(SortOrder.descending);
		}else{
			setColumn(SortOrder.descending);
		}
	}
	
	public SortOrder getColumn() {
		return column;
	}
	
	public void setColumn(SortOrder column) {
		this.column = column;
	}
	
	

}
