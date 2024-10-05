// Handle login form submission
if (window.location.pathname.endsWith("signin.html")) {
    document.querySelector('.form').addEventListener('submit', async (event) => {
        event.preventDefault(); // Prevent the default form submission
    
        const formData = {
            username: event.target.username.value,
            password: event.target.password.value,
        };
    
        try {
            const response = await fetch('/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });
    
            const result = await response.json();
            
            // Display the message in the message box
            const messageBox = document.getElementById('messageBox');
            const messageText = document.getElementById('messageText');
            
            messageBox.style.display = 'block'; // Show the message box
            messageText.innerText = result.message; // Set the message text
    
            // Optionally, redirect to another page if sign-in is successful
            if (result.success) {
                // Redirect to a different page or perform other actions
                window.location.href = '/dashboard'; // Example redirection
            }
        } catch (error) {
            console.error('Error:', error);
            const messageBox = document.getElementById('messageBox');
            const messageText = document.getElementById('messageText');
            
            messageBox.style.display = 'block'; // Show the message box
            messageText.innerText = 'There was an error signing in. Please try again.'; // Set error message
        }
    });
    
    
}

// Handle signup form submission
if (window.location.pathname.endsWith("signup.html")) {
    document.querySelector('.form').addEventListener('submit', async (event) => {
        event.preventDefault(); // Prevent the default form submission
    
        const formData = {
            username: event.target.username.value,
            email: event.target.email.value,
            password: event.target.password.value,
            confirmPassword: event.target.confirmPassword.value
        };
    
        try {
            const response = await fetch('/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });
    
            const result = await response.json();
            
            // Display the message in the message box
            const messageBox = document.getElementById('messageBox');
            const messageText = document.getElementById('messageText');
            
            messageBox.style.display = 'block'; // Show the message box
            messageText.innerText = result.message; // Set the message text
    
            // Optionally, clear the form if sign-up is successful
            if (result.success) {
                event.target.reset(); // Reset the form
            }
        } catch (error) {
            console.error('Error:', error);
            const messageBox = document.getElementById('messageBox');
            const messageText = document.getElementById('messageText');
            
            messageBox.style.display = 'block'; // Show the message box
            messageText.innerText = 'There was an error signing up. Please try again.'; // Set error message
        }
    });
    
}
