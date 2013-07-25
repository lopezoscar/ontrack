
 
function CreateIssueCtrl($scope,$http){
    //$scope.workflows = [{"id":3,"issueType":{"id":1,"description":"Bug"},"issueStatus":[{"id":1,"description":"TODO"}],"project":{"id":1,"name":"Proyecto","roles":[{"id":3,"roleName":"Desarrollador","acronym":"DEV"}],"users":[{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkMAYTsPU9NHMnyriu6ija1u-qkqW5mS3I","password":"Test","roles":[],"projects":[]}]}}];
    $scope.workflows = [];
    $scope.workflowsByProject = [];
    $scope.currentProject = {};
    $scope.statusByType = [];
    $scope.users = [];
    $scope.renderedIssueBtn = false;
    
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
    			 result.push.apply(result, wf.issueStatus);
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
    	$http({method: 'POST', url: server+'issuesrv/saveissue',data:issue,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.currentIssue = issue;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.saveIssueFail = true;
		  });
    };
    
    function enableSaveIssueBtn(){
    	$scope.renderedIssueBtn = true;
    }
}