package acme.components;


import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import acme.datatypes.Workload;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;

public class WorkloadFormatter implements Formatter<Workload> {
	
	// Formatter<Workload> interface ---------------------------------------------
	@Override
	public String print(final Workload workload, final Locale locale) {
		assert workload != null;
		assert locale != null;

		String result;

		String hour,minute;
	
		hour = String.format("%s",workload.getHours());
		minute = workload.getMinutes()==0||workload.getMinutes()==null ?"":String.format("%s",workload.getMinutes());
		
		if  (workload.getMinutes()==0)result = String.format("%s.00", hour);
		else if (workload.getMinutes()<10)result = String.format("%s.0%s", hour, minute);
		else result = String.format("%s.%s", hour, minute);
		
		return result;
	}
	@Override
	public Workload parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		Workload result;
		String regex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String hoursText,minutesText;
		String hoursRegex,minutesRegex;
		int hours, minutes;
		
		hoursRegex= "\\d{1,2}";
		minutesRegex= "\\d{2}";
		
		regex = String.format("^\\s*(?<H>%1$s)\\.(?<M>%2$s)\\s*$",hoursRegex,minutesRegex);
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

		matcher = pattern.matcher(text);
				
		final boolean regexe1=!matcher.find();

		if (regexe1) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(0, errorMessage);
		} else {
				hoursText =matcher.group("H");
				hours = Integer.valueOf(hoursText);

				minutesText =matcher.group("M");
				minutes = Integer.valueOf(minutesText);
				
				result = new Workload();
				result.setHours(hours);
				result.setMinutes(minutes);
				
		}

		return result;
		
	}
	

}
