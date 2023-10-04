// Function to fetch a list of companies by student ID
async function fetchCompaniesByStudentId(studentId) {
  try {
    const response = await fetch(`http://www.campusplacehub.com/api/v1/get/company/by/${studentId}`);

    if (response.ok) {
      const companyList = await response.json();
      displayCompanyList(companyList);
    } else {
      console.log('Error fetching company list');
    }
  } catch (error) {
    console.error('Error:', error);
  }
}

function displayCompanyList(companyList) {
  const companyListContainer = document.getElementById("companyList");
companyListContainer.style.backgroundColor = "black"; // Set background color
companyListContainer.style.padding = "10px"; // Set padding

  // Clear any existing content
  companyListContainer.innerHTML = "";

  // Create and append elements for each company
  companyList.forEach((company) => {
    // Create a list item for each company
    const listItem = document.createElement("li");
    listItem.classList.add("list-group-item");
    listItem.style.backgroundColor="darkslategray";
    const cardElement = document.createElement("div");
    cardElement.classList.add("company-card");

    const nameElement = document.createElement("p");
    const websiteElement = document.createElement("p");
    const websiteStatus = document.createElement("p");
    const websiteRegistration = document.createElement("p");

    nameElement.textContent = `Name: ${company.name}`;
    websiteElement.textContent = `Website: ${company.website}`;
    websiteStatus.textContent = `Status: ${company.status}`;

    if (company.registration === true) {
      websiteRegistration.textContent = `Registration: Applied`;
    } else {
      websiteRegistration.textContent = `Registration: Undefined`;
    }
 cardElement.style.backgroundColor = "black";
    cardElement.style.border = "1px solid #ccc";
    cardElement.style.boxShadow = "0 2px 4px rgba(0, 0, 0, 0.1)";
    cardElement.style.padding = "20px";
    cardElement.style.margin = "10px";
    cardElement.style.width = "80%";
    cardElement.style.maxWidth = "400px";
    cardElement.style.textAlign = "left";
    cardElement.style.transition = "transform 0.2s";

    // Apply styles to the text elements
    const textElements = [nameElement, websiteElement, websiteStatus, websiteRegistration];
    textElements.forEach((textElement) => {
      textElement.style.margin = "10px 0";
      textElement.style.color = "white";
      textElement.style.fontFamily = "sans-serif";
      textElement.style.fontWeight = "bold";
    });
    cardElement.appendChild(nameElement);
    cardElement.appendChild(websiteElement);
    cardElement.appendChild(websiteStatus);
    cardElement.appendChild(websiteRegistration);

    // Append the card to the list item
    listItem.appendChild(cardElement);

    // Append the list item to the company list container
    companyListContainer.appendChild(listItem);
  });
}

// Retrieve student ID from cookies
const studentIdFromCookie = getCookie('id');

if (studentIdFromCookie) {
  fetchCompaniesByStudentId(studentIdFromCookie);
}

// Function to retrieve cookie value by name
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}
