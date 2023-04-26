const fullNames=document.getElementById("fullNames");
const emails=document.getElementById("emails");
const passwords=document.getElementById("passwords");
const kartik=document.getElementById("myForms");
kartik.addEventListener("submit", async e => {
e.preventDefault();
let payload = { fullNames: fullNames.value, email:emails.value, password:passwords.value}
console.log(payload);
try{
console.log('check1');

 const response = await fetch(
    'http://localhost:8080/api/v1/users',
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    }
  );
  console.log('check2');
  console.log(response)
  let rsp=response.url
  if(response.redirected){
    window.location = rsp;
  }
  else{
  alert("Bad Credits")
  }
 // const content = await response.json();
  //const data = await content;
console.log('check3');

  if (!response.ok) {
    throw new Error(data.message);
    return;
  }
}
catch(e){}
}
)