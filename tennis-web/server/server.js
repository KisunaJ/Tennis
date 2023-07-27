const express = require('express');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const cors = require('cors');

const port = parseInt(process.env.PORT, 10) || 8080;
const dev = process.env.ENV !== 'production';

const server = express(); //crear un servidor express * abajo estan los parametros del server

server.use(bodyParser.json());
server.use(bodyParser.urlencoded({ extended: true }));
server.use(cookieParser());
server.use(cors({
    origin: `http://localhost:3000`


}));

if (dev) {
    const mockApiRoutes = require('./server-mock');
    server.use('/springtennis/api/v1/', mockApiRoutes);
  }
  
  server.listen(port, (err) => {
    if (err) throw err;
    console.log(` > Ready on http://localhost:${port}`);
  });

  
