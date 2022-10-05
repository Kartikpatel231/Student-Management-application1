const fullNames=document.getElementById("fullNames");
const emails=document.getElementById("emails");
const passwords=document.getElementById("passwords");
const kartik=document.getElementById("myForms");
kartik.addEventListener("submit", async e => {
e.preventDefault();
let payload = { fullName: fullNames.value, email:emails.value, password:passwords.value}
console.log(payload);
try{

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