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
    'http://www.campusplacehub.com/api/v1/users',
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

    if (!response.ok) {
      throw new Error('Login failed');
    }
 const responseData = await response.json();
    const id = responseData; // Replace 'responseData' with the actual field name containing the value you want to store

    // Set the received value as a cookie
    document.cookie = `id=${id}; max-age=86400`;
    // Set the received value as a session variable
    sessionStorage.setItem('id', id);

    // Set the received value (150) as a cookie


    // Redirect to 172.172.233.120://8080/home2.html
    window.location.href = 'http://www.campusplacehub.com/home';

  } catch (error) {
    console.error('Error:', error);
    alert('Login failed');
  }

}

)