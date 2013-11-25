
function CreateIssueCtrl($scope,$http,$location){
	
	$scope.currentIssueID = $location.search().issue; 
	
	
	$scope.modifyStatus = false;
    $scope.workflows = [];
    $scope.workflowsByProject = [];
    $scope.currentProject = {};
    $scope.statusByType = [];
    $scope.users = [];
    $scope.renderedIssueBtn = false;
    $scope.issueProperties = [];
    
    $scope.comments = [];
    
    $scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	retrieveWorkflowsByUser($scope.currentUser);
	   	/* 
		$http({method: 'POST', url: $scope.webserver+'contacts',headers: {'Content-Type': 'application/json'}}).
	  			success(function(data, status, headers, config) {
					$scope.members = data;
				}).error(function(data,status,headers,config){
					$scope.noContacts = true;
				});
		*/		
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
    
  
	
    function retrieveWorkflowsByUser(user){
    	$http({method: 'POST', url: $scope.server+'workflowsrv/listworkflowsbyuser',data:user,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.workflows = data;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noWorkflows = true;
		  });
    };
    
    
    
   
    
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
    
    getIssueById($scope.currentIssueID);
    
    function getIssueById(id){
    	if(id != 0 && id!= "undefinded" && id!= null){
	    	$http.get($scope.server+"issuesrv/getissuebyid/"+id).success(function(callback){
	    		$scope.issue = callback;
	    		$scope.modifyStatus = true;
	    		$scope.comments = $scope.issue.comments;
	    		getStatusByHTTPForModify($scope);
	    		$scope.currentProject = issue.project;
	    		
	    	});
    	}
    }
    
    getStatusByHTTPForModify($scope);
    
    function getStatusByHTTPForModify($scope){
    
	    if($scope.issue == null || $scope.issue.issueType == null){
	    	return ;
	    }
    			$http({method: 'POST', url: $scope.server+'issuestatussrv/getissuestatusbyissuetype',data:$scope.issue.issueType,headers: {'Content-Type': 'application/json'}}).
				  success(function(data, status, headers, config) {
		    	 	$scope.issueStatusByWorkflow = data;
		    	 	$scope.issueStatusForModify = getIssueStatusByProjectAndTypeForModify($scope.issueStatusByWorkflow);
		    	 	
				  }).
				  error(function(data, status, headers, config) {
				  	$scope.statusByWorkflowFail = true;
				  });
    }
    
    
    //TODO esta function se invoca desde otro llamado de http, podrían generarse demoras por el async en ambas llamadas
    function getIssueStatusByProjectAndTypeForModify(issueStatusByWorkflow){
    	var result = [];
    	angular.forEach(issueStatusByWorkflow,function(isbwf,idx){
					
					$http({method: 'GET', url: $scope.server+'issuestatussrv/getissuestatusbyid/'+isbwf.pk.status}).
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
    	
    		
    	return result; 
    }
    
    function existIssue(id){
    	return (id != 0 && id!= "undefinded" && id!= null);
    }
    
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
    
     $scope.saveComment = function(text){
	     var comment = {
	    	author: "Oscar",
	    	date: "06-08-2013",
	    	text: text,
	    	issueID: $scope.currentIssueID
	     };
    	 												 	 
    	 $http({method: 'POST', url: $scope.server+'issuesrv/addcommenttoissue',data:comment,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
    	 	$scope.comments.push(comment);
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.saveCommentFail = true;
		  });
    	 
    };
    
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
    			
    				$http({method: 'GET', url: $scope.server+'issuestatussrv/getissuestatusbyid/'+isbwf.pk.status}).
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
    	issue.comments = $scope.comments;
    	var issueToSend = {};
    	if($scope.modifyStatus){
    		issueToSend = issue;
    	}else{
    		issue.reporter = $scope.currentUser.userName;
	    	issueToSend = filterIssue(issue);
    	}
    	
    	$http({method: 'POST', url: $scope.server+'issuesrv/saveissue',data:issueToSend,headers: {'Content-Type': 'application/json'}}).
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
    		reporter: issueToFilter.reporter,
    		description: issueToFilter.description,
    		issueType: issueToFilter.issueType.issueType,
    		currentStatus: issueToFilter.issueStatus.issueStatus,
    		project: issueToFilter.project.project,
    		owner: issueToFilter.owner,
    		entries: issueToFilter.entries,
    		reporter: issueToFilter.reporter
    		
    		
    	};
    	return issue;
    };
    
    function filterIssueForModify(issueToFilter){
    
    }
    
    function enableSaveIssueBtn(){
    	$scope.renderedIssueBtn = true;
    }
}