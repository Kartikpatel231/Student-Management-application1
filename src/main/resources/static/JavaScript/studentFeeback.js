const id=document.getElementById("id");
const title=document.getElementById("Title");
const descriptions=document.getElementById("descriptions");
const kartik=document.getElementById("myProfile");

kartik.addEventListener("submit", async e => {
e.preventDefault();
let payload = { id: id.value, title:title.value,descriptions:descriptions.value}
console.log(payload);
try{

 const response = await fetch(
    'http://localhost:8080/api/v1/create/feedback',
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
    console.log(response);
//    let rsp=response.url
  //  if(response.redirected){
    //  window.location = rsp;
    //}
    //else{
    alert("FeedBack Submited Successfully");
       setTimeout(() => {
                        window.location.href="../home.html";
                 }, 2000);
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