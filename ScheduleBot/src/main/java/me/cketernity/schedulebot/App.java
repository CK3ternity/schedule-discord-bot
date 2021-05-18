package me.cketernity.schedulebot;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {
	public static JDA jda;
	public static JDABuilder builder;
	public static String prefix = "!";
	public static ArrayList<Event> events;
	
    public static void main(String[] args) throws LoginException, IOException {
    	builder = JDABuilder.createDefault(args[0]);
    	builder.setActivity(Activity.watching("the calendar, always. Waiting."));
    	builder.addEventListeners(new ScheduleListener());
    	
    	jda = builder.build();
    }
}
