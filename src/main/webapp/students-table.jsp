<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Users table" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="studentsTable" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@page import="marketapp.model.User"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class=container>

	<h2 class="my-4">Users table</h2>

	<table id="table" class="tabla table table-striped">

		<thead>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Role</td>
				<td>Photo</td>
				<td>Options</td>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${students}" var="s">
				
				<tr>
					<td>${s.id}</td>

					<td>${s.name}</td>
					
					<td>${s.role.name} (${s.role.id})</td>

					<td>
						<img src="${s.image}" alt="User image">
					</td>

					<td>
						<a href="new-edit-student?id=${s.id}" class="mr-4">
							<i class="far fa-edit fa-1x" title="Edit"></i>
						</a>
						<a href="DeleteStudent?id=${s.id}">
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