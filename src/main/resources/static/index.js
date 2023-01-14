$(document).ready(function() {
	console.log("document loaded");
	$("#buttonAPI").click(function() {
		$.post("/callcoindesk", {}, (result) => {
			$("#formControlTextarea1").text(result[0]);
			$("#formControlTextarea2").text(result[1]);
		}).fail(function(result) {
			console.log(result.responseText);
		});
	});
	$("#buttonQry").click(function() {
		$.post("/queryData", {}, (result) => {
			$("#formControlTextarea3").text(result);
		}).fail(function(result) {
			console.log(result.responseText);
		});
	});
});
