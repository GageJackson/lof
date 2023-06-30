(function () {
    "use strict";

    async function getData(){
        let summonerID = 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE';
        let response = await fetch(`https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/${summonerID}?api_key=${RIOT_KEY}`)
        let response2 = response.json();
        console.log(response2);
    }

    getData();
})();