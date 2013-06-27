function MyIssuesController($scope,$http,$location){
	
	//Debería traerme los datos del usuario de sesion
	
	var server = "http://localhost:8080/OnTrack-SOA/";
	var reporterURL = "issuesrv/getissusbyreporter/";
	var ownerURL = "issuesrv/getissuesbyownerid";
	$scope.issues = [{"id":1,"title":"Error al guardar Issue","description":"NullPointerException al Guardar un Issue del tipo Bug para el proyecto 1","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":1,"description":"Cualquiera"},"parent":null,"childs":null,"project":null,"entries":null},{"id":2,"title":"Error al crear estadística","description":"Error al generar la estadística para los tipos de issue","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":1,"description":"Cualquiera"},"parent":null,"childs":null,"project":null,"entries":null},{"id":6,"title":"Titulo del Issue","description":"<p>\r\n\tdes</p>\r\n","reporter":"Oscar","owner":{"id":1,"firstName":"Oscar","lastName":"Lopez","mail":"lopezoscar.job@gmail.com","userName":"https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8","password":"Test","roles":[{"id":1,"roleName":"Usuario","acronym":"ROLE_USER"},{"id":2,"roleName":"Administrador","acronym":"ROLE_ADMIN"}],"projects":[]},"currentStatus":{"id":1,"description":"TODO"},"issueType":{"id":2,"description":"Issue"},"parent":null,"childs":null,"project":null,"entries":null}];
	$scope.viewIssue = function(issue){
		$location.path('/create-issue.html');
	};
	//$scope.issues = [];
	//$scope.issues.pushAll(retrieveIssues(server+reporterURL+"Oscar"));
	//$scope.issues.pushAll(retrieveIssues(server+ownerURL+"1"));	
}
function retrieveIssues(url){
	$http.get(url).success(function(callback){
		return callback;	
	});
}