function ServicesController($scope,$http,$location){
	$scope.response = {};
	$scope.currentSrv = {};
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	
	$scope.saveCurrentService = function (service){
		$scope.currentService = service;
	}
	
	$scope.post = function(srv){
		$scope.currentSrv = srv;
		
		//$http.post(srv.path, srv.body).success(function (callback){
			//$scope.currentSrv.response = angular.toJson(callback,true);
		//});
		
		
		$http({method: 'POST', url: $scope.server+srv.path,data:srv.body,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		    $scope.currentSrv.response = angular.toJson(data,true);
		    $scope.currentSrv.header = headers();
		    $scope.currentSrv.status = status;
		  }).
		  error(function(data, status, headers, config) {
		    $scope.currentSrv.response = angular.toJson(data,true);
		    $scope.currentSrv.header = headers();
		    $scope.currentSrv.status = status;
		  });
			
	};
	
	
	
	$http.get($scope.server+"consolesrv/listservices").success(function(callback){
		$scope.services = callback;
		$scope.categories = [];
		var category = {};
		angular.forEach($scope.services,function(value,key){
			category.name = key;
			
			angular.forEach(value,function(method,key){
				if(typeof method.path != "undefined" && method.path.charAt(0) == '/'){
					method.path = method.path.replace('/','');	
				}
				if(method.method == "POST"){
					angular.forEach(method.parameters,function(param,itemNo){
						param.body = JSON.stringify(param.body);
					});
				}
				
			});
			
				
			category.methods = value;
			$scope.categories.push(category);
			category = {};
		});
	});
	
	
	$scope.get = function(srv){
		$scope.currentSrv = srv;
		//$http.get(srv.path).success(function(callback){
			//$scope.currentSrv.response = angular.toJson(callback,true);
		//});
		
	$http({method: 'GET', url: $scope.server+srv.path}).
	  success(function(data, status, headers, config) {
	    $scope.currentSrv.response = angular.toJson(data,true);
	    $scope.currentSrv.header = headers();
	    $scope.currentSrv.status = status;
	  }).
	  error(function(data, status, headers, config) {
	    $scope.currentSrv.response = angular.toJson(data,true);
	    $scope.currentSrv.header = headers();
	    $scope.currentSrv.status = status;
	  });
		
	};
	
	$scope.isGet = function(srv){
		return srv.method == "GET";
	};
	
	$scope.isPost = function(srv){
		return srv.method == "POST";
	};


}
