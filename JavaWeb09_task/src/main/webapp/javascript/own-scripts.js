function plus(id, max) {
	var input = document.getElementById(id);
	input.value = isNaN(max) ? +input.value + 1 : Math.min(+input.value + 1, max);
}

function minus(id) {
    var input = document.getElementById(id);
	input.value = Math.max(+input.value - 1, 1);
}

function buy(id) {
	var input = document.getElementById(id);
	var i = parseInt(input.value);
	if(isNaN(i) || i <= 0) {
		alert("Ошибка ввода");
	}
}

function layOut(id) {
	buy(id)
}