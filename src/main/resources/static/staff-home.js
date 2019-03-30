window.addEventListener("load",initialize,true);


function initialize(){


    //initial call to poll staff appointments
    pollAppointments();

    //populateAppointments();

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
        xmlHttp.open("GET", '/staff/getAppointments/', true);
        xmlHttp.send(null);
    }


    //update the DOM to show the updated list of appointments
    function updateChatRoomList(rawJson, callback){
        if (allApps.length !== 0){

            var staffAppHead = document.getElementById('staffAppHead');
            staffAppHead.textContent = 'Currently Scheduled Appointments:';

            var staffAppList = document.getElementById('staffAppList');
            staffAppList.innerHTML = '';
            for(var i = 0; i < allApps.length; i++){
                var appInd = document.createElement('li');
                var appmJoin = document.createElement('a');
                var appDel = document.createElement('button');
                appDel.id = '/deleteRoom/'+allApps[i].id;
                appDel.addEventListener('click', deleteRoom,false);
                appInd.textContent = allApps[i].name+ " ";
                appJoin.textContent = "Join Chat";
                appJoin.href = '/room/'+allApps[i].id;
                appDel.textContent = "Delete Room";
                appDel.id = '/deleteRoom/'+allApps[i].id;
                staffAppList.appendChild(appInd);
                appInd.appendChild(appJoin);
                appInd.appendChild(appDel);
            }
        }
        else{
            var staffAppHead = document.getElementById('staffAppHead');
            staffAppHead.textContent = 'You currently have no appointments scheduled';
        }

        setTimeout(callback, 1000);
    }
