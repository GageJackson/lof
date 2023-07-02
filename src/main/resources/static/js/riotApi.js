(function () {
    "use strict";

    async function getRankData(){
        let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await fetch(`https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/${summonerID}?api_key=${RIOT_KEY}`)
        let data = await response.json();

        console.log(data);

        for (var i = 0; i < data.length; i++){
            if(data[i].queueType == "RANKED_SOLO_5x5"){
                // Extract relevant data
                const rankData = {
                    tier: data[i].tier,
                    ranking: data[i].rank,
                    leaguePoints: data[i].leaguePoints,
                    summonerId: data[i].summonerId
                };

                console.log(rankData);

                // Send the extracted data to the backend
                await fetch('/saveRankData', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(rankData)
                });

            } else {
                console.log("Not using: " + data[i].queueType);
            }
        }
    }

    async function getChampsData(){
        let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await  fetch(`https://na1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/${summonerID}/top?count=10&api_key=${RIOT_KEY}`)
        let data = await response.json();
        let champs = new Array();

        console.log(data);

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

        // Send the extracted data to the backend
        await fetch('/saveChampsData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(champs)
        });
    }

    async function getFriendData(){
        let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await  fetch(`https://na1.api.riotgames.com/lol/summoner/v4/summoners/${summonerID}?api_key=${RIOT_KEY}`)
        let data = await response.json();

        console.log(data);

        const friend = {
            icon: data.profileIconId,
            summonerLevel: data.summonerLevel,
            summonerId: data.id
        };

        console.log(friend);

        // Send the extracted data to the backend
        await fetch('/saveFriendData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(friend)
        });
    }

    async function getFriendMatches(){
        let puuID = 'LcGogwi3lEv5SCdlgEfIOhL8jOK-VW4BNhptrDtJEhJwSLqWgtK3gKPR2wG4L-k2E6D2Gycs38Lvsg';
        let response = await  fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuID}/ids?start=0&count=20&api_key=${RIOT_KEY}`)
        let data = await response.json();
        let matches = new Array();

        console.log(data);

        for (var i = 0; i < data.length; i++){
            const match = {
                matchId: data[i],
                summonerId: puuID
            };

            console.log(match);
            matches.push(match);
        }

        console.log(matches);

        // Send the extracted data to the backend
        await fetch('/saveFriendMatches', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(matches)
        });
    }

    async function getMatch(){
        let matchId = 'NA1_4699810363';
        let response = await  fetch(`https://americas.api.riotgames.com/lol/match/v5/matches/${matchId}?api_key=${RIOT_KEY}`)
        let data = await response.json();

        console.log(data);

        const match = {
            matchId: data.metadata.matchId,
            info: data.info,
            // participants: data.info.participants,
            // teams: data.info.teams
        };

        console.log(match);

        //Send the extracted data to the backend
        await fetch('/saveMatchData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(match)
        });
    }

    getMatch();
})();