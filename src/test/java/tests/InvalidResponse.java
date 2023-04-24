package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class InvalidResponse {
    @Test
    public void validateInvalidResponse(){
        String response = get("http://parabank.parasoft.com/parabank/services/bank/customers/12214/").asString();
        Assert.assertEquals(response,"Could not find customer #12214");
    }
}
