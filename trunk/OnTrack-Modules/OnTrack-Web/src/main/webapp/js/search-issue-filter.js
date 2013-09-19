


function SearchIssueFilterCtrl($scope,$http){
	$scope.issues = [];
	
	$scope.server = "http://localhost:8080/OnTrack-SOA/";
	$scope.webserver = "http://localhost:8080/OnTrack/";
    
    
    $scope.ownerFilter = new OwnerFilter();
    $scope.ownerFilter.owner = 1;
    $scope.ownerFilter.init($scope);
    $scope.ownerFilter.searchIssues($http);
	
}

function retrieveIssuesByGet($scope,$http,url){
	$http.get($scope.server+url).success(function(callback){
		$scope.issues = callback;
		$scope.issues = parserResultToDataTable($scope.issues);
		var asInitVals = new Array();
		var oTable = $('#issues').dataTable($scope.issues);
		  
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
			
	});
};

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
		aaSorting: [ [0,'desc'], [1,'asc'] ,[2,'asc']],
		aaData: [],
		aoColumns: [
		            { "sTitle": "ID Issue" },
		            { "sTitle": "Titulo" },
		            { "sTitle": "Acciones" }]
	};
	
	angular.forEach(data, function(value, key){
		var row = [value.id,value.title,"Ver"];
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



