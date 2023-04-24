package jsonResponses;

import data.ReadDataForJsonResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;

public class ValidateJsonDataWithExcel {

    @DataProvider
    public Object[][] responseData() throws IOException {
        ReadDataForJsonResponse readData = new ReadDataForJsonResponse();
        return readData.getExcelData("sheet1");
    }

    @Test(dataProvider = "responseData")
    public void validateData(String email, String firstName, String lastName, String avatar){
        String response = get("https://reqres.in/api/users?page=2").andReturn().asString();
        Assert.assertTrue(response.contains(email));
        Assert.assertTrue(response.contains(firstName));
        Assert.assertTrue(response.contains(lastName));
        Assert.assertTrue(response.contains(avatar));
    }
}
