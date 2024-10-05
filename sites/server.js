const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const app = express();
const PORT = 3000;

// Middleware
app.use(bodyParser.json());
app.use(express.static('site')); // Serve static files from 'public' directory

// Handle Sign Up
// Add this route at the beginning of your server.js file, before other routes
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/site/signin.html'); // Change to your desired HTML file
});

app.post('/signup', (req, res) => {
    const { username, email, password, confirmPassword } = req.body;

    // Simple validation
    if (!username || !email || !password || !confirmPassword) {
        return res.status(400).json({ success: false, message: "All fields are required." });
    }

    if (password !== confirmPassword) {
        return res.status(400).json({ success: false, message: "Passwords do not match." });
    }

    // Load existing users from users.json
    fs.readFile('users.json', 'utf8', (err, data) => {
        if (err) {
            console.error('Error reading users file:', err);
            return res.status(500).json({ success: false, message: "Internal server error." });
        }

        let users = [];
        if (data) {
            users = JSON.parse(data);
        }

        // Check if user already exists
        const userExists = users.find(user => user.username === username || user.email === email);
        if (userExists) {
            return res.status(400).json({ success: false, message: "User already exists." });
        }

        // Add new user to the users array
        const newUser = { username, email, password }; // In a real app, don't store passwords in plain text!
        users.push(newUser);                

        // Write updated users back to users.json
        fs.writeFile('users.json', JSON.stringify(users, null, 2), (err) => {
            if (err) {
                console.error('Error writing users file:', err);
                return res.status(500).json({ success: false, message: "Internal server error." });
            }

            res.json({ success: true, message: "User registered successfully!" });
        });
    });
});

// Handle Sign In
app.post('/signin', (req, res) => {
    const { username, password } = req.body;

    // Load existing users from users.json
    fs.readFile('users.json', 'utf8', (err, data) => {
        if (err) {
            console.error('Error reading users file:', err);
            return res.status(500).json({ success: false, message: "Internal server error." });
        }

        const users = JSON.parse(data || '[]'); // Default to an empty array if the file is empty
        const user = users.find(user => user.username === username && user.password === password);

        if (user) {
            return res.json({ success: true, message: "Login successful!" });
        } else {
            return res.status(400).json({ success: false, message: "Invalid username or password." });
        }
    });
});

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
