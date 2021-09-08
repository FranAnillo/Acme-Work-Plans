package acme.filter;

import java.util.Date;
import java.util.List;

import acme.datatypes.Workload;

public class Filter {

private Filter() {
	
}
	// Internal state
	
		public static boolean calculate(final Date start, final Date end, final Workload workload) {
		return workload.getHours()*60+workload.getMinutes()<=(end.getTime() / 60000.00) - (start.getTime() / 60000.00);
		
	}

	public static double calculate(final Date start, final Date end) {
		double i = ((end.getTime() / 60000.00) - (start.getTime() / 60000.00));
		int k = 0;
		final double res= 60.00;
	while (i>=60) {
			k++;
			i=i-res;

	}
		i= i/100;
		i= k+i;
		return i;
	}
	
	
	
	
	
	
	
	public static boolean filterString(final String s,final List<String> censoredWords, final int thresholdRepository) {
		final String l =s.toLowerCase();
        final String j = l.replace(" ", ";");
        final String[] palabras = j.split(";");
        float numberBannedWords = 0;
        float numberOfWords = 0;

        for (int x = 0; palabras.length > x; x++) {
            if (!palabras[x].isEmpty()) {
                numberOfWords++;
            }
        }

        for (int i = 0; censoredWords.size() > i; i++) {
            for (int k = 0; palabras.length > k; k++) {
            	final StringBuilder banned=new StringBuilder();
            	banned.append(censoredWords.get(i).replace(" ", ";"));
                final int numberOfCensoredString =banned.toString().split(";").length;
                final StringBuilder bannedString= new StringBuilder();
                bannedString.append(palabras[k]);
                if (bannedString == null || bannedString.toString().isEmpty())
                    break;
                for (int w = 1; numberOfCensoredString > (w+i); w++) {     

                    if (k + w >= palabras.length|| palabras[k + w].isEmpty())
                        break;
                    bannedString.append(";"+ palabras[k + w]);
                }
                if (banned.toString().equals(bannedString.toString())) {
                    numberBannedWords = numberBannedWords + numberOfCensoredString;


                }

            }
        }
        
        if ((numberBannedWords * 100 / numberOfWords) >= thresholdRepository) {

            return false;
        }
        return true;

    }
    }
		

