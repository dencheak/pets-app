var mainUrl = "http://localhost:8080";
var kindMas =[];
var lastAddressID;
var fileInBase64;
let file = document.getElementById("file");
var userId;
var userRole;

makeRequest();
$('#page').change(makeRequest);
$('#size').change(makeRequest);

$("#user-name").append(`
		${window.localStorage.getItem('login')}
	`);

function getPaginationRequest() {
	return {
		page: $('#page').val() - 1,
		size: $('#size').val(),
		sortRequest: {
			field: 'name',
			direction:'ASC'
		}
	};
}

function makeRequest() {
	$.ajax({
		url: mainUrl + "/pet/page",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(getPaginationRequest()),
		success: (page) =>{
			$('.container').html('');

			for(let pet of page.content){
				appendPets(pet);
			}
		},
		error: (e) =>{
			console.log("Token is invalid. Please sign in ");
			window.location.href = '/sign';
		}
	});
}


function appendPets(pet){
	let $container = $('.container');
	let img = pet.imagePath ?
		'http://localhost:8080/img/' + pet.imagePath
		: 'http://denrakaev.com/wp-content/uploads/2015/03/no-image.png';
	$container.append(`
		<div class="pet">
			<div class="pet-image "  >
				<img  style="width: 100%" src="${img}">
				<div class="div.container">
					<p class="pet-name">${pet.name}</p>
				</div>
			</div>
			<div class="pet-description">
				<div class="pet-kind">${pet.kindResponse.name} ${pet.kindResponse.breed}</div>
				<div class="pet-address">${pet.addressResponse.country}, ${pet.addressResponse.city}, ${pet.addressResponse.street} ${pet.addressResponse.numberOfHouse}</div>
			</div>
		</div>
	`);
}

$('#logOut').on('click', function () {
	window.location.href = '/sign';
})

$('#addAddress1').on('click', function(){
	var adr1 = document.getElementById("address1");
	adr1.style.display = "block";
});

$('#addAddress2').on('click', function(){
	var adr2 = document.getElementById("address2");
	adr2.style.display = "block";
});



$('#confirmAdr1').on('click', function(){
	var adr = document.getElementById("address1");
	let adrRequest ={
		country: $("#countryName1").val(),
		city: $("#cityName1").val(),
		street: $("#streetName1").val(),
		numberOfHouse: $("#numOfHouse1").val()
	};
	$.ajax({
		url: mainUrl +"/address",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(adrRequest),
		success: function (res){
			adr.style.display = "none";
			console.log("post address", res);
			lastAddressID=res.id;
		},
		error: (e) =>{
			console.log(e);
		}
	});
});

$('#confirmAdr2').on('click', function(){
	var adr = document.getElementById("address2");
	let adrRequest ={
		country: $("#countryName2").val(),
		city: $("#cityName2").val(),
		street: $("#streetName2").val(),
		numberOfHouse: $("#numOfHouse2").val()
	};
	$.ajax({
		url: mainUrl +"/address",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(adrRequest),
		success: function (res){
			adr.style.display = "none";
			console.log("post address", res);
			lastAddressID=res.id;
		},
		error: (e) =>{
			console.log(e);
		}
	});
});


$('#btnCreatePet').on('click', function (){
	let petRequest ={
		name: $("#pname").val(),
		kindId: $("#breed option:selected").val(),
		addressId: lastAddressID,
		fileRequest: {
			data: fileInBase64
		}
	};
	$.ajax({
		url: mainUrl +"/pet",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(petRequest),
		success: function (res){
			console.log("post pet", res);
			alert("Pet are posted");
			makeRequest();
		},
		error: (e) =>{
			console.log(e);
			alert("you have no rights to do that or not all fields are filled");
		}
	});
});

function getBase64(file) {
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function () {
		console.log(reader.result);
		fileInBase64 = reader.result;
	};
	reader.onerror = function (error) {
		console.log('Error: ', error);
	};
}

$('#btnCreateShelter').on('click', function (){

    let shelterRequest ={
		name: $("#shelterName").val(),
		addressId: lastAddressID,
		userId: userId
	};
	$.ajax({
		url: mainUrl +"/shelter",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(shelterRequest),
		success: function (res){
			console.log("post shelter", res);
			alert("Shelter are posted");
		},
		error: (e) =>{
			console.log(e);
			alert("you have no rights to do that or not all fields are filled");
		}
	});
});

$('#btnCreateKind').on('click', function (){
	let kindRequest ={
		name: $("#kindName").val(),
		breed: $("#breedName").val(),
	};
	$.ajax({
		url: mainUrl +"/kind",
		type: 'POST',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		data: JSON.stringify(kindRequest),
		success: function (res){
			console.log("post kind", res);
			alert("Kind are posted");
			$('#kind').empty();
			fillKind();
		},
		error: (e) =>{
			console.log(e);
			alert("you have no rights to do that or not all fields are filled");
		}
	});
});


function fillKind(){
	$.ajax({
		url: mainUrl + "/kind/get",
		type: 'GET',
		contentType: 'application/json',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		success: function (dataResponse){
			$('#kind').append(` <option value="" disabled selected>Choose your option</option>`);
			kindMas=[];
			setKindsToSelect(dataResponse);
			kindMas=dataResponse;
		},
		error: function(e){
			alert(e.message);
		}
	});
}

function setKindsToSelect(kinds){
	var mas = [], j = 0;
	for(var i=0;i<kinds.length;i++) {
		var check=true;
		if(i!=0) {
			for (var z = 0; z < j; z++) {
				if (mas[z].name == kinds[i].name) {
					check = false;
					break;
				}
			}
		}
		if(check) {
			mas[j] = kinds[i];
			setKindToSelect(mas[j]);
			j++;
		}

	}
}

function setKindToSelect(kind){
	let k =$('#kind');
	k.append(`
		<option value="${kind.id}">${kind.name}</option>
	`)
}

$("#kind").change(function(){
	var k = $("#kind option:selected").text();
	var breed = document.getElementById("breedDiv");
	breed.style.display = "block";
    $('#breed').empty();
	for(var i=0;i<kindMas.length;i++){
		if(kindMas[i].name===k){
		    appendBreed(kindMas[i]);
		}
	}
});
 

function appendBreed(kind){
		let breed = $("#breed");
		breed.append(`
			<option value="${kind.id}">${kind.breed}</option>
		`);
}


file.addEventListener("change", checkInputFile);

function  checkInputFile() {
	var label = document.getElementById("fileLabel");
	if (event.target == file) {
		if (file.files.length > 0) {
			getBase64(file.files[0]);
			label.innerText = file.files[0].name;
		}
	}
}

function getUser(){
	$.ajax({
		url: mainUrl +"/user/" + window.localStorage.getItem('login'),
		type: 'POST',
		headers: {
			'Authorize': window.localStorage.getItem('token')
		},
		success: function (res){
			userId = res.id;
			window.localStorage.setItem('userId', res.id);
			userRole = res.role;
			window.localStorage.setItem('userRole', res.role);
		},
		error: (e) =>{
			console.log(e);
		}
	});
}

function setModalConfiguration1() {
        // Get the modal
        var modal1 = document.getElementById("myModal1");

        // Get the button that opens the modal
        var btn = document.getElementById("openModal1");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal
        btn.onclick = function () {
            modal1.style.display = "block";
        };

        // When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal1.style.display = "none";
        };

        // When the user clicks anywhere outside of the modal, close it
        // window.onclick = function (event) {
        //     if (event.target == modal1) {
        //         modal1.style.display = "none";
        //     }
        // };
    }


    function closeModal() {
        window.onclick = function (event) {
            let m1 = document.getElementById("myModal1");
            let m2 = document.getElementById("myModal2");
            let m3 = document.getElementById("myModal3");
            if (event.target == m1) {
                m1.style.display = "none";
            }
            if (event.target == m2) {
                m2.style.display = "none";
            }
            if (event.target == m3) {
                m3.style.display = "none";
            }
        };
    }
function setModalConfiguration2() {
	// Get the modal
	var modal2 = document.getElementById("myModal2");

	// Get the button that opens the modal
	var btn = document.getElementById("openModal2");


	// When the user clicks the button, open the modal
	btn.onclick = function () {
		modal2.style.display = "block";
	};


	// When the user clicks anywhere outside of the modal, close it
	// window.onclick = function (event) {
	// 	if (event.target == modal2) {
	// 		modal2.style.display = "none";
	// 	}
	// };
}
function setModalConfiguration3() {
	// Get the modal
	var modal3 = document.getElementById("myModal3");

	// Get the button that opens the modal
	var btn = document.getElementById("openModal3");


	// When the user clicks the button, open the modal
	btn.onclick = function () {
		modal3.style.display = "block";
	};


	// When the user clicks anywhere outside of the modal, close it
	// window.onclick = function (event) {
	// 	if (event.target == modal3) {
	// 		modal3.style.display = "none";
	// 	}
	// };
}