window.addEventListener("load",initialize,true);


function initialize(){

    let button = document.getElementById('create-appt-button');
    button.addEventListener('click', function() {
        const formData = {
            "appointmentTime": document.getElementById('appt-time').value,
            "problem": document.getElementById('appt-description').value,
            "cost": 25
        };
        createAppointment(formData);
    });

}

function createAppointment(data) {
    $.ajax({
        url: "http://localhost:8080/createAppointment",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        headers: {
            'username': localStorage.getItem("username")
        },
        success: function() {
            window.location.href = "/customer/home"
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(jqXHR.status);
            console.log(textStatus);
            console.log(errorThrown);
            // TODO - ERROR HANDLE
        }
    });
}
