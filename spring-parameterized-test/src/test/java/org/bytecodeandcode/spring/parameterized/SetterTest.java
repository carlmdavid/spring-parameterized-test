package org.bytecodeandcode.spring.parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.bytecodeandcode.spring.batch.persistence.domain.Person;
import org.bytecodeandcode.spring.parameterized.service.PersonService;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@SpringApplicationConfiguration(Application.class)
public class SetterTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    
    @Autowired
    private PersonService personService;

	@Test
//	@Parameters({"John", "Martha", "Sue", "Deborah"})
	@Parameters
	public void test(String firstName
			, String lastName
			) {
		assertThat(firstName, notNullValue());
		assertThat(lastName, notNullValue());
		
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		
		Person savedPerson = personService.savePerson(p);
		
		assertThat(personService.findPersonById(savedPerson.getPersonId()), is(notNullValue()));
		
		personService.deletePersonById(savedPerson.getPersonId());
		
		assertThat(personService.countPersons(), is(equalTo(0l)));
	}

    public static Collection<Object[]> parametersForTest() {
    	 return Arrays.asList(new Object[][] {
        	{"John", "Doe"},
            {"John", "Smith"},
            {"Deborah", "Johnson"},
            {"Jan", "Kowalski"}
        });
    }

	/*public Object[] params() {
		return new Object[] {"John", "Martha", "Sue", "Deborah"};
	}*/

}
