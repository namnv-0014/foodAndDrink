$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	
	$('.alert-autocloseable-success').delay(3000).fadeOut( "slow", function() {
		// Animation complete.
		$('#autoclosable-btn-success').prop("disabled", false);
	});
	
	$('.alert-autocloseable-error').delay(6000).fadeOut( "slow", function() {
		// Animation complete.
		$('#autoclosable-btn-error').prop("disabled", false);
	});
	
	var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
                $('.avatar').attr('value', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    
    $(".file-upload").on('change', function(){
        readURL(this);
    });
});