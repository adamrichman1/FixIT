window.addEventListener("load",initialize,true);

const PENDING = 0;
const ACCEPTED = 1;
const CLOSED = 2;

function initialize(){
    //initial call to poll staff appointments
    pollAppointments();

}

function pollAppointments() {
    $.ajax({
        url: "http://localhost:8080/customer/getAppointmentHistory",
        type: 'GET',
        contentType: "application/json",
        headers: {
            'username': localStorage.getItem("username")
        },
        success: function(data) {
            console.log("Hello");
            console.log(data);
            populateAppointments(data);
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(jqXHR.status);
            console.log(textStatus);
            console.log(errorThrown);
            // TODO - ERROR HANDLE
        }
    });
}

function populateAppointments(appointments) {
    if (appointments.length !== 0) {
        let apptHead = document.getElementById('appt-head');
        apptHead.textContent = 'Currently Scheduled Appointments';

        let apptList = document.getElementById('appt-list');

        for (var i = 0; i < appointments.length; i++) {
            let appointmentID = appointments[i].appointmentID;
            let listItem = document.createElement('li');
            listItem.className = "list-group-item";

            let apptData = document.createElement('div');

            let idLink = document.createElement('a');
            idLink.innerText = "ID: " + appointments[i].appointmentID;
            idLink.href = "http://localhost:8080/customer/appointment/summary?appointmentID=" + appointmentID;

            let problem = document.createElement('p');
            problem.className = 'small';
            problem.innerText = "Description: " + appointments[i].problem;

            let apptTime = document.createElement('p');
            apptTime.className = 'small';
            apptTime.innerText = "Time: " + appointments[i].appointmentTime;

            apptData.append(idLink);
            apptData.append(problem);
            apptData.append(apptTime);

            if (appointments[i].appointmentStatus === CLOSED) {
                let closedItem = document.createElement('p');
                closedItem.className='text-danger';
                closedItem.innerText="CLOSED";
                apptData.append(closedItem);
            } else if (appointments[i].appointmentStatus === ACCEPTED) {
                let activeItem = document.createElement('p');
                activeItem.className='text-success';
                activeItem.innerText="ACCEPTED";
                apptData.append(activeItem);
            }
            else {
                let activeItem = document.createElement('p');
                activeItem.className='text-danger';
                activeItem.innerText="PENDING";
                apptData.append(activeItem);
            }

            listItem.appendChild(apptData);

            apptList.appendChild(listItem);
        }
    } else {
        let apptHead = document.getElementById('appt-head');
        apptHead.textContent = 'No Appointments';
    }
}
