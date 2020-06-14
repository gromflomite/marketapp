<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta name="description" content="Raúl - UF2406 - Ejercicio práctico 1 - 02-06-2020">

<meta charset="UTF-8">

<!--  CSS links -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">

<title>UF2406 - Ejercicio práctico 06-02-2020 - Apartado B</title>

</head>

<body>

	<div class="container">

		<div class="jumbotron mt-5">

			<h2 class="display-4">Resultado B - Datos</h2>
			
			<h2 class="mt-3">Extrayendo los datos desde la session</h2>

			<h3 class="mt-5">Hora de inicio: ${loginTime}</h3>

			<hr class="my-4">

			<h3 class="mt-5">Nombre de usuario: ${userName}</h3>

			<hr class="my-4">

			<h3 class="mt-5">Navegador utilizado: ${header['user-agent']}</h3>

		</div>

	</div>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>

</html>