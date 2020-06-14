<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>

	<!-- Common navbar to all users (logged or not) -->
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<img id="logo" class="navbar-brand" src="img/logo.png" alt="MarketApp logo">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">

				<!--  Check if the user is logged to show the correct navbar items -->
				<!--  NOT LOGGED -->
				<c:if test="${empty userLogin}">

					<li class="nav-item ${ ('home' eq param.activeTag) ? 'active' : '' }">
						<a class="nav-link" href="index.jsp">Home</a>
					</li>					

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Other sections </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="curriculum.jsp">CV form</a>
							<a class="dropdown-item" href="get-post.jsp">Get &amp; Post diff</a>
							<a class="dropdown-item" href="MyFirstServlet">Servlet example</a>
							<a class="dropdown-item" href="uf2406exercice1/apartadoA.jsp">UF2406 - 02-06-2020 - A</a>
							<a class="dropdown-item" href="uf2406exercice1/apartadoB.jsp">UF2406 - 02-06-2020 - B</a>
						</div>
					</li>

				</c:if>

				<!-- LOGGED -->
				<c:if test="${not empty userLogin}">

					<li class="nav-item ${ ('home' eq param.activeTag) ? 'active' : '' }">
						<a class="nav-link" href="index.jsp">Home</a>
					</li>

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">Users</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="students-table">Users table</a>
							<a class="dropdown-item" href="new-edit-student?id=0">Create user</a>
						</div>
					</li>


					<!--------------------------- Keeping this code for future references of dinamic active tag --------------------------->
					<%-- 					<li class="nav-item ${ ('studentsTable' eq param.activeTag) ? 'active' : '' }"> --%>
					<!-- 						<a class="nav-link" href="students-table">Users table</a> -->
					<!-- 					</li> -->

					<%-- 					<li class="nav-item ${ ('createEditUser' eq param.activeTag) ? 'active' : '' }"> --%>
					<!-- 						We are forcing a parameter 0 to use it in the controller doGet method -->
					<!-- 						<a class="nav-link" href="new-edit-student?id=0">Create user</a> -->
					<!-- 					</li> -->

					<%-- 					<li class="nav-item ${ ('productsTable' eq param.activeTag) ? 'active' : '' }"> --%>
					<!-- 						<a class="nav-link" href="product-table">Products table</a> -->
					<!-- 					</li> -->

					<%-- 					<li class="nav-item ${ ('createEditProduct' eq param.activeTag) ? 'active' : '' }"> --%>
					<!-- 						<a class="nav-link" href="new-edit-product?id=0">Create product</a> -->
					<!-- 					</li> -->

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">Products</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="product-table">Products table</a>
							<a class="dropdown-item" href="new-edit-product?id=0">Create product</a>
						</div>
					</li>

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Other sections </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="curriculum.jsp">CV form</a>
							<a class="dropdown-item" href="get-post.jsp">Get &amp; Post diff</a>
							<a class="dropdown-item" href="MyFirstServlet">Servlet example</a>
							<a class="dropdown-item" href="uf2406exerc1/apartadoA.jsp">UF2406 - 02-06-2020 - A</a>
							<a class="dropdown-item" href="uf2406exerc1/apartadoB.jsp">UF2406 - 02-06-2020 - B</a>
						</div>
					</li>

				</c:if>
				<!--  End logged check block -->

			</ul>

			<!-- User session button common to all users (logged or not) ------------------------------>
			<span class="form-inline">
				<c:if test="${empty userLogin}">
					<a class="nav-link btn btn-outline-primary" href="login.jsp">Log in</a>
				</c:if>

				<c:if test="${not empty userLogin}">
					<a href="new-edit-student?id=${userLogin.id}" class="badge badge-warning mr-3 p-2">${userLogin.name}</a>					
					<a class="nav-link btn btn-outline-danger" href="logout">Log out</a>
				</c:if>
			</span>
			<!-- User session button end ------------------------------------------------------------->

		</div>

	</nav>

	<!-- Including the .jsp to show feedback -->
	<jsp:include page="feedback.jsp"></jsp:include>

</header>