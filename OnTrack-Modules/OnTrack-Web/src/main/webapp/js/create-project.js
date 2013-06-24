function CreateProjectCtrl($scope,$http){
	//$scope.members = [{"selected":false,"name":"Daniel Galanti","email":"dgalanti@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/64ac740a52fc9e"},{"selected":false,"name":"arielaguirre1420@gmail.com","email":"arielaguirre1420@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/c6f1850f2fb5ed"},{"selected":false,"name":"Gonzalez Mariela","email":"mgonzalez@escueladavinci.net","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/3e516260eef2ebe"},{"selected":false,"name":"Pablo Sebastián Reitano","email":"pablo.reitano@softtek.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/449a107897e0f33"},{"selected":false,"name":"Agostina Sysone","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/4981aea09f156e9"},{"selected":false,"name":"Pablo Pallocchi","email":"pablopallocchi@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/56346228fbc91fc"},{"selected":false,"name":"Roselis Guzmán","email":"rguzman@sysone.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/57db2118a77a322"},{"selected":false,"name":"toluispo@hotmail.com","email":"toluispo@hotmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/5b8ca91880639fa"},{"selected":false,"name":"Santiago Lohigorry","email":"santiago.lohigorry@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6af932f0c1e425a"},{"selected":false,"name":"Robert Anderson","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6d927a40c5b1b3e"},{"selected":false,"name":"Cristian Mielgo","email":"cristian.mielgo@cesvi.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6dc2d040f6c9263"},{"selected":false,"name":"Pablo Romanos","email":"pabloromanos@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/7e6ca7d08ab62c8"},{"selected":false,"name":"Gustavo Martinez","email":"gmartinez@datatech.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/7f75160094efbd4"},{"selected":false,"name":"Nicolas Gladkoff","email":"ngladkoff@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/80b6f8f899a5e20"},{"selected":false,"name":"Alejandro Gaete","email":"agaete.rsa@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/878111d8acdaa59"},{"selected":false,"name":"maxilimeres@gmail.com","email":"maxilimeres@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/8acb8140bf874de"},{"selected":false,"name":"Romero, Sergio","email":"sromero@rsaelcomercio.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/90c12678c1d7fd4"},{"selected":false,"name":"Matias Antunez","email":"matias.antunez@cardinalsisa.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/988047f8c179e4b"},{"selected":false,"name":"Laura Vazquez Profe","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/9a25d700b9cfd52"},{"selected":false,"name":"Cecilia Bietti","email":"cbietti@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/9cd25ce8abb307c"}]
	$scope.members = [];
	$scope.issueTypes = [];
	$scope.issueProperties = [];
	$scope.workflows = [];
	//$scope.issuePropertyTypes = ["Texto","Numérico","Calendario","Archivo"];
	
	var userData = {
		mail: "lopezoscar.job@gmail.com",
		password: "javaDeveloper1"
	};
	
	var server = 'http://localhost:8080/OnTrack-SOA/';
	
	$http.post(server+'usersrv/contacts', userData).success(function (callback){
		$scope.members = callback;
	});
	
	$http.get(server+'issuepropertysrv/allissuepropertytypes').success(function (callback){
		$scope.issuePropertyTypes = callback;
	});
	
	$scope.saveIssueType = function (desc){
		//if($.inArray(desc, $scope.issueTypes) < 0){
		
			var issueType = {
				desc: desc,
				issueProperties: [],
				status: []
			};
			
			$scope.issueTypes.push(issueType);
			$scope.desc = angular.copy("");
		//}
	}
	
	$scope.savePropertyType = function (property){
		//Work around para create-project.html. Se necesita setear por default un valor en el ng-model
		property.issueType = $scope.currentIssueType;
		
		var idx = $scope.issueTypes.indexOf($scope.currentIssueType);
		var it = $scope.issueTypes[idx] ;
		
		if($.inArray(property, it.issueProperties) < 0){
			it.issueProperties.push(property);
		}
		
		$scope.property = angular.copy({});
		$scope.currentIssueType = angular.copy({});
	}
	
	$scope.saveWorkflow = function(workflow){
		if($.inArray(workflow, $scope.workflow) < 0){
			$scope.workflows.push(workflow);
		}
	}
	
	$scope.removeIssueType = function (idx){
		 //var index=$scope.issueTypes.indexOf(idx)
		//if($.inArray(desc, $scope.issueTypes) == 0){
			$scope.issueTypes.splice(idx,1);
		//}
	};
	
	$scope.removePropertyType = function (idx){
	 //var index=$scope.issueTypes.indexOf(idx)
	//if($.inArray(desc, $scope.issueTypes) == 0){
	
		$scope.currentIssueType.issueProperties.splice(idx,1);
		
		var itIdx = $scope.issueTypes.indexOf($scope.currentIssueType);
		
		$scope.issueTypes[itIdx] = angular.copy($scope.currentIssueType);
		
		//$scope.issueProperties.splice(idx,1);
	//}
	};
	
	$scope.saveStatus = function(status){
		var itIdx = $scope.issueTypes.indexOf(status.type);
		var issueType = $scope.issueTypes[itIdx];
		
		issueType.status.push(status);
		
		$scope.currentTypeForIssueStatus = status.type;
		
		$scope.status = angular.copy({});
		
		
	};
	
	$scope.setCurrentIssueType = function(type){
		$scope.currentIssueType = type
	};
	
	$scope.removeStatusFromIssueType = function(idx){
		
	};
	
	$scope.updateCurrentStatus = function(type){
		$scope.currentTypeForIssueStatus = type;
	};
}




