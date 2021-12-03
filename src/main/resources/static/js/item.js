$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(item, status){
			$('#idEdit').val(item.id);
			$('#nameEdit').val(item.name);
			$('#wightEdit').val(item.wight);
			$('#colorEdit').val(item.color);
			$('#priceEdit').val(item.price);

		});
		$('#editModal').modal();
	});
});