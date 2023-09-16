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

    // if(isMatchGraph){
    //     document.getElementById((chartLocation + '-toggleMatch')).addEventListener("click", function() {
    //         chart.updateOptions(removeData(chart));
    //         chart.updateOptions(addMatchData(data));
    //     })
    //
    //     document.getElementById((chartLocation + '-toggleTeam')).addEventListener("click", function() {
    //         chart.updateOptions(removeData(chart));
    //         chart.updateOptions(addTeamData(data));
    //     })
    //
    //     document.getElementById((chartLocation + '-toggleIndividual')).addEventListener("click", function() {
    //         chart.updateOptions(removeData(chart));
    //         chart.updateOptions(addIndividualData(data));
    //     })
    //
    //     for (let i = 1; i <= 10 ; i++) {
    //         console.log('participantToggle-' + i);
    //         document.getElementById(('participantToggle-' + i)).addEventListener("click", function() {
    //             chart.toggleSeries(i + '');
    //         })
    //     }
    // }

    chart.render();
}

function removeData(chart){
    return chart.w.globals.series.slice(1, 1);
}

function addIndividualData(data){
    return ({
        series: data,
        colors: [
            '#6d92c5',
            '#4068a0',
            '#2f4c75',
            '#1d2f49',
            '#111c2c',
            '#c36f79',
            '#9e424d',
            '#733038',
            '#481e23',
            '#2b1215',
        ]
    });
}

function addTeamData(data){
    let finalData = [];
    let blueTeamData = [];
    let redTeamData = [];
    for (let i = 0; i < data.length; i++) {
        if (i < 5){
            for (let j = 0; j < data[i].data.length; j++) {
                if (i === 0){
                    blueTeamData.push(data[i].data[j])
                } else {
                    blueTeamData[j] += data[i].data[j];
                }
            }
        } else {
            for (let j = 0; j < data[i].data.length; j++) {
                if (i === 5){
                    redTeamData.push(data[i].data[j])
                } else {
                    redTeamData[j] += data[i].data[j];
                }
            }
        }
    }
    finalData.push({name:'blue team', data:blueTeamData, type:'bar'});
    finalData.push({name:'red team', data:redTeamData, type:'bar'});
    return ({
        series: finalData,
        colors: [
            '#4775B4',
            '#a2434e',

        ]
    });
}

function addMatchData(data){
    let finalData = [];
    let teamDiffData = []
    for (let i = 0; i < data.length; i++) {
        if (i < 5){
            for (let j = 0; j < data[i].data.length; j++) {
                if (i === 0){
                    teamDiffData.push(data[i].data[j])
                } else {
                    teamDiffData[j] += data[i].data[j];
                }
            }
        } else {
            for (let j = 0; j < data[i].data.length; j++) {
                teamDiffData[j] -= data[i].data[j];
            }
        }
    }
    finalData.push({name:'team diff', data:teamDiffData, type:'bar'});
    return ({
        series: finalData,
        colors: [function({ value, seriesIndex, w }) {
            if (value < 0) {
                return '#4775B4'
            } else {
                return '#a2434e'
            }
        }]
    });
}


const participantCharts = document.getElementsByClassName("participant-graphs");



window.onload = async (event) => {
    let participants = await getParticipants();
    await setGraphDataSets(participants);

    drawMatchGraphs(matchTotalCsData);
    drawParticipantGraphs(participants);
};

async function getParticipants(){
    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    return getData('/participant-stats-chart?match=' + matchId);
}
//these need to be hard refactored and condensed
// function drawMatchGraphs(participants){
//     let matchDamageDealt = getMatchDamageDealt(participants);
//     let matchDamageTaken = getMatchDamageTaken(participants);
//     let matchGold = getMatchGold(participants);
//     let matchCs = getMatchCs(participants);
//     let matchXp = getMatchXp(participants);
//
//     drawApexChart('', 'match-DamageDealt', matchDamageDealt, 'line', true);
//     drawApexChart('', 'match-DamageTaken', matchDamageTaken, 'line', true);
//     drawApexChart('', 'match-Gold', matchGold, 'line', true);
//     drawApexChart('', 'match-CS', matchCs, 'line', true);
//     drawApexChart('', 'match-Xp', matchXp, 'line', true);
// }

function drawMatchGraphs(dataSet){
    let individualGraph = getIndividualAllGraph(dataSet, "graphName", 'line');
    let teamCompareGraph = getTeamCompareGraph(dataSet, "graphName", 'line');
    let teamDifferenceGraph = getTeamDifferenceGraph(dataSet, "graphName", 'line');

    console.log("teamCompareGraph");
    console.log(teamCompareGraph);

    console.log("teamDifferenceGraph");
    console.log(teamDifferenceGraph);

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

    console.log('team compare graph')
    console.log(teamsData)

    let blueTeamData = addArrays(teamsData[0]);
    let redTeamData = addArrays(teamsData[1]);

    console.log(blueTeamData)
    console.log(redTeamData)

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
    // console.log(arrays);
    for (let i = 0; i < arrays.length; i++) {
        for (let j = 0; j < arrays[0].length; j++) {
            if (i === 0) {
                result.push(arrays[i][j]);
            } else {
                result[j] += arrays[i][j];
            }
        }
    }
    // console.log(result);
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

    console.log('team diff graph')
    console.log(teamsData)

    let blueTeamData = addArrays(teamsData[0]);
    let redTeamData = addArrays(teamsData[1]);

    console.log(blueTeamData)
    console.log(redTeamData)

    let teamDiffData = subtractArrays([blueTeamData, redTeamData])
    console.log(teamDiffData)

    return getGraphEntry(graphName, teamDiffData, graphType);
}

function getMatchDamageDealt(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {

            graphData.push(participantData[j].participantFrameDamage.totalDamageDoneToChamps);
        }
        finalData.push({name:i + 1, data:graphData, type:'line'})
    }
    return finalData;
}

function getMatchDamageTaken(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {
            graphData.push(participantData[j].participantFrameDamage.totalDamageTaken);
        }
        finalData.push({name:i + 1, data:graphData, type:'line'})
    }

    return finalData;
}

function getMatchGold(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {
            graphData.push(participantData[j].totalGold);
        }
        finalData.push({name:i + 1, data:graphData, type:'line'})
    }

    return finalData;
}

function getMatchCs(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {
            graphData.push(participantData[j].jungleMinionsKilled + participantData[j].minionsKilled);
        }
        finalData.push({name:i + 1, data:graphData, type:'line'})
    }

    return finalData;
}

function getMatchXp(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {
            graphData.push(participantData[j].xp);
        }
        finalData.push({name:i + 1, data:graphData, type:'line'})
    }

    return finalData;
}
