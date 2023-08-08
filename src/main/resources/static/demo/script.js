const Name = document.getElementById("name");
const website = document.getElementById("website");
const description = document.getElementById("description");
const Kartik = document.getElementById("createForm");

Kartik.addEventListener("submit", e => {
e.preventDefault();
const registration = document.querySelector('input[name="registration"]:checked');
let payload = { name: Name.value, website: website.value, description: description.value, registration: registration.value }
console.log('voooon');
fetch(
'http://localhost:8080/api/v1/create',
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
  window.open(data.url, "_self");
console.log("our data", data);
})
.catch(e => {
console.log("error is :", e)
});
}
)