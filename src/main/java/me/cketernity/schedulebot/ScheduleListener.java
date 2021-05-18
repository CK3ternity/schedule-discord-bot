package me.cketernity.schedulebot;

import java.io.IOException;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ScheduleListener extends ListenerAdapter {
	public ScheduleListener() {
		super();
		
		RemindThread thread = new RemindThread(this);
		thread.start();
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String msg = event.getMessage().getContentRaw();
		
		//Remind command, syntax "!remind MM/DD/YYYY HH:MM, [message]"
		if(msg.length() > App.prefix.length() + 8 && msg.substring(0, App.prefix.length() + 7).equalsIgnoreCase(App.prefix + "remind ")) {
			String[] split = msg.substring(App.prefix.length() + 7).split(", ", 2);
			String[] splitDateTime = split[0].split(" ");
			
			String[] splitDate = splitDateTime[0].split("/");
			int[] date = new int[splitDate.length];
			
			for(int i = 0; i < splitDate.length; i++) {
				date[i] = Integer.parseInt(splitDate[i]);
			}
			
			String[] splitTime = splitDateTime[1].split(":");
			int[] time = new int[splitTime.length];
			
			for(int i = 0; i < splitTime.length; i++) {
				time[i] = Integer.parseInt(splitTime[i]);
			}
			
			Event e = new Event(Long.parseLong(event.getChannel().getId()), split[1], new Date(date[0], date[1], date[2], time[0], time[1]), true);
			event.getMessage().addReaction("✔️").queue();
			
			try {
				e.addEvent();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	public void sendMessage(Event event) {
		TextChannel channel = App.jda.getTextChannelById(event.getChannelId());
		String message = event.getMessage();
		
		channel.sendMessage(message).queue();
	}
}
