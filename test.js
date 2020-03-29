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

client.login('NjkzOTEzNzA0NDEyMDg2MzAy.XoEAHg.M9zAT619JkbZVUjvs9F4vWbZCUI');
