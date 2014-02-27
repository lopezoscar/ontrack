function ProfileController($scope,$http,$location){

	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    $scope.userNameRequired = false;
    $scope.userNameExist = false;
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	  
	  $scope.saveUser = function(user){
	  
	  		var username = $scope.currentUser.userName;
	  		 if(typeof username === "undefined" || username == null){
			 		$scope.userNameRequired = true;
			 		return;
			 };
			 
    		$http({method: 'GET', url: $scope.server+'usersrv/existusername/'+username,headers: {'Content-Type': 'application/json'}}).
			  success(function(data, status, headers, config) {
			  
			   	if(data == 'true'){
			   		$scope.userNameExist = true;
			   	}else{
			   		$http({method: 'POST', url: $scope.server+'usersrv/updateuser',data:user, headers: {'Content-Type': 'application/json'}}).
					  success(function(data, status, headers, config) {
					   	$scope.currentUser = data;
					   	$scope.saveOk = true;
					   	$('#myModal').modal('hide');
					   	
					  }).
					  error(function(data, status, headers, config) {
					  	$scope.noUser = true;
					  	$scope.saveFail = true;
					  });
					 }
			  }).
			  error(function(data, status, headers, config) {
			  	$scope.noProjects = true;
			  });
    	};
	  
	 
	 	
	 	
	 	
	 	
	 
	 	

}
