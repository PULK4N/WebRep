hashmapButtons = { "#loginButton" : [false,"#loginHTML"], /*"#usersButton" : [false,"#usersHTML"], */"#managersButton" : [false,"#adminForManagersHTML"],"#profileButton" :[false,"#profileHTML"], "#registerButton" : [false,"#registerHTML"], "#indexButton":[true,"#indexHTML"], "#loginButton2" : [false,"#loginHTML"], "#cinemasButton" : [false,"#cinemasHTML"], "#projSearchButton" : [false,"#projSearchHTML"], "#reservedTicketsButton" : [false,"#reservedTicketsHTML"], "#watchedMoviesButton" : [false,"#watchedMoviesHTML"]};//button:[toggled,html]

var projectionListData = [];

$(document).ready(function (){
    for(button in hashmapButtons){//dodavanje funkcije svakom button
        $(button).click(function (){
            button = "#" + this.id;
            if (hashmapButtons[button][0] == false){//ako nije toggled
                for(buttonQ in hashmapButtons){//sakri svaki button i stavi da su iskljuceni
                    $(hashmapButtons[buttonQ][1]).attr("hidden", true);
                    hashmapButtons[buttonQ][0] = false;
                    $(buttonQ).removeClass("text-danger");
                }
                hashmapButtons[button][0] = true;//stavi da je ovaj ukljucen
                $(hashmapButtons[button][1]).attr("hidden", false);//otkrij ga
                $(button).addClass("text-danger");//promeni boju da je ukljucen
            }else{
                for(buttonQ in hashmapButtons){//sakri svaki button
                    $(hashmapButtons[buttonQ][1]).attr("hidden", true);
                    hashmapButtons[buttonQ][0] = false;//stavi da su iskljuceni
                    $(buttonQ).removeClass("text-danger");
                }
                $(button).removeClass("text-danger");//promeni boju da je iskljucen
                hashmapButtons["#indexButton"][0] = true;//prikazi indexHTML
                $(hashmapButtons["#indexButton"][1]).attr("hidden", false);

            }
        });
    }
});

$(document).ready(function () {

    $('.prev').click(function () { $('.theFirstCartousel').carousel('prev'); return false; });
    $('.next').click(function () { $('.theFirstCartousel').carousel('next'); return false; });

    $('.prev2').click(function () { $('.theSecondCartousel').carousel('prev'); return false; });
    $('.next2').click(function () { $('.theSecondCartousel').carousel('next'); return false; });
});

function getProjectionsAttr(){
    // console.log("I WAS HEREEEEE  getProjection(). attributeChanges.js");
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/projections",
		dataType:"json",
		success:function(data){
            projectionListData = data;
            console.log(data);
            createTableSearchFromData(data);
		},
		error:function(data){
			console.log(data);
		},
	});
};

$(document).ready(function () {
    $("#projSearchButton").click(function (){
        getProjectionsAttr();
        $("#inputProjectionSearchLabels")[0].addEventListener('input', (event) =>{
            var inputProjectionSearchLabels = event.target.value;
            
            createTableSearchFromData(searchTable(inputProjectionSearchLabels,projectionListData));
        });
    });
});

function searchTable(value,data){
    var filteredData = Array.from(data);
    var values = value.split(";");
    var comparisonValues = ['movieName',
    'movieGenre',
    'movieDescription',
    'movieGrade',
    'price',
    'scheduledTime']
    for(var j=0; j<values.length;j++){
        values[j] = values[j].toLowerCase();
        for(var i=0;i<data.length;i++){
            var comparisonValue = data[i][comparisonValues[j]].toLowerCase();
            if(!(comparisonValue.includes(values[j]))){
                if(filteredData.includes(data[i])){
                    filteredData.splice(filteredData.indexOf(data[i]), 1);
                }
            }
        }
        
    }
    return filteredData;
}

function createTableSearchFromData(data){
    $(".projectionsSearchToRemove").remove();
    data.sort(function(a, b) {
        return a.price < b.price;
    });
    for( i = 0; i< data.length; i++){
        var row = '<tr class="projectionsSearchToRemove">';  
        row += "<td>" + data[i]['movieName'] + "</td>";
        row += "<td>" + data[i]['movieGenre'] + "</td>";
        row += "<td>" + data[i]['movieDescription'] + "</td>";
        row += "<td>" + data[i]['movieGrade'] + "</td>";
        row += "<td>" + data[i]['price'] + "</td>";
        row += "<td>" + data[i]['scheduledTime'] + "</td>";

        var rowSelect = '<option class="projectionsSearchToRemove" value="' 
        + data[i]['id']
        + '">' 
        + data[i]['movieName'] + " "
        + data[i]['movieGenre'] + " "
        + data[i]['movieDescription'] +  " "
        + data[i]['movieGrade'] +   " "
        + data[i]['price'] +   " "
        + data[i]['scheduledTime'] +   " " + '</option>';
        $('#selectReserveTicket').append(rowSelect);
        $('#projectionsForSearch').append(row);                        
    }
}