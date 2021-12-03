$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(ingredient, status){
			$('#idEdit').val(ingredient.id);
			$('#descriptionEdit').val(ingredient.description);
			$('#amountEdit').val(ingredient.amount);
			$('#ddlCategoryDetails').val(ingredient.recipeid);
		});
		$('#editModal').modal();
	});
});