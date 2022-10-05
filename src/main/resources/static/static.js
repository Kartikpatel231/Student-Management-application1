const name=document.getElementById("fullName");
const email=document.getElementById("email");
const mobile=document.getElementById("mobileNumber");
const password=document.getElementById("password");
const submit=document.getElementById("myform");

submit.addEventListener("submit", async e => {
e.preventDefault();
const gender=document.querySelector('input[name="gender"]:checked');
let payload = { fullName: name.value, email:email.value, mobileNumber:mobile.value,password:password.value,gender:gender.value}
console.log(payload);
try{

 const response = await fetch(
    'http://localhost:8080/api/v1/users/register',
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    }
  );
  const content = await response.json();
  const data = await content;

  if (!response.ok) {
    throw new Error(data.message);
    return;
  }



}
catch(e){}
}
)