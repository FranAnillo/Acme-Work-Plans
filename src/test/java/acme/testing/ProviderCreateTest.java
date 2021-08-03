package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ProviderCreateTest extends AcmePlannerTest{
	//Este metodo prueba que se crea de forma correcta un provider introduciendo los datos pertinentes
	@ParameterizedTest
	@CsvFileSource(resources = "/provider/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createProviderPositive(final int recordIndex, final String company, 
		final String sector) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a provider");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		super.signOut();

	}
	
}
