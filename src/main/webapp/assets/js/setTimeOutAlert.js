$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	$('.alert-autocloseable-success').delay(3000).fadeOut( "slow", function() {
		// Animation complete.
		$('#autoclosable-btn-success').prop("disabled", false);
	});
});