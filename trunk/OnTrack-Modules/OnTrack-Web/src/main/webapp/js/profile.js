function ProfileController($scope,$http,$location){

	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	  
	 $scope.saveUser = function(user){
	 	$http({method: 'POST', url: $scope.server+'usersrv/updateuser',data:user, headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.currentUser = data;
		   	$scope.saveOk = true;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noUser = true;
		  	$scope.saveFail = true;
		  });
		 };

}