var username;
var password;
var CinemaNames = [];
var role;

$(document).ready(function(){
    $("#managersButton").click(function (){
		getManagers();
		getCinemas();
	});
	$("#reservedTicketsButton").click(function (){
		getMyReservedTickets();
		console.log("reservedTicketsButton clicked");
	});

	$("#cinemasButton").click(function(){
		getMovies();
		getManagerCinemas();
		getManagerAuditoriums();
	});

	$("#watchedMoviesButton").click(function(){
		getViewerScores();
	});
	

});

$(document).on("submit","#createProjectionForm",function(event){
	event.preventDefault();
	
	var scheduledTime = $("#inputProjectionDateTime").val();
	var movieName = $("#selectMovieAuditorium").val();
 	var price=$("#inputProjectionPrice").val();
	var auditoriumId=$("#selectAuditoriumprojectionCreate").val();	
 	var newProjectionJSON=formProjectionCreateToJSON("",price,scheduledTime,"",movieName,auditoriumId);
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/register-projection",
 		dataType:"json",
 		contentType:"application/json",
 		data:newProjectionJSON,
 		success:function(data){
			getManagerAuditoriums();
			getManagerProjections();
 		},
 		error:function(data){
			console.log("alert");
 		}
 	});	
});

$(document).on("submit","#changeProjectionForm",function(event){
	event.preventDefault();
	
	var id = $("#selectIdChangeProjection").val();
	var scheduledTime = $("#inputProjectionDateTimeChange").val();
 	var newProjectionJSON=formProjectionCreateToJSON(id,"",scheduledTime,"","","");
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/change-projection",
 		dataType:"json",
 		contentType:"application/json",
 		data:newProjectionJSON,
 		success:function(data){
			getManagerAuditoriums();
			getManagerProjections();
 		},
 		error:function(data){
			console.log("alert");
 		}
 	});	
});

$(document).on("submit","#deleteProjectionForm",function(event){
	event.preventDefault();
	
	var id = $("#selectIdDeleteProjection").val();
 	var newProjectionJSON=formProjectionCreateToJSON(id,"","","","","");
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/delete-projection",
 		dataType:"json",
 		contentType:"application/json",
 		data:newProjectionJSON,
 		success:function(data){
			getManagerAuditoriums();
			getManagerProjections();
 		},
 		error:function(data){
			console.log("alert");
 		}
 	});	
});

$(document).on("submit","#reserveTicket",function(event){
	event.preventDefault();
	var projectionId = $("#selectReserveTicket").val();
	var newProjectionJSON=formReserveTicketCreateToJson(projectionId,username,password);
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/reserve-ticket",
 		dataType:"json",
 		contentType:"application/json",
 		data:newProjectionJSON,
 		success:function(data){
			alert("card reserved succesfully");
			getMyReservedTickets();
 		},
 		error:function(data){
			console.log("alert");
		}
	});	
});

$(document).on("submit","#cancelReservation",function(event){
	event.preventDefault();
	var projectionId = $("#selectReserveTicketToCancel").val();
	if(projectionId == null){
		return;
	}
	var newProjectionJSON=formReserveTicketCreateToJson(projectionId,username,password);
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/cancel-reserve-ticket",
 		dataType:"json",
 		contentType:"application/json",
 		data:newProjectionJSON,
 		success:function(data){
			alert("canceled succesfully");
			getMyReservedTickets();
 		},
 		error:function(data){
			console.log("alert");
		}
	});	
});

$(document).on("submit","#scoreMovieForm",function(event){
	event.preventDefault();
	var scoreId = $("#selectMovieToScore").val();
	var movieScore = $("#selectValueOfAScore").val();
	var newScoreJSON=JSON.stringify({
		"username":username,
		"password":password,
		"movieScore":movieScore,
		"scoreId":scoreId
	});
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/score-a-movie",
 		dataType:"json",
 		contentType:"application/json",
 		data:newScoreJSON,
 		success:function(data){
			alert("scored succesfully");
			getViewerScores();
 		},
 		error:function(data){
			console.log("alert");
		}
	});	
});

$(document).on("submit","#buyTicketForm",function(event){
	event.preventDefault();
	var projectionId = $("#selectReserveTicketToBuyTicket").val();

	if(projectionId == null){
		return;
	}
	var newBuyTicketJson=JSON.stringify({
		"username":username,
		"password":password,
		"projectionId":projectionId
	});
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/create-score",
 		dataType:"json",
 		contentType:"application/json",
 		data:newBuyTicketJson,
 		success:function(data){
			alert("canceled succesfully");
			getMyReservedTickets();
 		},
 		error:function(data){
			console.log("alert");
		}
	});	
});


$(document).on("submit","#createAuditoriumForm",function(event){
	event.preventDefault();
		
 	var capacity=$("#inputAuditoriumCapacity").val();
	var cinemaName=$("#selectAuditoriumCinema").val();	
 	var newAuditoriumJSON=formAuditoriumToJSON(cinemaName,capacity);	
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/register-auditorium",
 		dataType:"json",
 		contentType:"application/json",
 		data:newAuditoriumJSON,
 		success:function(data){
			getManagerAuditoriums();
			getManagerProjections();
 		},
 		error:function(data){
			console.log("alert");
 		}
 	});	
 });

 $(document).on("submit","#deleteAuditoriumForm",function(event){
	event.preventDefault();
		
	var id=$("#selectAuditoriumDelete").val();	
 	var newAuditoriumJSON=formAuditoriumToDeleteJSON("","",id);	
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/delete-auditorium",
 		dataType:"json",
 		contentType:"application/json",
 		data:newAuditoriumJSON,
 		success:function(data){
			getManagerAuditoriums();
			getManagerProjections();
 		},
 		error:function(data){
			console.log("alert");
 		}
 	});	
 });

 $(document).on("submit","#login",function(event){
	event.preventDefault();
 	username=$("#inputUsername").val();
 	password=$("#inputPassword").val();	
 	var newUserJSON=formToJSON(username,password);	
 	$.ajax({
 		type:"POST",
 		url:"http://localhost:8080/api/get-user",
 		dataType:"json",
 		contentType:"application/json",
 		data:newUserJSON,
 		success:function(data){
			role = data['role'];
			$("#indexHTML").attr("hidden",false);
			$("#loginButton").attr("hidden",true);
			$("#loginHTML").attr("hidden",true);
			if(data['role'] == "Viewer"){
				$("#profileButton").attr("hidden",false);
				$("#reservedTicketsButton").attr("hidden",false);
				$("#watchedMoviesButton").attr("hidden",false);
				$("#profileName").append(data['username']);
				$("#username").append(data['username']);
				$('#password').append(data['password']);
		    	$('#fullName').append(data['firstName'] + " " + data['lastName']);
		    	$('#profileEmail').append(data['email']);
		    	$('#profilePhoneNumber').append(data['phoneNumber']);
		    	$('#profileRole').append(data['role']);
		    	$('#birthDate').append(data['dateOfBirth']);
			}else if(data['role'] == "Manager"){
				$("#cinemasButton").attr("hidden",false);
			}
			else{
				$("#managersButton").attr("hidden",false);
				getManagers();
			}

 		},
 		error:function(data){
 			alert("Wrong username or password");
 		}
 	});	
 });

function formToJSON(username,password){
	return JSON.stringify({
		"username":username,
		"password":password
	});
}

function formAuditoriumToJSON(cinemaName,capacity){
	return JSON.stringify({
		"capacity":capacity,
		"cinemaName":cinemaName,
	});
}
function formAuditoriumToDeleteJSON(cinemaName,capacity,id){
	return JSON.stringify({
		"capacity":capacity,
		"cinemaName":cinemaName,
		"id":id
	});
}

function formProjectionCreateToJSON(id,price,scheduledTime,reservedCards,movieName,auditoriumId){
	return JSON.stringify({
		"id":id,
		"price":price,
		"scheduledTime":scheduledTime,
		"reservedCards":reservedCards,
		"movieName":movieName,
		"auditoriumId":auditoriumId
	});
}

function formReserveTicketCreateToJson(projectionId,username,password){
	return JSON.stringify({
		"projectionId":projectionId,
		"username":username,
		"password":password
	});
}

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



function getMovies(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/get-movies",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS : ", data);
			Movies = data;
			$(".moviesToRemove").remove();
			var row;
			for(var i=0; i<data.length; i++){
				row += '<option class="moviesToRemove" value="' + data[i]['name'] + '">' + data[i]['name'] + '</option>';
			}
			$("#selectMovieAuditorium").append(row);
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
}
function getViewerScores(){
	$(".userScoresToRemove").remove();
	var newUserJSON = formToJSON(username,password);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/viewer-scores",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			console.log("succesfull");
			console.log(data);
			for( i = 0; i< data.length; i++){
				var row = '<tr class="userScoresToRemove">';            
	            row += "<td>" + data[i]['movieName'] + "</td>";
				row += "<td>" + data[i]['movieScore'] + "</td>";

				if(data[i]['movieScore'] == 0){
					var rowSelect = '<option class="userScoresToRemove" value="' + data[i]['scoreId'] + '">' + data	[i]['movieName'] + '</option>';
					$('#selectMovieToScore').append(rowSelect);  
				}
				  
				$("#scoredMoviesTable").append(row);                  
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
}
function getManagerAuditoriums(){
	$(".managerAuditoriumsToRemove").remove();
	for(var i=0; i<CinemaNames.length;i++){
		var newUserJSON = cinemaFormToJson(CinemaNames[i],"","","","",username);
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/api/managers-auditoriums",
			dataType:"json",
			contentType:"application/json",
			data:newUserJSON,
			success:function(data){
				for( i = 0; i< data.length; i++){
					var row = '<tr class="managerAuditoriumsToRemove">';            
	                row += "<td>" + data[i]['id'] + "</td>";
					row += "<td>" + data[i]['capacity'] + "</td>";
					row += "<td>" + data[i]['cinemaName'] + "</td>";

					var rowSelect = '<option class="managerAuditoriumsToRemove" value="' + data[i]['id'] + '">' + data[i]['id'] + '</option>';

					$('#managerAuditoriums').append(row);    
					$("#selectAuditoriumDelete").append(rowSelect);
					$("#selectAuditoriumprojectionCreate").append(rowSelect);                  
				}
			},
			error: function (data) {
				console.log("ERROR : ", data);
			}
		});
	}
}
function getManagerCinemas(){
	var newUserJSON = formToJSON(username,password);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/managers-cinema",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
            $(".managerCinemasToRemove").remove();
			CinemaNames = [];
			for( i = 0; i< data.length; i++){
				var row = '<tr class="managerCinemasToRemove">';     
                row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				CinemaNames.push(data[i]['name']);
				var rowSelect = '<option class="managerCinemasToRemove" value="' + data[i]['name'] + '">' + data[i]['name'] + '</option>';

				$('#managerCinemas').append(row);       
				$('#selectAuditoriumCinema').append(rowSelect);                 
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
}
function getManagerProjections(){
	var newUserJSON = formToJSON(username,password);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/managers-projections",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			$(".projectionsToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="projectionsToRemove">';  
				row += "<td>" + data[i]['id'] + "</td>";
				row += "<td>" + data[i]['movieName'] + "</td>";
				row += "<td>" + data[i]['price'] + "</td>";
				row += "<td>" + data[i]['reservedCards'] + "</td>";
				row += "<td>" + data[i]['scheduledTime'] + "</td>";
				row += "<td>" + data[i]['auditoriumId'] + "</td>";
				$('#projectionsForManager').append(row);

				var rowSelect = '<option class="projectionsToRemove" value="' + data[i]['id'] + '">' + data[i]['id'] + '</option>';

				$('#selectIdChangeProjection').append(rowSelect);   
				$('#selectIdDeleteProjection').append(rowSelect);   
                        
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
}
function getManagers(){
	var newUserJSON = formToJSON(username,password);
    $.ajax({
		type:"GET",
		url:"http://localhost:8080/api/managers",
		dataType:"json",
		success:function(data){
            $(".managersToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="managersToRemove">';  
				row += "<td>" + data[i]['username'] + "</td>";
                row += "<td>" + data[i]['firstName'] + "</td>";
				row += "<td>" + data[i]['lastName'] + "</td>";
				row += "<td>" + data[i]['cinemaName'] + "</td>";

                $('#managers').append(row);                        
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
    });
}
function getCinemas(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/cinemas",
		dataType:"json",
		success:function(data){
			$(".cinemasToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="cinemasToRemove">';  
				row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "<td>" + data[i]['phoneNumber'] + "</td>";
				row += "<td>" + data[i]['manager'] + "</td>";
				$('#cinemas').append(row);                        
			}
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		},

	});
}
function getProjections(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/projections",
		dataType:"json",
		success:function(data){
			$(".projectionsSearchToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="projectionsSearchToRemove">';  
				row += "<td>" + data[i]['name'] + "</td>";
				row += "<td>" + data[i]['address'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "<td>" + data[i]['phoneNumber'] + "</td>";
				row += "<td>" + data[i]['manager'] + "</td>";
				$('#projectionsForSearch').append(row);                        
			}
		},
		error:function(data){
			console.log(data);
			alert("Username probably already exists, try another one");
		},
	});
}
function getMyReservedTickets(){
	var newUserJSON = formToJSON(username,password);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/viewers-projections",
		dataType:"json",
		contentType:"application/json",
		data:newUserJSON,
		success:function(data){
			$(".projectionsViewerToRemove").remove();
			for( i = 0; i< data.length; i++){
				var row = '<tr class="projectionsViewerToRemove">';  
				row += "<td>" + data[i]['movieName'] + "</td>";
				row += "<td>" + data[i]['movieGenre'] + "</td>";
				row += "<td>" + data[i]['movieDescription'] + "</td>";
				row += "<td>" + data[i]['movieGrade'] + "</td>";
				row += "<td>" + data[i]['price'] + "</td>";
				row += "<td>" + data[i]['scheduledTime'] + "</td>";
				$('#reservedTicketsOfProjections').append(row);

				var rowSelect = '<option class="projectionsViewerToRemove" value="' 
				+ data[i]['id']
				+ '">' 
				+ data[i]['movieName'] + " "
				+ data[i]['movieGenre'] + " "
				+ data[i]['movieDescription'] +  " "
				+ data[i]['movieGrade'] +   " "
				+ data[i]['price'] +   " "
				+ data[i]['scheduledTime'] +   " " + '</option>';
  
				$('#selectReserveTicketToBuyTicket').append(rowSelect); 
				$('#selectReserveTicketToCancel').append(rowSelect);   
                        
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
}