
$(function () {
    $('#add').click(function (event) {
        event.preventDefault();
       // $('.addTable').removeClass('d-none');

      

        bootbox.alert(popupoptions);
    })
})
$(function () {
    $("#button").click(function (event) {
        event.preventDefault();
        $("#myTable").last().append("<tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>");
    });
});