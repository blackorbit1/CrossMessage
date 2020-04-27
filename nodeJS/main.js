const Discord = require('discord.js');
const client = new Discord.Client();

var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);

var mysql = require('mysql');

var PERSON = require("/Users/enzoportable/Documents/PC3R/Projet/person.js").Person();
var GROUP = require("/Users/enzoportable/Documents/PC3R/Projet/group.js").Group(PERSON);

const port_site = 3005;
const hub_name = "test";
var hub_id = 0;
var liste_sockets = [];
var liste_channels = [];


/////// MISE EN PLACE DU SITE ///////

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

http.listen(port_site, () => {
  console.log('listening on *:' + port_site);
});


/////// MISE EN PLACE MYSQL ///////

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "root",
  database: 'CrossMessage',
  port: "8889"
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});

con.query('SELECT id_discord_room FROM room', (err,rows) => {
  if(err) throw err;

  console.log('Rooms received from Db:');
  rows.forEach( (row) => {
    console.log(`- room ${row.id_discord_room}`);
    liste_channels.push(row.id_discord_room);
  });
});

con.query('SELECT id FROM hub WHERE name = ?', [hub_name], (err,[row]) => {
  if(err) throw err;
  hub_id = row.id;
});

function save_msgs(message, id_hub){
  const temp = { message_content: message, id_hub: id_hub };
  con.query('INSERT INTO message SET ?', temp, (err, res) => {
    if(err) throw err;
    console.log("Message ajouté à le base de données");
  });
}


/////// MISE EN PLACE DE LA TRANSMISSION DES MESSAGES ///////


client.on('ready', () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

client.on('message', msg => {
    if(!msg.author.bot && liste_channels.includes(msg.channel.id)){
        save_msgs(msg.content, hub_id);
        io.emit('chat message', msg.content);
        PERSON.generateEvent({ type: msg.content, channel: msg.channel.id });
        console.log("message transmis"); 
    }
    
});

io.on('connection', (socket) => {
  liste_sockets.push(socket);
  console.log('a user connected');
  socket.on('disconnect', () => {
      console.log('user disconnected');
  });

  socket.on('chat message', (msg) => {
      save_msgs(msg, hub_id);
      PERSON.generateEvent({ type: msg, channel: "rien" });
      io.emit('chat message', msg);
      console.log("message transmis"); 
  });
});
  
client.login('NjkzOTEzNzA0NDEyMDg2MzAy.Xo3e4Q.6ZaDRWZLs1WKnVFdZ7Jaz4YOUJE');
