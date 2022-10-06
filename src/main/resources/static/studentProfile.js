const id=document.getElementById("id");
const studentName=document.getElementById("studentName");
const fatherName=document.getElementById("fatherName");
const motherName=document.getElementById("motherName");
const mobileNumber=document.getElementById("mobileNumber");
const bloodGroup=document.getElementById("bloodGroup");
const dateOfBirth=document.getElementById("dateOfBirth");
const address1=document.getElementById("address1");

const category=document.getElementById("category");
const aadhaarFilePath=document.getElementById("aadhaar");
const zipcode=document.getElementById("zipcode");
const kartik=document.getElementById("myProfile");

kartik.addEventListener("submit", async e => {
e.preventDefault();
let payload = { id: id.value, studentName:studentName.value,fatherName:fatherName.value,motherName:motherName.value,mobileNumber:mobileNumber.value,
bloodGroup:bloodGroup.value,dateOfBirth:dateOfBirth.value,category:category.value,aadhaar:aadhaarFilePath.value,zipcode:zipcode.value,address1:address1.value}
console.log(payload);
try{

 const response = await fetch(
    'http://localhost:8080/api/v1/create/profile',
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