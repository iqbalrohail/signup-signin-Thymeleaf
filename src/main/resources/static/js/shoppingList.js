$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(shoppingList, status){
			$('#idEdit').val(shoppingList.id);
			$('#ddlShoppingDetails').val(shoppingList.itemid);
		});
		$('#editModal').modal();
	});
});