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
            type: "POST",
            url: "products.html",
            data: "productId=" + id + "&count=" + count,
            success: function(data) {
                $("#prodCount").html(data);
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
            type: "POST",
            url: "cart.html",
            data: "productId=" + id + "&count=" + count,
            success: function(data) {
            	var result = data.split("|");

            	$("#prodCount").html(result[0]);
            	if(result[1] > 0) {
            		$("#theProdCount" + id).html(result[1]);
            	} else {
            		$("#card" + id).remove();
            	}
            }
        });
    }
}