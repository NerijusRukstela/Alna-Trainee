function findHourArrow(h, min) {
    return h * (360 / 12) + min * 0.5
}

function findMinuteArrow(min) {
    return min * (360 / 60);
}

function measureAngleDegree(hour, minutes) {
    var degreeBetweenArrow = Math.abs(findHourArrow(hour, minutes) - findMinuteArrow(minutes));
    var smallestAngle = 0;
    if (degreeBetweenArrow > 180) {
        smallestAngle = 360 - degreeBetweenArrow;
    } else {
        smallestAngle = degreeBetweenArrow;
    }
    return smallestAngle;
}

function isChecked() {
    if ($('#check').is(":checked")) {
        return true;
    } else {
        $('#checkBoxError').removeClass('d-none');
        $('#check1').addClass('border border-danger');
        return false;
    }
}

function isCorrectHours() {
    if ($('#hours').val() == "") {

        $('#hoursEmpty').removeClass('d-none');
        $('#hours').addClass('border border-danger');
        return false;
    }
    if (0 <= $('#hours').val() && $('#hours').val() <= 12) {
        return true;
    } else {
        $('#hoursInccorect').removeClass('d-none');
        $('#hours').addClass('border border-danger');
        return false;
    }
}

function isCorrectMinutes() {
    if ($('#minutes').val() == "") {

        $('#minutesEmpty').removeClass('d-none');
        $('#minutes').addClass('border border-danger');
        return false;
    }
    if (0 <= $('#minutes').val() && $('#minutes').val() < 60) {
        return true;
    } else {
        $('#minutesInccorect').removeClass('d-none');
        $('#minutes').addClass('border border-danger');
        return false;
    }
}

function isValidated() {
    hideAllError();

    return isChecked() & isCorrectMinutes() & isCorrectHours();
}

function hideAllError() {
    $('.errorElement').addClass('d-none');
    $('.errorBorder').removeClass('border border-danger');


}
function selectMinutes() {
    for(var i=0; i<60; i++){
        "'<option value='"+i+"'>"+i+"</option>";
    }

}
function selectHour() {
    var yy = "<option value = '0'>select</option>";
    for(var i=1; i<=12; i++){
        yy+="<option value='"+i+"'>"+i+"</option>"
    }
    $('#ccexpy').innerHTML = yy;
document.getElementById('ccexpy').innerHTML = yy;
}

$(function () {

    $("#button").click(function (event) {
        event.preventDefault();

        if (isValidated()) {
            $('#answer').html(measureAngleDegree(parseFloat($('#hours').val()), parseFloat($('#minutes').val())));
        } else {
            $('#errorList').removeClass('d-none');
        }

    });
    $('#date').datepicker({
        uiLibrary: 'bootstrap',
        iconsLibrary: 'fontawesome'
    });

});
