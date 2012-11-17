package com.sappe.ontrack.web.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.web.controller.NewProjectController;

@ManagedBean(name="DnDIssueStatus")
@RequestScoped
public class DragAndDropIssueStatus implements DropListener{
	
	@ManagedProperty(value="#{newprojectctrl}")
	private NewProjectController newProjectCtrl;
	
	@Override
	public void processDrop(DropEvent e) {
		newProjectCtrl.addStatusToTarget((IssueStatus)e.getDragValue());
	}
	

	public NewProjectController getNewProjectCtrl() {
		return newProjectCtrl;
	}

	public void setNewProjectCtrl(NewProjectController newProjectCtrl) {
		this.newProjectCtrl = newProjectCtrl;
	}

	
	
	

}
