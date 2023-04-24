package tests;

import data.ReadData;
import io.restassured.path.xml.XmlPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;

public class ValidateXMLDataResponseWithExcel {

    @DataProvider()
    public Object[][] responseData() throws IOException {
        ReadData data = new ReadData();
        return data.getExcelData("sheet1");
    }

    @Test(dataProvider = "responseData",alwaysRun = true)
    public void validateResponseData(String customerId, String firstName, String lasName, String street, String city
             , String state,String zipCode, String phoneNumber, String ssn){

        String xml = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").andReturn().asString();
        XmlPath xmlPath = new XmlPath(xml).setRoot("customer");
        String id = xmlPath.getString("id");
        String f_name = xmlPath.getString("firstName");
        String l_name = xmlPath.getString("lastName");
        String st = xmlPath.getString("address.street");
        String City = xmlPath.getString("address.city");
        String State = xmlPath.getString("address.state");
        String z_code = xmlPath.getString("address.zipCode");
        String PhoneNumber = xmlPath.getString("phoneNumber");
        String SSN = xmlPath.getString("ssn");
        Assert.assertEquals(id,customerId);
        Assert.assertEquals(f_name,firstName);
        Assert.assertEquals(l_name,lasName);
        Assert.assertEquals(st,street);
        Assert.assertEquals(City,city);
        Assert.assertEquals(State,state);
        Assert.assertEquals(z_code,zipCode);
        Assert.assertEquals(PhoneNumber,phoneNumber);
        Assert.assertEquals(SSN,ssn);
    }
}
