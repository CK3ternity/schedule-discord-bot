package me.cketernity.schedulebot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RemindThread extends Thread {
	private ScheduleListener listener;
	
	public RemindThread(ScheduleListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		checkPastEvents();
		
		while(true) {
			checkEvents();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void checkPastEvents() {
		ArrayList<Event> events = new ArrayList<>();
		String line;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/java/me/cketernity/schedulebot/Events.txt"));
			
			while((line = reader.readLine()) != null) {
				String[] split = line.split("-", 3);
				
				String[] splitDateTime = split[1].split(" ", 2);
				String[] splitDate = splitDateTime[0].split("/", 3);
				int[] date = new int[splitDate.length];
				
				for(int i = 0; i < splitDate.length; i++) {
					date[i] = Integer.parseInt(splitDate[i]);
				}
				
				String[] splitTime = splitDateTime[1].split(":", 2);
				int[] time = new int[splitTime.length];
				
				for(int i = 0; i < splitTime.length; i++) {
					time[i] = Integer.parseInt(splitTime[i]);
				}
				
				events.add(new Event(Long.parseLong(split[0]), split[2], new Date(date[0], date[1], date[2], time[0], time[1]), false));
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> eventIndexes = new ArrayList<>();
		Date currentDate = new Date();
		int count = 0;
		
		for(Event event: events) {
			if(event.getRemindDate().before(currentDate)) {
				eventIndexes.add(count);
			}
			count++;
		}
		
		
		for(int index: eventIndexes) {
			listener.sendMessage(events.get(index));
		}
		
		for(int index: eventIndexes) {
			events.remove(events.get(index));
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/me/cketernity/schedulebot/Events.txt"));
			writer.write("");
			writer.close();
			
			for(Event event: events) {
				event.addEvent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void checkEvents() {
		ArrayList<Event> events = new ArrayList<>();
		String line;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/java/me/cketernity/schedulebot/Events.txt"));
			
			while((line = reader.readLine()) != null) {
				String[] split = line.split("-", 3);
				
				String[] splitDateTime = split[1].split(" ", 2);
				String[] splitDate = splitDateTime[0].split("/", 3);
				int[] date = new int[splitDate.length];
				
				for(int i = 0; i < splitDate.length; i++) {
					date[i] = Integer.parseInt(splitDate[i]);
				}
				
				String[] splitTime = splitDateTime[1].split(":", 2);
				int[] time = new int[splitTime.length];
				
				for(int i = 0; i < splitTime.length; i++) {
					time[i] = Integer.parseInt(splitTime[i]);
				}
				
				events.add(new Event(Long.parseLong(split[0]), split[2], new Date(date[0], date[1], date[2], time[0], time[1]), false));
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> eventIndexes = new ArrayList<>();
		Date currentDate = new Date();
		int count = 0;
		
		for(Event event: events) {
			if(event.getRemindDate().equals(currentDate)) {
				eventIndexes.add(count);
			}
			count++;
		}
		
		
		for(int index: eventIndexes) {
			listener.sendMessage(events.get(index));
		}
		
		for(int index: eventIndexes) {
			events.remove(events.get(index));
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/me/cketernity/schedulebot/Events.txt"));
			writer.write("");
			writer.close();
			
			for(Event event: events) {
				event.addEvent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
