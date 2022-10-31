
function getUserData(){
	//fetch('http://localhost:63342/api/v1/current-user-principal')           //api for the get request
	fetch('http://localhost:8080/display')
   .then(response => response.json())
   .then(data => console.log(data)
  //document.getElementById("profile-pic").src=data.picture;
   //document.getElementById("fullname").innerHTML=data.name;
    //document.getElementById("email").src=data.email;
  );

 // window.onload = () => {
   // setTimeout(() => {
     // document.querySelector("body").classList.add("display");
    //}, 4000);
  //};

}
function getRegisterData(){
fetch('http://localhost:8080/api/v1/users/register')
.then(response=>response.json())
.then(data=>console.log(data));
document.getElementById("studentId").innerHTML=data.studentId;
document.getElementById("url").innerHTML=data.url;
}
function getStudentData(){
console.log("myprofiledata");
fetch('http://localhost:8080/api/v1/create/profile')
.then(response=>response.json())
.then(data=>{console.log(data);
document.getElementById("studentName1").innerHTML=data.studentName;
document.getElementById("mobileNumber1").innerHTML=data.mobileNumber;
document.getElementById("address2").innerHTML=data.address1;
});
console.log("MyProfileData");
}
