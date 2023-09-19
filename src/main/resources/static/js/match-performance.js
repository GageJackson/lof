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