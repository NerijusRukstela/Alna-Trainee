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
    if ($('#hours').val() == "0") {

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
    if ($('#minutes').val() == "0") {

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

// function selectMinutes() {
//     // var value = "<option value = '0'>Minutes</option>";
//     var value = $('#minutes').append("<option value = '0'>Minutes</option>");
//     for (var i = 0; i < 60; i++) {
//         value += "<option value='" + i + "'>" + i + "</option>"
//     }
//     $('#minutes').append(value);
//
// }
function selectMinutes() {
    // var value = "<option value = '0'>Minutes</option>";
    var array = [];
    array.push($('<option>').text('Minutes'));
    for (var i = 1; i < 60; i++) {
        array.push($('<option>').val(i).text(i));
    }

    $('#minutes').append(array);
}

// function selectHour() {
//     //   var value = $("<option>").addClass('d-none');
//     var array = [];
//
//     array.push($('<option>').text('Hours'));
//     for (var i = 1; i <= 12; i++) {
//         array.push($('<option >').val(i).text(i));
//     }
//
//     $('#hours').append(array);
// }

function selectHour() {   //ByTadas
    //   var value = $("<option>").addClass('d-none');
    var array = [];

    array.push($('<option>').text('Hours'));
    for (var i = 1; i <= 12; i++) {
        array.push($('<option >', {
            value: i,
            text: i
        }));
    }
    $('#hours').append(array);
}


$(function () {

    hideAllError();
    selectHour();
    selectMinutes();

    $("#button").click(function (event) {
        event.preventDefault();

        bootbox.confirm({
            message: "Do you want to continue?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {

                if (result) {
                    if (isValidated()) {
                        $('#answer').html(measureAngleDegree(parseFloat($('#hours').val()), parseFloat($('#minutes').val())));
                    } else {
                        $('#errorList').removeClass('d-none');
                    }
                }
            }
        });


    });
    $('#date').datepicker({
        uiLibrary: 'bootstrap',
        iconsLibrary: 'fontawesome'
    });

});

$(function () {
    $('#clear').click(function (event) {
        event.preventDefault();
        hideAllError();
        $('#myForm')[0].reset();

    })
})
