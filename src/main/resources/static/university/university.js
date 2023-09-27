function submitForm() {
    const form = document.getElementById("universityDetailForm");
    const formData = new FormData(form);

    // Convert formData to a JSON object
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // Get the specific cookie value you want to include in the request
    const specificCookieValue = getCookieValue('id'); // Replace 'yourCookieName' with the actual cookie name you want to use

    // Construct the URL with the correct protocol and port
    const url = `http://localhost:8080/api/v4/create/${specificCookieValue}/uni`;

    // Send the JSON data to the server using fetch
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        if (response.ok) {
            alert('Data submitted successfully');
        } else {
            alert('Failed to submit data');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// Function to retrieve a specific cookie value by name
function getCookieValue(cookieName) {
    const cookies = document.cookie.split(';');
    for (const cookie of cookies) {
        const [name, value] = cookie.trim().split('=');
        if (name === cookieName) {
            return value;
        }
    }
    return null; // Return null if the cookie is not found
}
