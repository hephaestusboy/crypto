// server.js
const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 3000;

// Middleware
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// MySQL Connection
const db = mysql.createConnection({
    host: 'localhost',
    user: 'your_username',  // Replace with your MySQL username
    password: 'your_password', // Replace with your MySQL password
    database: 'your_database' // Replace with your MySQL database name
});

// Connect to the database
db.connect(err => {
    if (err) {
        console.error('Database connection failed:', err.stack);
        return;
    }
    console.log('Connected to the database.');
});

// Route to handle sign-in form submission
// Route to handle sign-in form submission
app.post('/signin', (req, res) => {
    const { username, password } = req.body;

    const sql = 'SELECT * FROM users WHERE username = ? AND password = ?';
    db.query(sql, [username, password], (err, results) => {
        if (err) {
            console.error('Error querying data:', err);
            return res.status(500).send('Database error');
        }

        if (results.length > 0) {
            res.status(200).send('Login successful');
        } else {
            res.status(401).send('Invalid username or password');
        }
    });
});


// Route to handle sign-up form submission
app.post('/signup', (req, res) => {
    const { username, email, password } = req.body;

    const sql = 'INSERT INTO users (username, email, password) VALUES (?, ?, ?)';
    db.query(sql, [username, email, password], (err, results) => {
        if (err) {
            console.error('Error inserting data:', err);
            return res.status(500).send('Database error');
        }
        res.status(200).send('Sign Up Data Inserted Successfully');
    });
});

// Route to handle new password entry
app.post('/add-password', (req, res) => {
    const { title, username, password } = req.body;

    const sql = 'INSERT INTO passwords (title, username, password) VALUES (?, ?, ?)';
    db.query(sql, [title, username, password], (err, results) => {
        if (err) {
            console.error('Error inserting data:', err);
            return res.status(500).send('Database error');
        }
        res.status(200).send('New Password Added Successfully');
    });
});

// Start the server
app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
