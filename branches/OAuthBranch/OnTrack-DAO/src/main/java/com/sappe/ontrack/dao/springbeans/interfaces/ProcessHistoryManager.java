package com.sappe.ontrack.dao.springbeans.interfaces;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.ProcessHistory;

public interface ProcessHistoryManager extends CRUD<ProcessHistory> {

	void addEntryToHistory(Issue issue);
}
