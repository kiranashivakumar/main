package utils;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]
        		// username, password, expectedButtonEnabled, expectedMessage

        		{
        	    {"", "", "DISABLED", ""},
                {"automation@generator1email.com", "Abc@123455","ERROR",ConfigReader.get("ExceptionMessageForLogin")},
                {"aun@generator1email.com", "Admin@123","ERROR",ConfigReader.get("ExceptionMessageForLogin")},
                {"", "Admin@123","DISABLED",""},
                {"automation@generator1email.com","","DISABLED", ""},
                {"kirana1234323@gmail.com","Admin@123","ERROR",ConfigReader.get("ExceptionMessageForLogin")}
        };
        
    }

}
