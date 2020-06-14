<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Get and Post diff" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="getPost" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<div class="container">

	<br>

	<h1>Differences between GET and POST</h1>

	<hr>

	<h2>GET</h2>

	<p>Se lanzarán desde un enlace.</p>
	<p>Se puede enviar desde un formulario cambiando method.</p>
	<p>
		Se usa generalmente para solicitar información al servidor (aunque en algún
		<a href="see-students-table">ejemplo</a>
		lo usamos para elimininar registros en BD).
	</p>

	<p>Forzamos el envío de los dos parámetros que necesita el controlador mediante hardcoded en el enlace:</p>

	<p>Se enviará:
	<pre>a href="sum?operand1=25&amp;?operand2=13"</pre>
	</p>


	<br>

	<span style="font-weight: 700;">
		<a href="sum?operand1=25&operand2=13">Sum 25 + 13</a>
	</span>

	<br>

	<br>

	<hr>

	<h2>POST</h2>

	<p>Siempre será necesario un formulario.</p>
	<p>Las peticiones POST generalmente sirven para crear o modificar recursos en el servidor.</p>

	<form action="sum" method="post">
		<input type="number" name="operand1" value="${operand1}" placeholder="Enter a number">
		<br>
		<input type="number" name="operand2" value="${operand1}" placeholder="Enter a second number">
		<br>
		<input type="submit" value="Sum">
	</form>

	<hr>

	<h2>Result</h2>

	<br>

	<span style="color: red; font-weight: 700;">${errorfeedback}</span>

	${donewith} ${result}

	<br>

	<br>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />