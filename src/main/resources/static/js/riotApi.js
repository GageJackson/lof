(function () {
    "use strict";

    async function getRankData(summonerID){
        //let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await fetch(`https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/${summonerID}?api_key=${RIOT_KEY}`)
        let data = await response.json();

        //console.log(data);

        for (var i = 0; i < data.length; i++){
            if(data[i].queueType == "RANKED_SOLO_5x5"){
                // Extract relevant data
                const rankData = {
                    tier: data[i].tier,
                    ranking: data[i].rank,
                    leaguePoints: data[i].leaguePoints,
                    summonerId: data[i].summonerId
                };

                //console.log(rankData);

                await postData('/saveRankData', rankData)

            } else {
                console.log("Not using: " + data[i].queueType);
            }
        }
    }

    async function getChampsData(summonerID){
        //let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await  fetch(`https://na1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/${summonerID}/top?count=10&api_key=${RIOT_KEY}`)
        let data = await response.json();
        let champs = [];

        //console.log(data);

        for (var i = 0; i < data.length; i++){
            const champ = {
                champId: data[i].championId,
                champLevel: data[i].championLevel,
                champPoints: data[i].championPoints,
                summonerId: data[i].summonerId
            };

            console.log(champ);
            champs.push(champ);
        }

        await postData('/saveChampsData', champs)
    }

    async function getFriendData(summonerID){
        // let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await  fetch(`https://na1.api.riotgames.com/lol/summoner/v4/summoners/${summonerID}?api_key=${RIOT_KEY}`)
        let data = await response.json();

        //console.log(data);

        const friend = {
            icon: data.profileIconId,
            summonerLevel: data.summonerLevel,
            summonerId: data.id
        };

        //console.log(friend);

        await postData('/saveFriendData', friend)
    }

    async function getFriendMatches(puuID){
        let start = 0; //starts at most recent
        let count = 100; //pulls up to 100 games
        let response = await  fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuID}/ids?start=${start}&count=${count}&api_key=${RIOT_KEY}`)
        let data = await response.json();

        let matches = []

        // console.log(data);

        for (let i = 0; i < data.length; i++){
            const match = {
                matchId: data[i],
                puuId: puuID
            };

            // console.log(match);
            matches.push(match);
        }

        await postData('/saveFriendMatches', matches)
        //saveMatchData(matches);
    }

    async function saveMatchData(matches) {

        for (const match of matches) {
            const overviewResponse = await fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/NA1_${match}?api_key=${RIOT_KEY}`);
            // const overviewResponse = await fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/${match.matchId}?api_key=${RIOT_KEY}`);
            const overviewData = await overviewResponse.json();
            const overviewMatch = {
                matchId: overviewData.metadata.matchId,
                info: overviewData.info
            };
            console.log(overviewData);
            // console.log(overviewMatch);

            const timelineResponse = await fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/NA1_${match}/timeline?api_key=${RIOT_KEY}`);
            // const timelineResponse = await fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/${match.matchId}/timeline?api_key=${RIOT_KEY}`);
            const timelineData = await timelineResponse.json();
            const timelineMatch = {
                matchId: timelineData.metadata.matchId,
                info: timelineData.info
            };
            console.log(timelineData);
            // console.log(timelineMatch);

            const matches = [overviewMatch, timelineMatch];

            await postData('/saveMatchData', matches)
        }
    }

    async function postData(postLocation, postInfo){
        await fetch(postLocation, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postInfo)
        });
    }

    async function getData(getLocation){
        let response = await fetch(getLocation, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        //console.log(data);
        return await response.json();
    }

    async function pageLoad() {
        let friends = await getData('/testing');
        console.log(friends);
        for (const friend of friends) {
            let promises = [];
            console.log(friend.name);
            promises.push(getFriendData(friend.summonerId));
            promises.push(getRankData(friend.summonerId));
            promises.push(getChampsData(friend.summonerId));
            promises.push(getFriendMatches(friend.puuId));

            // Wait for 0.25 seconds to prevent exceeding 20 calls per second
            await delay(250);
            await Promise.all(promises);
        }
    }


    function delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function handleButtonClick(event) {
        const button = event.target.closest('.match');
        const matchId = button.id
        console.log(button.id)
        saveMatchData([matchId]);
    }

    const matchButtons = document.querySelectorAll('.match');

    matchButtons.forEach(button => {
        button.addEventListener('click', handleButtonClick);
    });


    //pageLoad();

    // let matches = [4713039841,4712997908,4712936002,4712891138,4712872161]
    // saveMatchData(matches);
    //saveMatchData();
})();