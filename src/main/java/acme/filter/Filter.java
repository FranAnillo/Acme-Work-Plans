package acme.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import acme.features.administrator.personalization.AdministratorPersonalizationRepository;

public class Filter {

	// Serialisation identifier -----------------------------------------------


		// Attributes -------------------------------------------------------------

		@Autowired
		protected  AdministratorPersonalizationRepository repository;
		
		@NotNull
		protected String censoredWord;
		
		@NotNull
		static int umbral = 10;
		
		
		
		
		
		public void addWord(final String censoredWord) {
			final List<String> res = this.censoredWords();
			res.add(censoredWord);
		}
		
	public List<String> censoredWords(){
	final List<String> res = new ArrayList<>();
	res.add("sex");
	res.add("sexo");
	res.add("hard core");
	res.add("pornografía");
	res.add("duro");
	res.add("viagra");
	res.add("cialis");
	res.add("tadalafil");
	res.add("nigeria");
	res.add("you’ve won");
	res.add("has ganado");
	res.add("million dollar");
	res.add("million euros");
	res.add("millon de dolares");
	res.add("millon de euros");
	return res;
	}
	
	public boolean filterString(final String s) {
		final String j=s.replace(" ", ";");
		final int number = j.split(";").length;
		int numberBannedWords= 0;
		final List<String> censoredWords= this.repository.findCensoredWords();
		for(int i = 0; censoredWords.size()>i; i++) {
		if(s.toLowerCase().contains(censoredWords.get(i))) {
			numberBannedWords= numberBannedWords+1;
		}	
		}
		if(((float)numberBannedWords/number)*100> Filter.umbral) return false;
		
		return true;
	}
	
	public static double calculate(final Date start, final Date end) {
		return (end.getTime() / 3600000.00) - (start.getTime() / 3600000.00);
		
	}

}
