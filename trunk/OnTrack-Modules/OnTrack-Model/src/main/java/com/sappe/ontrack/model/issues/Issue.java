package com.sappe.ontrack.model.issues;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sappe.ontrack.model.users.User;

@Entity
@Table(name="issue")
@NamedQueries(
		{
			@NamedQuery(name="getIssuesByProjectId",query="SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.project.id = :projectid" ),
			@NamedQuery(name="getIssuesByOwnerId",query="SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType,i.project) FROM Issue as i where i.owner.id = :ownerid" ),
			@NamedQuery(name="getIssuesByReporter",query = "SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.reporter = :reporter"),
			@NamedQuery(name="getIssuesByStatus",query = "SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.currentStatus = :status"),
			@NamedQuery(name="getIssuesByType",query = "SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.issueType = :type"),
			@NamedQuery(name="getIssuesByCode",query = "SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.code = :code"),
			@NamedQuery(name="getIssuesByIssueType", query="SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType) FROM Issue as i where i.issueType = :type"),
			@NamedQuery(name="getIssuesByUserFromProject", query="SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType,i.project) FROM Issue as i where :user in elements (i.project.users)"),
			@NamedQuery(name="getIssuesByProjectIdAndOwnerId",query="SELECT NEW com.sappe.ontrack.model.issues.Issue(i.id,i.title,i.description,i.reporter,i.owner,i.currentStatus,i.issueType,i.project) FROM Issue as i where i.project.id = :projectId and i.owner.id = :ownerId" ),
			
			
		}
)
public class Issue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6314916409129029867L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue")
	private Long id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="reporter")
	private String reporter;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	@OneToOne
	@JoinColumn(name="current_status")
	private IssueStatus currentStatus;
	
	@OneToOne
	@JoinColumn(name="issue_type")
	private IssueType issueType;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="parent_issue")
	private Issue parent;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parent",cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue")
	private Set<Issue> childs;
	
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue")
	private List<IssueEntry> entries;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue")
	private List<IssueComment> comments;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue")
	private List<LogIssue> logs;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue")
	private List<ProcessHistory> history;

	public Issue(Long id,String code, String title, String description, String reporter,
			User owner, IssueStatus currentStatus, IssueType issueType,
			Issue parent, Set<Issue> childs, Project project,
			List<IssueEntry> entries) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.description = description;
		this.reporter = reporter;
		this.owner = owner;
		this.currentStatus = currentStatus;
		this.issueType = issueType;
		this.parent = parent;
		this.childs = childs;
		this.project = project;
		this.entries = entries;
	}
	
	


	public Issue(Long id,String code, String title, String description,
			IssueStatus currentStatus, IssueType issueType) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.description = description;
		this.currentStatus = currentStatus;
		this.issueType = issueType;
	}




	public Issue(Long id,String code, String title, String description) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.description = description;
	}

	


	public Issue(Long id, String title, String description, String reporter,
			User owner, IssueStatus currentStatus, IssueType issueType) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.reporter = reporter;
		this.owner = owner;
		this.currentStatus = currentStatus;
		this.issueType = issueType;
	}
	
	




	public Issue(Long id, String title, String description, String reporter,
			User owner, IssueStatus currentStatus, IssueType issueType,
			Project project) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.reporter = reporter;
		this.owner = owner;
		this.currentStatus = currentStatus;
		this.issueType = issueType;
		this.project = project;
	}




	public Issue(){}


	@JsonIgnore
	@Transient
	public boolean isLeaf() {
		return (childs == null || childs.size() == 0);
	}
	
	@JsonIgnore
	@Transient
	public boolean isRoot(){
		return (parent == null);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getReporter() {
		return reporter;
	}


	public void setReporter(String reporter) {
		this.reporter = reporter;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public IssueStatus getCurrentStatus() {
		return currentStatus;
	}


	public void setCurrentStatus(IssueStatus currentStatus) {
		this.currentStatus = currentStatus;
	}


	public IssueType getIssueType() {
		return issueType;
	}


	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}


	public Issue getParent() {
		return parent;
	}


	public void setParent(Issue parent) {
		this.parent = parent;
	}


	@JsonIgnore
	public Set<Issue> getChilds() {
		return childs;
	}
	
	public void setChilds(Set<Issue> childs) {
		this.childs = childs;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<IssueEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<IssueEntry> entries) {
		this.entries = entries;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<IssueComment> getComments() {
		return comments;
	}

	public void setComments(List<IssueComment> comments) {
		this.comments = comments;
	}

	public List<LogIssue> getLogs() {
		return logs;
	}
	
	public void setLogs(List<LogIssue> logs) {
		this.logs = logs;
	}

	public List<ProcessHistory> getHistory() {
		return history;
	}

	public void setHistory(List<ProcessHistory> history) {
		this.history = history;
	}

	


	public Date getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Estado: ");
		sb.append(currentStatus);
		sb.append("\n");
		sb.append("Owner: ");
		sb.append(owner);
		sb.append("\n");
		sb.append("Reporter ");
		sb.append(reporter);
		sb.append("\n");
		sb.append("Tipo de Issue: ");
		sb.append(issueType);
		
		return sb.toString();
	}

	

	
	
	
	
	
}
