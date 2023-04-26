 const fileInput = document.getElementById("input-file");
    fileInput.style.width = '100px';
    fileInput.style.height = 'auto';
  id = getCookie("id");

function getCookie(cname) {
let name = cname + "=";
let decodedCookie = decodeURIComponent(document.cookie);
let ca = decodedCookie.split(';');
for (let i = 0; i < ca.length; i++) {
let c = ca[i];
while (c.charAt(0) == ' ') {
c = c.substring(1);
}
if (c.indexOf(name) == 0) {
return c.substring(name.length, c.length);
}
}
return "";
}
  fileInput.addEventListener("change", () => {
     var formdata = new FormData()
     formdata.append("file", fileInput.files[0])
     fetch("http://localhost:8080/file/upload/" + id
, {
        method: 'post',
        body: formdata,
     })
      const reader=new FileReader();
      reader.onload=function(){
      const img=new Image();
        img.style.width = '100px';
        img.style.height = 'auto';
      img.src=reader.result;
      document.body.appendChild(img);
  }
  reader.readAsDataURL(fileInput.files[0]);
},false)