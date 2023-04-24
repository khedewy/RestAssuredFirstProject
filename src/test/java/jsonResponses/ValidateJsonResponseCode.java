package jsonResponses;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ValidateJsonResponseCode {

    @Test
    public void validateResponseCode(){
        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void validateResponseType(){
        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void ValidateResponseData(){
        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().body("data[0].'id'",equalTo(7)).and()
                .assertThat().body("page",equalTo(2)).and()
                .assertThat().body("data[0].'email'",equalTo("michael.lawson@reqres.in"))
                .and().assertThat().body("data[2].'id'",equalTo(9))
                .and().assertThat().body("data[0].'last_name'",equalTo("Lawson"));
    }

    @Test
    public void validateInvalidData(){
        String value = get("https://reqres.in/api/users?page=100000").andReturn().asString();
        Assert.assertTrue(value.contains("contributions towards server costs are appreciated!"));
    }

}
