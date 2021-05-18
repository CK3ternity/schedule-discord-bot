package me.cketernity.schedulebot;

import java.time.LocalDateTime;

public class Date {
	private int day;
	private int month;
	private int year;
	
	private int hour;
	private int minute;
	
	public Date(int month, int day, int year, int hour, int minute) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
	}
	public Date() {
		this(-1, -1, -1, -1, -1);
		LocalDateTime ldt = LocalDateTime.now();
		
		setYear(ldt.getYear());
		setMonth(ldt.getMonthValue());
		setDay(ldt.getDayOfMonth());
		setHour(ldt.getHour());
		setMinute(ldt.getMinute());
	}
	public Date(int month, int day, int year) {
		this(month, day, year, 0, 0);
		LocalDateTime ldt = LocalDateTime.now();
		
		setHour(ldt.getHour());
		setMinute(ldt.getMinute());
	}
	public Date(int hour, int minute) {
		this(1, 1, 1970, hour, minute);
		LocalDateTime ldt = LocalDateTime.now();
		
		setYear(ldt.getYear());
		setMonth(ldt.getMonthValue());
		setDay(ldt.getDayOfMonth());
	}
	
	public void setDay(int day) {
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(day >= 1 && day <= 31)
				this.day = day;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if(day >= 1 && day <= 30)
				this.day = day;
			break;
		case 2:
			if(year % 4 == 0) {
				if(year % 100 == 0) {
					if(year % 400 == 0) {
						// is a leap year
						if(day >= 1 && day <= 29)
							this.day = day;
					} else {
						// isn't a leap year
						if(day >= 1 && day <= 28)
							this.day = day;
					}
				} else {
					// is a leap year
					if(day >= 1 && day <= 29)
						this.day = day;
				}
			}
			break;
		}
	}
	public void setMonth(int month) {
		if(month >= 1 && month <= 12) {
			this.month = month;
		}
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setHour(int hour) {
		if(hour >= 0 && hour <= 24)
			this.hour = hour;
		else if(hour >= 101 && hour <= 112)
			this.hour = hour - 100;
		else if(hour >= 201 && hour <= 212)
			this.hour = hour - 188;
	}
	public void setMinute(int minute) {
		if(minute >= 0 && minute <= 59)
			this.minute = minute;
	}
	
	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }

	public int getHour() { return hour; }
	public int getMinute() { return minute; }
	
	@Override
	public String toString() {
		String str = month + "/" + day + "/" + year + " " + hour + ":" + minute;
		return str;
	}
	
	public boolean equals(Date other) {
		if(this.year != other.year)
			return false;
		
		if(this.month != other.month)
			return false;
		
		if(this.day != other.day)
			return false;
		
		if(this.hour != other.hour)
			return false;
		
		if(this.minute != other.minute)
			return false;
		
		return true;
	}
	public boolean before(Date other) {
		if(this.year < other.year)
			return true;
		else if(this.year > other.year)
			return false;
		
		if(this.month < other.month)
			return true;
		else if(this.month > other.month)
			return false;

		if(this.day < other.day)
			return true;
		else if(this.day > other.day)
			return false;

		if(this.hour < other.hour)
			return true;
		else if(this.hour > other.hour)
			return false;

		if(this.minute < other.minute)
			return true;
		else if(this.minute > other.minute)
			return false;
		
		return false;
	}
}