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
	
	$("#search").keyup(function(){
		
		var search = $(this).val();
		
		
		var endpoint = "/player/get-filter-players";
		var methodType = "post";
		var data = { search : search}
		var afterSuccessElement = "#search-result";
		var parentelement = "#search-result"
		ajaxRequest(endpoint, methodType, data,afterSuccessElement,parentelement);
	});
})

function ajaxRequest(endpoint, methodType, data,afterSuccessElement,parentelement) {

	$.ajax({
		url : endpoint,
		type : methodType,
		data : data,
		dataType : 'json',
		success:function(result){
			
			if(result.status == "success"){
				
				$(parentelement).empty();
				$.each(result.data, function(i, player){
					
					var player = "<li  value="+player.id+" >"+player.name+"</li>";
					
					$(afterSuccessElement).append(player);
					
		        });
			}
			
			
			
			
        }
	});
}


