function StatsController($scope,$http,$location){

	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	$scope.projects = [];
	$scope.issuesBySelectedProject = [];
	$scope.showDateRange = false;
	$scope.fromDateEmpty = false;
	$scope.toDateEmpty = false;
	$scope.fromDateAfterToDate = false;
	$scope.issueTypesList = [];
	$scope.issuesForIssueStatusByType = [];
	$scope.issueByProjectAndIssueType = [];
	$scope.issueByProjectFilter = [];
	$scope.issuesByProjectAndFilterByCreatedDate = [];
	
	$scope.showCreatedDateChart = false;
	$scope.showCreatedDateChartLoaded = false;
	$scope.isProjectLoaded = false;
	
	$scope.showNoDataForCreatedDateChart = false;
	
	$scope.filterIssueTypes = function(issues){
		$scope.issueTypesList = [];
		var ids = [];	
		angular.forEach(issues,function(issue,itemNo){
			if(ids.indexOf(issue.issueType.id) < 0){
				ids.push(issue.issueType.id);
				$scope.issueTypesList.push(issue.issueType);
			}
		});
	};
	
	$scope.filterIssuesByType = function(issues){
		if(typeof $scope.selectedIssueType != "undefined"){
			angular.forEach(issues,function(issue,itemNo){
				if(issue.issueType.id == $scope.selectedIssueType.id){
					$scope.issuesForIssueStatusByType.push(issue);
				}
			});
		}
	};
	
	$scope.filterIssuesByProjectFilter = function(issues){
		angular.forEach(issues,function(issue,itemNo){
			if(issue.project.id == $scope.selectedProject.id){
				$scope.issueByProjectFilter.push(issue);
			}
		});
	
	};
	
	$scope.filterIssuesByIssueTypeAndProject = function(issues){
		angular.forEach(issues,function(issue,itemNo){
			if(issue.project.id == $scope.selectedProject.id && issue.issueType.id == $scope.selectedIssueType.id){
				$scope.issueByProjectAndIssueType.push(issue);
			}
		});
	
	};
	
	$scope.searchIssuesFromProject = function(){
	
			$scope.lookupService = "issuesrv/listIssuesByUserFromProject"; 
	  
			$http({method: 'POST', url: $scope.server+$scope.lookupService,data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
			  success(function(data, status, headers, config) {
			  		$scope.issuesForProject = data;
			  		$scope.filterIssueTypes($scope.issuesForProject);
			  		
			  		$scope.fillProjectList();
			  		/*var rows = parseChartDataForIssueByProject($scope.issuesForProject);
			  		drawChart(rows,'cant_issues_div','Issues Por Proyecto');*/
			  		
			  		
			  }).error(function(data, status, headers, config) {
			  	$scope.noUser = true;
			  });
	};
	
	$scope.searchIssueTypesByRangeOnCurrentDate = function(issues){
		issues.forEach(function(issue){
			console.log("Created Date "+issue.createdDate);
			var nDate = new Date(issue.createdDate);
			console.log("Fecha Timestamp "+issue.createdDate+" - Fecha Date "+nDate);
			console.log("Created fromDate "+$scope.fromDate);
			console.log("Created toDate "+$scope.toDate);
			
			var toDate = new Date($scope.toDate);
			toDate.setDate(toDate.getDate()+1);
			
			var createdDate = issue.createdDate;
			if(issue.project.id == $scope.selectedProject.id && createdDate >= $scope.fromDate && createdDate < toDate){
				$scope.issuesByProjectAndFilterByCreatedDate.push(issue);
			} 
		});
	}
	
	$http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	
    	$http({method: 'POST', url: $scope.server+"issuesrv/listIssuesByUser",data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	$scope.filterIssueTypes($scope.issues);
		   	
		   	
		   	
		   	var rows = parseChartDataCurrentStatus($scope.issues);
		   	drawChart(rows,'issues_status_div','Mis Issues Por Estado Status');
		   	rows = parseChartDataIssueType($scope.issues);
		   	drawChart(rows,'issues_type_div','Mis Issues Por Tipo de Issue');
		   	
		   	var rows = parseChartDataForIssueByProject($scope.issues);
			drawChart(rows,'cant_issues_div','Issues Por Proyecto');
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });
		 
		  $scope.searchIssuesFromProject();
		
	
		
	}).error(function(data, status, headers, config) {
			  	$scope.noUser = true;
	});
		
	$scope.updateCharts = function(){
			if(typeof $scope.selectedProject != "undefined"){
				$scope.isProjectLoaded = true;
				$scope.showCreatedDateChart = true;
			}
	
			$scope.issuesBySelectedProject = [];
			$scope.issueTypesList = [];
			$scope.issuesForIssueStatusByType = [];
			$scope.issueByProjectAndIssueType = []; 
			$scope.issueByProjectFilter = [];
			$scope.issuesByProjectAndFilterByCreatedDate = [];
			
			//Actualiza issuesBySelectedProject
			$scope.filterIssuesByProject();
			var rows = parseChartDataCurrentStatus($scope.issuesBySelectedProject);
		   	drawChart(rows,'status_div','Issues Por Estado Status');
		   	
		   	rows = parseChartDataIssueType($scope.issuesBySelectedProject);
		   	drawChart(rows,'type_div','Issues Por Tipo de Issue');
		   	
		   	/*
		   	rows = parseChartDataForIssueByProject($scope.issuesBySelectedProject);
			drawChart(rows,'issues_div','Issues');
			*/
		   	
			//issueTypesList
			$scope.filterIssueTypes($scope.issuesBySelectedProject);
			
			//issuesForIssueStatusByType
			$scope.filterIssuesByType($scope.issuesBySelectedProject);
			rows = parseChartDataCurrentStatus($scope.issuesForIssueStatusByType);
		   	drawChart(rows,'issues_type_status_div','Issues Por Estado');
		   	
		   	//issueByProjectFilter
		   	$scope.filterIssuesByProjectFilter($scope.issues);
			rows = parseChartDataCurrentStatus($scope.issueByProjectFilter);
		   	drawChart(rows,'issues_status_div_for_issues_tab_on_selectedProject','Issues Por Estado');
		   	rows = parseChartDataIssueType($scope.issueByProjectFilter);
		   	drawChart(rows,'issues_type_div_for_issues_tab_on_selectedProject','Issues Por Tipo de Issue');
			
			//issueByProjectAndIssueType
			$scope.filterIssuesByIssueTypeAndProject($scope.issues);
			rows = parseChartDataCurrentStatus($scope.issueByProjectAndIssueType);
		   	drawChart(rows,'issues_type_status_div_for_issue','Issues Por Estado');
		   	
		   	
		  
			
	};

		$scope.fillProjectList = function(){
		
			var projectsIds = [];
			angular.forEach($scope.issuesForProject,function(issue,itemNo){
				if(projectsIds.indexOf(issue.project.id) < 0){
					projectsIds.push(issue.project.id);
					$scope.projects.push(issue.project);
				}
			});
		};
		 
		 $scope.filterIssuesByProject = function(){
		 	angular.forEach($scope.issuesForProject,function(issue,itemNo){
		 		if(issue.project.id == $scope.selectedProject.id){
		 			$scope.issuesBySelectedProject.push(issue);
		 		}
		 	});
		 };
		 
	 $scope.showDateRange = function(){
	 	$scope.showDateRange = true;
	 };
	 	
	$scope.createIssueCreatedDateChart = function(){
		$scope.showNoDataForCreatedDateChart = false;
		
		$scope.fromDateEmpty = false;
		$scope.toDateEmpty = false;
		$scope.fromDateAfterToDate = false;
	
		var from = document.getElementById("from").value;
		
		var to = document.getElementById("to").value;
		
		if(typeof from === "undefined" || from == ""){
			$scope.fromDateEmpty = true;
			return;
		}
		if(typeof to === "undefined" || to == ""){
			$scope.toDateEmpty = true;
			return;
		}
		console.log("From antes de parsear"+from);
		
		var fromDate = Date.parse(from);
		console.log("fromDate parseado"+fromDate);
		
		$scope.fromDate = fromDate;
		
		console.log("To antes de parsear"+to);
		var toDate = Date.parse(to);
		console.log("toDate parseado"+toDate);
		$scope.toDate = toDate;
		
		
		if(fromDate > toDate){
			$scope.fromDateAfterToDate = true;
			return;
		}
		
		$scope.issuesByProjectAndFilterByCreatedDate = [];
		$scope.searchIssueTypesByRangeOnCurrentDate($scope.issuesForProject);
	   	rows = parseChartDataIssueTypeWithColor($scope.issuesByProjectAndFilterByCreatedDate);
	   	
	   	if(rows.length > 1){
		   	drawColumnChart(rows,'issues_type_by_create_date','Cantidad de Issues Por Tipo de Issue en un Rango');
			$scope.showCreatedDateChartLoaded = true;
	   	}else{
	   		$scope.showNoDataForCreatedDateChart = true;
	   		$scope.showCreatedDateChartLoaded = false;
	   	}
	   	
	   	$scope.fromDate = from;
		$scope.toDate = to;
		
	};
		 	  
				  
				
		  
		  
		    

}

function parseChartDataCurrentStatus(issues){
	var allData = [];
	var statusList = [];
	var result = [];
	angular.forEach(issues, function(issue, key){
		var status = issue.currentStatus;
		var idx = statusList.indexOf(status.description);
		//IF DOESNT EXIST
		if(idx < 0){
			statusList.push(status.description);
			allData.push({status: status.description, cant: 1});
		}else{
			var statusCount = allData[idx];
			statusCount.cant++;
		}
	});
	
	angular.forEach(allData, function(statusCount, key){
		result.push([statusCount.status,statusCount.cant]);
	});
	return result;
}

function parseChartDataIssueType(issues){
	var allData = [];
	var typeList = [];
	var result = [];
	angular.forEach(issues, function(issue, key){
		var it = issue.issueType;
		var idx = typeList.indexOf(it.description);
		//IF DOESNT EXIST
		if(idx < 0){
			typeList.push(it.description);
			allData.push({issueType: it.description, cant: 1});
		}else{
			var typeCount = allData[idx];
			typeCount.cant++;
		}
	});
	
	angular.forEach(allData, function(typeCount, key){
		result.push([typeCount.issueType,typeCount.cant]);
	});
	return result;
}

function parseChartDataForIssueByProject(issues){
	var allData = [];
	var result = [];
	var projectList = [];
	angular.forEach(issues, function(issue, key){
		var project = issue.project;
		var idx = projectList.indexOf(issue.project.name);
		//IF DOESNT EXIST
		if(idx < 0){
			projectList.push(issue.project.name);
			allData.push({project: issue.project.name, cant: 1});
		}else{
			var issueCount = allData[idx];
			issueCount.cant++;
		}
	});
	
	angular.forEach(allData, function(issueCount, key){
			result.push([issueCount.project,issueCount.cant]);
		});
	return result;

}

/**
[Tipo Issue, Cantidad, Color ]
[1,Bugs, #DDA0DD]  

**/
function parseChartDataIssueTypeWithColor(issues){
	var allData = [];
	var typeList = [];
	var result = [];
	angular.forEach(issues, function(issue, key){
		var it = issue.issueType;
		var idx = typeList.indexOf(it.description);
		//IF DOESNT EXIST
		if(idx < 0){
			typeList.push(it.description);
			allData.push({issueType: it.description, cant: 1});
		}else{
			var typeCount = allData[idx];
			typeCount.cant++;
		}
	});
	
	var colors = ['red','green','blue','orange','lightblue','lightgreen','gray','purple','pink','brown'];
	var colorsCount = 0;
	result.push(['Tipo de Issue', 'Cantidad', { role: 'style' }]);
	angular.forEach(allData, function(typeCount, key){
		if(colorsCount == colors.length){
			colorsCount = 0;
		}
		var color = colors[colorsCount];
		result.push([typeCount.issueType,typeCount.cant,color]);
		colorsCount++;
	});
	return result;
}
