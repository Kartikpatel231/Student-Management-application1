const companyDetailElement = document.getElementById('companyDetail');

// Function to get the company ID from the URL parameter
function getCompanyIdFromURL() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('id');
}

// Function to fetch and display company details
function fetchCompanyDetails(companyId) {
  fetch(`http://localhost:8080/api/v1/get/${companyId}`)
    .then(response => response.json())
    .then(company => {
      if (company) {
        // Populate the company details on the page
        companyDetailElement.innerHTML = `
          <h1>${company.name}</h1>
          <p><strong>Description:</strong> ${company.description}</p>
          <p><strong>Registration:</strong> ${company.registration}</p>
          <p><strong>Website:</strong> <a href="${company.website}" target="_blank">${company.website}</a></p>
        `;
      } else {
        companyDetailElement.innerHTML = '<p>Company not found.</p>';
      }
    })
    .catch(err => {
      console.error('Error fetching company details:', err);
      companyDetailElement.innerHTML = '<p>An error occurred while fetching company details.</p>';
    });
}

// Get the company ID from the URL parameter and fetch details
const companyId = getCompanyIdFromURL();
if (companyId) {
  fetchCompanyDetails(companyId);
} else {
  companyDetailElement.innerHTML = '<p>Company ID not provided in the URL.</p>';
}
