
 
function CreateIssueCtrl($scope,$http){
    //$scope.workflows = [{"id":3,"issueType":{"id":1,"description":"Bug"},"issueStatus":[{"id":1,"description":"TODO"}],"project":{"id":1,"name":"Proyecto","roles":[{"id":3,"roleName":"Desarrollador","acronym":"DEV"}],"users":[{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkMAYTsPU9NHMnyriu6ija1u-qkqW5mS3I","password":"Test","roles":[],"projects":[]}]}}];
    $scope.workflows = [];
    $scope.workflowsByProject = [];
    $scope.currentProject = {};
    $scope.statusByType = [];
    $scope.users = [];
    $scope.renderedIssueBtn = false;
    $scope.issueProperties = [];
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
    
    $scope.updateIssueTypes = function(issue){
    	//TODO Bug - el primer .project en realidad es el obj Workflow
    	$scope.currentProject = issue.project.project;
    	$scope.workflowsByProject = getIssueTypesBySelectedProject($scope.currentProject,$scope.workflows);
    };
    
    $scope.updateStatusByTypeAndUsers = function (issue){
    	$scope.statusByType = getIssueStatusByProjectAndType($scope.currentProject,issue.issueType,$scope.workflows);
    	$scope.users = getUsersByProjectAndType($scope.currentProject,issue.issueType,$scope.workflows);
    	$scope.issueProperties = issue.issueType.issueProperties;
    	$scope.entries = createEntriesByProperty($scope.issueProperties);
    };
    
    function createEntriesByProperty(issueProperties){
    	var result = [];
    	angular.forEach(issueProperties,function(prop,idx){
    		var entry = {
    			property: prop
    		};
    		result.push(entry);
    		
    	});
    	return result;
    }
    
    function retrieveStatusById(id){
    	var issueStatus = {};
    	
    	return issueStatus;
    };
    
    //Duplicate function on search-issue - Ver Injección de dependencias y-o crear servicios
    function getIssueTypesBySelectedProject(currentProject,workflows){
    	var result = [];
    	angular.forEach(workflows,function(wf,idx){
    		if(wf.project.id == currentProject.id){
    			 result.push(wf);
    		}
    	});
    	
    	return result;
    }
    
    //TODO mejorar algoritmos de búsqueda no es performance por el foreach
    function getIssueStatusByProjectAndType(currentProject,issueType,workflows){
    	var result = [];
    	angular.forEach(workflows,function(wf,idx){
    	
    		if(wf.project.id == currentProject.id && wf.issueType == issueType.issueType){
    		
    			angular.forEach(wf.issueStatusByWorkflow,function(isbwf,idx){
    			
    				$http({method: 'GET', url: server+'issuestatussrv/getissuestatusbyid/'+isbwf.pk.status}).
					  success(function(data, status, headers, config) {
					  	var issueStatus = data;
	    				var status = {
	    					issueStatus: issueStatus,
	    					position: isbwf.position
	    				};
    			 		result.push(status);    	
					   
					  }).
					  error(function(data, status, headers, config) {
					    
					  });
    			
    			
    						
    			});		
    		}
    	});
    	return result; 
    }
    
  	//TODO mejorar algoritmos de búsqueda no es performance por el foreach
    function getUsersByProjectAndType(currentProject,issueType,workflows){
    	var result = [];
    	angular.forEach(workflows,function(wf,idx){
    		if(wf.project.id == currentProject.id && wf.issueType == issueType.issueType){
    			 result.push.apply(result, wf.project.users);
    		}
    	});
    	return result; 
    }
    
    $scope.saveIssue = function(issue){
    	issue.entries = $scope.entries;
	    var issueToSend = filterIssue(issue);
    	$http({method: 'POST', url: server+'issuesrv/saveissue',data:issueToSend,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.currentIssue = issue;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.saveIssueFail = true;
		  });
    };
    
    function filterIssue(issueToFilter){
    	var issue = {
    		title: issueToFilter.title,
    		description: issueToFilter.description,
    		issueType: issueToFilter.issueType.issueType,
    		currentStatus: issueToFilter.issueStatus.issueStatus,
    		project: issueToFilter.project.project,
    		owner: issueToFilter.owner,
    		entries: issueToFilter.entries
    	};
    	return issue;
    };
    
    function enableSaveIssueBtn(){
    	$scope.renderedIssueBtn = true;
    }
}