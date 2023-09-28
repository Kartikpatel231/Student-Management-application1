const id=document.getElementById("id");
const studentName=document.getElementById("studentName");
const fatherName=document.getElementById("fatherName");
const motherName=document.getElementById("motherName");
const mobileNumber=document.getElementById("mobileNumber");
const bloodGroup=document.getElementById("bloodGroup");
const dateOfBirth=document.getElementById("dateOfBirth");
const address1=document.getElementById("address1");
const category=document.getElementById("category");
const enrollementNumber=document.getElementById("enrollementNumber");
const zipcode=document.getElementById("zipcode");
const kartik=document.getElementById("myProfile");

kartik.addEventListener("submit", async e => {
e.preventDefault();
let payload = { id: id.value, studentName:studentName.value,fatherName:fatherName.value,motherName:motherName.value,mobileNumber:mobileNumber.value,
bloodGroup:bloodGroup.value,dateOfBirth:dateOfBirth.value,category:category.value,enrollementNumber:enrollementNumber.value,zipcode:zipcode.value,address1:address1.value}
console.log("one")
fetch(
    'http://localhost:8080/api/v1/create/profile',
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

    alert("ProfileCreated Successfully")
             // Simulate a delay and then redirect to another page
             setTimeout(() => {
                    window.location.href="../company/studentDatass.html";
             }, 2000); // Redirect after 3 seconds (adjust the delay as needed)
    console.log("our data", data);
    document.getElementById("studentName1").textContent=data.studentName;
    document.getElementById("mobileNumber1").innerHTML=data.mobileNumber;
    document.getElementById("address2").innerHTML=data.address1;
    document.getElementById("studentName2").textContent=data.motherName;
    document.getElementById("mobileNumber2").innerHTML=data.category;
    })
    .catch(e => {
    console.log("error is :", e)
    });
    }
    )

