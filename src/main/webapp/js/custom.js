// Datatables plugin JS
// Ejecuta la funcion cuando todo el documento de html DOM este listo y cargado
$(document).ready(function() {
	// Seleccion por id => #example y ejecuta el plugin .DataTable();
	$('#table').DataTable();
});

// Password hash cipher function
function cipherPassword() {
	
	// Retrieve password from form
	var password = document.getElementById('password').value;

	// Use the library to get the password hash
	var passwordHash = md5(password);

	// Save the hash and send back
	document.getElementById('password').value = passwordHash;

	// USER EDIT SCENARIO - Check if the user wants to change the password
	var newPassword = document.getElementById('passwordChangeConfirm');
	
	if (newPassword){
	 
	 var newPasswordHash = md5(newPassword.value);	 
	 
	 document.getElementById('passwordChangeConfirm').value =  newPasswordHash;	 
	} 
	
	// Send the form
	return true; // If not true, the form will not be sended
}