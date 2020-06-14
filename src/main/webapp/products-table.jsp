<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Products table" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="productsTable" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@page import="marketapp.model.Product"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class=container>

	<h2 class="my-4">Products table</h2>

	<p>${feedback}</p>

	<table id="table" class="tabla table table-striped">

		<thead>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Price</td>
				<td>Image</td>
				<td>Options</td>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${products}" var="p">
				<tr>

					<%
						// p.id == p.getId() NOT needed because we are using EL
					%>

					<td>${p.id}</td>

					<td>${p.name}</td>

					<td>${p.price} &euro;</td>

					<td><img src="${p.image}" alt="Product image"></td>

					<td>
						<a href="new-edit-product?id=${p.id}" class="mr-4">
							<i class="far fa-edit fa-1x" title="Edit"></i>
						</a>
						<a href="DeleteProduct?id=${p.id}">
							<i class="fas fa-trash fa-1x" title="Delete"></i>
						</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>

	<hr>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />