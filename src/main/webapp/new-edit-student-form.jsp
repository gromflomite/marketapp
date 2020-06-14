<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Create or edit user" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="createEditUser" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">	

	<h2 class="my-4">Create or edit a user</h2>	

	<div class="jumbotron">
		<div class="container">
			<ul>
				<li>User is not able to edit the ID field</li>
				<li>Initial number 0 in ID if user comes here via "Create new user" from index</li>
				<li>ID number field filled with value from the DB if user uses "Edit" option from "See users table"</li>
			</ul>
		</div>
	</div>

	<form action="new-edit-student" method="post" onsubmit="cipherPassword()">

		<div class="form-group">
			<label for="id">Id:</label>
			<input type="text" class="form-control" name="id" id="id" value="${student.id}" placeholder="Id" readonly>
		</div>

		<div class="form-group">
			<label for="studentName">User name:</label>
			<input type="text" class="form-control" name="studentName" id="studentName" value="${student.name}" placeholder="User name" required>
		</div>

		<!-- If new user (id == 0) show just the initial password field -->
		<c:if test="${student.id == 0}">

			<div class="form-group">
				<label for="password">Password:</label>
				<!--  id used by JS cipher, name by Java controller -->
				<!-- Using the same id (for JS cipher) than below because just one password field will appear in user view (because if's) -->
				<input type="password" name="password" id="password" value="" class="form-control" placeholder="Password" required>
			</div>

		</c:if>

		
		<div class="form-group">
			<label for="idRol">Role:</label>
			<select class="form-control" name="idRol" id="idRol">
				<!-- Getting the role id value and using ternary operators to add "selected" tag in the select form -->				
				<option value="1" ${ ( 1 == student.role.id ) ? "selected" : "" }>1 (User)</option>
				<option value="2" ${ ( 2 == student.role.id ) ? "selected" : "" }>2 (Admin)</option>
			</select>
		</div>

		<div class="form-group">
			<label for="image">Image URL:</label>
			<input type="text" class="form-control" name="image" id="image" value="${student.image}" placeholder="User image URL" required>
		</div>

		<!-- If editing user (id == user id on DB) show the option to change password -->
		<c:if test="${student.id != 0}">

			<a class="btn btn-link ml-0 pl-0 mb-3" data-toggle="collapse" href="#passwordChange" role="button">Change password</a>

			<br>

			<div class="collapse" id="passwordChange">

				<div class="form-group">
					<label for="passwordChange">New password:</label>
					<!--  id used by JS cipher, name by Java controller -->
					<!-- Using the same id (for JS cipher) than above because just one password field will appear in user view (because if's) -->
					<input type="password" class="form-control" name="passwordChange" id="password" value="" placeholder="New password">
				</div>

				<div class="form-group">
					<label for="passwordChangeConfirm">Confirm new password:</label>
					<!--  id used by JS cipher, name user by Java controller -->
					<input type="password" class="form-control" name="passwordChangeConfirm" id="passwordChangeConfirm" value="" placeholder="New password confirmation">
				</div>

			</div>

		</c:if>

		<button type="submit" class="btn btn-primary">Save user</button>

	</form>

	<br>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />