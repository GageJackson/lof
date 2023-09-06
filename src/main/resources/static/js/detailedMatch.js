function drawApexAreaChart(participantNum, chartLocation, data){

    let options = {
        series: data,
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
                },
                labels: {
                    formatter: function(val, index) {
                        return val.toFixed(2);
                    }
                },
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
            width: 2,
        },
        fill: {
            type: 'solid',
        },
        legend: {
            position: 'top',
            horizontalAlign: 'center',
            labels: {
                colors: ['#a2434e', '#4775b4', '#f6f1e5'],
                useSeriesColors: false
            },
        },
        tooltip: {
            enabled: true,
            enabledOnSeries: undefined,
            shared: true,
            followCursor: false,
            intersect: false,
            inverseOrder: false,
            custom: undefined,
            fillSeriesColor: false,
            theme: false,
            style: {
                fontSize: '12px',
                fontFamily: undefined
            },
            onDatasetHover: {
                highlightDataSeries: false,
            },
            x: {
                show: false,
            },
            y: {
                formatter: undefined,
                title: {
                    formatter: (seriesName) => seriesName,
                },
            },
            marker: {
                show: true,
            },
            items: {
                display: 'flex',
            },
            fixed: {
                enabled: false,
                position: 'topRight',
                offsetX: 0,
                offsetY: 0,
            },
        }
    };

    let chart = new ApexCharts(document.getElementById(chartLocation + participantNum), options);
    chart.render();
}

function getYaxisConfig(numberOfDatasets){
    let yaxisConfig = [];

    if (numberOfDatasets >= 2) {
        // Add the configuration for the left y-axis
        yaxisConfig.push({
            axisTicks: {
                show: true,
            },
            axisBorder: {
                show: true,
                color: '#008FFB',
            },
            labels: {
                style: {
                    colors: '#008FFB',
                },
                formatter: function (value) {
                    return value.toLocaleString();
                }
            },
            tooltip: {
                enabled: false,
            },
        });
    }

    if (numberOfDatasets >= 4) {
        // Add the configuration for the right y-axis
        yaxisConfig.push({
            opposite: true,
            axisTicks: {
                show: true,
            },
            axisBorder: {
                show: true,
                color: '#00E396',
            },
            labels: {
                style: {
                    colors: '#00E396',
                },
                formatter: function (value) {
                    return value.toLocaleString();
                }
            },
            tooltip: {
                enabled: false,
            },
        });
    }

    return yaxisConfig;
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
                stops: [0, 100],
            }
        }
    }
}

function getChartType(chartType){
    if (chartType === 'line') {
        return {
            type: 'line',
            height: 350,
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
    }

    if (chartType === 'area') {
        return {
            type: 'area',
            stacked: true,
            height: 350,
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
    }
}

function drawApexChart(participantNum, chartLocation, data, graphType){
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
            colors: colors,
            width: 2,
            type: 'solid',
        },
        fill: fillType,
        legend: {
            position: 'top',
            horizontalAlign: 'right',
            offsetX: 0,
            offsetY: 50,
        },
        tooltip: {
            enabled: true,
            enabledOnSeries: undefined,
            shared: true,
            followCursor: false,
            intersect: false,
            inverseOrder: false,
            custom: function({ series, seriesIndex, dataPointIndex, w }) {
                var tooltipContent = `
                    <div class="custom-tooltip d-flex bg-transparent color-neutral-light"
                         style="box-shadow: none"
                    >
                `;

                // Iterate through all series and display their values
                for (let i = 0; i < w.config.series.length; i++) {
                    if(w.config.series[i].data[dataPointIndex])
                    {
                        let seriesColor = w.config.colors[i]; // Get the color of the series

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
                position: 'topLeft',
                offsetX: 60,
                offsetY: 30,
            },
            dropShadow: {
                enabled: false,
            },
        }
    };

    let chart = new ApexCharts(document.getElementById(chartLocation + participantNum), options);
    chart.render();
}


const participantCharts = document.getElementsByClassName("participant-charts");

window.onload = async (event) => {
    const match = document.getElementById('match')
    const matchId = match.getAttribute("data-matchId");
    let participants = await getData('/participant-stats-chart?match=' + matchId);

    for (let i = 0; i < participantCharts.length; i++) {
        let container = participantCharts[i];
        let participantNum = container.getAttribute("data-participantId");
        let participantData = participants[i];

        let damageOverviewData = getDamageOverviewData(participantData);
        let damageDealtData = getDamageDealtData(participantData);
        let damageTakenData = getDamageTakenData(participantData);

        let overviewGoldData = getOverviewGoldData(participantData);
        let overviewCsData = getOverviewCsData(participantData);
        let overviewXpData = getOverviewXpData(participantData);

        let statsBasicData = getStatsBasicData(participantData);
        let statsDamageData = getStatsDamageData(participantData);
        let statsPenData = getStatsPenData(participantData);
        let statsDefenseData = getStatsDefenseData(participantData);
        let statsLifestealData = getStatsLifestealData(participantData);

        drawApexChart(participantNum, 'chart-damageOverview', damageOverviewData, 'line');
        drawApexChart(participantNum, 'chart-damageDealt', damageDealtData, 'area');
        drawApexChart(participantNum, 'chart-damageTaken', damageTakenData, 'area');

        drawApexChart(participantNum, 'chart-overviewGold', overviewGoldData, 'area');
        drawApexChart(participantNum, 'chart-overviewCs', overviewCsData, 'area');
        drawApexChart(participantNum, 'chart-overviewXp', overviewXpData, 'area');

        drawApexChart(participantNum, 'chart-statsBasic', statsBasicData, 'line');
        drawApexChart(participantNum, 'chart-statsDamage', statsDamageData, 'line');
        drawApexChart(participantNum, 'chart-statsPen', statsPenData, 'line');
        drawApexChart(participantNum, 'chart-statsDefense', statsDefenseData, 'line');
        drawApexChart(participantNum, 'chart-statsLifesteal', statsLifestealData, 'line');
    }
};

function getDamageOverviewData(participantData){
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

function getOverviewGoldData(participantData){
    let goldData = [];

    for (let i = 0; i < participantData.length; i++) {
        goldData.push(participantData[i].totalGold);
    }

    return [{
        name: 'Gold',
        data: goldData
    }];
}

function getOverviewCsData(participantData){
    let csData = [];

    for (let i = 0; i < participantData.length; i++) {
        csData.push(participantData[i].jungleMinionsKilled + participantData[i].minionsKilled);
    }

    return [{
        name: 'CS',
        data: csData
    }];
}

function getOverviewXpData(participantData){
    let xpData = [];

    for (let i = 0; i < participantData.length; i++) {
        xpData.push(participantData[i].xp);
    }

    return [{
        name: 'XP',
        data: xpData
    }];
}

function getStatsBasicData(participantData){
    let healthMax = [];
    let healthRegen = [];
    let powerMax = [];
    let powerRegen = [];

    for (let i = 0; i < participantData.length; i++) {
        healthMax.push(participantData[i].participantFrameChamp.healthMax);
        healthRegen.push(participantData[i].participantFrameChamp.healthRegen);
        powerMax.push(participantData[i].participantFrameChamp.powerMax);
        powerRegen.push(participantData[i].participantFrameChamp.powerRegen);
    }

    return [
        {
            name: 'healthMax',
            data: healthMax,
            group: 'group1',
            yaxisIndex: 0
        },
        {
            name: 'powerMax',
            data: powerMax,
            group: 'group1',
            yaxisIndex: 0
        },
        {
            name: 'healthRegen',
            data: healthRegen,
            group: 'group2',

            yaxisIndex: 3
        },
        {
            name: 'powerRegen',
            data: powerRegen,
            group: 'group2',

            yaxisIndex: 3
        }
    ]
}

function getStatsDamageData(participantData){
    let abilityHaste = [];
    let abilityPower = [];
    let attackDamage = [];
    let attackSpeed = [];

    for (let i = 0; i < participantData.length; i++) {
        abilityHaste.push(participantData[i].participantFrameChamp.abilityHaste);
        abilityPower.push(participantData[i].participantFrameChamp.abilityPower);
        attackDamage.push(participantData[i].participantFrameChamp.attackDamage);
        attackSpeed.push(participantData[i].participantFrameChamp.attackSpeed);
    }

    return [
        {
            name: 'attackDamage',
            data: attackDamage
        },
        {
            name: 'abilityPower',
            data: abilityPower
        },
        {
            name: 'attackSpeed',
            data: attackSpeed
        },
        {
            name: 'abilityHaste',
            data: abilityHaste
        },

    ]

}

function getStatsPenData(participantData){
    let armorPen = [];
    let armorPenPercent = [];
    let magicPen = [];
    let magicPenPercent = [];

    for (let i = 0; i < participantData.length; i++) {
        armorPen.push(participantData[i].participantFrameChamp.armorPen);
        armorPenPercent.push(participantData[i].participantFrameChamp.armorPenPercent + participantData[i].participantFrameChamp.bonusArmorPenPercent);
        magicPen.push(participantData[i].participantFrameChamp.magicPen);
        magicPenPercent.push(participantData[i].participantFrameChamp.magicPenPercent + participantData[i].participantFrameChamp.bonusMagicPenPercent);
    }

    return [
        {
            name: 'armorPen',
            data: armorPen
        },
        {
            name: 'magicPen',
            data: magicPen
        },
        {
            name: 'armorPenPercent',
            data: armorPenPercent
        },
        {
            name: 'magicPenPercent',
            data: magicPenPercent
        }
    ]

}

function getStatsDefenseData(participantData){
    let armor = [];
    let magicResist = [];

    for (let i = 0; i < participantData.length; i++) {
        armor.push(participantData[i].participantFrameChamp.armor);
        magicResist.push(participantData[i].participantFrameChamp.magicResist);
    }

    return [
        {
            name: 'armor',
            data: armor
        },
        {
            name: 'magicResist',
            data: magicResist
        },
    ]

}

function getStatsLifestealData(participantData){
    let lifesteal = [];
    let omnivamp = [];
    let physicalVamp = [];
    let spellVamp = [];

    for (let i = 0; i < participantData.length; i++) {

        lifesteal.push(participantData[i].participantFrameChamp.lifesteal);
        omnivamp.push(participantData[i].participantFrameChamp.omnivamp);
        physicalVamp.push(participantData[i].participantFrameChamp.physicalVamp);
        spellVamp.push(participantData[i].participantFrameChamp.spellVamp);
    }

    return [
        {
            name: 'lifesteal',
            data: lifesteal
        },
        {
            name: 'omnivamp',
            data: omnivamp
        },
        {
            name: 'physicalVamp',
            data: physicalVamp
        },
        {
            name: 'spellVamp',
            data: spellVamp
        }
    ]
}