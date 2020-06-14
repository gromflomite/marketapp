<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Create or edit product" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="createEditProduct" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<div class="container">	

	<h2 class="my-4">Create or edit a product</h2>	

	<div class="jumbotron">
		<div class="container">
			<ul>
				<li>User is not able to edit the ID field</li>
				<li>Initial number 0 in ID if user comes here via "Create new product" from index</li>
				<li>ID number field filled with value from the DB if user uses "Edit" option from "See products table"</li>
			</ul>
		</div>
	</div>

	<form action="new-edit-product" method="post">

		<div class="form-group">
			<label for="id">Id:</label>
			<input type="text" class="form-control" name="id" id="id" value="${product.id}" placeholder="Id" readonly>
		</div>

		<div class="form-group">
			<label for="productName">Product name:</label>
			<input type="text" name="productName" class="form-control" id="productName" value="${product.name}" placeholder="Product name">
		</div>

		<div class="form-group">
			<label for="productPrice">Product price:</label>			
			<input type="text" name="productPrice" class="form-control" id="productPrice" value="${product.price}" placeholder="Product price">
		</div>

		<div class="form-group">
			<label for="productImage">Image URL:</label>
			<input type="text" name="productImage" class="form-control" id="productImage" value="${product.image}" placeholder="Product URL image">
		</div>

		<button type="submit" class="btn btn-primary">Save product</button>

	</form>

	<br>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />