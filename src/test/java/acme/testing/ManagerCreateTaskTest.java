package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ManagerCreateTaskTest extends AcmePlannerTest {
	//Este metodo prueba que se crea correctamente una task del manager con los datos pertinentes
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final String title, final String description,final String workload,final String start, final String end, final String link) {		
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Register");
		super.clickOnMenu("Manager", "Create Task");	
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Create");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
//		super.checkSimplePath("/master/welcome");
		super.signOut();
	}
	//Este metodo prueba que no se crea correctamente una task del manager por meter de forma incorrecta algun dato
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String title, final String description,final String workload,final String start, final String end, final String link) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Register");
		super.clickOnMenu("Manager", "Create Task");	
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Create");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
//		super.checkSimplePath("/manager/task/create");
		super.signOut();

	}

}

