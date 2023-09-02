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

function drawDamageDoneChart(participant, participantNum){
    let phyDam = [];
    let magDam = [];
    let truDam = [];

    for (let i = 0; i < participant.length; i++) {
        phyDam.push(participant[i].participantFrameDamage.physicalDamageDone);
        magDam.push(participant[i].participantFrameDamage.magicDamageDone);
        truDam.push(participant[i].participantFrameDamage.trueDamageDone);
    }

    let options = {
        series: [
            {
                name: 'Physical',
                data: phyDam
            },
            {
                name: 'Magic',
                data: magDam
            },
            {
                name: 'True',
                data: truDam
            }
        ],
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
            xaxis: {
                lines: {
                    show: false
                }
            },
            yaxis: {
                lines: {
                    show: true
                }
            },
            // row: {
            //     colors: undefined,
            //     opacity: 0.5
            // },
            // column: {
            //     colors: undefined,
            //     opacity: 0.5
            // },
            // padding: {
            //     top: 0,
            //     right: 0,
            //     bottom: 0,
            //     left: 0
            // },
        },
        chart: {
            type: 'area',
            height: 350,
            stacked: true,
            events: {
                selection: function (chart, e) {
                    console.log(new Date(e.xaxis.min))
                }
            },
            toolbar: {
                show: false,
            },
            zoom: {
                enabled: false,
            },
        },
        colors: ['#673339', '#314664', '#ababa9'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            colors: ['#a2434e', '#4775b4', '#f6f1e5'],
            width: 3,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center'
        },
        // xaxis: {
        //     type: 'num'
        // },
    };

    let chart = new ApexCharts(document.getElementById('stats-damageDone-chart-' + participantNum), options);
    chart.render();
}

function drawDamageTakenChart(participant, participantNum){
    let phyDam = [];
    let magDam = [];
    let truDam = [];

    for (let i = 0; i < participant.length; i++) {
        phyDam.push(participant[i].participantFrameDamage.physicalDamageTaken);
        magDam.push(participant[i].participantFrameDamage.magicDamageTaken);
        truDam.push(participant[i].participantFrameDamage.trueDamageTaken);
    }

    let options = {
        series: [
            {
                name: 'Physical',
                data: phyDam
            },
            {
                name: 'Magic',
                data: magDam
            },
            {
                name: 'True',
                data: truDam
            }
        ],
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
            xaxis: {
                lines: {
                    show: false
                }
            },
            yaxis: {
                lines: {
                    show: true
                }
            },
        },
        chart: {
            type: 'area',
            height: 350,
            stacked: true,
            events: {
                selection: function (chart, e) {
                    console.log(new Date(e.xaxis.min))
                }
            },
            toolbar: {
                show: false,
            },
            zoom: {
                enabled: false,
            },
        },
        colors: ['#673339', '#314664', '#ababa9'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            colors: ['#a2434e', '#4775b4', '#f6f1e5'],
            width: 3,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center'
        },
    };

    let chart = new ApexCharts(document.getElementById('stats-damageTaken-chart-' + participantNum), options);
    chart.render();
}

function drawGoldChart(participant, participantNum){
    let gold = [];

    for (let i = 0; i < participant.length; i++) {
        gold.push(participant[i].totalGold);
    }

    let options = {
        series: [
            {
                name: 'Gold',
                data: gold
            }
        ],
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
            xaxis: {
                lines: {
                    show: false
                }
            },
            yaxis: {
                lines: {
                    show: true
                }
            },
        },
        chart: {
            type: 'area',
            height: 350,
            stacked: true,
            events: {
                selection: function (chart, e) {
                    console.log(new Date(e.xaxis.min))
                }
            },
            toolbar: {
                show: false,
            },
            zoom: {
                enabled: false,
            },
        },
        colors: ['#5e5543'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            colors: ['#9C865CFF'],
            width: 3,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center'
        },
    };

    let chart = new ApexCharts(document.getElementById('stats-gold-chart-' + participantNum), options);
    chart.render();
}

function drawCsChart(participant, participantNum){
    let cs = [];

    for (let i = 0; i < participant.length; i++) {
        cs.push(participant[i].jungleMinionsKilled + participant[i].minionsKilled);
    }

    let options = {
        series: [
            {
                name: 'CS',
                data: cs
            }
        ],
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
            xaxis: {
                lines: {
                    show: false
                }
            },
            yaxis: {
                lines: {
                    show: true
                }
            },
        },
        chart: {
            type: 'area',
            height: 350,
            stacked: true,
            events: {
                selection: function (chart, e) {
                    console.log(new Date(e.xaxis.min))
                }
            },
            toolbar: {
                show: false,
            },
            zoom: {
                enabled: false,
            },
        },
        colors: ['#5e5543'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            colors: ['#9C865CFF'],
            width: 3,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center'
        },
    };

    let chart = new ApexCharts(document.getElementById('stats-cs-chart-' + participantNum), options);
    chart.render();
}

function drawStatsChart(participant, participantNum){
    let data01 = [];
    let data02 = [];
    let data03 = [];
    let data04 = [];
    let data05 = [];
    let data06 = [];
    let data07 = [];
    let data08 = [];
    let data09 = [];
    let data10 = [];
    let data11 = [];
    let data12 = [];
    let data13 = [];
    let data14 = [];
    let data15 = [];
    let data16 = [];
    let data17 = [];
    let data18 = [];
    let data19 = [];
    let data20 = [];
    let data21 = [];
    let data22 = [];
    let data23 = [];
    let data24 = [];
    let data25 = [];

    for (let i = 0; i < participant.length; i++) {
        data01.push(participant[i].participantFrameChamp.abilityHaste);
        data02.push(participant[i].participantFrameChamp.abilityPower);
        data03.push(participant[i].participantFrameChamp.armor);
        data04.push(participant[i].participantFrameChamp.armorPen);
        data05.push(participant[i].participantFrameChamp.armorPenPercent);
        data06.push(participant[i].participantFrameChamp.attackDamage);
        data07.push(participant[i].participantFrameChamp.attackSpeed);
        data08.push(participant[i].participantFrameChamp.bonusArmorPenPercent);
        data09.push(participant[i].participantFrameChamp.bonusMagicPenPercent);
        data10.push(participant[i].participantFrameChamp.ccReduction);
        data11.push(participant[i].participantFrameChamp.cooldownReduction);
        data12.push(participant[i].participantFrameChamp.health);
        data13.push(participant[i].participantFrameChamp.healthMax);
        data14.push(participant[i].participantFrameChamp.healthRegen);
        data15.push(participant[i].participantFrameChamp.lifesteal);
        data16.push(participant[i].participantFrameChamp.magicPen);
        data17.push(participant[i].participantFrameChamp.magicPenPercent);
        data18.push(participant[i].participantFrameChamp.magicResist);
        data19.push(participant[i].participantFrameChamp.movementSpeed);
        data20.push(participant[i].participantFrameChamp.omnivamp);
        data21.push(participant[i].participantFrameChamp.physicalVamp);
        data22.push(participant[i].participantFrameChamp.power);
        data23.push(participant[i].participantFrameChamp.powerMax);
        data24.push(participant[i].participantFrameChamp.powerRegen);
        data25.push(participant[i].participantFrameChamp.spellVamp);
    }

    let options = {
        series: [
            {
                name: 'abilityHaste',
                data: data01
            },
            {
                name: 'abilityPower',
                data: data02
            },
            {
                name: 'armor',
                data: data03
            },
            {
                name: 'armorPen',
                data: data04
            },
            {
                name: 'armorPenPercent',
                data: data05
            },
            {
                name: 'attackDamage',
                data: data06
            },
            {
                name: 'attackSpeed',
                data: data07
            },
            {
                name: 'bonusArmorPenPercent',
                data: data08
            },
            {
                name: 'bonusMagicPenPercent',
                data: data09
            },
            {
                name: 'ccReduction',
                data: data10
            },
            {
                name: 'cooldownReduction',
                data: data11
            },
            {
                name: 'health',
                data: data12
            },
            {
                name: 'healthMax',
                data: data13
            },
            {
                name: 'healthRegen',
                data: data14
            },
            {
                name: 'lifesteal',
                data: data15
            },
            {
                name: 'magicPen',
                data: data16
            },
            {
                name: 'magicPenPercent',
                data: data17
            },
            {
                name: 'magicResist',
                data: data18
            },
            {
                name: 'movementSpeed',
                data: data19
            },
            {
                name: 'omnivamp',
                data: data20
            },
            {
                name: 'physicalVamp',
                data: data21
            },
            {
                name: 'power',
                data: data22
            },
            {
                name: 'powerMax',
                data: data23
            },
            {
                name: 'powerRegen',
                data: data24
            },
            {
                name: 'spellVamp',
                data: data25
            },
        ],
        grid: {
            show: true,
            borderColor: '#4b4b56',
            strokeDashArray: 0,
            position: 'back',
            xaxis: {
                lines: {
                    show: false
                }
            },
            yaxis: {
                lines: {
                    show: true
                }
            },
        },
        chart: {
            type: 'line',
            height: 350,
            events: {
                selection: function (chart, e) {
                    console.log(new Date(e.xaxis.min))
                }
            },
            toolbar: {
                show: false,
            },
            zoom: {
                enabled: false,
            },
        },
        // colors: ['#5e5543'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            // colors: ['#9C865CFF'],
            width: 3,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center'
        },
    };

    let chart = new ApexCharts(document.getElementById('stats-stats-chart-' + participantNum), options);
    chart.render();
}

const chartDamageDoneContainers = document.getElementsByClassName("stats-damageDone");
const chartDamageTakenContainers = document.getElementsByClassName("stats-damageTaken");
const chartGoldContainers = document.getElementsByClassName("stats-gold");
const chartCsContainers = document.getElementsByClassName("stats-cs");
const chartStatsContainers = document.getElementsByClassName("stats-stats");

window.onload = async (event) => {
    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    let participants = await getData('/participant-stats-chart?match=' + matchId);

    for (let i = 0; i < chartDamageDoneContainers.length; i++) {
        let container = chartDamageDoneContainers[i];
        let participantNum = container.getAttribute("data-participantId");
        drawDamageDoneChart(participants[i], participantNum)
    }

    for (let i = 0; i < chartDamageTakenContainers.length; i++) {
        let container = chartDamageTakenContainers[i];
        let participantNum = container.getAttribute("data-participantId");
        drawDamageTakenChart(participants[i], participantNum)
    }

    for (let i = 0; i < chartGoldContainers.length; i++) {
        let container = chartGoldContainers[i];
        let participantNum = container.getAttribute("data-participantId");
        drawGoldChart(participants[i], participantNum)
    }

    for (let i = 0; i < chartCsContainers.length; i++) {
        let container = chartCsContainers[i];
        let participantNum = container.getAttribute("data-participantId");
        drawCsChart(participants[i], participantNum)
    }

    for (let i = 0; i < chartStatsContainers.length; i++) {
        let container = chartStatsContainers[i];
        let participantNum = container.getAttribute("data-participantId");
        drawStatsChart(participants[i], participantNum)
    }
};

