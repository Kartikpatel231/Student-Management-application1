$(function() {
   

$('#myFormSubmit').click(function (e) {
    var formdata = new FormData()
    formdata.append('photo',$('#input-image').files[0])
    $.ajax({
        method : 'POST',
        processData : false,
        contentType : false,
        url : '/imageupload',
        data : formdata,
        success : function(o){
            //callback here on success
        },
        error : function(e){
            //callback here on error
        }
    })
});
});