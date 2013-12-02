function SearchIssueFilterCtrl($scope,$http,$location){
	$scope.issues = [];
	
	$scope.server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack-SOA/";
	$scope.webserver = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
    
  
    
    $http({method: 'GET', url: $scope.webserver+'currentUser',headers: {'Content-Type': 'application/json'}}).
	  success(function(data, status, headers, config) {
	   	$scope.currentUser = data;
	   	
	   	$http({method: 'POST', url: $scope.server+"issuesrv/listIssuesByUser",data:$scope.currentUser,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	$scope.issues = parserResultToDataTableForIssues($scope.issues);
			createDatatables($scope.issues,$location);
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
	   	
	   	
	  }).error(function(data, status, headers, config) {
		  	$scope.noUser = true;
	  });;
    
    
}

function createDatatables(issues,$location){
		var asInitVals = new Array();
		var oTable = $('#issues').dataTable(issues);
		  
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
	    
	    
	    $('#issues tbody tr').on('mouseover', function (event) { 
	    	 var aData = oTable.fnGetData(this); // get datarow
		        if (null != aData){
		        }
	    });
	    
	    $('#issues tbody tr').on('click', function (event) {        
	        var aData = oTable.fnGetData(this); // get datarow
	        if (null != aData)  // null if we clicked on title row
	        {
	        	var server = $location.$$protocol+"://"+$location.$$host+":"+$location.$$port+"/OnTrack/";
	        	window.location = server+"create-issue.html?issue="+aData[0];
	        };
	    });
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
		sDom: 'T<"clear">lfrtip',
		bJQueryUI : true,
		sPaginationType: "full_numbers",
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
		var owner = validateNullAndUndenfinded(issue.owner.lastName+", "+issue.owner.firstName)? "INDEFINIDO": issue.owner.lastName+", "+issue.owner.firstName;
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
		console.log(row);
		source.aaData.push(row);
	});
	
	return source;
	
	
}

