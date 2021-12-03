$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(category, status){
			$('#idEdit').val(category.id);
			$('#planNameEdit').val(category.planName);
			$('#venueEdit').val(category.venue);

			var eventDate = category.eventDate.substr(0,10);
			$('#eventDateEdit').val(category.eventDate);

		});
		$('#editModal').modal();
	});
});