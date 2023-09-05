function drawApexLineChart(participantNum, chartLocation, data){

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

        drawApexLineChart(participantNum, 'chart-damageOverview', damageOverviewData);
        drawApexLineChart(participantNum, 'chart-damageDealt', damageDealtData);
        drawApexLineChart(participantNum, 'chart-damageTaken', damageTakenData);

        drawApexLineChart(participantNum, 'chart-overviewGold', overviewGoldData);
        drawApexLineChart(participantNum, 'chart-overviewCs', overviewCsData);
        drawApexLineChart(participantNum, 'chart-overviewXp', overviewXpData);

        drawApexLineChart(participantNum, 'chart-statsBasic', statsBasicData);
        drawApexLineChart(participantNum, 'chart-statsDamage', statsDamageData);
        drawApexLineChart(participantNum, 'chart-statsPen', statsPenData);
        drawApexLineChart(participantNum, 'chart-statsDefense', statsDefenseData);
        drawApexLineChart(participantNum, 'chart-statsLifesteal', statsLifestealData);
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
            data: healthMax
        },
        {
            name: 'healthRegen',
            data: healthRegen
        },
        {
            name: 'powerMax',
            data: powerMax
        },
        {
            name: 'powerRegen',
            data: powerRegen
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
            name: 'attackSpeed',
            data: attackSpeed
        },
        {
            name: 'abilityHaste',
            data: abilityHaste
        },
        {
            name: 'abilityPower',
            data: abilityPower
        }
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
            name: 'armorPenPercent',
            data: armorPenPercent
        },
        {
            name: 'magicPen',
            data: magicPen
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