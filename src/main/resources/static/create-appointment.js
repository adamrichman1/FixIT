window.addEventListener("load",initialize,true);


function initialize(){

    var createApp  = document.getElementById('createAppointment');
    createApp.addEventListener("click",createAppointment);


}

//Update the token that was just placed
function createAppointment()
{

    var time = document.getElementById('apptTime');
    console.log(time.value);
    var notes = document.getElementById('apptNotes');
    console.log(notes.value);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", '/createAppointment', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        var result = JSON.parse(xhr.responseText);

    };

    xhr.send(null);
}
