const express = require('express'),
    app = express(),
    http = require('http').createServer(app),
    io = require('socket.io')(http);

const host = '127.0.0.1';
const port = 7000;

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

let clients = [];

io.on('connection', (socket) => {
    console.log(`Client with id ${socket.id} connected`);
    clients.push(socket.id);

    socket.on('message', async (message) => {
        console.log(message);
        for (let i = 0; i < message.length; i++) {
            socket.emit('message', message[i]);
            await sleep(1000);
        }
    });

    socket.on('disconnect', () => {
        clients.splice(clients.indexOf(socket.id), 1);
        console.log(
            `Client with id ${socket.id} disconnected`
        );
    });
});

app.use(express.static(__dirname));

app.get('/', (req, res) => res.render('index'));

//получение количества активных клиентов
app.get('/clients-count', (req, res) => {
    res.json({
        count: io.clients().server.engine.clientsCount,
    });
});

//отправка сообщения конкретному клиенту по его id
app.post('/client/:id', (req, res) => {
    if (clients.indexOf(req.params.id) !== -1) {
        io.sockets.connected[req.params.id].emit(
            'private message',
            `Message to client with id ${req.params.id}`
        );
        return res.status(200).json({
            message: `Message was sent to client with id ${req.params.id}`,
        });
    } else
        return res
            .status(404)
            .json({ message: 'Client not found' });
});

http.listen(port, host, () =>
    console.log(`Server listens http://${host}:${port}`)
);
