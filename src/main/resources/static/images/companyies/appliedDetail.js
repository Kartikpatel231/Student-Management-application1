// Function to fetch a list of companies by student ID
async function fetchCompaniesByStudentId(studentId) {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/get/company/by/${studentId}`);

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

// Function to display the list of companies
function displayCompanyList(companyList) {
  const companyDetailContainer = document.getElementById("companyDetail");

  // Clear any existing content
  companyDetailContainer.innerHTML = "";

  // Create and append elements for each company
  companyList.forEach((company) => {
    const companyDiv = document.createElement("div");
    const nameElement = document.createElement("p");
    const websiteElement = document.createElement("p");

    nameElement.textContent = `Name: ${company.name}`;
    websiteElement.textContent = `Website: ${company.website}`;

    companyDiv.appendChild(nameElement);
    companyDiv.appendChild(websiteElement);

    companyDetailContainer.appendChild(companyDiv);
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
