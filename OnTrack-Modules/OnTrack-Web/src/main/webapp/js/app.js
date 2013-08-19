// Create a new module
var issue_mod = angular.module('issue_mod', []);

issue_mod.config(function($locationProvider) {
  $locationProvider.hashPrefix('!');
  $locationProvider.html5Mode(true);
});

var injector = angular.injector(['ng', 'issue_mod']);