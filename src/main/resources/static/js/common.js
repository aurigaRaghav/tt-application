$(document).ready(
		function() {

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

			$("#search").keyup(
					function() {

						var search = $(this).val();
						var endpoint = "/player/get-filter-players";
						var methodType = "post";
						var data = {
							search : search
						}
						var afterSuccessElement = "#search-result";
						var parentelement = "#search-result"
						if (search.length > 0) {

							ajaxRequest(endpoint, methodType, data,
									afterSuccessElement, parentelement);
						} else {

							$(parentelement).empty();
						}
					});
		})

function ajaxRequest(endpoint, methodType, data, afterSuccessElement,
		parentelement) {

	$.ajax({
		url : endpoint,
		type : methodType,
		data : data,
		dataType : 'json',
		success : function(result) {

			if (result.status == "success") {

				$(parentelement).empty();
				$.each(result.data, function(i, player) {

					var player = `<div class='card-body' style='box-shadow: 0 0 15px rgb(0 0 0/ 55%);margin-top: 31px;
    margin-bottom: 30px;padding: 35px;'>
						<div class='img-with-text'>
							<img
								style='margin-right: 50px; width: 50px; height: 50px; border-radius: 50%;'
								src='/images/tt.png' />

						</div>
						<div class='card-content'>
							<a><h5 class='card-title'>`+player.name+`</h5></a>
							<div class='card-content-inner'>
								`+player.age+`<span style='margin-right: 65px;' class='card-text' >years</span> <span
									class='card-text' >`+player.gender+`</span>
							</div>
						</div>

					</div>`;

					$(afterSuccessElement).append(player);

				});
			}

		}
	});
}
