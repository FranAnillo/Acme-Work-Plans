package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ManagDeleteTaskTest extends AcmePlannerTest {
	//Este metodo prueba que se crea correctamente una task del manag con los datos pertinentes
	@ParameterizedTest
	@CsvFileSource(resources = "/manag/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deletePositive(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {		
		
		super.signIn("manag5", "manag5");
		super.clickOnMenu("Manager", "My Tasks");	
		
		// Checkea que se cumple que las columnas coinciden con los valores que indicamos
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, start);
		super.checkColumnHasValue(recordIndex, 4, end);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload",workload);
		super.checkInputBoxHasValue("start",start);
		super.checkInputBoxHasValue("end",end);
		super.checkInputBoxHasValue("link",link);
		super.clickOnSubmitButton("Delete");
		
		super.signOut(); 
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manag/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTasksNegativemanag(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {		

		// Se logea con el usuario manag
		super.signIn("manag2", "manag2");

		// Clica en el menú para acceder a las tareas del manag
		this.driver.get("localhost:8080/Acme-Work-Plans/manag/task/delete?id=20");
		
		super.checkPanicExists();
		
		super.signOut();
	}

}

