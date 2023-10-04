const companyDetailElement = document.getElementById('companyDetail');

// Function to get the company ID from the URL parameter
function getCompanyIdFromURL() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('id');
}

// Function to fetch and display company details
function fetchCompanyDetails(id) {
  fetch(`http://www.campusplacehub.com/api/v1/get/${id}`)
    .then(response => response.json())
    .then(company => {
      if (company) {
        // Populate the company details on the page

 const createdOnDate = new Date(company.createdOn);
 const formattedCreatedOn = createdOnDate.toLocaleString('en-US', {
   year: 'numeric',
   month: 'long',
   day: 'numeric',
   hour: '2-digit',
   minute: '2-digit',
   second: '2-digit'
 });


        companyDetailElement.innerHTML = `
          <h1>${company.name}</h1>
          <p><strong>Description:</strong> ${company.description}</p>
          <p><strong>Registration:</strong> ${company.registration}</p>
          <p><strong>Website:</strong> <a href="${company.website}" target="_blank">${company.website}</a></p>
                  <p style="color:white"><strong>Created On:</strong> ${formattedCreatedOn}</p> <!-- Formatted createdOn field -->
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
const id = getCompanyIdFromURL();
if (id) {
  fetchCompanyDetails(id);
} else {
  companyDetailElement.innerHTML = '<p>Company ID not provided in the URL.</p>';
}
