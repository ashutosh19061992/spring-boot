<jsp:include page="base.jsp" />
<div class='container mt-4'>
	<div class="card text-center">
		<div class="card-body" style="background-color: #d7dcf1;">
			<div class='row'
				style="border-width: 1; border-style: groove; border-color: #babaf5; background-color: aliceblue; padding-top: 16px; border-radius: 5px;">
				<form action="upload-csv-data" method="post"
					enctype="multipart/form-data">
					<div class="col-sm-10">
						<input type="file" name="file" class="form-control-file"
							id="exampleFormControlFile1">
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary mt-2">Upload
							Data</button>
					</div>
				</form>
			</div>

			<div class="row"
				style="border-width: 1; border-style: groove; border-color: #babaf5; background-color: aliceblue; padding-top: 16px; border-radius: 5px;">
				<form action="downloadCSV" method="get">
					<div class="container">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-danger mt-2">download</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>