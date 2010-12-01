package com.cs446teameo.UI;

import java.util.Calendar;

import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;

import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Profile.ProfileManager;

public class CalendarSetting {

	public static final int EVENT_OCCURS_BEFORE = 0, EVENT_OCCURS_AFTER = 1, NO_EVENT_OCCURS = 2, EVENT_OCCURS_SAME = 3,  NO_EVENT_OCCURS_BUT_TODAY = 4;
    public static final int pastEventColor = Color.rgb(51, 51, 204);
    public static final int presentEventColor = Color.rgb(51, 204, 51);
    public static final int futureEventColor = Color.rgb(153, 51, 153);
	
	// Clear the color, and style of a button
	public static void clearStyle (Button button)
	{
		button.setTextColor(Color.BLACK);
		button.setTextSize(14);
		button.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
		Drawable d = button.getBackground();
		button.invalidateDrawable(d);
		d.clearColorFilter();
	}
	
	// Set the color and style for the present event
	public static void setCurrentStyle(Button button)
	{
		button.setTextColor(Color.RED);
		button.setTextSize(16);
		button.setTypeface(Typeface.SERIF, Typeface.BOLD);
	}
	
	// Set the color and style for the present event
	public static void setPresentStyle(Button button)
	{
		button.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
		Drawable d = button.getBackground();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(presentEventColor, Mode.SRC_ATOP );
        d.setColorFilter(filter);
	}

	// Set the color and style for the past event
	public static void setPastStyle(Button button)
	{
		button.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
		Drawable d = button.getBackground();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(pastEventColor, Mode.SRC_ATOP );
        d.setColorFilter(filter);
	}
	
	// Set the color and style for the past event
	public static void setFutureStyle(Button button)
	{
		button.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
		Drawable d = button.getBackground();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(futureEventColor, Mode.SRC_ATOP );
        d.setColorFilter(filter);
	}

	// Set the event's style based on whether they are past, present or future from today
	public static void setEventsTime(int eventOccurs, Button button)
	{
		if(eventOccurs == EVENT_OCCURS_BEFORE)
		{
			setPastStyle(button);
		}
		else if(eventOccurs == EVENT_OCCURS_AFTER)
		{
			setFutureStyle(button);
		}
		else if(eventOccurs == EVENT_OCCURS_SAME)
		{
			setPresentStyle(button);
		}
		else if(eventOccurs == NO_EVENT_OCCURS_BUT_TODAY)
		{
			setCurrentStyle(button);
		}
	}
	
	// Check to see when an event occurs
	public static int eventOccursWhen(int i, int calendarType, Calendar c)
	{
		// Create a calendar for today
		Calendar today = Calendar.getInstance();
		
		// Create a temporary calendar
		Calendar tempCalendar = (Calendar)c.clone();
		
		// Set the calendar to the month/day being set
		tempCalendar.set(calendarType, i);
		
		// If any event occurs on the temporary calendar's year
		if(calendarType == Calendar.MONTH)
		{
			if(EventManager.getInstance().eventOccursOnMonth(tempCalendar))
			{
				// If this month occurs before today
				if(tempCalendar.before(today))
				{
					return EVENT_OCCURS_BEFORE;
				}
				else if(tempCalendar.equals(today))
				{
					return EVENT_OCCURS_SAME;
				}
				else
				{
					return EVENT_OCCURS_AFTER;
				}
			}
			else if (monthEquals(tempCalendar, today))
			{
				return NO_EVENT_OCCURS_BUT_TODAY;
			}
		}
		else if(calendarType == Calendar.DAY_OF_MONTH)
		{
			if(EventManager.getInstance().eventOccursOnDay(tempCalendar))
			{
				// If this day occurs before today
				if(tempCalendar.before(today))
				{
					return EVENT_OCCURS_BEFORE;
				}
				else if(tempCalendar.equals(today))
				{
					return EVENT_OCCURS_SAME;
				}
				else
				{
					return EVENT_OCCURS_AFTER;
				}
			}
			else if (dayEquals(tempCalendar, today))
			{
				return NO_EVENT_OCCURS_BUT_TODAY;
			}
		}
		
		Log.d("Calendar Setting", "Temp: " + tempCalendar.get(Calendar.DAY_OF_MONTH) + "/" + tempCalendar.get(Calendar.MONTH) + "/" + tempCalendar.get(Calendar.YEAR));
		Log.d("Calendar Setting", "Today: " + today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.MONTH) + "/" + today.get(Calendar.YEAR));
		
		
		// No event occurs
		return NO_EVENT_OCCURS;
	}
	
	// Compare equality of two days
	public static boolean dayEquals(Calendar c1, Calendar c2)
	{
		return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
						&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
						&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}
	
	// Compare equality of two months
	public static boolean monthEquals(Calendar c1, Calendar c2)
	{
		return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}
}
