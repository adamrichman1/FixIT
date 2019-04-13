window.addEventListener("load",initialize,true);


function initialize(){


    //initial call to poll staff appointments
    pollAppointments();

}


    //poll the model for any new rooms that may have been created
    function pollAppointments() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
                var rawJson = JSON.parse(xmlHttp.responseText);
                updateChatRoomList(rawJson, pollAppointments);
            }
        };
        xmlHttp.open("GET", '/staff/getAppointmentHistory', true);
        xmlHttp.send(null);
        //var testData = {"subject":"PC","address": "A street","appointmentTime": 1000,"notes":"FixComputer","date":"4/28/19","ID":1,"name":"John"};
        console.log(rawJson);
        updateAppointmentList(rawJson, pollAppointments);
    }


    //update the DOM to show the updated list of appointments
    function updateAppointmentList(rawJson, callback){

        //TODO if there is an appointment
        if (true){


            var staffAppHead = document.getElementById('staffAppHead');
            staffAppHead.textContent = 'Currently Scheduled Appointments:';

            var staffAppList = document.getElementById('staffAppList');
            staffAppList.innerHTML = '';

            // TODO how to iterate through list
            for(var i = 0; i < 1; i++){
                var appInd = document.createElement('li');
                var appTime = document.createElement('h4');
                var appDate = document.createElement('h4');
                var appAddress = document.createElement('h4');
                var appSubject = document.createElement('h4');
                var appName = document.createElement('h4');
                var appNotesHead = document.createElement('h4');
                var appNotes = document.createElement('p');
                var appDel = document.createElement('button');
                appDel.id = rawJson.ID;
                appDel.addEventListener('click', cancelAppointment,false);
                appTime.textContent = "Appointment Time: "+ rawJson.appointmentTime;
                appDate.textContent = "Appointment Date: "+rawJson.date;
                appAddress.textContent = "Appointment Address: "+rawJson.address;
                appSubject.textContent = "Appointment Subject: "+rawJson.subject;
                appName.textContent = "Customer Name: "+rawJson.name;
                appNotesHead.textContent = "Appointment Notes:";
                appNotes.textContent = rawJson.notes;
                appDel.textContent = "Cancel Appointment";

                staffAppList.appendChild(appInd);
                appInd.appendChild(appDate);
                appInd.appendChild(appTime);
                appInd.appendChild(appAddress);
                appInd.appendChild(appSubject);
                appInd.appendChild(appNotesHead);
                appInd.appendChild(appNotes);
                appInd.appendChild(appDel);
                staffAppList.appendChild(appInd);
            }
        }
        else{
            var staffAppHead = document.getElementById('staffAppHead');
            staffAppHead.textContent = 'You currently have no appointments scheduled';
        }

        setTimeout(callback, 1000);
    }

    //function to cancel an appointment
    function cancelAppointment(eve){
        var appointmentID = eve.srcElement.id;
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
                var rawJson = JSON.parse(xmlHttp.responseText);
                //updateChatRoomList(rawJson, pollAppointments);
            }
        };
        xmlHttp.open("POST", '/staff/cancelAppointment/'+appointmentID, true);
        xmlHttp.send(null);
    }

