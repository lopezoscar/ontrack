function SearchIssueFilterCtrl($scope,$http){
	$scope.issues = [];
	
	$scope.server = "http://localhost:8080/OnTrack-SOA/";
	$scope.webserver = "http://localhost:8080/OnTrack/";
    
    
    
    $scope.ownerFilter = new OwnerFilter();
    $scope.ownerFilter.owner = 1;
    $scope.ownerFilter.init($scope);
    $scope.ownerFilter.searchIssues($http);
	
}

function retrieveIssuesByGet($scope,$http,url){
	$http.get($scope.server+url).success(function(callback){
		$scope.issues = callback;
	});
};

function retrieveIssuesByPOST($scope,$http,url,data){
	$http.post($scope.server+url).data(data).success(function(callback){
		$scope.issues = callback;
	});
};

function OwnerFilter(){
	this.scope = {};

	this.name="Owner";
	this.owner ="";
	this.url = "issuesrv/getissuesbyownerid";
	this.init = function(scope){
		this.scope = scope;
	};
	
	this.isOwnerFilter = true;
	
	this.searchIssues = function($http){
		var params = [];
		params.push(this.owner);
		retrieveIssuesByGet(this.scope,$http,buildURL(this.url,params));
	};
	
	function buildURL(url,params){
		var result = url;
		angular.forEach(params, function(value, key){
			result = result+"/"+value;
		});
		return result;
	}
}