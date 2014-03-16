function HomeController($scope,$http,$location){
	$scope.issues = [];
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    	
	$http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	if($scope.currentUser.userName == null || $scope.currentUser.lastName || $scope.currentUser.firstName){
	   		$location.path("profile.html");
	   	} 
	   	
    	$http({method: 'POST', url: $scope.server+"issuesrv/listIssuesByUser",data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	/*
		   	var rows = parseChartDataCurrentStatus($scope.issues);
		   	drawChart(rows,'status_div','Mis Issues Por Estado Status');
		   	rows = parseChartDataIssueType($scope.issues);
		   	drawChart(rows,'type_div','Mis Issues Por Tipo de Issue');
		   	*/
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
		  
		retrieveProjectsByUser($scope.currentUser);  
		  
		  
		  
		  
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	  
   			
   // $scope.currentUser = UserService.getCurrentUser();
    
	
    function retrieveProjectsByUser(user){
    	$http({method: 'POST', url: $scope.server+'projectsrv/projectsbyuser',data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.projects = data;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noProjects = true;
		  });
    };
    
    
    
    function retrieveProjectsByAdmin(user){
    	$http({method: 'POST', url: $scope.server+'projectsrv/projectsbyadmin',data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.projects = data;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noProjects = true;
		  });
    }
    
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




