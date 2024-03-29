function SearchIssueCtrl($scope,$http){
	$scope.selectedFilter = {};
	$scope.workflows = [];
	//$scope.issues = [{"id":1,"title":"Error al guardar Issue","description":"NullPointerException al Guardar un Issue del tipo Bug para el proyecto 1","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":1,"description":"Cualquiera"},"parent":null,"childs":null,"project":null,"entries":null},{"id":2,"title":"Error al crear estadística","description":"Error al generar la estadística para los tipos de issue","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":1,"description":"Cualquiera"},"parent":null,"childs":null,"project":null,"entries":null},{"id":6,"title":"Titulo del Issue","description":"<p>\r\n\tdes</p>\r\n","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":2,"description":"Issue"},"parent":null,"childs":null,"project":null,"entries":null}];
	$scope.issues = [];
	$scope.updateFilter = function(filter){
		filter.init($scope);
		$scope.selectedFilter = filter;
		$scope.issues = [];
	};
	
	$scope.webserver = "http://localhost:8080/OnTrack";
	
	$scope.instanceFilters = function (){
		$scope.filters.push(new ProjectFilter());
		$scope.filters.push(new IssueTypeFilter());
		$scope.filters.push(new ReporterFilter());
		$scope.filters.push(new OwnerFilter());
		$scope.filters.push(new IssueStatusFilter());
		$scope.filters.push(new IssueCodeFilter());
	};
	$scope.filters = [];
	
	$scope.searchIssues = function(){
		$scope.selectedFilter.searchIssues($http);
	};
	
	var user = {
    	id: 1
    };
    var server = 'http://localhost:8080/OnTrack-SOA/';
    function retrieveWorkflowsByUser(user){
    	$http({method: 'POST', url: server+'workflowsrv/listworkflowsbyuser',data:user,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.workflows = data;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noWorkflows = true;
		  });
    };
    
    retrieveWorkflowsByUser(user);
};

function retrieveIssuesByGet($scope,$http,url){
	var server = "http://localhost:8080/OnTrack-SOA/";
	$http.get(server+url).success(function(callback){
		$scope.issues = callback;
	});
};

function retrieveIssuesByPOST($scope,$http,url,data){
    var server = "http://localhost:8080/OnTrack-SOA/";
	$http.post(server+url).data(data).success(function(callback){
		$scope.issues = callback;
	});
};



function ProjectFilter(){
	this.name="Proyecto";
	this.projects = [];
	this.selectedProject = "";
	this.url = "issuesrv/getissusbytype";
	this.scope = {};
	this.init = function(scope){
		this.scope = scope;
		this.projects = getAllProjectsFromWorkflows(scope.workflows);
	};
	
	this.isProjectFilter = true;
	
	
	this.searchIssues = function($http){
		
	};
	
}

function IssueTypeFilter(){

	this.scope = {};

	this.name="Issue Type";
	this.projects = [];
	this.selectedProject = "";
	this.issueTypes = [];
	
	this.changeIssueTypesByProject = function(workflows){
		this.issueTypes = getIssueTypesBySelectedProject(this.selectedProject,workflows);
	};
	
	this.init = function(scope){
		this.scope = scope; 
		this.projects = getAllProjectsFromWorkflows(scope.workflows);
	};
	
	this.isIssueTypeFilter = true;
	
	this.searchIssues = function($http){
		
	};
}
	
function IssueStatusFilter(){
	this.name="Estado";
	this.url = "issuesrv/getissusbystatus";
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
	
	this.searchIssues = function($http){
		
	};
}

function issueTypesByProjectId(filter){
	if(filter.id == null){return [];};
	$http.get(server+'issuetypesrv/getissuetypesbyprojectid/'+filter.id).success(function (callback){
			filter.issueTypes = callback;
	});
}

function ReporterFilter(){
	this.scope = {};
	this.httpMethod = "GET";
	this.name="Reporter";
	this.reporter ="";
	this.url = "issuesrv/getissusbyreporter";
	
	this.init = function(scope){
		this.scope = scope;
	};
	
	this.isReporterFilter = true;
	
	this.searchIssues = function($http){
		var params = [];
		params.push(this.reporter);
		
		retrieveIssuesByGet(this.scope,$http,buildURL(this.url,params));
	};
	
	function buildURL(url,params){
		var result = url;
		angular.forEach(params, function(value, key){
			result = result+"/"+value;
		});
		return result;
		
	}
}

function OwnerFilter(){
	this.scope = {};

	this.name="Owner";
	this.owner ="";
	this.url = "issuesrv/getissuesbyownerid";
	this.init = function(scope){
		this.scope = scope;
	};
	
	this.isOwnerFilter = true;
	
	this.searchIssues = function($http){
		var params = [];
		params.push(this.owner);
		
		retrieveIssuesByGet(this.scope,$http,buildURL(this.url,params));
		search(this);
	};
	
	function buildURL(url,params){
		var result = url;
		angular.forEach(params, function(value, key){
			result = result+"/"+value;
		});
		return result;
		
	}
}

function IssueCodeFilter(){
	this.name="Issue Code";
	this.issueCode="";
	this.url = "issuesrv/getissuesbycode";
	this.init = function(scope){};
	
	this.isIssueCodeFilter = true;
	
	this.searchIssues = function(){
	
	};
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



