package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AdministratorBannedWordTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/personalization/newCensoredWord.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void newCensoredWord(final String word, final String author, final String text, final String info) {
		
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Censored words");	
		super.fillInputBoxIn("censoredWords", word);
		super.clickOnSubmitButton("Create");
		super.signOut();
		
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		super.checkErrorsExist();
		
	}
	
	
}
