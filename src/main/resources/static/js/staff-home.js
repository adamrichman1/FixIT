window.addEventListener("load",initialize,true);


function initialize(){

    populateAppointments();

}



//Update the token that was just placed
function populateAppointments()
{

    var staffID = 10; // TODO Get this from somewhere
    var xhr = new XMLHttpRequest();
    xhr.open("GET", '/staff/appointments/'+staffID, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        var result = JSON.parse(xhr.responseText);
        if(xhr.status !== 200){
            console.log("Error Status:"+xhr.status);
        }
        if(result["completed"]){
            console.log("Purchase created - POST: /purchases");

            populateList(result);

        }
        else {
            alert("Error")
        }
    };
    xhr.send(null);

}

function populateList( jsonResult){

    var appointmentList = document.getElementByID("staffAppointmentList");
    var appointments = jsonResponse['appointments'];

    for(var z = 0; z < newMessages.length; z++){
        var message = appointments[z];

        var newLI = document.createElement('li');
        var msgString = appointments.name;
        newLI.textContent = msgString;
        appointmentList.appendChild(newLI);
    }
}


//Update the token that was just placed
function populate()
{
    e = event;

    inDate = e.srcElement;
    var rdate;
    if (inDate.value != ''){
        rdate = inDate.value;}
    else{
        return;
    }
    styID = document.URL.substring(34);
    console.log(styID);
    console.log(styIDTest);
    var jsonObject = JSON.stringify(
        {
            ID: styID,
            date: rdate
        }
    );


    var xhr = new XMLHttpRequest();
    xhr.open("POST", '/loadDate', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        var result = JSON.parse(xhr.responseText);
        if(xhr.status !== 200){
            console.log("Error Status:"+xhr.status);
        }
        if(result["completed"]){
            console.log("Purchase created - POST: /purchases");

            loadRes(result);

        }
        else {
            alert("Error")
        }
    };
    xhr.send(jsonObject);

}