(function () {
    "use strict";
function establishFriendButtons(){
    const friendButtons = document.getElementsByClassName('friend-btns');
    console.log(friendButtons);

    for (const friendButton of friendButtons) {
        friendButton.addEventListener('click', () => sortMatchesByFriend(friendButton.id))
    }

    // friendButtons.forEach(friendButton => {
    //     friendButton.addEventListener('click', sortMatchesByFriend(friendButton.id));
    // });
}

async function sortMatchesByFriend(friendButtonId){
    const friendId = friendButtonId.slice(friendButtonId.indexOf('-') + 1);
    const url = '/sort/' + friendId;

    try {
        const response = await fetch(url);
        const data = await response.text();
        console.log(data);
    } catch (error) {
        console.error('Error:', error);
    }
}

establishFriendButtons();

})();