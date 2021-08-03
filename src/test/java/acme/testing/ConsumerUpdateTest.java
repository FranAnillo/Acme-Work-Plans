package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ConsumerUpdateTest extends AcmePlannerTest {
	//Este metodo prueba que se actualice un consumidor correctamente con los datos pertinentes 
	@ParameterizedTest
	@CsvFileSource(resources = "/consumer/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void PcreateConsumer(final int recordIndex, final String company, 
		final String sector,final String newCompany, 
		final String newSector) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a provider");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Account", "Provider data");
		super.fillInputBoxIn("company", newCompany);
		super.fillInputBoxIn("sector", newSector);

		super.signOut();
	}	

}
