<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta name="description" content="Raúl - UF2406 - Ejercicio práctico 1 - 02-06-2020">

<meta charset="UTF-8">

<!--  CSS links -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">

<title>UF2406 - Ejercicio práctico 06-02-2020 - Apartado A</title>

</head>

<body>

	<div class="container">

		<div class="jumbotron mt-5">
			
			<h1>Apartado A - Petición de datos</h1>

			<form action="apartado-a" method="post">

				<div class="form-group">
					<label for="userName">Nombre de usuario:</label>
					<input type="text" name="userName" class="form-control" id="userName" placeholder="Nombre de usuario">
				</div>

				<div class="form-group">
					<label for="favouriteColor">Tu color favorito:</label>
					<select class="form-control" name="favouriteColor" id="favouriteColor">
						<option value="Rojo">Rojo</option>
						<option value="Verde">Verde</option>
						<option value="Azul">Azul</option>
						<option value="Negro">Negro</option>
					</select>
				</div>

				<button type="submit" class="btn btn-primary mt-3">Enviar datos</button>

			</form>
		
		</div>

	</div>
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>