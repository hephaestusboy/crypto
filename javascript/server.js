const net = require('net');

const PORT = 8080;

const server = net.createServer((socket) => {
    console.log('Client connected');

    socket.on('data', (data) => {
        const message = data.toString();
        console.log('Message received:', message);

        // Send a response to the client
        const response = 'Hello from server';
        socket.write(response);
        console.log('Response sent:', response);
    });

    socket.on('end', () => {
        console.log('Client disconnected');
    });

    socket.on('error', (err) => {
        console.error('Socket error:', err);
    });
});

server.listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`);
});
