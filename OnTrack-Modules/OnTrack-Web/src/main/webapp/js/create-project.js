function CreateProjectCtrl($scope,$http,$location){

	$scope.disableActions = false;
	$scope.selectedMember = "";
	$scope.currentProjectID = GetURLParameter("project");
	$scope.modifyStatus = false;
	$scope.members = [];
	$scope.issueTypes = [];
	$scope.issueProperties = [];
	$scope.workflows = [];
	$scope.savedProject = {};
	$scope.selectedMembers = [];
	$scope.projectNameNotFound = false;
	$scope.hideLoadContacts = false;
	$scope.isAdminError = false;
	
	
	    
    $scope.saveIssueTypeForKeyPress = function(ev) {
        $scope.pressed = ev.which;
	    if (ev.which==13){
	    	saveIssueType(desc);
	    	
	    }
	};
	
	$scope.redirect = function(){
		$scope.$apply(function() { $location.path("home.html"); });
	
	};
	
	$scope.emptyContactList = false;
	$scope.wrongMail = false;
	
	$scope.updateCurrentType = function(type){
    	$scope.currentTypeForIssueStatus = type;
    	//$scope.$apply();
    };
    
    $scope.createMember = function(selectedMember){
    	try{
    		var titleAndMail = selectedMember.split("-");
    		var member = {
				title: titleAndMail[0],
				email: titleAndMail[1].trim()
		        	};
			return member;
    	}catch(err){
    		console.log(err);
    		return;
    	}
		
    };
	
	$scope.addMember = function (selectedMember){
		$scope.userAlreadyExist = false;
		$scope.wrongMail = false;
		$scope.isAdminError = false;
		$scope.emptyContactList = false;
	
		var input = document.getElementById("contactsBox");
		var selectedMember = input.value;

		
		var member = $scope.createMember(selectedMember);
		if(typeof member === "undefined"){
			$scope.wrongMail = true;
			return;
		}
		
		var keepGoing = true;		        	 
		if(typeof $scope.membersForAutocomplete != "undefined"){
			angular.forEach($scope.membersForAutocomplete,function(contactMemberRaw,itemNo){
				if(keepGoing){
					var contactMember = $scope.createMember(contactMemberRaw);
					if(contactMember.email != member.email){
						$scope.wrongMail = true;
					}else{
						keepGoing = false;
						$scope.wrongMail = false;
					}
				}	
			});
			
		}else{
			$scope.emptyContactList = true;
		}         	

		if($scope.wrongMail || $scope.emptyContactList){
			return;
		}
		var existUser = false;
		var keepGoing = true;
		
		//Valida los existentes
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
		if($scope.modifyStatus){
			var user = $scope.selectedMembers[$index];
			
			
		$http({method: 'GET', url: $scope.server+'usersrv/removeMember/'+user.email+'/'+$scope.project.id,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		  		if(data == "isAdmin"){
		  			$scope.isAdminError = true;
		  		}else{
		  			$scope.selectedMembers.splice($index,1);
		  		}
		  }).
		  error(function(data, status, headers, config) {
					    console.log(status);
			});
			
		}else{
			$scope.selectedMembers.splice($index,1);
		}
	
	
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
    					email:user.mail.trim()
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
		
		property.type = {id: 1, 
						 name:"Texto"
						};
		
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
	
	$scope.saveStatusForKeyPress = function(event,status) {
			//13 == Enter Key
		  if (event.which==13)
		    $scope.saveStatus(status);
	}
	
	$scope.saveStatus = function(status){
		var itIdx = $scope.issueTypes.indexOf(status.type);
		var issueType = $scope.issueTypes[itIdx];
		
		issueType.status.push(status);
		
		$scope.currentTypeForIssueStatus = status.type;
		
		$scope.status = angular.copy({});
		
		$scope.status.type = status.type;
		
		
		
	};
	
	$scope.setCurrentIssueType = function(type){
		$scope.currentIssueType = type;
	};
	
	$scope.removeStatusFromIssueType = function(status){
		var itIdx = $scope.issueTypes.indexOf(status.type);
		var issueType = $scope.issueTypes[itIdx];
		
		var statusIdx = issueType.status.indexOf(status);
		issueType.status.splice(statusIdx,1);
	};
	
	$scope.updateCurrentStatus = function(type){
		$scope.currentTypeForIssueStatus = type;
	};
	
	function parseMemberToUser(){
		var users = [];
		if(! $scope.modifyStatus){
			users.push($scope.currentUser);
		}
		angular.forEach($scope.selectedMembers,function(member,itemNo){
			var user = {
				userName: member.title,
				mail: member.email.trim()
			};
			users.push(user);
		});
		
		
		return users;
	}
	
	$scope.saveProject = function(){
		$scope.userAlreadyExist = false;
		$scope.wrongMail = false;
		$scope.isAdminError = false;
		$scope.emptyContactList = false;
	
	
		if(typeof $scope.project === "undefined"){
			$scope.projectNameNotFound = true;
			return;
		}
	
		if(typeof $scope.project != "undefined" && typeof $scope.project.name === "undefined"){
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
		 
		 
		var parsedUsers = parseMemberToUser();
		var project = {
			name: $scope.project.name,
			users: parsedUsers,
			admin: $scope.currentUser
		};
		
		
		if($scope.project != null && $scope.modifyStatus){
			project = $scope.project;
			project.users = parsedUsers;
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


function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}








