// Create a new module
var issue_mod = angular.module('issue_mod', []);

issue_mod.config(function($locationProvider) {
  $locationProvider.hashPrefix('!');
  $locationProvider.html5Mode(true);
});

var injector = angular.injector(['ng', 'issue_mod']);


var project_mod = angular.module('project_mod', []);

project_mod.config(function($locationProvider) {
  $locationProvider.hashPrefix('!');
  $locationProvider.html5Mode(true);
});

angular.injector(['ng', 'project_mod']);

var search_issues_mod = angular.module('search-filter', []);


var profile_mod = angular.module('profile_mod',[]);
angular.injector(['ng','profile_mod']);


/*
var home_mod = angular.module('homeApp',[]);
angular.injector(['ng', 'homeApp']);
var services = angular.module("homeApp.services",[]);

home_mod.factory('UserService',[
	function($http) {
		return {getCurrentUser : function(){
			return $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
				  	success(function(data, status, headers, config) {
				   		return data;
				  	}).
				  	error(function(data, status, headers, config) {
				  		return {};
				});
		}
	};
}]);
*/



