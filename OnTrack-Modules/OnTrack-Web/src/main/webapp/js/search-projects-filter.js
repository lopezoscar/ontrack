function SearchProjectsController($scope,$http){
	$scope.projects = [];
	
	$scope.server = "http://localhost:8080/OnTrack-SOA/";
	$scope.webserver = "http://localhost:8080/OnTrack/";
    
	$http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	$http({method: 'POST', url: $scope.server+'projectsrv/projectsbyuser',data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.projects = data;
		   	var rows = parserResultToDataTable($scope.projects);
		   	createDatatablesForProjects(rows);
		  }).
		  error(function(data, status, headers, config) {
		  	$scope.noProjects = true;
		  });
	   	 
	   	
	  }).
	  error(function(data, status, headers, config) {
	  	$scope.noUser = true;
	  });
	  
   
    
    	
}

function createDatatablesForProjects(projects,$location){
		var asInitVals = new Array();
		var oTable = $('#projects').dataTable(projects);
		  
	    $("tfoot input").keyup( function () {
	        /* Filter on the column (the index) of this element */
	        oTable.fnFilter( this.value, $("tfoot input").index(this) );
	    } );
	     
	    /*
	     * Support functions to provide a little bit of 'user friendlyness' to the textboxes in
	     * the footer
	     */
	    $("tfoot input").each( function (i) {
	        asInitVals[i] = this.value;
	    } );
	     
	    $("tfoot input").focus( function () {
	        if ( this.className == "search_init" )
	        {
	            this.className = "";
	            this.value = "";
	        }
	    } );
	     
	    $("tfoot input").blur( function (i) {
	        if ( this.value == "" )
	        {
	            this.className = "search_init";
	            this.value = asInitVals[$("tfoot input").index(this)];
	        }
	    } );
	    
	    
	    $('#projects tbody tr').on('mouseover', function (event) { 
	    	 var aData = oTable.fnGetData(this); // get datarow
		        if (null != aData){
		        }
	    });
	    
	    $('#projects tbody tr').on('click', function (event) {        
	        var aData = oTable.fnGetData(this); // get datarow
	        if (null != aData)  // null if we clicked on title row
	        {
	        	window.location = "http://localhost:8080/OnTrack/create-project.html?project="+aData[0];
	        };
	    });
}


function parserResultToDataTable(data){
	var source = {
		sDom: 'T<"clear">lfrtip',
		bJQueryUI : true,
		aaSorting: [ [0,'desc']],
		aaData: [],
		aoColumns: [
		            { "sTitle": "ID Proyecto" },
		            { "sTitle": "Nombre" },
		            { "sTitle": "Administrador" }
		            ]
	};
	
	function validateNullAndUndenfinded(param){
		return param == null || param == "undefined";
	}
	
	angular.forEach(data, function(project, key){
		var projectID =  validateNullAndUndenfinded(project.id) ? "INDEFINIDO":project.id;
		var name =   validateNullAndUndenfinded(project.name) ? "INDEFINIDO":project.name;
		var admin = validateNullAndUndenfinded(project.admin) ? "INDEFINIDO": project.admin.userName;
		
		
		var row = [projectID,
				   name,
				   admin
				   ];
		source.aaData.push(row);
	});
	
	return source;
	
	
}

