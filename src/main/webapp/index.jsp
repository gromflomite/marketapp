<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="home" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->
<div class=container>

	<c:if test="${not empty userLogin}">

		<br>
		<br>

		<h1 class="mb-5">JSP - JSTL - Expression Language (EL)</h1>

		<hr>

		<h3>Insert Java code in HTML</h3>

		<%
			// Java code
		out.print("<span> This line has been created using a Java statement </span>");
		%>

		<hr>

		<h3>User CRUD</h3>

		<a href="students-table">See users table (delete and edit options)</a>

		<br>

		<!-- We are forcing a parameter 0 to use it in the controller doGet method -->
		<a href="new-edit-student?id=0">Create new user</a>

		<span style="color: green; font-weight: 700;">(pay attention to this code)</span>

		<br>

		<hr>

		<h3>Product CRUD</h3>

		<a href="product-table">See products table (delete and edit options)</a>

		<br>

		<!-- We are forcing a parameter 0 to use it in the controller doGet method -->
		<a href="new-edit-product?id=0">Create new product</a>

		<span style="color: green; font-weight: 700;">(pay attention to this code)</span>

		<hr>

		<h3>Passing parameters to controller from view and returning attributes back to another view</h3>

		<a href="curriculum.jsp">Curriculum form</a>

		<br>

		<hr>

		<h3>Hodgepodge</h3>

		<a href="get-post.jsp">Differences between GET and POST</a>

		<br>

		<a href="MyFirstServlet">My first servlet</a>

		<br>

		<hr>

		<br>

	</c:if>

	<c:if test="${empty userLogin}">
	
		<div class="jumbotron mt-5">
			<h1 class="display-4">Welcome to MarketApp!</h1>
			
			<p class="lead">Start session to see all the contents</p>
			
			<hr class="my-4">	
						
			<p class="lead">
				<a class="btn btn-primary btn-lg" href="login.jsp" role="button">Log in</a>
			</p>		
		</div>

	</c:if>

</div>

<br>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />