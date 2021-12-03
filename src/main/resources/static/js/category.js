$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(category, status){
			$('#idEdit').val(category.id);
			$('#descriptionEdit').val(category.description);
		});
		$('#editModal').modal();
	});
});