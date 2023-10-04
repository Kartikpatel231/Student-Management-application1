const companyListElement = document.getElementById('companyList');

// Function to handle applying to a company
const applyToCompany = (companyId, studentId) => {
  const apiUrl = `http://www.campusplacehub.com/api/v1/applied/${companyId}/by/${studentId}`;

  // Make a POST request to the apply endpoint
  fetch(apiUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({}) // You can provide data in the request body if needed
  })
    .then(response => response.json())
    .then(result => {
      alert('Applied successfully');
      setTimeout(() => {
        window.location.href = './status/status';
      }, 2000);
      console.log('Application result:', result);
      // Now you can handle the application result, e.g., show a success message
    })
    .catch(error => {
      console.error('Error applying to company:', error);
    });
};

// Function to view full company details
const viewCompanyDetails = (companyId) => {
  // Redirect to the details page with the company ID as a query parameter
  window.location.href = `company-detail?id=${companyId}`;

};

// Retrieve student ID from cookie named "id"
const studentIdFromCookie = getCookie('id');

// Fetch a list of companies
fetch('http://www.campusplacehub.com/api/v1')
  .then(response => response.json())
  .then(companies => {
    companies.forEach(company => {
      const listItem = document.createElement('li');
      listItem.classList.add('company-item');


 listItem.style.marginBottom = '30px';
      const companyDetails = document.createElement('div');
      companyDetails.classList.add('company-details');


 const createdOnDate = new Date(company.createdOn);
 const formattedCreatedOn = createdOnDate.toLocaleString('en-US', {
   year: 'numeric',
   month: 'long',
   day: 'numeric',
   hour: '2-digit',
   minute: '2-digit',
   second: '2-digit'
 });

      companyDetails.innerHTML = `

        <p style="color:white"><strong style="font-weight: bold";>Name:</strong> ${company.name}</p>
        <p style="color:white"><strong>Registration:</strong> ${company.registration}</p>
        <p style="color:white"><strong>Website:</strong> <a href="${company.website}" target="_blank">${company.website}</a></p>
        <p style="color:white"><strong>Created On:</strong> ${formattedCreatedOn}</p> <!-- Formatted createdOn field -->

      `;
      listItem.appendChild(companyDetails);

      const applyButton = document.createElement('button');
      applyButton.textContent = 'Apply';
      applyButton.classList.add('apply-button');
      applyButton.style.backgroundColor = 'green'; // Change the background color
      applyButton.style.color = 'white'; // Change the text color
      applyButton.style.border = 'none'; // Remove the border
      applyButton.style.padding = '5px 10px'; // Add padding
      applyButton.classList.add('apply-button');
      applyButton.addEventListener('click', () => {
        applyToCompany(company.companyId, studentIdFromCookie);
       alert("applied successfully");
                    window.location.href="../status/status";


      });
      listItem.appendChild(applyButton);

      // Create a button for viewing full company details
      const viewDetailsButton = document.createElement('button');
      viewDetailsButton.textContent = 'View Full Details';
      viewDetailsButton.style.backgroundColor = 'blue'; // Change the background color
      viewDetailsButton.style.color = 'white'; // Change the text color
      viewDetailsButton.style.border = 'none'; // Remove the border
      viewDetailsButton.style.padding = '5px 10px'; // Add padding
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
