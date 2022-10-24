function getUserData(){
	//fetch('http://localhost:63342/api/v1/current-user-principal')           //api for the get request
	fetch('http://localhost:8080/display')
   .then(response => response.json())
   .then(data => console.log(data)
  //document.getElementById("profile-pic").src=data.picture;
   //document.getElementById("fullname").innerHTML=data.name;
    //document.getElementById("email").src=data.email;
  );
}