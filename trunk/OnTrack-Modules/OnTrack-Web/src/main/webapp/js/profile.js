function ProfileController($scope,$http,$location){

	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    $scope.userNameRequired = false;
    $scope.userNameExist = false;
    $scope.avoidExistUsername = false;
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	  
	  $scope.saveUser = function(user){
	  
	  		 var username = $scope.newUserName;
	  		 if(typeof username === "undefined" || username == null){
			 		$scope.userNameRequired = true;
			 		return;
			 };
			 if(typeof  $scope.currentUser.lastName === "undefined" || $scope.currentUser.lastName == null){
			 		$scope.userNameRequired = true;
			 		return;
			 };
			 if(typeof $scope.currentUser.firstName === "undefined" || $scope.currentUser.firstName == null){
			 		$scope.userNameRequired = true;
			 		return;
			 };
			 
			 
			 if($scope.newUserName == $scope.currentUser.userName){
				$scope.avoidExistUsername = true;			 
			 }
			 
    		$http({method: 'GET', url: $scope.server+'usersrv/existusername/'+username,headers: {'Content-Type': 'application/json'}}).
			  success(function(data, status, headers, config) {
			  
			   	if(data == 'true' && !$scope.avoidExistUsername){
			   		$scope.userNameExist = true;
			   	}else{
			   		$scope.currentUser.userName = $scope.newUserName;
			   		user = $scope.currentUser;
			   	
			   		$http({method: 'POST', url: $scope.server+'usersrv/updateuser',data:user, headers: {'Content-Type': 'application/json'}}).
					  success(function(data, status, headers, config) {
					   	$scope.currentUser = data;
					   	$scope.saveOk = true;
					   	$scope.userNameExist = false;
					   	$('#myModal').modal('hide');
					   	var server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
					   	window.location = server+"home.html";
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
