// 	Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
//google.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart(rows,domLocation,title) {

	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows(rows);
/*
 * [
	              ['Bugs', 3],
	              ['Issues', 1],
	              ['Releases', 1],
	              ['User Stories', 1],
	              ['Requirement', 2]
	              ]
 * */
	// Set chart options
	var options = {'title':title,
			'width':500,
			'height':375};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById(domLocation));
	chart.draw(data, options);
}

function drawColumnChart(rows,domLocation,title){

	var data = google.visualization.arrayToDataTable(rows);
	//data.addRows(rows);
	// Set chart options
	var options = {'title':title,
			'width':500,
			'height':375};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.ColumnChart(document.getElementById(domLocation));
	chart.draw(data, options);
}