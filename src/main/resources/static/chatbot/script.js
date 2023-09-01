const companyListElement = document.getElementById('companyList');

// Function to handle applying to a company
const applyToCompany = (companyId, studentId) => {
  const apiUrl = `http://localhost:8080/api/v1/applied/${companyId}/by/${studentId}`;

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
      console.log('Applied successfully');
      console.log('Application result:', result);
      // Now you can handle the application result, e.g., show a success message
    })
    .catch(error => {
      console.error('Error applying to company:', error);
    });
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
