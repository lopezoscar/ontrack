function ProfileModal($scope,$http,$location){

	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	

    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	if(typeof $scope.currentUser.userName === "undefined" || $scope.currentUser.userName == null || $scope.currentUser.lastName == null || $scope.currentUser.firstName == null){
			var options = {
						show:true,
						keyboard: false,
						backdrop: 'static'
						};
			
			$('#myModal').modal(options);	
	   	}
	   	
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });

}