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
            '#a2434e',
            '#852b35',
            '#65151e',
            '#4f0810',
            '#330106',
            '#4775B4',
            '#2d5a96',
            '#153d72',
            '#082d5d',
            '#001d42'
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
          <div class="series-tooltip fs-200 d-flex align-items-center gap-2 me-1 px-2 rounded-pill"
               style="background-color:${seriesColor}"
          >
              <span class="series-name text-bolder"> ${w.config.series[i].name}</span>
              <span class="data-point"> ${(w.config.series[i].data[dataPointIndex]).toLocaleString()}</span>
          </div>
        `;
        }
    }

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

    if(isMatchGraph){
        document.getElementById((chartLocation + '-toggleMatch')).addEventListener("click", function() {
            chart.updateOptions(removeData(chart));
            chart.updateOptions(addMatchData(data));
        })

        document.getElementById((chartLocation + '-toggleTeam')).addEventListener("click", function() {
            chart.updateOptions(removeData(chart));
            chart.updateOptions(addTeamData(data));
        })

        document.getElementById((chartLocation + '-toggleIndividual')).addEventListener("click", function() {
            chart.updateOptions(removeData(chart));
            chart.updateOptions(addIndividualData(data));
        })
    }

    chart.render();
}

function removeData(chart){
    return chart.w.globals.series.slice(1, 1);
}

function addIndividualData(data){
    return ({
        series: data,
        colors: [
            '#a2434e',
            '#852b35',
            '#65151e',
            '#4f0810',
            '#330106',
            '#4775B4',
            '#2d5a96',
            '#153d72',
            '#082d5d',
            '#001d42'
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
            '#a2434e',
            '#4775B4'
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
                return '#a2434e'
            } else {
                return '#4775B4'
            }
        }]
    });
}


const participantCharts = document.getElementsByClassName("participant-graphs");



window.onload = async (event) => {
    let participants = await getParticipants();

    drawMatchGraphs(participants);
    drawParticipantGraphs(participants);
};

async function getParticipants(){
    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    return getData('/participant-stats-chart?match=' + matchId);
}

function drawMatchGraphs(participants){
    let matchDamageDealt = getMatchDamageDealt(participants);
    let matchDamageTaken = getMatchDamageTaken(participants);
    let matchGold = getMatchGold(participants);
    let matchCs = getMatchCs(participants);
    let matchXp = getMatchXp(participants);

    drawApexChart('', 'match-DamageDealt', matchDamageDealt, 'line', true);
    drawApexChart('', 'match-DamageTaken', matchDamageTaken, 'line', true);
    drawApexChart('', 'match-Gold', matchGold, 'line', true);
    drawApexChart('', 'match-CS', matchCs, 'line', true);
    drawApexChart('', 'match-Xp', matchXp, 'line', true);
}

function drawParticipantGraphs(participants){
    for (let i = 0; i < participantCharts.length; i++) {
        let participantData = participants[i];

        let damageBothData = getDamageBothData(participantData);
        let damageDealtData = getDamageDealtData(participantData);
        let damageTakenData = getDamageTakenData(participantData);
        let overviewData = getOverviewData(participantData);
        let csData = getCsData(participantData);

        drawApexChart(i, 'chart-DamageBoth', damageBothData, 'line', false);
        drawApexChart(i, 'chart-DamageDealt', damageDealtData, 'area', false);
        drawApexChart(i, 'chart-DamageTaken', damageTakenData, 'area', false);
        drawApexChart(i, 'chart-Overview', overviewData, 'line', false);
        drawApexChart(i, 'chart-CS', csData, 'area', false);
    }
}

function getMatchDamageDealt(participants){
    let finalData = [];

    for (let i = 0; i < participants.length; i++) {
        let participantData = participants[i];
        let graphData = [];
        for (let j = 0; j < participantData.length; j++) {

            graphData.push(participantData[j].participantFrameDamage.totalDamageDoneToChamps);
        }
        finalData.push({name:participantData[0].id, data:graphData, type:'line'})
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
        finalData.push({name:participantData[0].id, data:graphData, type:'line'})
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
        finalData.push({name:participantData[0].id, data:graphData, type:'line'})
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
        finalData.push({name:participantData[0].id, data:graphData, type:'line'})
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
        finalData.push({name:participantData[0].id, data:graphData, type:'line'})
    }

    return finalData;
}


function getDamageBothData(participantData){
    let taken = [];
    let dealt = [];

    for (let i = 0; i < participantData.length; i++) {
        taken.push(participantData[i].participantFrameDamage.totalDamageTaken);
        dealt.push(participantData[i].participantFrameDamage.totalDamageDoneToChamps);
    }

    return [
        {
            name: 'Taken',
            data: taken
        },
        {
            name: 'Dealt',
            data: dealt
        },
    ];
}

function getDamageDealtData(participantData){
    let phyData = [];
    let magData = [];
    let truData = [];

    for (let i = 0; i < participantData.length; i++) {
        phyData.push(participantData[i].participantFrameDamage.physicalDamageDoneToChamps);
        magData.push(participantData[i].participantFrameDamage.magicDamageDoneToChamps);
        truData.push(participantData[i].participantFrameDamage.trueDamageDoneToChamps);
    }

    return [
        {
            name: 'Physical',
            data: phyData
        },
        {
            name: 'Magic',
            data: magData
        },
        {
            name: 'True',
            data: truData
        }
    ];
}

function getDamageTakenData(participantData){
    let phyData = [];
    let magData = [];
    let truData = [];

    for (let i = 0; i < participantData.length; i++) {
        phyData.push(participantData[i].participantFrameDamage.physicalDamageTaken);
        magData.push(participantData[i].participantFrameDamage.magicDamageTaken);
        truData.push(participantData[i].participantFrameDamage.trueDamageTaken);
    }

    return [
        {
            name: 'Physical',
            data: phyData
        },
        {
            name: 'Magic',
            data: magData
        },
        {
            name: 'True',
            data: truData
        }
    ];
}

function getOverviewData(participantData){
    let goldData = [];
    let xpData = [];

    for (let i = 0; i < participantData.length; i++) {
        goldData.push(participantData[i].totalGold);
        xpData.push(participantData[i].xp);
    }

    return [
        {
            name: 'Gold',
            data: goldData
        },
        {
            name: 'Xp',
            data: xpData
        }
    ];
}

function getCsData(participantData){
    let jungleData = [];
    let minionData = [];

    for (let i = 0; i < participantData.length; i++) {
        jungleData.push(participantData[i].jungleMinionsKilled);
        minionData.push(participantData[i].minionsKilled);
    }

    return [
        {
            name: 'Jungle',
            data: jungleData
        },
        {
            name: 'Minion',
            data: minionData
        }
    ];
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function drawDonut(participantNum, chartLocation, data){
    let colors = getColors(data.length);

    let options = {
        series: data,
        colors: colors,
        chart: {
            type: 'donut',
        },
        responsive: [{
            breakpoint: 480,
            options: {
                chart: {
                    width: 200
                },
                legend: {
                    show: false,
                }
            }
        }]
    };

    let chart = new ApexCharts(document.getElementById(chartLocation + participantNum), options);
    chart.render();
}
