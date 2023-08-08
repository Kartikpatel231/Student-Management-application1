document.addEventListener("DOMContentLoaded", function() {
    const companyList = document.getElementById("companyList");

    // Fetch and display all companies
    fetch("http://localhost:8080/api/v1/getAll")
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                companyList.innerHTML = "<p>No companies available.</p>";
            } else {
                data.forEach(company => {
                    const listItem = document.createElement("li");
                    listItem.textContent = `Name: ${company.name}, Website: ${company.website}, Description: ${company.description}`;
                    companyList.appendChild(listItem);
                });
            }
        })
        .catch(error => {
            console.error("Error:", error);
            companyList.innerHTML = "<p>An error occurred while fetching companies.</p>";
        });
});
document.addEventListener("DOMContentLoaded", function() {
    const createForm = document.getElementById("createForm");
    const message = document.getElementById("message");

    createForm.addEventListener("submit", function(event) {
        event.preventDefault();
        
        // ... (existing form submission code) ...

        // Redirect to the company list page after successful creation
        window.location.href = "company-list.html";
    });
});
