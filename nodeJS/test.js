const Discord = require('discord.js');
const client = new Discord.Client();

client.on('ready', () => {
  console.log(`Logged in as ${client.user.tag}!`);
});

client.on('message', msg => {
  if (msg.content === 'ping') {
    console.log("recu un message, envoie de réponse");
    msg.reply('Pong!');
  }
});

client.login('NjkzOTEzNzA0NDEyMDg2MzAy.Xo3e4Q.6ZaDRWZLs1WKnVFdZ7Jaz4YOUJE');




let {scanf} = require('nodejs-scanf');

// input: 'hello world'
scanf('%s', function(str) {
  const channel = client.channels.cache.get('302492740627988492');
  channel.send(str);
  console.log("message envoyé");  
});