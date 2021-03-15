function plus(id) {
	var input = $("#input" + id).get(0);
	var result = +input.value + 1;
	var prodCountTagId = "#theProdCount" + id;
	
	if($(prodCountTagId).get(0) != undefined) {
		var max = parseInt($(prodCountTagId).text());
		result = Math.min(result, max);
	}

	input.value = result;
}

function minus(id) {
	var input = $("#input" + id).get(0);
	input.value = Math.max(+input.value - 1, 1);
}

function buy(id) {
	var input = $("#input" + id).get(0);
    var count = parseInt(input.value);

    input.value = 1;
    if(isNaN(count) || count <= 0) {
        alert("Ошибка ввода");
    } else {
        $.ajax({
            type: "PUT",
            url: "cart/add",
            data: JSON.stringify({"productId":id, "count":count}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(cartState) {
                $("#prodCount").html(cartState.totalProductCount);
            }
        });
    }
}

function layOut(id) {
	var input = $("#input" + id).get(0);
	var count = parseInt(input.value);

	input.value = 1;
    if(isNaN(count) || count <= 0) {
        alert("Ошибка ввода");
    } else {
        $.ajax({
            type: "DELETE",
            url: "cart/remove",
            data: JSON.stringify({"productId":id, "count":count}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(cartState) {
                var concernedProdCount = cartState.concernedProductCount; 
                var totalProdCount = cartState.totalProductCount;
                
                if(concernedProdCount > 0) {
                    $("#theProdCount" + id).html(concernedProdCount);
                } else {
                    $("#card" + id).remove();
                }
                
                $("#prodCount").html(totalProdCount);
            }
        });
    }
}