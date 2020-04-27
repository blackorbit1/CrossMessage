//var PERSON = require("person.js").Person();
var events = require('events');

const Discord = require('discord.js');
const client = new Discord.Client();

var is_on = false;
const liste_channels = ["302492740627988492", "697464500638449735"];
/*
client.on('ready', () => {
    console.log(`Logged in as ${client.user.tag}!`);
  });
*/
function send_message(client_instance, args){
    console.log("arrivée dans send_message()");
    for (const channel_id in liste_channels) {
        console.log(liste_channels[channel_id]);
        console.log(args["channel"]);
        if (liste_channels[channel_id] != args["channel"]) {
            const channel = client_instance.channels.cache.get(liste_channels[channel_id]);
            channel.send(args["type"]);
            console.log("message envoyé");
        }
    }
}

exports.Group = function(PERSON) {
    function handleEvent(args) {
        //console.log(liste_channels)
        console.log("Caught event with args "+JSON.stringify(args));


        if(!is_on){
            console.log("if1");console.log("if1")
            client.login('NjkzOTEzNzA0NDEyMDg2MzAy.Xo3e4Q.6ZaDRWZLs1WKnVFdZ7Jaz4YOUJE');
            client.on('ready', () => {
                console.log(`Logged in as ${client.user.tag}!`);
                is_on = true;
                send_message(client, args)
                console.log("post send_message 1");
            });
        } else {
            console.log("if2")
            send_message(client, args)
            console.log("post send_message 2");
        }
    }

    PERSON.personEvents.on("myEvent", function(args) {
        handleEvent(args);
    });
}

