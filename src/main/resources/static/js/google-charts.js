function drawChart(chartType, matchId, team1Data, team2Data) {
    google.charts.load("current", { packages: ["corechart"] });
    google.charts.setOnLoadCallback(function () {
        var data = google.visualization.arrayToDataTable([
            ['Team', chartType],
            ['Blue Team', team1Data],
            ['Red Team', team2Data]
        ]);

        var options = {
            pieHole: 0.7,
            backgroundColor: 'transparent',
            pieSliceBorderColor: 'transparent',
            legend: 'none',
            pieSliceText: 'none',
            tooltip: {
                textStyle: {color: '#252525', fontSize: 8},
                trigger: 'selection'
            },
            colors: ['#4775b4', '#a2434e', '#9c865c', '#4b4b56']
        };

        var chart = new google.visualization.PieChart(document.getElementById(chartType + 'Chart-' + matchId));
        chart.draw(data, options);
    });
}

var chartContainers = document.getElementsByClassName("chart-container");
for (var i = 0; i < chartContainers.length; i++) {
    var container = chartContainers[i];
    var chartType = container.getAttribute("data-chart-type");
    var matchId = container.getAttribute("data-match-id");
    var team1Data = parseInt(container.getAttribute("data-team1"));
    var team2Data = parseInt(container.getAttribute("data-team2"));
    drawChart(chartType, matchId, team1Data, team2Data);
}