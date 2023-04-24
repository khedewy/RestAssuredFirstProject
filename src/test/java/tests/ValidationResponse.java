package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ValidationResponse {
	
	@Test
	public void validateResponseCode() {
		
		given().when().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then()
		.assertThat().statusCode(200);
		
	}
	
	@Test
	public void validateContentType() {
		given().when().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().
		assertThat().contentType(ContentType.XML);
	}

	
	@Test
	public void validateResponseData() {
		given().when().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/")
		.then().assertThat().body("customer.id", equalTo("12212"))
		.and().assertThat().body("customer.firstName",equalTo("John"))
		.and().assertThat().body("customer.lastName",equalTo("Smith")).
		and().assertThat().body("customer.address.street", equalTo("1431 Main St"))
		.and().assertThat().body("customer.address.city", equalTo("Beverly Hills"))
		.and().assertThat().body("customer.address.state", equalTo("CA"))
		.and().assertThat().body("customer.address.zipCode", equalTo("90210"))
				.and().assertThat().body("customer.phoneNumber",equalTo("310-447-4121"))
				.and().assertThat().body("customer.ssn",equalTo("622-11-9999"));
		
	}

}
