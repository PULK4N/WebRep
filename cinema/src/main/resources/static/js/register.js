
$(document).on("submit","#registerForm",function(event){
	event.preventDefault();
	
	var active = true;
	var username=$("#inputRegUsername").val();
	var email=$("#inputEmail").val();
	var password=$("#inputRegPassword").val();	
	var password2=$("#repeatRegPassword").val();
	var firstName = $("#inputFirstName").val();
	var lastName = $("#inputLastName").val();
	var phoneNumber = $("#inputPhoneNumber").val();
	var dateOfBirth = $("#inputDateOfBirth").val();
	var role = $("#inputRole").val();
	if (password != password2){
		alert("Both passwords must be equal");
		return;
	}
	var newUserJSON=formToJSON(username,password,firstName,lastName,email,phoneNumber,dateOfBirth,role,active);
	console.log(newUserJSON);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/register-user",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Registration succesful, you can login now");
			$("#registerHTML").attr("hidden",true);
			$("#loginHTML").attr("hidden",false);
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		}
	});	
  });

$(document).on("submit","#adminForManagersForm",function(event){
	event.preventDefault();
	
	var active = true;
	var username=$("#inputManagerUsername").val();
	var email=$("#inputManagerEmail").val();
	var password=$("#inputManagerPassword").val();	
	var firstName = $("#inputManagerFirstName").val();
	var lastName = $("#inputManagerLastName").val();
	var phoneNumber = $("#inputManagerPhoneNumber").val();
	var dateOfBirth = $("#inputManagerDateOfBirth").val();
	var role = $("#inputRole").val();
	var newUserJSON=formToJSON(username,password,firstName,lastName,email,phoneNumber,dateOfBirth,role,active);
	console.log(newUserJSON);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/register-manager",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Manager registered succesfully");
			$(".managersToRemove").remove();
			console.log("SUCCESS : ", data);
			for( i = 0; i< data.length; i++){
				var row = '<tr class="managersToRemove">';  
				row += "<td>" + data[i]['username'] + "</td>";
				row += "<td>" + data[i]['firstName'] + "</td>";
				row += "<td>" + data[i]['lastName'] + "</td>";
				row += "<td>" + data[i]['cinemaName'] + "</td>";
				row += "<td>" + data[i]['active'] + "</td>";
				$('#managers').append(row);                        
			}

			$.ajax({
				type:"GET",
				url:"http://localhost:8080/api/managers",
				dataType:"json",
				success:function(data){
					console.log("SUCCESS: ",data);
					alert("Managers listed succesfully");
					$(".managersToRemove").remove();
					for( i = 0; i< data.length; i++){
						var row = '<tr class="managersToRemove">';  
						row += "<td>" + data[i]['username'] + "</td>";
						row += "<td>" + data[i]['firstName'] + "</td>";
						row += "<td>" + data[i]['lastName'] + "</td>";
						row += "<td>" + data[i]['cinemaName'] + "</td>";
						row += "<td>" + data[i]['active'] + "</td>";
						$('#managers').append(row);     
										
					}	
				},
				error:function(data){
					console.log(data);
					alert("Username probably already exists, try another one");
				}
			});	
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		}
	});	
});

$(document).on("submit","#deleteManagerForm",function(event){
	event.preventDefault();
	
	var username=$("#inputManagerForDeletion").val();
	var newUserJSON=formToJSON(username,"","","","","","","Manager","");
	console.log(newUserJSON);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/delete-manager",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Manager deleted succesfully");
			$(".managersToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="managersToRemove">';  
				row += "<td>" + data[i]['username'] + "</td>";
				row += "<td>" + data[i]['firstName'] + "</td>";
				row += "<td>" + data[i]['lastName'] + "</td>";
				row += "<td>" + data[i]['cinemaName'] + "</td>";
				row += "<td>" + data[i]['active'] + "</td>";

				$('#managers').append(row);     
				                
			}
			getCinemas();
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		}
	});	

});


function formToJSON(username,password,firstName,lastName,email,phoneNumber,dateOfBirth,role,active){
   return JSON.stringify({
		"username":username,
		"password":password,
		"firstName":firstName,
		"lastName":lastName,
		"email":email,
		"dateOfBirth":dateOfBirth,
		"phoneNumber":phoneNumber,
		"role":role,
		"active": active
   });
}

$(document).on("submit","#createChangeCinemaForm",function(event){
	event.preventDefault();
	
	var Name=$("#inputCinemaName").val();
	var NewName=$("#inputCinemaNewName").val();
	var Address=$("#inputCinemaAddress").val();
	var Email=$("#inputCinemaEmail").val();
	var PhoneNumber=$("#inputCinemaPhoneNumber").val();
	var Manager=$("#inputCinemaManager").val();
	var newCinemaJSON=cinemaFormToJson(Name,NewName,Address,Email,PhoneNumber,Manager);
	console.log("Data being sent");
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/register-cinema",
		dataType:"json",
		contentType:"application/json",
		data:newCinemaJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			$(".cinemasToRemove").remove();
			console.log(data.length);
			for( i = 0; i< data.length; i++){
				var row = '<tr class="cinemasToRemove">';  
				row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "<td>" + data[i]['phoneNumber'] + "</td>";
				row += "<td>" + data[i]['manager'] + "</td>";
				console.log(row);
				console.log($('#cinemas'));
				$('#cinemas').append(row);                        
			}
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		}
	});	
});

$(document).on("submit","#deleteCinemaForm",function(event){
	event.preventDefault();
	
	var Name=$("#inputCinemaForDeletion").val();
	var newCinemaJSON=cinemaFormToJson(Name,"","","","","");
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/delete-cinema",
		dataType:"json",
		contentType:"application/json",
		data:newCinemaJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			$(".cinemasToRemove").remove();
			console.log(data.length);
			for( i = 0; i< data.length; i++){
				var row = '<tr class="cinemasToRemove">';  
				row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "<td>" + data[i]['phoneNumber'] + "</td>";
				row += "<td>" + data[i]['manager'] + "</td>";
				console.log(row);
				console.log($('#cinemas'));
				$('#cinemas').append(row);        
			}
			getManagers();
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		}
	});	
});

function cinemaFormToJson(name,newName,address,email,phoneNumber,manager){
	return JSON.stringify({
		 "name":name,
		 "newName":newName,
		 "address":address,
		 "email":email,
		 "phoneNumber":phoneNumber,
		 "manager":manager
	});
}

function getCinemas(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/cinemas",
		dataType:"json",
		success:function(data){
			$(".cinemasToRemove").remove();
			console.log(data.length);
			for( i = 0; i< data.length; i++){
				var row = '<tr class="cinemasToRemove">';  
				row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "<td>" + data[i]['phoneNumber'] + "</td>";
				row += "<td>" + data[i]['manager'] + "</td>";
				console.log(row);
				console.log($('#cinemas'));
				$('#cinemas').append(row);                        
			}
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		},

	});
}