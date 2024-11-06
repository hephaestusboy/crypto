// Function to handle form submissions
function handleFormSubmission(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get the form that triggered the event
    const form = event.target;

    // Extract values from the form based on its ID
    if (form.id === 'signinForm') {
        const username = form.username.value;
        const password = form.password.value;
        console.log('Sign In Form Submitted');
        console.log('Username:', username);
        console.log('Password:', password);

        // Retrieve user details from local storage
        const storedUser = JSON.parse(localStorage.getItem('user'));

        // Check if stored user details match input
        if (storedUser && storedUser.username === username && storedUser.password === password) {
            alert('Login successful!');
            window.location.href = 'landing.html'; // Redirect to landing page
        } else {
            // If not found in local storage, try to validate against the server
            fetch('http://localhost:3000/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text(); // Or response.json() if your server returns JSON
            })
            .then(data => {
                alert(data); // Show success message or server response
                window.location.href = 'landing.html'; // Redirect to landing page
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to log in. Please check your credentials and try again.');
            });
        }

    } else if (form.id === 'signupForm') {
        const username = form.username.value;
        const email = form.email.value;
        const password = form.password.value;
        const confirmPassword = form.querySelector('input[name="confirm-password"]').value; // Update this line
        console.log('Sign Up Form Submitted');
        console.log('Username:', username);
        console.log('Email:', email);
        console.log('Password:', password);
        console.log('Confirm Password:', confirmPassword);

        // Check if password and confirm password match
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        // Store user details in local storage
        const userDetails = {
            username: username,
            email: email,
            password: password
        };

        // Optional: You can also send this data to the server
        fetch('http://localhost:3000/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userDetails)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            alert(data); // Show success message
            // Store in local storage
            localStorage.setItem('user', JSON.stringify(userDetails)); // Store as a string
            alert('Sign Up Successful! You can now sign in.');
            window.location.href = 'signin.html'; // Redirect to sign-in page
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to sign up. Please try again.');
        });

    } else if (form.id === 'passwordEntryForm') {
        const title = form.title.value;
        const username = form.username.value;
        const password = form.password.value;
        console.log('Add New Password Form Submitted');
        console.log('Title:', title);
        console.log('Username:', username);
        console.log('Password:', password);
        // Add password entry handling logic here (optional)
    }
}

// Attach event listeners to forms when the document is loaded
document.addEventListener('DOMContentLoaded', () => {
    const signinForm = document.getElementById('signinForm');
    const signupForm = document.getElementById('signupForm');
    const passwordEntryForm = document.getElementById('passwordEntryForm');

    if (signinForm) {
        signinForm.addEventListener('submit', handleFormSubmission);
    }

    if (signupForm) {
        signupForm.addEventListener('submit', handleFormSubmission);
    }

    if (passwordEntryForm) {
        passwordEntryForm.addEventListener('submit', handleFormSubmission);
    }
});
