<!-- Setting the title -->
<%!String title = "CV resume";%>

<!-- Getting the <head> -->
<%@include file="includes/head.jsp"%>

<!-- Getting the <header> -->
<%@include file="includes/header.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<br>

	<h1>Curriculum resume</h1>

	<hr>

	<h3>Name:</h3>${name}

	<h3>Surname:</h3>${surname}

	<h3>Age:</h3>${age}

	<h3>Studies:</h3>${studies}

	<h3>Shifts:</h3>

	<%
		ArrayList<String> shifts = (ArrayList<String>) request.getAttribute("shiftsSelected");

	for (String shift : shifts) {
	%>

	<p>
		<%=shift%>
	</p>

	<%
		}
	%>

	<h3>Gender:</h3>${gender}

	<h3>Comments:</h3>${comments}

	<br>

</div>

<!-- Getting the <footer> -->
<%@include file="includes/footer.jsp"%>

<!-- Getting the foot -->
<%@include file="includes/foot.jsp"%>