function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

function CreateIssueCtrl($scope,$http,$location){

	
	//CKEDITOR.on('instanceReady', function(){ CKEDITOR.instances.editor.setData("Coloque una descripción aquí"); }); 
	
	$scope.currentIssueID = GetURLParameter("issue");
	
	$scope.issueStatusForModify = [];
	$scope.projectsFromWorkflow = [];
	$scope.isSaved = false;
	$scope.modifyStatus = false;
    $scope.workflows = [];
    $scope.workflowsByProject = [];
    $scope.currentProject = {};
    $scope.statusByType = [];
    $scope.users = [];
    $scope.renderedIssueBtn = false;
    $scope.issueProperties = [];
    $scope.editOwner = false;
    
    $scope.validateIssueStatus = function(){
    	$scope.workflowError = false;
    	$scope.showErrorPanel = false;
    	if(typeof $scope.lastStatus === "undefined"){
    		return;
    	}
    	if(typeof $scope.lastStatus.position === "undefined"){
    		return;
    	}
    	
    	if($scope.lastStatus.position == 0){
    		$scope.searchPositionForIssueStatus($scope.lastStatus);
    	}
    	
		if($scope.issue.currentStatus.position > $scope.lastStatus.position){
			var positionResult = $scope.issue.currentStatus.position - $scope.lastStatus.position;
			if(positionResult != 1){
				$scope.workflowError = true;
				$scope.showErrorPanel = true;
				$scope.issue.currentStatus = $scope.lastStatus;
				window.scrollTo(0,document.body.scrollHeight);
			}
		}
    };
    
    $scope.searchPositionForIssueStatus = function(lastStatus){
    	angular.forEach($scope.issueStatusForModify,function(is,itemNo){
    		if(is.id == lastStatus.id){
    			$scope.lastStatus.position = is.position;
    			return;
    		}	
    	});	
    }
    
    $scope.validateIssueStatusForCreate = function(){
    	$scope.workflowError = false;
    	$scope.showErrorPanel = false;
    	
		if($scope.issue.issueStatus.position > 1){
				$scope.workflowError = true;
				$scope.showErrorPanel = true;
				
				angular.forEach($scope.statusByType,function(status,itemNo){
					if(status.position == 0 || status.position == 1){
						$scope.issue.issueStatus = status;
						return;
					}
				});
				
				return;
		}
    };
    
    
    $scope.titleError = false;
    $scope.workflowError = false;
    $scope.showErrorPanel = false;
    
    $scope.setEditOwner = function(){
    	$scope.editOwner = true;
    }
    
    $scope.comments = [];
    
    $scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	
	
	getIssueById($scope.currentIssueID);
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   
	   	if(!$scope.modifyStatus){
			
	   		retrieveWorkflowsByUser($scope.currentUser);
	   	}
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
    
    $scope.setInitStatus = function(statusList){
    	
    	angular.forEach(statusList,function(status,itemNo){
    		if(status.position == 1 || status.position == 0){
    			$scope.lastStatus = $scope.issue.currentStatus;
    			$scope.issue.currentStatus = status;
    		}
    	});
    };
  
	
    function retrieveWorkflowsByUser(user){
    	$http({method: 'POST', url: $scope.server+'workflowsrv/listworkflowsbyuser',data:user,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.workflows = data;
		   	filterProjectsFromWorkflow($scope.workflows);
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noWorkflows = true;
		  });
    };
    
    function filterProjectsFromWorkflow(workflows){
    	var projectsIds = [];
    	angular.forEach(workflows,function(wf,itemNo){
    		if(projectsIds.indexOf(wf.project.id) < 0) {
				projectsIds.push(wf.project.id);
    		}
    	});
    	
    	var projects = [];
    	
		angular.forEach(projectsIds,function(pid,itemNo){
			//var project = getProjectById(pid);
	    	if(pid != 0 && pid!= "undefinded" && pid!= null){
		    	$http.get($scope.server+"projectsrv/projectbyid/"+pid).success(function(callback){
					$scope.projectsFromWorkflow.push(callback);
		    	});
	    	}
		});
		    	
    	return projects;
    };
    
    function getProjectById(id){
    }
    
    
    
   $scope.filterUsers = function(issue){
   		var users = issue.project.users;
   		var list = $scope.filterProjectUsers(users);
   		return list;
   }
    
    $scope.updateIssueTypes = function(issue){
    	//TODO Bug - el primer .project en realidad es el obj Workflow
    	$scope.currentProject = issue.project;
    	$scope.workflowsByProject = getIssueTypesBySelectedProject($scope.currentProject,$scope.workflows);
    	
    	//Correccion reset status
    	issue.issueStatus = {};
    	
    	
    	$scope.projectUsers = $scope.filterProjectUsers($scope.currentProject.users);
    };
    
    $scope.filterProjectUsers = function(users){
    	var list = [];
    	angular.forEach(users,function(user,itemNo){
    		if(user.userName != null && user.userName != "undefined"){
    			list.push(user);
    		}
    	});
    	return list;
    }
    
    $scope.updateStatusByTypeAndUsers = function (issue){
    	$scope.statusByType = getIssueStatusByProjectAndType($scope.currentProject,issue.issueType,$scope.workflows);
    	$scope.users = getUsersByProjectAndType($scope.currentProject,issue.issueType,$scope.workflows);
    	$scope.issueProperties = issue.issueType.issueProperties;
    	$scope.entries = createEntriesByProperty($scope.issueProperties);
    	
    	$scope.setInitStatus($scope.statusByType);
    };
    
   
    
    function getIssueById(id){
    	if(id != 0 && id!= "undefinded" && id!= null){
	    	$http.get($scope.server+"issuesrv/getissuebyid/"+id).success(function(callback){
	    		$scope.issue = callback;
	    		$scope.modifyStatus = true;
	    		
	    		$scope.lastStatus = $scope.issue.currentStatus;
	    		
	    		$scope.comments = $scope.issue.comments;
	    		$scope.entries = $scope.issue.entries;
	    		getStatusByHTTPForModify($scope);
	    		$scope.currentProject = $scope.issue.project;
	    		CKEDITOR.instances.editor.setData($scope.issue.description);
	    		
	    		$scope.projectUsers = $scope.filterUsers($scope.issue);
	    		
	    	});
    	}
    }
    
    
    
    function getStatusByHTTPForModify($scope){
	    if($scope.issue == null || $scope.issue.issueType == null){
	    	return ;
	    }
    
    	$scope.issueStatusForModify = [];
	    
	    var urlService = $scope.server+'issuestatussrv/getissuestatusbyissuetype/'+$scope.issue.issueType.id+"/"+$scope.issue.project.id;
    			$http({method: 'GET', url: urlService,headers: {'Content-Type': 'application/json'}}).
				  success(function(data, status, headers, config) {
		    	 	$scope.issueStatusByWorkflow = data;
		    	 	
		    	 	getIssueStatusByProjectAndTypeForModify($scope.issueStatusByWorkflow);
		    	 	
		    	 	//Issue status for modify
		    	 	/*
					angular.forEach($scope.issueStatusForModify,function(issueStatus,itemNo){
		    	 		if(issueStatus.id == $scope.issue.currentStatus.id){
		    	 			$scope.issue.currentStatus.position = issueStatus.position;
		    	 		}
		    	 		console.log(issueStatus);
		    	 	});
		    	 	*/		    	 	
		    	 	
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
					  	issueStatus.position = isbwf.position;
	    				var status = {
	    					issueStatus: issueStatus,
	    					position: isbwf.position
	    				};
	    				
	    				if($scope.lastStatus.id == status.issueStatus.id){
	    					$scope.lastStatus = status.issueStatus;
	    				}
    			 		$scope.issueStatusForModify.push(status);    	
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
    
	//For todays date;
	Date.prototype.today = function(){ 
	    return ((this.getDate() < 10)?"0":"") + this.getDate() +"/"+(((this.getMonth()+1) < 10)?"0":"") + (this.getMonth()+1) +"/"+ this.getFullYear() 
	};
	//For the time now
	Date.prototype.timeNow = function(){
	     return ((this.getHours() < 10)?"0":"") + this.getHours() +":"+ ((this.getMinutes() < 10)?"0":"") + this.getMinutes() +":"+ ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
	};
	
	
    
     $scope.saveComment = function(text){
     	var newDate = new Date();
	     var comment = {
	    	author: $scope.currentUser.userName,
	    	date: newDate.today(),
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
					  	issueStatus.position = isbwf.position;
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
    	$scope.titleError = "false";
    	
    	if(typeof issue === "undefined"){
    		$scope.titleErrorMessage = "Falta el título";
    		$scope.titleError = true;
    		return;
    	}
    	
    	if(typeof issue.title === "undefined" || issue.title == null){
    		$scope.titleError = true;
    		$scope.titleErrorMessage = "Falta el título";
    		return ;
    	}
    	
    	$scope.isSaved = true;
   
    	issue.description = CKEDITOR.instances.editor.getData();
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
		   	
		   	if(! $scope.modifyStatus){
		   		window.location = $scope.webserver;
		   	}
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
    		project: issueToFilter.project,
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