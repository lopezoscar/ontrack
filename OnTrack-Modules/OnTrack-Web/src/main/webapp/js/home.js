function HomeController($scope,$http,$location){
	$scope.issues = [];
	
	$scope.server = "http://localhost:8080/OnTrack-SOA/";
	$scope.webserver = "http://localhost:8080/OnTrack/";
    var user = {
    	id: 1
    };
    
    $scope.currentUser = user;
    
    $http({method: 'POST', url: $scope.server+"issuesrv/listIssuesByUser",data:user,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	var rows = parseChartDataCurrentStatus($scope.issues);
		   	drawChart(rows,'status_div','Mis Issues Por Current Status');
		   	rows = parseChartDataIssueType($scope.issues);
		   	drawChart(rows,'type_div','Mis Issues Por Issue Type');
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
	
	
	
    
    //retrieveWorkflowsByUser($scope.currentUser);
    
    function retrieveProjectsByUser(user){
    	$http({method: 'POST', url: $scope.server+'projectsrv/projectsbyuser',data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.projects = data;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noProjects = true;
		  });
    };
    
    retrieveProjectsByUser($scope.currentUser);
    
    
    $scope.viewIssue = function(issue){
    	$location.replace(true).path("/create-issue.html"+"?issue="+issue.id);
    };
    
    $scope.viewProject = function(project){
    	$location.replace(true).path("/create-project.html"+"?project="+project.id);
    };
    
};


function parseChartDataCurrentStatus(issues){
	var allData = [];
	var statusList = [];
	var result = [];
	angular.forEach(issues, function(issue, key){
		var status = issue.currentStatus;
		var idx = statusList.indexOf(status.description);
		//IF DOESNT EXIST
		if(idx < 0){
			statusList.push(status.description);
			allData.push({status: status.description, cant: 1});
		}else{
			var statusCount = allData[idx];
			statusCount.cant++;
		}
	});
	
	angular.forEach(allData, function(statusCount, key){
		result.push([statusCount.status,statusCount.cant]);
	});
	return result;
}

function parseChartDataIssueType(issues){
	var allData = [];
	var typeList = [];
	var result = [];
	angular.forEach(issues, function(issue, key){
		var it = issue.issueType;
		var idx = typeList.indexOf(it.description);
		//IF DOESNT EXIST
		if(idx < 0){
			typeList.push(it.description);
			allData.push({issueType: it.description, cant: 1});
		}else{
			var typeCount = allData[idx];
			typeCount.cant++;
		}
	});
	
	angular.forEach(allData, function(typeCount, key){
		result.push([typeCount.issueType,typeCount.cant]);
	});
	return result;
}

function retrieveIssuesByGet($scope,$http,url){
	var server = "http://localhost:8080/OnTrack-SOA/";
	$http.get(server+url).success(function(callback){
		$scope.issues = callback;
		//$scope.$apply();
	});
};

function retrieveIssuesByPOST($scope,$http,url,data){
    var server = "http://localhost:8080/OnTrack-SOA/";
	$http.post(server+url).data(data).success(function(callback){
		$scope.issues = callback;
		$scope.$apply();
	});
};



function ProjectFilter(){
	this.name="Proyecto";
	this.projects = [];
	this.selectedProject = {};
	this.url = "issuesrv/getissusbytype";
	this.init = function(scope){
		//Here should be ti search workflows by user
		var result = getAllProjectsFromWorkflows(scope.workflows);
		scope.selectedFilter.projects = result;	
	};
	
	this.isProjectFilter = true;
	
	
	this.	s = function($http){
		
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
	this.owner = "";
	this.url = "issuesrv/getissuesbyownerid";
	this.init = function(scope){
		this.scope = scope;
	};
	
	this.isOwnerFilter = true;
	
	this.searchIssues = function($http){
		var params = [];
		params.push(1);
		
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



