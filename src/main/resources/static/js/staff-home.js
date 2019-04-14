window.addEventListener("load",initialize,true);

const PENDING = 0;
const ACTIVE = 1;
const CLOSED = 2;

function initialize(){
    //initial call to poll staff appointments
    pollAppointments();

}

function pollAppointments() {
    $.ajax({
        url: "http://localhost:8080/staff/getAppointmentHistory",
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
            const appointmentID = appointments[i].appointmentID;

            let listItem = document.createElement('li');
            listItem.className = "list-group-item";

            let apptData = document.createElement('div');

            let idLink = document.createElement('a');
            idLink.innerText = "ID: " + appointments[i].appointmentID;

            let problem = document.createElement('p');
            problem.className = 'small';
            problem.innerText = "Description: " + appointments[i].problem;

            let apptTime = document.createElement('p');
            apptTime.className = 'small';
            apptTime.innerText = "Time: " + appointments[i].appointmentTime;

            let acceptButton = document.createElement('button');

            acceptButton.className = ' btn btn-primary';
            acceptButton.innerText = "Accept Appointment";
            acceptButton.style.visibility = 'hidden';

            acceptButton.addEventListener('click', function() {
                const formData = {
                    "appointmentID": appointmentID,
                    "appointmentStatus": 1
                };
                updateAppointmentStatus(formData);
                window.location.href = "/staff/appointment/worklog?appointmentID=" + appointmentID;
            });

            if (appointments[i].appointmentStatus === PENDING) {
                acceptButton.style.visibility = '';
            }

            apptData.append(idLink);
            apptData.append(problem);
            apptData.append(apptTime);
            apptData.append(acceptButton);

            if (appointments[i].appointmentStatus === CLOSED) {
                let closedItem = document.createElement('p');
                closedItem.className='text-danger';
                closedItem.innerText="CLOSED";
                apptData.append(closedItem);
                idLink.href="http://localhost:8080/staff/appointment/worklog?appointmentID=" + appointmentID;
            } else if (appointments[i].appointmentStatus === ACTIVE) {
                let activeItem = document.createElement('p');
                activeItem.className='text-success';
                activeItem.innerText="ACTIVE";
                apptData.append(activeItem);
                idLink.href="http://localhost:8080/staff/appointment/worklog?appointmentID=" + appointmentID;
            }

            listItem.appendChild(apptData);

            apptList.appendChild(listItem);
        }
    } else {
        let apptHead = document.getElementById('appt-head');
        apptHead.textContent = 'No Appointments';
    }
}

function updateAppointmentStatus(formData) {
    $.ajax({
        url: "http://localhost:8080/updateAppointmentStatus",
        type: 'PUT',
        contentType: "application/json",
        headers: {
            'username': localStorage.getItem("username")
        },
        data: JSON.stringify(formData),
        success: function() {
            console.log(formData);
            console.log("Hello");
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(jqXHR.status);
            console.log(textStatus);
            console.log(errorThrown);
            // TODO - ERROR HANDLE
        }
    });
}
