function SearchIssueFilterCtrl($scope,$http,$location){
	$scope.issues = [];
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    
	$scope.lookupService = "issuesrv/listIssuesByUser";
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	$http({method: 'POST', url: $scope.server+$scope.lookupService,data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	$scope.issues = parserResultToDataTableForIssues($scope.issues);
			createDatatables($scope.issues,$location,"issues");
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
	   	
	   	
	  }).error(function(data, status, headers, config) {
		  	$scope.noUser = true;
	  });;
    
    
}

function SearchIssueFilterForProjectCtrl($scope,$http,$location){
	$scope.issues = [];
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    
	$scope.lookupService = "issuesrv/listIssuesByUserFromProject";
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	$http({method: 'POST', url: $scope.server+$scope.lookupService,data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	$scope.issues = parserResultToDataTableForIssues($scope.issues);
			createDatatables($scope.issues,$location,"issuesProject");
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
	   	
	   	
	  }).error(function(data, status, headers, config) {
		  	$scope.noUser = true;
	  });;
    
    
}

function createDatatables(issues,$location,tableId){
		var asInitVals = new Array();
		var oTable = $('#'+tableId).dataTable(issues);
		  
	    $("#"+tableId+' tfoot input').keyup( function () {
	        /* Filter on the column (the index) of this element */
	        oTable.fnFilter( this.value, $("#"+tableId+' tfoot input').index(this) );
	    } );
	     
	    /*
	     * Support functions to provide a little bit of 'user friendlyness' to the textboxes in
	     * the footer
	     */
	    $("#"+tableId+' tfoot input').each( function (i) {
	        asInitVals[i] = this.value;
	    } );
	     
	    $("#"+tableId+' tfoot input').focus( function () {
	        if ( this.className == "search_init" )
	        {
	            this.className = "";
	            this.value = "";
	        }
	    } );
	     
	    $("#"+tableId+' tfoot input').blur( function (i) {
	        if ( this.value == "" )
	        {
	            this.className = "search_init";
	            this.value = asInitVals[$("tfoot input").index(this)];
	        }
	    } );
	    
	    
	    $("#"+tableId+' tbody tr').on('mouseover', function (event) { 
	    	 var aData = oTable.fnGetData(this); // get datarow
		        if (null != aData){
		        }
	    });
	    
	    $("#"+tableId+' tbody tr').on('click', function (event) {        
	        var aData = oTable.fnGetData(this); // get datarow
	        if (null != aData)  // null if we clicked on title row
	        {
	        	var server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	        	window.location = server+"create-issue.html?issue="+aData[0];
	        };
	    });
	    
	    $("#"+tableId).bind( 'draw', function(){
	    	  $("#"+tableId+' tbody tr').on('click', function (event) {        
			        var aData = oTable.fnGetData(this); // get datarow
			        if (null != aData)  // null if we clicked on title row
			        {
			        	var server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
			        	window.location = server+"create-issue.html?issue="+aData[0];
			        };
			    });
	        
	    } );
}

function retrieveIssuesByGet($scope,$http,url){
	$http.get($scope.server+url).success(function(callback){
		$scope.issues = callback;
	});
}

function retrieveIssuesByPOST($scope,$http,url,data){
	$http.post($scope.server+url).data(data).success(function(callback){
		$scope.issues = callback;
		$scope.issues = parserResultToDataTableForIssues($scope.issues);
		$('#issues').dataTable($scope.issues);
	});
};



function parserResultToDataTableForIssues(data){
	var source = {
		oLanguage: {
		    "sProcessing":     "Procesando...",
		    "sLengthMenu":     "Mostrar _MENU_ registros",
		    "sZeroRecords":    "No se encontraron resultados",
		    "sEmptyTable":     "Ningún dato disponible en esta tabla",
		    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
		    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
		    "sInfoPostFix":    "",
		    "sSearch":         "Buscar:",
		    "sUrl":            "",
		    "sInfoThousands":  ",",
		    "sLoadingRecords": "Cargando...",
		    "oPaginate": {
		        "sFirst":    "<<",
		        "sLast":     ">>",
		        "sNext":     ">",
		        "sPrevious": "<"
		    },
		    "oAria": {
		        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
		        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
		    }
		},
		sDom: 'T<"clear">lfrtip',
		bJQueryUI : true,
		oTableTools: {
			"sSwfPath": "datatables/extras/TableTools/media/swf/copy_csv_xls_pdf.swf",
			"aButtons": [
				"copy",
				"csv",
				"xls",
				{
					"sExtends": "pdf",
					"sPdfOrientation": "landscape",
					"sPdfMessage": "Issues"
				},
				"print"
			]
		},
		aaSorting: [ [0,'desc']],
		aaData: [],
		aoColumns: [
		            { "sTitle": "ID Issue" },
		            { "sTitle": "Titulo" },
		            { "sTitle": "Reporter" },
		            { "sTitle": "Owner" },
		            { "sTitle": "Estado Actual" },
		            { "sTitle": "Tipo de Issue" },
		            { "sTitle": "Proyecto" },
		            
		            ]
	};
	
	function validateNullAndUndenfinded(param){
		return param == null || param == "undefined";
	}
	
	angular.forEach(data, function(issue, key){
		var issueId =  validateNullAndUndenfinded(issue.id) ? "INDEFINIDO":issue.id;
		var title =   validateNullAndUndenfinded(issue.title) ? "INDEFINIDO":issue.title;
		var reporter = validateNullAndUndenfinded(issue.reporter) ? "INDEFINIDO":issue.reporter;
		var owner = validateNullAndUndenfinded(issue.owner.lastName) ? "INDEFINIDO": issue.owner.lastName+", "+issue.owner.firstName;
		var currentStatus = validateNullAndUndenfinded(issue.currentStatus.description) ? "INDEFINIDO":issue.currentStatus.description; 
		var issueType = validateNullAndUndenfinded(issue.issueType.description)? "INDEFINIDO":issue.issueType.description;
		var project = validateNullAndUndenfinded(issue.project)? "INDEFINIDO":issue.project.name;
		var description = validateNullAndUndenfinded(issue.description) ? "INDEFINIDO":issue.description;  
		
		
		
		var row = [issueId,
				   title,
				   reporter,
				   owner, 
				   currentStatus,
				   issueType,
				   project
				   ];
		//console.log(row);
		source.aaData.push(row);
	});
	
	return source;
	
	
}

