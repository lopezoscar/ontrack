var profile_mod = angular.module('profile_mod',[]);
profile_mod.config(function($locationProvider) {
  $locationProvider.hashPrefix('!');
  $locationProvider.html5Mode(false);
});

angular.injector(['ng','profile_mod']);