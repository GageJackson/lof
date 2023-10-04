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
        let radius = 75;
        let diameter = Math.round(3.14 * 2 * radius);
        let strokeOffset = diameter - ( diameter * ( redNum / matchTotal ))

        frontRing.style.strokeDasharray = diameter;
        frontRing.style.strokeDashoffset = strokeOffset;
        frontRing.style.strokeWidth = 12;
        frontRing.setAttribute("cx", 100);
        frontRing.setAttribute("cy", 100);
        frontRing.setAttribute("r", radius);

        backRing.style.strokeWidth = 12;
        backRing.setAttribute("cx", 100);
        backRing.setAttribute("cy", 100);
        backRing.setAttribute("r", radius);
    })

    const playerStat = document.querySelectorAll('.player-stat');
    playerStat.forEach(item => {
        let playerData = item.getAttribute('data-info-player');
        let bestData = item.getAttribute('data-info-best');

        let playerNum = parseInt(playerData);
        let bestNum = parseInt(bestData);

        let frontRect = item.querySelector('.front-rect');
        let backRect = item.querySelector('.back-rect');
        let width = 120;
        let height = 20;
        let adjustedWidth = width - ( width * ( playerNum / bestNum ))

        frontRect.setAttribute("width", adjustedWidth);
        frontRect.setAttribute("height", height);

        if (! item.querySelector('.front-rect-blue')){
            frontRect.setAttribute("x", (width - adjustedWidth));
        }

        backRect.setAttribute("width", width);
        backRect.setAttribute("height", height);
    })

    const playerStatBtns = document.querySelectorAll('.p-stat-btn');
    playerStatBtns.forEach(item => {
        let btnId = item.id;
        let setName = item.getAttribute('data-set-name');
        let btnNum = parseInt(item.getAttribute('data-set-num'));
        let playerStats = document.querySelectorAll('.' + setName + '-stat');

        item.addEventListener("click", function(){
            for (const playerStat of playerStats) {
                if (parseInt(playerStat.getAttribute('data-set-num')) === btnNum){
                    playerStat.classList.remove('d-none')
                } else {
                    playerStat.classList.add('d-none');
                }
            }
        });
    });
});