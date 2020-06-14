<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="CV form" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="cvForm" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<br>

	<h1>Curriculum form</h1>

	<hr>

	<c:if test="${not empty validations}">
		<div style="padding: 20px; background-color: #DDD; color: #000;">
			<ol>
				<c:forEach items="${validations}" var="validationError">
					<li>${validationError}</li>
				</c:forEach>
			</ol>
		</div>
	</c:if>

	<div class="form">

		<form novalidate action="CvResume" method="post">

			<label for="name">Name:</label>

			<br>

			<input type="text" name="name" id="name" value="${name}" required autofocus>

			<br>
			<br>

			<label for="surname">Surname:</label>

			<br>

			<input type="text" name="surname" id="surname" value="${surname}" required>

			<br>
			<br>

			<label for="age">Age:</label>

			<br>

			<input type="number" min="16" step="1" name="age" id="age" size="3" maxlength="3" value="${age}">

			<br>

			<br>

			<label for="studies">Studies:</label>
			<select name="studies" id="studies">
				<option value="u" ${ ( studies eq "University" ) ? "selected" : "" }>University</option>
				<option value="v" ${ ( studies eq "Vocational training" ) ? "selected" : "" }>Vocational training</option>
				<option value="other" ${ ( studies eq "Others" ) ? "selected" : "" }>Other</option>
			</select>

			<br>

			<br>

			<fieldset>
				<legend>Shift:</legend>

				<input type="checkbox" id="fulltime" name="shift" value="ft">
				<label for="fulltime"> Full time</label>
				<br>
				<input type="checkbox" id="parttime" name="shift" value="pt">
				<label for="parttime"> Part time</label>
				<br>
				<input type="checkbox" id="all" name="shift" value="all" checked>
				<label for="all"> All</label>
				<br>
			</fieldset>

			<br>

			<fieldset>
				<legend>Gender:</legend>

				<input type="radio" id="male" name="gender" value="m">
				<label for="male">Male</label>
				<br>
				<input type="radio" id="female" name="gender" value="f">
				<label for="female">Female</label>
				<br>

				<input type="radio" id="other" name="gender" value="other" checked>
				<label for="other">Other</label>
				<br>
			</fieldset>

			<br>
			<label for="comments">Comments:</label>
			<br>
			<textarea name="comments" placeholder="Enter your comments" id="comments"></textarea>
			<br>
			<br>
			<input type="submit" value="Send">
			<br>
			<br>

		</form>

	</div>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />