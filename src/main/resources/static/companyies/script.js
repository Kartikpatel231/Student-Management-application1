const companyListElement = document.getElementById('companyList');

// Function to handle applying to a company
const applyToCompany = (companyId, studentId) => {
  // ... Your existing code for applying to a company ...
   const apiUrl = `http://localhost:8080/api/v1/applied/${companyId}/by/${studentId}`;

    // Make a POST request to the apply endpoint
 fetch(apiUrl, {
   method: 'POST',
   headers: {
     'Content-Type': 'application/json'
   },
   body: JSON.stringify({}) // You can provide data in the request body if needed
 })
   .then(response => {
     if (response.status === 200) {
     alert("Applied successfully");
       return response.json();
     } else {
       throw new Error('Non-200 response status');
     }
   })
   .then(result => {
     // Check if the response message is "Applied successfully"
     if (result && result.message === "Applied successfully") {
       // Display an alert message with the success text
       alert('Applied successfully');

       // Redirect to another page after a delay
       setTimeout(() => {
         window.location.href = '../demo/status.html';
       }, 3000); // Redirect after 3 seconds (adjust the delay as needed)
     } else {
       // Handle the case where the server response indicates an error
       throw new Error('Server response indicates an error');
     }
   })
   .catch(error => {
     console.error('Error applying to company:', error);
     //alert("Applied successfully");
     window.location.href = '../demo/status.html'; // Redirect on error
   });

};

// Function to view full company details and navigate to another page
const viewCompanyDetails = (companyId) => {
  // Construct the URL to the details page with the companyId
  const detailsPageUrl = `../demo/company-detail.html?id=${companyId}`;
  
  // Navigate to the details page
  window.location.href = detailsPageUrl;
};

// Retrieve student ID from cookie named "id"
const studentIdFromCookie = getCookie('id');

// Fetch a list of companies
fetch('http://localhost:8080/api/v1')
  .then(response => response.json())
  .then(companies => {
    companies.forEach(company => {
      const listItem = document.createElement('li');
      listItem.classList.add('company-item');

      const companyDetails = document.createElement('div');
      companyDetails.classList.add('company-details');
      companyDetails.innerHTML = `
        <p><strong>Name:</strong> ${company.name}</p>
        <p><strong>Description:</strong> ${company.description}</p>
        <p><strong>Registration:</strong> ${company.registration}</p>
        <p><strong>Website:</strong> <a href="${company.website}" target="_blank">${company.website}</a></p>
      `;
      listItem.appendChild(companyDetails);

      const applyButton = document.createElement('button');
      applyButton.textContent = 'Apply';
      applyButton.classList.add('apply-button');
      applyButton.addEventListener('click', () => {
        applyToCompany(company.companyId, studentIdFromCookie);
      });
      listItem.appendChild(applyButton);

      // Create a button for viewing full company details
      const viewDetailsButton = document.createElement('button');
      viewDetailsButton.textContent = 'View Details';
      viewDetailsButton.classList.add('view-details-button');
      viewDetailsButton.addEventListener('click', () => {
        viewCompanyDetails(company.companyId);
      });
      listItem.appendChild(viewDetailsButton);

      companyListElement.appendChild(listItem);
    });
  })
  .catch(error => {
    console.error('Error fetching companies:', error);
  });

// Function to retrieve cookie value by name
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}
