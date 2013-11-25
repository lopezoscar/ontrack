function CreateProjectCtrl($scope,$http,$location){

	$scope.disableActions = false;
	$scope.selectedMember = "";
	$scope.currentProjectID = $location.search().project; 
	$scope.modifyStatus = false;
	//$scope.members = [{"selected":false,"name":"Daniel Galanti","email":"dgalanti@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/64ac740a52fc9e"},{"selected":false,"name":"arielaguirre1420@gmail.com","email":"arielaguirre1420@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/c6f1850f2fb5ed"},{"selected":false,"name":"Gonzalez Mariela","email":"mgonzalez@escueladavinci.net","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/3e516260eef2ebe"},{"selected":false,"name":"Pablo Sebastián Reitano","email":"pablo.reitano@softtek.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/449a107897e0f33"},{"selected":false,"name":"Agostina Sysone","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/4981aea09f156e9"},{"selected":false,"name":"Pablo Pallocchi","email":"pablopallocchi@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/56346228fbc91fc"},{"selected":false,"name":"Roselis Guzmán","email":"rguzman@sysone.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/57db2118a77a322"},{"selected":false,"name":"toluispo@hotmail.com","email":"toluispo@hotmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/5b8ca91880639fa"},{"selected":false,"name":"Santiago Lohigorry","email":"santiago.lohigorry@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6af932f0c1e425a"},{"selected":false,"name":"Robert Anderson","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6d927a40c5b1b3e"},{"selected":false,"name":"Cristian Mielgo","email":"cristian.mielgo@cesvi.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/6dc2d040f6c9263"},{"selected":false,"name":"Pablo Romanos","email":"pabloromanos@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/7e6ca7d08ab62c8"},{"selected":false,"name":"Gustavo Martinez","email":"gmartinez@datatech.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/7f75160094efbd4"},{"selected":false,"name":"Nicolas Gladkoff","email":"ngladkoff@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/80b6f8f899a5e20"},{"selected":false,"name":"Alejandro Gaete","email":"agaete.rsa@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/878111d8acdaa59"},{"selected":false,"name":"maxilimeres@gmail.com","email":"maxilimeres@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/8acb8140bf874de"},{"selected":false,"name":"Romero, Sergio","email":"sromero@rsaelcomercio.com.ar","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/90c12678c1d7fd4"},{"selected":false,"name":"Matias Antunez","email":"matias.antunez@cardinalsisa.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/988047f8c179e4b"},{"selected":false,"name":"Laura Vazquez Profe","email":"","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/9a25d700b9cfd52"},{"selected":false,"name":"Cecilia Bietti","email":"cbietti@gmail.com","photoLink":"https://www.google.com/m8/feeds/photos/media/lopezoscar.job%40gmail.com/9cd25ce8abb307c"}]
	$scope.members = [];
	$scope.issueTypes = [];
	$scope.issueProperties = [];
	$scope.workflows = [];
	$scope.savedProject = {};
	$scope.selectedMembers = [];
	$scope.projectNameNotFound = false;
	$scope.redirect = function(){
		$scope.$apply(function() { $location.path("home.html"); });
	
	};
	
	$scope.addMember = function (selectedMember){
		$scope.userAlreadyExist = false;
	
		var input = document.getElementById("contactsBox");
		var selectedMember = input.value;

		var titleAndMail = selectedMember.split("-");
		
		var member = {
				title: titleAndMail[0],
				email: titleAndMail[1]
		        	};

		var existUser = false;
		var keepGoing = true;
        $scope.selectedMembers.forEach(function(existingMember,itemNo){
        	if(!keepGoing) return;
        	
        	if(existingMember.email == member.email){
        		existUser = true;
        		keepGoing = true;
        		$scope.existingMember = existingMember;
        	}
        	
        });	
        
        if(!existUser){
        	$scope.addUser(member);
        }else{
        	$scope.userAlreadyExist = true;
        }	
     
        	
        	//$scope.$apply();
        input.value = "";
	};
	
	$scope.removeMember = function($index){
		$scope.selectedMembers.splice($index,1);
	};
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	
	getProjectById($scope.currentProjectID);
	
	function getProjectById(id){
    	if(id != 0 && id!= "undefinded" && id!= null){
	    	$http.get($scope.server+"projectsrv/projectbyid/"+id).success(function(callback){
	    		$scope.project = callback;
	    		$scope.modifyStatus = true;
	    		$scope.isAdmin = $scope.project.admin.id == $scope.currentUser.id;
	    		if($scope.isAdmin){
	    			$scope.disableActions = false;
	    		}else{
	    			$scope.disableActions = true;
	    		}
	    		
	    		fillSelectedMembers($scope.project); 
	    		
		    	listWorkflowsByProject($scope.project);
	    	});
    	}
    };
    
    function fillSelectedMembers(project){
    	var users = project.users;
    	users.forEach(function (user,itemNo){
    		var fullName = "Usuario Pendiente de Registro";
    		if(user.lastName != null && user.firstName != null){
    			fullName = user.lastName+" ,"+user.firstName;
    		}
    	
    		var user = {
    					title:fullName, 
    					email:user.mail
    					};
    		$scope.selectedMembers.push(user);
    	});
    };
    
    
    
    function listWorkflowsByProject(project){
   		 $http({method: 'POST', url: $scope.server+'workflowsrv/listworkflowsbyproject',data:project,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.workflows = data;
		   	retrieveIssueStatusName($scope.workflows);
		   	
		   	listIssueTypesFromWorkflows($scope.workflows);
		   	
		   	
		   	/**
    			"$digest already in progress" error:  
    			If you get this error it's because you called $scope.$apply() 
    			from inside an already-firing $apply() (aka $digest cycle). 
    			If it's impossible to refactor to avoid this accidental recursion (recommended), 
    			then use this check: if (!$scope.$$phase) $scope.$apply()
    			**/
    			if (!$scope.$$phase) {}
		   	
		  
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noWorkflows = true;
		  });
    }
    
    function listIssueTypesFromWorkflows(workflows){
    	angular.forEach(workflows,function(wf,key){
    		var issueType = {
    			id: wf.issueType.id,
    			description: wf.issueType.description,
    			status: []
    		};
    		$scope.issueTypes.push(issueType);
    		
    	});
    }
    
    function listIssueStatusFromIssueType(issueTypes,project,workflows){
    	angular.forEach(issueTypes,function(issueType,key){
    		getIssueStatusByProjectAndType(project,issueType,workflows);
    	});
    }
    
    function listIssuePropertiesFromIssueType(){
    
    }
    
    function retrieveIssueStatusName(workflows){
    	angular.forEach(workflows,function(wf,idx){
    			angular.forEach(wf.issueStatusByWorkflow,function(isbwf,idx){
    				$http({method: 'GET', url: $scope.server+'issuestatussrv/getissuestatusbyid/'+isbwf.pk.status}).
					  success(function(data, status, headers, config) {
					  	var issueStatus = data;
	    				var status = {
	    					issueStatus: issueStatus,
	    					position: isbwf.position,
	    					issueType:wf.issueType
	    				};
					   
					  }).
					  error(function(data, status, headers, config) {
					    console.log(status);
					  });
    			
    			
    						
    			});		
    	});
    }
    
    
    
	
	$scope.addUser = function(member){
		$scope.selectedMembers.push(member);
	};
	
	$scope.removeUser = function(member){
		$scope.selectedMembers.pop(member);
	};
	//$scope.issuePropertyTypes = ["Texto","Numérico","Calendario","Archivo"];
	$http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	/* 
		$http({method: 'POST', url: $scope.webserver+'contacts',headers: {'Content-Type': 'application/json'}}).
	  			success(function(data, status, headers, config) {
					$scope.members = data;
				}).error(function(data,status,headers,config){
					$scope.noContacts = true;
				});
		*/		
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	
	
	$http.get($scope.server+'issuepropertysrv/allissuepropertytypes').success(function (callback){
		$scope.issuePropertyTypes = callback;
	});
	
	$scope.saveIssueType = function (desc){
		//if($.inArray(desc, $scope.issueTypes) < 0){
		
			var issueType = {
				description: desc,
				issueProperties: [],
				status: []
			};
			
			$scope.issueTypes.push(issueType);
			$scope.desc = angular.copy("");
		//}
	};
	
	
	
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
	};
	
	$scope.saveWorkflow = function(workflow){
		if($.inArray(workflow, $scope.workflow) < 0){
			$scope.workflows.push(workflow);
		}
	};
	
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
		$scope.currentIssueType = type;
	};
	
	$scope.removeStatusFromIssueType = function(idx){
		
	};
	
	$scope.updateCurrentStatus = function(type){
		$scope.currentTypeForIssueStatus = type;
	};
	
	function parseMemberToUser(){
		var users = [];
		users.push($scope.currentUser);
		angular.forEach($scope.selectedMembers,function(member,itemNo){
			var user = {
				userName: member.title,
				mail: member.email
			};
			users.push(user);
		});
		
		
		return users;
	}
	
	$scope.saveProject = function(){
		if($scope.project == "undefined"){
			$scope.projectNameNotFound = true;
			return;
		}
	
		if($scope.project != "undefined" && $scope.project.name == "undefined"){
			$scope.projectNameNotFound = true;
			return;
		}
	
	
		var listTypes = [];
		angular.forEach($scope.issueTypes,function(value,key){
			var issueType = {
				id:null,
				project:null,
				description: value.description
			};
			listTypes.push(issueType);
		});
		 
		 
		 
		var project = {
			name: $scope.project.name,
			users: parseMemberToUser(),
			admin: $scope.currentUser
		};
		
		if($scope.project != null && $scope.modifyStatus){
			project = $scope.project;
		}
	
		//{"id":null,"issueType":{"id":1,"description":"Cualquiera"},"issueStatus":[{"id":1,"description":"TODO"}],"project":{"id":1,"name":"Proyecto","roles":[{"id":3,"roleName":"Desarrollador","acronym":"DEV"}],"users":[]}}
		
		$http({method: 'POST', url: $scope.server+'projectsrv/saveproject',data:project,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.savedProject = data;
		   	$scope.projectOK = true;
		   	$scope.redirect();
		   	//Init saveWorkflows
		  var workflowsToSave = [];
		  
		  angular.forEach($scope.issueTypes,function(wf,key){
		  	
		  var issueTypeDescription = $scope.modifyStatus ? wf.description : wf.description;
		  	var workflow = {
				project: $scope.savedProject,
				issueType :  {description:issueTypeDescription},
				issueStatus: filterDescriptionOnStatus(wf.status),
				issueProperties: filterDescriptionAndTypeOnIssueProperties(wf.issueProperties)
			};
			workflowsToSave.push(workflow);
		  });
		  
		  /*
		  var workflow = {
				project: $scope.savedProject,
				issueTypes :  filterDescriptionOnTypes($scope.issueTypes),
				issueStatus: filterDescriptionOnStatus(wf.status)
			};
		  	*/	
		  			
		  
		  $http({method: 'POST', url: $scope.server+'workflowsrv/createworkflowbylist',data:workflowsToSave,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.workflowsArePersisted = true;
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.workflowsArePersisted = false;
		  });
		  
		  //End saveWorkflows
		   	
		   	
		   	
		  }).
		  error(function(data, status, headers, config) {
		  
		  });
		  //END saveProject
		  
		  
		  
		  
		
		
	};
	
	//No debería acceder a elementos del DOM desde un controlador
	//Se hace dado que fue la solución más rápida entre angular y jquery
	var sortableEle;
    $scope.dragStart = function(e, ui) {
        ui.item.data('start', ui.item.index());
    };
    $scope.dragEnd = function(e, ui) {
        var start = ui.item.data('start'),
            end = ui.item.index();
        
        $scope.workflow.type.status.splice(end, 0, 
            $scope.workflow.type.status.splice(start, 1)[0]);
        
        $scope.$apply();
    };
        
    sortableEle = $('#sortable').sortable({
        start: $scope.dragStart,
        update: $scope.dragEnd
    });
    

	
	
	 //TODO mejorar algoritmos de búsqueda no es performance por el foreach
    function getIssueStatusByProjectAndType(currentProject,issueType,workflows){
    	angular.forEach(workflows,function(wf,idx){
    	
    		if(wf.project.id == currentProject.id && wf.issueType.id == issueType.id){
    			
    			
    			
    			angular.forEach(wf.issueStatusByWorkflow,function(isbwf,idx){
    				$http({method: 'GET', url: $scope.server+'issuestatussrv/getissuestatusbyid/'+isbwf.pk.status}).
					  success(function(data, status, headers, config) {
					  	var issueStatus = data;
	    				var status = {
	    					issueStatus: issueStatus,
	    					position: isbwf.position
	    				};
    			 		//issueType.status.push(status);    	
					   
					  }).
					  error(function(data, status, headers, config) {
					    console.log(status);
					  });
    			
    			
    						
    			});		
    		}
    	});
    }
	
	
};

function filterDescriptionOnStatus(status){
	var issueStatusOnlyDesc = [];
	var pos = 0;
	angular.forEach(status,function(value,key){
		pos++;
		issueStatusOnlyDesc.push({description:value.description, position:pos});
	});
	return issueStatusOnlyDesc;
};

function filterDescriptionOnTypes(types){
	var issueTypesOnlyDesc = [];
	angular.forEach(types,function(value,key){
		issueTypesOnlyDesc.push({description:value.description});
	});
	return issueTypesOnlyDesc;
};

function filterDescriptionAndTypeOnIssueProperties(properties){
	var propertiesWithoutIssueType = [];
	angular.forEach(properties,function(value,key){
		propertiesWithoutIssueType.push({description:value.description, type: value.type});
	});
	return propertiesWithoutIssueType;
};







