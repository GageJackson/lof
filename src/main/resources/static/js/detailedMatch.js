// function drawChart(chartType, matchId, team1Data, team2Data) {
//     google.charts.load("current", { packages: ["corechart"] });
//     google.charts.setOnLoadCallback(function () {
//         var data = google.visualization.arrayToDataTable([
//             ['Team', chartType],
//             ['Blue Team', team1Data],
//             ['Red Team', team2Data]
//         ]);
//
//         var options = {
//             pieHole: 0.7,
//             backgroundColor: 'transparent',
//             pieSliceBorderColor: 'transparent',
//             legend: 'none',
//             pieSliceText: 'none',
//             tooltip: {
//                 textStyle: {color: '#252525', fontSize: 8},
//                 trigger: 'selection'
//             },
//             colors: ['#4775b4', '#a2434e', '#9c865c', '#4b4b56']
//         };
//
//         var chart = new google.visualization.PieChart(document.getElementById(chartType + 'Chart-' + matchId));
//         chart.draw(data, options);
//     });
// }

// google.charts.load('current', {'packages':['line']});
// google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('number', 'Day');
    data.addColumn('number', 'Guardians of the Galaxy');
    data.addColumn('number', 'The Avengers');
    data.addColumn('number', 'Transformers: Age of Extinction');

    data.addRows([
        [1,  37.8, 80.8, 41.8],
        [2,  30.9, 69.5, 32.4],
        [3,  25.4,   57, 25.7],
        [4,  11.7, 18.8, 10.5],
        [5,  11.9, 17.6, 10.4],
        [6,   8.8, 13.6,  7.7],
        [7,   7.6, 12.3,  9.6],
        [8,  12.3, 29.2, 10.6],
        [9,  16.9, 42.9, 14.8],
        [10, 12.8, 30.9, 11.6],
        [11,  5.3,  7.9,  4.7],
        [12,  6.6,  8.4,  5.2],
        [13,  4.8,  6.3,  3.6],
        [14,  4.2,  6.2,  3.4]
    ]);

    var options = {
        chart: {
            title: 'Box Office Earnings in First Two Weeks of Opening',
            subtitle: 'in millions of dollars (USD)'
        },
        width: 600,
        height: 600
    };

    var chart = new google.charts.Line(document.getElementById('linechart_material'));

    chart.draw(data, google.charts.Line.convertOptions(options));
}

const chartStatContainers = document.getElementsByClassName("chart-stats");
window.onload = async (event) => {
    console.log("page is fully loaded");

    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    let participants = await getData('/participant-stats-chart?match=' + matchId);
    console.log("hello");
    console.log(participants);


    // for (var i = 0; i < chartStatContainers.length; i++) {
    //     var container = chartStatContainers[i];
    //
    //
    // }
};

