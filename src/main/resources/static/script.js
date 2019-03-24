var mainUrl = "http://localhost:8080";
var kindMas =[];
var lastAddressID;

$('#get-page-button').click(() => {
			let pageRequest = {
				page: $('page-value').val(),
				size: 3,
				sort: {
					direction: 'ASC',
					fieldName: 'name'
				}
			}
			
			$.ajax({
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(pageRequest),
				success: (page) =>{
					$('.container').html(''); 
					for(let pet of page.content){
						appendProduct(pet);
					}
				},
				error: (e) =>{
					console.log(e)
				}
			});
		});
function appendProduct(prod){
	let container = $('.container');
			
	container.append(`
		<div class="product">
			<h3 class="product-name">${prod.name}</h3>
			<div class="product-image" ></div>
			<div class="product-description">
				<div class="product-kind">${prod.kind}</div>
				<div class="product-address">${prod.address}</div>
			</div>
		</div>
	`);
}

$('#addAddress').on('click', function(){
	var adr = document.getElementById("address");
	adr.style.display = "block";
});



$('#confirmAdr').on('click', function(){
	var adr = document.getElementById("address");
	let adrRequest ={
		country: $("countryName").val(),
		city: $("cityName").val(),
		street: $("streetName").val(),
		numberOfHouse: $("numOfHouse").val()
	}
	$.ajax({
		url: mainUrl +"/address",
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(adrRequest),
		success: function (){
			adr.style.display = "none";
		},
		error: (e) =>{
			console.log(e);
		}
	});
	$.ajax({
		url: mainUrl + "/address/get",
		type: 'GET',
		contentType: 'application/json',
		success: function (dataResponse) {
			lastAddressID = dataResponse[dataResponse.length-1].id;
		},
		error: (e) =>{
			console.log(e);
		}
	});
});



function fillKind(){
	$.ajax({
		url: mainUrl + "/kind/get",
		type: 'GET',
		contentType: 'application/json',
		success: function (dataResponse){
			setKindsToSelect(dataResponse);
			kindMas=dataResponse;
		},
		error: function(e){
			alert(e.message);
		}
	});
}

function setKindsToSelect(kinds){
	var mas = [], j = 0, check = true;
	for(var i=0;i<kinds.length;i++) {
		if(i!=0) {
			for (var z = 0; z < j; z++) {
				if (mas[z].name == kinds[i].name) {
					check = false;
				}
			}
		}
		if(check==true) {
			mas[j] = kinds[i];
			setKindToSelect(mas[j]);
			j++;
		}
		else{check=true;}
	}
}

function setKindToSelect(kind){
	let k =$('#kind');
	k.append(`
		<option value="${kind.id}">${kind.name}</option>
	`)
}

$("#kind").change(function(){
	var k = $("#kind option:selected").text;
	var breed = document.getElementById("breed");
	if(breed.style.display == "none"){
		breed.style.display = "block";
	}
	for(var i=0;i<kindMas.length;i++){
		if(kindMas[i].name == k){
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
function setModalConfiguration() {
        // Get the modal
        var modal = document.getElementById("myModal");

        // Get the button that opens the modal
        var btn = document.getElementById("openModal");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal
        btn.onclick = function () {
            modal.style.display = "block";
        };

        // When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal.style.display = "none";
        };

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };
    }