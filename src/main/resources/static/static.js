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
console.log(response)
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
      console.log(response)
   console.log('check2');
     console.log(response)
 //    let rsp=response.url
   //  if(response.redirected){
     //  window.location = rsp;
     //}
     //else{
     alert("Signup Successfull Now You can Login")
     //}
    // const content = await response.json();
     //const data = await content;
   console.log('check3');
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