$(document).ready(function() {

	$(document).on("click", "#signup-form-btn", function() {

		var data = {};
		$.each($('form#signup-form').serializeArray(), function() {
			data[this.name] = this.value;
		});
		console.log("data: " + JSON.stringify(data));
		endpoint = "/api/register-user";
		methodType = "post";
		ajaxRequest(endpoint, methodType, JSON.stringify(data));
	});

	$(".datepicker").datepicker({
		dateFormat : "yy-mm-dd"
	});
})

function ajaxRequest(endpoint, methodType, data) {

	$.ajax({
		url : endpoint,
		type : methodType,
		data : data,
		contentType : "application/json",
		dataType : 'json',
	});
}


