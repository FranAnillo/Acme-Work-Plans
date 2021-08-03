package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AdministratorDashboardTest extends AcmePlannerTest {
	
	//Este metodo prueba que se muestre una lista de los Shout
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void listDashboard(final String publicTask, final String privateTask, final String finishTask,
		final String notFinishTask, final String minimumWorkload, final String maximumWorkload, final String averageWorkload, final String deviationWorkload,
		final String averageExecutionPeriods) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");		
		
		super.checkInputBoxHasValue("numberOfPublicTask", publicTask);
		super.checkInputBoxHasValue("numberOfPrivateTask", privateTask);
		super.checkInputBoxHasValue("numberOfFinishTask", finishTask);
		super.checkInputBoxHasValue("numberOfNotFinishTask", notFinishTask);
		super.checkInputBoxHasValue("minimumWorkload", minimumWorkload);
		super.checkInputBoxHasValue("maximumWorkload", maximumWorkload);
		super.checkInputBoxHasValue("averageWorkload", averageWorkload);
		super.checkInputBoxHasValue("deviationWorkload", deviationWorkload);
		super.checkInputBoxHasValue("averageExecutionPeriods", averageExecutionPeriods);
	}



}
	


