const net = require('net');

const SERVER_PORT = 8080;
const SERVER_HOST = 'localhost';

const client = new net.Socket();

client.connect(SERVER_PORT, SERVER_HOST, () => {
    console.log('Connected to server');

    // Send a message to the server
    const message = 'Hello from client';
    client.write(message);
    console.log('Message sent:', message);
});

client.on('data', (data) => {
    const response = data.toString();
    console.log('Response received:', response);

    // Close the connection after receiving the response
    client.end();
});

client.on('close', () => {
    console.log('Connection closed');
});

client.on('error', (err) => {
    console.error('Client error:', err);
});
