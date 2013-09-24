


function SearchIssueFilterCtrl($scope,$http){
	$scope.issues = [];
	
	$scope.server = "http://localhost:8080/OnTrack-SOA/";
	$scope.webserver = "http://localhost:8080/OnTrack/";
    
    var user = {
    	id: 1
    };
    
    $http({method: 'POST', url: $scope.server+"issuesrv/listIssuesByUser",data:user,headers: {'Content-Type': 'application/json'}}).
		  success(function(data, status, headers, config) {
		   	$scope.issues = data;
		   	$scope.issues = parserResultToDataTable($scope.issues);
			createDatatables($scope.issues);
		
		
		
		})
		.error(function(data, status, headers, config) {
		  	$scope.noIssues = true;
		  });;
		  
 	
}

function createDatatables(issues){
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
}

function retrieveIssuesByGet($scope,$http,url){
	$http.get($scope.server+url).success(function(callback){
		$scope.issues = callback;
	});
}

function retrieveIssuesByPOST($scope,$http,url,data){
	$http.post($scope.server+url).data(data).success(function(callback){
		$scope.issues = callback;
		$scope.issues = parserResultToDataTable($scope.issues);
		$('#issues').dataTable($scope.issues);
	});
};


var data = {
		"aaData": [
           [ "Trident", "Internet Explorer 4.0", "Win 95+"],
           [ "Trident", "Internet Explorer 5.0", "Win 95+"],
           [ "Webkit", "Safari 3.0", "OSX.4+"]
       ],
       "aoColumns": [
           { "sTitle": "ID Issue" },
           { "sTitle": "Titulo" },
           { "sTitle": "Accciones" }
       ]
};

function parserResultToDataTable(data){
	var source = {
		"sDom": 'T<"clear">lfrtip',
		"oTableTools": {
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
		aaSorting: [ [0,'desc'], [1,'asc'] ,[2,'asc']],
		aaData: [],
		aoColumns: [
		            { "sTitle": "ID Issue" },
		            { "sTitle": "Titulo" },
//		            { "sTitle": "Reporter" },
//		            { "sTitle": "Owner" },
//		            { "sTitle": "Estado Actual" },
//		            { "sTitle": "Tipo de Issue" },
//		            { "sTitle": "Proyecto" },
//		            { "sTitle": "Descripcion" },
		            { "sTitle": "Acciones" }
		            ]
	};
	
	function validateNullAndUndenfinded(param){
		return param == null || param == "undefined";
	}
	
	angular.forEach(data, function(issue, key){
		var issueId =  validateNullAndUndenfinded(issue.id) ? "":issue.id;
		var title =   validateNullAndUndenfinded(issue.title) ? "":issue.title;
		var reporter = validateNullAndUndenfinded(issue.reporter) ? "":issue.reporter;
		var owner = validateNullAndUndenfinded(issue.owner.lastName+", "+issue.owner.firstName)? "": issue.owner.lastName+", "+issue.owner.firstName;
		var currentStatus = validateNullAndUndenfinded(issue.currentStatus.description) ? "":issue.currentStatus.description; 
		var issueType = validateNullAndUndenfinded(issue.issueType.descrption)? "":issue.issueType.descrption;
		var project = validateNullAndUndenfinded(issue.project)? "":issue.project;
		var description = validateNullAndUndenfinded(issue.description) ? "":issue.description;  
		
		
		
		var row = [issueId,
				   title,
//				   owner, 
//				   currentStatus,
//				   issueType,
//				   project,
//				   description,
				    "Ver"];
		console.log(row);
		source.aaData.push(row);
	});
	
	return source;
	
	
}

function OwnerFilter(){
	this.scope = {};

	this.name="Owner";
	this.owner ="";
	this.url = "issuesrv/getissuesbyownerid";
	this.init = function(scope){
		this.scope = scope;
	};
	
	this.isOwnerFilter = true;
	
	this.searchIssues = function($http){
		var params = [];
		params.push(this.owner);
		retrieveIssuesByGet(this.scope,$http,buildURL(this.url,params));
	};
	
	function buildURL(url,params){
		var result = url;
		angular.forEach(params, function(value, key){
			result = result+"/"+value;
		});
		return result;
	}
}



