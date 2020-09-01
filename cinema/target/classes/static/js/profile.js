// $(document).on("submit","#editProfileForm",function(event){
// 	event.preventDefault();
// 	firstname=$("#changeFirstName").val();
// 	lastname=$("#changeLastName").val();
// 	password=$("#changePassword").val();
// 	repeatPassword = $("#repeatChangePassword").val();
// 	email=$("#changeEmail").val();
// 	phoneNumber = $("#phoneNumber");
// 	dateOfBirth = $("#dateOfBirth");
// 	if(repeatPassword != password){
// 		alert("Passwords are not equal!");
// 		return;
// 	}
// 	var newUserJSON=formToJSON(username,password,firstname,lastname,email,phoneNumber,dateOfBirth);	
// 	$.ajax({
// 		type:"POST",
// 		url:"http://localhost:8080/api/change-user",
// 		dataType:"json",
// 		contentType:"application/json",
// 		data:newUserJSON,
// 		success:function(data){
// 			console.log("SUCCESS: ",data);
		
// 		},
// 		error:function(data){
// 			alert("Error while changing profile");
// 		}
// 	});	
// });

// function formToJSON(username,password,firstName,lastName,email,phoneNumber,dateOfBirth){
// 	return JSON.stringify({
// 		"username":username,
// 		"password":password,
// 		"firstName":firstName,
// 		"lastName":lastName,
// 		"email":email,
// 		"phoneNumber":phoneNumber,
// 		"dateOfBirth":dateOfBirth
// 	});
// }