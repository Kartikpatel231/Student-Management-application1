const userName = document.getElementById("fullName");
const email = document.getElementById("email");
const mobile = document.getElementById("mobileNumber");
const password = document.getElementById("password");
const Kartik = document.getElementById("myform");

Kartik.addEventListener("submit", e => {
e.preventDefault();
const gender = document.querySelector('input[name="gender"]:checked');
let payload = { fullName: userName.value, email: email.value, mobileNumber: mobile.value, password: password.value, gender: gender.value }
console.log('voooon');
fetch(
'http://www.campusplacehub.com/api/v1/users/register',
{
  method: "POST",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
  body: JSON.stringify(payload),
}

).then(resp => resp.json())
.then((data) => {
 document.cookie = `id=${data.studentId}; max-age=86400`;
 // Set the received value as a session variable
 const sesionId=data.studentId;
sessionStorage.setItem('id',sesionId);
 window.location.href = 'http://www.campusplacehub.com/home';
//  window.open(data.url, "_self");
console.log("our data", data);
})
.catch(e => {
console.log("error is :", e)
});
}
)