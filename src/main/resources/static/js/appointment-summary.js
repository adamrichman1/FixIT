window.addEventListener("load",initialize,true);

function initialize(){
    document.getElementById("1star").addEventListener("click", colorRating(1));
    document.getElementById("2star").addEventListener("click", colorRating(2));
    document.getElementById("3star").addEventListener("click", colorRating(3));
    document.getElementById("4star").addEventListener("click", colorRating(4));
    document.getElementById("5star").addEventListener("click", colorRating(5));
   showRating(); // TODO : alter the argument to "showRating" to be the appointment status
}

// this will optionally display the html for the user to rate the consultant
function showRating(appointmentStatus){
    var ratingDiv = document.getElementById("rating");

    switch (appointmentStatus)
    {
        case 2:
            ratingDiv.style.display = "block";
            break;
        default:
            ratingDiv.style.display = "none";
            break;
    }
}

function colorRating(rating){
    switch (rating){
        case 5:
            check(document.getElementById("1star"));
            check(document.getElementById("2star"));
            check(document.getElementById("3star"));
            check(document.getElementById("4star"));
            check(document.getElementById("5star"));
            break;
        case 4:
            check(document.getElementById("1star"));
            check(document.getElementById("2star"));
            check(document.getElementById("3star"));
            check(document.getElementById("4star"));
            uncheck(document.getElementById("5star"));
            break;
        case 3:
            check(document.getElementById("1star"));
            check(document.getElementById("2star"));
            check(document.getElementById("3star"));
            uncheck(document.getElementById("4star"));
            uncheck(document.getElementById("5star"));
            break;
        case 2:
            check(document.getElementById("1star"));
            check(document.getElementById("2star"));
            uncheck(document.getElementById("3star"));
            uncheck(document.getElementById("4star"));
            uncheck(document.getElementById("5star"));
            break;
        case 1:
            check(document.getElementById("1star"));
            uncheck(document.getElementById("2star"));
            uncheck(document.getElementById("3star"));
            uncheck(document.getElementById("4star"));
            uncheck(document.getElementById("5star"));
            break;
        default:
            break;
    }
}

function check(elem) {
    elem.removeClass("unchecked");
    elem.addClass("checked")
}

function uncheck(elem) {

}