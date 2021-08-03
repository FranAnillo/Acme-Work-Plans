/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class UpdateAccountTest extends AcmePlannerTest {
	
	//Este metodo prueba un caso positivo de la actualizacion de un usuario
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSignUp(final String username, final String password, final String name, final String surname, final String email,
		final String newPassword, final String newName, final String newSurname, final String newEmail) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "General data");
		super.fillInputBoxIn("password", newPassword);
		super.fillInputBoxIn("confirmation", newPassword);
		super.fillInputBoxIn("identity.name", newName);
		super.fillInputBoxIn("identity.surname", newSurname);
		super.fillInputBoxIn("identity.email", newEmail);
		super.clickOnSubmitButton("Update");
		super.signOut();
		super.signIn(username, newPassword);
		super.signOut();
}
}