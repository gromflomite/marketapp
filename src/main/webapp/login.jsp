<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="home" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<br>

<div class="container">

	<h2 class="my-4">Log in</h2>

	<form action="login" method="post" onsubmit="cipherPassword()">

		<div class="form-group">
			<label for="username">Username:</label>
			<input type="text" name="user" class="form-control" id="username" placeholder="Enter your username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
		</div>

		<div class="form-group">
			<label for="language">Language:</label>
			<select class="form-control" name="selectedLang" id="language">
				<option value="en">English</option>
				<option value="es">Castellano</option>
				<option value="eu">Euskera</option>
			</select>
		</div>

		<div class="form-check">
			<input type="checkbox" name="rememberMe" class="form-check-input" id="rememberMe">
			<label class="form-check-label" for="rememberMe">Remember me (if checked, we will not delete your cookies on logout)</label>
		</div>

		<button type="submit" class="btn btn-primary mt-3">Log in</button>

	</form>

	<br>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />