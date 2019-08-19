/*$('#addCardSubmit').click(function (e) {
    e.preventDefault();
    let form = $(this);
    let url = form.action;
    console.log(form.)
    $.post(url,
        $('#addCard').serialize(),
        function (data, status, xhr) {
            // do something here with response;
        });
});*/
$( "addCard" ).on( "addCardSubmit", function( event ) {
    event.preventDefault();
    console.log( $( this ).serialize() );
});