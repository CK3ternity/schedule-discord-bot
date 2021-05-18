package me.cketernity.schedulebot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Event {
	long channelId;
	String message;
	Date remindDate;
	
	public Event(long channelId, String message, Date date, boolean addEveryone) {
		this.channelId = channelId;
		this.message = addEveryone ? "@everyone " + message : message;
		this.remindDate = date;
	}
	
	public long getChannelId() { return channelId; }
	public String getMessage() { return message; }
	public Date getRemindDate() { return remindDate; }
	
	public void addEvent() throws IOException {
		ArrayList<String> oldFileLines = new ArrayList<>();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader("src/main/java/me/cketernity/schedulebot/Events.txt"));
		
		while((line = reader.readLine()) != null) {
			oldFileLines.add(line);
		}
		
		reader.close();
		
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/me/cketernity/schedulebot/Events.txt"));
		
		for(String event: oldFileLines) {
			writer.write(event + "\n");
		}
		
		writer.write(channelId + "-" + remindDate + "-" + message);
		writer.close();
	}
	
}
