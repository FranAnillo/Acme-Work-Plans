package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateConsumerTest extends AcmePlannerTest {
	
	//Este metodo prueba un caso negativo de crear un consumidor
	
	@ParameterizedTest
	@CsvFileSource(resources = "/consumer/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createConsumer(final int recordIndex, final String company, 
		final String sector) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a consumer");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();

		super.signOut();
	}	
	
	//Este metodo prueba un caso positivo de crear un consumidor
	
	@ParameterizedTest
	@CsvFileSource(resources = "/consumer/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void PcreateConsumer(final int recordIndex, final String company, 
		final String sector) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a consumer");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");

		super.signOut();
	}	

}

