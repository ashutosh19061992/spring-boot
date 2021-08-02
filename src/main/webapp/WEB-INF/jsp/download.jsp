<jsp:include page="base.jsp" />
<div class='container mt-4'>
	<div class="card text-center">
		<div class="card-body" style="background-color: #d7dcf1;"
			id="download_card">
			<div class='row'
				style="border-width: 1; border-style: groove; border-color: #babaf5; background-color: aliceblue; padding-top: 16px; border-radius: 5px;">
				<form action="exportToCSV" method="post" class="form-inline" id="download_data">
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" id="inputTableName"
							placeholder="table-name">
					</div>
					<button id="export" type="submit" class="btn btn-primary mb-2">ExportToCSV</button>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script>
	$(document).ready(function() {

		$("#download_data").submit(function() {
			var parentElement = $("div #download_card");
			var inputVal = $(parentElement).find("input#inputTableName").val();
			$.ajax({
				url : $("#download_data").attr("action"),

				type : "post",
				dataType : "json",
				data : {
					"search_string" : inputVal
				},
				success : function(data) {
					console.log(data);
					alert(data[0]);
					console.log(data["response"]);
				}
			});
		});
	});

	function download_file(data, fileName) {
		var blob = new Blob([ data["st_export_data"] ]);
		var link = document.createElement('a');
		link.href = window.URL.createObjectURL(blob);
		link.download = fileName;
		link.click();
	}
</script>