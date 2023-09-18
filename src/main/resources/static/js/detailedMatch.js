let matchCharts = [];
let matchDamageDealtPhysicalData = [];
let matchDamageDealtMagicData = [];
let matchDamageDealtTrueData = [];
let matchDamageDealtTotalData = [];

let matchDamageTakenPhysicalData = [];
let matchDamageTakenMagicData = [];
let matchDamageTakenTrueData = [];
let matchDamageTakenTotalData = [];

let matchTotalGoldData = [];
let matchTotalXpData = [];
let matchTotalMinionData = [];
let matchTotalJungleData = [];
let matchTotalCsData = [];

function getYaxisConfig(numberOfDatasets) {
    const yaxisConfig = [];

    if (numberOfDatasets >= 0) {
        yaxisConfig.push(getYAxisConfig('#c5c2bb'));
    }

    return yaxisConfig;
}

function getYAxisConfig(color, opposite = false) {
    return {
        opposite: opposite,
        axisTicks: {
            show: true,
        },
        axisBorder: {
            show: true,
            color: color,
        },
        labels: {
            style: {
                colors: color,
            },
        },
        tooltip: {
            enabled: false,
        },
    };
}

function getColors(numberOfDatasets){
    if (numberOfDatasets === 1) {
        return ['#9C865C'];
    }

    if (numberOfDatasets === 2) {
        return ['#a2434e', '#4775b4'];
    }

    if (numberOfDatasets === 3) {
        return ['#a2434e', '#4775b4', '#9C865C'];
    }

    if (numberOfDatasets === 4) {
        return ['#a2434e', '#4775b4', '#673339', '#314664'];
    }
    if (numberOfDatasets > 4) {
        return [
            '#4775B4',
            '#2d5a96',
            '#153d72',
            '#082d5d',
            '#001d42',
            '#a2434e',
            '#852b35',
            '#65151e',
            '#4f0810',
            '#330106',
        ];
    }
}

function getFillType(chartType) {
    if (chartType === 'line') {
        return {
            type: 'solid',
        }
    }
    if (chartType === 'area') {
        return {
            type: 'gradient',
            gradient: {
                shade: 'dark',
                // type: "vertical",
                shadeIntensity: 0.5,
                inverseColors: true,
                opacityFrom: 1,
                opacityTo: 1,
                // stops: [0, 100],
            }
        }
    }
}

function getChartType(chartType, height, width) {
    const commonOptions = {

        toolbar: {
            show: false,
        },
        zoom: {
            enabled: false,
        },
        dropShadow: {
            enabled: false,
        },
    };

    if (chartType === 'line') {
        return {
            type: 'line',
            ...commonOptions,
        };
    }

    if (chartType === 'area') {
        return {
            type: 'area',
            stacked: true,
            ...commonOptions,
        };
    }
}

function getCustomTooltip({ series, seriesIndex, dataPointIndex, w }) {
    let tooltipContent = `
        <div class="custom-tooltip d-flex bg-transparent color-neutral-light"
             style="box-shadow: none"
        >
    `;

    for (let i = 0; i < w.config.series.length; i++) {
        if (w.config.series[i].data[dataPointIndex]) {
            const seriesColor = w.config.colors[i];
            tooltipContent += `
          <div class="series-tooltip fs-200 d-flex justify-content-end align-items-center gap-2 me-1 px-2 rounded-pill"
               style="background-color:${seriesColor}"
          >
              <span class="data-point"> ${(w.config.series[i].data[dataPointIndex]).toLocaleString()}</span>
          </div>
        `;
        }
    }
    // <span class="series-name text-bolder"> ${w.config.series[i].name}</span>


    tooltipContent += '</div>';
    return tooltipContent;
}

function drawApexChart(participantNum, chartLocation, data, graphType, isMatchGraph){
// Determine the number of datasets you have
    let numberOfDatasets = data.length; // Change this according to your actual number of datasets

// Define the y-axis configuration
    let yaxisConfig = getYaxisConfig(numberOfDatasets);
    let colors = getColors(numberOfDatasets);
    let chartType = getChartType(graphType);
    let fillType = getFillType(graphType);

    let options = {
        series: data,
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
        },
        xaxis: {
            show: false,
            tooltip: {
                enabled: false,
            },
        },
        yaxis: yaxisConfig,
        chart: chartType,
        colors: colors,
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            width: 2,
            type: 'solid',
        },
        fill: fillType,
        legend: {
            show: (!isMatchGraph),
            position: 'top',
            horizontalAlign: 'left',
            offsetX: 30,
            offsetY: 30,
        },
        tooltip: {
            enabled: true,
            enabledOnSeries: undefined,
            shared: true,
            followCursor: false,
            intersect: false,
            inverseOrder: false,
            custom: function({ series, seriesIndex, dataPointIndex, w }) {
                return getCustomTooltip({ series, seriesIndex, dataPointIndex, w });
            },
            fillSeriesColor: undefined,
            theme: undefined,
            onDatasetHover: {
                highlightDataSeries: false,
            },
            x: {
                show: false,
            },
            y: {
                title: {
                    formatter: (seriesName) => seriesName,
                },
            },
            marker: {
                show: false,
            },
            items: {
                display: 'flex',
            },
            fixed: {
                enabled: true,
                position: 'topright',
                offsetX: 0,
                offsetY: 30,
            },
            dropShadow: {
                enabled: false,
            },
        }
    };

    let chart = new ApexCharts(document.getElementById(chartLocation + participantNum), options);

    if(isMatchGraph){
        matchCharts.push(chart);
    }

    chart.render();
}

function setMatchToggleBtns(){
    document.getElementById(('toggleDamageDealt')).addEventListener("click", function() {
        setNewGraphData(matchDamageDealtTotalData, 'Damage Dealt');
        document.getElementById(('toggleDamageDealt')).classList.add('active');
    })

    document.getElementById(('toggleDamageTaken')).addEventListener("click", function() {
        setNewGraphData(matchDamageTakenTotalData, 'Damage Taken');
        document.getElementById(('toggleDamageTaken')).classList.add('active');
    })

    document.getElementById(('toggleXp')).addEventListener("click", function() {
        setNewGraphData(matchTotalXpData, 'XP');
        document.getElementById(('toggleXp')).classList.add('active');
    })

    document.getElementById(('toggleCs')).addEventListener("click", function() {
        setNewGraphData(matchTotalCsData, 'CS');
        document.getElementById(('toggleCs')).classList.add('active');
    })

    document.getElementById(('toggleGold')).addEventListener("click", function() {
        setNewGraphData(matchTotalGoldData, 'Gold');
        document.getElementById(('toggleGold')).classList.add('active');
    })
}

function setNewGraphData (data, dataName){
    let individualGraph = getIndividualAllGraph(data, 'graphName', 'line');
    let teamCompareGraph = getTeamCompareGraph(data, 'graphName', 'line');
    let teamDifferenceGraph = getTeamDifferenceGraph(data, 'graphName', 'area');

    for (let i = 0; i < matchCharts.length; i++) {
        // matchCharts[i].updateSeries(removeData(matchCharts[i]));
        if(i === 0){
            matchCharts[i].updateOptions(updateData([teamDifferenceGraph]));

        } else if(i === 1) {
            matchCharts[i].updateOptions(updateData(teamCompareGraph));

        } else if (i === 2) {
            newChartToggle(matchCharts[i]);
            matchCharts[i].updateOptions(updateData(individualGraph));
            newChartToggle(matchCharts[i]);

        } else {
            newChartToggle(matchCharts[i]);
            matchCharts[i].updateOptions(updateData(individualGraph));
            newChartToggle(matchCharts[i]);
        }
    }

    document.querySelectorAll('.match-graph-toggle').forEach(function(element) {
        element.classList.remove('active');
    });

    document.querySelectorAll('.match-graph-title').forEach(function(element) {
        element.innerText = dataName;
    });
}

function newChartToggle(chart){
    for (const individual of toggledIndividuals) {
        toggleChart(chart, individual);
    }
}


function individualButtons(chart) {
    for (let i = 1; i <= 10; i++) {
        const button = document.getElementById('individual-toggle-' + i);

        button.addEventListener("click", createClickListener(chart, i));
    }
}

let toggledIndividuals = [];

function createClickListener(chart, i) {
    return function() {
        let toggledGraph = 'graphName' + (i - 1);
        toggleIndividuals(toggledGraph);
        toggleChart(chart, toggledGraph);
        this.classList.toggle('unclicked');
    };
}

function toggleIndividuals(toggledGraph){
    if(toggledIndividuals.includes(toggledGraph)){
        let removeIndividual = toggledIndividuals.indexOf(toggledGraph);
        toggledIndividuals.splice(removeIndividual,1)
    } else {
        toggledIndividuals.push(toggledGraph);
    }
}

function toggleChart(chart, toggledGraph){
    chart.toggleSeries(toggledGraph);
}

function removeData(chart){
    return chart.w.globals.series.slice(1, 1);
}

function updateData(data){
    return ({
        series: data,
    });
}

// const participantCharts = document.getElementsByClassName("participant-graphs");

window.onload = async (event) => {
    let participants = await getParticipants();
    await setGraphDataSets(participants);

    await drawMatchGraphs(matchDamageDealtTotalData);
    drawParticipantGraphs(participants);
    setMatchToggleBtns();
    individualButtons(matchCharts[2]);
};

async function getParticipants(){
    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    return getData('/participant-stats-chart?match=' + matchId);
}

function drawMatchGraphs(dataSet){
    let individualGraph = getIndividualAllGraph(dataSet, "graphName", 'line');
    let teamCompareGraph = getTeamCompareGraph(dataSet, "graphName", 'line');
    let teamDifferenceGraph = getTeamDifferenceGraph(dataSet, "graphName", 'area');

    drawApexChart('', 'match-Match', [teamDifferenceGraph], 'line', true);
    drawApexChart('', 'match-Team', teamCompareGraph, 'line', true);
    drawApexChart('', 'match-Individual', individualGraph, 'line', true);
}

function drawParticipantGraphs(participantFramesSet){
    for (let i = 0; i < participantFramesSet.length; i++) {
        let participantFrames = participantFramesSet[i];

        let graphEntryDamageTakenPhysicalData = getGraphEntry('Physical Damage Taken', matchDamageTakenPhysicalData[i], 'area');
        let graphEntryDamageTakenMagicData = getGraphEntry('Magic Damage Taken', matchDamageTakenMagicData[i], 'area');
        let graphEntryDamageTakenTrueData  = getGraphEntry('True Damage Taken', matchDamageTakenTrueData[i], 'area');
        let graphEntryDamageTakenTotalData = getGraphEntry('Total Damage Taken', matchDamageTakenTotalData[i], 'line');

        let graphEntryDamageDealtPhysicalData = getGraphEntry('Physical Damage Dealt', matchDamageDealtPhysicalData[i], 'area');
        let graphEntryDamageDealtMagicData = getGraphEntry('Magic Damage Dealt', matchDamageDealtMagicData[i], 'area');
        let graphEntryDamageDealtTrueData = getGraphEntry('True Damage Dealt', matchDamageDealtTrueData[i], 'area');
        let graphEntryDamageDealtTotalData = getGraphEntry('Total Damage Dealt', matchDamageDealtTotalData[i], 'line');

        let graphEntryTotalGoldData = getGraphEntry('Total Gold', matchTotalGoldData[i], 'line');
        let graphEntryTotalCsData = getGraphEntry('Total CS', matchTotalCsData[i], 'area');
        let graphEntryTotalXpData = getGraphEntry('Total XP', matchTotalXpData[i], 'line');
        let graphEntryTotalJungleData = getGraphEntry('Total Jungle CS', matchTotalJungleData[i], 'area');
        let graphEntryTotalMinionData = getGraphEntry('Total Minion CS', matchTotalMinionData[i], 'area');

        let graphDamageBoth = [graphEntryDamageDealtTotalData, graphEntryDamageTakenTotalData];
        let graphDamageDealt = [graphEntryDamageDealtPhysicalData, graphEntryDamageDealtMagicData, graphEntryDamageDealtTrueData];
        let graphDamageTaken = [graphEntryDamageTakenPhysicalData, graphEntryDamageTakenMagicData, graphEntryDamageTakenTrueData];
        let graphOverview = [graphEntryTotalGoldData, graphEntryTotalXpData];
        let graphCS = [graphEntryTotalMinionData, graphEntryTotalJungleData]

        drawApexChart(i, 'chart-DamageBoth', graphDamageBoth, 'line', false);
        drawApexChart(i, 'chart-DamageDealt', graphDamageDealt, 'area', false);
        drawApexChart(i, 'chart-DamageTaken', graphDamageTaken, 'area', false);
        drawApexChart(i, 'chart-Overview', graphOverview, 'line', false);
        drawApexChart(i, 'chart-CS', graphCS, 'area', false);
    }
}

function setGraphDataSets(participantFramesSet){
    for (let i = 0; i < participantFramesSet.length; i++) {
        let participantFrames = participantFramesSet[i];

        let damageDealtPhysicalData = getGraphDataSet(participantFrames, 'physicalDamageDoneToChamps', 'damage');
        let damageDealtMagicData = getGraphDataSet(participantFrames, 'magicDamageDoneToChamps', 'damage');
        let damageDealtTrueData = getGraphDataSet(participantFrames, 'trueDamageDoneToChamps', 'damage');
        let damageDealtTotalData = getGraphDataSet(participantFrames, 'totalDamageDoneToChamps', 'damage');

        let damageTakenPhysicalData = getGraphDataSet(participantFrames, 'physicalDamageTaken', 'damage');
        let damageTakenMagicData = getGraphDataSet(participantFrames, 'magicDamageTaken', 'damage');
        let damageTakenTrueData = getGraphDataSet(participantFrames, 'trueDamageTaken', 'damage');
        let damageTakenTotalData = getGraphDataSet(participantFrames, 'totalDamageTaken', 'damage');

        let totalGoldData = getGraphDataSet(participantFrames, 'totalGold', 'main');
        let totalXpData = getGraphDataSet(participantFrames, 'xp', 'main');
        let totalMinionData = getGraphDataSet(participantFrames, 'minionsKilled', 'main');
        let totalJungleData = getGraphDataSet(participantFrames, 'jungleMinionsKilled', 'main');
        let totalCsData = addArrays([totalMinionData, totalJungleData]);

        matchDamageDealtPhysicalData.push(damageDealtPhysicalData);
        matchDamageDealtMagicData.push(damageDealtMagicData);
        matchDamageDealtTrueData.push(damageDealtTrueData);
        matchDamageDealtTotalData.push(damageDealtTotalData);

        matchDamageTakenPhysicalData.push(damageTakenPhysicalData);
        matchDamageTakenMagicData.push(damageTakenMagicData);
        matchDamageTakenTrueData.push(damageTakenTrueData);
        matchDamageTakenTotalData.push(damageTakenTotalData);

        matchTotalGoldData.push(totalGoldData);
        matchTotalXpData.push(totalXpData);
        matchTotalMinionData.push(totalMinionData);
        matchTotalJungleData.push(totalJungleData);
        matchTotalCsData.push(totalCsData);
    }
}

function getGraphDataPoint(participantFrame, dataName, dataType){
    if (dataType === 'damage'){
        return participantFrame.participantFrameDamage[dataName];
    } else if (dataType === 'champ'){
        return participantFrame.participantFrameChamp[dataName];
    } else {
        return participantFrame[dataName];
    }
}

function getGraphDataSet(participantFrames, dataName, dataType){
    let dataSet =[]
    for (const participantFrame of participantFrames) {
        let dataPoint = getGraphDataPoint(participantFrame, dataName, dataType)
        dataSet.push(dataPoint)
    }
    return dataSet;
}

function getGraphEntry(graphName, graphData, graphType){
    return{name:graphName, data:graphData, type:graphType};
}

function getIndividualOneGraph(participantFrames, graphData, graphName, graphType){
    // const graphData = getGraphDataSet (participantFrames, dataName, dataType);
    return getGraphEntry(graphName, graphData, graphType);
}

function getIndividualAllGraph(dataSet, graphName, graphType){
    let individualAllGraph = [];
    for (let i = 0; i < dataSet.length; i++) {
        const graph = getGraphEntry((graphName + i), dataSet[i], graphType);
        individualAllGraph.push(graph)
    }
    return individualAllGraph;
}

function getTeamCompareGraph(dataSet, graphName, graphType){
    let teamCompareGraph = [];
    let teamsData = getTeamsData(dataSet);

    let blueTeamData = addArrays(teamsData[0]);
    let redTeamData = addArrays(teamsData[1]);

    let blueTeamGraph = getGraphEntry((graphName + 'blue'), blueTeamData, graphType);
    let redTeamGraph = getGraphEntry((graphName + 'red'), redTeamData, graphType);

    teamCompareGraph.push(blueTeamGraph);
    teamCompareGraph.push(redTeamGraph);

    return teamCompareGraph;
}

function getTeamsData(dataSet){
    let teamsData = [];
    const playerCount = dataSet.length;
    const teamSize =  playerCount / 2;
    let blueTeam = [];
    let redTeam = [];

    for (let i = 0; i < playerCount; i++) {
        let individualDataSet = dataSet[i];
        if (i < teamSize){
            blueTeam.push(individualDataSet);
        } else {
            redTeam.push(individualDataSet);
        }
    }

    teamsData.push(blueTeam);
    teamsData.push(redTeam);
    return teamsData;
}

function addArrays(arrays){
    const result = [];

    for (let i = 0; i < arrays.length; i++) {
        for (let j = 0; j < arrays[0].length; j++) {
            if (i === 0) {
                result.push(arrays[i][j]);
            } else {
                result[j] += arrays[i][j];
            }
        }
    }

    return result;
}

function subtractArrays(arrays){
    const result = [];

    for (let i = 0; i < arrays.length; i++) {
        for (let j = 0; j < arrays[0].length; j++) {
            if (i === 0) {
                result.push(arrays[i][j]);
            } else {
                result[j] -= arrays[i][j];
            }
        }
    }

    return result;
}

function getTeamDifferenceGraph(dataSet, graphName, graphType){
    let teamsData = getTeamsData(dataSet);

    let blueTeamData = addArrays(teamsData[0]);
    let redTeamData = addArrays(teamsData[1]);

    let teamDiffData = subtractArrays([blueTeamData, redTeamData])

    return getGraphEntry(graphName, teamDiffData, graphType);
}

/*

<div class="stat-block" data-info-red="300000" data-info-blue="150000">
		<div class="stat-info">
			<p class="number">
				<span class="blue">300,000</span>
			</p>
			<p class="title">Damage Dealt</p>
      <p class="number">
				<span class="red">15,000</span>
			</p>
		</div>
		<svg class="stat-ring">
      <circle class="back-ring"/>
			<circle class="front-ring"/>
		</svg>
</div>


@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;900&display=swap');
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Roboto', sans-serif;
	font-weight: 400;
	font-size: 20px;
	color: #333;
	list-style-type: none;
	text-decoration: none;
}

body {
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
	padding: 20px;
	background-color: #292929;
}

.stat-block {
	position: relative;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 200px;
	height: 200px;
}

.stat-info {
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	width: calc(100% - 80px);
	height: calc(100% - 80px);
	border-radius: 50%;
	background-color: #292929;
	box-shadow: 0 0 5px 3px #222121;
}

.blue {
	font-size: 12px;
	font-weight: bold;
  color: blue;
}

.red {
	font-size: 12px;
	font-weight: bold;
  color: red;
}


.title {
	font-size: 16px;
	color: #9b9b9b;
}

.stat-ring {
	position: absolute;
	width: 100%;
	height: 100%;
  display: flex;
	align-items: center;
	justify-content: center;
	fill: none;
	transform: rotate(-90deg);

}

.front-ring {
	stroke: blue;
	stroke-width: .75rem;
}

.back-ring {
	stroke: red;
	stroke-width: .6825rem;
}


window.addEventListener('load', function(){
  const statBlock = document.querySelectorAll('.stat-block');
  statBlock.forEach(item => {
    let redTeam = item.getAttribute('data-info-red');
    let blueTeam = item.getAttribute('data-info-blue');

    let redNum = parseInt(redTeam);
    let blueNum = parseInt(blueTeam);
    let matchTotal = redNum + blueNum;

    let frontRing = item.querySelector('.front-ring');
    let backRing = item.querySelector('.back-ring');
    let radius = 80;
    let diameter = Math.round(3.14 * 2 * radius);
    let strokeOffset = diameter - ( diameter * ( blueNum / matchTotal ))

    frontRing.style.strokeDasharray = diameter;
    frontRing.style.strokeDashoffset = strokeOffset;
    frontRing.style.strokeWidth = 12;
    frontRing.setAttribute("cx", 100);
    frontRing.setAttribute("cy", 100);
    frontRing.setAttribute("r", radius);

    backRing.style.strokeWidth = 10;
    backRing.setAttribute("cx", 100);
    backRing.setAttribute("cy", 100);
    backRing.setAttribute("r", radius);
  })
});

 */