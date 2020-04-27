var events = require('events');

const Discord = require('discord.js');
const client = new Discord.Client();

exports.Person = function() {
    var personEvents = new events.EventEmitter();

    function generateEvent(args) {
        console.log("Emitting event with args "+JSON.stringify(args));
        personEvents.emit("myEvent", args);
    }

    return {
        personEvents: personEvents,
        generateEvent: generateEvent
    };
}