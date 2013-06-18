function SearchIssueCtrl($scope,$http){
	$scope.selectedFilter = {};
	
	$scope.workflows = [{"id":3,"issueType":{"id":1,"description":"Bug"},"issueStatus":[{"id":1,"description":"TODO"}],"project":{"id":1,"name":"Proyecto","roles":[{"id":3,"roleName":"Desarrollador","acronym":"DEV"}],"users":[{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkMAYTsPU9NHMnyriu6ija1u-qkqW5mS3I","password":"Test","roles":[],"projects":[]}]}}];
	
	$scope.updateFilter = function(filter){
		filter.init($scope);
		$scope.selectedFilter = filter;
	};
	
	$scope.instanceFilters = function (){
		$scope.filters.push(new ProjectFilter());
		$scope.filters.push(new IssueTypeFilter());
		$scope.filters.push(new ReporterFilter());
		$scope.filters.push(new OwnerFilter());
		$scope.filters.push(new IssueStatusFilter());
		$scope.filters.push(new IssueCodeFilter());
	};
	$scope.filters = [];
}

function ProjectFilter(){
	this.name="Proyecto";
	this.projects = [];
	this.selectedProject = {};
	
	this.init = function(scope){
		//Here should be ti search workflows by user
		var result = getAllProjectsFromWorkflows(scope.workflows);
		scope.selectedFilter.projects = result;	
	};
	
	this.isProjectFilter = true;
}

function IssueTypeFilter(){
	this.name="Issue Type";
	this.projects = [];
	
	this.selectedProject = "";
	this.issueTypes = [];
	
	this.changeIssueTypesByProject = function(workflows){
		this.issueTypes = getIssueTypesBySelectedProject(this.selectedProject,workflows);
	};
	
	this.init = function(scope){
		this.projects = getAllProjectsFromWorkflows(scope.workflows);
	};
	
	this.isIssueTypeFilter = true;
}

function IssueStatusFilter(){
	this.name="Estado";
	this.selectedProject = {};
	this.issueType = {};
	this.issueStatus= {};
	this.projects = [];
	this.issueTypes = [];
	this.listIssueStatus = [];
	
	this.getIssueStatusByProjectAndType = function(){
	
	};
	
	this.changeIssueStatusBySelectedType = function(){
	
	};
	
	this.changeIssueTypesByProject = function(workflows){
		this.issueTypes = getIssueTypesBySelectedProject(this.selectedProject,workflows);
	};
	
	this.init = function(scope){
		var result = getAllProjectsFromWorkflows(scope.workflows);
		scope.selectedFilter.projects = result;	
	};
	
	this.isIssueStatusFilter = true;
}

function issueTypesByProjectId(filter){
	if(filter.id == null){return [];};
	$http.get(server+'issuetypesrv/getissuetypesbyprojectid/'+filter.id).success(function (callback){
			filter.issueTypes = callback;
	});
}

function ReporterFilter(){
	this.name="Reporter";
	this.reporter ="";
	
	this.init = function(scope){};
	
	this.isReporterFilter = true;
}

function OwnerFilter(){
	this.name="Owner";
	this.owner ="";
	
	this.init = function(scope){};
	
	this.isOwnerFilter = true;
}

function IssueCodeFilter(){
	this.name="Issue Code";
	this.issueCode="";
	
	this.init = function(scope){};
	
	this.isIssueCodeFilter = true;
}

function searchIssues(filter){

}

//Duplicate function on create-issue - Ver Injección de dependencias y-o crear servicios
function getIssueTypesBySelectedProject(currentProject,workflows){
    	var result = [];
    	angular.forEach(workflows,function(wf,idx){
    		if(wf.project.id == currentProject.id){
    			 result.push(wf);
    		}
    	});
    	
    	return result;
}

function getAllProjectsFromWorkflows(workflows){
		var result = [];
    	angular.forEach(workflows,function(wf,idx){
    		 result.push(wf.project);
    	});
    	return result;
}

//TODO mejorar algoritmos de búsqueda no es performance por el foreach
function getIssueStatusByProjectAndType(currentProject,issueType,workflows){
	var result = [];
	angular.forEach(workflows,function(wf,idx){
		if(wf.project.id == currentProject.id && wf.issueType == issueType.issueType){
			 result.push.apply(result, wf.issueStatus);
		}
	});
	return result; 
}

