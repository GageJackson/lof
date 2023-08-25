(function () {
    "use strict";
function initializeFriendButtons(){
    const friendButtons = document.getElementsByClassName('friend-btns');
    console.log(friendButtons);

    for (const friendButton of friendButtons) {
        friendButton.addEventListener('click', () => toggleFriendSelection(friendButton))
    }
}

async function toggleFriendSelection(friendButton){
    const friendId = friendButton.id.slice(friendButton.id.indexOf('-') + 1);
    friendButton.classList.toggle('selected');
    // Get the selected friend IDs
    const selectedFriendIds = Array.from(document.getElementsByClassName('selected'))
        .map(selectedFriendButton => selectedFriendButton.id.slice(selectedFriendButton.id.indexOf('-') + 1));

    // Send the selected friend IDs to the server
    const url = '/sort?friendIds=' + selectedFriendIds.join(',');
    try {
        window.location.href = url;
    } catch (error) {
        console.error('Error:', error);
    }
}

initializeFriendButtons();

})();