package ecom.bitm.SwagLabsLogin;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;


import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;
import ecom.bitm.genericUtility.ScreenshotUtility;



public class LoginPageNegative extends BaseClass {

    @Test(dataProvider = "negativeLoginData",
            dataProviderClass = DataProviderUtility.class,groups = {"FT", "Negative"})
    public void verifyLoginWithInvalidCredentials(String Username,
            String Password) {

        SwagLabsLogin login = new SwagLabsLogin(driver);

        // Enter invalid username and valid password
        login.login(Username, Password);

        // Verify that login is unsuccessful
        Assert.assertFalse(
            driver.getCurrentUrl().contains("inventory.html"),
            "Login should not succeed with invalid credentials"
        );

        // Verify the error message
        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"),
            "Expected error message was not displayed"
        );
        ScreenshotUtility.takeScreenshot(
    		    driver, "Negative_Login"
    		);

        Reporter.log(
            "Negative test passed: Login failed with invalid credentials",
            true
        );
        
    }
}