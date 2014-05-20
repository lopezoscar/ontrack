function SkinController($scope){

	$scope.updateSkin = function(skin){
		$scope.selectedSkin = skin;
		$scope.skinUrl = "css/skins/"+$scope.selectedSkin+"/bootstrap.css";
		
		
		document.getElementById("bootstrapLink").href = $scope.skinUrl;
	}
}

function MenuController($scope,$http,$location){
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	$http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.currentUser = data;
		  }).error(function(data, status, headers, config) {
			$scope.noUser = true;
	});
}