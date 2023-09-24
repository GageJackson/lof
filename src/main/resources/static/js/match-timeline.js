window.addEventListener('load', function() {
    const participants = document.querySelectorAll('.participant-kill-btns');
    const matchBlocks = document.querySelectorAll('.event-blocks');
    const matchDots = document.querySelectorAll('.event-dots');
    let clicked = 0;

    participants.forEach(item => {
        item.addEventListener('click', function (event){
            let participantId = parseInt(item.getAttribute('data-participant-id'));

            console.log(clicked);

            if (clicked === participantId){
                clicked = 0;
                resetParticipants(participants, matchBlocks, matchDots);

            } else {
                clicked = participantId;
                toggleParticipants(participantId, participants);
                toggleDots(participantId, matchDots);
                toggleBlocks(participantId, matchBlocks);
            }

            console.log(clicked);
        });
    });

    matchBlocks.forEach(item => {
        let eventId = item.getAttribute('id');
        let idNum = eventId.slice(eventId.lastIndexOf('-') + 1);
        let twinElement = document.getElementById('event-dot-'+ idNum);

        item.addEventListener('mouseenter', function(event) {
            item.classList.add('highlighted-block');
            twinElement.classList.add('highlighted-dot');
        });

        item.addEventListener('mouseleave', function(event) {
            item.classList.remove('highlighted-block');
            twinElement.classList.remove('highlighted-dot');
        });
    });

    matchDots.forEach(item => {
        let eventId = item.getAttribute('id');
        let idNum = eventId.slice(eventId.lastIndexOf('-') + 1);
        let twinElement = document.getElementById('event-block-'+ idNum);

        item.addEventListener('mouseenter', function(event) {
            item.classList.add('highlighted-dot');
            twinElement.classList.add('highlighted-block');
        });

        item.addEventListener('mouseleave', function(event) {
            item.classList.remove('highlighted-dot');
            twinElement.classList.remove('highlighted-block');
        });
    });
});

function toggleParticipants(participantId, participants){
    for (const participant of participants) {
        let buttonId = parseInt(participant.getAttribute('data-participant-id'));

        if (buttonId !== participantId){
            participant.classList.add('unclicked');
        } else {
            participant.classList.remove('unclicked');
        }
    }
}

function toggleBlocks(participantId, matchBlocks){
    for (const block of matchBlocks) {
        let blockKillerId = parseInt(block.getAttribute('data-killer-id'));
        let blockKilledId = parseInt(block.getAttribute('data-killed-id'));

        if (blockKillerId !== participantId && blockKilledId !== participantId){
            block.classList.add('hide-me');
        } else {
            block.classList.remove('hide-me');
        }
    }
}

function toggleDots(participantId, matchDots){
    for (const dot of matchDots) {
        let dotKillerId = parseInt(dot.getAttribute('data-killer-id'));
        let dotKilledId = parseInt(dot.getAttribute('data-killed-id'));

        if (dotKillerId !== participantId && dotKilledId !== participantId){
            dot.classList.add('hide-me');
        } else {
            dot.classList.remove('hide-me');
        }
    }
}

function resetParticipants(participants, matchBlocks, matchDots){
    for (const participant of participants) {
        participant.classList.remove('unclicked');
    }
    for (const block of matchBlocks) {
        block.classList.remove('hide-me');
    }
    for (const dot of matchDots) {
        dot.classList.remove('hide-me');
    }
}