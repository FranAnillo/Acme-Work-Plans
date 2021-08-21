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

		String hour,minute,dot;



		
		
		hour = String.format("%s",workload.getHours());
		minute = workload.getMinutes()==null ?"":String.format("%s",workload.getMinutes());
		dot = workload.getMinutes()==null ?"":String.format("%s",".");	
		result = String.format("%s%s%s", hour,dot, minute);
		
		return result;
	}
	@Override
	public Workload parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		Workload result;
		String regex,hour,minute,punto;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String hoursText,minutesText;
		int hours, minutes;
		
		hour="\\d{0,2}" ;
		punto="\\.";
		minute="\\d{0,2})"; 
		
		
		regex = String.format("^\\s*(?<H>\\d{0,2})\\.(?<M>\\d{0,2}))\\s*$");
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

		matcher = pattern.matcher(text);
		if (!matcher.find()) {
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
