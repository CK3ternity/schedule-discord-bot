# schedule-discord-bot
This is a discord bot made using https://github.com/DV8FromTheWorld/JDA.
It's only command is !remind, which using the syntax "!remind MM/DD/YYYY HH:MM, [message]", sends "@everyone [message]" when the specified date and time occur.
On startup it also looks through the file to check if any of the specified dates/times happened while offline.
After the message is sent, the event is removed from the file, as to stop every past event from being sent every time on startup.
You can add this bot to your discord server via: https://discord.com/api/oauth2/authorize?client_id=843986607056355388&permissions=8&scope=bot
