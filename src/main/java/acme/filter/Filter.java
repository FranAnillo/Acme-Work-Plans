package acme.filter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.datatypes.Workload;
import acme.features.administrator.personalization.AdministratorPersonalizationRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;

public class Filter {


	// Internal state

		@Autowired
		protected  AdministratorPersonalizationRepository personalizationRepository;
		
		@Autowired
		protected AdministratorThresholdRepository			thresholdRepository;
		
		List<String> censoredWords=  this.personalizationRepository.findCensoredWords();
	
	
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

}