package com.conf.scheduleMgmt.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.enblom.time.TimeOfDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All the time manipulation is done with the help of this class
 * 
 */
public class TimeUtils {
	private static Logger log = LoggerFactory.getLogger(TimeUtils.class
			.getName());

	public static TimeOfDay addMins(String stringFmt, int duration) {
		return convertStringToTime(stringFmt).plusMinutes(duration);

	}

	public static String convertTimeToString(TimeOfDay timeOfDay) {

		return timeOfDay.toString().substring(0,
				timeOfDay.toString().lastIndexOf(":"));
	}

	public static TimeOfDay convertStringToTime(String stringFmt) {

		return TimeOfDay.factory.parse(stringFmt.concat(":00"));
	}

	public static boolean isGreaterThan4PM(String stringFmt) {
		return convertStringToTime(stringFmt).isLaterMinuteThan(
				TimeOfDay.factory.parse(16, 00, 00));
	}

	public static String convertTo12HrFormat(String strformat) {
		Date date;
		String now = "";
		try {
			date = new SimpleDateFormat("HH:mm").parse(strformat);
			now = new SimpleDateFormat("hh:mmaa").format(date);

		} catch (ParseException e) {
			log.error("Error occurred while Parsing the date", e);
		}

		return now;

	}

}
